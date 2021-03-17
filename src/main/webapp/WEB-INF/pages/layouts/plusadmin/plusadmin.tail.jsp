<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
    <!-- main-footer -->
    <footer class="main-footer">
      <strong>Copyright &copy; 2021 <a href="https://adminlte.io">SAMIL.CO.LTD</a></strong> All rights Reserved.
    </footer>
    <!-- //main-footer -->

    <!-- control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark"></aside>
    <!-- //control-sidebar -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<div id="postcodeLayer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;z-index:100;">
    <img src="//i1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">



</div>
