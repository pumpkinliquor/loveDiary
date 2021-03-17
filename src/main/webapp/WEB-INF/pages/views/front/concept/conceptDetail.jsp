<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@ page import="java.util.Enumeration"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>
<c:set var="requestParam" value="<%=new ObjectMapper().writeValueAsString(request.getParameterMap())%>" />
<c:set var="notionInfo" value="${resultMap.resultDataConcept.notionInfo[0]}"/>
<c:set var="conceptInfo" value="${resultMap.resultDataConcept.detailList[0]}"/>
<!-- 개념학습 -> 개념상세 -->
<%
	Enumeration<String> attrNames = request.getAttributeNames();
%>
<body class="study_body2">
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>개념학습</h1>
		</header>
		<div class="container btm0">
			<div class="study_conts">
				<c:set var="subName" value="${resultMap.resultDataConcept.detailList[0].subName }" />
				<c:set var="acvName" value="${resultMap.resultDataConcept.detailList[0].acvName }" />
				<c:set var="unitName" value="${resultMap.resultDataConcept.detailList[0].unitName }" />
				<h2>${subName }</h2>
				<h3>${unitName }</h3>
				<h4>${acvName }</h4>
				<div class="study_subject btm0">
					<div class="title">
						<a href="javascript:void(0);">${resultMap.resultDataConcept.detailList[0].cptName }</a>
					</div>
					<div class="view">
						<c:choose>
							<c:when test="${notionInfo.notType eq 'P' }">
								<div class="video-container">
									<iframe width="560" height="315" src="https://www.youtube.com/embed/<c:out value="${notionInfo.notPlayPath }"/>?modestbranding=1&controls=0&showinfo=0&rel=0&iv_load_policy=3&fs=0" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen> </iframe>
								</div>
							</c:when>
							<c:when test="${notionInfo.notType eq 'T' }">
								<c:out value="${notionInfo.notText }" escapeXml="false" />
							</c:when>
							<c:when test="${notionInfo.notType eq 'I' }">
								<c:forEach var="flist" items="${resultMap.resultDataFile.list }">
									<img src="/common/siteImgView?safSeq=${flist.safSeq}" alt="임시 이미지" onerror="this.src='/assets/images/_tmp/img_qa.jpg'">
								</c:forEach>
							</c:when>
						</c:choose>
					</div>
				</div>
			</div>
			<!-- 수정0219 -->
			<c:if test="${conceptInfo.prevCptId ne null || conceptInfo.nextCptId ne null }">
				<div class="btm_btn_div2">
					<c:if test="${conceptInfo.prevCptId ne null }">
						<a href="javascript:void(0)" class="prev" <c:if test="${conceptInfo.prevCptId ne null }">onclick="goConceptDetail('${conceptInfo.prevCptId}','${conceptInfo.acvId}','${conceptInfo.notId}');"</c:if>><span>이전</span></a>
					</c:if>
					<c:if test="${conceptInfo.nextCptId ne null }">
						<a href="javascript:void(0)" class="next" <c:if test="${conceptInfo.nextCptId ne null }">onclick="goConceptDetail('${conceptInfo.nextCptId}','${conceptInfo.acvId}','${conceptInfo.notId}');"</c:if>><span>다음</span></a>
					</c:if>
				</div>
			</c:if>
			<!-- //수정0219 -->
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
		console.log("${conceptInfo.prevCptId}");
		console.log("${conceptInfo.nextCptId}");
	});
	
	function goConceptDetail(cptId, acvId, notId) {
		$.formSubmit("/front/concept/conceptDetail", $.extend(null, {
			"cptId" : cptId,
			"acvId" : acvId,
			"notId" : notId
		}), {
			method : 'post'
		});
	}

</script>