<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp" %>

<body>
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>결제완료</h1>
		</header>
		<div class="container cont_box purchase_history">
			<div class="contents">
				<div class="fin_div"><p>정상적으로 상품 결제가 완료되었습니다. </p></div>
				<div class="purchase_div btx">
					<h2 class="w100">상품명상품명 상품명 </h2>
					<ul>
						<li><span class="label">결제일</span><span class="data">2021-03-12</span></li>
						<li><span class="label">결제금액</span><span class="data">&#8361; 32,000</span></li>
						<li><span class="label">이용기간</span><span class="data">2021-03-12 ~ 2021-04-11</span></li>
					</ul>
				</div>
				<button class="btn btn_st3 fs1" onclick="goLearn();">AI 문제풀이 시작 Go</button>
			</div>
		</div><!-- //container -->
	</div>
</body>

<script type="text/javascript">

modalClose();

var $form = $("#form");

$(document).ready(function(){
	
	
	
});

function goLearn(){
    window.location.href = "/front/learn/home";
}
</script>