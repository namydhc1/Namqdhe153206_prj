<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="insert" method="POST">
            <!--            <input type="hidden" name="" value="34657">-->

            <table style="width: 75%" border="1">

                <tbody>
                    <tr>
                        <td>Tên Sản Phẩm</td>
                        <td> <input type="text" name="name"/></td>
                    </tr>
                    <tr>
                        <td>Ngày Nhập Hàng</td>
                        <td><input type="date" name="dateofWarehousing" /></td>
                    </tr>
                    <tr>
                        <td>Tiền Nhập Hàng</td>
                        <td><input type="text" name="purchaseMoney"/></td>
                    </tr>
                    <tr>
                        <td>Số Lượng nhập kho </td>
                        <td><input type="text" name="quantityWarehousing"/></td>
                    </tr>
                    <tr>
                        <td>Tồn Kho</td>
                        <td><input type="text" name="stocks"/></td>
                    </tr>
                    <tr>
                        <td>Loại Hàng</td>
                        <td>
                            <select name="types">
                                <c:forEach items="${requestScope.types}" var="t">
                                    <option value="${t.name}">${t.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Giá Bán</td>
                        <td><input type="text" name="unitprice"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Save"/></td>
                    </tr>
                </tbody>
            </table>
            <h3><a href="display">Quay lại kho hàng</a></h3>
        </form>
    </body>
</html>
