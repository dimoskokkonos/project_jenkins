package crudmusicmk2

class UrlMappings {
    //TODO: ΝΑ ΚΟΒΩ ΤΟ controller/showGenres, να κοβω url Με λαθος μεθοδους
    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                controller(validator: { return it != 'albumRest'})
            }
        }

        "/$controller/albums/$id" { action = [GET: "showAlbums"] }
        "/$controller/genres/$id" { action = [GET: "showGenres"] }

        "/$controller/albums/" { action = [GET: "showAlbums", POST: "createAlbums"] }
        "/$controller/genres/" { action = [GET: "showGenres", POST: "createGenres"] }
        "/$controller/genres-of-album/" { action = [POST: "createGenresOfAlbum"] }


//        "/$controller/$id?"{
//            action = [GET: "show", POST: "save", PUT: "update", DELETE: "remove"]
//        }

//        delete "/$controller/$id(.$format)?"(controller: 'albumRest', action:"delete")
//        get "/$controller(.$format)?"(controller: 'albumRest', action:"index")
//        get "/$controller/$id(.$format)?"(controller: 'albumRest', action:"show")
//        post "/$controller(.$format)?"(controller: 'albumRest', action:"save")
//        put "/$controller/$id(.$format)?"(controller: 'albumRest', action:"update")
//        patch "/$controller/$id(.$format)?"(controller: 'albumRest', action:"patch")

        "/" (view:"/album/index")
        "500" (view:'/error')
        "404" (controller: 'albumRest', action:"notFound")
    }
}
