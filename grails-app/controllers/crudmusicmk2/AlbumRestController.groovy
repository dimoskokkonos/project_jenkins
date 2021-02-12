package crudmusicmk2

import grails.converters.JSON

class AlbumRestController {
//    def tableService
    // na kanw flowchart se xarti.. exw klisi: 1( tage validation.. exw params? an nai, vgazoun noima? Eimai ok? parakatw, den eimai, efuge (response
    // estw oti eimai ok (den exw parameters, i exw me swstes times). Kanw search stoxeumeno.. formating twn dedomenwn 9epistrefw sugkekrimeno attribute)
    // na kanw validate
    def tableRestService

    def hello() {
        render 'Hello, World!'
    }

    def index() { }

    def showAlbums() {
        def albumsTable = tableRestService.selectAllAlbum()
        def albumData = tableRestService.albumsParamsValidator(albumsTable, params.id, params.atr)

        if (albumData == [] || albumData== "ERROR FLAG") {
            response.sendError(404, "Album ID was not found")
        } else {
            render albumData as JSON
        }

    }

    def showGenres() {
        def genresTable = tableRestService.selectAllGenres()
        if (params.id) {

            def output = []
            if (params.id.matches("[0-9]+")) {

                def checkId = params.id as Integer
                output = genresTable.genresData.findAll{ it.id == checkId }

            } else {
                println genresTable.genresData[0]

//                if(genresTable.genresData[0][params.id]) {
                if (genresTable.genresData[0].containsKey(params.id)) {
                    genresTable.genresData.each{ rowOfGenre ->
                        output.add(rowOfGenre[params.id])
                    }
                    render output as JSON
                } else {
                    return response.sendError(401, "The genre table does not have this attribute")
                }
            }

            if (output == []) {
                return response.sendError(401, "Genre ID was not found")
            }
            if (params.atr) {
                def attribute = params.atr

                if (!genresTable.genresData[0].containsKey(attribute)) {
                    return response.sendError(401, "The album genres does not have this attribute")
                } else {
                    def response = [output[0][attribute]]
                    render response as JSON
                }
            } else {
                render output[0] as JSON
            }
        } else {
            render genresTable.genresData as JSON
        }
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
        def status = tableRestService.update Album(params.id as Integer, parameters)
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
