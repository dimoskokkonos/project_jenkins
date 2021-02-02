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

//        returning_data_1.genresData.each {
//            row -> println row
//                prinln returning_data_2{0}
//        }

        def new_genresData = []
        def outer_list = []
        for (def i = 0; i <returning_data_1.genresData.size(); i++) {
            def temp = returning_data_1.genresData[i]
            def inner_list = []
            temp.each {row ->
                println row
                inner_list.add(row)

            }
            inner_list.add(returning_data_2.data_of_albums_genre[i])
//            println inner_list
//            new_genresData.add(returning_data_2.data_of_albums_genre[i])

        }

        println new_genresData.size()
        println new_genresData

//        for (def i = 0; i <new_genresData.size(); i++) {
//            println new_genresData[i]
//
//
//        }
        [genreData: returning_data_1.genresData, albumData: returning_data_1.albumData, mixedData :returning_data_2.data_of_albums_genre]

    }


}
