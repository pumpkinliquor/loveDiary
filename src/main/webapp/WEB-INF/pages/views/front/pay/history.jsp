<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp" %>

<%-- 테스트 관련 필드 (DB연동 후 삭제) --%>
<c:set var="deviceType" value="ios" />
<%-- <c:set var="deviceType" value="and" /> --%>
<%-- <c:set var="deviceType" value="etc" /> --%>
<c:set var="payHistoryList" value="" />
<%-- 테스트 관련 필드 --%>

<body>
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>구매내역</h1>
		</header>
		<div class="container cont_box purchase_history">
			<div class="contents">
				<c:choose>
				<c:when test="${not empty payHistoryList }">
					<div class="purchase_div">
						<h2>상품명상품명 상품명</h2>
						<ul>
							<li><span class="label">결제일</span><span class="data">yyyy-mm-dd</span></li>
							<li><span class="label">결제금액</span><span class="data">&#8361; ###,###</span></li>
							<li><span class="label">이용기간</span><span class="data">yyyy-mm-dd ~ yyyy-mm-dd</span></li>
						</ul>
						<div class="link_div"><a href="#" class="cancel_link">취소신청</a></div>
						<span class="badge bdg1">이용중</span>
					</div>
					<div class="purchase_div">
						<h2>상품명상품명 상품명상품명</h2>
						<ul>
							<li><span class="label">결제일</span><span class="data">yyyy-mm-dd</span></li>
							<li><span class="label">결제금액</span><span class="data">&#8361; ###,###</span></li>
							<li><span class="label">이용기간</span><span class="data">yyyy-mm-dd ~ yyyy-mm-dd</span></li>
						</ul>
						<span class="badge bdg2">이용종료</span>
					</div>
					
					<%-- 가장 최근에 결제한 내역이 이용중일 때 and 안드로이드에서 결제 --%>
					<div class="guide_box android">
						<a href="#" class="link">AIGo 환불규정</a>
						<button class="btn btn_st2">
							<span>구매내역 업데이트</span>
							<span class="fs">(결제오류 복원)</span>
						</button>
					</div>
					
					<%-- 가장 최근에 결제한 내역이 이용중일 때 and 안드로이드에서 결제 --%>
					<div class="guide_box iOS">
						<p>결제가 애플 앱스토어에서 진행된 경우의 환불<br><br>
						애플 앱스토어를 통해 AIGo 상품을 구매하신 경우 Apple사의 정책에 따라 Apple사에 직접 환불을 요청 하셔야 합니다. (애플 고객센터 : 080-333-4000) <br><br>
						애플 앱스토어에서 환불이 완료되었나요?<br><br>
						애플 앱스토어에서 환불 처리가 완료됐다면 AIGo에 환불 완료 여부를 반드시 알려주셔야 합니다. <br><br>
						<a href="#" class="link">환불완료 이메일 접수</a></p>
					</div>
				</c:when>
				<c:otherwise>
					<p class="purchase_none_txt">구매내역이 없습니다.</p>
					<button class="btn btn_st3 f1" onclick="goProductInfo();">AIGo 이용권 구매하기</button>
				</c:otherwise>
				</c:choose>
				
			</div>
		</div><!-- //container -->
	</div>
	
</body>

<script type="text/javascript">

modalClose();

var $form = $("#form");

$(document).ready(function(){
	
	
	
});

// function goProductInfo(){
	
//     window.location.href = "/front/product/info";
	
// }

</script>