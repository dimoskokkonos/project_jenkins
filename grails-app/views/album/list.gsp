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
            function fetchEditDataAlbum(idTag, tableName) {
                $.ajax({
                    type: 'GET',
                    url:"<g:createLink url="[action:'editFormAlbum',controller:'album']" />",
                    data: {
                        editId: idTag,
                        tableName: tableName,
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

            function submitEditedAlbum() {
                var date = $('#formDate_day').val() + '-' + $('#formDate_month').val() + '-' + $('#formDate_year').val()

                $.ajax({
                    type: 'GET',
                    url:"<g:createLink url="[action:'update',controller:'album']" />",
                    data: {
                        artist: $('#formName').val(),
                        albumTitle: $('#formTitle').val(),
                        songNumber: $('#formNumber').val(),
                        releaseDate: date,
                        genres: JSON.stringify($('#formSelect').val()),
                        idFormAlbum: $('#hiddenAlbumId').val()  ,
                    },
                    success: function() {
                        $('#albumTable').val(fetchAlbumData());
                    }
                });
            }

            function fetchAlbumData() {
                $.ajax({
                    type: 'GET',
                    url:"<g:createLink url="[action:'selectAlbum',controller:'album']" />",
                    success: function(data) {
                        // console.log( data);
                        $('#albumBody').html("");


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
                        <g:textField name="searchTagAlbum" value="${searchTagAlbum}" placeholder="Enter Album Title"/>
                        <g:actionSubmit value="Search" action="list"/>
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
                                <g:link controller="album" action="deleteOne" method="post" params="[albumId: row[0]]">
                                    <button type="button" class="btn btn-outline-danger">Delete Entry</button>
                                </g:link>
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
                            <g:textField name="searchTagGenre" value="${searchTagGenre}"/>
                            <g:actionSubmit value="Search" action="list"/>
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
                    <tbody>
                        <g:each in="${genreData}" var="row">
                            <tr>
                                <td>${row.id}</td>
                                <td>${row.name}</td>
                                <td>${row.creator}</td>
                                <td>${row.isPopular}</td>
                                <td>
                                    <g:link controller="album" action="deleteOne" method="post" params="[genreId: row.id]">
                                        <button type="button" class="btn btn-outline-danger">Delete Genre</button>
                                    </g:link>
                                </td>
                                <td>
                                    <g:link controller="album" action="list" method="get" params="[formIdGenre: row.id]">
                                        <button type="button" class="btn btn-outline-info">Edit</button>
                                    </g:link>
                                </td>
                            </tr>
                        </g:each>

                    </tbody>
                </table>
            </div>
        </div>
    <br/>
    %{--//////////////////////////////////////////////////////////////////////////////////////////////////////--}%


    <div class="container">
        <div class="row">
            <div class="col-sm" align="left">
                <g:form align="middle">
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
                            <select class="form-control" multiple
                                    id="formSelect"/><br/>
                    </div>
                    <div><g:hiddenField name="idFormAlbum" id="hiddenAlbumId"/></div>
                    <button type="button" class="btn btn-outline-info"
                            onclick="submitEditedAlbum()">Save</button>
%{--                    <g:actionSubmit value="Save" action="update"/>--}%
                </g:form>
            </div>

%{--//////////////////////////////////////////////////////////////////////////////////////////////////////--}%


            <div class="col-sm" align="right">
                <g:form align="middle">
                    <h3>Edit an album entry</h3>
                    <div class="form-group">
                        <label for="field1">Name of Music Genre</label>
                        <g:textField class="form-control" required="true" name='name' value="${formDataGenres.name}"/>
                    </div>
                    <div class="form-group">
                        <label for="field2">Name of Creator</label>
                        <g:textField class="form-control" id="field2" required="true" name='creator' value="${formDataGenres.creator}"/>
                    </div>
                    <div class="form-group">
                        <label for="field3">Popular Music Genre</label>
                        <g:checkBox class="form-control" id="field3" checked="${formDataGenres.isPopular}" name='isPopular'/>
                    </div>
                    <div><g:hiddenField name="idFormGenre" value="${formDataGenres.id}"/></div>
                    <g:actionSubmit value="Save" action="update"/>
                </g:form>
            </div>
        </div>



    </div>
    <br/><br/>




    </body>
</html>