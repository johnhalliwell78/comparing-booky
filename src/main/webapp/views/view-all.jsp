<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <c:set var="site" value="${requestScope.SITE}"/>
    <c:set var="category" value="${requestScope.CATEGORY}"/>

    <title><c:out value="${site}"/></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/css/templatemo-style.css">
</head>

<body>
<!-- Menu -->
<div class="header">
    <jsp:include page="header.jsp"/>
</div>

<section class="page-heading">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1><c:out value="${site}"/></h1>
            </div>
        </div>
    </div>
</section>

<br/>

<div class="container">
    <div class="row">
        <a href="/home">Trang chủ</a> &gt; <a href="/products/${site}"><c:out value="${site}"/></a> &gt; <a href="/products/${site}/${category}"><c:out value="${category}"/></a>

    </div>
</div>

<div class="container">
    <div class="row">
        <h1><strong><c:out value="${site}"/></strong></h1>
    </div>
</div>

<c:set var="products" value="${requestScope.PRODUCTS}"/>

<c:import charEncoding="UTF-8" url="/xslt/view-category.xsl" var="xslt"/>

<x:transform xml="${products}" xslt="${xslt}"/>

<br/>
<br/>
<br/>

<c:set var="current" value="${requestScope.CURRENT_PAGE}"/>
<c:set var="total" value="${requestScope.NUMBER_OF_PAGES}"/>

<div class="container">
    <div class="row">
        <div class="col">
            <ul class="pagination">
                <c:forEach var="pageNumber" begin="1" end="${total}">
                    <c:choose>
                        <c:when test="${pageNumber eq current}">
                            <li class="page-item active">
                                <a class="page-link"
                                   href="/products/${site}/page=${pageNumber}">${pageNumber}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link"
                                   href="/products/${site}/page=${pageNumber}">${pageNumber}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>

<footer>
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <h4>PRX301 - Project</h4>
                <h4>GIANGTHSE62424</h4>
            </div>
        </div>
    </div>
</footer>

</body>

</html>
