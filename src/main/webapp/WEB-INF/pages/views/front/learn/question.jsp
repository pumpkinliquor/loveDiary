<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp" %>
<c:set var="data" value="${resultMap.resultData.question}"/>

<body class="question_body">
	<div class="wrapper">
		<header class="header">
			<h1 class="logo">AIGo</h1>
			<a href="/front/learn/home" class="btn_out">나가기</a>
			<div class="my_grade_top" style="text-align: center;">
				<div class="onoffswitch2">
					<input type="checkbox" id="set1" checked="checked">
					<label for="set1"><span></span></label>
				</div>
				<div>
					<span>최초 등급 5등급 </span>
					<span class="fc1">성취 예상 등급 <em>2등급</em></span>
				</div>
			</div>
		</header>
		<div class="container">
			<div class="tab_wrap">
				<div class="tab_menu_wrap afterAnswerArea" style="display: none;">
					<div class="tab_menu tab3" id="qTab">
						<button class="tab_m1 tab_m active" type="button">채점</button>
						<button class="tab_m2 tab_m" type="button">해설</button>
						<button class="tab_m3 tab_m" type="button">개념</button>
					</div>
				</div>
				<div class="tab_cont_wrap" id="qTabCont">
					<div class="tab_cont1 tab_cont active">
						<div class="question_conts">
							<h2 class="q_number"><c:out value="${data.currentQuestionViewNumber }"/>.</h2>
							<span class="ico_X passArea afterAnswerArea" style="display: none;">O</span>
							<div class="q_solve_top">
								<p class="q"><c:out value="${data.subName }"/> <c:out value="${data.levName }"/>. <c:out value="${data.acvName }"/></p>
								<div class="tag_div">
									<a href="#" class="tag">로그의 성실</a>
									<a href="#" class="tag">상용로그</a>
									<a href="#" class="tag">로그의 성실</a>
									<a href="#" class="tag">상용로그</a>
								</div>
								<div class="ov_h">
									<div class="aigo_rate"><img src="/assets/images/logo.png" alt="AIGo"><span>정답률</span> <em>83%</em></div>
									<div><span>난이도</span><span class="d_E">E</span></div>
								</div>
							</div>
							<div class="q_img_div">${data }
								<%-- 추후 컨텐츠 타입에 따라 바뀌는 로직 추가해야 함 --%>
								<img class="q_img" src="<c:out value="${data.safPath }/${data.safFile }"/>" alt="문제 이미지">
							</div>
							<div class="dontknow beforeAnswerArea">
								<a href="#" class="fR btn btn_st1" onclick="fnChooseAnswer('');">잘 모르겠어요</a>
							</div>
						</div>
						<div class="answer_cont mt afterAnswerArea" style="display: none;">
							<div class="answer">
								<span class="userAnswerArea">입력: -</span>
								<em>정답: <c:out value="${data.qstValue }"/></em>
							</div>
							<%-- 객관식일 경우 정답/입력답 표시  --%>
							<c:if test="${data.qstType eq 'S' }">
							<ul class="number_div mt10">
								<c:forEach begin="1" end="${data.qstTypeNum }" var="num">
								<li class="<c:out value="${num eq data.qstValue? 'on' : '' }" />" >
									<div><span class="num"><c:out value="${num }" /></span></div>
									<div><span class="markArea"></span></div>
								</li>
								</c:forEach>
							</ul>
							</c:if>
						</div>
						<div class="btm_btn_div2 mrg afterAnswerArea" style="display: none;">
							<!-- <a href="#" class="prev"><span>이전</span></a> -->
							<a href="#" class="next"><span>다음</span></a>
						</div>
					</div>
					<div class="tab_cont2 tab_cont"></div>
					<div class="tab_cont3 tab_cont"></div>
				</div>
			</div>
			<section class="progress_cont beforeAnswerArea">
				<div class="progress_div"><span class="bar" style="width:73%"><span class="text">0문제 중 0번</span></span><span class="txt_right" style="width:27%">학습율 73% </span></div>
				<div class="expect_div">
					<dl class="fc">
						<dt>나의 풀이 시간</dt>
						<dd>00분 00초</dd>
					</dl>
					<dl>
						<dt>권장 풀이 시간</dt>
						<dd>00분 00초</dd>
					</dl>
				</div>
			</section>
			<section class="answer_submit_cont beforeAnswerArea">
				<c:choose>
				<c:when test="${data.qstType eq 'S' }">
				<%-- 객관식 답 입력 --%>
				<div class="number_div">
					<c:forEach begin="1" end="${data.qstTypeNum }" var="num">
					<button type="button" onclick="fnChooseAnswer('<c:out value="${num }" />');"><c:out value="${num }" /></button>
					</c:forEach>
				</div>
				</c:when>
				<c:otherwise>
				<%-- 주관식 답 입력 --%>
				<div><input type="text" name="" class="ipt_answer shortAnswer" placeholder="정답을 입력하세요" maxlength="30"></div>
				</c:otherwise>
				</c:choose>
				<button type="button" class="btn btn_submit" onclick="fnSendAnswer();">답안제출</button>
			</section>
		</div><!-- //container -->
	</div>
