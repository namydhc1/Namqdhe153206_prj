<%-- 
    Document   : storage
    Created on : Apr 27, 2023, 1:28:22 AM
    Author     : Namqd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/pagger.js" type="text/javascript"></script>
        <link href="css/pagger.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="bg-img">
            <h1>Quản lý nhập hàng</h1>
            <a id="home" href="home">Quay về Trang Chủ</a>
            
            <table id="table" border="5">
                <tbody>
                    <tr>
                        <td>Tên sản phẩm</td>
                        <td>Loại hàng</td>
                        <td>Số Lượng</td>
                        <td></td>
                    </tr>
                    <c:forEach items="${requestScope.storages}" var="s">
                        <tr>
                            <td id="${s.name}">${s.name}</td>
                            <td>${s.types}</td>
                            <td>${s.stocks}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div id ="containerbot" class ="pagger">        </div>
            <script>
                pagger("containerbot",${requestScope.pageindex},${requestScope.totalpage}, 3);
            </script>
        </div>
    </body>
</html>
