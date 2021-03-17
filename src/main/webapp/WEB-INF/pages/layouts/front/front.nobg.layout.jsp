<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
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
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="/assets/user/${lan}/css/slick.css">
  <link rel="stylesheet" href="/assets/user/${lan}/css/csr-slick.css">
  <link rel="stylesheet" href="/assets/user/${lan}/css/style.css?v=1">
  <link rel="stylesheet" href="/assets/user/${lan}/css/slick-theme.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
  <script src="/assets/user/${lan}/js/jquery.js"></script>
  <script src="/assets/user/${lan}/js/script.js"></script>
<script type="text/javascript" charset="UTF-8" src="/assets/js/front.plus.js"></script>
</head>
<body>
		
		<div class="pc">
			<div class="main">
				<tiles:insertAttribute name="menu" />
				<tiles:insertAttribute name="contents" />
				<tiles:insertAttribute name="footer" />
			</div>
		</div>
		<div class="moblie">
			<tiles:insertAttribute name="mb_menu" />
			<tiles:insertAttribute name="mb_contents" />
			<tiles:insertAttribute name="mb_footer" />
		</div>

</body>
</html>