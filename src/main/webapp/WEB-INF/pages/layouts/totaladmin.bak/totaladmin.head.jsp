<%@ page import="com.plushih.common.constant.LoginSession" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
//   String sesstionId = "";
//   if(request.getSession()!=null){
//    sesstionId = request.getSession().getAttribute(LoginSession.ID).toString();
//   }

%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />



        <header class="topbar">
            <nav class="navbar top-navbar navbar-expand-md navbar-light">

                <!-- Logo -->

                <div class="navbar-header">
                    <a class="navbar-brand" href="/admin/dashboard">
                        <!-- Logo icon --><b>
                            <!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
                            <!-- Light Logo icon -->
                            <img src="/assets/images/logo-light-icon.png" alt="homepage" class="light-logo" />
                        </b>
                        <!--End Logo icon -->
                        <!-- Logo text --><span class="">
                         <!-- Light Logo text -->
                         관리자</span> </a>
                </div>

                <!-- End Logo -->

                <div class="navbar-collapse">

                    <!-- toggle and nav items -->
                    <ul class="navbar-nav mr-auto">
                        <!-- This is  -->
                        <li class="nav-item"> <a class="nav-link nav-toggler hidden-md-up waves-effect waves-dark" href="javascript:void(0)"><i class="fa fa-bars"></i></a> </li>
                        <li class="nav-item"> <a class="nav-link sidebartoggler hidden-sm-down waves-effect waves-dark" href="javascript:void(0)"><i class="fa fa-bars"></i></a> </li>
                        <li class="nav-item "><a class="nav-link red-theme" href="javascript:;">[ 토탈관리자  ]</a></li>
                        <li class="nav-item ml-2"><a class="nav-link red-theme" href="/plusadmin/dashboard">[ 플러스관리자  ]</a></li>
                        <li class="nav-item ml-2"><a class="nav-link red-theme" href="/main">[ FRONT  ]</a></li>
                    </ul>

                    <!-- User profile and search -->

                    <ul class="navbar-nav my-lg-0">

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle waves-effect waves-dark" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-user"></i>
                                관리자(${loginSessionName}) </a>

                            <div class="dropdown-menu dropdown-menu-right animated flipInY">
                                <ul class="dropdown-user">
                                    <li>
                                        <div class="dw-user-box">
                                            <div class="u-img"><div class="btn btn-danger btn-circle"><i class="fa fa-user"></i></div></div>
                                            <div class="u-text">
                                                <h4>${loginSessionName}</h4>
                                                <p class="text-muted"><?=date('Y-m-d H:i')?></p>
                                            </div>
                                        </div>
                                    </li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="#"><i class="ti-user"></i> My Profile</a></li>
                                    <li><a href="#"><i class="ti-wallet"></i> My Balance</a></li>
                                    <li><a href="#"><i class="ti-email"></i> Inbox</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="#"><i class="ti-settings"></i> Account Setting</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="#"><i class="fa fa-power-off"></i> Logout</a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>

        <!-- End Topbar header -->
