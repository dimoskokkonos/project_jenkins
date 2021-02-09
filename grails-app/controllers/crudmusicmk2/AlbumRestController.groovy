package crudmusicmk2

import grails.converters.JSON

class AlbumRestController {
//    def tableService
    def tableRestService

    def index() { }

    def showAlbums() {
        def albumsTable = tableRestService.selectAllAlbum()
        if (params.id) {
            def checkId = params.id as Integer
            def output = albumsTable.albumData.findAll{ it.id == checkId}

            if (output == []) {
                return response.sendError(404, "Album ID was not found")
            }
            render output[0] as JSON
        }
        render albumsTable.albumData as JSON
    }

    def showGenres() {
        def genresTable = tableRestService.selectAllGenres()
        if (params.id) {
            def checkId = params.id as Integer
            def output = genresTable.genresData.findAll{ it.id == checkId}

            if (output == []) {
                return response.sendError(404, "Album ID was not found")
            }
            render output[0] as JSON
        }
        render genresTable.genresData as JSON
    }

    def createAlbums() {
        def insertOutput = tableRestService.insertAlbums(params.artist, params.albumTitle, params.songNumber as Integer,
                                                            params.releaseDate)
        render insertOutput as JSON
    }

    def createGenres() {
        def insertOutput = tableRestService.insertGenres(params.name, params.creator, params.isPopular)
        render insertOutput as JSON
    }

    def createGenresOfAlbum() {
        def insertOutput = tableRestService.insertGenresOfAlbum(params.idAlbum as Integer, params.idGenre as Integer)
        render insertOutput as JSON
    }

    def insert() { }

    def delete() { }

    def notFound() {
        def output = [error: "This is an error 404.. You efforts were fruitless, everything is lost, nothing is found.."]
        render output as JSON
    }
}
