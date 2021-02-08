<%--
  Created by IntelliJ IDEA.
  User: dimos
  Date: 2/2/2021
  Time: 3:33 μ.μ.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<asset:stylesheet src="bootstrap.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<html>
  <head>
      <title></title>
      <script>

        function submitAlbum() {
          if ( isNaN($("#songNumb").val() ) ) {
            alert("Please input only a number")
            return "hm huh"
          }
          // if ()
          if ($("#genreSelect").val().length === 0) {
            alert("Please select at least on music genre")
            return "hm huh"
          }

          var form = $("form");
          var url = "<g:createLink url="[action:'insert',controller:'album']" />";

          $.ajax({
            type: "POST",
            url: url,
            data: form.serialize(), // serializes the form's elements.
            success: function()
            {
              // alert(data); // show response from the php script.
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
            <g:link class="nav-link active" controller="album" action="create">Refresh Page</g:link>
          </li>
          <li class="nav-item">
            <g:link class="nav-link active"  controller="album" action="index">View Table Data</g:link><br />
          </li>
          <li class="nav-item">
            <g:link class="nav-link active"  controller="album" action="remakeTables">Remake the tables</g:link><br />
          </li>
        </ul>
      </div>
    </div>
  </nav>
%{--          FIXME: Όταν επιλέγεται string ως είσοδος στο songNumber --> error... Πως θα αναγκάσω επιλογή integer?--}%

  <br/> <br/> <br/>
  <div class="container">
      <div class="row">
        <div class="col-sm" align="left">
          <form align="middle" id="formAlbum">
            <h3>Edit an album entry</h3>
            <div class="form-group">
              <label>Name of artist: </label>
              <g:textField class="form-control" required="true" name='artist' />
            </div>
            <div class="form-group">
              <label>Title of the album: </label>
              <g:textField class="form-control" required="true" name='albumTitle' />
            </div>
            <div class="form-group">
              <label>Number of songs: </label>
              <g:textField class="form-control" required="true" name='songNumber' id="songNumb"/>
            </div>
            <div class="form-group">
              <label>Release Date: </label>
              <g:datePicker class="form-control"  name='releaseDate' precision="day" />
            </div>
            <div class="form-group" align="center">
              <label>Genre: </label>
              <g:select name="genres"
                        id="genreSelect"
                        class="form-control"
                        from="${genresData.name}"
                        size="5"
                        multiple="multiple"
                        params="[genresData: genresData.id]"
              />
            </div>
            <button type="button" class="btn btn-outline-info"
                    onclick="submitAlbum()">Submit</button>
          </form>
        </div>
        <div class="col-sm" align="right">
          <form align="middle">
            <h3>Create new music genre</h3>
            <div class="form-group">
              <label>Name of Music Genre</label>
              <g:textField class="form-control" required="true" name='name'/>
            </div>
            <div class="form-group">
              <label>Name of Creator</label>
              <g:textField class="form-control" required="true" name='creator'/>
            </div>
            <div class="form-group" id="numberBox">
              <label>Popular Music Genre</label>
              <g:checkBox  class="form-control" checked="false" name='isPopular' />
            </div>
            <button type="button" class="btn btn-outline-info"
                    onclick="submitAlbum()">Submit</button>
          </form>
        </div>
      </div>
    </div>



  </body>
</html>