<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>Giới thiệu</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/css/templatemo-style.css">

</head>

<style>
    .loader {
        border: 16px solid #f3f3f3;
        border-radius: 50%;
        border-top: 16px solid #000000;
        width: 120px;
        height: 120px;
        -webkit-animation: spin 2s linear infinite;
        animation: spin 2s linear infinite;
    }

    @-webkit-keyframes spin {
        0% {
            -webkit-transform: rotate(0deg);
        }
        100% {
            -webkit-transform: rotate(360deg);
        }
    }

    @keyframes spin {
        0% {
            transform: rotate(0deg);
        }
        100% {
            transform: rotate(360deg);
        }
    }
</style>

<body>
<!-- Menu -->
<div class="header">
    <jsp:include page="header.jsp"/>
</div>

<h1 class="container text-center">THU THẬP DỮ LIỆU</h1>
<c:set var="role" value="${sessionScope.USER_ROLE}"/>
<c:if test="${role eq 'ROLE_ADMIN'}">
    <div class="container" style="width: 400px">
        <div class="row text-center" style="height: 50px">
            <div class="loader center-block" style="visibility: hidden" id="loader">
            </div>
            <h1 id="crawl-info"></h1>
        </div>
        <div class="row" style="margin-top: 200px">
            <div class="col-md-6">
                <button class="btn btn-lg btn-default btn-block" onclick="enableLoader(); crawl('run')">Chạy</button>
            </div>
            <div class="col-md-6">
                <button class="btn btn-lg btn-block" onclick="disableLoader(); crawl('stop')">Ngừng</button>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${role ne 'ROLE_ADMIN'}">
    <div class="container">
        <div class="row">
            <h1>Xin lỗi, bạn không có quyền truy cập trang này...</h1>
            <h3><a href="/home">Về trang chủ</a></h3>
        </div>
    </div>
</c:if>

<script>
    function crawl(status) {
        var url = "/crawl-data";
        var status = "status=" + status;
        var xhttp = new XMLHttpRequest();
        xhttp.open('POST', url, true);
        xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhttp.send(status);
    }

    function enableLoader() {
        var loader = document.getElementById("loader");
        loader.style.visibility = "visible";

        var info = document.getElementById("crawl-info");
        info.innerHTML = "Đang thu thập dữ liệu...";
    }

    function disableLoader() {
        var loader = document.getElementById("loader");
        loader.style.visibility = "hidden";

        var info = document.getElementById("crawl-info");
        info.innerHTML = "";
    }
</script>
</body>
</html>