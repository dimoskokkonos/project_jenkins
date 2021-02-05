package crudmusicmk2

import grails.converters.JSON


class AjaxForNoobsController {

    def index() { }

    def delete() {

        def trojan = [blah: 'blah', pineapple: 'unsolved']
        def row = [id: 1, name:'dimos', horseType: trojan, mouse: 'furry', childern: -1, eatingStatus: false]


        render row as JSON
    }





}
