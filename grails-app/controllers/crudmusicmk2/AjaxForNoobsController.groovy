package crudmusicmk2

import grails.converters.JSON


class AjaxForNoobsController {

    def index() {
//        render(text: "<JSON>some xml</JSON>", contentType: "text/JSON", encoding: "UTF-8")

    }

    def delete() {
        def trojan = [blah: 'blah', pineapple: 'unsolved']
        def row = [id: 1, name:'dimos', horseType: trojan, mouse: 'furry', childern: -1, eatingStatus: false]
//        println row
//        render (contentType: "text/json", text: '[results: {"name":"Afghanistan" ...}]')
//        [data: row]
        render row as JSON
//        render ([data: row])
//        myString = new JSONObject().put("JSON", "Hello, World!").toString();
//        render "Book ${b.id} was deleted"
    }





}
