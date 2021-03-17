<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp" %>

<body>
	<div class="wrapper">
		<header class="header">
			<h1>이용권</h1>
			<a href="/front/product/info" class="btn_back">이전으로</a>
		</header>
		<div class="container pass">
			<div class="contents scroll_y">
				<div class="ban1">무제한 학습 + 무제한 분석 리포트</div>
				<div class="ban2"><span class="logo_img">AIGo</span> <span class="txt">Free Pass</span></div>
				<div class="free_pass_price2">
					<span class="origin_price">38,000</span>
					<p><strong>32,000</strong>원</p>
				</div>
				<h2>상품 상세 설명</h2>
				<h3>상품 특징</h3>
				<ul class="chk_list">
					<li>수학 전 과목 문제 <em>25,674</em>개 무제한 제공</li>
					<li>나의 예측 등급, 강점/약점 단원 분석 </li>
					<li>학습한 모든 문제의 무제한 다시 풀기</li>
					<li>개념 보완을 위한 핵심 강의 무제한 제공</li>
				</ul>
				<h3 class="mt">이용 방법</h3>
				<ul class="use_div">
					<li><span class="label">이용방법</span><span class="txt">구매 후 메인 화면 학습 시작 버튼을 눌러 시작</span></li>
					<li><span class="label">이용기간</span><span class="txt">결제 완료일부터~까지 이용가능 </span></li>
				</ul>
				<div class="guide_box1">
					<p><span>학습 시작 후 환불 및 취소는 불가합니다.</span></p>
					<p><span>단체 이용, 아이디 공유, 불법 다운로드는 관련법에 의거 처벌됩니다.</span></p>
				</div>
				<h3 class="mt">저작권</h3>
				<p class="txt_st4">AIGo에서 제공하는 모든 콘텐츠는 AIGo의 자산입니다. AIGo를 통해 제공되는 모든 콘텐츠의 내용 및 문제의 무단 전제, 복사 및 배포/유포를 금지하며, 위반 시 관련 법령에 의거하여 처벌될 수 있습니다</p>
				<button class="btn btn_st3 fs1" onclick="goPay();">구매</button>
			</div>
		</div><!-- //container -->
	</div>
</body>

<script type="text/javascript">

var $form = $("#form");

$(document).ready(function(){
	
	
	
});

//이용권 구매 - 인앱결제 이동
function goPay(){
	try{
		Native.postMessage('{"event":"send_product","memId":"${loginSessionId}"}');
	}catch(e){
		alert('모바일 환경에서만 이용가능합니다..');
	}
    //alert('인앱결제 구현 예정입니다.');
    //window.location.href = "/front/pay/result";
}

</script>