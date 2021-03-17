<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script src="${contextPath}/resources/js/user/login.js"></script>
<script>
  $(function() {
    jQuery(document).ready(function($) {
      $('a').each(function () {
        if ($(this).attr('href') == '#') {
          $(this).attr('href','javascript:;');
        }
      });
    });

    Header.init.userHeader();
  });

</script>
<header id="header">
  header
</header>