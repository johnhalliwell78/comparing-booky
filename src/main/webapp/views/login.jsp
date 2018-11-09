<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Đăng nhập</title>

    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/css/templatemo-style.css">

</head>
<body>
<div class="container" style="width: 400px; margin-top: 200px;  display: block">
    <div class="row">
        <div class="col">
            <%--<img href="/home" src="/img/booky-icon.jpg"/>--%>
        </div>
    </div>
    <hr/>
    <div class="row">
        <div id="error">

        </div>
        <form>
            <div class="form-group row">
                <h4>Tên đăng nhập:</h4>
                <div class="col">
                    <input class="form-control" type="text" placeholder="Tên đăng nhập" id="username"/>
                </div>
            </div>
            <div class="form-group row">
                <h4>Mật khẩu:</h4>
                <div class="col">
                    <input class="form-control" type="password" placeholder="Mật khẩu" id="password"/>
                </div>
            </div>
            <br/>
            <div class="form-group row">
                <button class="btn btn-default btn-lg btn-block" type="button" onclick="login()">Đăng nhập</button>
            </div>
        </form>
    </div>
    <div class="row" style="float: left">
        <a href="/home">Về trang chủ</a>
    </div>
</div>

<script>
    function login() {
        var errorDiv = document.getElementById('error');
        errorDiv.innerHTML = '';

        var username = document.getElementById('username').value;
        var password = document.getElementById('password').value;


        var url = '/login';
        var params = 'username=' + username + '&password=' + password;
        var xhttp = new XMLHttpRequest();

        xhttp.open('POST', url, true);
        xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

        xhttp.onreadystatechange = function () {
            if (xhttp.readyState === 4 && xhttp.status === 200) {
                window.location.replace('http://localhost:8080/home');
            } else if (xhttp.status === 404) {
                errorDiv.innerHTML = '';
                var errorInform = document.createElement('div');
                errorInform.className = 'alert alert-danger';
                errorInform.innerHTML = 'Tên đăng nhập hoặc mật khẩu không chính xác, xin thử lại.';

                errorDiv.appendChild(errorInform);
            }
        };

        xhttp.send(params);
    }
</script>
</body>
</html>