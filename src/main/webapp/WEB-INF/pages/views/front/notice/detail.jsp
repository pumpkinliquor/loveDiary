<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>
<c:set var="notiDtl" value="${resultMap.resultData.noticeDetail }" />

<body>
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_back">이전으로</a>
			<h1>공지사항</h1>
		</header>
		<div class="container cont_box">
			<div class="contents">
				<div class="board_wrap">
					<h2><c:out value="${notiDtl.bbTitle }"/></h2>
					<div class="board_view_cont">
						<div class="view_top">
							<div class="date">
								<span><c:out value="${notiDtl.fmtDate }"/></span>
							</div>
							<c:set var="bafSeq" value="${fn:split(notiDtl.bafSeq, ',') }"/>
							<c:set var="bafFile" value="${fn:split(notiDtl.bafFile, ',') }"/>
							<c:set var="bafOrFile" value="${fn:split(notiDtl.bafOrFile, ',') }"/>
							<c:if test="${notiDtl.bafSeq ne null}">
								<div class="file_div">
									<c:forEach var="baf" items="${bafSeq }" varStatus="vs">
										<a href="javascript:void(0)" onclick="fileDown('<c:out value="${baf }"/>', '<c:out value="${bafFile[vs.index] }"/>', '<c:out value="${bafOrFile[vs.index] }"/>'); return false;" class="ico_file">
											<c:out value="${bafOrFile[vs.index] }"/>
										</a>
										<c:if test="${vs.count ne fn:length(bafSeq)}">
											<br>
										</c:if>
									</c:forEach>
								</div>
							</c:if>
						</div>
						<div class="view_div">
							<p><c:out value="${notiDtl.bbContents }" escapeXml="false" /></p>
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
	
	function fileDown(bafSeq, bafFile, bafOrFile) {
// 		alert(bafSeq + " / " + bafFile + " / " + bafOrFile);
		$.formSubmit("/front/notice/fileDown", $.extend(null, {
			"bafSeq" : bafSeq,
			"bafFile" : bafFile,
			"bafOrFile" : bafOrFile
		}), {
			method : 'post'
		});
	}
</script>