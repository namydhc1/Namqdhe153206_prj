<%-- 
    Document   : userdetail
    Created on : Dec 12, 2022, 6:29:10 PM
    Author     : Namqd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/userdetail.css" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="top " id="hero">

            <a class="home" href="theotherpage.html">Home</a>
            <a class="user-name" href="theotherpage.html">Quách Duy Nam</a>

        </div>

        <p class="title">USER INFORMATION</p>

        <br>

        <div class="mid">
            <form class="user-infor">
                <table class="table">
                    <thead>
                        <tr>
                            <td>User Name :</td>
                            <td>Quách Duy Nam</td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Email :</td>
                            <td>Quachduynam@gmail.com</td>
                        </tr>
                        <tr>
                            <td>Phone :</td>
                            <td>0123456789</td>
                        </tr>
                        <tr>
                            <td>Address :</td>
                            <td>London,Engand</td>
                        </tr>
                        <tr>
                        </tr>
                    </tbody>

                </table>

                <a class="edit" href="edit_info_user.html">EDIT YOUR PROFILE</a>
            </form>

        </div>

        <br>

        <div class="bot">
        </div>
    </div>
</body>
</html>
