<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>

<body class="study_body2">
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>학습목표</h1>
		</header>
		<div class="container">
			<div class="goal_conts">
				<div class="conts_top">
					<p class="txt1">
						<span> <em><c:choose>
									<c:when test="${!empty loginSessionNickName }">
										<c:out value="${loginSessionNickName }" />
									</c:when>
									<c:otherwise>
										<c:out value="${loginSessionId }" />
									</c:otherwise>
								</c:choose></em>님이
						</span> <b>LEVEL ${loginSessionLevel}</b> <b>${resultMap.resultData.detail.acvName}</b>학습해야 하는 내용입니다.
					</p>
				</div>
				<div class="conts_sec">
					<pre>
					${resultMap.resultData.detail.acvText}
					</pre>
				</div>
				<div class="btm_btn_div2">
					<a href="/front/achieve/list" class="prev"><span>이전</span></a> <a href="javascript:void(0)" onclick="goTendency('${resultMap.resultData.detail.acvId}');" class="next"><span>다음</span></a>
				</div>
			</div>
		</div>
		<!-- //container -->
	</div>
</body>

<script type="text/javascript" charset="UTF-8" src="/assets/js/public/default.js"></script>

<script type="text/javascript">
	function goTendency(acvId) {
		$.formSubmit("/front/tendency/detail", $.extend(null, {
			"acvId" : acvId
		}), {
			method : 'post'
		});
	}
</script>