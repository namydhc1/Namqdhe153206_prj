<%@page import="model.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/paggerlist.js" type="text/javascript"></script>
         <link href="css/ordercss/list.css" rel="stylesheet" type="text/css"/>
        <%
            Order order = (Order) session.getAttribute("neworder");
            if (order == null) {
                order = new Order();
            }
        %>
    </head>
    <body>
        <a id="home" href="home">Quay về Trang Chủ</a>
        <br>
        <a href="confirmorder">Xác nhận hóa đơn</a>
        <br>
        <%=order.getSize() + " "%> items in the order

        <table id="table" border="5">

            <tbody>
                <tr>
                    <td>ID</td>
                    <td>Tên sản phẩm</td>
                    <td>Tồn Kho</td>
                    <td>Giá Bán</td>
                    <td>Số Hàng Đã Bán</td>
                </tr>
                <c:forEach items="${requestScope.storages}" var="s">
                    <tr>
                        <td>${s.id}</td>
                        <td id="${s.name}">${s.name}</td>                     
                        <td>${s.stocks}</td>
                        <td>${s.unitprice}</td>
                <form action="addorder" method="POST">

                <td><input type="number" name="quantity" value="0" min="0" max="${s.stocks}"></td>
                <td>
                    <input type="hidden" value="${s.id}" name="id"/>
                    <input type="submit" value="Thêm vào hóa đơn" />
                </td>
            </form>


        </tr>
    </c:forEach>
</tbody>
</table>    
<div id ="containerbot" class ="pagger">        </div>
<script>
    pagger("containerbot",${requestScope.pageindex},${requestScope.totalpage}, 3);
</script>
</body>
</html>
