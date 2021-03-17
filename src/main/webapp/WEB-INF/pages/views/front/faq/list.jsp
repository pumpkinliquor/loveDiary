<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>
<c:set var="faqList" value="${resultMap.resultData.faqList }" />

<body>
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>FAQ</h1>
		</header>
		<div class="container cont_box">
			<div class="contents">
				<div class="faq_wrap">
					<div class="srch_div">
						<input type="text" name="keyword" class="ipt_srch" placeholder="궁금한 내용을 검색 해 보세요.">
						<button class="btn_ico_srch" onclick="srchClick();" style="display: none;">
							<span>검색</span>
						</button>
					</div>
					<ul class="faq_list_cont">
						<c:if test="${!empty faqList}">
							<c:forEach var="fgr" items="${faqList }" varStatus="vs">
								<li class=""><a href="javascript:void(0)" class="faq_tit1"><c:out value="${fgr.fgrTitle }" /></a> <c:set var="faqIdList" value="${fn:split(faqList[vs.index].faqIdGroup,',') }" /> <c:set var="faqTitleList" value="${fn:split(faqList[vs.index].faqTitleGroup,',') }" /> <c:if test="${!empty faqIdList }">
										<ul class="faq_2depth">
											<c:forEach var="faq" items="${faqIdList }" varStatus="faqVs">
												<li class="faq_tit2"><a href="javascript:void(0)" onclick="goFaqDetail('<c:out value="${fgr.bbSeq }"/>', '<c:out value="${fgr.fgrId }"/>', '<c:out value="${faq }"/>'); return false;">${faqTitleList[faqVs.index] }</a></li>
											</c:forEach>
										</ul>
									</c:if></li>
							</c:forEach>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
		<!-- //container -->
	</div>
</body>
<form id="listFrm" name="listFrm" method="post">
	<input type="hidden" name="word" value="${paramMap.word }" />
</form>
<script type="text/javascript" charset="UTF-8" src="/assets/js/public/default.js"></script>
<script src="/assets/plugin/owlcarousel/owl.carousel.min.js"></script>
<script type="text/javascript">
	faqList();

	// 다시풀기 문제페이지 이동
	function goFaqDetail(bbSeq, fgrId, faqId) {
		$.formSubmit("/front/faq/detail", $.extend(null, {
			"bbSeq" : bbSeq,
			"fgrId" : fgrId,
			"faqId" : faqId
		}), {
			method : 'post'
		});
	}

	$(document).on("keydown", "[name=keyword]", function(e) {
		if (e.keyCode == 13) {
			srchClick();
		}
	});

	function srchClick() {
		if(validateFunc()) {
			$("input[name=word]").val($("input[name=keyword]").val());
			$(".contents").empty();
			$(".container.cont_box").addClass("faq");
	
			//ajax로 처리
			$.call('/front/ajax/faq/srchList', $("[id=listFrm]").serializeObject(), function(r) {
				changeListFunc(r.resultData.srchList);
			});
		}
	};

	function changeListFunc(r) {
		if (r.length <= 0) {
			$(".contents").append($("#noneList").val());
			$(".contents").find(".keyword_box").text($("input[name=word]").val());
			$(".contents").find(".result_txt").append("<em>\'" + $("input[name=word]").val() + "\'</em><br>에 대한 검색결과가 없습니다.");
		} else {
			$(".contents").append($("#srchList").val());
			$(".contents").find(".keyword_box").text($("input[name=word]").val());
			$(".contents").find(".result_txt").append("<em>\'" + $("input[name=word]").val() + "\'</em><br>에 대한 " + r.length + "개의 검색결과가 있습니다.");
			for (var i = 0; i < r.length; i++) {
				$(".contents").find(".result_list").append($("#liList").val());
				$(".contents").find(".result_list").find("li:eq( " + i + " )").find("a").empty();
				var replaceTitle = r[i].faqTitle.replaceAll($("input[name=word]").val(), "<em>" + $("input[name=word]").val() + "</em>");
				$(".contents").find(".result_list").find("li:eq( " + i + " )").find("a").append(replaceTitle);
				$(".contents").find(".result_list").find("li:eq( " + i + " )").find("a").attr("onclick", "goFaqDetail(" + r[i].fgrId + ", " + r[i].faqId + ");");
			}
		}
	}

	$(document).mouseup(function(e) {
		var focusEl = $(".srch_div");
		if (focusEl.has(e.target).length === 0) {
			focusEl.removeClass("active");
			focusEl.find("button").css("display", "none");
			focusEl.find("input").attr("placeholder", "궁금한 내용을 검색 해 보세요.");
		} else {
			focusEl.addClass("active");
			focusEl.find("button").css("display", "");
			focusEl.find("input").removeAttr("placeholder");
		}
	});
	
	$(document).on("focusin", ".srch_div", function() {
		$(this).addClass("active");
		$(this).find("button").css("display", "");
		$(this).find("input").removeAttr("placeholder");
	});
	
	function validateFunc() {
		var $keyword = $("input[name=keyword]").val();
		var flag = true;
		if($keyword == "") {
			commonModalPopup('<b>검색어를 입력하세요.</b>');
			flag = false;
		}
		
		return flag;
	}
</script>

<!-- 상단 container 에 faq 클래스추ㅏㄱ -->
<textarea id="noneList" style="display: none">
	<div class="keyword_box"></div>
	<p class="result_txt">
	</p>
</textarea>

<!-- 상단 container 에 faq 클래스추ㅏㄱ -->
<textarea id="srchList" style="display: none">
	<div class="keyword_box"></div>
	<p class="result_txt">
	</p>
	<ul class="result_list">
	</ul>
</textarea>

<textarea id="liList" style="display: none;">
	<li><a href="javascript:void(0)"><em></em></a></li>
</textarea>