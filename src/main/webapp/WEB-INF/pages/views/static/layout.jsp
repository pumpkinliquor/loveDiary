<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<script src="/assets/js/templates.js?v=1"></script>
</head>

<body class="sub">
<div class="blind"><a href="#content" class="hidden">본문 바로가기</a></div>

<!-- wrap -->
<div id="wrap">
    <!-- header -->
    <header style="top: 0px;">
        <section class="utill clear02" >
            <!-- left -->
            <div class="leftArea" style="width: 284px;">
                <div class="logo">
                    <a href="#">
                    <img src="/assets/images/logo-white-text.png" alt="IBMS">
                    </a>
                </div>
                <div class="currentP" style="width: 117px;">
                    <a href="#">
                    <div class="depthOne">
                        <p title="남산빌딩afdsafsdadf">남산빌딩afdsafsdadf
                        </p>
                        <img src="/assets/images/common/h_select_icon.gif" alt="">
                    </div>
                    </a>
                    <div class="currentP_sub" style="display: none; min-width: 117px;">
                        <ul>
                            <li>
                                <a href="#">
                                <p title="T 타워afdsafdsafsdafsdaf">T 타워afdsafdsafsdafsdaf
                                </p>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                <p title="빌딩2">빌딩2
                                </p>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                <p title="빌딩3">빌딩3
                                </p>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- //left -->
            <!-- right -->
            <div class="rightArea clear02">
                <div class="clear02">
                    <div class="time">
                        <p id="util_date_dev">2013.10.27 (목)
                        <span>
                        <i class="fa fa-clock-o"></i>23:15:30
                        </span>
                        </p>
                    </div>
                    <div class="user navbar navbar-expand">


<ul class="navbar-nav flex-row ml-md-auto d-none d-md-flex">
    <li class="nav-item ">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
        <i class="fa fa-envelope fa-fw"></i> <i class="fa fa-caret-down"></i>
        </a>
        <ul class="dropdown-menu dropdown-messages">
            <li>
                <a href="#">
                <div>
                    <strong>John Smith</strong>
                    <span class="pull-right text-muted">
                    <em>Yesterday</em>
                    </span>
                </div>
                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="#">
                <div>
                    <strong>John Smith</strong>
                    <span class="pull-right text-muted">
                    <em>Yesterday</em>
                    </span>
                </div>
                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                </a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="#">
                <div>
                    <strong>John Smith</strong>
                    <span class="pull-right text-muted">
                    <em>Yesterday</em>
                    </span>
                </div>
                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                </a>
            </li>
            <li class="divider"></li>
            <li>
                <a class="text-center" href="#">
                <strong>Read All Messages</strong>
                <i class="fa fa-angle-right"></i>
                </a>
            </li>
        </ul>
        <!-- /.dropdown-messages -->
    </li>
    <li class="nav-item ">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
        </a>
        <ul class="dropdown-menu dropdown-user">
            <li>
                <a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
            </li>
        </ul>
        <!-- /.dropdown-user -->
    </li>

</ul>


                        <!--
                        <%--<img src="/images/common/user_icon.png" alt="회원 아이콘">--%>

                        <p class="usNm">
                        <i class="fa fa-user-circle"></i>
                        <a href="#" class="uName">Cloudbems
                        </a>&nbsp;님
                        </p>
                        <div class="userSub" style="display: none;">
                            <div class="neInfo">
                                <p class="nm">홍길동1fsdafsdafsdafsdafsdaasdfsdf
                                </p>
                                <p class="em">abcd@skteelcom.com
                                </p>
                            </div>
                            <div class="ractionBox clear02">
                                <p class="left">
                                <a href="#" class="pwChange">비밀번호 변경
                                </a>
                                <a href="#" class="emChange">이메일 변경
                                </a>
                                </p>
                                <div class="log">
                                    <a href="#" title="로그아웃">
                                    <i class="fa fa-sign-out"></i>
                                    </a>
                                    <a href="#" title="로그인">
                                    <i class="fa fa-sign-in"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                        -->
                        <!-- //user sub Area -->
                    </div>
                </div>
            </div>
            <!-- //right -->
        </section>
        <div class="liner">
            &nbsp;
        </div>


        <div class="wsmainfull clearfix">
            <div class="wsmainwp clearfix">



                <!--Main Menu HTML Code-->
                <nav class="wsmenu clearfix">
                    <ul class="wsmenu-list">

                        <li aria-haspopup="true"><a href="#" class="active menuhomeicon " id="sidebarCollapse">
