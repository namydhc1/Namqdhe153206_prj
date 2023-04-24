<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/register.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <div class="bg-img">
            <h1>Tạo tài khoản mới</h1>
            
            <div class="register">
                <form action="register" method="POST">
                    <h5 style="color: red">${requestScope.mess}</h5>
                    <input type="text" placeholder="Tên" name="displayname" /> <br/>
                    <input type="text" placeholder="Tài Khoản" name="username" /> <br/>
                    <input type="password" placeholder="Mật Khẩu" name="password"/> <br/>
                    <input type="password" placeholder="Nhập lại mật khẩu" name="repassword" required><br>
                    <p >${registerFailed}</p>
                    <input id="register" type="submit" value="Đăng Ký" /><br>
                    <a id="login" href="login">Bạn đã có tài khoản?</a><br>
                </form>
            </div>
        </div>
    </body>
</html>
