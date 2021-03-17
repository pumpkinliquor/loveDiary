<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>

<body class="study_body2">
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>성취기준</h1>
		</header>
		<div class="container btm0">
			<div class="mystandard_conts">
				<div class="conts_top">
					<p class="txt1">
						<span><em><c:choose>
									<c:when test="${!empty userInfo.memNickname }">
										<c:out value="${userInfo.memNickname }" />
									</c:when>
									<c:otherwise>
										<c:out value="${userInfo.memEmail }" />
									</c:otherwise>
								</c:choose></em>님</span>LEVEL. ${loginSessionLevel } 학습할 성취 요소를 선택하세요.
					</p>
					<p class="txt1">AI가 성취요소별 맞춤 문제를 제공합니다.</p>
				</div>

				<%-- 조회 데이터 Set --%>
				<c:set var="mathI" value="${resultMap.resultData.mathI }" />
				<c:set var="mathII" value="${resultMap.resultData.mathII }" />
				<c:set var="mathOptional" value="${resultMap.resultData.mathOptional }" />
				<c:set var="mathOptionalInfo" value="${resultMap.resultData.mathOptionalInfo }" />

				<!-- 수학 I -->
				<c:if test="${not empty mathI }">
					<%-- 과목별 푼 문제 계산 --%>
					<c:set var="totalQstCnt" value="0" />
					<c:set var="totalAnsCnt" value="0" />
					<c:forEach var="row" items="${mathI }">
						<c:set var="totalQstCnt" value="${totalQstCnt + row.qstCnt }" />
						<c:set var="totalAnsCnt" value="${totalAnsCnt + row.ansCnt }" />
					</c:forEach>
					<div class="subject_div">
						<div>
							<span class="subject_tit">수학 I</span>
						</div>
						<p>
							총
							<fmt:formatNumber value="${totalQstCnt }" pattern="#,###" />
							개의 수학I 문제 중 내 수준에 맞는
							<fmt:formatNumber value="${totalAnsCnt }" pattern="#,###" />
							개의 문제를 풀었습니다.
						</p>
					</div>
					<ul class="guide_div">
						<c:forEach var="row" items="${mathI }" varStatus="status">
							<!-- 학습상태 설정 -->
							<c:set var="badgeClass" value="bdg3" />
							<c:set var="badgeName" value="학습 전" />
							<c:choose>
								<c:when test="${row.qstCnt eq row.ansCnt }">
									<c:set var="badgeClass" value="bdg2" />
									<c:set var="badgeName" value="학습완료" />
								</c:when>
								<c:when test="${row.qstCnt ne row.ansCnt and row.ansCnt gt 0}">
									<c:set var="badgeClass" value="bdg1" />
									<c:set var="badgeName" value="학습중" />
								</c:when>
							</c:choose>
							<li>
								<div>
									<span class="badge <c:out value="${badgeClass }"/>"><c:out value="${badgeName }" /></span> <span class="badge bdgA">성취도 <em><fmt:formatNumber value="${row.ansCnt / row.qstCnt}" type="percent" /></em></span>
								</div>
								<div class="ov_h">
									<a href="javascript:"> <c:choose>
											<c:when test="${row.qstCnt eq row.ansCnt }">
												<p onclick="goReview();">
													<c:out value="${row.acvName }" />
													<br />문제풀이를 시작하세요
												</p>
											</c:when>
											<c:when test="${row.qstCnt ne row.ansCnt and row.ansCnt gt 0}">
												<p onclick="goLearning('<c:out value="${row.acvId }"/>','<c:out value="${row.subId }"/>','<c:out value="${row.acvName }"/>');">
													<c:out value="${row.acvName }" />
													<br />문제풀이를 시작하세요
												</p>
											</c:when>
											<c:otherwise>
												<p onclick="goAchieveDetail('<c:out value="${row.acvId }"/>');">
													<c:out value="${row.acvName }" />
													<br />문제풀이를 시작하세요
												</p>
											</c:otherwise>
										</c:choose>
									</a> <a href="javascript:" class="btn btn_st2" onclick="goConceptList('<c:out value="${row.acvId }"/>'); return false;">개념학습</a>
								</div>
							</li>
						</c:forEach>
					</ul>
				</c:if>

				<!-- 수학 II -->
				<c:if test="${not empty mathII }">
					<%-- 과목별 푼 문제 계산 --%>
					<c:set var="totalQstCnt" value="0" />
					<c:set var="totalAnsCnt" value="0" />
					<c:forEach var="row" items="${mathII }">
						<c:set var="totalQstCnt" value="${totalQstCnt + row.qstCnt }" />
						<c:set var="totalAnsCnt" value="${totalAnsCnt + row.ansCnt }" />
					</c:forEach>
					<div class="subject_div mt_1">
						<div>
							<span class="subject_tit">수학 II</span>
						</div>
						<p>
							총
							<fmt:formatNumber value="${totalQstCnt }" pattern="#,###" />
							개의 수학I 문제 중 내 수준에 맞는
							<fmt:formatNumber value="${totalAnsCnt }" pattern="#,###" />
							개의 문제를 풀었습니다.
						</p>
					</div>
					<ul class="guide_div">
						<c:forEach var="row" items="${mathII }" varStatus="status">
							<!-- 학습상태 설정 -->
							<c:set var="badgeClass" value="bdg3" />
							<c:set var="badgeName" value="학습 전" />
							<c:choose>
								<c:when test="${row.qstCnt eq row.ansCnt }">
									<c:set var="badgeClass" value="bdg2" />
									<c:set var="badgeName" value="학습완료" />
								</c:when>
								<c:when test="${row.qstCnt ne row.ansCnt and row.ansCnt gt 0}">
									<c:set var="badgeClass" value="bdg1" />
									<c:set var="badgeName" value="학습중" />
								</c:when>
							</c:choose>
							<li>
								<div>
									<span class="badge <c:out value="${badgeClass }"/>"><c:out value="${badgeName }" /></span> <span class="badge bdgA">성취도 <em><fmt:formatNumber value="${row.ansCnt / row.qstCnt}" type="percent" /></em></span>
								</div>
								<div class="ov_h">
									<a href="javascript:"> <c:choose>
											<c:when test="${row.qstCnt eq row.ansCnt }">
												<p onclick="goReview();">
													<c:out value="${row.acvName }" />
													<br />문제풀이를 시작하세요
												</p>
											</c:when>
											<c:when test="${row.qstCnt ne row.ansCnt and row.ansCnt gt 0}">
												<p onclick="goLearning('<c:out value="${row.acvId }"/>','<c:out value="${row.subId }"/>','<c:out value="${row.acvName }"/>');">
													<c:out value="${row.acvName }" />
													<br />문제풀이를 시작하세요
												</p>
											</c:when>
											<c:otherwise>
												<p onclick="goAchieveDetail('<c:out value="${row.acvId }"/>');">
													<c:out value="${row.acvName }" />
													<br />문제풀이를 시작하세요
												</p>
											</c:otherwise>
										</c:choose>
									</a> <a href="javascript:" class="btn btn_st2" onclick="goConceptList('<c:out value="${row.acvId }"/>'); return false;">개념학습</a>
								</div>
							</li>
						</c:forEach>
					</ul>
				</c:if>

				<!-- 선택과목 -->
				<c:if test="${not empty mathOptionalInfo }">
					<%-- 과목별 푼 문제 계산 --%>
					<c:set var="totalQstCnt" value="0" />
					<c:set var="totalAnsCnt" value="0" />
					<c:forEach var="row" items="${mathOptional }">
						<c:set var="totalQstCnt" value="${totalQstCnt + row.qstCnt }" />
						<c:set var="totalAnsCnt" value="${totalAnsCnt + row.ansCnt }" />
					</c:forEach>
					<div class="subject_div mt_1">
						<div>
							<span class="subject_tit"><c:out value="${mathOptionalInfo.subName }" /></span>
						</div>
						<p>
							총
							<fmt:formatNumber value="${totalQstCnt }" pattern="#,###" />
							개의 수학I 문제 중 내 수준에 맞는
							<fmt:formatNumber value="${totalAnsCnt }" pattern="#,###" />
							개의 문제를 풀었습니다.
						</p>
					</div>
					<ul class="guide_div">
						<c:forEach var="row" items="${mathOptional }" varStatus="status">
							<!-- 학습상태 설정 -->
							<c:set var="badgeClass" value="bdg3" />
							<c:set var="badgeName" value="학습 전" />
							<c:choose>
								<c:when test="${row.qstCnt eq row.ansCnt }">
									<c:set var="badgeClass" value="bdg2" />
									<c:set var="badgeName" value="학습완료" />
								</c:when>
								<c:when test="${row.qstCnt ne row.ansCnt and row.ansCnt gt 0}">
									<c:set var="badgeClass" value="bdg1" />
									<c:set var="badgeName" value="학습중" />
								</c:when>
							</c:choose>
							<li>
								<div>
									<span class="badge <c:out value="${badgeClass }"/>"><c:out value="${badgeName }" /></span> <span class="badge bdgA">성취도 <em><fmt:formatNumber value="${row.ansCnt / row.qstCnt}" type="percent" /></em></span>
								</div>
								<div class="ov_h">
									<a href="javascript:"> <c:choose>
											<c:when test="${row.qstCnt eq row.ansCnt }">
												<p onclick="goReview('<c:out value="${row.acvId }"/>');">
													<c:out value="${row.acvName }" />
													<br />문제풀이를 시작하세요
												</p>
											</c:when>
											<c:when test="${row.qstCnt ne row.ansCnt and row.ansCnt gt 0}">
												<p onclick="goLearning('<c:out value="${row.acvId }"/>','<c:out value="${row.subId }"/>','<c:out value="${row.acvName }"/>');">
													<c:out value="${row.acvName }" />
													<br />문제풀이를 시작하세요
												</p>
											</c:when>
											<c:otherwise>
												<p onclick="goAchieveDetail('<c:out value="${row.acvId }"/>');">
													<c:out value="${row.acvName }" />
													<br />문제풀이를 시작하세요
												</p>
											</c:otherwise>
										</c:choose>
									</a> <a href="javascript:" class="btn btn_st2" onclick="goConceptList('<c:out value="${row.acvId }"/>'); return false;">개념학습</a>
								</div>
							</li>
						</c:forEach>
					</ul>
				</c:if>

			</div>
		</div>
		<!-- //container -->
	</div>
