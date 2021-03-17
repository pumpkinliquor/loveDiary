<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>
<c:set var="faqDtl" value="${resultMap.resultData.faqDetail }" />

<body>
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_back">이전으로</a>
			<h1>FAQ</h1>
		</header>
		<div class="container cont_box">
			<div class="contents">

				<div class="faq_wrap">
					<span class="tit1"><c:out value="${faqDtl.fgrTitle }"/></span>
					<h2><c:out value="${faqDtl.faqTitleGroup }"/></h2>
					<div class="faq_view_cont">
						<div class="view_top">
							<div class="date">
								<span><c:out value="${faqDtl.regDate }"/></span>
							</div>
						</div>
						<div class="view_div">
							<p><c:out value="${faqDtl.faqContentGroup }"/></p>

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
	faqList();
</script>