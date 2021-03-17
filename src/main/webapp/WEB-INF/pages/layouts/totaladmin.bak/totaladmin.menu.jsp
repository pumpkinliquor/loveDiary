<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

            <!-- Sidebar scroll-->
            <div class="scroll-sidebar">
                <nav class="sidebar-nav">
                    <ul id="sidebarnav">

                        <li> <a class=" waves-effect waves-dark" href="/totaladmin/dashboard" aria-expanded="false"><i class="fa fa-globe"></i><span class="hide-menu"> Dashboard </span></a></li>
                        <li> <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="fa fa-cubes"></i><span class="hide-menu"> 사업자관리 <span class="label label-rouded label-themecolor pull-right">4</span></span></a>
                            <ul aria-expanded="false" class="collapse">
                                <li><a href="/totaladmin/business/businessInfoList">사업자관리 </a></li>
                                <%--<li><a href="/totaladmin/business/businessClientList">고객사관리 </a></li>--%>
                            </ul>
                        </li>
                        <%--<li> <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="fa fa-bullseye"></i><span class="hide-menu">자산관리</span></a>
                            <ul aria-expanded="false" class="collapse">
                                <li><a href="/totaladmin/assets/buildingList">건물목록</a></li>
                            </ul>
                        </li>
                        <li> <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="fa fa-bullseye"></i><span class="hide-menu">임대관리</span></a>
                            <ul aria-expanded="false" class="collapse">
                                <li><a href="/totaladmin/rents/rentList">임대목록</a></li>
                            </ul>
                        </li>--%>
                        <li> <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="fa fa-file"></i><span class="hide-menu">사용자메뉴관리</span></a>
                            <ul aria-expanded="false" class="collapse">
                                <li><a href="/totaladmin/menus/menuList">사용자메뉴관리</a></li>
                                <li><a href="/totaladmin/menus/menuGrant">메뉴권한관리</a></li>
								<!--<li><a href="#form-basic.html">메뉴관리</a></li>-->
                            </ul>
                        </li>
                        <li> <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="fa fa-file"></i><span class="hide-menu">어드민메뉴관리</span></a>
                            <ul aria-expanded="false" class="collapse">
                                <li><a href="/totaladmin/menus/admmenuList">어드민메뉴관리</a></li>
                                <li><a href="/totaladmin/menus/admmenuGrant">메뉴권한관리</a></li>
								<!--<li><a href="#form-basic.html">메뉴관리</a></li>-->
                            </ul>
                        </li>
                        <%--<li> <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="fa fa-table"></i><span class="hide-menu">게시물관리</span></a>
                            <ul aria-expanded="false" class="collapse">
                                <li><a href="/totaladmin/boards/boardList">전체공지사항</a></li>
                                <li><a href="#">게시판관리</a></li>
                                <li><a href="#">게시판글관리</a></li>
                            </ul>
                        </li>--%>
                        <li> <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="fa fa-user"></i><span class="hide-menu">회원관리</span></a>
                            <ul aria-expanded="false" class="collapse">
                                <li><a href="/totaladmin/users/userList">회원관리</a></li>
                                <li><a href="/totaladmin/users/userGroup">그룹관리</a></li>
                                <li><a href="/totaladmin/users/userPermission">회원그룹관리</a></li>
                            </ul>
                        </li>
                        <li> <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="fa fa-line-chart"></i><span class="hide-menu">통계관리</span></a>
                            <ul aria-expanded="false" class="collapse">
                                <li><a href="#">접속자통계</a></li>
                                <li><a href="#">페이지별통계</a></li>
                                <li><a href="#">사이트통계</a></li>
                            </ul>
                        </li>
                        <%--<li> <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="fa fa-info-circle"></i><span class="hide-menu">VOC관리</span></a>
                            <ul aria-expanded="false" class="collapse">
                                <li><a href="#">요청사항</a></li>
                                <li><a href="#">오류수집</a></li>
                                <li><a href="#">기능보안</a></li>

                                <li><a href="#">완료리스트</a></li>
                            </ul>
                        </li>
                        <li> <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="fa fa-send"></i><span class="hide-menu">SMS/메일발송</span></a>
                            <ul aria-expanded="false" class="collapse">
                                <li><a href="#">SMS설정</a></li>
                                <li><a href="#">SMS발송관리</a></li>
                                <li><a href="#">메일설정</a></li>
                                <li><a href="#">메일발송관리</a></li>
                            </ul>
                        </li>--%>
                        <li> <a class="has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="fa fa fa-sliders"></i><span class="hide-menu">환경설정</span></a>
                            <ul aria-expanded="false" class="collapse">
                                <li><a href="#">기본환경설정</a></li>
                                <li><a href="/totaladmin/configs/codeList">공통코드관리</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
            <!-- End Sidebar scroll-->