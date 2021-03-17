<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp" %>

<body class="study_body2">
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>다시풀기</h1>
		</header>
		<div class="container">
			<div class="study_conts">
				<div class="again_solve_view_cont">
					<div class="title_div ov_h">
						<p class="fL">
							<span class="tit">수학Ⅰ Lv.1 로그의 뜻과 성질 이해</span>
							<span class="date">학습한 날 yyyy-mm-dd</span>
						</p>
						<strong class="ico_O fR">O</strong>
					</div>
					<div class="q_view_div">
						<img src="/assets/images/_tmp/img_question_2.jpg" alt="">
					</div>
				</div>
			</div>
			<section class="answer_submit_cont">
				<div class="number_div">
					<button type="button">1</button>
					<button type="button">2</button>
					<button type="button" class="on">3</button>
					<button type="button">4</button>
					<button type="button">5</button>
				</div>
				<button type="submit" class="btn btn_submit">답안제출</button>
			</section>
			<div class="answer_cont">
				<div class="answer">
					<span>입력: 3</span>
					<em>정답: 3</em>
				</div>
				<ul class="number_div mt10">
					<li>
						<div><span class="num">1</span></div>
						<div></div>
					</li>
					<li class="on">
						<div><span class="num">2</span></div>
						<div><span class="ico_O">O</span></div>
					</li>
					<li>
						<div><span class="num">3</span></div>
						<div><span class="ico_X">X</span></div>
					</li>
					<li>
						<div><span class="num">4</span></div>
						<div></div>
					</li>
					<li>
						<div><span class="num">5</span></div>
						<div></div>
					</li>
				</ul>
				<div class="btm_div">
					<a href="#" class="btn btn_st2 fL" onclick="goCommentary('<c:out value="${data.cmtrId }"/>');">해설보기</a>
					<a href="#" class="btn btn_st2 fR" onclick="goConcept('<c:out value="${data.cptId }"/>');">개념보기</a>
				</div>
			</div>
		</div><!-- //container -->
	</div>
</body>

<form name="form" id="form" action="post">
	<input type="hidden" name="qstId" value="" />
	<input type="hidden" name="cmtrId" value="" />
	<input type="hidden" name="cptId" value="" />
</form>

<script type="text/javascript">

var $form = $("#form");

$(document).ready(function(){
	
	
	
});

// 해설보기 페이지 이동
function goCommentary(cmtrId){
	$form.find("input[name='cmtrId']").val(cmtrId);
	$form.attr("action", "/front/review/commentary").submit();
}

// 개념보기 페이지 이동
function goConcept(cptId){
	$form.find("input[name='cptId']").val(cptId);
	$form.attr("action", "/front/review/concept").submit();
}
</script>