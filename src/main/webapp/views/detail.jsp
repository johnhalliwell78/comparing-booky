<%--
  Created by IntelliJ IDEA.
  User: John Halliwell
  Date: 10/31/2018
  Time: 8:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <c:set var="category" value="${requestScope.CATEGORY}"/>
    <c:set var="site" value="${requestScope.SITE}"/>
    <%--<c:set var="model" value="${requestScope.MODEL}"/>--%>

    <c:set var="id" value="${requestScope.PRODUCT_ID}"/>
    <c:set var="code" value="${requestScope.PRODUCT_CODE}"/>
    <c:set var="price" value="${requestScope.PRICE}"/>
    <c:set var="name" value="${requestScope.NAME}"/>
    <c:set var="author" value="${requestScope.AUTHOR}"/>

    <title><c:out value="${site}"/> -
        <c:out value="${category}"/> <c:out value="${code}"/></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/css/templatemo-style.css">

</head>

<body onload="loadDetail(${id})">
<!-- Menu -->
<div class="header">
    <jsp:include page="header.jsp"/>
</div>

<section class="page-heading">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>
                     <c:out value="${category}"/> <c:out value="${code}"/>
                </h1>
                <p><strong><c:out value="${site}"/></strong></p>
            </div>
        </div>
    </div>
</section>

<%-- Navigator --%>
<div class="container">
    <div class="row">
        <a href="/home">Trang chủ</a> &gt; <a href="/products/${site}"><c:out value="${site}"/></a> &gt; <a
            href="/products/${site}/${category}"><c:out value="${category}"/></a> &gt; <a
            href="/products/${site}/${category}/${model}"><c:out value="${model}"/></a> &gt; <a
            href="/products/id=${id}"><c:out value="${code}"/></a>
    </div>
</div>

<div class="container">
    <div class="row">
        <h2><strong><c:out value="${site}"/> - <c:out value="${category}"/> <c:out value="${code}"/></strong></h2>
    </div>
</div>

<c:set var="detail" value="${requestScope.PRODUCT}"/>

<%--<c:import charEncoding="UTF-8" url="/xslt/detail.xsl" var="xslt"/>--%>
<%--<x:transform xml="${detail}" xslt="${xslt}"/>--%>

<div class="container">
    <div class="row">
        <div class="item-detail">
            <div class="col-md-6 col-sm-12 col-xs-12">
                <div class="row detail-image">
                    <img id="product-image"/>
                </div>
                <div class="row detail-thumbs">
                    <div class="col-md-4 detail-thumb">
                        <img id="product-thumb-1" onclick="showThumbnail('product-thumb-1')"/>
                    </div>
                    <div class="col-md-4 detail-thumb">
                        <img id="product-thumb-2" onclick="showThumbnail('product-thumb-2')"/>
                    </div>
                    <div class="col-md-4 detail-thumb">
                        <img id="product-thumb-3" onclick="showThumbnail('product-thumb-3')"/>
                    </div>
                </div>
            </div>
            <div class="col-md-6 col-sm-12 col-xs-12 detail-content">
                <h2>
                    <strong>
                        <span id="product-name"></span>
                    </strong>
                </h2>
                <h3>
                    <strong>Tác Giả: </strong>
                    <span id="product-author"></span>
                </h3>
                <h3>
                    <span id="product-category"></span>
                </h3>
                <h3>
                    <strong>Mã: </strong>
                    <span id="product-code"></span>
                </h3>

                <br/>

                <h3>
                    <strong>Giá: </strong>
                    <strong><span id="product-price" style="font-size: 32px"></span></strong> VNĐ
                </h3>

                <br/>
                <br/>

                <a id="product-link" class="btn btn-default btn-lg">
                    Xem trên <span id="product-site"></span>
                </a>
            </div>
        </div>
    </div>
</div>

<c:import charEncoding="UTF-8" url="/xslt/preview.xsl" var="xsltPreview"/>

<c:if test="${site ne 'KHAITAM'}">
    <c:set var="khaitamstore" value="${requestScope.KHAITAM_SUGGESTS}"/>
    <hr/>
    <div class="container">
        <div class="model-product-row">
            <div class="row">
                <h2>Sản phẩm tương tự có tại <strong>KHAITAM:</strong></h2>
            </div>

            <x:transform xml="${khaitamstore}" xslt="${xsltPreview}">
                <x:param name="compare" value="${price}"/>
            </x:transform>

        </div>
    </div>
</c:if>

<c:if test="${site ne 'VANLANG'}">
    <c:set var="vanlangstore" value="${requestScope.VANLANG_SUGGESTS}"/>
    <hr/>
    <div class="container">
        <div class="model-product-row">
            <div class="row">
                <h2>Sản phẩm tương tự có tại <strong>VANLANG.VN:</strong></h2>
            </div>

            <x:transform xml="${vanlangstore}" xslt="${xsltPreview}">
                <x:param name="compare" value="${price}"/>
            </x:transform>

        </div>
    </div>
</c:if>
<br/>
<br/>
<br/>
<script>
    function loadDetail(id) {
        var url = "/product-detail/" + id;
        var xhttp = new XMLHttpRequest();

        xhttp.onreadystatechange = function () {
            if (xhttp.readyState === 4 && xhttp.status === 200) {
                var xmlString = xhttp.responseText;
                console.log(xmlString);

                var parser = new DOMParser();
                var xmlDoc = parser.parseFromString(xmlString, "application/xml");

                //get product images
                var imageTag = document.getElementById("product-image");
                imageTag.src = xmlDoc.getElementsByTagName("avatarLink")[0].textContent;

                //get product name
                var nameTag = document.getElementById("product-name");
                nameTag.innerHTML = xmlDoc.getElementsByTagName("name")[0].textContent;

                //get product author
                var authorTag = document.getElementById("product-author");
                authorTag.innerHTML = xmlDoc.getElementsByTagName("author")[0].textContent;

                //get product code
                var codeTag = document.getElementById("product-code");
                codeTag.innerHTML = xmlDoc.getElementsByTagName("code")[0].textContent;

                // //get product category
                // var categoryTag = document.getElementById("product-category");
                // categoryTag.innerHTML = xmlDoc.getElementsByTagName("category")[0].textContent;

                // get product price
                var priceTag = document.getElementById("product-price");
                priceTag.innerHTML = xmlDoc.getElementsByTagName("price")[0].textContent
                    .toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
                    .replace(/\.(\d+)?/g, "");

                //get product link
                var linkTag = document.getElementById("product-link");
                linkTag.href = xmlDoc.getElementsByTagName("productLink")[0].textContent;

                //get product site
                var siteTag = document.getElementById("product-site");
                siteTag.innerHTML = xmlDoc.getElementsByTagName("siteKey")[0].textContent;
            }
        };

        xhttp.open("GET", url, true);
        xhttp.send();
    }

    function showThumbnail(thumbId) {
        var imageTag = document.getElementById("product-image");
        var thumbTag = document.getElementById(thumbId);

        imageTag.src = thumbTag.src;
    }
</script>

</body>

</html>