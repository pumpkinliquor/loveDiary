<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@ page import="java.util.Enumeration"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>
<c:set var="requestParam" value="<%=new ObjectMapper().writeValueAsString(request.getParameterMap())%>" />
<%
	Enumeration<String> attrNames = request.getAttributeNames();

	System.out.println(
			"===============================================================================================================");
	while (attrNames.hasMoreElements()) {
		String attrName = attrNames.nextElement();
		Object attrValue = request.getAttribute(attrName);
		System.out.println(attrName + " <> " + attrValue);
	}
	System.out.println(
			"===============================================================================================================");
%>
<body class="study_body2">
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>개념학습</h1>
		</header>
		<div class="container btm0">
			<div class="study_conts scroll_y">
				<c:set var="subName" value="${resultMap.resultData.detailList[0].subName }" />
				<c:set var="acvName" value="${resultMap.resultData.detailList[0].acvName }" />
				<c:set var="unitName" value="${resultMap.resultData.detailList[0].unitName }" />
				<h2>${subName }</h2>
				<h3>${unitName }</h3>
				<h4>${acvName }</h4>
				<div class="study_subject">
					<c:forEach var="dtlList" items="${resultMap.resultData.detailList }">
						<div class="title">
							<a href="javascirpt:" onclick="goConceptDetail('${dtlList.cptId}', '${dtlList.acvId }', '${dtlList.notId }'); return false;">${dtlList.cptName }</a>
						</div>
					</c:forEach>
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

	$(document).ready(function() {
		
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