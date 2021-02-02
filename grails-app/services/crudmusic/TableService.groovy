package crudmusic

import grails.gorm.transactions.Transactional
import groovy.sql.Sql
import java.sql.*
@Transactional
class TableService {
    def dataSource


    def tableCreation() {
        def sql = new Sql(dataSource)


        def createTableAlbum = """
            CREATE TABLE IF NOT EXISTS album (
                id SERIAL PRIMARY KEY,
                artist VARCHAR(50) NOT NULL,
                album_title VARCHAR(50) NOT NULL,
                song_number NUMERIC, 
                release_date DATE
            );
            """

        def createTableGenre = """
            CREATE TABLE IF NOT EXISTS genres (
                id SERIAL PRIMARY KEY,
                name VARCHAR(50) NOT NULL,
                creator VARCHAR(50) DEFAULT 'unknown',
                is_popular BOOLEAN DEFAULT false
            );
            """

        def createTableManyToMany = """
            CREATE TABLE IF NOT EXISTS album_genres (
                album_id INTEGER REFERENCES album(id),
                genre_id INTEGER REFERENCES genres(id),
                PRIMARY KEY(album_id, genre_id)
            );
            """

        sql.execute(createTableAlbum);
        sql.execute(createTableGenre);
        sql.execute(createTableManyToMany);

        sql.close()
    }

    def insertInitialValue() {
        def sql = new Sql(dataSource)


        def insertSqlAlbum = """INSERT INTO album 
            (artist, album_title, song_number, release_date) VALUES 
            (?, ?, ?, TO_DATE(?, \'DD/MM/YYYY\'))""";

        def insertSqlGenre = """INSERT INTO genres 
            (name, creator, is_popular) VALUES 
            (?, ?, ?)""";

        def insertSqlManyMany = """INSERT INTO album_genres 
            (album_id, genre_id) VALUES 
            (?, ?)""";

        //Transaction for inserts in album
        sql.withTransaction {
            def paramsAlbum = ['BobbyZ', 'The Fly Airplane', 9, '01/01/2000'];
            sql.execute(insertSqlAlbum, paramsAlbum);
            paramsAlbum = ['John Smith', 'Time and Relative DIS', 13, '11/08/1960'];
            sql.execute(insertSqlAlbum, paramsAlbum);
            paramsAlbum = ['Ethna', 'The hell ladies', 5, '03/03/2013'];
            sql.execute(insertSqlAlbum, paramsAlbum);
            paramsAlbum = ['Miho', 'The solo comp', 1, '02/01/2019'];
            sql.execute(insertSqlAlbum, paramsAlbum);
        }

        //Transaction for inserts in genres
        sql.withTransaction {
            def paramsGenre = ['Funk', "", true];
            sql.execute(insertSqlGenre, paramsGenre);
            paramsGenre = ['Rock', "The rockerator", false];
            sql.execute(insertSqlGenre, paramsGenre);
            paramsGenre = ['K-Pop', "Kawai Corporation", false];
            sql.execute(insertSqlGenre, paramsGenre);
            paramsGenre = ['Metal', "IronMan", true];
            sql.execute(insertSqlGenre, paramsGenre);

        }

        //Transaction for inserts relational table
        sql.withTransaction {
            def paramsManyMany = [1, 1];
            sql.execute(insertSqlManyMany, paramsManyMany);
            paramsManyMany = [1, 3];
            sql.execute(insertSqlManyMany, paramsManyMany);
            paramsManyMany = [2, 1];
            sql.execute(insertSqlManyMany, paramsManyMany);
            paramsManyMany = [2, 2];
            sql.execute(insertSqlManyMany, paramsManyMany);
            paramsManyMany = [2, 4];
            sql.execute(insertSqlManyMany, paramsManyMany);
            paramsManyMany = [3, 2];
            sql.execute(insertSqlManyMany, paramsManyMany);
            paramsManyMany = [3, 4];
            sql.execute(insertSqlManyMany, paramsManyMany);
            paramsManyMany = [4, 1];
            sql.execute(insertSqlManyMany, paramsManyMany);
            paramsManyMany = [4, 3];
            sql.execute(insertSqlManyMany, paramsManyMany);

        }

        sql.close()
    }

    def deleteTables() {

        def sql = new Sql(dataSource)

        sql.withTransaction {
            sql.execute("DROP TABLE IF EXISTS album CASCADE ;");
            sql.execute("DROP TABLE IF EXISTS genres CASCADE;");
            sql.execute("DROP TABLE IF EXISTS album_genres CASCADE;");
        }

        sql.close();
    }

    def selectTables() {

        def sql = new Sql(dataSource)
        def data_of_albums = sql.rows('SELECT * FROM album')
        def data_of_genres = sql.rows('SELECT * FROM genres')


        return [ albumData: data_of_albums, genresData: data_of_genres]
    }

    def manyGenres() {

        //TODO: change the names, iterate for every id

        def sql = new Sql(dataSource)

        def albumsID = sql.rows('SELECT id FROM genres')

        def outer_list =[]
        albumsID.each{id_tag ->

            def inner_list = []
            def selectQuery = """
                SELECT genres.name FROM genres 
                    INNER JOIN album_genres ON genres.id=album_genres.genre_id
                    INNER JOIN album ON album.id=album_genres.album_id 
                    WHERE album_id=${id_tag.id}
                """;
            sql.eachRow(selectQuery) {row ->
                inner_list.add("$row.name")
            }
            outer_list.add(inner_list)

        }
        println ""

        println outer_list

//
//        println outer_list
//
//        def insertSqlManyMany = """
//            SELECT genres.name FROM genres
//                INNER JOIN album_genres ON genres.id=album_genres.genre_id
//                INNER JOIN album ON album.id=album_genres.album_id
//                WHERE album.id = ${id_tag}
//            """;
//        def list_thingy = []
//        def data_of_albums_genre = sql.rows(insertSqlManyMany).each{
//            row->
//                list_thingy.add("$row.name")
//        }
//        id_tag = 2
//        insertSqlManyMany = """
//            SELECT genres.name FROM genres
//                INNER JOIN album_genres ON genres.id=album_genres.genre_id
//                INNER JOIN album ON album.id=album_genres.album_id
//                WHERE album.id = ${id_tag}
//            """;
//        def list_thingy2 = []
//        data_of_albums_genre = sql.rows(insertSqlManyMany).each{
//            row->
//                list_thingy2.add("$row.name")
//        }
//
//        def list_thingy3 = []
//        list_thingy3.add(list_thingy)
//        list_thingy3.add(list_thingy2)
//
//        println list_thingy3
//        def list_thingy3 = []
        return [data_of_albums_genre: outer_list]
    }
}
