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
                <col width="400px" />
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
                    <col width="400px" />

                    <tr>
                        <g:each in="${row}" var="row2">
                            <td>${row2}</td>
                        </g:each>
                        <br/>
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
                    <col width="250px" />
                    <tr>
                        <td>${row.id}</td>
                        <td>${row.name}</td>
                        <td>${row.creator}</td>
                        <td>${row.is_popular}</td>
                        <br/>
                    </tr>
                </table>
            </g:each><br/>
        </div>
%{--        <div>--}%
%{--            <g:link controller="album" action="index">Return to the main page</g:link><br />--}%
%{--        </div>--}%

%{--    <div>--}%
%{--        <g:each in="${mixedData}" var="row">--}%
%{--            r=${row} <br/>--}%
%{--        </g:each>--}%
%{--    </div>--}%


    </body>
</html>