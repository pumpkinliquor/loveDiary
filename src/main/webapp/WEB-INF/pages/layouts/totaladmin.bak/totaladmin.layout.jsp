<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html lang="en">
<%
    request.setCharacterEncoding("UTF-8");
    response.setHeader("Cache-Control", "no-cache, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    response.setHeader("returnURI", request.getRequestURI());
    response.setContentType("text/html; charset=UTF-8");
%>
<head>
<script src="/assets/js/templates.admin.js?v=1"></script>
</head>
<body class="fix-header card-no-border fix-sidebar">
    <div class="preloader">
        <div class="loader">
            <div class="loader__figure"><div><img src ="/assets/images/ajax-loader.svg" alt="Loading" /></div></div>
            <p class="loader__label">TOTAL Admin</p>
        </div>
    </div>
    <!-- Main wrapper - style you can find in pages.scss -->

    <div id="main-wrapper">
<tiles:insertAttribute name="head" />
    <!-- container -->
        <aside class="left-sidebar">
<tiles:insertAttribute name="menu" />
        </aside>
            <!-- Page Content -->
        <div class="page-wrapper">
            <div class="container-fluid">
<tiles:insertAttribute name="contents" />
            </div>
        </div>
<tiles:insertAttribute name="footer" />
</div>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</body>
</html>
