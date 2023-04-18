<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <div class="bg-img">
            <h1>Quản Lý My Storage</h1>
            <form action="login" method="POST">
                <div class="login">
                    <h2>Đăng nhập Tài Khoản</h2>
                    <input type="text" name="username" placeholder="Tài Khoản" /> <br/>
                    <input type="password" name="password" placeholder="Mật Khẩu" /> <br/>
                    <p >${loginFailed}</p>
                    <input id="login" type="submit" value="Đăng Nhập" /><br>
                    
                    <a id="register" href="register" >Tạo tài khoản mới</a>

                </div>
            </form>
        </div>
    </body>
</html>
