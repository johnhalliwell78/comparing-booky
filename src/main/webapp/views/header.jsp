<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Header</title>
</head>
<body>

<div class="container" style="width: 1370px">
    <div class="row" style="display: inline">
        <div class="col-md-12">
            <img href="/home" src="/img/booky-logo.jpg"/>
        </div>
        <%--<div class="col-md-6 col-sm-12 col-xs-12" style="margin-top: 20px">--%>
        <%--<form class="form-inline" action="/search" method="post">--%>
        <%--<div class="form-group">--%>
        <%--<input class="form-control" type="text" name="searchValue"--%>
        <%--placeholder="Aviator, RB3025..."/>--%>
        <%--</div>--%>
        <%--<button class="btn btn-default" type="submit">Tìm kiếm</button>--%>
        <%--</form>--%>
        <%--</div>--%>
    </div>
    <hr/>
    <nav class="navbar" role="navigation">

        <div id="main-nav" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li>
                    <a href="/home">TRANG CHỦ</a>
                </li>
                <li>
                    <a href="/categories/AVIATOR">GIỚI THIỆU</a>
                </li>

                <c:set var="categories" value="${sessionScope.CATEGORIES}"/>

                <li class="my-dropdown">
                    <a class="drop-btn" href="/products/KHAITAM">KHAI TAM BOOK</a>
                    <div class="my-dropdown-content">
                        <a href="/products/KHAITAM/VIETNAM">
                            Văn Học Việt Nam
                        </a>
                        <a href="/products/KHAITAM/THEGIOI">
                            Văn Học Thế Giới
                        </a>
                    </div>
                </li>
                <li class="my-dropdown">
                    <a class="drop-btn" href="/products/VANLANG">VAN LANG BOOK</a>
                    <div class="my-dropdown-content">
                        <a href="/products/VANLANG/VIETNAM">
                            Văn Học Việt Nam
                        </a>
                        <a href="/products/VANLANG/THEGIOI">
                            Văn Học Thế Giới
                        </a>
                    </div>
                </li>
                <c:set var="user" value="${sessionScope.USER_NAME}"/>
                <c:if test="${user eq null}">
                    <li>
                        <a href="/views/login.jsp">ĐĂNG NHẬP</a>
                    </li>
                </c:if>

                <c:if test="${user ne null}">
                    <c:set var="role" value="${sessionScope.USER_ROLE}"/>
                    <c:if test="${role eq 'ROLE_ADMIN'}">
                        <li>
                            <a href="/views/crawl-data.jsp">DỮ LIỆU</a>
                        <li>
                    </c:if>
                    <c:if test="${role ne 'ROLE_ADMIN'}">
                        <li>
                            <a class="disabled"><strong>Xin chào, <c:out value="${user}"/></strong></a>
                        </li>
                    </c:if>
                    <li>
                        <a href="/logout">ĐĂNG XUẤT</a>
                    </li>
                </c:if>

            </ul>
        </div>
    </nav>
</div>

</body>
</html>