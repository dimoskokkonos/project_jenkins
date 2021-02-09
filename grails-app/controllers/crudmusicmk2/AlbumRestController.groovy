package crudmusicmk2

import grails.converters.JSON

class AlbumRestController {
    def tableService


    def index() { }

    def show() {
        println params

//        def block = [name: "JohnyBoi", occupation: "CoronaParty Planner", dependable: "Error 404"]
//        def data = tableService.selectTables()
        def selectOfTablesAlbumGenres = tableService.selectTables()
        def selectOfAlbumGenresRelation = tableService.manyGenres(selectOfTablesAlbumGenres.albumData)
        def albumDataWithGenre = []

        #FIXME: ίσως να μετατρεψω και του αρχικου project σε αυτη την μορφη?
        selectOfTablesAlbumGenres.albumData.eachWithIndex{rowData, i ->
            def interiorDataMap = [artist: rowData.artist, artist: rowData.artist, albumTitle: rowData.albumTitle,
                           songNumber: rowData.songNumber, releaseDate: rowData.releaseDate,
                           genres: selectOfAlbumGenresRelation.dataOfAlbumsGenre[i].join(", ")]
            albumDataWithGenre.add(interiorDataMap)
        }

        def outputMap = [genreData: selectOfTablesAlbumGenres.genresData, albumData: albumDataWithGenre]
        render outputMap as JSON

        render outputMap as JSON
    }

    def create() { }

    def insert() { }

    def delete() { }
}
