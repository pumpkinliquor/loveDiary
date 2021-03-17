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
		<div class="container">
			<div class="study_conts">
				<c:set var="subName" value="${resultMap.resultData.detailList[0].subName }" />
				<c:set var="acvName" value="${resultMap.resultData.detailList[0].acvName }" />
				<h2>${subName }</h2>
				<h3>대단원명</h3>
				<h4>${acvName }</h4>
				<div class="study_subject">
					<c:forEach var="dtlList" items="${resultMap.resultData.detailList }">
						<div class="title">
							<a href="javascript:void(0);">${dtlList.cptName }</a>
						</div>
					</c:forEach>
					<div class="view">
						<img src="/assets/images/_tmp/img_qa.jpg" alt="">
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
		console.log("${resultMap.resultData.detailList}");
	});

</script>