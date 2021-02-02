<%--
  Created by IntelliJ IDEA.
  User: dimos
  Date: 1/2/2021
  Time: 10:25 π.μ.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>This is the basic Page</title>
    </head>

    <body>
        <div>
            <h1>Main Page</h1>
        </div>
        <div>
            <g:link controller="album" action="index">Refresh</g:link><br />
            <g:link controller="album" action="remakeTables">Remake the tables</g:link><br />
            <g:link controller="album" action="create">Add Entries To Tables</g:link><br />

        %{--            <g:link controller="album" action="deleteAll">Drop the tables</g:link><br />--}%
%{--            <g:link controller="album" action="initialization">Initialize with the default values</g:link><br />--}%
            <g:link controller="album" action="list">View The Tables</g:link><br />
        </div>



    </body>
</html>