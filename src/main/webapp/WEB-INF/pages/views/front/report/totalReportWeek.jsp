<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@ page import="java.util.Enumeration"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>
<c:set var="requestParam" value="<%=new ObjectMapper().writeValueAsString(request.getParameterMap())%>" />
<c:set var="a" value="${resultMap.resultData.acvList}" />
<c:set var="ql" value="${resultMap.resultData.weekAcvList}" />
<%
	Enumeration<String> attrNames = request.getAttributeNames();
%>

<body class="study_body">
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>주간학습 상세 문항분석</h1>
		</header>
		<div class="container study_analysis">
			<section class="contents">
				<a href="javascript:void(0)" class="btn btn_select mb" onclick="choiceAcv();"><span>성취기준 선택</span></a>
				<div class="tbl_wrap">
						<table id="studyT" class="studyT table table-bordered table-hover dataTable dtr-inline">
							<colgroup>
								<col width="70px">
								<col width="70px">
								<col width="150px">
								<col width="70px">
								<col width="70px">
								<col width="70px">
								<col width="70px">
								<col width="70px">
							</colgroup>
							<thead>
								<tr role="row">
									<th class="sticky-col first-col"><span>구분</span></th>
									<th class="sticky-col second-col"><span>번호</span></th>
									<th><span>개념요소</span></th>
									<th><span>채점<br>결과</span></th>
									<th><span>정답율</span></th>
									<th><span>난이도</span></th>
									<th><span>가장<br>빈번한<br>오답</span></th>
									<th><span>상위50%<br>정답율</span></th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${!empty ql }">
									<c:forEach var="q" items="${ql }">
										<tr role="row" class="odd">
											<td class="sticky-col first-col"><c:out value="${q.acaName }"/></td>
											<td class="sticky-col second-col">
												<input type="hidden" name="qstId" value="${q.qstId }" />
												<a href="javascript:void(0)" onclick="goQuestion(this);">${q.qstId }</a>
											</td>
											<td><c:out value="${q.cptNmStr }"/></td>
											<c:choose>
												<c:when test="${q.qstValue eq '1' }">
													<c:set var="spNum" value="①"/>
												</c:when>
												<c:when test="${q.qstValue eq '2' }">
													<c:set var="spNum" value="②"/>
												</c:when>
												<c:when test="${q.qstValue eq '3' }">
													<c:set var="spNum" value="③"/>
												</c:when>
												<c:when test="${q.qstValue eq '4' }">
													<c:set var="spNum" value="④"/>
												</c:when>
												<c:when test="${q.qstValue eq '5' }">
													<c:set var="spNum" value="⑤"/>
												</c:when>
												<c:otherwise>
													<c:set var="spNum" value="${q.qstValue}"/>
												</c:otherwise>
											</c:choose>
											<td <c:if test="${q.passYn eq 'n'}">class="fc_R"</c:if>>
												<c:if test="${q.passYn eq 'n'}">X ${spNum }</c:if>
												<c:if test="${q.passYn eq 'y'}">O</c:if>
											</td>
											<td><fmt:formatNumber value="${q.allPer }" type="percent" /></td>
											<td><c:out value="${q.testLev }"/></td>
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
				<p class="txt_st1">※ 난이도는 A가 가장 어렵고 E가 가장 쉬운 문항을 의미합니다.</p>

			</section>
		</div>
		<!-- //container -->

	</div>

	<div class="modal-wrap">
		<div class="modal">
			<div class="pop_cont">
				<ul class="select_div">
					<c:if test="${!empty a }">
						<c:forEach var="l" items="${a }" varStatus="vs">
							<li class="ipt_radio_div">
								<input type="radio" id="radio_acvId${vs.index }" name="acvId" value="${l.acvId }" <c:if test="${resultMap.resultData.weekAcvList[0].acvId eq l.acvId}">checked</c:if>/>
								<label for="radio_acvId${vs.index }">
									<span><c:out value="${l.acvName }"/></span>
								</label>
							</li>
						</c:forEach>
					</c:if>
				</ul>
			</div>

		</div>
	</div>
	
	<form id="acvFrm" name="acvFrm" method="post">
		<input type="hidden" id="frm_acvId" name="acvId"/>
	</form>
	
	<script type="text/javascript" charset="UTF-8" src="/assets/js/public/default.js"></script>
	
	<script type="text/javascript" src="/assets/plugin/datatables/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="/assets/plugin/datatables/jquery.dataTables.js"></script>
	
	<script type="text/javascript">
		modalClose();
		
		$(document).ready(function() {
			console.log("${resultMap.resultData.acvList}");
// 			console.log("${resultMap.resultData.weekAcvList}");
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
		
		function choiceAcv() {
			$("ul[class=select_div]").closest(".modal-wrap").addClass("is-visible");
		}
		
		$(document).mouseup(function(e) {
			var LayerPopup = $("ul[class=select_div]").closest(".modal");
			if (LayerPopup.closest(".modal-wrap").is(".is-visible")) {
				if (LayerPopup.has(e.target).length === 0) {
					LayerPopup.closest(".modal-wrap").removeClass("is-visible");
					if ($(":radio[name=acvId]").is(":checked")) {
						$("#frm_acvId").val($(":radio[name=acvId]:checked").val());
						//동기처리
// 						$.formSubmit("/front/report/totalReportWeek", $.extend($("[id=acvFrm]").serializeObject(), {
// 							"acvId" : $("#frm_acvId").val()
// 						}), {
// 							method : 'post'
// 						});

						//ajax로 처리
// 						$.call('/front/ajax/report/totalWeekList', $("[id=acvFrm]").serializeObject(), function(r) {
// 							$("#studyT tbody").empty();
// 							changeListFunc(r.resultData.weekAcvList);
// 						});
					}
				}
			}
		});
		
		// 바뀔 때
		$(document).on("change", ":radio[name=acvId]", function() {
			var LayerPopup = $("ul[class=select_div]").closest(".modal");
			LayerPopup.closest(".modal-wrap").removeClass("is-visible");
			$("#frm_acvId").val($(":radio[name=acvId]:checked").val());
			$.formSubmit("/front/report/totalReportWeek", $.extend($("[id=acvFrm]").serializeObject(), {
				"acvId" : $("#frm_acvId").val()
			}), {
				method : 'post'
			});
		});
		
// 		function changeListFunc(r) {
// 			for (var i = 0; i < r.length; i++) {
// 				var addClass = (r[i].passYn == "n") ? "fc_R" : "";
// 				var falseValue = (r[i].maxFalseValue != null && r[i].maxFalseValue != "") ? r[i].maxFalseValue : "-";
// 				var allPer = (r[i].allPer != null && r[i].allPer != "") ? r[i].allPerStr + "%" : "-";
// 				var topPer = (r[i].topPer != null && r[i].topPer != "") ? r[i].topPerStr + "%" : "-";
// 				var spNum = (r[i].passYn == "n") ? ((r[i].qstValue == "1") ? "①" : (r[i].qstValue == "2") ? "②" : (r[i].qstValue == "3") ? "③" : (r[i].qstValue == "4") ? "④" : (r[i].qstValue == "5") ? "⑤" : r[i].qstValue) : "";
// 				$("#studyT tbody").append($("[id=txt_acvList]").val());
// 				$("#studyT tbody").find("tr:eq(" + i + ")").find("td[id=td_acaName]").text(r[i].acaName);
// 				$("#studyT tbody").find("tr:eq(" + i + ")").find("td[id=td_qstNo]").find("a").text(r[i].qstId);
// 				$("#studyT tbody").find("tr:eq(" + i + ")").find("td[id=td_qstNo]").find("input").val(r[i].qstId);
// 				$("#studyT tbody").find("tr:eq(" + i + ")").find("td[id=td_cptName]").text(r[i].cptNmStr);
// 				$("#studyT tbody").find("tr:eq(" + i + ")").find("td[id=td_spNum]").addClass(addClass);
// 				$("#studyT tbody").find("tr:eq(" + i + ")").find("td[id=td_spNum]").text((r[i].passYn == 'y') ? "O" : "X" + " " + spNum);
// 				$("#studyT tbody").find("tr:eq(" + i + ")").find("td[id=td_allPer]").text(allPer);
// 				$("#studyT tbody").find("tr:eq(" + i + ")").find("td[id=td_testLev]").text(r[i].testLev);
// 				$("#studyT tbody").find("tr:eq(" + i + ")").find("td[id=td_falseValue]").text(falseValue);
// 				$("#studyT tbody").find("tr:eq(" + i + ")").find("td[id=td_topPer]").text(topPer);
// 			}
// 		}
		
		// 다시풀기 문제페이지 이동
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
	</script>
	
<!-- 	<textarea id="txt_acvList" style="display:none;"> -->
<!-- 		<tr role="row" class="odd"> -->
<!-- 			<td class="sticky-col first-col" id="td_acaName"></td> -->
<!-- 			<td class="sticky-col second-col" id="td_qstNo"> -->
<!-- 				<input type="hidden" name="qstId" value="" /> -->
<!-- 				<a href="javascript:void(0)" onclick="goQuestion(this);"></a> -->
<!-- 			</td> -->
<!-- 			<td class="sorting_1" id="td_cptName"></td> -->
<!-- 			<td id="td_spNum"></td> -->
<!-- 			<td id="td_allPer"></td> -->
<!-- 			<td id="td_testLev"></td> -->
<!-- 			<td id="td_falseValue"></td> -->
<!-- 			<td id="td_topPer"></td> -->
<!-- 		</tr> -->
<!-- 	</textarea> -->