<svg class="svg-inline--fa fa-align-left fa-w-8" aria-hidden="true" data-prefix="fas" data-icon="align-left" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" data-fa-i2svg=""><path fill="currentColor" d="M288 44v40c0 8.837-7.163 16-16 16H16c-8.837 0-16-7.163-16-16V44c0-8.837 7.163-16 16-16h256c8.837 0 16 7.163 16 16zM0 172v40c0 8.837 7.163 16 16 16h416c8.837 0 16-7.163 16-16v-40c0-8.837-7.163-16-16-16H16c-8.837 0-16 7.163-16 16zm16 312h416c8.837 0 16-7.163 16-16v-40c0-8.837-7.163-16-16-16H16c-8.837 0-16 7.163-16 16v40c0 8.837 7.163 16 16 16zm256-200H16c-8.837 0-16 7.163-16 16v40c0 8.837 7.163 16 16 16h256c8.837 0 16-7.163 16-16v-40c0-8.837-7.163-16-16-16z"></path></svg><!-- <i class="fas fa-align-left"></i> -->

                            <span class="hometext"> Home</span></a></li>
                        <li aria-haspopup="true">
                            <a href="#"><i class="fa fa-align-justify"></i> Dropdowns <span class="wsarrow"></span></a>
                            <ul class="sub-menu">
                                <li aria-haspopup="true"><a href="#"><i class="fa fa-angle-right"></i>Website Design </a></li>
                                <li aria-haspopup="true"><a href="#"><i class="fa fa-angle-right"></i>Ecommerce Solutions</a></li>
                                <li aria-haspopup="true"><a href="#"><i class="fa fa-angle-right"></i>Application Development</a></li>
                                <li aria-haspopup="true"><a href="#"><i class="fa fa-angle-right"></i>Website Development</a></li>
                                <li aria-haspopup="true">
                                    <a href="#"><i class="fa fa-angle-right"></i>Open Source Development</a>
                                    <ul class="sub-menu">
                                        <li aria-haspopup="true"><a href="#"><i class="fa fa-angle-right"></i>Submenu item 1</a></li>
                                        <li aria-haspopup="true"><a href="#"><i class="fa fa-angle-right"></i>Submenu item 2</a></li>
                                        <li aria-haspopup="true"><a href="#"><i class="fa fa-angle-right"></i>Submenu item 3</a></li>
                                        <li aria-haspopup="true">
                                            <a href="#"><i class="fa fa-angle-right"></i>Submenu item 4</a>
                                            <ul class="sub-menu">
                                                <li aria-haspopup="true"><a href="#"><i class="fa fa-angle-right"></i>Submenu item 1 Sub</a></li>
                                                <li aria-haspopup="true"><a href="#"><i class="fa fa-angle-right"></i>Submenu item 2 Sub</a></li>
                                                <li aria-haspopup="true"><a href="#"><i class="fa fa-angle-right"></i>Submenu item 3 Sub</a></li>
                                                <li aria-haspopup="true"><a href="#"><i class="fa fa-angle-right"></i>Submenu item 4 Sub</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </li>

                        <li aria-haspopup="true">
                            <a href="#"><i class="fa fa-list-alt"></i>Half menu <span class="wsarrow"></span></a>
                            <div class="wsmegamenu clearfix halfmenu">
                                <div class="container-fluid">
                                    <div class="row">

                                        <ul class="col-lg-6 col-md-12 col-xs-12 link-list">
                                            <li class="title">Product Header</li>
                                            <li><a href="#"><i class="fa fa-arrow-circle-right"></i>Submenu link style</a></li>
                                            <li><a href="#"><i class="fa fa-arrow-circle-right"></i>Submenu link style</a></li>
                                            <li><a href="#"><i class="fa fa-arrow-circle-right"></i>Submenu link style</a></li>
                                            <li><a href="#"><i class="fa fa-arrow-circle-right"></i>Submenu link style</a></li>
                                            <li><a href="#"><i class="fa fa-arrow-circle-right"></i>Submenu link style</a></li>
                                        </ul>
                                        <ul class="col-lg-6 col-md-12 col-xs-12 link-list">
                                            <li class="title">Product Header</li>
                                            <li><a href="#"><i class="fa fa-arrow-circle-right"></i>Submenu link style</a></li>
                                            <li><a href="#"><i class="fa fa-arrow-circle-right"></i>Submenu link style</a></li>
                                            <li><a href="#"><i class="fa fa-arrow-circle-right"></i>Submenu link style</a></li>
                                            <li><a href="#"><i class="fa fa-arrow-circle-right"></i>Submenu link style</a></li>
                                            <li><a href="#"><i class="fa fa-arrow-circle-right"></i>Submenu link style</a></li>
                                        </ul>
                                    </div>

                                </div>
                            </div>
                        </li>

                        <li aria-haspopup="true">
                            <a href="#"><i class="fa fa-th-large"></i>Product images <span class="wsarrow"></span></a>
                            <div class="wsmegamenu clearfix ">
                                <div class="container">
                                    <div class="row">


                                        <div class="col-lg-4 col-md-12 col-xs-12">
                                            <h3 class="title">Product Features</h3>
                                            <div class="fluid-width-video-wrapper"><img src="images/image02.jpg" alt="" /> </div>
                                            <p class="wsmwnutxt">Lorem Ipsum is dummy text of the printing specimen book. It has survived not only five centuries, but also typesetting in the the contantly with desktoncluding.</p>
                                        </div>
                                        <div class="col-lg-4 col-md-12 col-xs-12">
                                            <h3 class="title">Blog Article </h3>
                                            <div class="fluid-width-video-wrapper"><img src="images/image03.jpg" alt="" /> </div>
                                            <p class="wsmwnutxt">Lorem Ipsum is dummy text of the printing specimen book. It has survived not only five centuries, but also typesetting in the the contantly with desktoncluding.</p>
                                        </div>
                                        <div class="col-lg-4 col-md-12 col-xs-12">
                                            <h3 class="title">Highlight Your Services</h3>
                                            <div class="fluid-width-video-wrapper"><a href="#"><img src="images/image04.jpg" alt=""/></a> </div>
                                            <p class="wsmwnutxt">Lorem Ipsum is dummy text of the printing specimen book. It has survived not only five centuries, but also typesetting in the the contantly with desktoncluding.</p>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </li>

                        <li aria-haspopup="true">
                            <a href="#"><i class="fa fa-list-alt"></i> Mega menu <span class="wsarrow"></span></a>
                            <div class="wsmegamenu clearfix">
                                <div class="container">
                                    <div class="row">

                                        <div class="col-lg-5 col-md-12 col-xs-12 link-list">
                                            <div class="title">More Text Header</div>
                                            <p class="wsmwnutxt">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and
                                            scrambled it to make a type
                                            specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum
                                            passages,
                                            and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                                            <p class="wsmwnutxt">Lorem Ipsum is simplntly with desktop publishing software like Aldus PageMaker but also the leap into electronic typesetting including versions of Lorem Ipsum.</p>
                                        </div>


                                        <ul class="col-lg-3 col-md-12 col-xs-12 link-list">
                                            <li class="title">Product Header</li>
                                            <li><a href="#"><i class="fa fa-arrow-circle-right"></i>Open Source link goes here</a></li>
                                            <li><a href="#"><i class="fa fa-arrow-circle-right"></i>Long link area with no scroll</a></li>
                                            <li><a href="#"><i class="fa fa-arrow-circle-right"></i>Dummy more link style</a></li>
                                            <li><a href="#"><i class="fa fa-arrow-circle-right"></i>Smart Shop link here</a></li>
                                            <li><a href="#"><i class="fa fa-arrow-circle-right"></i>CMS Submenu link style</a></li>
                                            <li><a href="#"><i class="fa fa-arrow-circle-right"></i>New link area with no scroll</a></li>
                                            <li><a href="#"><i class="fa fa-arrow-circle-right"></i>HTML5 link style</a></li>
                                            <li><a href="#"><i class="fa fa-arrow-circle-right"></i>Wordpress link style</a></li>
                                            <li><a href="#"><i class="fa fa-arrow-circle-right"></i>Submenu link style</a></li>

                                        </ul>



                                        <div class="col-lg-4 col-md-12 col-xs-12">
                                            <h3 class="title">New Arrival Slider </h3>
                                            <div id="demo" class="carousel slide" data-ride="carousel">
                                                <!-- The slideshow -->
                                                <div class="carousel-inner">
                                                    <div class="carousel-item active">
                                                        <img src="images/image01.jpg" alt="" />
                                                        <div class="carousel-caption">
                                                            <h3>Slider Caption Option 01</h3>
                                                        </div>
                                                    </div>
                                                    <div class="carousel-item">
                                                        <img src="images/image02.jpg" alt="" />
                                                        <div class="carousel-caption">
                                                            <h3>Slider Caption Option 02</h3>
                                                        </div>
                                                    </div>
                                                    <div class="carousel-item">
                                                        <img src="images/image03.jpg" alt="" />
                                                        <div class="carousel-caption">
                                                            <h3>Slider Caption Option 03</h3>
                                                        </div>
                                                    </div>
                                                    <div class="carousel-item">
                                                        <img src="images/image04.jpg" alt="" />
                                                        <div class="carousel-caption">
                                                            <h3>Slider Caption Option 04</h3>
                                                        </div>
                                                    </div>
                                                    <div class="carousel-item">
                                                        <img src="images/image05.jpg" alt="" />
                                                        <div class="carousel-caption">
                                                            <h3>Slider Caption Option 05</h3>
                                                        </div>
                                                    </div>
                                                    <div class="carousel-item">
                                                        <img src="images/image06.jpg" alt="" />
                                                        <div class="carousel-caption">
                                                            <h3>Slider Caption Option 06</h3>
                                                        </div>
                                                    </div>
                                                </div>

                                                <!-- Left and right controls -->
                                                <a class="carousel-control-prev" href="#demo" data-slide="prev"> <span class="carousel-control-prev-icon"></span> </a>
                                                <a class="carousel-control-next" href="#demo" data-slide="next"> <span class="carousel-control-next-icon"></span> </a>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </li>
                        <li aria-haspopup="true">
                            <a href="#"><i class="fa fa-paragraph"></i>Typography <span class="wsarrow"></span></a>
                            <div class="wsmegamenu clearfix">
                                <div class="typography-text clearfix">
                                    <div class="container-fluid">
                                        <div class="row">

                                            <div class="col-lg-6 col-sm-12">
                                                <h3 class="title">This is another title</h3>
                                                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make
                                                a type
                                                specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s Ipsum more recently with desktop publishing software like
                                                Aldus
                                                PageMaker including versions of Lorem Ipsum.</p>
                                            </div>

                                            <div class="col-lg-3 col-sm-12">
                                                <h3 class="title">This is another title</h3>
                                                <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney
                                                College in
                                                Virginia.
                                                </p>
                                            </div>

                                            <div class="col-lg-3 col-sm-12">
                                                <h3 class="title">This is another title</h3>
                                                <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney
                                                College in
                                                Virginia.
                                                </p>
                                            </div>

                                        </div>

                                        <div class="row">
                                            <div class="cl"></div>

                                            <div class="col-lg-3 col-sm-12">
                                                <h3 class="title">Other Services</h3>
                                                <ul>
                                                    <li><a href="#"><i class="fa fa-wordpress"></i>Wordpress Development</a></li>
                                                    <li><a href="#"><i class="fa fa-drupal"></i>Drupal Development</a></li>
                                                    <li><a href="#"><i class="fa fa-shopping-cart"></i>Shoping Cart Development</a></li>
                                                </ul>
                                            </div>

                                            <div class="col-lg-3 col-sm-12">
                                                <h3 class="title">More Services</h3>
                                                <ul>
                                                    <li><a href="#"><i class="fa fa-android"></i> Android App Development</a></li>
                                                    <li><a href="#"><i class="fa fa-apple"></i>iPhone App Development</a></li>
                                                    <li><a href="#"><i class="fa fa-windows"></i>Windows App Development</a></li>
                                                </ul>
                                            </div>

                                            <div class="col-lg-3 col-sm-12">
                                                <h3 class="title">Other Products</h3>
                                                <ul>
                                                    <li><a href="#"><i class="fa fa-wordpress"></i>Wordpress Development</a></li>
                                                    <li><a href="#"><i class="fa fa-drupal"></i>Drupal Development</a></li>
                                                    <li><a href="#"><i class="fa fa-joomla"></i>Joomla Development</a></li>
                                                </ul>
                                            </div>

                                            <div class="col-lg-3 col-sm-12">
                                                <h3 class="title">More Services</h3>
                                                <ul>
                                                    <li><a href="#"><i class="fa fa-android"></i> Android App Development</a></li>
                                                    <li><a href="#"><i class="fa fa-mobile"></i>HTML5 App Development</a></li>
                                                    <li><a href="#"><i class="fa fa-paypal"></i>Paypal Store Integration</a></li>
                                                </ul>
                                            </div>
                                        </div>

                                    </div>

                                </div>
                            </div>
                        </li>
                        <li aria-haspopup="true">
                            <a href="#"><i class="fa fa-paper-plane"></i> Contact Us <span class="wsarrow"></span></a>
                            <div class="wsmegamenu halfdiv">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <h3 class="title">Contact Form</h3>
                                            <form name="contact_name" class="menu_form">
                                            <input type="text" placeholder="Name">
                                            <input type="text" placeholder="Email">
                                            <textarea placeholder="Your message..."></textarea>
                                            <input type="button" value="Reset">
                                            <input type="submit" value="Send">

                                            </form>
                                            <div class="cl"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>

                    </ul>
                </nav>
                <!--Menu HTML Code-->
            </div>
        </div>

    </header>
    <!-- //header -->

    <!-- container -->
    <div id="container">
        <div class="wrapper">
            <!-- Sidebar -->
            <nav id="sidebar" class="lnb">
                <div class="sidebar-header">
                    <h3>Bootstrap Sidebar</h3>
                </div>

                <ul class="list-unstyled components ">
                    <p>Dummy Heading</p>
                    <li class="active depth03 on">
                        <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Home</a>
                        <ul class="collapse list-unstyled" id="homeSubmenu">
                            <li>
                                <a href="#">Home 1</a>
                            </li>
                            <li>
                                <a href="#">Home 2</a>
                            </li>
                            <li>
                                <a href="#">Home 3</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">About</a>
                    </li>
                    <li>
                        <a href="#pageSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Pages</a>
                        <ul class="collapse list-unstyled" id="pageSubmenu">
                            <li>
                                <a href="#">Page 1</a>
                            </li>
                            <li>
                                <a href="#">Page 2</a>
                            </li>
                            <li>
                                <a href="#">Page 3</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">Portfolio</a>
                    </li>
                    <li>
                        <a href="#">Contact</a>
                    </li>
                </ul>
            </nav>
            <!-- Page Content -->
            <div id="content">

                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <div class="container-fluid">
aWEF<>AWE:fawefmalw;e<br/>aWEF<>AWE:fawefmalw;e<br/>
                        aWEF<>AWE:fawefmalw;e<br/>
                    </div>
                </nav>
            </div>
        </div>
    </div>
    <!-- //container -->
</div>
<!-- //wrap -->
</body>
</html>
