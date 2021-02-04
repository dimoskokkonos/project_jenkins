<%--
  Created by IntelliJ IDEA.
  User: dimos
  Date: 1/2/2021
  Time: 12:59 μ.μ.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <title>View All Data</title>
    </head>
    <body>
        <div>
            <h2>Search via album title</h2>
            <g:form>
                <g:textField name="searchTagAlbum" value="${searchTagAlbum}"/>
                <g:actionSubmit value="Search" action="list"/>
            </g:form>
        </div>
        <div>
            <h1>The Album Data</h1>
            <table class="fixed">
                <col width="120px" />
                <col width="120px" />
                <col width="200px" />
                <col width="250px" />
                <col width="150px" />
                <col width="200px" />
                <tr>
                    <td>Id of the album</td>
                    <td>Artist</td>
                    <td>Title of the Album</td>
                    <td>Number of songs in the album</td>
                    <td>Album Release Date</td>
                    <td>Music Genres of the Album</td>
                </tr>
            </table>

            <g:each in="${albumData}" var="row">
                <table class="fixed">
                    <col width="120px" />
                    <col width="120px" />
                    <col width="200px" />
                    <col width="250px" />
                    <col width="150px" />
                    <col width="220px" />
                    <tr>
                        <g:each in="${row}" var="row2">
                            <td>${row2}</td>
                        </g:each>
                        <td>
                            <g:link controller="album" action="deleteOne" method="post" params="[albumId: row[0]]">
                                <input type="button" value="Delete Entry" class="button"/>
                            </g:link>
                            <g:link controller="album" action="list" method="get" params="[formIdAlbum: row[0]]">
                                <input type="button" value="Edit" class="button"/>
                            </g:link>
                        </td><br/>
                    </tr>
                </table>

            </g:each><br/>
        </div>

        <div>
            <h2>Search via genre name</h2>
            <g:form>
                <g:textField name="searchTagGenre" value="${searchTagGenre}"/>
                <g:actionSubmit value="Search" action="list"/>
            </g:form>
        </div>
        <div>
            <h1>The Genre Data</h1>
            <table class="fixed">
                <col width="120px" />
                <col width="120px" />
                <col width="200px" />
                <col width="250px" />
                <tr>
                    <td>Id of the genre</td>
                    <td>Name of genre</td>
                    <td>Creator of the music genre</td>
                    <td>Popular Genre</td>
                </tr>

            </table>

            <g:each in="${genreData}" var="row">
                <table class="fixed">
                    <col width="120px" />
                    <col width="120px" />
                    <col width="200px" />
                    <col width="120px" />
                    <col width="250px" />

                    <tr>
                        <td>${row.id}</td>
                        <td>${row.name}</td>
                        <td>${row.creator}</td>
                        <td>${row.isPopular}</td>
                        <td>
                            <g:link controller="album" action="deleteOne" method="post" params="[genreId: row.id]">
                                <input type="button" value="Delete Genre" class="button"/>
                            </g:link> <br/>
                            <g:link controller="album" action="list" method="get" params="[formIdGenre: row.id]">
                                <input type="button" value="Edit" class="button"/>
                            </g:link>
                        </td>
                        <br/>
                    </tr>

                </table>
            </g:each><br/>
        </div><br/>
        <div>
            <h1>Edit an album entry</h1>
            <g:form>
                Name of artist: <g:textField required="true" name='artist' value="${formDataAlbum.artist}"/> <br />
                Title of the album: <g:textField required="true" name='albumTitle' value="${formDataAlbum.albumtitle}"/> <br />
                Number of songs: <g:textField required="true" name='songNumber' value="${formDataAlbum.songnumber}"/> <br />
                Release Date: <g:datePicker name='releaseDate' precision="day" value="${formDataAlbum.releasedate}"/> <br />
                <g:hiddenField name="idFormAlbum" value="${formDataAlbum.id}" />
                    Genre: <g:select name="genres"
                                 from="${dataGenresName}"
                                 multiple="multiple"
                                 value="${formDataGenresOfAlbum}" /> <br />
                <g:actionSubmit value="Save" action="update"/>
            </g:form>
        </div>


        <div>
            <h1>Edit a genre entry</h1>
            <g:form>
                Name of Music Genre: <g:textField required="true" name='name' value="${formDataGenres.name}"/> <br />
                Name of Creator: <g:textField required="true" name='creator' value="${formDataGenres.creator}"/> <br />
                Popular Music Genre: <g:checkBox  checked="${formDataGenres.isPopular}" name='isPopular'/> <br />
                <g:hiddenField name="idFormGenre" value="${formDataGenres.id}"/><br />
                <g:actionSubmit value="Save" action="update"/>
            </g:form>
        </div>

    <g:link controller="album" action="list">Refresh Page</g:link><br />
    <g:link controller="album" action="index">Return to Main Page</g:link><br />
    </body>
</html>