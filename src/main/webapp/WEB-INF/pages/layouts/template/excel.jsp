<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="application/vnd.ms-excel; name='excel', text/html; charset=euc-kr" pageEncoding="euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="excelName" value="${excelName}"/>
<%
  request.setCharacterEncoding("UTF-8");
  response.setHeader("Cache-Control", "no-cache, must-revalidate");
  response.setHeader("Pragma", "no-cache");
  response.setDateHeader("Expires", 0);
  response.setHeader("returnURI", request.getRequestURI());
  response.setHeader("Content-Type", "application/vnd.ms-excel;charset=euc-kr");
  response.setHeader("Content-Disposition", "attachment; filename=KOBAY_" + pageContext.getAttribute("excelName") + ".xls");
%>
<body>
  <tiles:insertAttribute name="contents" />
</body>