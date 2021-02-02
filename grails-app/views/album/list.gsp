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
                            <g:link controller="album" action="deleteOne" method="post" params="[entry:row[0]]">
                                <input type="button" value="Delete Entry" class="button"/>
                            </g:link>

%{--                            TODO: Implement update functionality to the button--}%
                            <g:link controller="album" action="update" method="post">
                                <input type="button" value="Edit --> WIP" class="button"/>
                            </g:link>
                        </td><br/>
                    </tr>
                </table>

            </g:each><br/>
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
                            <g:link controller="album" action="deleteOne" method="post" params="[entry2:row.id]">
                                <input type="button" value="Delete Genre" class="button"/>
                            </g:link>

%{--                            TODO: Implement update functionality to the button--}%
                            <g:link controller="album" action="update" method="post">
                                <input type="button" value="Edit" class="button"/>
                            </g:link>
                        </td>
                        <br/>
                    </tr>

                </table>
            </g:each><br/>
        </div><br/>


    <g:link controller="album" action="index">Return to Main Page</g:link><br />
    <g:link controller="album" action="remakeTables">AGAIN, BUT BETTER</g:link>

    </body>
</html>