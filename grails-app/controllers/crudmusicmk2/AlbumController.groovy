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
        def returning = tableService.selectTables()
        [tableData: returning]
    }


}
