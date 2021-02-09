package crudmusicmk2

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/$controller/$id?"{
            action = [GET:"show", POST:"save", PUT:"update", DELETE:"remove"]
        }
        //        get "/$controller"(controller: 'albumRest', action:"index")

//        delete "/$controller/$id(.$format)?"(controller: 'albumRest', action:"delete")
//        get "/$controller(.$format)?"(controller: 'albumRest', action:"index")
//        get "/$controller/$id(.$format)?"(controller: 'albumRest', action:"show")
//        post "/$controller(.$format)?"(controller: 'albumRest', action:"save")
//        put "/$controller/$id(.$format)?"(controller: 'albumRest', action:"update")
//        patch "/$controller/$id(.$format)?"(controller: 'albumRest', action:"patch")

        "/" (view:"/album/index")
        "500" (view:'/error')
        "404" (view:'/notFound')
    }
}
