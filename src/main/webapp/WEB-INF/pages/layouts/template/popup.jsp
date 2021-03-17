<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script type="text/javascript">var contextPath = '${contextPath}';</script>
<%--<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/user/popup.css" />--%>
<%--<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/user/table.css" />       <!-- 테이블 -->--%>
<body>
<div class="closeBtn" style="float:right; padding:5px">
    <i class="fa fa-window-close" aria-hidden="true" onclick="javascript:LayerPopupUtils.close()"></i>
</div>
<div style="clear:both"></div>
<div id="popupWrap">
    <div class="popupContents">
        <tiles:insertAttribute name="contents" />
    </div>
</div>
</body>