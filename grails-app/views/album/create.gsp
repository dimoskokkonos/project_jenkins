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

%{--    FIXME: Όταν επιλέγεται string ως είσοδος στο songNumber --> error... Πως θα αναγκάσω επιλογή integer?--}%
        Number of songs: <g:textField required="true" name='songNumber' /> <br />
        Release Date: <g:datePicker name='releaseDate' precision="day" /> <br />

%{--        FIXME: Όταν δεν επιλέγω genre, έχει τιμή null που δεν είναι αποδεκτή..
              Πως θα αναγκάσω επιλόγη έστω ενός ή θα βάλω default?--}%

        Genre: <g:select name="genres"
                         from="${genresData.name}"
                         size="5"
                         multiple="multiple"
                         params="[genresData: genresData.id]"/> <br />
        <g:actionSubmit value="Save" action="insert"/>
      </g:form>


    </div>
    <div>

      <h1>Create new music genre</h1>
      <g:form controller="album" action="insert">
        Name of Music Genre: <g:textField required="true" name='name' /> <br />
        Name of Creator: <g:textField required="true" name='creator' /> <br />
        Popular Music Genre: <g:checkBox  checked="false" name='isPopular' /> <br />
        <g:actionSubmit value="Save" action="insert"/>
      </g:form>

    </div>

    <g:link controller="album" action="index">Return to Main Page</g:link><br />



  </body>
</html>