<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<body>
	<div class="wrapper bg_1">
		<div class="pre_q_container">
			<p class="txt1">
				<span class="txt_AI">AI</span> 진단으로 제공되는 <br>지금 나에게 꼭 필요한 <br>최우선 순위의 수능 수학 문제찾기
			</p>
			<a href="#" class="btn_start"><span>시작하기</span></a>
			
			<c:if test="${not empty loginSessionId }">
				<p class="txt2">
				   수학문제를 연습장에 풀수있는 <br>
					 환경에서 진행해주세요.
				 </p>
			</c:if>
			<c:if test="${empty loginSessionId }">
			<p class="txt2">이미 가입하셨나요? <br>
				<a href="#!" onclick="goLoginPage();">로그인 후 맞춤문제를 확인하세요</a>
			</p>
			</c:if>
			<div class="btm_btn_div">
				<a href="javascript:void(0)" class="next disabled"><span>다음</span></a>
			</div>
		</div>
	</div>
</body>

<script>
	
	$(document).ready(function(){
		$('.btn_start').click(function(){
			$('.disabled').removeClass('disabled');
		});
		
		$('.next').click(function(){
			if($(this).is('.disabled')){
				return false;
			}
			$.call('/front/ajax/aigo/firs/step01',{tep_class:$('.q_choice .on button').attr('data')},function(r){
				location.href= '/front/furs/step02';
			});
		})
	});
	
</script>