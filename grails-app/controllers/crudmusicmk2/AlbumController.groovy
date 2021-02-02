package crudmusicmk2

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
    }

    def list() {
        def selectOfTablesAlbumGenres = tableService.selectTables()
        def selectOfAlbumGenresRelation = tableService.manyGenres()
        def albumDataWithGenre = []

        for (def i = 0; i <selectOfTablesAlbumGenres.albumData.size(); i++) {
            def temp = selectOfTablesAlbumGenres.albumData[i]
            def temporaryMap = []

            temporaryMap.add(temp.id)
            temporaryMap.add(temp.artist)
            temporaryMap.add(temp.albumTitle)
            temporaryMap.add(temp.songNumber)
            temporaryMap.add(temp.releaseDate)
            temporaryMap.add(selectOfAlbumGenresRelation.dataOfAlbumsGenre[i].join(", "))

            albumDataWithGenre.add(temporaryMap)
        }
        [genreData: selectOfTablesAlbumGenres.genresData, albumData: albumDataWithGenre]
    }

    def deleteOne() {
        if (params.entry) { tableService.deleteRowAlbum(Integer.parseInt(params.entry)) }
        if (params.entry2) { tableService.deleteRowGenre(Integer.parseInt(params.entry2)) }

        redirect (action: 'list')
    }

    def remakeTables() {
        tableService.deleteTables()
        tableService.tableCreation()
        tableService.insertInitialValue()

        redirect (action: 'list')
    }
}
