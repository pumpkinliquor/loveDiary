<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding( "UTF-8" );
	response.setHeader( "Cache-Control", "no-cache, must-revalidate" ); //TODO-lms0123: IE8 caching 확인 필요
	response.setHeader( "Pragma", "no-cache" );
	response.setDateHeader( "Expires", 0 );
	response.setHeader( "returnURI", request.getRequestURI() );
	response.setContentType( "text/html; charset=UTF-8" );
%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Content-Style-Type" content="text/css">
	<meta http-equiv="Content-Script-Type" content="text/javascript">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
	<meta http-equiv="imagetoolbar" content="no">
	<meta http-equiv="keywords" content="우표, 고미술품, 고문서, 고지도, 근현대사, 자료  화폐, 미술품, 헌책방, 만화, 사진, 스포츠카드, 인형, 장난감, 수석, 분재, 귀금속, 쥬얼리, 음반, 공예품, 엔티크, 중고용품, 생활용품 ">
	<meta name="description" content="(주)코베이 취미, 예술품, 엔티크 인터넷경매서비스 전문업체">
    <meta property="og:site_name" content="plus" />
    <meta property="og:title" content="${item.itemName}" />
    <meta property="og:description" content="# ${item.itemName} 경매 마감시간 : <fmt:formatDate value="${item.endDate}" pattern="yyyy/MM/dd HH:mm"/>까지  (주)코베이 취미, 예술품, 엔티크 인터넷경매서비스 전문업체" />
  <meta property="og:image" content="http://plus.plus.co.kr/resources/images/user/logo200.png" />
    <link rel="stylesheet" href="${contextPath}/resources/css/user/bootstrap.css" />
	<link rel="stylesheet" href="${contextPath}/resources/css/user/common.css" />
	<link rel="stylesheet" href="${contextPath}/resources/css/user/search.css" />
	<link rel="stylesheet" href="${contextPath}/resources/css/user/table.css" />
	<link rel="stylesheet" href="${contextPath}/resources/css/user/popup.css" />
	<link rel="stylesheet" href="${contextPath}/resources/css/user/nav.css" />          <!-- 메뉴와 마이페이지메뉴 -->
	<link rel="stylesheet" href="${contextPath}/resources/css/user/mosaicflow.css" />   <!-- 이미지 오버 했을때 나오는 내용 -->
	<link rel="stylesheet" href="${contextPath}/resources/css/jquery/jquery-ui-1.11.2.custom.css" />
	<script src="${contextPath}/resources/js/jquery/jquery-1.11.1.min.js"></script>
	<script src="${contextPath}/resources/js/jquery/jquery.form.js"></script>
	<script src="${contextPath}/resources/js/jquery/jquery-ui-1.11.2.custom.js"></script>
    <script src="${contextPath}/resources/js/jquery/jquery.caret-1.5.2.min.js"></script>
    <script src="${contextPath}/resources/js/jquery/jquery.watermark.js"></script>
    <script src="${contextPath}/resources/js/jquery/jquery.mosaicflow.js"></script>
    <script src="${contextPath}/resources/js/jquery/jquery.session.js"></script>
    <script src="${contextPath}/resources/js/jquery/jquery.cookie.js"></script>
	<script src="${contextPath}/resources/js/jquery/jquery-dateFormat.min.js"></script>
    <script>
      var contextPath = '${contextPath}';
      $(function () {
        $.session.remove('loginId');
        $.session.set('loginId', '${userLoginSession.userId}');
      });
    </script>
    <script src="${contextPath}/resources/js/jquery/jquery.blockUI.js"></script>
	<script src="${contextPath}/resources/js/jquery/jquery.customSelect.js"></script>
	<script src="${contextPath}/resources/js/jquery/jquery.dotdotdot.min.js"></script>
    <script src="${contextPath}/resources/js/jquery/jquery.serializejson.min.js"></script>
	<%--<script src="${contextPath}/resources/js/external/bootstrap.js"></script>--%>
	<script src="${contextPath}/resources/js/external/slidereveal.js"></script>
	<script src="${contextPath}/resources/js/external/numeral.js"></script>
	<!--[if lt IE 9]>
		<script src="${contextPath}/resources/js/external/html5shiv.js"></script>
		<script src="${contextPath}/resources/js/external/respond.min.js"></script>
		<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
	<![endif]-->
	<script src="${contextPath}/resources/js/utils/utils.js"></script>
	<script src="${contextPath}/resources/js/common.js"></script>
	<script src="${contextPath}/resources/js/user/common.js"></script>
	<script src="${contextPath}/resources/js/user/header.js"></script>
	<script src="${contextPath}/resources/js/user/inquiry.js"></script>
    <script src="${contextPath}/resources/js/user/category.js"></script>
	<script src="${contextPath}/resources/js/utils/utils.page.js"></script>

	<title>plus</title>
</head>
