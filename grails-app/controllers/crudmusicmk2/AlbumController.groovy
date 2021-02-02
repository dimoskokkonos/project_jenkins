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
        def new_genresData = []
        def outer_list = []

        for (def i = 0; i <returning_data_1.albumData.size(); i++) {
            def temp = returning_data_1.albumData[i]
            def inner_list = []

            inner_list.add(temp.id)
            inner_list.add(temp.artist)
            inner_list.add(temp.album_title)
            inner_list.add(temp.song_number)
            inner_list.add(temp.release_date)
            inner_list.add(returning_data_2.data_of_albums_genre[i].join(", "))

            outer_list.add(inner_list)
        }

        [genreData: returning_data_1.genresData, albumData: outer_list]
    }


}
