<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@ page import="java.util.Enumeration"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>
<c:set var="paramLevId" value="${resultMap.resultData.levId }" />
<c:set var="levDtl" value="${resultMap.resultData.levelMap }" />
<c:set var="levList" value="${resultMap.resultData.levelList }" />
<c:set var="levReportList" value="${resultMap.resultData.levReportList }" />

<%
	Enumeration<String> attrNames = request.getAttributeNames();
%>

<body class="study_body">
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>레벨평가 상세 문항분석</h1>
		</header>
		<div class="container study_analysis">
			<section class="contents">
				<div class="tab_wrap">
					<div class="tab_menu_wrap">
						<div class="tab_menu" id="levelTab">
							<if test="${!empty levList }">
								<c:forEach var="levL" items="${levList }" varStatus="vs">
									<input type="hidden" name="levId" value="${levL.levId }"/>
									<button class="tab_m${vs.count } tab_m <c:if test="${paramLevId eq levL.levId }">active</c:if>" type="button"><c:out value="${levL.levName }"/></button>
								</c:forEach>
							</if>
						</div>
					</div>
					<div class="tab_cont_wrap" id="levelTabCont">
						<div class="tab_cont1 tab_cont active">
							<div class="tbl_wrap">
								<table id="studyT" class="studyT table table-bordered table-hover dataTable dtr-inline">
									<colgroup>
										<col width="55px">
						                <col width="45px">
						                <col width="80px">
						                <col width="45px">
						                <col width="60px">
						                <col width="60px">
						                <col width="65px">
						                <col width="75px">
									</colgroup>
									<thead>
										<tr>
											<th class="sticky-col first-col"><span>번호</span></th>
											<th class="sticky-col second-col"><span>과목</span></th>
											<th><span>개념요소</span></th>
											<th><span>채점<br>결과
											</span></th>
											<th><span>정답율</span></th>
											<th><span>난이도</span></th>
											<th><span>가장<br>빈번한<br>오답
											</span></th>
											<th><span>상위50%<br>정답율
											</span></th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${!empty levReportList }">
											<c:forEach var="q" items="${levReportList }">
												<tr>
													<td class="sticky-col first-col">
														<input type="hidden" name="qstId" value="${q.qstId }" />
														<a href="javascript:void(0)" onclick="goQuestion(this);">${q.qstId }</a>
													</td>
													<td class="sticky-col second-col"><c:out value="${q.subName }" /></td>
													<td><c:out value="${q.cptNmStr }" /></td>
													<c:choose>
														<c:when test="${q.qstValue eq '1' }">
															<c:set var="spNum" value="①" />
														</c:when>
														<c:when test="${q.qstValue eq '2' }">
															<c:set var="spNum" value="②" />
														</c:when>
														<c:when test="${q.qstValue eq '3' }">
															<c:set var="spNum" value="③" />
														</c:when>
														<c:when test="${q.qstValue eq '4' }">
															<c:set var="spNum" value="④" />
														</c:when>
														<c:when test="${q.qstValue eq '5' }">
															<c:set var="spNum" value="⑤" />
														</c:when>
														<c:otherwise>
															<c:set var="spNum" value="${q.qstValue}" />
														</c:otherwise>
													</c:choose>
													<td <c:if test="${q.passYn eq 'n'}">class="fc_R"</c:if>>
														<c:if test="${q.passYn eq 'n'}">X ${spNum }</c:if>
														<c:if test="${q.passYn eq 'y'}">O</c:if>
													</td>
													<td>
														<fmt:formatNumber value="${q.allPer }" type="percent" />
													</td>
													<td>
														<c:out value="${q.testLev }" />
													</td>
													<td>
														<c:if test="${q.maxFalseValue ne null}">${q.maxFalseValue }</c:if>
														<c:if test="${q.maxFalseValue eq null || q.maxFalseValue eq ''}">-</c:if>
													</td>
													<td>
														<c:if test="${q.topPer ne null}">
															<fmt:formatNumber value="${q.topPer }" type="percent" />
														</c:if>
														<c:if test="${q.topPer eq null || q.topPer eq ''}">
																-
															</c:if>
													</td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>

				<p class="txt_st1">※ 난이도는 A가 가장 어렵고 E가 가장 쉬운 문항을 의미합니다.</p>

			</section>
		</div>
		<!-- //container -->

	</div>
</body>

<script type="text/javascript" charset="UTF-8" src="/assets/js/public/default.js"></script>
<script type="text/javascript">
	levelTab();
	
	$(document).ready(function() {
// 		console.log("${resultMap.resultData.levId }");
// 		console.log("${resultMap.resultData.levelMap }");
// 		console.log("${resultMap.resultData.levelList }");
// 		console.log("${resultMap.resultData.levReportList }");
	});

	$(function() {
		$('#studyT').DataTable({
			"paging" : false,
			"lengthChange" : false,
			"searching" : false,
			"ordering" : true,
			"info" : false,
			"autoWidth" : false,
			"responsive" : false,
		});
	});
	
	//다시풀기 문제페이지 이동
	function goQuestion(el) {
		var qstId = $(el).closest("td").find("input[name=qstId]").val();
		if (qstId != "") {
			$.formSubmit("/front/review/question", $.extend(null, {
				"qstId" : qstId
			}), {
				method : 'post'
			});
		} else {
			commonModalPopup("문제번호가 없습니다.");
			return false;
		}
	}
	
	$(document).on("click", "button[class^=tab_m]", function(e) {
		var levId = $(this).prev("input").val();
		if (levId != "") {
			$.formSubmit("/front/report/totalReportLevel", $.extend(null, {
				"levId" : levId
			}), {
				method : 'post'
			});
		} else {
			commonModalPopup("레벨 번호가 없습니다.");
			return false;
		}
	});
</script>