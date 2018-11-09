<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Trang chủ</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/css/templatemo-style.css">

</head>
<body>
<div class="header">
    <jsp:include page="header.jsp"/>
</div>
<section class="services">
    <div class="container">
        <div class="row">
            <div class="heading">
                <h2>TÌM SÁCH</h2>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="service-item">
                    <form class="form-inline" action="/search">
                        <div class="form-group">
                            <input class="form-control" type="text" name="searchValue"
                                   placeholder="Tên sách, Tên tác giả..."/>
                        </div>
                        <button class="btn btn-default" type="submit">Tìm kiếm</button>
                    </form>
                </div>
            </div>

        </div>
    </div>
</section>
<section class="services">
    <div class="container">
        <div class="row">
            <div class="heading">
                <h2>NHÀ SÁCH ONLINE</h2>
            </div>
        </div>
        <div class="row" style="text-align: center">
            <div class="col-sm-12 col-md-6 col-xs-12">
                <h3>KHAITAMBOOK</h3>
                <div class="producer">
                    <a href="https://www.sachkhaitam.com">
                        <img src="/img/khaitam-logo.png"/>
                    </a>
                </div>
            </div>
            <div class="col-sm-12 col-md-6 col-xs-12">
                <h3>VANLANGBOOK</h3>
                <div class="producer">
                    <a href="https://online.vanlang.vn">
                        <img src="/img/vanlang-logo.png"/>
                    </a>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>