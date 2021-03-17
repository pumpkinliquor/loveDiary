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

<c:if test="${lan!='EN'}">
    <div class="header">
        <div class="header-menu">
            <div class="logo">
                <a href="/main/KR">
                    <img src="/assets/user/KR/img/logo.png" alt="로고">
                </a>
            </div>
            <div class="suffix">
                <ul>
                    <li class="langs accordion">
                        <a href="javascript:void(0)" onclick="mb_selectLang('click')" name="mbLang">KOR</a>
                    </li>
                    <li class="langs panel">
                        <a href="javascript:void(0)" onclick="mb_selectLang('click')" name="mbLang">ENG</a>
                    </li>
                </ul>
                <div class="hamburger hamburger--slider">
                    <div class="hamburger-box">
                        <div class="hamburger-inner"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="menu">
            <div class="gnb">
                <div class="border-type-accordion has-arrow-accordion">
                    <ul>
                        <li>
                            <ul>
                                <li class="accordion">About US</li>
                                <li class="panel">
                                    <ul>
                                        <li><a href="/front/aboutUs/vision?tab=1">VISION</a></li>
                                        <li><a href="/front/aboutUs/samilHistory?tab=2">SAMIL HISTORY</a></li>
                                        <li><a href="/front/aboutUs/design?tab=3">디자인 삼일</a></li>
                                        <li><a href="/front/aboutUs/ethical?tab=4">윤리경영</a></li>
                                        <li><a href="/front/aboutUs/info?tab=5">오시는 길</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <ul>
                                <li class="accordion">CSR</li>
                                <li class="panel">
                                    <ul>
                                        <li class="menu-tit"><a href="/front/csr" class="fw-bold">CSR</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <ul>
                                <li class="accordion">OUR BUSINESS</li>
                                <li class="panel">
                                    <ul>
                                        <li><a href="/front/ourBusiness/cdmo">CDMO</a></li>
                                        <li><a href="/front/ourBusiness/rd">R&D</a></li>
                                        <li><a href="/front/ourBusiness/global">GLOBAL PARTNERS</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <ul>
                                <li class="accordion">PRODUCTS</li>
                                <li class="panel">
                                    <ul>
                                        <li><a href="/front/product/searchProduct">제품검색</a></li>
                                        <li><a href="/front/product/productNews1">제품소식</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <ul>
                                <li class="accordion">RECRUIT</li>
                                <li class="panel">
                                    <ul>
                                        <li><a href="/front/recruit/hrCare">인재케어</a></li>
                                        <li><a href="/front/recruit/workCare">업무케어</a></li>
                                        <li><a href="/front/recruit/recruitNotice">채용공지</a></li>
                                        <li><a href="/front/recruit/recruitFaq">채용FAQ</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <ul>
                                <li class="accordion">IR/투자정보</li>
                                <li class="panel">
                                    <ul>
                                        <li><a href="/front/ir/irOverview">OVERVIEW</a></li>
                                        <li><a href="/front/ir/financialInfo">재무정보</a></li>
                                        <li><a href="/front/ir/irInfo">IR정보</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <ul>
                                <li class="accordion">SAMIL STORY</li>
                                <li class="panel">
                                    <ul>
                                        <li><a href="/front/story/pressRelease">보도자료</a></li>
                                        <li><a href="/front/story/brodAd">방송광고</a></li>
                                        <li><a href="/front/story/printAd">인쇄광고</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <div class="box-list">
                    <ul class="three-dept">
                        <li>
                            <a href="/front/etc/faq">FAQ</a>
                        </li>
                        <li>
                            <a href="/front/etc/contactUs">CONTACT US</a>
                        </li>
                        <li>
                            <a href="/front/etc/legalNoti">법적고지</a>
                        </li>
                    </ul>
                </div>
                <div class="box-list">
                    <ul class="two-dept">
                        <li>
                            <a href="/front/etc/privacyInfo">개인정보처리방침</a>
                        </li>
                        <li>
                            <a href="/front/etc/emailCollect">이메일무단수집거부</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${lan=='EN'}">
    <div class="header">
        <div class="header-menu">
            <div class="logo">
                <a href="/main/EN">
                    <img src="/assets/user/KR/img/logo.png" alt="로고">
                </a>
            </div>
            <div class="suffix">
                <ul>
                    <li class="langs accordion">
                        <a href="javascript:void(0)" onclick="mb_selectLang('click')" name="mbLang">ENG</a>
                    </li>
                    <li class=" langs panel">
                        <a href="javascript:void(0)" onclick="mb_selectLang('click')" name="mbLang">KOR</a>
                    </li>
                </ul>
                <div class="hamburger hamburger--slider">
                    <div class="hamburger-box">
                        <div class="hamburger-inner"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="menu">
            <div class="gnb">
                <div class="border-type-accordion has-arrow-accordion">
                    <ul>
                        <li>
                            <ul>
                                <li class="accordion">About US</li>
                                <li class="panel">
                                    <ul>
                                        <li><a href="/front/aboutUs/vision/EN?tab=1">VISION</a></li>
                                        <li><a href="/front/aboutUs/samilHistory/EN?tab=2">SAMIL HISTORY</a></li>
                                        <li><a href="/front/aboutUs/design/EN?tab=3">DESIGN SAMIL</a></li>
                                        <li><a href="/front/aboutUs/ethical/EN?tab=4">ETHICAL MANAGEMENT</a></li>
                                        <li><a href="/front/aboutUs/info/EN?tab=5">DIRECTIONS</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <ul>
                                <li class="accordion">CSR</li>
                                <li class="panel">
                                    <ul>
                                        <li class="menu-tit"><a href="/front/csr/EN" class="fw-bold">CSR</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <ul>
                                <li class="accordion">OUR BUSINESS</li>
                                <li class="panel">
                                    <ul>
                                        <li><a href="/front/ourBusiness/cdmo/EN">CDMO</a></li>
                                        <li><a href="/front/ourBusiness/rd/EN">R&D</a></li>
                                        <li><a href="/front/ourBusiness/global/EN">GLOBAL PARTNERS</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <ul>
                                <li class="accordion">PRODUCTS</li>
                                <li class="panel">
                                    <ul>
                                        <li><a href="/front/product/searchProduct/EN">PRODUCT SEARCH</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <ul>
                                <li class="accordion">RECRUIT</li>
                                <li class="panel">
                                    <ul>
                                        <li><a href="/front/recruit/recruit/EN">RECRUIT</a></li>
                                        <li><a href="/front/recruit/hrCare/EN">HUMAN RESOUCE CARE</a></li>
                                        <li><a href="/front/recruit/workCare/EN">WORK CARE</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <ul>
                                <li class="accordion">IR</li>
                                <li class="panel">
                                    <ul>
                                        <li><a href="/front/ir/irOverview/EN">OVERVIEW</a></li>
                                        <li><a href="/front/ir/financialInfo/EN">FINANCIAL INFORMATION</a></li>
                                        <li><a href="/front/ir/irInfo/EN">IR INFORMATION</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <div class="box-list">
                    <ul>
                        <li>
                            <a href="/front/etc/contactUs/EN">CONTACT US</a>
                        </li>
                    </ul>
                </div>
                <div class="box-list">
                    <ul>
                        <li>
                            <a href="/front/etc/legalNoti/EN">LEGAL NOTICE</a>
                        </li>
                    </ul>
                </div>
                <div class="box-list">
                    <ul >
                        <li>
                            <a href="/front/etc/privacyInfo/EN">PRIVACY POLICY</a>
                        </li>
                    </ul>
                </div>

                <div class="box-list">
                    <ul >
                        <li>
                            <a href="/front/etc/emailCollect/EN">REJECTION OFUNAUTHORIZED COLLECTION OF EMAIL ADDRESSES </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</c:if>


