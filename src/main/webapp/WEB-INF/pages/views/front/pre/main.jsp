<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<body>
	<div class="wrapper bg_1">
		<div class="pre_q_container">
			<p class="txt1">
				<span class="txt_AI">AI</span> 진단준비가 끝났습니다.<br/><br/>
				지금, 단 5문제만 풀면<br/>
				성공적으로 1등급을 달성하는<br/>
				비법을 확인할 수 있습니다.
			</p>
			<a href="#" class="btn_start"><span>시작하기</span></a>
			<p class="txt2">
				수학 문제를 연습장에 풀 수 있는<br>
				환경에서 진행해주세요.
			</p>
		</div>
	</div>
</body>

<script>

var $form = $("#form");

$(document).ready(function(){
	$('.btn_start').click(function(){
		$('.disabled').removeClass('disabled');
	});
});

</script>