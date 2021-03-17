<%@ page import="com.plushih.common.constant.LoginSession" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%
    String sesstionLang = "";
    String sesstionLANGIDX = "";
    if (request.getSession() != null) {
        sesstionLang = LoginSession.getLanguage(request.getSession());//.getAttribute(LoginSession.LANG).toString();
        sesstionLANGIDX = LoginSession.getLanIdx(request.getSession());//request.getSession().getAttribute(LoginSession.LANGIDX).toString();

        pageContext.setAttribute("sesstionLang", sesstionLang);
        pageContext.setAttribute("sesstionLANGIDX", sesstionLANGIDX);

    }

%>
<script>


</script>

