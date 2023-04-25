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


        <script>
            function deleteStorage(id)
            {
                var result = confirm("are you sure?");
                if (result)
                {
                    window.location.href = 'delete?id=' + id;
                }
            }
        </script>
    </head>
    <body>
        
        <div class="bg-img">
            <h1>Kho Hàng Của Bạn</h1>
            <a id="home" href="home">Quay về Trang Chủ</a>
            <h3><a id="insert" href="insert">Thêm Mặt Hàng Mới</a></h3>
            <table id="table" border="5">
                <tbody>
                    <tr>
                        <td>ID</td>
                        <td>Tên sản phẩm</td>
                        <td>Ngày nhập hàng</td>
                        <td>Tiền nhập hàng</td>
                        <td>Số lượng nhập kho</td>
                        <td>Tồn Kho</td>
                        <td>Loại hàng</td>
                        <td>Giá Bán</td>
                    </tr>
                    <c:forEach items="${requestScope.storages}" var="s">
                        <tr>
                            <td>${s.pid}</td>
                            <td id="${s.name}">${s.pname}</td>
                            <td>${s.dateofWarehousing}</td>
                            <td>${s.purchaseMoney}</td>
                            <td>${s.quantityWarehousing}</td>
                            <td>${s.inventory}</td>
                            <td>${s.cid}</td>
                            <td>${s.unitprice}</td>
                            <td><a id="update" href="update?id=${s.pid}">Thay Đổi Mặt Hàng </a></td>
                            <td><a id="delete" href="#" onclick="deleteStorage(${s.pid})" >Delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div id ="containerbot" class ="pagger"></div>
            <script>
                pagger("containerbot",${requestScope.pageindex},${requestScope.totalpage}, 3);
            </script>
        </div>

    </body>
</html>
