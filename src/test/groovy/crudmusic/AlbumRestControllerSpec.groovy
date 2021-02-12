package crudmusic

import crudmusicmk2.AlbumRestController
import grails.testing.web.controllers.ControllerUnitTest
//import org.grails.testing.GrailsUnitTest
import spock.lang.Specification

class AlbumRestControllerSpec extends Specification implements ControllerUnitTest<AlbumRestController> {
//    def controller

    def setup() {
    }

    def cleanup() {
    }

    void "lol and lol"() {
        when:
            controller.hello()
        then:
            println response.text
    }
}