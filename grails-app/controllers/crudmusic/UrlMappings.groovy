package crudmusic

class UrlMappings {
    //FIXME: Να βγάζει error values ισως οταν δεν εκτελειται το query.. pχ matching ηδη με υπαρχων entry.. πως??


    //FIXME: ολα τα album με specific genre
    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                controller(validator: { return it != 'albumRest'})
            }
        }

        "/$controller/albums/$id/$atr" { action = [GET: "showAlbums", PUT: "errorAction"] }
        "/$controller/genres/$id/$atr" { action = [GET: "showGenres", PUT: "errorAction"] }

        "/$controller/albums/$id" { action = [GET: "showAlbums", PUT: "updateAlbums"] }
        "/$controller/genres/$id" { action = [GET: "showGenres", PUT: "updateGenres"] }
        "/$controller/genres-of-album/$id" { action = [GET: "showGenresOfAlbum", POST: "createGenresOfAlbum"] }

        "/$controller/albums/" { action = [GET: "showAlbums", POST: "insertAlbums"] }
        "/$controller/genres/" { action = [GET: "showGenres", POST: "insertGenres"] }
        "/$controller/genres-of-album/" { action = [GET: "showGenresOfAlbum", POST: "insertGenresOfAlbum"] }



        "/" (view:"/album/index")
        "500" (view:'/error')
        "404" (controller: 'albumRest', action:"notFound")
        "400" (controller: 'albumRest', action:"missingData")
        "405" (controller: 'albumRest', action:"error405")
    }
}
