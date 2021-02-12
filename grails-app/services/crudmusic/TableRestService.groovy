package crudmusic

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import groovy.sql.Sql

@Transactional
class TableRestService {
    def dataSource

    def selectAlbumsQuery() {
        def sql = new Sql(dataSource)
        def dataOfAlbums = sql.rows('SELECT * FROM album ORDER BY id ASC')
        return [albumData: dataOfAlbums]
    }

    def selectAllGenresOfAlbum() {
        def sql = new Sql(dataSource)
        def dataOfAlbumGenres = sql.rows('SELECT * FROM album_genres ORDER BY albumId ASC')
        return [albumGenresData: dataOfAlbumGenres]
    }

    def nameOfGenresInAlbum(albumsTable) {
        def sql = new Sql(dataSource)
        def dataOfAlbumsGenre =[]


        albumsTable.each{entry ->
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

    def selectAllAlbum() {
        def dataOfAlbums = selectAlbumsQuery()
        def genresNamesOfAlbum = nameOfGenresInAlbum(dataOfAlbums.albumData)
        def albumDataWithGenre = mergeAlbumWithGenres(dataOfAlbums.albumData, genresNamesOfAlbum)

        return albumDataWithGenre
    }

    def mergeAlbumWithGenres(dataOfAlbums, genresName) {
        def albumDataWithGenre = []


        dataOfAlbums.eachWithIndex{rowData, i ->

            def interiorDataMap = [id: rowData.id, artist: rowData.artist, artist: rowData.artist, albumTitle: rowData.albumTitle,
                                   songNumber: rowData.songNumber, releaseDate: rowData.releaseDate,
                                   genres: genresName.dataOfAlbumsGenre[i].join(", ")]
            albumDataWithGenre.add(interiorDataMap)
        }
    }

    def selectAllGenres() {
        def sql = new Sql(dataSource)
        def dataOfGenres = sql.rows('SELECT * FROM genres ORDER BY id ASC')

        return [genresData: dataOfGenres]
    }



    def insertAlbums(albumArtist, albumTitle, albumSongNumber, albumReleaseDate) {
        def sql = new Sql(dataSource)

        def parameters = [param1: albumArtist, param2: albumTitle, param3: albumSongNumber, param4: albumReleaseDate]
        def insertAlbum = """INSERT INTO album
                (artist, albumTitle, songNumber, releaseDate) VALUES
                (${parameters.param1}, ${parameters.param2}, ${parameters.param3}, TO_DATE(${parameters.param4}, \'DD/MM/YYYY\'))"""
        def insertOutput = sql.executeInsert(insertAlbum)

        return insertOutput[0]
    }

    def insertGenres(genreName, genreCreator, genrePopularity) {
        def sql = new Sql(dataSource)

        def popularity = false
        if (genrePopularity == 'true') { popularity = true }

        def parameters = [param1: genreName, param2: genreCreator, param3: popularity]
        def insertGenre = """INSERT INTO genres 
                (name, creator, isPopular) VALUES 
                (${parameters.param1}, ${parameters.param2}, ${parameters.param3})"""
        def insertOutput = sql.executeInsert(insertGenre)

        return insertOutput[0]
    }

    def insertGenresOfAlbum(idAlbum, idGenre) {
        def sql = new Sql(dataSource)

        def parameters = [param1: idAlbum, param2: idGenre]
        def insertGenreOfAlbum = """INSERT INTO album_genres
                                    (albumId, genreId) VALUES
                                    (${parameters.param1}, ${parameters.param2})"""
        def insertOutput = sql.executeInsert(insertGenreOfAlbum)

        return insertOutput[0]
    }

    def updateAlbum(idAlbum, parameters) {
        def sql = new Sql(dataSource)

        def updateAlbumRow = """UPDATE album SET 
                                    artist = ${parameters.artist}, albumTitle = ${parameters.albumTitle}, 
                                    songNumber = ${parameters.songNumber}, 
                                    releaseDate = TO_DATE(${parameters.releaseDate}, \'DD/MM/YYYY\') 
                                    WHERE id = ${idAlbum} """
        return sql.executeUpdate(updateAlbumRow)
    }

    def updateGenres(idGenres, parameters) {
        def sql = new Sql(dataSource)

        def popularity = false
        if (parameters.isPopularBool == 'true') { popularity = true }

        def updateGenreRow = """UPDATE genres SET 
                                name = ${parameters.name}, 
                                creator = ${parameters.creator}, 
                                isPopular = ${popularity} 
                                WHERE id = ${idGenres} """
        return sql.executeUpdate(updateGenreRow)
    }

    def deleteAlbums(idAlbum) {
        def sql = new Sql(dataSource)
        sql.execute("DELETE FROM album WHERE id=${idAlbum}");
    }

    def deleteGenres(idGenre) {
        def sql = new Sql(dataSource)
        sql.execute("DELETE FROM genres WHERE id=${idGenre}");
    }

    def deleteGenresOfAlbum(idAlbum) {
        def sql = new Sql(dataSource)
        sql.execute("DELETE FROM genres WHERE id=${idGenre}");
    }

    def albumsParamsValidator(albumsTable, paramsId, paramsAtr) {

        if (paramsAtr) {
            return albumsIdAndParam(paramsId, paramsAtr)
        }
        if (paramsId) {
            return albumsIdOnly(paramsId)
        }

        return albumsTable
    }

    def albumsIdOnly(paramsId) {

        if (paramsId.matches("[0-9]+")) {
            def output = selectAlbumsById(paramsId as Integer)
            return output.albumsDataByid
        } else {
            def output = selectAlbumsByColumns(paramsId)
            return output
        }

    }

    def selectAlbumsById(paramsId) {
        def sql = new Sql(dataSource)

        def selectQuery = """ SELECT * FROM album WHERE id=${paramsId} """;
        def dataOfAlbums = sql.rows(selectQuery)

        return [albumsDataByid: dataOfAlbums]
    }

    def selectAlbumsByColumns(paramsColumns) {
        def sql = new Sql(dataSource)

        if (albumContainsKey(paramsColumns)) {
            def selectQuery = """ SELECT """ + paramsColumns + """ FROM album""";
            def dataOfAlbums = sql.rows(selectQuery)

            return dataOfAlbums
        } else {
            return "ERROR FLAG"
        }
    }

    def albumContainsKey( paramsColumns ) {
        def sql = new Sql(dataSource)

        def dataOfAlbum = sql.rows('SELECT * FROM album LIMIT 1')
        def flagTheColumnsIsAKeyInAlbums = dataOfAlbum[0].keySet().contains( paramsColumns )
        return flagTheColumnsIsAKeyInAlbums
    }

    def albumsIdAndParam(paramsId, paramsColumn) {
        def sql = new Sql(dataSource)

        if (albumContainsKey(paramsColumn)) {
            def selectQuery = """ SELECT """ + paramsColumn + """ FROM album WHERE id=${paramsId} """;
            def dataOfAlbums = sql.rows(selectQuery)

            return dataOfAlbums[0]
        } else {
            return "ERROR FLAG"
        }
    }
}
