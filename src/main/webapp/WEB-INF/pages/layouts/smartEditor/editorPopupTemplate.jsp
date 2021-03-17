<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
  request.setCharacterEncoding( "UTF-8" );
  response.setHeader( "Cache-Control", "no-cache, must-revalidate" );
  response.setHeader( "Pragma", "no-cache" );
  response.setDateHeader( "Expires", 0 );
  response.setHeader( "returnURI", request.getRequestURI() );
  response.setContentType( "text/html; charset=UTF-8" );
%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
  <title>Kobay</title>
  <link rel="stylesheet" href="${contextPath}/resources/css/jquery/jquery-ui-1.11.2.custom.css" />
  <link rel="stylesheet" href="${contextPath}/resources/css/jquery/jquery-ui-1.11.2.custom.structure.css" />
  <link rel="stylesheet" href="${contextPath}/resources/css/jquery/jquery-ui-1.11.2.custom.theme.css" />

  <script type="text/javascript"> var contextPath = '${contextPath}';</script>
  <script type="text/javascript" src="${contextPath}/resources/js/jquery/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="${contextPath}/resources/js/jquery/jquery.form.js"></script>
  <script type="text/javascript" src="${contextPath}/resources/js/jquery/jquery.watermark.js"></script>
  <script type="text/javascript" src="${contextPath}/resources/js/jquery/jquery-ui-1.11.2.custom.js"></script>
  <script type="text/javascript" src="${contextPath}/resources/js/jquery/jquery.blockUI.js"></script>
  <script type="text/javascript" src="${contextPath}/resources/js/jquery/grid.locale-en.js" ></script>
</head>
<body>
<tiles:insertAttribute name="body" />
</body>
</html>