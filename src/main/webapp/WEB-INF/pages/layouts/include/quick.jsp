<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script>
  $(function () {
    $('.icon-top').click(function () {
      $("html, body").animate({scrollTop: 0}, '200');
    });

    /**
     * 오늘 본 상품 쿠키 정보 출력
     */
    var itemKey = KobayDefault.cookieList("itemKey");
    var path = KobayDefault.cookieList("path");
    var url = KobayDefault.cookieList("url");
    var keyLength = itemKey.items() == null ? 0 : itemKey.items().length;
    var keyList = itemKey.items();
    var pathList = path.items();
    var urlList = url.items();

    if (keyLength != 0) {
      for (var i in keyList) {
        if (i == '3') return;
        var keyListLength = keyLength - (Number(i) + 1);
        $('.currentAuctionList').append('<li><a href="' + contextPath + urlList[keyListLength] + keyList[keyListLength] + '">' +
        '<img src="' + contextPath + pathList[keyListLength] + '"/></a></li>');
      }
    }

  });
</script>
<div class="quick-menu pc">
  <ul class="service">
    <li class="icon-notice">
      <a href="${contextPath}/customerCenter/notice/list">공지사항</a>
    </li>
    <li class="icon-guide">
      <a href="${contextPath}/customerCenter/tutorial/main">코베이가이드</a>
    </li>
    <li class="icon-blog">
      <a target="_blank" href="http://blog.naver.com/e_kobay">블로그</a>
    </li>
  </ul>
  <div class="goods">
    <h1 class="tit">오늘 본 상품</h1>
    <ul class="goods-list currentAuctionList">
    </ul>
    <p class="m1">
      <a href="${contextPath}/customerCenter/oneToOne/main">
        <span>코베이 112</span>
      </a>
    </p>
    <p class="m2">
      <a href="${contextPath}/customerCenter/faq/main">
        <span>도난문화재</span>
      </a>
    </p>
  </div>
  <p class="icon-top">
    <a href="javascript:;"></a>
  </p>
</div>