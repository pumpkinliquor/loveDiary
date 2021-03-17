<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<tiles:insertAttribute name="head" />
<body>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/user/main.css" />         <!-- 메인페이지에서만 사용 -->
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/user/owl.carousel.css" >  <!-- 메인페이지 배너 슬라이더 사용 -->
	<script type="text/javascript" src="${contextPath}/resources/js/external/awesome-grid-1.0.2.min.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/js/user/main.js"></script>

	<div class="wrap">
		<tiles:insertAttribute name="header" />
		<div id="container">
			<div class="contents">
				<tiles:insertAttribute name="contents" />
				<%--<tiles:insertAttribute name="quick" />--%>
			</div>
		</div>
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>
