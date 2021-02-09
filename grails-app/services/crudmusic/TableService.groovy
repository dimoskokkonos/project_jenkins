package crudmusic

import grails.gorm.transactions.Transactional
import groovy.sql.Sql

@Transactional
class TableService {
    def dataSource

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
                name VARCHAR(50) NOT NULL UNIQUE,
                creator VARCHAR(50) DEFAULT 'unknown',
                isPopular BOOLEAN DEFAULT false
            )
            """
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


        parameters = [param1: 'Funk', param2: 'The all lawyer band', param3: true]
        def insertGenre = """INSERT INTO genres 
            (name, creator, isPopular) VALUES 
            (${parameters.param1}, ${parameters.param2}, ${parameters.param3})"""
        sql.execute(insertGenre)

        parameters = [param1: 'Rock', param2: 'The Rockerator', param3: false]
        insertGenre = """INSERT INTO genres 
            (name, creator, isPopular) VALUES 
            (${parameters.param1}, ${parameters.param2}, ${parameters.param3})"""
        sql.execute(insertGenre)

        parameters = [param1: 'K-Pop', param2: 'Kawai Corporation', param3: false]
        insertGenre = """INSERT INTO genres 
            (name, creator, isPopular) VALUES 
            (${parameters.param1}, ${parameters.param2}, ${parameters.param3})"""
        sql.execute(insertGenre)

        parameters = [param1: 'Metal', param2: 'Iron Man', param3: true]
        insertGenre = """INSERT INTO genres 
            (name, creator, isPopular) VALUES 
            (${parameters.param1}, ${parameters.param2}, ${parameters.param3})"""
        sql.execute(insertGenre)

        parameters = [param1: 'Jazz', param2: 'Slow Mo', param3: true]
        insertGenre = """INSERT INTO genres 
            (name, creator, isPopular) VALUES 
            (${parameters.param1}, ${parameters.param2}, ${parameters.param3})"""
        sql.execute(insertGenre)

        parameters = [param1: 'Traditional', param2: 'The People', param3: false]
        insertGenre = """INSERT INTO genres 
            (name, creator, isPopular) VALUES 
            (${parameters.param1}, ${parameters.param2}, ${parameters.param3})"""
        sql.execute(insertGenre)

        parameters = [param1: 'Trap', param2: 'IT IS A TRAP', param3: false]
        insertGenre = """INSERT INTO genres 
            (name, creator, isPopular) VALUES 
            (${parameters.param1}, ${parameters.param2}, ${parameters.param3})"""
        sql.execute(insertGenre)

        parameters = [param1: 1, param2: 1]
        def insertSqlManyToMany = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertSqlManyToMany)
        parameters = [param1: 1, param2: 3]
        insertSqlManyToMany = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertSqlManyToMany)
        parameters = [param1: 1, param2: 4]
        insertSqlManyToMany = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertSqlManyToMany)
        parameters = [param1: 1, param2: 7]
        insertSqlManyToMany = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertSqlManyToMany)

        parameters = [param1: 2, param2: 1]
        insertSqlManyToMany = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertSqlManyToMany)
        parameters = [param1: 2, param2: 2]
        insertSqlManyToMany = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertSqlManyToMany)
        parameters = [param1: 2, param2: 6]
        insertSqlManyToMany = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertSqlManyToMany)

        parameters = [param1: 3, param2: 3]
        insertSqlManyToMany = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertSqlManyToMany)
        parameters = [param1: 3, param2: 4]
        insertSqlManyToMany = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertSqlManyToMany)
        parameters = [param1: 3, param2: 6]
        insertSqlManyToMany = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertSqlManyToMany)
        parameters = [param1: 3, param2: 7]
        insertSqlManyToMany = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertSqlManyToMany)

        parameters = [param1: 4, param2: 1]
        insertSqlManyToMany = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertSqlManyToMany)
        parameters = [param1: 4, param2: 2]
        insertSqlManyToMany = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertSqlManyToMany)
        parameters = [param1: 4, param2: 5]
        insertSqlManyToMany = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertSqlManyToMany)
        parameters = [param1: 4, param2: 6]
        insertSqlManyToMany = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertSqlManyToMany)
        parameters = [param1: 4, param2: 7]
        insertSqlManyToMany = """INSERT INTO album_genres 
            (albumId, genreId) VALUES 
            (${parameters.param1}, ${parameters.param2})"""
        sql.execute(insertSqlManyToMany)

    }

    def deleteTables() {
        def sql = new Sql(dataSource)

        sql.execute("DROP TABLE IF EXISTS album CASCADE ;")
        sql.execute("DROP TABLE IF EXISTS genres CASCADE;")
        sql.execute("DROP TABLE IF EXISTS album_genres CASCADE;")
    }

    def selectTables() {
        def sql = new Sql(dataSource)
        def dataOfAlbums = sql.rows('SELECT * FROM album ORDER BY id ASC')
        def dataOfGenres = sql.rows('SELECT * FROM genres ORDER BY id ASC')

        return [albumData: dataOfAlbums, genresData: dataOfGenres]
    }

    def manyGenres(albumsID) {
        def sql = new Sql(dataSource)
        def dataOfAlbumsGenre =[]

        albumsID.each{entry ->
            def id_tag = entry.id
            def tempMap = []
            def selectQuery = """
                SELECT genres.name FROM genres 
                    INNER JOIN album_genres ON genres.id=album_genres.genreId
                    INNER JOIN album ON album.id=album_genres.albumId 
                    WHERE albumId=${id_tag}
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

    def insertEntry(albumArtist, albumTitle, albumSongNumber, albumReleaseDate, albumGenres,
                    genreName, genreCreator, genrePopularity) {
        def sql = new Sql(dataSource)

        if (albumArtist) {
            def parameters = [param1: albumArtist, param2: albumTitle, param3: albumSongNumber, param4: albumReleaseDate]
            def insertAlbum = """INSERT INTO album
                (artist, albumTitle, songNumber, releaseDate) VALUES
                (${parameters.param1}, ${parameters.param2}, ${parameters.param3}, TO_DATE(${parameters.param4}, \'DD/MM/YYYY\'))"""
            def albumEntry = sql.executeInsert(insertAlbum)
            def idEntry = albumEntry[0][0]

            if (albumGenres[0].size() > 1) {
                albumGenres.each { genreNameForAlbum ->
                    def tempGenreId = sql.rows("""SELECT genres.id FROM genres WHERE genres.name = ${genreNameForAlbum}""")

                    insertAlbum = """INSERT INTO album_genres
                                        (albumId, genreId) VALUES
                                        (${idEntry}, ${tempGenreId.id[0]})"""
                    sql.execute(insertAlbum)
                }
            } else {
                def tempGenreId = sql.rows("""SELECT genres.id FROM genres WHERE genres.name = ${albumGenres}""")

                insertAlbum = """INSERT INTO album_genres
                                    (albumId, genreId) VALUES
                                    (${idEntry}, ${tempGenreId.id[0]})"""
                sql.execute(insertAlbum)
            }
        }

        if (genreName) {
            def parameters = [param1: genreName, param2: genreCreator, param3: genrePopularity]
            def insertGenre = """INSERT INTO genres 
                (name, creator, isPopular) VALUES 
                (${parameters.param1}, ${parameters.param2}, ${parameters.param3})"""
            sql.execute(insertGenre)
        }
    }

    def selectRow(idRow, whichTable) {
        def sql = new Sql(dataSource)

        if (whichTable == 'album') {
            def selectRowAlbum = """SELECT * FROM album WHERE album.id = ${Integer.parseInt(idRow)}"""
            def dataRowAlbum = sql.rows(selectRowAlbum)


            def insertGenresName = """SELECT name FROM genres"""
            def dataGenresName = sql.rows(insertGenresName)

            def selectQuery = """
                SELECT genres.name FROM genres 
                    INNER JOIN album_genres ON genres.id=album_genres.genreId
                    INNER JOIN album ON album.id=album_genres.albumId 
                    WHERE albumId=${Integer.parseInt(idRow)}
                """;
            def dataRowGenres = sql.rows(selectQuery)
            return [dataRowAlbum: dataRowAlbum, dataGenresName: dataGenresName, dataRowGenres: dataRowGenres]

        } else {
            def selectRowGenre = """SELECT * FROM genres WHERE genres   .id = ${Integer.parseInt(idRow) }"""
            def dataRowGenre = sql.rows(selectRowGenre)

            return [dataRowGenre: dataRowGenre]
        }
    }

    def updateRow(artist, albumTitle, songNumber, releaseDate, idOfAlbum, genreNames,
                  name, creator, isPopular, idOfGenre) {
        def sql = new Sql(dataSource)

        if (artist) {
            def updateAlbumRow = """UPDATE album SET 
                                    artist = ${artist}, albumTitle = ${albumTitle}, 
                                    songNumber = ${songNumber}, 
                                    releaseDate = TO_DATE(${releaseDate}, \'DD/MM/YYYY\') 
                                    WHERE id = ${idOfAlbum} """
            sql.execute(updateAlbumRow)

            def deleteRowsInAlbumGenreRelation = """DELETE FROM album_genres WHERE albumId = ${idOfAlbum}"""
            sql.execute(deleteRowsInAlbumGenreRelation)

            genreNames.each { genreName ->
                def idQuery = """SELECT genres.id FROM genres WHERE genres.name = ${genreName}"""
                def idGenre = sql.rows(idQuery)

                def insertSqlManyToMany = """INSERT INTO album_genres 
                                    (albumId, genreId) VALUES 
                                     (${idOfAlbum}, ${idGenre[0].id})"""
                sql.execute(insertSqlManyToMany)
            }
        } else {
            def isPopularBool = false
            if (isPopular == 'true') {
                isPopularBool = true
            }

            def updateAlbumRow = """UPDATE genres SET 
                                name = ${name}, 
                                creator = ${creator}, 
                                isPopular = ${isPopularBool} 
                                WHERE id = ${idOfGenre} """
            sql.execute(updateAlbumRow)
        }
    }

    def selectWithSearchFeature (searchStr, whichTable) {
        def sql = new Sql(dataSource)

        def strForPattern = /(.*)${searchStr}(.*)/
        def dataOfAlbum = [], dataOfGenres = []
        if (whichTable=='album') {
            def dataOfAlbumAll = sql.rows('SELECT * FROM album ORDER BY id ASC')
            dataOfGenres = sql.rows('SELECT * FROM genres ORDER BY id ASC')

            dataOfAlbumAll.each {dataRow ->
                if (dataRow.albumtitle ==~ strForPattern) {
                    dataOfAlbum.add(dataRow)
                }
            }

        } else {
            dataOfAlbum = sql.rows( 'SELECT * FROM album ORDER BY id ASC')
            def dataOfGenresAll = sql.rows('SELECT * FROM genres ORDER BY id ASC')

            dataOfGenresAll.each {dataRow ->
                if (dataRow.name ==~ strForPattern) {
                    dataOfGenres.add(dataRow)
                }
            }


            def searchSelectQuery = """SELECT * FROM genres WHERE genres.name LIKE  '${strForLike}' ORDER BY id ASC"""
            dataOfGenres = sql.rows(searchSelectQuery)
        }

        return [albumData: dataOfAlbum, genresData: dataOfGenres]
    }

    def initialSelect() {
        def selectOfTablesAlbumGenres = selectTables()
        def selectOfAlbumGenresRelation = manyGenres(selectOfTablesAlbumGenres.albumData)
        def albumDataWithGenre = []

        selectOfTablesAlbumGenres.albumData.eachWithIndex{rowData, i ->
            def interiorDataMap = []
            interiorDataMap.add(rowData.id)
            interiorDataMap.add(rowData.artist)
            interiorDataMap.add(rowData.albumTitle)
            interiorDataMap.add(rowData.songNumber)
            interiorDataMap.add(rowData.releaseDate)
            interiorDataMap.add(selectOfAlbumGenresRelation.dataOfAlbumsGenre[i].join(", "))

            albumDataWithGenre.add(interiorDataMap)
        }

        return [genreData: selectOfTablesAlbumGenres.genresData, albumData: albumDataWithGenre]
    }
}
