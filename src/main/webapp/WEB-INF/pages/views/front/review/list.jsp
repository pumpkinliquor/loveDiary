<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>

<body class="study_body2">
	<div class="wrapper reviewListArea">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>다시풀기</h1>
		</header>
		<div class="container btm0">
			<div class="study_conts scroll_y">
				<p class="q_txt">
					<span><c:choose>
						<c:when test="${!empty userInfo.memNickname }">
							<c:out value="${userInfo.memNickname }" />
						</c:when>
						<c:otherwise>
							<c:out value="${userInfo.memEmail }" />
						</c:otherwise>
					</c:choose>님</span> 풀었던 문제를 다시 풀어보세요.
				</p>
				<div class="study_nav">
					<div class="fL">
						<a class="btn_filter">필터</a> <a class="btn_sort">정렬기준</a>
						<div class="layer_pop sort_pop">
							<ul class="select_div">
								<li class="ipt_radio_div"><input type="radio" id="select1" name="select"> <label for="select1"><span>과목순</span></label></li>
								<li class="ipt_radio_div"><input type="radio" id="select2" name="select"> <label for="select2"><span>최근 풀이순</span></label></li>
								<li class="ipt_radio_div"><input type="radio" id="select3" name="select"> <label for="select3"><span>과거 풀이순</span></label></li>
								<li class="ipt_radio_div"><input type="radio" id="select4" name="select"> <label for="select4"><span>낮은 정답율순</span></label></li>
								<li class="ipt_radio_div"><input type="radio" id="select5" name="select"> <label for="select5"><span>높은 정답율순</span></label></li>
							</ul>
						</div>
					</div>
					<div class="fR">
						<button class="btn btn_list" onclick="oxFilter('n');">틀린문제</button>
						<button class="btn btn_list" onclick="oxFilter('y');">맞춘문제</button>
					</div>
				</div>
				<div class="again_solve_list_cont">
					<c:if test="${!empty resultMap.resultData.list }">
						<c:forEach var="l" items="${resultMap.resultData.list }">
							<div class="div">
								<div class="ov_h" onclick="goReviewQuestion(this);">
									<p class="fL">
										<span class="tit"><c:out value="${l.subName }" /> Lv.<c:out value="${l.levId }" /> <c:out value="${l.acvName }" /></span> <span class="date">학습한 날 <c:out value="${l.testDay }" /></span>
									</p>
									<c:if test="${l.passYn eq 'y' }">
										<c:set var="pass" value="O" />
									</c:if>
									<c:if test="${l.passYn eq 'n' }">
										<c:set var="pass" value="X" />
									</c:if>
									<strong class="ico_${pass } fR"> ${pass } </strong>
								</div>
								<div class="values">
									<input type="hidden" name="qstId" value="<c:out value="${l.qstId }"/>" />
									<input type="hidden" name="cmtrId" value="<c:out value="${l.cmtrId }"/>" />
									<input type="hidden" name="notId" value="<c:out value="${l.notId }"/>" />
									<input type="hidden" name="acvId" value="<c:out value="${l.acvId }"/>" />
								</div>
								<div class="btn_div">
									<button class="btn btn_st2" onclick="goReviewQuestion(this);">문제보기</button>
									<c:if test="${l.cmtrId ne null }">
										<button class="btn btn_st2" onclick="goCommentary(this);">해설보기</button>
									</c:if>
									<c:if test="${l.notIdStr eq 'y' }">
										<button class="btn btn_st2" onclick="goNotion(this);">개념보기</button>
									</c:if>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>
				<!-- //again_solve_cont -->
			</div>
		</div>
		<!-- //container -->
	</div>

	<!-- 다시풀기 필터레이어 -->
	<div class="wrapper filter_layer" style="display: none;">
		<header class="header">
			<h1>다시풀기 문제필터</h1>
			<a href="#" class="btn_close">닫기</a>
		</header>
		<div class="container btm0">
			<div class="content">
				<p class="txt">* 복수 선택 가능합니다. 원하는 항목 선택 후 적용을 누르세요.</p>
				<h2>레벨</h2>
				<c:set var="lvList" value="${fn:split(resultMap.resultData.fList[0].levId,',')}" />
				<div class="lv_btn_div">
					<c:if test="${!empty lvList }">
						<c:forEach var="val" items="${lvList }">
							<button class="btn btn_lv <c:if test="${dp.levIdStr eq val }">select</c:if>" value="${val }">LV. ${val }</button>
						</c:forEach>
					</c:if>
				</div>
				<h2>과목, 단원</h2>
				<div class="sbj_btn_div">
					<div class="btn_div1">
						<c:if test="${!empty resultMap.resultData.fList }">
							<c:forEach var="s" items="${resultMap.resultData.fList }">
								<c:set var="subIdB" value="${s.subId }" />
								<c:if test="${subIdB eq subIdA }">
									<button class="btn btn_sbj_none"></button>
								</c:if>
								<c:if test="${subIdB ne subIdA }">
									<button class="btn btn_sbj <c:if test="${dp.subIdStr eq s.subId && s.subId ne null}">select</c:if>" value="${s.subId }">
										<c:out value="${s.subName }" />
									</button>
								</c:if>
								<c:set var="subIdA" value="${s.subId }" />
							</c:forEach>
						</c:if>
					</div>
					<div class="btn_div2">
						<c:if test="${!empty resultMap.resultData.fList }">
							<c:forEach var="s" items="${resultMap.resultData.fList }">
								<button class="btn btn_sbj <c:if test="${dp.unitIdStr eq s.unitId && s.unitId ne null}">select</c:if>" value="${s.unitId }">
									<c:out value="${s.unitName }" />
								</button>
							</c:forEach>
						</c:if>
					</div>
				</div>
			</div>
			<button type="button" class="btn btn_join btnSearch">적용</button>
		</div>
		<!-- //container -->
	</div>
