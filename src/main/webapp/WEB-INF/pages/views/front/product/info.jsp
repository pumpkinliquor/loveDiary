<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp" %>

<%-- 테스트 관련 필드 (DB연동 후 삭제) --%>
<c:set var="productList" value="list" />
<%-- 테스트 관련 필드 --%>

<body>
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>이용권</h1>
		</header>
		<div class="container pass">
			<c:choose>
			<c:when test="${not empty productList }">
				<div class="contents scroll_y">
				<div class="ban1">무제한 학습 + 무제한 분석 리포트</div>
				<div class="ban2"><span class="logo_img">AIGo</span> <span class="txt">Free Pass</span></div>
				<ul class="chk_list mt">
					<li>수학 전 과목 문제 <em>25,674</em>개 무제한 제공</li>
					<li>나의 예측 등급, 강점/약점 단원 분석 </li>
					<li>학습한 모든 문제의 무제한 다시 풀기</li>
					<li>개념 보완을 위한 핵심 강의 무제한 제공</li>
				</ul>
				<div class="free_pass_price">
					<span class="origin_price">38,000</span>
					<p><strong>32,000</strong>원</p>
				</div>
				<div class="btn_div">
					<a href="#!" class="btn btn_a fL" onclick="goProductDetail('1233');">상세보기</a>
					<a href="#!" class="btn btn_b fR" onclick="goPay();">구매</a>
				</div>
			</div>
			</c:when>
			<c:otherwise>
				<ul style="margin-top: 70%;text-align: center;">
					<li>구매 가능한 이용권이 없습니다.</li>
				</ul>
			</c:otherwise>
			</c:choose>
			
		</div><!-- //container -->
	</div>
</body>

<form id="form" name="form" method="post">
	<input name="prdId" value="" />
</form>

<script type="text/javascript">

var $form = $("#form");

$(document).ready(function(){
	
	
	
});

// 이용권 상세정보
function goProductDetail(prdId){
    $form.find("input[name='prdId']").val(prdId);
    $form.attr("action","/front/product/detail").submit();
}

// 이용권 구매 - 인앱결제 이동
function goPay(){

    //window.location.href = "/front/pay/result";
	try{
		Native.postMessage('{"event":"send_product","memId":"${loginSessionId}"}');
	}catch(e){
		alert('모바일 환경에서만 이용가능합니다..');
	}
}

</script>