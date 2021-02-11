package crudmusicmk2

class UrlMappings {
    //FIXME: Να επιτρέπω συγκεκριμένα controller actions.. error state οταν κανω illegal?
    //FIXME: Να βγάζει error values ισως οταν δεν εκτελειται το query.. pχ matching ηδη με υπαρχων entry.. πως??

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                controller(validator: { return it != 'albumRest'})
            }
        }

        "/$controller/albums/$id/$atr" { action = [GET: "showAlbums"] }
        "/$controller/genres/$id/$atr" { action = [GET: "showGenres"] }

        "/$controller/albums/$id" { action = [GET: "showAlbums", PUT: "updateAlbums", DELETE: "deleteAlbums"] }
        "/$controller/genres/$id" { action = [GET: "showGenres", PUT: "updateGenres", DELETE: "deleteGenres"] }
        "/$controller/genres-of-album/$id" { action = [GET: "showGenresOfAlbum", POST: "createGenresOfAlbum"] }

        "/$controller/albums/" { action = [GET: "showAlbums", POST: "insertAlbums"] }
        "/$controller/genres/" { action = [GET: "showGenres", POST: "insertGenres"] }
        "/$controller/genres-of-album/" { action = [GET: "showGenresOfAlbum", POST: "insertGenresOfAlbum"] }



        "/" (view:"/album/index")
        "500" (view:'/error')
        "404" (controller: 'albumRest', action:"notFound")
        "400" (controller: 'albumRest', action:"missingData")

    }
}
