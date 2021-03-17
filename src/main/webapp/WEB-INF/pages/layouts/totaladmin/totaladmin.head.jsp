<%@ page import="com.plushih.common.constant.LoginSession" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%/*<title>삼일제약 _ 관리자정보</title>*/%>
<%
//   String sesstionId = "";
   String sesstionNm = "";
   if(request.getSession()!=null){
       sesstionNm = LoginSession.getLoginName(request.getSession());
//      sesstionId = request.getSession().getAttribute(LoginSession.ID).toString();
//      sesstionNm = request.getSession().getAttribute(LoginSession.NAME).toString();
//
   }
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


    <!-- navbar -->
    <nav class="main-header navbar navbar-expand navbar-white navbar-light">

        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-times"></i></a>
            </li>
        </ul>

        <ul class="navbar-nav ml-auto">
            <li class="btn fz-12"><%=sesstionNm%></li>
            <li class="btn  fz-12 "><a href="/">FRONT</a></li>
            <li class="btn  fz-12 "><a href="/plusadmin/main">PLUSADMIN</a></li>
            <li class="btn btn-secondary fz-12 btn_basic logout">로그아웃</li>
        </ul>

    </nav>
    <!-- navbar -->
