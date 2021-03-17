<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>

<body class="study_body2">
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>성취기준</h1>
		</header>
		<div class="container">
			<div class="mystandard_conts">
				<div class="conts_top">
					<p class="txt1">
						<span><em><c:out value="${loginSessionNickName }" /></em>님</span>LEVEL. ${resultMap.resultData.lv } 학습할 성취 요소를 선택하세요.
					</p>
				</div>

				<%-- 조회 데이터 Set --%>
				<c:set var="mathI" value="${resultMap.resultData.mathI }" />
				<c:set var="mathII" value="${resultMap.resultData.mathII }" />
				<c:set var="mathOptional" value="${resultMap.resultData.mathOptional }" />

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
				<c:if test="${not empty mathOptional }">
					<%-- 과목별 푼 문제 계산 --%>
					<c:set var="totalQstCnt" value="0" />
					<c:set var="totalAnsCnt" value="0" />
					<c:forEach var="row" items="${mathOptional }">
						<c:set var="totalQstCnt" value="${totalQstCnt + row.qstCnt }" />
						<c:set var="totalAnsCnt" value="${totalAnsCnt + row.ansCnt }" />
					</c:forEach>
					<div class="subject_div mt_1">
						<div>
							<span class="subject_tit">미적분</span>
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
									<a href="javascript:">
										<c:choose>
											<c:when test="${row.qstCnt eq row.ansCnt }">
												<p onclick="goReview();">
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
									</a>
									<a href="javascript:" class="btn btn_st2" onclick="goConceptList('<c:out value="${row.acvId }"/>'); return false;">개념학습</a>
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
	<input type="hidden" name="acvId" value="" />
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
</script>