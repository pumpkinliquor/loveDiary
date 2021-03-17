<!doctype html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
    request.setCharacterEncoding("UTF-8");
    response.setHeader("Cache-Control", "no-cache, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    response.setHeader("returnURI", request.getRequestURI());
    response.setContentType("text/html; charset=UTF-8");

%>
<c:set var="ran"><%= java.lang.Math.round(java.lang.Math.random() * 1234567) %></c:set>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AIGO</title>
    <link rel="stylesheet" href="/assets/css/style_m.css?v=1">
    <link rel="stylesheet" href="/assets/pb/assets/plugin/owlcarousel/owl.carousel.min.css">
	<link rel="stylesheet" href="/assets/pb/assets/plugin/owlcarousel/owl.theme.default.css">
<!--     <link rel="stylesheet" href="/assets/css/simplePagination.css"> -->
<!-- 	<script type="text/javascript" charset="UTF-8" src="/assets/js/jquery.min.js"></script> -->
	<script type="text/javascript" charset="UTF-8" src="/assets/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="/assets/js/boostrap/popper.js"></script>
    <script type="text/javascript" charset="UTF-8" src="/assets/js/boostrap/bootstrap.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="/assets/js/datatables.min.js"></script>
     <script type="text/javascript" charset="utf-8" src="/assets/js/jquery/jquery.form.min.js"></script>
<!--     <script type="text/javascript" charset="UTF-8" src="/assets/js/jquery.simplePagination.js"></script> -->
    <script type="text/javascript" charset="UTF-8" src="/assets/js/global.js"></script>
    <script type="text/javascript" charset="UTF-8" src="/assets/js/plus.js?v=1"></script>
    <script type="text/javascript" charset="UTF-8" src="/assets/js/front.plus.js"></script>
    <script type="text/javascript" charset="UTF-8" src="/assets/js/script_m.js?v=1"></script>
    <script type="text/javascript" charset="UTF-8" src="/assets/pb/assets/plugin/owlcarousel/owl.carousel.min.js"></script>
</head>
<body>



    <tiles:insertAttribute name="contents" />
    <tiles:insertAttribute name="footer" />


<!--공통스크립트-->
<!-- <script type="text/javascript" charset="UTF-8" src="/assets/js/jquery.min.js"></script> -->

<!--메인 progress bar-->
<script>
   //main
</script>

</body>
</html>