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
                <tr>
                    <td>Id of the album</td>
                    <td>Artist</td>
                    <td>Title of the Album</td>
                    <td>Number of songs in the album</td>
                    <td>Album Release Date</td>
                </tr>
            </table>

            <g:each in="${tableData}" var="row">
                <table class="fixed">
                    <col width="120px" />
                    <col width="120px" />
                    <col width="200px" />
                    <col width="250px" />
                    <col width="150px" />
                    <tr>
                        <td>${row.id}</td>
                        <td>${row.artist}</td>
                        <td>${row.album_title}</td>
                        <td>${row.song_number}</td>
                        <td>${row.release_date}</td>
                        <br/>
                    </tr>
                </table>
            </g:each><br/>


            <g:link controller="album" action="index">Return to the main page</g:link><br />

        </div>


    </body>
</html>