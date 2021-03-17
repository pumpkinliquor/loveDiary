<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>
<body class="study_body2">
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>학습목표</h1>
		</header>
		<div class="container btm0">
			<div class="goal_conts">
				<div class="conts_top">
					<p class="txt1">
						<span> <em><c:choose>
									<c:when test="${!empty userInfo.memNickname }">
										<c:out value="${userInfo.memNickname }" />
									</c:when>
									<c:otherwise>
										<c:out value="${userInfo.memEmail }" />
									</c:otherwise>
								</c:choose></em>님이
						</span> <b>LEVEL ${loginSessionLevel}</b> <b>${resultMap.resultData.detail[0].acvName}</b>학습해야 하는 내용입니다.
					</p>
				</div>
				<div class="conts_sec">
					<c:if test="${!empty resultMap.resultData.detail}">
						<c:forEach var="l" items="${resultMap.resultData.detail }">
							<c:if test="${!empty l.safSeq }">
								<img src="/common/siteImgView?safSeq=${l.safSeq}" alt="임시 이미지" onerror="this.src='/assets/images/_tmp/img_qa.jpg'">
							</c:if>
							<br>
						</c:forEach>
					</c:if>
				</div>
				<div class="btm_btn_div2">
					<a href="/front/achieve/list" class="prev"><span>이전</span></a> <a href="javascript:void(0)" onclick="goTendency('${resultMap.resultData.detail[0].acvId}');" class="next"><span>다음</span></a>
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