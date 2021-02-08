<%--
  Created by IntelliJ IDEA.
  User: dimos
  Date: 1/2/2021
  Time: 12:59 μ.μ.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<asset:stylesheet src="bootstrap.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<html>
    <head>
        <title>View All Data</title>
        <script>
            function fetchEditDataAlbum(idTag) {
                $.ajax({
                    type: 'GET',
                    url:"<g:createLink url="[action:'editFormAlbum',controller:'album']" />",
                    data: {
                        editId: idTag,
                    },
                    success: function(data) {
                        $('#formName').val(data.formDataAlbum.artist);
                        $('#formTitle').val(data.formDataAlbum.albumtitle);
                        $('#formNumber').val(data.formDataAlbum.songnumber);
                        $('#formDate').val(data.formDataAlbum.releasedate);
                        $('#hiddenAlbumId').val(idTag);

                        $("#formSelect").empty();
                        $.each(data.dataGenresName, function (i, genreName) {
                            if (Object.values(data.formDataGenresOfAlbum).includes(genreName)) {
                                $("#formSelect").append('<option value="' + genreName + '" selected="selected">' +
                                    genreName + '</option>');
                            } else {
                                $("#formSelect").append('<option value="' + genreName + '">' +
                                    genreName + '</option>');
                            }
                        });
                    }
                });
            }

            function fetchEditDataGenre(idTag) {
                $.ajax({
                    type: 'GET',
                    url:"<g:createLink url="[action:'editFormGenre',controller:'album']" />",
                    data: {
                        editId: idTag,
                    },
                    success: function(data) {
                        $('#genreName').val(data.formDataGenre.name);
                        $('#genreCreator').val(data.formDataGenre.creator);
                        $('#genreIsPopular').prop("checked", data.formDataGenre.ispopular);
                        $('#idFormGenre').val(idTag);
                    }
                });
            }

            function submitEditedAlbum() {
                var date = $('#formDate_day').val() + '-' + $('#formDate_month').val() + '-' + $('#formDate_year').val()

                $.ajax({
                    type: 'GET',
                    url:"<g:createLink url="[action:'update',controller:'album']" />",
                    data: $("form").serialize(),
                    //     genres: JSON.stringify($('#formSelect').val()),
                    // },
                    success: function() {
                        fetchAlbumData();
                    }
                });
            }

            function submitEditedGenre() {
                $.ajax({
                    type: 'GET',
                    url:"<g:createLink url="[action:'update',controller:'album']" />",
                    data: $("form").serialize(),
                    success: function() {
                        fetchGenreData();
                    }
                });
            }

            function fetchAlbumData() {
                event.preventDefault();
                $.ajax({
                    type: 'GET',
                    url:"<g:createLink url="[action:'selectAlbum',controller:'album']" />",
                    success: function(data) {
                        $('#albumBody').html("");

                        $.each(data.albumData, function (i, albumRow) {
                            $('#albumBody').append('<tr>')
                            $.each(albumRow, function (i, rowElement) {
                                $('#albumBody').append('<td>' + rowElement + '</td>')
                            });

                            let albumName = "'album'";
                            $('#albumBody').append('<td><button type="button" class="btn btn-outline-danger" onclick="deleteRowAlbum('+albumRow[0]+', '+albumName+')">Delete Entry</button></td>')
                            $('#albumBody').append('<td><button type="button" class="btn btn-outline-info" onclick="fetchEditDataAlbum('+albumRow[0]+', '+albumName+')">Edit</button></td>')
                            $('#albumBody').append('</tr>')
                        });
                    }
                });
            }

            function fetchGenreData() {
                $.ajax({
                    type: 'GET',
                    url:"<g:createLink url="[action:'selectGenre',controller:'album']" />", //TODO: ASD
                    success: function(data) {
                        $('#genreBody').html("");

                        $.each(data.genreData, function (i, genreRow) {
                            $('#genreBody').append('<tr>')
                            $('#genreBody').append('<td>'+genreRow.id+'</td>')
                            $('#genreBody').append('<td>'+genreRow.name+'</td>')
                            $('#genreBody').append('<td>'+genreRow.creator+'</td>')
                            $('#genreBody').append('<td>'+genreRow.isPopular+'</td>')

                            var genreName = "'genres'"
                            $('#genreBody').append('<td><button type="button" class="btn btn-outline-danger" onclick="deleteRowGenre('+genreRow.id+')">Delete Entry</button></td>')
                            $('#genreBody').append('<td><button type="button" class="btn btn-outline-info" onclick="fetchEditDataGenre('+genreRow.id+', '+genreName+')">Edit</button></td>')
                            $('#genreBody').append('</tr>')
                        });
                    }
                });
            }

            function deleteRowAlbum(idTag, tableName) {
                $.ajax({
                    type: 'GET',
                    url:"<g:createLink url="[action:'deleteOne',controller:'album']" />",
                    data: {
                        albumId: idTag,
                        tableName: tableName,
                    },
                    success: function() {
                        fetchAlbumData()
                    }
                });
            }

            function deleteRowGenre(idTag) {
                $.ajax({
                    type: 'GET',
                    url: "<g:createLink url="[action:'deleteOne',controller:'album']"/>",
                    data: {
                        genreId: idTag,
                        tableName: 'genres',
                    },
                    success: function() {
                        fetchGenreData()
                    }
                });
            }

            function searchBarFetchAlbum() {
                $.ajax({
                    type: 'POST',
                    url:"<g:createLink url="[action:'searchAlbum',controller:'album']" />",
                    data: {
                        //TODO: φορμα με ajax
                        searchTagAlbum: $('#searchBarAlbum').val(),
                    },
                    success: function(data) {
                        $('#albumBody').html("");

                        $.each(data.albumData, function (i, albumRow) {
                            $('#albumBody').append('<tr>')
                            $.each(albumRow, function (i, rowElement) {
                                $('#albumBody').append('<td>' + rowElement + '</td>')
                            });

                            let albumName = "'album'";
                            $('#albumBody').append('<td><button type="button" class="btn btn-outline-danger" onclick="deleteRow('+albumRow[0]+', '+albumName+')">Delete Entry</button></td>')
                            $('#albumBody').append('<td><button type="button" class="btn btn-outline-info" onclick="fetchEditDataAlbum('+albumRow[0]+', '+albumName+')">Edit</button></td>')
                            $('#albumBody').append('</tr>')
                        });
                    }
                });
            }

            function searchBarFetchGenres() {
                $.ajax({
                    type: 'POST',
                    url:"<g:createLink url="[action:'searchGenre',controller:'album']" />",
                    data: {
                        //TODO: φορμα με ajax
                        searchTagGenre: $('#searchGenreByName').val(),
                    },
                    success: function(data) {
                        $('#genreBody').html("");

                        $.each(data.genreData, function (i, genreRow) {
                            $('#genreBody').append('<tr>')
                            $('#genreBody').append('<td>'+genreRow.id+'</td>')
                            $('#genreBody').append('<td>'+genreRow.name+'</td>')
                            $('#genreBody').append('<td>'+genreRow.creator+'</td>')
                            $('#genreBody').append('<td>'+genreRow.isPopular+'</td>')

                            var genreName = "'genres'"
                            $('#genreBody').append('<td><button type="button" class="btn btn-outline-danger" onclick="deleteRowGenre('+genreRow.id+')">Delete Entry</button></td>')
                            $('#genreBody').append('<td><button type="button" class="btn btn-outline-info" onclick="fetchEditDataGenre('+genreRow.id+', '+genreName+')">Edit</button></td>')
                            $('#genreBody').append('</tr>')
                        });
                    }
                });
            }

        </script>
    </head>
    <body>

    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <div class="collapse navbar-collapse">

                <ul class="navbar-nav">
                    <li class="navbar-brand" align="left">Dimosthenis Kokkonos</li>

                    <li class="nav-item">
                        <g:link class="nav-link active" controller="album" action="list">Refresh Page</g:link>
                    </li>
                    <li class="nav-item">
                        <g:link class="nav-link active"  controller="album" action="index">Return to Main Page</g:link><br />
                    </li>
                    <li class="nav-item">
                        <g:link class="nav-link active"  controller="album" action="remakeTables">Remake the tables</g:link><br />
                    </li>
                    <li class="nav-item">
                        <g:link class="nav-link active"  controller="album" action="create">Add Entries To Tables</g:link><br />
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <br/>
    <div class="row justify-content-center">
        <div class="col">
            <div class="container">
                <div class="row">
                    <h3 class="col-sm" align="left">Table Of Albums</h3>
                    <g:form id="form1" class="col-sm" align="right">
                        <g:textField name="searchTagAlbum" id="searchBarAlbum" value="${searchTagAlbum}" placeholder="Enter Album Title"/>
                        <button type="button" class="btn btn-outline-danger"
                                onclick="searchBarFetchAlbum()">Search</button>
                    </g:form>
                </div>
            </div>
            <table id="albumTable" class="table borderless caption-top table-striped table-hover table-bordered text-center table-responsive" class="col-sm" align="left">
                <thead>
                    <tr>
                        <td>Id of the album</td>
                        <td>Artist</td>
                        <td>Title of the Album</td>
                        <td>Number of songs in the album</td>
                        <td>Album Release Date</td>
                        <td>Music Genres of the Album</td>
                        <td/>
                        <td/>
                    </tr>
                </thead>
                <tbody id="albumBody">
                    <g:each in="${albumData}" var="row">
                        <tr>
                            <g:each in="${row}" var="row2">
                                <td>${row2}</td>
                            </g:each>
                            <td>
                                <button type="button" class="btn btn-outline-danger"
                                        onclick="deleteRowAlbum(${row[0]}, 'album')">Delete Entry</button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-outline-info"
                                        onclick="fetchEditDataAlbum(${row[0]}, 'album')">Edit</button>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
            </div>
        </div>
        <br/><br/><br/><br/>
        <div class="row justify-content-center">
            <div class="col-auto">
                <div class="container">
                    <div class="row">
                        <h3 class="col-sm" align="left">Table Of Music Genres</h3>
                        <g:form id="form1" class="col-sm" align="right">
                            <g:textField name="searchTagGenre" id="searchGenreByName" value="${searchTagGenre}" placeholder="Enter Genre Name"/>
                            <button type="button" class="btn btn-outline-danger"
                                    onclick="searchBarFetchGenres()">Search</button>
                        </g:form>
                    </div>
                </div>
                <table class="table borderless caption-top table-striped table-hover table-bordered text-center table-responsive" class="col-sm" align="left">
                    <thead>
                        <tr>
                            <td>Id of the genre</td>
                            <td>Name of genre</td>
                            <td>Creator of the music genre</td>
                            <td>Popular Genre</td>
                            <td/>
                            <td/>
                        </tr>
                    </thead>
                    <tbody id="genreBody">
                        <g:each in="${genreData}" var="row">
                            <tr>
                                <td>${row.id}</td>
                                <td>${row.name}</td>
                                <td>${row.creator}</td>
                                <td>${row.isPopular}</td>
                                <td>
                                    <button type="button" class="btn btn-outline-danger"
                                            onclick="deleteRowGenre(${row.id})">Delete Entry</button>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-outline-info"
                                            onclick="fetchEditDataGenre(${row.id}, 'genres')">Edit</button>
                                </td>
                            </tr>
                        </g:each>

                    </tbody>
                </table>
            </div>
        </div>
        <br/>
        <div class="container">
            <div class="row">
                <div class="col-sm" align="left">
                    <g:form align="middle" id="formAlbum">
                        <h3>Edit an album entry</h3>
                        <div class="form-group">
                            <label>Name of artist</label>
                            <g:textField class="form-control" id="formName" required="true" name='artist'/>
                        </div>
                        <div class="form-group">
                            <label>Title of the album</label>
                            <g:textField class="form-control" id="formTitle" required="true" name='albumTitle'/>
                        </div>
                        <div class="form-group">
                            <label>Number of songs</label>
                            <g:textField class="form-control" id="formNumber" required="true" name='songNumber'/>
                        </div>
                        <div class="form-group">
                            <label>Release Date</label>
                            <g:datePicker class="form-control" id="formDate" required="true" name='releaseDate' precision="day" />
                        </div>
                        <div class="form-group">
                            <label>Genre</label>
                                <select class="form-control" multiple name="formSelectedGenres"
                                        id="formSelect"/><br/>
                        </div>
                        <div><g:hiddenField name="idFormAlbum" id="hiddenAlbumId"/></div>
                        <button type="button" class="btn btn-outline-info"
                                onclick="submitEditedAlbum()">Save</button>
                    </g:form>
                </div>
                <div class="col-sm" align="right">
                    <form align="middle">
                        <h3>Edit an genre entry</h3>
                        <div class="form-group">
                            <label>Name of Music Genre</label>
                            <g:textField class="form-control" id="genreName" required="true" name='name'/>
                        </div>
                        <div class="form-group">
                            <label>Name of Creator</label>
                            <g:textField class="form-control" id="genreCreator" required="true" name='creator'/>
                        </div>
                        <div class="form-group">
                            <label>Popular Music Genre</label>
                            <input type="checkbox" class="form-control" id="genreIsPopular">
                        </div>
                        <div><g:hiddenField name="idFormGenre"/></div>
                        <button type="button" class="btn btn-outline-info"
                                onclick="submitEditedGenre()">Save</button>
                    </form>
                </div>
            </div>
        </div><br/><br/>
    </body>
</html>