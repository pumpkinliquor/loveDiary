<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@ page import="java.util.Enumeration"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>
<c:set var="requestParam" value="<%=new ObjectMapper().writeValueAsString(request.getParameterMap())%>" />
<c:set var="notionInfo" value="${resultMap.resultDataConcept.notionInfo[0]}" />
<%
	Enumeration<String> attrNames = request.getAttributeNames();
%>
<body class="study_body2">
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>개념보기</h1>
		</header>
		<div class="container btm0">
			<div class="question_conts comment_cont">
				<div class="q_top_section" id="qTopHeight">
					<div class="q_solve_top">
						<p class="q">${resultMap.resultDataConcept.detail.subName } Lv.${resultMap.resultDataConcept.detail.levId } ${resultMap.resultDataConcept.detail.acvName }</p>
						<div class="tag_div">
							<c:if test="${resultMap.resultDataConcept.detail.allCptId != null }">
								<c:set var="conceptIdList" value="${fn:split(resultMap.resultDataConcept.detail.allCptId,',')}" />
								<c:set var="acvIdList" value="${fn:split(resultMap.resultDataConcept.detail.allAcvId,',')}" />
								<c:set var="conceptNameList" value="${fn:split(resultMap.resultDataConcept.detail.allCptName,',')}" />
								<c:if test="${!empty conceptIdList }">
									<c:forEach var="val" items="${conceptIdList }" varStatus="vs">
										<a href="#" onclick="goConceptDetail('${val}', '${acvIdList[vs.index] }'); return false;" class="tag">${conceptNameList[vs.index] }</a>
									</c:forEach>
								</c:if>
							</c:if>
						</div>
						<div class="ov_h">
							<div class="aigo_rate">
								<span class="logo_img">AIGo</span><span class="txt">정답율</span> <em> <c:if test="${!empty resultMap.resultDataConcept.detail.per }">
										<fmt:formatNumber value="${resultMap.resultDataConcept.detail.per }" type="percent" />
									</c:if> <c:if test="${empty resultMap.resultDataConcept.detail.per }">
									-
								</c:if>
								</em>
							</div>
							<div>
								<span>난이도</span><span class="d_E"><em>${resultMap.resultDataConcept.detail.levName }</em></span>
							</div>
						</div>
					</div>
				</div>
				<div class="q_section">
					<div class="media_div">
						<c:choose>
							<c:when test="${notionInfo.notType eq 'P' }">
								<c:if test="${not empty notionInfo.notPlayPath }">
									<div class="video-container">
										<iframe width="560" height="315" src="https://www.youtube.com/embed/<c:out value="${notionInfo.notPlayPath }"/>?modestbranding=1&controls=0&showinfo=0&rel=0&iv_load_policy=3&fs=0" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen> </iframe>
									</div>
								</c:if>
							</c:when>
							<c:when test="${notionInfo.notType eq 'T' }">
								<c:out value="${notionInfo.notText }" escapeXml="false" />
							</c:when>
							<c:when test="${notionInfo.notType eq 'I' }">
								<c:forEach var="flist" items="${resultMap.resultDataFile.list }">
									<img src="/common/siteImgView?safSeq=${flist.safSeq}" alt="임시 이미지" onerror="this.src='/assets/images/_tmp/thumb_media.jpg'">
								</c:forEach>
							</c:when>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
		<!-- //container -->
	</div>
</body>

<script type="text/javascript" charset="UTF-8" src="/assets/js/plugin/owlcarousel/owl.carousel.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="/assets/js/public/default.js"></script>
<script type="text/javascript">
	qCardSlider();
	numSelect();

	$(document).ready(function() {
		// 		console.log("${resultMap.resultDataConcept.detail}");
	});

	function goConceptDetail(cptId, acvId) {
		$.formSubmit("/front/concept/conceptDetail", $.extend(null, {
			"cptId" : cptId,
			"acvId" : acvId
		}), {
			method : 'post'
		});
	}
</script>