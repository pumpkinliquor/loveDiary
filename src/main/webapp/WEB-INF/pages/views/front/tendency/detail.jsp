<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>

<body class="study_body2">
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>출제경향</h1>
		</header>
		<div class="container btm0">
			<div class="goal_conts">
				<div class="conts_top">
					<p class="txt1">
						<span><em><c:choose>
									<c:when test="${!empty userInfo.memNickname }">
										<c:out value="${userInfo.memNickname }" />
									</c:when>
									<c:otherwise>
										<c:out value="${userInfo.memEmail }" />
									</c:otherwise>
								</c:choose></em>님이</span> <b>LEVEL ${loginSessionLevel}</b> <b>${resultMap.resultData.detail[0].acvName}</b> 연관된 출제경향을 파악하세요.
					</p>
				</div>
				<div class="conts_sec">
					<c:if test="${!empty resultMap.resultData.detail}">
						<c:forEach var="l" items="${resultMap.resultData.detail }">
							<c:out value="${l.tenText}" escapeXml="false" />
							<br>
						</c:forEach>
					</c:if>
					<br>
				</div>
				<div class="btm_btn_div2">
					<a href="/front/achieve/list" class="prev"><span>이전</span></a> <a href="#!" class="next" onclick="goLearning();"><span>다음</span></a>
				</div>
			</div>
		</div>
	</div>

</body>

<form id="form" name="form" method="post">
	<input type="hidden" name="subId" value="<c:out value="${resultMap.resultData.detail[0].subId}"/>" />
	<input type="hidden" name="acvId" value="<c:out value="${resultMap.resultData.detail[0].acvId}"/>" />
	<input type="hidden" name="levId" value="<c:out value="${loginSessionLevel}"/>" />
	<input type="hidden" name="currentTestType" value="" />
	<%-- 풀고있는 평가종류 / 추후 개선하여 삭제 예정 --%>
	<input type="hidden" name="currentTestTypeSub" value="" />
	<%-- 풀고있는 평가종류 / 추후 개선하여 삭제 예정 --%>
</form>

<script type="text/javascript">
	var $form = $("#form");

	$(document).ready(function() {
		// 	console.log("${resultMap.resultData[0].detail}");
		//다 보여줘야됨
		$(".conts_sec").css("max-height", "inherit");
	});

	function goLearning() {

		var param = $form.domJson();
		$form.find("input[name='currentTestTypeSub']").val("basic");

		$.call('/front/ajax/learn/checkLearnProgress', param, function(r) {
			var resultCode = r.resultData.resultCode;
			if (resultCode == 'S01') {
				commonModalPopup('<h3>${resultMap.resultData.detail[0].acvName}</h2><br/>학습이 완료되었습니다.<br/>Aigo 주간 리포트에서 나의 평가 결과와<br/>학습 분석 내용을 확인하세요.');
				$(".popupConfirm").click({}, goReportWeekly);
			} else if (resultCode == 'S02') {
				$form.find("input[name='currentTestType']").val("weekly");
				commonModalPopup('<h3>${resultMap.resultData.detail[0].acvName}</h2><br/>해당하는 모든 문제를 풀었습니다.<br/>Aigo 맞춤 주간평가를 통해<br/>성취 진단을 진행합니다.');
				$(".popupConfirm").text("AI 주간평가 Go");
				$(".popupConfirm").click({}, goWeeklyTest);
			} else if (resultCode == 'S03') {
				$form.find("input[name='currentTestType']").val("prev");
				$form.attr("action", "/front/learn/question").submit();
			} else {

			}
		});

	}

	// 주간평가 페이지 이동
	function goWeeklyTest() {
		$form.attr("action", "/front/learn/weeklyTest").submit();
	}

	// 주간 리포트 페이지 이동
	function goReportWeekly() {
		$form.attr("action", "/front/report/weekReport").submit();
	}
</script>