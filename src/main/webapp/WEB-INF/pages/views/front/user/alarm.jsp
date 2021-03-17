<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@ page import="java.util.Enumeration"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>
<c:set var="requestParam" value="<%=new ObjectMapper().writeValueAsString(request.getParameterMap())%>" />
<c:set var="ar" value="${resultMap.resultData.arList}" />
<%
	Enumeration<String> attrNames = request.getAttributeNames();
%>
<body>
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>알림함</h1>
		</header>
		<div class="container cont_box">
			<div class="contents">
				<ul>
					<c:choose>
						<c:when test="${!empty ar }">
							<c:forEach var="a" items="${ar }" varStatus="as">
								<li class="noti_div <c:if test="${a.notReadYn eq 'y' && fn:contains(a.diffStr, '-')}"><c:out value="opc5"/></c:if>"><input type="hidden" name="getNotId" value="${a.notId }" /> <input type="hidden" name="getNotUrl" value="${a.notUrl }" />
									<div class="alarm_img">
										<c:if test="${a.notReadYn eq 'n' }">
											<i class="pointer">새</i>알림
										</c:if>
									</div>
									<div class="noti_txt">
										<p class="txt1">AIGo 오늘의 학습이 도착했어요.</p>
										<p class="txt2">
											<c:out value="${a.notTitle }" />
											<br>
											<c:out value="${a.notMessage }" />
										</p>
										<p class="txt3">
											<c:out value="${a.diffStr }" />
										</p>
									</div>
								</li>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<%-- 받은 알림 없을경우 화면 추가 --%>
							<li class="noti_div none"><p>알림 내역이 없습니다.</p></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>

		</div>
		<!-- //container -->

	</div>
</body>

<form id="notFrm" name="notFrm" method="post">
	<input type="hidden" name="notId" value="" />
</form>

<script type="text/javascript" charset="UTF-8" src="/assets/js/public/default.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		// 		console.log("${resultMap.resultData.arList}");
	});
	$(function() {

	});

	function init() {

	}

	$(document).on("click", ".noti_div", function() {
		var $getThis = $(this);
		var url = $getThis.find("input[name=getNotUrl]").val();
		$("input[name=notId]").val($getThis.find("input[name=getNotId]").val());
		$.call("/front/ajax/user/readAlarm", $("[id=notFrm]").serializeObject(), function(r) {
			if (r.resultCode == "S00") {
				$.formSubmit(url, $.extend($("[id=notFrm]").serializeObject(), {}), {
					method : 'post'
				});
			}
		});
	});
</script>
