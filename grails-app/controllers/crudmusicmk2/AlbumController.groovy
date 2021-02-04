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

    def index() {}

    def list() {

        //Τμήμα κώδικα για το list των δεδομένων

        //Aν κάνω search πάω για το select με like, αλλιώς το select *
        def selectOfTablesAlbumGenres
        if (params.searchTagAlbum) {
            selectOfTablesAlbumGenres = tableService.selectWithSearchFeature(params.searchTagAlbum, 'album')

        } else if (params.searchTagGenre) {
            selectOfTablesAlbumGenres = tableService.selectWithSearchFeature(params.searchTagGenre, 'genre')
        } else {
            selectOfTablesAlbumGenres = tableService.selectTables()
        }

        def selectOfAlbumGenresRelation = tableService.manyGenres(selectOfTablesAlbumGenres.albumData)

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

        //τμήμα κώδικα για την φόρμα του update στο album
        // Κάνω click το edit, μου δίνει id της σειράς. Αν δεν έχω κάνει click ακόμα, αρχικοποίηση του map   με null
        def dataRowAlbum = []
        def dataGenresName = []
        def formDataGenresOfAlbum = []


        if (params.formIdAlbum) {
            def tempAlbumDataOfRow = tableService.selectRow(params.formIdAlbum, "album")
            dataRowAlbum = tempAlbumDataOfRow.dataRowAlbum[0]
            dataGenresName = tempAlbumDataOfRow.dataGenresName.name
            formDataGenresOfAlbum = tempAlbumDataOfRow.dataGenresOfTheRow.name

        } else {
            dataRowAlbum = [artist: null, albumtitle: null, songnumber:null, releasedate:null]
        }

        //τμήμα κώδικα για την φορμα του update στο genre. Ίδια λογική
        def dataRowGenre =[]
        if (params.formIdGenre) {
            def tempAlbumDataOfRow = tableService.selectRow(params.formIdGenre, "genre")
            dataRowGenre = tempAlbumDataOfRow.dataRowGenre[0]
        } else {
            dataRowGenre = [name: null, creator: null]
        }
        //έξοδος
        [genreData: selectOfTablesAlbumGenres.genresData, albumData: albumDataWithGenre,
         formDataAlbum: dataRowAlbum, dataGenresName: dataGenresName, formDataGenresOfAlbum: formDataGenresOfAlbum,
         formDataGenres: dataRowGenre]
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

        redirect (action: 'index')
    }

    def create() {
        def selectDataFromTables = tableService.selectTables()

        [genresData: selectDataFromTables.genresData]

    }

    def insert() {
        def date = params.releaseDate_day + '-' + params.releaseDate_month + '-' + params.releaseDate_year
        def inputSongNumber, inputIsPopular

        // Όταν κάνω κλικ το κουμπι για την υποβολή genre, επειδή χρησιμοποιώ το ίδιο insert service, πηγαίνει null
        // στην parseInt εντολή --> error.. Η if αυτή στέλνει null ως είσοδο στο service όταν δεν υπάρχει η είσοδος songNumber
        if (params.songNumber) {
            inputSongNumber = Integer.parseInt(params.songNumber)
        } else {
            inputSongNumber = null
        }

        // Όταν είναι τικαρισμένο, παράγει έξοδο string = on, όταν όχι παράγει null.. όταν υπάρχει θέτω boolean είσοδο true, διαφορετικά false
        if (params.isPopular) {
            inputIsPopular = true
        } else {
            inputIsPopular = false
        }

        //πρώτη σειρά έχει εισόδους για insert album, δεύτερη για insert genre
        tableService.insertEntry(params.artist, params.albumTitle, inputSongNumber, date,
                params.genres, params.name, params.creator, inputIsPopular)

        redirect (action: 'create')
    }

    def update() {
        def date = params.releaseDate_day + '-' + params.releaseDate_month + '-' + params.releaseDate_year

        def songNumber, idAlbum, idGenre
        if (params.songNumber) { songNumber = Integer.parseInt(params.songNumber) }
        if (params.idFormAlbum) { idAlbum = Integer.parseInt(params.idFormAlbum) }
        if (params.idFormGenre) { idGenre = Integer.parseInt(params.idFormGenre) }


        tableService.updateRow(params.artist, params.albumTitle, songNumber, date, idAlbum, params.genres,
                params.name, params.creator, params.isPopular, idGenre)

        redirect (action: 'list')
    }

}
