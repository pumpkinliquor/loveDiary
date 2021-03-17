<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp" %>

<body class="study_body2">
	<div class="wrapper reviewListArea">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>다시풀기</h1>
		</header>
		<div class="container">
			<div class="study_conts">
				<p class="q_txt"><span><c:out value="${loginSessionEmail }" /> 님</span> 풀었던 문제를 다시 풀어보세요. </p>
				<div class="study_nav">
					<div class="fL">
						<a class="btn_filter">필터</a>
						<a class="btn_sort">정렬기준</a>
						<div class="layer_pop sort_pop">
							<ul class="select_div">
								<li class="ipt_radio_div">
									<input type="radio" id="select1" name="select">
									<label for="select1"><span>과목순</span></label>
								</li>
								<li class="ipt_radio_div">
									<input type="radio" id="select2" name="select">
									<label for="select2"><span>최근 풀이순</span></label>
								</li>
								<li class="ipt_radio_div">
									<input type="radio" id="select3" name="select">
									<label for="select3"><span>과거 풀이순</span></label>
								</li>
								<li class="ipt_radio_div">
									<input type="radio" id="select4" name="select">
									<label for="select4"><span>낮은 정답률순</span></label>
								</li>
								<li class="ipt_radio_div">
									<input type="radio" id="select5" name="select">
									<label for="select5"><span>높은 정답률순</span></label>
								</li>
							</ul>
						</div>
					</div>
					<div class="fR">
						<button class="btn btn_list">틀린문제</button>
						<button class="btn btn_list">맞춘문제</button>
					</div>
				</div>
				<div class="again_solve_list_cont">
					<div class="div">
						<div class="ov_h">
							<p class="fL">
								<span class="tit">수학Ⅰ  Lv.1 로그의 뜻과 성질 이해</span>
								<span class="date">학습한 날 yyyy-mm-dd</span>
							</p>
							<strong class="ico_O fR">O</strong>
						</div>
						<div class="btn_div">
							<button class="btn btn_st2" onclick="goReviewQuestion('<c:out value="${row.qstId }"/>');"><b>문제보기</b></button>
							<button class="btn btn_st2" onclick="goCommentary('<c:out value="${row.cmtrId }"/>');"><b>해설보기</b></button>
							<button class="btn btn_st2" onclick="goConcept('<c:out value="${row.cptId }"/>');"><b>개념보기</b></button>
						</div>
					</div>
					<div class="div">
						<div class="ov_h">
							<p class="fL">
								<span class="tit">수학Ⅰ Lv.1 로그의 뜻과 성질 이해</span>
								<span class="date">학습한 날 yyyy-mm-dd</span>
							</p>
							<strong class="ico_X fR">O</strong>
						</div>
						<div class="btn_div">
							<button class="btn btn_st2">문제보기</button>
							<button class="btn btn_st2">해설보기</button>
							<button class="btn btn_st2">개념보기</button>
						</div>
					</div>
					<div class="div">
						<div class="ov_h">
							<p class="fL">
								<span class="tit">수학Ⅰ Lv.1 로그의 뜻과 성질 이해</span>
								<span class="date">학습한 날 yyyy-mm-dd</span>
							</p>
							<strong class="ico_O fR">O</strong>
						</div>
						<div class="btn_div">
							<button class="btn btn_st2">문제보기</button>
							<button class="btn btn_st2">해설보기</button>
							<button class="btn btn_st2">개념보기</button>
						</div>
					</div>
					<div class="div">
						<div class="ov_h">
							<p class="fL">
								<span class="tit">수학Ⅰ Lv.1 로그의 뜻과 성질 이해</span>
								<span class="date">학습한 날 yyyy-mm-dd</span>
							</p>
							<strong class="ico_X fR">O</strong>
						</div>
						<div class="btn_div">
							<button class="btn btn_st2">문제보기</button>
							<button class="btn btn_st2">해설보기</button>
							<button class="btn btn_st2">개념보기</button>
						</div>
					</div>
				</div><!-- //again_solve_cont -->
			</div>
		</div><!-- //container -->
	</div>
	
	<!-- 다시풀기 필터레이어 -->
	<div class="wrapper filter_layer" style="display: none;">
		<header class="header">
			<h1>다시풀기 문제필터</h1>
			<a href="#" class="btn_close">닫기</a>
		</header>
		<div class="container">
			<p class="txt">* 복수 선택 가능합니다. 원하는 항목 선택 후 적용을 누르세요.</p>
			<h2>레벨</h2>
			<div class="lv_btn_div">
				<button class="btn btn_lv select">LV. 1</button>
				<button class="btn btn_lv">LV. 2</button>
				<button class="btn btn_lv">LV. 3</button>
				<button class="btn btn_lv">LV. 4</button>
				<button class="btn btn_lv">LV. 5</button>
			</div>
			<h2>과목, 단원</h2>
				<div class="sbj_btn_div">
					<div class="btn_div1">
						<button class="btn btn_sbj select">수학 I</button>
						<button class="btn btn_sbj">수학 I</button>
					</div>
					<div class="btn_div2">
						<button class="btn btn_sbj">지수함수와 로그함수</button>
						<button class="btn btn_sbj select">삼각함수</button>
						<button class="btn btn_sbj">수열</button>
					</div>
				</div>
			<button type="button" class="btn btn_join btnSearch">적용</button>
		</div><!-- //container -->
	</div>
</body>

<form name="form" id="form" action="post">
	<input type="hidden" name="qstId" value="" />
	<input type="hidden" name="cmtrId" value="" />
	<input type="hidden" name="cptId" value="" />
</form>
<form name="searchForm" id="searchForm" action="post">
</form>

<script type="text/javascript">

filterBtnSelect();

var $body = $('body');
var $form = $("#form");
var $searchForm = $("#searchForm");

$(document).ready(function(){
	
	// 필터 선택
	$('.btn_filter').on('click', function(){
		$body.attr('class', '');
		$body.addClass('bg_b');
		$('.reviewListArea').hide();
		$('.filter_layer').show();
	});
	
	// 필터설정완료
	$('.btnSearch, .btn_close').on('click', function(){
		$body.attr('class', '');
		$body.addClass('study_body2');
		$('.filter_layer').hide();
		$('.reviewListArea').show();
	});
	
});

// 다시풀기 문제페이지 이동
function goReviewQuestion(qstId){
	$form.find("input[name='qstId']").val(qstId);
	$form.attr("action", "/front/review/question").submit();
}

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