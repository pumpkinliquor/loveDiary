<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>
<c:set var="cmtrInfo" value="${resultMap.resultData.cmtrInfo }" />
<body class="study_body2">
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>해설보기</h1>
		</header>
		<div class="container btm0">
			<div class="question_conts comment_cont">
				<div class="q_top_section" id="qTopHeight">
					<div class="q_solve_top">
						<p class="q">${resultMap.resultData.detail.subName } Lv.${resultMap.resultData.detail.levId } ${resultMap.resultData.detail.acvName }</p>
						<div class="tag_div">
							<c:if test="${resultMap.resultData.detail.allCptId != null }">
								<c:set var="conceptIdList" value="${fn:split(resultMap.resultData.detail.allCptId,',')}" />
								<c:set var="acvIdList" value="${fn:split(resultMap.resultData.detail.allAcvId,',')}" />
								<c:set var="conceptNameList" value="${fn:split(resultMap.resultData.detail.allCptName,',')}" />
								<c:if test="${!empty conceptIdList }">
									<c:forEach var="val" items="${conceptIdList }" varStatus="vs">
										<a href="#" onclick="goConceptDetail('${val}', '${acvIdList[vs.index] }'); return false;" class="tag">${conceptNameList[vs.index] }</a>
									</c:forEach>
								</c:if>
							</c:if>
						</div>
						<div class="ov_h">
							<div class="aigo_rate">
								<span class="logo_img">AIGo</span><span class="txt">정답율</span> <em> <c:if test="${!empty resultMap.resultData.detail.per }">
										<fmt:formatNumber value="${resultMap.resultData.detail.per }" type="percent" />
									</c:if> <c:if test="${empty resultMap.resultData.detail.per }">
									-
								</c:if>
								</em>
							</div>
							<div>
								<span>난이도</span><span class="d_E"><em>${resultMap.resultData.detail.levName }</em></span>
							</div>
						</div>
					</div>
				</div>
				<div class="q_section">
					<c:choose>
						<c:when test="${cmtrInfo.cmtrType eq 'T' }">
							<c:out value="${cmtrInfo.cmtrText }" escapeXml="false" />
						</c:when>
						<c:when test="${cmtrInfo.cmtrType eq 'P' }">
							<c:if test="${not empty cmtrInfo.cmtrPlayPath }">
								<div class="video-container">
									<iframe width="560" height="315" src="https://www.youtube.com/embed/<c:out value="${cmtrInfo.cmtrPlayPath }"/>?modestbranding=1&controls=0&showinfo=0&rel=0&iv_load_policy=3&fs=0" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen> </iframe>
								</div>
							</c:if>
						</c:when>
						<c:when test="${cmtrInfo.cmtrType eq 'I' }">
							<c:if test="${not empty cmtrInfo.fileList }">
								<c:forEach var="row" items="${cmtrInfo.fileList }">
									<img src="/common/siteImgView?safSeq=<c:out value="${row.safSeq }"/>" />
								</c:forEach>
							</c:if>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
		<!-- //container -->
	</div>
</body>

<script type="text/javascript">
	var $form = $("#form");

	$(document).ready(function() {

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