</body>

<form name="form" id="form" action="post">
	<input type="hidden" name="qstId" value="" />
	<input type="hidden" name="cmtrId" value="" />
	<input type="hidden" name="notId" value="" />
	<input type="hidden" name="acvId" value="" />
	<input type="hidden" name="reUrl" value="" />
</form>
<form name="searchForm" id="searchForm" action="post">
	<input type="hidden" name="levIdStr" value="" />
	<input type="hidden" name="subIdStr" value="" />
	<input type="hidden" name="unitIdStr" value="" />
	<input type="hidden" name="oxFilter" value="" />
	<input type="hidden" name="sortFilter" value="" />
</form>

<style>
.btn_sbj_none {
	width: 80px;
	height: 35px;
	background: transparent;
	border-radius: 5px;
	font-size: 14px;
	color: #fff;
	opacity: .5;
	cursor: default;
	font-weight: 300;
	margin-right: 5px;
	margin-bottom: 10px;
}
</style>

<script type="text/javascript" charset="UTF-8" src="/assets/js/public/default.js"></script>
<script type="text/javascript">
	filterBtnSelect();

	var $body = $('body');
	var $form = $("#form");
	var $searchForm = $("#searchForm");

	$(document).ready(function() {
		("${dp.levIdStr}" != "") ? $("[name=levIdStr]").val("${dp.levIdStr}") : true;
		("${dp.subIdStr}" != "") ? $("[name=subIdStr]").val("${dp.subIdStr}") : true;
		("${dp.unitIdStr}" != "") ? $("[name=unitIdStr]").val("${dp.unitIdStr}") : true;
		// 필터 선택
		$('.btn_filter').on('click', function() {
			$body.attr('class', '');
			$body.addClass('bg_b');
			$('.reviewListArea').hide();
			$('.filter_layer').show();
		});

		// 필터설정완료
		$('.btnSearch, .btn_close').on('click', function() {
			filterVal();
			$body.attr('class', '');
			$body.addClass('study_body2');
			if ($(this).is(".btnSearch")) {
				$.call('/front/ajax/review/list', $("[id=searchForm]").serializeObject(), function(r) {
					$(".again_solve_list_cont").empty();
					for (var i = 0; i < r.resultData.list.length; i++) {
						$(".again_solve_list_cont").append($("[id=txt_reviewList]").val());
						changeListFunc(i, r.resultData.list);
					}
				});
			}
			$('.filter_layer').hide();
			$('.reviewListArea').show();
		});

	});

	function oxFilter(ox) {
		ox = (ox == $("[name=oxFilter]").val()) ? "" : ox;
		$("[name=oxFilter]").val(ox);
		$.call('/front/ajax/review/list', $("[id=searchForm]").serializeObject(), function(r) {
			$(".again_solve_list_cont").empty();
			for (var i = 0; i < r.resultData.list.length; i++) {
				$(".again_solve_list_cont").append($("[id=txt_reviewList]").val());
				changeListFunc(i, r.resultData.list);
			}
		});
	}

	$(document).mouseup(function(e) {
		var LayerPopup = $(".layer_pop");
		if (LayerPopup.is(".active")) {
			if (LayerPopup.has(e.target).length === 0) {
				LayerPopup.removeClass("active");
// 				if ($(":radio[name=select]").is(":checked")) {
// 					$("[name=sortFilter]").val($(":radio[name=select]:checked").attr("id").replace(noneEng, ""));
// 					$.call('/front/ajax/review/list', $("[id=searchForm]").serializeObject(), function(r) {
// 						$(".again_solve_list_cont").empty();
// 						for (var i = 0; i < r.resultData.list.length; i++) {
// 							$(".again_solve_list_cont").append($("[id=txt_reviewList]").val());
// 							changeListFunc(i, r.resultData.list);
// 						}
// 					});
// 				}
			}
		}
	});
	
	// 바뀔 때
	$(document).on("change", ":radio[name=select]", function() {
		var LayerPopup = $(".layer_pop");
		LayerPopup.removeClass("active");
		$("[name=sortFilter]").val($(":radio[name=select]:checked").attr("id").replace(noneEng, ""));
		$.call('/front/ajax/review/list', $("[id=searchForm]").serializeObject(), function(r) {
			$(".again_solve_list_cont").empty();
			for (var i = 0; i < r.resultData.list.length; i++) {
				$(".again_solve_list_cont").append($("[id=txt_reviewList]").val());
				changeListFunc(i, r.resultData.list);
			}
		});
	});
	
	function filterVal() {
		var lvList = "";
		var subList = "";
		var unitList = "";
		$(".lv_btn_div").find(".select").each(function(i, e) {
			if (i == 0) lvList += $(e).val();
			else lvList += "," + $(e).val();
		});
		$(".btn_div1").find(".select").each(function(i, e) {
			if (i == 0) subList += $(e).val();
			else subList += "," + $(e).val();
		});
		$(".btn_div2").find(".select").each(function(i, e) {
			if (i == 0) unitList += $(e).val();
			else unitList += "," + $(e).val();
		});

		$("[name=levIdStr]").val(lvList);
		$("[name=subIdStr]").val(subList);
		$("[name=unitIdStr]").val(unitList);
	}

	function changeListFunc(i, v) {
		var pass = (v[i].passYn == "y") ? "O" : "X";
		var acvName = (typeof v[i].acvName == "undefined") ? "" : v[i].acvName;
		$(".again_solve_list_cont").find("div[class=div]:eq(" + i + ")").find("span[class=tit]").text(v[i].subName + " Lv." + v[i].levId + " " + acvName);
		$(".again_solve_list_cont").find("div[class=div]:eq(" + i + ")").find("span[class=date]").text("학습한 날 " + v[i].testDay);
		$(".again_solve_list_cont").find("div[class=div]:eq(" + i + ")").find("strong").text(pass);
		$(".again_solve_list_cont").find("div[class=div]:eq(" + i + ")").find("strong").attr("class", "ico_" + pass + " fR");
		$(".again_solve_list_cont").find("div[class=div]:eq(" + i + ")").find(".values > input[name=qstId]").val(v[i].qstId);
		$(".again_solve_list_cont").find("div[class=div]:eq(" + i + ")").find(".values > input[name=cmtrId]").val(v[i].cmtrId);
		$(".again_solve_list_cont").find("div[class=div]:eq(" + i + ")").find(".values > input[name=notId]").val(v[i].notId);
		$(".again_solve_list_cont").find("div[class=div]:eq(" + i + ")").find(".values > input[name=acvId]").val(v[i].acvId);
		if(v[i].cmtrId == null) {
			$(".again_solve_list_cont").find("div[class=div]:eq(" + i + ")").find(".btn_div > .btn_st2:eq(1)").remove();
		}
		if(v[i].notIdStr == 'n') {
			$(".again_solve_list_cont").find("div[class=div]:eq(" + i + ")").find(".btn_div > .btn_st2:eq(2)").remove();
		}
	}

	// 다시풀기 문제페이지 이동
	function goReviewQuestion(el) {
		var cmtrId = $(el).closest("div[class=div]").find("div[class=values]").find("input[name=cmtrId]").val();
		var notId = $(el).closest("div[class=div]").find("div[class=values]").find("input[name=notId]").val();
		var acvId = $(el).closest("div[class=div]").find("div[class=values]").find("input[name=acvId]").val();
		var qstId = $(el).closest("div[class=div]").find("div[class=values]").find("input[name=qstId]").val();
		if (qstId != "") {
			$form.find("input[name='notId']").val(notId);
			$form.find("input[name='acvId']").val(acvId);
			$form.find("input[name='cmtrId']").val(cmtrId);
			$form.find("input[name='qstId']").val(qstId);
			$form.attr("action", "/front/review/question").submit();
		} else {
			commonModalPopup("문제번호가 없습니다.");
			return false;
		}
	}

	// 해설보기 페이지 이동
	function goCommentary(el) {
		var cmtrId = $(el).closest("div[class=div]").find("div[class=values]").find("input[name=cmtrId]").val();
		var notId = $(el).closest("div[class=div]").find("div[class=values]").find("input[name=notId]").val();
		var acvId = $(el).closest("div[class=div]").find("div[class=values]").find("input[name=acvId]").val();
		var qstId = $(el).closest("div[class=div]").find("div[class=values]").find("input[name=qstId]").val();
		if (cmtrId != "") {
			$form.find("input[name='notId']").val(notId);
			$form.find("input[name='acvId']").val(acvId);
			$form.find("input[name='cmtrId']").val(cmtrId);
			$form.find("input[name='qstId']").val(qstId);
			$form.attr("action", "/front/commentary/commentaryDetail").submit();
		} else {
			commonModalPopup("해설번호가 없습니다.");
			return false;
		}
	}

	// 개념보기 페이지 이동
	function goNotion(el) {
		var cmtrId = $(el).closest("div[class=div]").find("div[class=values]").find("input[name=cmtrId]").val();
		var notId = $(el).closest("div[class=div]").find("div[class=values]").find("input[name=notId]").val();
		var acvId = $(el).closest("div[class=div]").find("div[class=values]").find("input[name=acvId]").val();
		var qstId = $(el).closest("div[class=div]").find("div[class=values]").find("input[name=qstId]").val();
		if (qstId != "") {
			$form.find("input[name='notId']").val(notId);
			$form.find("input[name='acvId']").val(acvId);
			$form.find("input[name='cmtrId']").val(cmtrId);
			$form.find("input[name='qstId']").val(qstId);
			$form.attr("action", "/front/review/notion").submit();
		} else {
			commonModalPopup("문제번호가 없습니다.");
			return false;
		}
	}
</script>

<textarea id="txt_reviewList" style="display: none;">
	<div class="div" onclick="goReviewQuestion(this);">
		<div class="ov_h">
			<p class="fL">
				<span class="tit"></span>
				<span class="date"></span>
			</p>
			<strong class="ico_O fR"></strong>
		</div>
		<div class="values">
			<input type="hidden" name="qstId" value="" />
			<input type="hidden" name="cmtrId" value="" />
			<input type="hidden" name="notId" value="" />
			<input type="hidden" name="acvId" value="" />
		</div>
		<div class="btn_div">
			<button class="btn btn_st2" onclick="goReviewQuestion(this);">
				문제보기
			</button>
			<button class="btn btn_st2" onclick="goCommentary(this);">
				해설보기
			</button>
			<button class="btn btn_st2" onclick="goNotion(this);">
				개념보기
			</button>
		</div>
	</div>
</textarea>