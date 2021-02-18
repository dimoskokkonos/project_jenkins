package crudmusic

import crudmusic.AlbumRestController
import grails.converters.JSON
import grails.testing.web.controllers.ControllerUnitTest
//import org.grails.testing.GrailsUnitTest
import spock.lang.Specification

class AlbumRestControllerSpec extends Specification implements ControllerUnitTest<AlbumRestController> {

    def testAlbum1 = [id: 1, artist: "tempArtist", albumTitle:"tempTitle", songNumber: 1, releaseDate:'1-1-2020']
    def testAlbum2 = [id: 2, artist: "tempArtist", albumTitle:"tempTitle", songNumber: 2, releaseDate:'1-1-2020']

    def testGenre1 = [id: 1, name: "tempGenre", creator:"tempPerson", isPopular: true]
    def testGenre2 = [id: 2, name: "tempGenre", creator:"tempPerson", isPopular: false]
    def testGenre3 = [id: 3, name: "tempGenre", creator:"tempPerson", isPopular: true]
    def testGenre4 = [id: 4, name: "tempGenre", creator:"tempPerson", isPopular: false]

    def testAlbumGenres1 = [albumid: 1, genreId: 1]
    def testAlbumGenres2 = [albumid: 2, genreId: 3]
    def testAlbumGenres3 = [albumid: 2, genreId: 4]

    def insertTestAlbum = [id: 1, artist: "Τασος", albumTitle:"Του τασου", songNumber: 1, releaseDate:"29-1-2019"]
    def insertTestGenre = [id: 1, name: "Toxicity", creator:"System Of A Down", isPopular: true]
    def insertTestAlbumGenres = [albumid: 1, genreId: 2]

    def updateAlbum = [id: 1, artist: "tempArtist", albumTitle:"tempTitle", songNumber: "1", releaseDate:"2-2-2020"]

    def setup() {
        controller.tableRestService = [
                selectAllAlbum: {
                    return [testAlbum1, testAlbum2]
                },

                albumsParamsValidator: {def albumsTable, int paramsId, def paramsAtr ->
                    return [testAlbum1, testAlbum2]
                },

                selectAllGenres: {
                    return [genresData: [testGenre1, testGenre2, testGenre3, testGenre4]]
                },

                selectAllGenresOfAlbum: {
                    return [albumGenresData: [testAlbumGenres1, testAlbumGenres2, testAlbumGenres3]]
                },

                insertAlbums: {def artist, def title, int songNumber, def date ->
                    return [id: 1, artist: artist, albumTitle:title, songNumber: songNumber, releaseDate:date]
                },

                insertGenres: {def name, def creator, boolean isPopular ->
                    return [id: 1, name: name, creator:creator, isPopular: isPopular]
                },

                insertGenresOfAlbum: {def idAlbum, def idGenre ->
                    return [albumid: idAlbum, genreId: idGenre]
                },

                updateAlbum: {int id, def par ->
                    return [id: id, artist: par[0], albumTitle: par[1], songNumber: par[2], releaseDate: par[3]]
                }
        ]
    }


    def cleanup() {
    }

    def 'testShowAllAlbums'() {
        given:
            controller.params.id = 1
            controller.params.atr = 'artist'
        when:
            controller.showAlbums()
        then:
            def output = [testAlbum1, testAlbum2] as JSON
            assert response.text == output as String
    }


    def 'testShowAllGenres'() {
        given:
            controller.params.id = null
            controller.params.atr = null
        when:
            controller.showGenres()
        then:
            def output = [testGenre1, testGenre2, testGenre3, testGenre4] as JSON
            assert response.text == output as String
    }

    def 'testSingleGenre'() {
        given:
            controller.params.id = "1"
            controller.params.atr = null
        when:
            controller.showGenres()
        then:
            def output = testGenre1 as JSON
            assert response.text == output as String
    }

    def 'testSingleGenreAttribute'() {
        given:
            controller.params.id = "1"
            controller.params.atr = "name"
        when:
            controller.showGenres()
        then:
            def output = ['"' + testGenre1.name + '"'] as String
            assert response.text == output
    }

    def 'genresOfAlbums'() {
        given:
            controller.params.id = null
        when:
            controller.showGenresOfAlbum()
        then:
            def output = [testAlbumGenres1, testAlbumGenres2, testAlbumGenres3] as JSON
            assert response.text == output as String
    }

    def 'genresOfSingleAlbum'() {
        given:
            controller.params.id = "1"
        when:
            controller.showGenresOfAlbum()
        then:
            def output = [testAlbumGenres1] as JSON
            assert response.text == output as String
    }

    def insertAlbum() {
        given:
            controller.params.artist = "Τασος"
            controller.params.albumTitle = "Του τασου"
            controller.params.songNumber = "1"
            controller.params.releaseDate = "29-1-2019"
        when:
            controller.insertAlbums()
        then:
            def output = insertTestAlbum as JSON
            assert response.text == output as String
    }

    def insertGenres() {
        given:
            controller.params.name = "Toxicity"
            controller.params.creator = "System Of A Down"
            controller.params.isPopular = true
        when:
            controller.insertGenres()
        then:
            def output = insertTestGenre as JSON
            assert response.text == output as String
    }

    def insertAlbumGenres() {
        given:
            controller.params.idAlbum = "1"
            controller.params.idGenre = "2"
        when:
            controller.insertGenresOfAlbum()
        then:
            def output = insertTestAlbumGenres as JSON
            assert response.text == output as String
    }

    def updateSingleAlbum() {
        given:
            controller.params.id = "1"
            controller.params.artistName = "tempArtist"
            controller.params.title = "tempTitle"
            controller.params.howManySongs = "1"
            controller.params.releaseDate = "2-2-2020"
        when:
            def parameters = [artist: params.artistName, albumTitle: params.title, songNumber: params.howManySongs as Integer, releaseDate: params.dateOfRelease]
            controller.updateAlbums(params.id as Integer, parameters)
        then:
            assert response.text == updateAlbum
    }

//    def updateAlbums() {
//        def parameters = [artist: params.artistName, albumTitle: params.title, songNumber: params.howManySongs as Integer, releaseDate: params.dateOfRelease]
//        def status = tableRestService.updateAlbum(params.id as Integer, parameters)
//        if (status == 0) {
//            return response.sendError(400, "Album ID was not found")
//        }
//        render parameters as JSON
//    }

}







