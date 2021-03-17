<%@ page import="com.plushih.common.constant.LoginSession" %>
<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html lang="en">
<%
    request.setCharacterEncoding("UTF-8");
    response.setHeader("Cache-Control", "no-cache, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    response.setHeader("returnURI", request.getRequestURI());
    response.setContentType("text/html; charset=UTF-8");

    String sesstionId = "";
    String sesstionNm = "";
    String sesstionGroup = "";
    if (request.getSession() != null) {
        sesstionId = LoginSession.getLoginId(request.getSession());
        sesstionNm = LoginSession.getLoginName(request.getSession());
        sesstionGroup = LoginSession.getLoginGrant(request.getSession());
        if (sesstionGroup == null) {
            sesstionGroup = "";
        }
//      sesstionId = request.getSession().getAttribute(LoginSession.ID).toString();
//      sesstionNm = request.getSession().getAttribute(LoginSession.NAME).toString();
//
    }
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="sesstionId" value="<%=sesstionId%>" />
<head>

  <!-- Google Font: Source Sans Pro -->
  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="/assets/admin-lte/plugins/fontawesome-free/css/all.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="/admassets/css/admin.css">
  <link rel="stylesheet" type="text/css" href="/assets/css/jquery.toast.css">
  <link rel="stylesheet" type="text/css" href="${contextPath}/assets/css/select/select2.min.css"/>

    <style>
        .dataTables_wrapper {
            width: 100% !important;
        }


        #gridElement_filter, #gridElement_filter input, #gridElement_filter button {

            font-size: 0.875rem !important;
            line-height: 1.5;
            border-radius: 0.2rem;
        }

        .table-footer_fix {
            position: relative
        }

        .table-footer_fix .dataTables_paginate {
            position: absolute;
            top: 25px;
            left: 45%;
        }

        /* On screens that are 992px or less, set the background color to blue */
        @media screen and (max-width: 992px) {
            body {
                left: 38%;
            }
        }

        /* On screens that are 600px or less, set the background color to olive */
        @media screen and (max-width: 600px) {
            body {
                left: 32%;
            }
        }

        label.error {
            margin-top: 5px;
            color: #FF0000;
        }
        .plyr {
            width: 250px;
            height: 140px;

        }
        .hidden {display:none}
        .paging{display:none}
        .paging_full_numbers{margin-top:20px;}
        .paging_full_numbers span,.paging_full_numbers a{padding:0.5rem 0.75rem;}
        .paging_full_numbers span.on,.paging_full_numbers a.on{border-bottom:3px solid #752cdd}
    </style>


  <!-- jQuery -->
  <script src="/assets/admin-lte/plugins/jquery/jquery.min.js"></script>
  <!-- jQuery UI 1.11.4 -->
  <script src="/assets/admin-lte/plugins/jquery-ui/jquery-ui.min.js"></script>
  <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->

    <script type="text/javascript" charset="utf-8" src="/assets/js/jquery/jquery.cookie.js"></script>
  <!-- Bootstrap 4 -->
<%--  <script src="/assets/admin-lte/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>--%>
  <!-- ChartJS -->
  <script src="/assets/admin-lte/plugins/chart.js/Chart.min.js"></script>
  <!-- Sparkline -->
<%--  <script src="/assets/admin-lte/sparklines/sparkline.js"></script>--%>
  <!-- JQVMap -->
<%--  <script src="/assets/admin-lte/plugins/jqvmap/jquery.vmap.min.js"></script>--%>
<%--  <script src="/assets/admin-lte/plugins/jqvmap/maps/jquery.vmap.usa.js"></script>--%>
  <!-- jQuery Knob Chart -->
<%--  <script src="/assets/admin-lte/plugins/jquery-knob/jquery.knob.min.js"></script>--%>
  <!-- daterangepicker -->
  <script src="/assets/admin-lte/plugins/moment/moment.min.js"></script>
  <script src="/assets/admin-lte/plugins/daterangepicker/daterangepicker.js"></script>

  <script type="text/javascript" src="/assets/neditor/js/service/HuskyEZCreator.js?v=1" charset="utf-8"></script>
  <!-- overlayScrollbars -->
  <script src="/assets/admin-lte/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
  <!-- AdminLTE App -->
  <script src="/assets/admin-lte/dist/js/adminlte.js"></script>
  <!-- AdminLTE for demo purposes -->
<%--  <script src="/assets/admin-lte/dist/js/demo.js"></script>--%>

  <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<%--  <script src="/assets/admin-lte/dist/js/pages/dashboard.js"></script>--%>
  <script type="text/javascript" src="${contextPath}/assets/js/select/select2.full.js"></script>
    <script type="text/javascript" charset="utf-8" src="/assets/js/jquery/jquery.toast.js"></script>
    <script type="text/javascript" charset="utf-8" src="/assets/js/jquery/jquery.form.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="/assets/js/jquery/jquery.tmpl.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="/assets/js/jquery/jquery.validate.js"></script>
    <script type="text/javascript" charset="utf-8" src="/assets/js/jquery/jquery.validate.add-method.js"></script>
<%--    <script type="text/javascript" charset="utf-8" src="/assets/js/jquery/jquery-ui-tab.min.js"></script>--%>
    <script type="text/javascript" charset="utf-8" src="/assets/js/datatables.min.js"></script>
    <script src="/assets/sweetalert2/dist/sweetalert2.all.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="/assets/js/global.js?v=1"></script>
<script type="text/javascript" charset="utf-8" src="/assets/js/plus.js?v=<%=Math.random()+""%>"></script>

<script type="text/javascript" charset="utf-8" src="/admassets/js/script.js?v=899"></script>
<!--[if lt IE 9]>
<script src="//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
<![endif]-->
    <script>
    $.widget.bridge('uibutton', $.ui.button);

    //전체선택 checkbox
    $(function() {
        //전체선택 체크박스 클릭
        $("#jb-checkbox").on('click',function(e) {

            //만약 전체 선택 체크박스가 체크된상태일경우
            if ($("#jb-checkbox").prop("checked")) {
                //해당화면에 전체 checkbox들을 체크해준다
                $("input[type=checkbox]").prop("checked", true);
                // 전체선택 체크박스가 해제된 경우
            } else {
                //해당화면에 모든 checkbox들의 체크를해제시킨다.
                $("input[type=checkbox]").prop("checked", false);
            }
        });


    });
  </script>
</head>
<body>
<div class="admin_wrapper">

    <tiles:insertAttribute name="menu" />
    <!-- menu -->
    <!-- navbar -->
    <div class="content-wrap">

        <div class="content-header">
            <ul class="fR">
                <li><%=sesstionNm%>
                </li>
                <li><a href="/">FRONT</a></li>
                <c:if test="${sesstionId=='hsk3807'||sesstionId=='friver'}">
                    <li><a href="/totaladmin/main">TOTALADMIN</a></li>
                </c:if>
                <li><a href="#" class="btn btn-secondary logout">로그아웃</a></li>
            </ul>
        </div>
        <div class="content">
            <tiles:insertAttribute name="contents" />
        </div>
    </div>
    <!-- navbar -->
    <!-- //content-wrapper -->
<%--    <tiles:insertAttribute name="footer" />--%>
</div>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<!-- 모달 영역 -->
  <div class="modal fade" id="alertModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header border-none">
          <a href="#" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true"><i class="fas fa-times"></i></span>
          </a>
        </div>
        <div class="modal-body text-center msg" >수정 되었습니다.</div>
        <div class="modal-footer border-none">
          <a href="#" class="btn btn-popup-offer" data-dismiss="modal">확인</a>
        </div>
      </div>
    </div>
  </div>
  <!-- //wrapper -->
</body>
</html>