</body>

<form id="form" name="form" method="post">
	<input type="hidden" name="subId" value="" />
	<input type="hidden" name="acvId" value="" />
	<input type="hidden" name="levId" value="<c:out value="${loginSessionLevel}"/>" />
	<input type="hidden" name="currentTestType" value="" />
	<%-- 풀고있는 평가종류 / 추후 개선하여 삭제 예정 --%>
	<input type="hidden" name="currentTestTypeSub" value="" />
	<%-- 풀고있는 평가종류 / 추후 개선하여 삭제 예정 --%>
</form>

<script type="text/javascript">
	var $form = $("#form");

	$(document).ready(function() {

	});

	// 학습목표 상세페이지 이동
	function goAchieveDetail(acvId) {
		$form.find("input[name='acvId']").val(acvId);
		$form.attr("action", "/front/achieve/detail").submit();
	}

	// 개념학습 리스트 페이지 이동
	function goConceptList(acvId) {
		$form.find("input[name='acvId']").val(acvId);
		$form.attr("action", "/front/concept/conceptList").submit();
	}

	// 다시풀기 리스트 페이지 이동
	function goReview(acvId) {
		$form.find("input[name='acvId']").val(acvId);
		$form.attr("action", "/front/review/list").submit();
	}

	// 학습중일 경우 문제풀기 페이지 바로이동
	function goLearning(acvId, subId, acvName) {

		$form.find("input[name='subId']").val(subId);
		$form.find("input[name='acvId']").val(acvId);
		$form.find("input[name='currentTestTypeSub']").val("basic");

		var param = $form.domJson();

		$.call('/front/ajax/learn/checkLearnProgress', param, function(r) {
			var resultCode = r.resultData.resultCode;
			if (resultCode == 'S01') {
				commonModalPopup('<h3>' + acvName + '</h2><br/>학습이 완료되었습니다.<br/>Aigo 주간 리포트에서 나의 평가 결과와<br/>학습 분석 내용을 확인하세요.');
				$(".popupConfirm").click({}, goReportWeekly);
			} else if (resultCode == 'S02') {
				$form.find("input[name='currentTestType']").val("weekly");
				commonModalPopup('<h3>' + acvName + '</h2><br/>해당하는 모든 문제를 풀었습니다.<br/>Aigo 맞춤 주간평가를 통해<br/>성취 진단을 진행합니다.');
				$(".popupConfirm").text("AI 주간평가 Go");
				$(".popupConfirm").click({}, goWeeklyTest);
			} else if (resultCode == 'S03') {
				$form.find("input[name='currentTestType']").val("prev");
				$form.attr("action", "/front/learn/question").submit();
			} else {

			}
		});

	}

	//주간평가 페이지 이동
	function goWeeklyTest() {
		$form.attr("action", "/front/learn/weeklyTest").submit();
	}
</script>