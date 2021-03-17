<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<div id="contentWrap" class="contentWrap wrap" style="min-height: 923px;">
    <!-- lnb영역 -->
    <div id="lnb" class="lnb" style="height: 923px;">
        <div class="menuBtn">
            <a href="javascript:void(0)" alt="좌메뉴 여닫기 버튼">
                <i class="fa fa-arrow-left"></i>
            </a>
        </div>
        asdfafdsf
        <ul class="depth">
        <c:set var="module" value="대쉬보드"/>
        <c:set var="moduleName" value="대쉬보드"/>
        <c:forEach var="menuList1" items="${menuList1}">
            <li class="">
                    <a href="javascript:void(0)" alt="DASHBOARD"><i class="fa20 fa <c:out value="${menuList1.immIcon}"/>"></i><c:out value="${menuList1.immName}"/></a>

                <ul class="depth_two">
                <c:forEach var="menuList2" items="${menuList2}">
                    <c:if test="${menuList1.immCode eq menuList2.immParent}">
                        <c:if test="${menuList2.immPath eq url}">
                            <c:set var="module" value="${menuList2.immModule}"/>
                            <c:set var="moduleName" value="${menuList2.immName}"/>
                        </c:if>
                        <li>
                            <a href="<c:out value="${menuList2.immPath}"/>" alt="<c:out value="${menuList2.immName}"/>" class="active open"><i class="fa fa-angle-right"></i><c:out value="${menuList2.immName}"/> </a>
                        </li>
                    </c:if>
                </c:forEach>
                </ul>
                <span class="tooltip"><c:out value="${menuList1.immName}"/></span>
            </li>
        </c:forEach>
        </ul>

    </div>

    <div id="content" class="content">
        <!-- 페이지 타이틀 영역 -->
        <div class="pageTitle_area">
            <h2 class="pageTitle"><c:out value="${moduleName}"/></h2>

            <ul class="location">
                <li>
                    <a href="javascript:void(0)" alt="home">home</a>
                </li>
                <li>
                    <i class="fa fa-angle-right"></i>
                    <a href="javascript:void(0)" alt="<c:out value="${module}"/>"><c:out value="${module}"/></a>
                </li>
                <li>
                    <i class="fa fa-angle-right"></i>
                    <a href="javascript:void(0)" alt="<c:out value="${moduleName}"/>" class="pageTitle"><c:out value="${moduleName}"/></a>
                </li>
            </ul>
        </div><!--// 페이지 타이틀 영역-->
