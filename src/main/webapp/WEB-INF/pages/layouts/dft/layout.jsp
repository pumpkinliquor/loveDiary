<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript">var contextPath = '${contextPath}';</script>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />

<%
    request.setCharacterEncoding("UTF-8");
    response.setHeader("Cache-Control", "no-cache, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    response.setHeader("returnURI", request.getRequestURI());
    response.setContentType("text/html; charset=UTF-8");
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>

    <!-- Jquery CSS -->
    <link rel="stylesheet" type="text/css" href="${contextPath}/asset/css/jquery/jquery-ui_ori.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/asset/css/jquery/jquery.domenu-0.100.77.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/asset/css/jquery/jquery.contextMenu.css"/>
    <!-- Jquery CSS End -->

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" type="text/css" href="${contextPath}/asset/css/bootstrap/bootstrap.min.css">
    <%--<link rel="stylesheet" type="text/css" href="${contextPath}/asset/css/open-iconic-bootstrap.css">--%>
    <%--<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">--%>
    <!-- Bootstrap CSS End -->


    <link rel="stylesheet" type="text/css" href="${contextPath}/asset/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/asset/css/layout.css?v=<%= java.lang.Math.random() * 2%>"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/asset/css/ui.jqgrid.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/asset/css/select2.min.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/asset/css/ui.multiselect.css"/>

    <%--<link rel="stylesheet" type="text/css" href="${contextPath}/asset/css/custom.css"/>--%>



    <!-- Jquery JS -->
    <script type="text/javascript" src="${contextPath}/asset/js/jquery/jquery.min.1.11.1.js"></script>
    <script type="text/javascript" src="${contextPath}/asset/js/jquery/jquery-ui-1.12.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/asset/js/jquery/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${contextPath}/asset/js/jquery/jquery.jqGrid.locale-kr.js"></script>
    <script type="text/javascript" src="${contextPath}/asset/js/jquery/jquery.domenu-0.100.77.js"></script>
    <script type="text/javascript" src="${contextPath}/asset/js/jquery/jquery.contextMenu.js"></script>
    <script type="text/javascript" src="${contextPath}/asset/js/jquery/jquery.blockUI.js"></script>
    <script type="text/javascript" src="${contextPath}/asset/js/jquery/jquery.toast.min.js"></script>
    <script type="text/javascript" src="${contextPath}/asset/js/jquery/jquery.validate.js"></script>
    <script type="text/javascript" src="${contextPath}/asset/js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="${contextPath}/asset/js/jquery/jquery.mask.min.js"></script>

    <!-- Jquery JS End -->

    <!-- BootStrap JS -->
    <script type="text/javascript" src="${contextPath}/asset/js/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="${contextPath}/asset/js/bootstrap/bootstrap-filestyle.min.js"></script>
    <!-- BootStrap JS End -->

    <script type="text/javascript" src="${contextPath}/asset/js/global.js"></script>
    <script type="text/javascript" src="${contextPath}/asset/js/select2.min.js"></script>
    <script type="text/javascript" src="${contextPath}/asset/js/ui.multiselect.js"></script>
    <script type="text/javascript" src="${contextPath}/asset/js/common.js?v=<%= java.lang.Math.random() * 2%>"></script>
    <script type="text/javascript" src="${contextPath}/asset/js/utils.js"></script>
    <script type="text/javascript" src="${contextPath}/asset/js/board.js"></script>

    <script type="text/javascript" src="${contextPath}/asset/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>


</head>
<body>
<tiles:insertAttribute name="head" />
<tiles:insertAttribute name="menu" />
<tiles:insertAttribute name="contents" />
<tiles:insertAttribute name="footer" />

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</body>
</html>
