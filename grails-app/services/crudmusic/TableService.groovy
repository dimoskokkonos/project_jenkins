package crudmusic

import grails.gorm.transactions.Transactional
import groovy.sql.Sql
import java.sql.*
@Transactional
class TableService {
    def dataSource

    //TODO: ? ΣΕ $ στα insert μου!

    def tableCreation() {

        def sql = new Sql(dataSource)
        def createTableAlbum = """
            CREATE TABLE IF NOT EXISTS album (
                id SERIAL PRIMARY KEY,
                artist VARCHAR(50) NOT NULL,
                albumTitle VARCHAR(50) NOT NULL,
                songNumber NUMERIC, 
                releaseDate DATE
            )
            """

        def createTableGenre = """
            CREATE TABLE IF NOT EXISTS genres (
                id SERIAL PRIMARY KEY,
                name VARCHAR(50) NOT NULL,
                creator VARCHAR(50) DEFAULT 'unknown',
                isPopular BOOLEAN DEFAULT false
            )
            """
//asdaadsasda
        def createTableManyToMany = """
            CREATE TABLE IF NOT EXISTS album_genres (
                albumId INTEGER REFERENCES album(id) ON DELETE CASCADE,
                genreId INTEGER REFERENCES genres(id) ON DELETE CASCADE,
                PRIMARY KEY(albumId, genreId)
            )
            """
        sql.execute(createTableAlbum)
        sql.execute(createTableGenre)
        sql.execute(createTableManyToMany)
    }

