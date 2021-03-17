<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>
<c:set var="notiList" value="${resultMap.resultData.noticeList }" />

<body>
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>공지사항</h1>
		</header>
		<div class="container cont_box">
			<div class="contents">
				<div class="board_wrap">
					<h2>AIGo의 소식을 확인하세요</h2>
					<div class="board_list_cont">
						<ul id="notiUl">
							<c:if test="${!empty notiList }">
								<c:forEach var="noti" items="${notiList }">
									<li class="<c:if test="${noti.bbType eq 'TOP' }">active</c:if>"><a href="javascript:void(0)" onclick="goNoticeDetail('${noti.bbSeq}'); return false;"> <span class="tit"><c:out value="${noti.bbTitle }" /></span> <span class="date"><c:out value="${noti.fmtDate }" /></span>
									</a></li>
								</c:forEach>
							</c:if>
							<c:if test="${empty notiList }">
								<li class="none"><a href="#"><span class="tit">등록된 글이 없습니다.</span></a></li>
							</c:if>
						</ul>
						<button class="more" onclick="limitPage(); return false;">더보기</button>
					</div>
				</div>
			</div>
		</div>
		<!-- //container -->
	</div>
</body>
<form id="limitFrm" name="limitFrm" method="post">
	<input type="hidden" name="limitNum"/>
</form>

<script type="text/javascript" charset="UTF-8" src="/assets/js/public/default.js"></script>
<script src="/assets/plugin/owlcarousel/owl.carousel.min.js"></script>
<script type="text/javascript">
	eventBanner();
	
	$(document).ready(function() {
		if(Number("${resultMap.resultData.noticeList[0].bbsCnt }") < 20) {
			$("button[class=more]").remove();
		}
	});

	// 다시풀기 문제페이지 이동
	function goNoticeDetail(bbSeq) {
		$.formSubmit("/front/notice/detail", $.extend(null, {
			"bbSeq" : bbSeq
		}), {
			method : 'post'
		});
	}
	
	function limitPage() {
		$("input[name=limitNum]").val(Number($("ul[id=notiUl]").find("li").length) + 20);
		//ajax로 처리
		$.call('/front/ajax/notice/list', $("[id=limitFrm]").serializeObject(), function(r) {
			changeListFunc(r.resultData.noticeList);
		});
	}
	
	function changeListFunc(r) {
		$("ul[id=notiUl]").empty();
		for (var i = 0; i < r.length; i++) {
			$("ul[id=notiUl]").append($("#ajaxNotiList").val());
			if(r[i].bbType == "TOP") {
				$("ul[id=notiUl]").find("li:eq(" + i + ")").addClass("active");
			}
			$("ul[id=notiUl]").find("li:eq(" + i + ")").find("a").attr("onclick", "goNoticeDetail(" + r[i].bbSeq + "); return false;");
			$("ul[id=notiUl]").find("li:eq(" + i + ")").find("a").find("span[class=tit]").text(r[i].bbTitle);
			$("ul[id=notiUl]").find("li:eq(" + i + ")").find("a").find("span[class=date]").text(r[i].fmtDate);
		}
		if(r.length == r[0].bbsCnt) {
			$("button[class=more]").remove();
		}
	}
	
	
</script>

<textarea id="ajaxNotiList" style="display:none;">
	<li>
		<a href="javascript:void(0)">
			<span class="tit"></span>
			<span class="date"></span>
		</a>
	</li>
</textarea>