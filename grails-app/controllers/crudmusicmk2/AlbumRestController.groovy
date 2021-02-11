package crudmusicmk2

import grails.converters.JSON

class AlbumRestController {
//    def tableService
    def tableRestService

    def index() { }

    def showAlbums() {
        def albumsTable = tableRestService.selectAllAlbum()
        if (params.id) {
            def output = []
            if (params.id.matches("[0-9]+")) {
                def checkId = params.id as Integer
                output = albumsTable.albumData.findAll{ it.id == checkId}

                if (output == []) { return response.sendError(401, "Album ID was not found") }
                render output[0] as JSON
            } else {
                if(albumsTable.albumData[0][params.id]) {
                    albumsTable.albumData.each{ rowOfAlbum ->
                        output.add(rowOfAlbum[params.id])
                    }
                    render output as JSON
                } else { return response.sendError(401, "The album table does not have this attribute") }
            }

            if (params.atr) {
                def attribute = params.atr
                if (!output[0][attribute]) { return response.sendError(401, "The album table does not have this attribute") }
                else {
                    def response = [output[0][attribute]]
                    render response as JSON
                }
            }
        }
        render albumsTable.albumData as JSON
    }

    def showGenres() {
        def genresTable = tableRestService.selectAllGenres()
        if (params.id) {
            def checkId
            if (params.id.matches("[0-9]+")) {
                println params.id
            } else {
                checkId = params.id as Integer
            }
            def output = genresTable.genresData.findAll{ it.id == checkId}

            if (output == []) {
                return response.sendError(401, "Genre ID was not found")
            }
            if (params.atr) {
                def attribute = params.atr
                if (!output[0][attribute]) {
                    return response.sendError(401, "The album genres does not have this attribute")
                } else {
                    def response = [output[0][attribute]]
                    render response as JSON
                }
            }
            render output[0] as JSON
        }
        render genresTable.genresData as JSON
    }

    def showGenresOfAlbum() {
        def albumGenresTable = tableRestService.selectAllGenresOfAlbum()

        def output
        if (params.id) {
            def checkId = params.id as Integer
            output = albumGenresTable.albumGenresData.findAll{ it.albumid == checkId}
            if (output == []) {
                return response.sendError(401, "Album ID was not found")
            }
        } else {
            output = albumGenresTable.albumGenresData
        }

        render output as JSON

    }

    def insertAlbums() {
        def insertOutput = tableRestService.insertAlbums(params.artist, params.albumTitle, params.songNumber as Integer,
                                                            params.releaseDate)
        render insertOutput as JSON
    }

    def insertGenres() {
        def insertOutput = tableRestService.insertGenres(params.name, params.creator, params.isPopular)
        render insertOutput as JSON
    }

    def insertGenresOfAlbum() {
        def insertOutput = tableRestService.insertGenresOfAlbum(params.idAlbum as Integer, params.idGenre as Integer)
        render insertOutput as JSON
    }

    def updateAlbums() {
        def parameters = [artist: params.artistName, albumTitle: params.title, songNumber: params.howManySongs as Integer, releaseDate: params.dateOfRelease]
        def status = tableRestService.updateAlbum(params.id as Integer, parameters)
        if (status == 0) {
            return response.sendError(400, "Album ID was not found")
        }
        render parameters as JSON
    }

    def updateGenres() {
        def parameters = [name: params.genreName, creator: params.genreCreator, isPopularBool: params.genrePopularity]

        def status = tableRestService.updateGenres(params.id as Integer, parameters)
        if (status == 0) {
            return response.sendError(400, "Album ID was not found")
        }
        render parameters as JSON
    }

    def deleteAlbums() {
        tableRestService.deleteAlbums(params.id as Integer)
    }

    def deleteGenres() {
        tableRestService.deleteGenres(params.id as Integer)
    }

    def deleteGenresOfAlbum() {
        tableRestService.deleteGenresOfAlbum(params.id as Integer)
    }

    def notFound() {
        def output = "This is an error 404.. You efforts were fruitless, everything is lost, nothing is found, including your page..."
        render output
    }

    def missingData() {
        def output = "Alert! Error 400 Alert!     Missformating in data names, inputs and/or types"
        render output
    }
    def error405() {
        def output = "Alert! Error 405 Alert!     The method is dissallowed!"
        render output
    }

    def errorAction() {
        return response.sendError(405, "This method is not allowed")
    }
}