    def insertInitialValue() {
        def sql = new Sql(dataSource)


        // Insert for the album table
        def parameters = [param1: 'BobbyZ', param2: 'The Fly Airplane', param3: 9, param4: '01/01/2000']
        def insertAlbum = """INSERT INTO album
            (artist, albumTitle, songNumber, releaseDate) VALUES
            (${parameters.param1}, ${parameters.param2}, ${parameters.param3}, TO_DATE(${parameters.param4}, \'DD/MM/YYYY\'))"""
        sql.execute(insertAlbum)

        parameters = [param1: 'John Smith', param2: 'Time and Relative DIS', param3: 13, param4: '11/08/1960']
        insertAlbum = """INSERT INTO album
            (artist, albumTitle, songNumber, releaseDate) VALUES
            (${parameters.param1}, ${parameters.param2}, ${parameters.param3}, TO_DATE(${parameters.param4}, \'DD/MM/YYYY\'))"""
        sql.execute(insertAlbum)

        parameters = [param1: 'Ethna', param2: 'The Hell Ladies', param3: 5, param4: '03/03/2013']
        insertAlbum = """INSERT INTO album
            (artist, albumTitle, songNumber, releaseDate) VALUES
            (${parameters.param1}, ${parameters.param2}, ${parameters.param3}, TO_DATE(${parameters.param4}, \'DD/MM/YYYY\'))"""
        sql.execute(insertAlbum)

        parameters = [param1: 'Miho', param2: 'Solo Material', param3: 1, param4: '02/01/2019']
        insertAlbum = """INSERT INTO album
            (artist, albumTitle, songNumber, releaseDate) VALUES
            (${parameters.param1}, ${parameters.param2}, ${parameters.param3}, TO_DATE(${parameters.param4}, \'DD/MM/YYYY\'))"""
        sql.execute(insertAlbum)


        //Insert for musical genres tables
        parameters = [param1: 'Funk', param2: 'The all lawyer band', param3: true]
        insertAlbum = """INSERT INTO genres 
            (name, creator, isPopular) VALUES 
            (${parameters.param1}, ${parameters.param2}, ${parameters.param3})"""
        sql.execute(insertAlbum)

        parameters = [param1: 'Rock', param2: 'The Rockerator', param3: false]
        insertAlbum = """INSERT INTO genres 
            (name, creator, isPopular) VALUES 
            (${parameters.param1}, ${parameters.param2}, ${parameters.param3})"""
        sql.execute(insertAlbum)

        parameters = [param1: 'K-Pop', param2: 'Kawai Corporation', param3: false]
        insertAlbum = """INSERT INTO genres 
            (name, creator, isPopular) VALUES 
            (${parameters.param1}, ${parameters.param2}, ${parameters.param3})"""
        sql.execute(insertAlbum)

        parameters = [param1: 'Metal', param2: 'Iron Man', param3: true]
        insertAlbum = """INSERT INTO genres 
            (name, creator, isPopular) VALUES 
            (${parameters.param1}, ${parameters.param2}, ${parameters.param3})"""
        sql.execute(insertAlbum)

        parameters = [param1: 'Jazz', param2: 'Slow Mo', param3: true]
        insertAlbum = """INSERT INTO genres 
            (name, creator, isPopular) VALUES 
            (${parameters.param1}, ${parameters.param2}, ${parameters.param3})"""
        sql.execute(insertAlbum)

        parameters = [param1: 'Traditional', param2: 'The People', param3: false]
        insertAlbum = """INSERT INTO genres 
            (name, creator, isPopular) VALUES 
            (${parameters.param1}, ${parameters.param2}, ${parameters.param3})"""
        sql.execute(insertAlbum)

        parameters = [param1: 'Trap', param2: 'IT IS A TRAP', param3: false]
        insertAlbum = """INSERT INTO genres 
            (name, creator, isPopular) VALUES 
            (${parameters.param1}, ${parameters.param2}, ${parameters.param3})"""
        sql.execute(insertAlbum)



        def insertSqlManyMany = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (?, ?)"""

        parameters = [param1: 1, param2: 1]
        insertAlbum = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertAlbum)
        parameters = [param1: 1, param2: 3]
        insertAlbum = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertAlbum)
        parameters = [param1: 1, param2: 4]
        insertAlbum = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertAlbum)
        parameters = [param1: 1, param2: 7]
        insertAlbum = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertAlbum)

        parameters = [param1: 2, param2: 1]
        insertAlbum = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertAlbum)
        parameters = [param1: 2, param2: 2]
        insertAlbum = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertAlbum)
        parameters = [param1: 2, param2: 6]
        insertAlbum = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertAlbum)

        parameters = [param1: 3, param2: 3]
        insertAlbum = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertAlbum)
        parameters = [param1: 3, param2: 4]
        insertAlbum = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertAlbum)
        parameters = [param1: 3, param2: 6]
        insertAlbum = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertAlbum)
        parameters = [param1: 3, param2: 7]
        insertAlbum = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertAlbum)

        parameters = [param1: 4, param2: 1]
        insertAlbum = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertAlbum)
        parameters = [param1: 4, param2: 2]
        insertAlbum = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertAlbum)
        parameters = [param1: 4, param2: 5]
        insertAlbum = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertAlbum)
        parameters = [param1: 4, param2: 6]
        insertAlbum = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertAlbum)
        parameters = [param1: 4, param2: 7]
        insertAlbum = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertAlbum)

    }

    def deleteTables() {
        def sql = new Sql(dataSource)

        sql.execute("DROP TABLE IF EXISTS album CASCADE ;")
        sql.execute("DROP TABLE IF EXISTS genres CASCADE;")
        sql.execute("DROP TABLE IF EXISTS album_genres CASCADE;")
    }

    def selectTables() {
        def sql = new Sql(dataSource)
        def dataOfAlbums = sql.rows('SELECT * FROM album')
        def dataOfGenres = sql.rows('SELECT * FROM genres')

        return [albumData: dataOfAlbums, genresData: dataOfGenres]
    }

    def manyGenres() {
        def sql = new Sql(dataSource)
        def albumsID = sql.rows('SELECT id FROM album')
        def dataOfAlbumsGenre =[]

        albumsID.each{id_tag ->
            def tempMap = []
            def selectQuery = """
                SELECT genres.name FROM genres 
                    INNER JOIN album_genres ON genres.id=album_genres.genreId
                    INNER JOIN album ON album.id=album_genres.albumId 
                    WHERE albumId=${id_tag.id}
                """;
            sql.eachRow(selectQuery) {row ->
                tempMap.add("$row.name")
            }
            dataOfAlbumsGenre.add(tempMap)
        }
        return [dataOfAlbumsGenre: dataOfAlbumsGenre]
    }


    def deleteRowAlbum(deleteId) {
        def sql = new Sql(dataSource)
        sql.execute("DELETE FROM album Where id=${deleteId}");
    }

    def deleteRowGenre(deleteId) {
        def sql = new Sql(dataSource)
        sql.execute("DELETE FROM genres Where id=${deleteId}");
    }
}
