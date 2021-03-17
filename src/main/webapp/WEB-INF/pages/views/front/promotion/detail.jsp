<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>
<c:set var="pmtDtl" value="${resultMap.resultData.promotionDetail }"/>

<body>
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_back">이전으로</a>
			<h1>이벤트</h1>
		</header>
		<div class="container cont_box">
			<div class="contents">
				<div class="ev_wrap">
					<h2><c:out value="${pmtDtl.prmName }"/></h2>
					<div class="ev_view_cont">
						<ul class="ev_info">
							<li><span class="ev_label">이벤트 기간</span><span class="ev_data"><c:out value="${pmtDtl.prmStartDate }"/> ~ <c:out value="${pmtDtl.prmEndDate }"/></span></li>
							<li><span class="ev_label">이벤트 경품</span><span class="ev_data"><c:out value="${pmtDtl.prmEvent }"/></span></li>
							<li><span class="ev_label">당첨자 발표</span><span class="ev_data"><c:out value="${pmtDtl.prmDate }"/>, <c:out value="${pmtDtl.prmTarget }"/></span></li>
						</ul>
						<div class="view_div">
							<p><c:out value="${pmtDtl.prmText }" escapeXml="false" /></p>
						</div>
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
</script>