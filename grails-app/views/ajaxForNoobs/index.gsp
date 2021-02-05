<%--
  Created by IntelliJ IDEA.
  User: dimos
  Date: 4/2/2021
  Time: 3:06 μ.μ.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <title></title>

    <script>
        %{--$(document).ready(function(){--}%
        %{--    $("button").click(function(){--}%
        %{--        $.ajax({--}%
        %{--            --}%%{--url:"${g.createLink(controller:'ajaxForNoobs',action:'delete')}",--}%
        %{--            url: '@Url.Action("delete", "ajaxForNoobs")'--}%
        %{--            success: function(result){--}%
        %{--                $("#div1").html(result);--}%
        %{--            }});--}%
        %{--    });--}%
        %{--});--}%

        function changeName()
        {
            $.ajax({
                type: 'get',
                url:"<g:createLink url="[action:'delete',controller:'ajaxForNoobs']" />",

                success: function(results) {
                    $('#fname').val(results.horseType);

                    console.log(results)
                }
            });
        }

                 // $("button").click(function(){
                 //     $.ajax({url: "ajaxForNoobs/delete", success: function(){
                 //             flash.message = "successfully deleted object";
                 //         }});
                 // });

        // $("#bForButton").click(function(e) {
        //     e.preventDefault();
        //     console.log("hiii");
        //     $.ajax({
        //         type: 'post',
        //         url: 'http://localhost:9009/ajaxForNoobs/delete',
        //         beforeSend: function() {
        //             alert(1);
        //         },
        //         success: function() {
        //             alert("ok"),
        //             console.log('ok')
        //         }
        //     });
        //
        // });
        // async
    </script>
</head>
    <body>

%{--    <g:form>--}%
%{--        <g:textField name="mytextbox" value="" />--}%
%{--        <button name = "mybutton" id = "mybutton" onclick="changeName()">change name</button>--}%
%{--    </g:form><br/>--}%

%{--        <button class="button" id="bForButton" onclick="">button 1</button><br/>--}%

        <button class="button" id="bForButton" onclick="changeName()">button 2</button><br/>
    <input type="text" class="form-control" id="fname" placeholder="First Name">

    %{--        <g:textArea name="asd" value="${data}"></g:textArea><br/>--}%
        <g:link controller="album" action="index">Leap of faith</g:link><br />






    </body>

</html>