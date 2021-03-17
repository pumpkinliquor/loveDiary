<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>
<c:set var="pmtList" value="${resultMap.resultData.promotionList }"/>

<body>
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>이벤트</h1>
		</header>
		<div class="container cont_box">
			<div class="contents">
				<div class="ev_wrap">
					<div class="ev_ban_slide">
						<c:if test="${!empty pmtList }">
							<c:forEach var="pmt" items="${pmtList }">
								<div class="ev_ban_div">
									<h2><c:out value="${pmt.prmName }"/></h2>
									<a href="javascript:void(0)"onclick="goPromotionDetail('<c:out value="${pmt.prmId }"/>'); return false;">
										<img src="/common/siteImgView?safSeq=${pmt.safSeq}" alt="이벤트 배너이미지" onerror="this.src='/assets/images/_tmp/ev_banner01.jpg'">
									</a>
									<div class="ev_btm">
										<ul>
											<li><span class="ev_label">이벤트 기간</span><span class="ev_txt"><c:out value="${pmt.prmStartDate }"/> ~ <c:out value="${pmt.prmEndDate }"/></span></li>
											<li><span class="ev_label">이벤트 경품</span><span class="ev_txt"><c:out value="${pmt.prmEvent }"/></span></li>
										</ul>
									</div>
								</div>
							</c:forEach>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<!-- //container -->
	</div>
</body>

<script type="text/javascript" charset="UTF-8" src="/assets/js/public/default.js"></script>
<script src="/assets/plugin/owlcarousel/owl.carousel.min.js"></script>
<script type="text/javascript">
	eventBanner();
	
	// 다시풀기 문제페이지 이동
	function goPromotionDetail(prmId) {
		if (prmId != "") {
			$.formSubmit("/front/promotion/detail", $.extend(null, {
				"prmId" : prmId
			}), {
				method : 'post'
			});
		} else {
			commonModalPopup("프로모션 번호가 없습니다.");
			return false;
		}
	}
</script>