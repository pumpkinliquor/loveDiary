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
%>
<head>

  <!-- Google Font: Source Sans Pro -->
  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="/assets/admin-lte/plugins/fontawesome-free/css/all.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Tempusdominus Bootstrap 4 -->
  <link rel="stylesheet" href="/assets/admin-lte/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="/assets/admin-lte/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  <!-- JQVMap -->
  <link rel="stylesheet" href="/assets/admin-lte/plugins/jqvmap/jqvmap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="/assets/admin-lte/dist/css/adminlte.css">
  <!-- overlayScrollbars -->
  <link rel="stylesheet" href="/assets/admin-lte/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
  <!-- Daterange picker -->
  <link rel="stylesheet" href="/assets/admin-lte/plugins/daterangepicker/daterangepicker.css">
  <!-- summernote -->
<%--  <link rel="stylesheet" href="/assets/admin-lte/plugins/summernote/summernote-bs4.min.css">--%>
  <!--style-->
  <link rel="stylesheet" href="/admassets/css/admin_style.css">
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
    </style>


  <!-- jQuery -->
  <script src="/assets/admin-lte/plugins/jquery/jquery.min.js"></script>
  <!-- jQuery UI 1.11.4 -->
  <script src="/assets/admin-lte/plugins/jquery-ui/jquery-ui.min.js"></script>
  <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->

    <script type="text/javascript" charset="utf-8" src="/assets/js/jquery/jquery.cookie.js"></script>
  <!-- Bootstrap 4 -->
  <script src="/assets/admin-lte/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
  <!-- ChartJS -->
  <script src="/assets/admin-lte/plugins/chart.js/Chart.min.js"></script>
  <!-- Sparkline -->
  <script src="/assets/admin-lte/sparklines/sparkline.js"></script>
  <!-- JQVMap -->
  <script src="/assets/admin-lte/plugins/jqvmap/jquery.vmap.min.js"></script>
  <script src="/assets/admin-lte/plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
  <!-- jQuery Knob Chart -->
  <script src="/assets/admin-lte/plugins/jquery-knob/jquery.knob.min.js"></script>
  <!-- daterangepicker -->
  <script src="/assets/admin-lte/plugins/moment/moment.min.js"></script>
  <script src="/assets/admin-lte/plugins/daterangepicker/daterangepicker.js"></script>
  <!-- Tempusdominus Bootstrap 4 -->
  <script
    src="/assets/admin-lte/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
  <!-- Summernote -->
<%--  <script src="/assets/admin-lte/plugins/summernote/summernote-bs4.min.js"></script>--%>

  <script type="text/javascript" src="/assets/w_mode/js/HuskyEZCreator.js" charset="utf-8"></script>
  <!-- overlayScrollbars -->
  <script src="/assets/admin-lte/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
  <!-- AdminLTE App -->
  <script src="/assets/admin-lte/dist/js/adminlte.js"></script>
  <!-- AdminLTE for demo purposes -->
  <script src="/assets/admin-lte/dist/js/demo.js"></script>

  <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
  <script src="/assets/admin-lte/dist/js/pages/dashboard.js"></script>
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
<script type="text/javascript" charset="utf-8" src="/assets/js/plus.js?v=2"></script>

<script type="text/javascript" charset="utf-8" src="/assets/js/custom.front.js?v=899"></script>

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
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">

    <tiles:insertAttribute name="head" />
    <tiles:insertAttribute name="menu" />
    <!-- container -->
    <div class="page-wrapper">
        <tiles:insertAttribute name="contents" />
    </div>
    <!-- //content-wrapper -->
    <tiles:insertAttribute name="footer" />
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
