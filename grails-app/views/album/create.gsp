<%--
  Created by IntelliJ IDEA.
  User: dimos
  Date: 2/2/2021
  Time: 3:33 μ.μ.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
      <title></title>
  </head>
  <body>
    <div>

      <h1>Create new album</h1>
      <g:form controller="album" action="insert">
        Name of artist: <g:textField required="true" name='artist' /> <br />
        Title of the album: <g:textField required="true" name='albumTitle' /> <br />
        Number of songs: <g:textField required="true" name='songNumber' /> <br />
        Release Date: <g:datePicker name='releaseDate' precision="day" /> <br />


        Genre: <g:select name="genres"
                         from="${genresData.name}"
                         multiple="multiple"
                         params="[genresData: genresData.id]"
                         noSelection="['null':'-Choose the genre of music-']"/>
        <g:actionSubmit value="Save" action="insert"/>
      </g:form>

%{--      TODO: να προσθέσω φόρμα για την είσοδο genre--}%


      <g:link controller="album" action="index">Return to Main Page</g:link><br />
    </div>





  </body>
</html>