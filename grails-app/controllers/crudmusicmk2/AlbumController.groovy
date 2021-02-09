package crudmusicmk2

import grails.converters.JSON

class AlbumController {

    def tableService

    def deleteAll() {
        tableService.deleteTables()
        redirect (action: 'index')
    }

    def initialization() {
        tableService.insertInitialValue()
        redirect (action: 'index')
    }

    def index() {
        redirect (action: 'list')
    }

    def list() {
        def selectOfTablesAlbumGenres = tableService.selectTables()
        def selectOfAlbumGenresRelation = tableService.manyGenres(selectOfTablesAlbumGenres.albumData)

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
        [genreData: selectOfTablesAlbumGenres.genresData, albumData: albumDataWithGenre]
    }

    def editFormAlbum() {
        def tempAlbumDataOfRow = tableService.selectRow(params.editId, 'album')
        def dataRowAlbum = tempAlbumDataOfRow.dataRowAlbum[0]
        def dataGenresName = tempAlbumDataOfRow.dataGenresName.name
        def formDataGenresOfAlbum = tempAlbumDataOfRow.dataRowGenres.name

        def outputMap =[formDataAlbum: dataRowAlbum, dataGenresName: dataGenresName, formDataGenresOfAlbum: formDataGenresOfAlbum ]
        render outputMap as JSON
    }

    def editFormGenre() {
        def selectAllOutput = tableService.selectRow(params.editId, 'genres')

        def outputMap =[formDataGenre: selectAllOutput.dataRowGenre[0]]
        render outputMap as JSON
    }

    def deleteOne() {
        if (params.albumId) { tableService.deleteRowAlbum(Integer.parseInt(params.albumId)) }
        if (params.genreId) { tableService.deleteRowGenre(Integer.parseInt(params.genreId)) }

        redirect (action: 'list')
    }

    def remakeTables() {
        tableService.deleteTables()
        tableService.tableCreation()
        tableService.insertInitialValue()

        redirect (action: 'list')
    }

    def create() {
        def selectDataFromTables = tableService.selectTables()

        [genresData: selectDataFromTables.genresData]
    }

    def insert() {
        def date = params.releaseDate_day + '-' + params.releaseDate_month + '-' + params.releaseDate_year
        def inputSongNumber, inputIsPopular

        if (params.songNumber) {
            inputSongNumber = Integer.parseInt(params.songNumber)
        } else {
            inputSongNumber = null
        }
        if (params.isPopular) {
            inputIsPopular = true
        } else {
            inputIsPopular = false
        }
        tableService.insertEntry(params.artist, params.albumTitle, inputSongNumber, date,
                params.genres, params.name, params.creator, inputIsPopular)

        redirect (action: 'create')
    }

    def update() {
        def date = params.releaseDate_day + '-' + params.releaseDate_month + '-' + '-' + params.releaseDate_year

        def songNumber, idAlbum, idGenre
        if (params.songNumber) { songNumber = Integer.parseInt(params.songNumber) }
        if (params.idFormAlbum) { idAlbum = Integer.parseInt(params.idFormAlbum) }
        if (params.idFormGenre) { idGenre = Integer.parseInt(params.idFormGenre) }


        tableService.updateRow(params.artist, params.albumTitle, songNumber, date, idAlbum, params.formSelectedGenres,
                params.name, params.creator, params.isPopular, idGenre)

        redirect (action: 'list')
    }

    def selectAlbum() {
        def selectOfTablesAlbumGenres = tableService.selectTables()
        def selectOfAlbumGenresRelation = tableService.manyGenres(selectOfTablesAlbumGenres.albumData)
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

        def outputMap = [genreData: selectOfTablesAlbumGenres.genresData, albumData: albumDataWithGenre]
        render outputMap as JSON
    }

    def searchAlbum() {
        def selectOfTablesAlbumGenres = tableService.selectWithSearchFeature(params.searchTagAlbum, 'album')
        def selectOfAlbumGenresRelation = tableService.manyGenres(selectOfTablesAlbumGenres.albumData)

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
        def outputMap = [genreData: selectOfTablesAlbumGenres.genresData, albumData: albumDataWithGenre]
        render outputMap as JSON
    }

    def searchGenre() {
        def selectOfTablesAlbumGenres = tableService.selectWithSearchFeature(params.searchTagGenre, 'genre')

        selectOfTablesAlbumGenres.genresData.isPopular.eachWithIndex { val, index ->
            selectOfTablesAlbumGenres.genresData[index].isPopular = String.valueOf(selectOfTablesAlbumGenres.genresData[index].isPopular)
        }

        def outputMap = [genreData: selectOfTablesAlbumGenres.genresData]
        render outputMap as JSON
    }

    def selectGenre() {
        def selectOfTablesAlbumGenres = tableService.selectTables()

        selectOfTablesAlbumGenres.genresData.isPopular.eachWithIndex { val, index ->
            selectOfTablesAlbumGenres.genresData[index].isPopular = String.valueOf(selectOfTablesAlbumGenres.genresData[index].isPopular)
        }

        def outputMap = [genreData: selectOfTablesAlbumGenres.genresData]
        render outputMap as JSON
    }

}
