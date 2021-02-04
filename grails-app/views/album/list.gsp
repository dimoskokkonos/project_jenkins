<%--
  Created by IntelliJ IDEA.
  User: dimos
  Date: 1/2/2021
  Time: 12:59 μ.μ.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<asset:stylesheet src="bootstrap.css"/>

<html>
    <head>
        <title>View All Data</title>
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
        <div class="col-auto">

            <div class="container">
                <div class="row">
                    <h3 class="col-sm" align="left">Table Of Albums</h3>
                    <g:form id="form1" class="col-sm" align="right">
                        <g:textField name="searchTagAlbum" value="${searchTagAlbum}" placeholder="Enter Album Title"/>
                        <g:actionSubmit value="Search" action="list"/>
                    </g:form>
                </div>
            </div>
            <table class="table caption-top table-striped table-hover table-bordered text-center table-responsive">
                <thead>
                    <tr>
                        <td>Id of the album</td>
                        <td>Artist</td>
                        <td>Title of the Album</td>
                        <td>Number of songs in the album</td>
                        <td>Album Release Date</td>
                        <td>Music Genres of the Album</td>
                        <td></td>
                        <td></td>
                    </tr>
                </thead>
                <tbody>
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
                                <g:link controller="album" action="list" method="get" params="[formIdAlbum: row[0]]">
                                    <button type="button" class="btn btn-outline-info">Edit</button>
                                </g:link>
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
    <div class="container">
        <div class="row">
            <div class="col-sm" align="left">
                <g:form align="middle">
                    <h3>Edit an album entry</h3>
                    <div class="form-group">
                        <label for="field1">Name of artist</label>
                        <g:textField class="form-control"  required="true" name='artist' id="field1" value="${formDataAlbum.artist}"/>
                    </div>
                    <div class="form-group">
                        <label for="field2">Title of the album</label>
                        <g:textField class="form-control" id="field2" required="true" name='albumTitle' value="${formDataAlbum.albumtitle}"/>
                    </div>
                    <div class="form-group">
                        <label for="field3">Number of songs</label>
                        <g:textField class="form-control" id="field3" required="true" name='songNumber' value="${formDataAlbum.songnumber}"/>
                    </div>
                    <div class="form-group">
                        <label for="field4">Release Date</label>
                        <g:datePicker class="form-control" id="field4" required="true" name='releaseDate' precision="day" value="${formDataAlbum.releasedate}"/>
                    </div>
                    <div class="form-group">
                        <label for="field5">Genre</label>
                        <g:select class="form-control"
                                  id="field5"
                                  name="genres"
                                  from="${dataGenresName}"
                                  multiple="multiple"
                                  value="${formDataGenresOfAlbum}" />
                    </div>
                    <div><g:hiddenField name="idFormAlbum" value="${formDataAlbum.id}"/></div>
                    <g:actionSubmit value="Save" action="update"/>
                </g:form>
            </div>




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