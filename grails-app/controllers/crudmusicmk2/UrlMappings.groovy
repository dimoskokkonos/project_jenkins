package crudmusicmk2

class UrlMappings {
    //FIXME: Να βγάζει error values ισως οταν δεν εκτελειται το query.. pχ matching ηδη με υπαρχων entry.. πως??

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                controller(validator: { return it != 'albumRest'})
            }
        }

        "/$controller/albums/$id/$atr" { action = [GET: "showAlbums", PUT: "errorAction", DELETE: "errorAction", POST: "errorAction"] }
        "/$controller/genres/$id/$atr" { action = [GET: "showGenres", PUT: "errorAction", DELETE: "errorAction", POST: "errorAction"] }

        "/$controller/albums/$id" { action = [GET: "showAlbums", PUT: "updateAlbums", DELETE: "deleteAlbums", POST: "errorAction"] }
        "/$controller/genres/$id" { action = [GET: "showGenres", PUT: "updateGenres", DELETE: "deleteGenres", POST: "errorAction"] }
        "/$controller/genres-of-album/$id" { action = [GET: "showGenresOfAlbum", POST: "createGenresOfAlbum", DELETE: "errorAction", POST: "errorAction"] }

        "/$controller/albums/" { action = [GET: "showAlbums", POST: "insertAlbums", PUT: "errorAction", DELETE: "errorAction"] }
        "/$controller/genres/" { action = [GET: "showGenres", POST: "insertGenres", PUT: "errorAction", DELETE: "errorAction"] }
        "/$controller/genres-of-album/" { action = [GET: "showGenresOfAlbum", POST: "insertGenresOfAlbum", PUT: "errorAction", DELETE: "errorAction"] }



        "/" (view:"/album/index")
        "500" (view:'/error')
        "404" (controller: 'albumRest', action:"notFound")
        "400" (controller: 'albumRest', action:"missingData")
        "405" (controller: 'albumRest', action:"error405")
    }
}
