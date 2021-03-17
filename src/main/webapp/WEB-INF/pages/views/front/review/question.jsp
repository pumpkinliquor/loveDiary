<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp" %>
<c:set var="data" value="${resultMap.question}"/>
<c:set var="dataFile" value="${resultMap.questionFileList}"/>
<body class="study_body2">
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>다시풀기</h1>
		</header>
		<div class="container btm0">
			<div class="study_conts again_study2">
				<div class="again_solve_view_cont">
					<div class="title_div ov_h">
						<p class="fL">
							<fmt:formatDate var="dateFmt" pattern="yyyy-MM-dd" value="${data.reg_sysdate}"/>
							<span class="tit">${data.sub_name} ${data.acv_name}</span>
							<span class="date">학습한 날 ${dateFmt} </span>
						</p>
						<c:if test="${data.pass_yn eq 'y'}">
							<strong name="icoOX" class="ico_O fR">O</strong>
						</c:if>
						<c:if test="${data.pass_yn eq 'n'}">
							<strong name="icoOX" class="ico_X fR">X</strong>
						</c:if>
					</div>
					<div class="q_view_div q_sec">
						<%-- 문항 컨텐츠 --%>
						<c:choose>
							<c:when test="${data.qst_cont_type eq 'I'}">
								<c:forEach var="row" items="${dataFile }">
									<c:if test="${fn:contains(row.safCode,'qstContType') }">
										<img class="q_img" src="/common/siteImgView?safSeq=<c:out value="${row.safSeq }"/>" alt="문제 이미지" onerror="this.src=''"/>
									</c:if>
								</c:forEach>
							</c:when>
							<c:when test="${data.qst_cont_type eq 'T'}">
								<c:if test="${not empty data.qst_cont_text }"><c:out value="${data.qst_cont_text }" escapeXml="false" /></c:if>
							</c:when>
						</c:choose>
						<br/>
						<c:choose>
							<c:when test="${data.qst_cont02_type eq 'I'}">
								<c:forEach var="row" items="${dataFile }">
									<c:if test="${fn:contains(row.safCode,'qstCont02Type') }">
										<img class="q_img" src="/common/siteImgView?safSeq=<c:out value="${row.safSeq }"/>" alt="문제 이미지" onerror="this.src=''"/>
									</c:if>
								</c:forEach>
							</c:when>
							<c:when test="${data.qst_cont02_type eq 'T'}">
								<c:if test="${not empty data.qst_cont02_text }"><c:out value="${data.qst_cont02_text }" escapeXml="false" /></c:if>
							</c:when>
						</c:choose>
						<br/>
						<c:choose>
							<c:when test="${data.qst_cont03_type eq 'I'}">
								<c:forEach var="row" items="${dataFile }">
									<c:if test="${fn:contains(row.safCode,'qstCont03Type') }">
										<img class="q_img" src="/common/siteImgView?safSeq=<c:out value="${row.safSeq }"/>" alt="문제 이미지" onerror="this.src=''"/>
									</c:if>
								</c:forEach>
							</c:when>
							<c:when test="${data.qst_cont03_type eq 'T'}">
								<c:if test="${not empty data.qst_answer_01 }">① <c:out value="${data.qst_answer_01 }"/><br/></c:if>
								<c:if test="${not empty data.qst_answer_02 }">② <c:out value="${data.qst_answer_02 }"/><br/></c:if>
								<c:if test="${not empty data.qst_answer_03 }">③ <c:out value="${data.qst_answer_03 }"/><br/></c:if>
								<c:if test="${not empty data.qst_answer_04 }">④ <c:out value="${data.qst_answer_04 }"/><br/></c:if>
								<c:if test="${not empty data.qst_answer_05 }">⑤ <c:out value="${data.qst_answer_05 }"/><br/></c:if>
							</c:when>
						</c:choose>
					</div>
				</div>
			</div>
			<section class="answer_submit_cont beforeAnswerArea">
				<c:choose>
				<c:when test="${data.qst_type eq 'S' }">
				<%-- 객관식 답 입력 --%>
				<div class="number_div">
					<c:forEach begin="1" end="${data.qst_type_num}" var="num">
					<button type="button" onclick="fnChooseAnswer('<c:out value="${num}" />');"><c:out value="${num}" /></button>
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
			<div class="answer_cont afterAnswerArea" style="display: none; position:absolute;bottom:0;left:0;right:0;">
				<div class="answer">
					<span class="userAnswerArea">입력: -</span>
					<em>정답: <c:out value="${data.qst_value }"/></em>
				</div>
				<%-- 객관식일 경우 정답/입력답 표시  --%>
				<c:if test="${data.qst_type eq 'S' }">
				<ul class="number_div mt10">
					<c:forEach begin="1" end="${data.qst_type_num }" var="num">
					<li class="<c:out value="${num eq data.qst_value? 'on' : '' }" />" >
						<div><span class="num"><c:out value="${num }" /></span></div>
						<div><span class="markArea <c:out value="${num eq data.qst_value? 'ico_O' : '' }" />"></span></div>
					</li>
					</c:forEach>
				</ul>
				</c:if>

				<div class="btm_div">
					<a href="#" class="btn btn_st2 fL" onclick="goCommentary('<c:out value="${data.cmtr_id }"/>');">해설보기</a>
					<a href="#" class="btn btn_st2 fR" onclick="goNotion('<c:out value="${data.cpt_id }"/>');">개념보기</a>
				</div>
			</div>

		</div><!-- //container -->
	</div>
