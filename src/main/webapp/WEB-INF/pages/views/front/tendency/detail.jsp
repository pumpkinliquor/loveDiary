<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>

<body class="study_body2">
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>출제경향</h1>
		</header>
		<div class="container">
			<div class="goal_conts">
				<div class="conts_top">
					<p class="txt1">
						<span><em><c:choose>
									<c:when test="${!empty loginSessionNickName }">
										<c:out value="${loginSessionNickName }" />
									</c:when>
									<c:otherwise>
										<c:out value="${loginSessionId }" />
									</c:otherwise>
								</c:choose></em>님이</span> <b>LEVEL ${loginSessionLevel}</b> <b>${resultMap.resultData.detail.acvName}</b> 학습해야 하는 내용입니다.
					</p>
				</div>
				<div class="conts_sec">
					<p>
						<c:out value="${resultMap.resultData.detail.tenText }"/><br>
						<br>
					</p>
					<p>* 성취기준별 원고 필요</p>
				</div>
				<div class="btm_btn_div2">
					<a href="/front/achieve/list" class="prev"><span>이전</span></a> <a href="#!" class="next" onclick="goLearning();"><span>다음</span></a>
				</div>
			</div>
		</div>
	</div>

</body>

<form id="form" name="form" method="post">
	<input type="hidden" name="subId" value="<c:out value="${resultMap.resultData.detail.subId}"/>" />
	<input type="hidden" name="acvId" value="<c:out value="${resultMap.resultData.detail.acvId}"/>" />
	<input type="hidden" name="levId" value="<c:out value="${loginSessionLevel}"/>" />
</form>

<script type="text/javascript">

var $form = $("#form");

$(document).ready(function() {
	console.log("${resultMap.resultData.detail}");
});

function goLearning(){
	$form.attr("action","/front/learn/question").submit();
}

</script>