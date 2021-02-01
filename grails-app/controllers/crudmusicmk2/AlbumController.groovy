package crudmusicmk2

class AlbumController {

    def tableService

    def deleteAll() {
        tableService.deleteTables()
        redirect action:"index"
    }

    def initialization() {
        tableService.insertInitialValue()
        redirect action:"index"
    }

    def index() {
        tableService.tableCreation()
    }

    def list() {
        def returning_data_1 = tableService.selectTables()
        def returning_data_2 = tableService.manyGenres()

        [genreData: returning_data_1.genresData, albumData: returning_data_1.albumData, mixedData :returning_data_2.data_of_albums_genre]

    }


}