</body>

<%-- 답안 채점 Form --%>
<form id="answerform" name="answerform" method="post">
	<input type="hidden" name="useYn" value="<c:out value="${data.use_yn}"/>" />
	<input type="hidden" name="qstId" value="<c:out value="${data.qst_id}"/>" />
	<input type="hidden" name="sCnt" value="<c:out value="${data.s_cnt}"/>" />
	<input type="hidden" name="fCnt" value="<c:out value="${data.f_cnt}"/>" />
	<input type="hidden" name="ansrId" value="<c:out value="${data.ansr_id}"/>" />
	<input type="hidden" name="ansValue" value="" />
	<input type="hidden" name="qstOrder" value="<c:out value="${data.qst_order}"/>" />
	<input type="hidden" name="ansOrder" value="<c:out value="${data.ans_order}"/>" />
	<input type="hidden" name="passYn" value="" /> <%-- 채점 결과를 리턴받을 때에만 쓰임 --%>
	<input type="hidden" name="qstValue" value="" /> <%-- 채점 결과를 리턴받을 때에만 쓰임 --%>
</form>
<form id="form" name="form" method="post">
	<input type="hidden" name="qstId" value="<c:out value="${data.qst_id}"/>" />
	<input type="hidden" name="subId" value="<c:out value="${data.sub_id}"/>" />
	<input type="hidden" name="acvId" value="<c:out value="${data.acv_id}"/>" />
	<input type="hidden" name="levId" value="<c:out value="${data.lev_id}"/>" />
	<input type="hidden" name="passYn" value="" />
	<input type="hidden" name="cmtrId" value="<c:out value="${data.cmtr_id}"/>" />
	<input type="hidden" name="cptId" value="<c:out value="${data.cpt_id}"/>" />
	<input type="hidden" name="notId" value="<c:out value="${data.not_id}"/>" />
</form>

<script type="text/javascript">

var $form = $("#form");
var $answerform = $("#answerform");
var $beforeAnswerArea = $(".beforeAnswerArea");
var $afterAnswerArea = $(".afterAnswerArea");
var $container = $(".container");

$(document).ready(function(){
	console.log("${dataFile }")
	console.log("${data }")
	$('.number_div button').on('click', function(){
		$('.number_div button').removeClass('on');
		$(this).addClass('on');
		$('.btn_submit').removeClass('disabled');
	});

	$('.shortAnswer').on('keyup', function(){
		var $this = $(this);
		$answerform.find("input[name='ansValue']").val($this.val());
		if($this.val() != ''){
			$('.btn_submit').removeClass('disabled');
		}else{
			$('.btn_submit').addClass('disabled');
		}
	});

	$('#set1').on('click', function(){
		$('.fc1').toggle();
	});
	
});

// 해설보기 페이지 이동
function goCommentary(cmtrId){
// 	$form.find("input[name='cmtrId']").val(cmtrId);
	$form.attr("action", "/front/commentary/commentaryDetail").submit();
}

// 개념보기 페이지 이동
function goNotion(cptId){
// 	$form.find("input[name='cptId']").val(cptId);
	$form.attr("action", "/front/review/notion").submit();
}

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
	if($answerform.find("input[name='ansValue']").val() != '' ){
		$.call('/front/ajax/learn/sendAnswer', param, function(r){
			$answerform.find("input[name='passYn']").val(r.resultData.resultStatus);
			$form.find("input[name='passYn']").val(r.resultData.resultStatus);
			$answerform.find("input[name='currentTestTypeSub']").val(r.resultData.pageTypeSub);
			$answerform.find("input[name='currentTestType']").val(r.resultData.pageType);
			$form.find("input[name='currentTestTypeSub']").val(r.resultData.pageTypeSub);
			$form.find("input[name='currentTestType']").val(r.resultData.pageType);
			markingAnswer();
			if(r.resultData.resultCode == "S03"){
				isFinish = true;
			}
		});
	}
}

// 답안 제출 시 채점액션
function markingAnswer(){
	var $qstValue = $answerform.find("input[name='qstValue']").val();
	var $ansValue = $answerform.find("input[name='ansValue']").val();
	var $passYn = $answerform.find("input[name='passYn']").val();
	var $passArea = $('.passArea');
	var $markArea = $('.markArea');
	var $icoOX= $("strong[name='icoOX']");
	$icoOX.removeClass('ico_O').removeClass('ico_X');
	if($passYn == 'y'){
		$icoOX.addClass('ico_O');
	}else{
		$icoOX.addClass('ico_X');
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

</script>