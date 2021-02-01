package crudmusic

import grails.gorm.transactions.Transactional
import groovy.sql.Sql
import java.sql.*
@Transactional
class TableService {

    
    def tableCreation() {
        //dbeaver

        def sql = new Sql(dataSource)
        //        def sql = Sql.newInstance("jdbc:postgresql://localhost:5432/crud",
//                "temp", "doubletemp", "org.postgresql.Driver")

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
        //FIXME: me yml application
//        def sql = Sql.newInstance("jdbc:postgresql://localhost:5432/crud",
//                "temp", "doubletemp", "org.postgresql.Driver")
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
//        def sql = Sql.newInstance("jdbc:postgresql://localhost:5432/crud",
//                "temp", "doubletemp", "org.postgresql.Driver")
        def sql = new Sql(dataSource)

        sql.withTransaction {
            sql.execute("DROP TABLE IF EXISTS album CASCADE ;");
            sql.execute("DROP TABLE IF EXISTS genres CASCADE;");
            sql.execute("DROP TABLE IF EXISTS album_genres CASCADE;");
        }

        sql.close();
    }

    def selectTables() {
        def sql = Sql.newInstance("jdbc:postgresql://localhost:5432/crud",
                "temp", "doubletemp", "org.postgresql.Driver")
        def data2 = []
        def data1
        def data3 = sql.rows('SELECT * FROM album')

        sql.query('SELECT * FROM album') { resultSet ->
            while (resultSet.next()) {
//                data2.add(resultSet.getString('artist'))
                data2.add(resultSet.getString('artist'))
            }
        }

//        sql.query('SELECT firstname, lastname FROM Author') { resultSet ->
//            while (resultSet.next()) {
//                def first = resultSet.getString(1)
//                def last = resultSet.getString('lastname')
//                assert expected[rowNum++] == "$first $last"
//            }
        return data3
    }
}