</body>

<%-- 답안 채점 Form --%>
<form id="answerform" name="answerform" method="post">
	<input type="hidden" name="qstId" value="<c:out value="${data.qstId}"/>" />
	<input type="hidden" name="ansValue" value="" />
	<input type="hidden" name="qstOrder" value="<c:out value="${data.currentQuestionNumber}"/>" />
	<input type="hidden" name="ansOrder" value="<c:out value="${data.currentQuestionViewNumber}"/>" />
	<input type="hidden" name="passYn" value="" /> <%-- 채점 결과를 리턴받을 때에만 쓰임 --%>
	<input type="hidden" name="qstValue" value="" /> <%-- 채점 결과를 리턴받을 때에만 쓰임 --%>
</form>
<%-- 다음 출제문제 이동 Form --%>
<form id="form" name="form" method="post">
	<input type="hidden" name="subId" value="<c:out value="${data.subId}"/>" />
	<input type="hidden" name="acvId" value="<c:out value="${data.acvId}"/>" />
	<input type="hidden" name="levId" value="<c:out value="${data.levId}"/>" />
	<input type="hidden" name="currentQuestionViewNumber" value="<c:out value="${data.currentQuestionViewNumber}"/>" />
	<input type="hidden" name="passYn" value="" />
</form>


<script type="text/javascript">

var $form = $("#form");
var $answerform = $("#answerform");
var $beforeAnswerArea = $(".beforeAnswerArea");
var $afterAnswerArea = $(".afterAnswerArea");
var $container = $(".container");

qTabMenu();

$(document).ready(function(){
	
	$('.number_div button').on('click', function(){
		$('.number_div button').removeClass('on');
		$(this).addClass('on');
	});
	
	$('.shortAnswer').on('keyup', function(){
		$answerform.find("input[name='ansValue']").val($(this).val());
	});
	
	$('#set1').on('click', function(){
		$('.fc1').toggle();
	});
	
});

// 사용자 답 선택
function fnChooseAnswer(num){
	if(num == ''){
		$answerform.find("input[name='ansValue']").val('unknown');
	}else{
		$answerform.find("input[name='ansValue']").val(num);
	}
}

// 답안제출
function fnSendAnswer(){
	var param = $answerform.domJson();
	console.log(param);
	//$.call('/front/ajax/learn/sendAnswer', param, function(r){
	//	console.log(r);
		$answerform.find("input[name='passYn']").val('n');
		markingAnswer();
	//});
	
}

// 답안 제출 시 채점액션
function markingAnswer(){
	var $qstValue = $answerform.find("input[name='qstValue']").val();
	var $ansValue = $answerform.find("input[name='ansValue']").val();
	var $passYn = $answerform.find("input[name='passYn']").val();
	var $passArea = $('.passArea');
	var $markArea = $('.markArea');
	$passArea.removeClass('ico_O').removeClass('ico_X');
	$markArea.removeClass('ico_O').removeClass('ico_X');
	if($passYn == 'y'){
		$passArea.addClass('ico_O');
		$markArea.eq(Number($ansValue)-1).addClass('ico_O');
	}else{
		$passArea.addClass('ico_X');
		$markArea.eq(Number($ansValue)-1).addClass('ico_X');
	}
	if($ansValue == 'unknown'){
		$('.userAnswerArea').text('입력: 잘 모르겠어요');
	}else if($ansValue == ''){
		$('.userAnswerArea').text('입력: -');
	}else{
		$('.userAnswerArea').text('입력: '+$ansValue);
	}
	$container.addClass('q_solving');
	$beforeAnswerArea.hide();
	$afterAnswerArea.show();
}

// 다음문제 버튼
function goNextQuestion(){
	$form.attr("action","/front/learn/question").submit();
}

</script>