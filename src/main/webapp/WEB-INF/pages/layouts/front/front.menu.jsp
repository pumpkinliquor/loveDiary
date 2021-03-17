<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>

<%-- 테스트 관련 필드 (DB연동 후 삭제) --%>
<c:set var="payYn" value="y"/>
<%-- 테스트 관련 필드 (DB연동 후 삭제) --%>

<div class="wrapper menu_layer" style="position: fixed; z-index: 9999; display: none;">
	<header class="header">
		<a href="#" class="btn_close">닫기</a>
		<div class="btn_div">
			<a href="/front/user/alarm" class="btn_noti">알림 <i class="pointer"></i></a> <a href="/front/user/setting" class="btn_settings"></a>
		</div>
		<c:choose>
			<c:when test="${not empty loginSessionId }">
				<div class="my_menu_top" onclick="goUserInfo();">
					<img class="my_pic" name="memIcon" alt="임시 프로필 이미지" onclick="goUserInfo();" onerror="this.src='/assets/images/default_img.png'">
					<p class="my_id">
						<span><em> <c:choose>
									<c:when test="${!empty loginSessionNickName }">
										<c:out value="${loginSessionNickName }" />
									</c:when>
									<c:otherwise>
										<c:out value="${loginSessionEmail }" />
									</c:otherwise>
								</c:choose>
						</em><span>님</span></span> <span>반갑습니다</span>
					</p>
				</div>
			</c:when>
			<c:otherwise>
				<div class="my_menu_top" onclick="goLogin();">
					<img class="my_pic" src="/assets/images/default_img.png" alt="임시 프로필 이미지">
					<p class="my_id">로그인 해주세요.</p>
				</div>
			</c:otherwise>
		</c:choose>
	</header>
	<section class="container layer_container">
		
		<c:choose>
			<c:when test="${payYn eq 'y' }">
			<div class="menu_wrap my_history">
				<div class="tit_depth1"><strong>AI 수학 1등급 Go</strong></div>
				<ul>
					<li class="tit_depth2"><a href="<c:out value="${empty loginSessionLevel? '/front/furs/main' : '/front/achieve/list' }"/>" class="ico_t1"><span class="txt">AI 문제 풀기</span></a></li>
					<li class="tit_depth2"><a href="/front/review/list" class="ico_t2"><span class="txt">다시풀기</span></a></li>
				</ul>
				
				<div class="tit_depth1"><strong>AI 수학 학습 분석</strong></div>
				<ul>
					<li class="tit_depth2">
						<a href="/front/report/totalReport" class="ico_t3"><span class="txt">AI 종합 리포트</span></a>
						<div class="badge_update">
							<span>Update</span>
						</div>
					</li>
					<li class="tit_depth2">
						<a href="/front/report/weekReport" class="ico_t4"><span class="txt">주간학습 리포트</span></a>
						<div class="badge_update">
							<span>Update</span>
						</div>
					</li>
					<c:set var="newIcon" value="" />
					<c:forEach var="row" items="${levelTestHistory }">
						<c:if test="${row.newYn eq 'y' }">
							<c:set var="newIcon" value="Y" />
						</c:if>
					</c:forEach>
					<li class="tit_depth2 <c:if test='${empty levelTestHistory}'>disabled</c:if>">
						<a href="<c:out value="${empty levelTestHistory? '#!' : '/front/report/levelReport' }"/>" class="ico_t5"><span class="txt">레벨평가 리포트</span> 
						<c:if test="${not empty newIcon }"><div class="badge_update"><span>Update</span></div></c:if>
					</a>
					</li>
				</ul>
				
				<div class="menu_layer_btm">
					<ul>
						<li><a href="/front/learn/home">메인</a></li>
						<li><a href="/front/promotion/list">이벤트</a></li>
						<li><a href="/front/notice/list">공지사항</a></li>
						<li><a href="/front/faq/list">FAQ</a></li>
					</ul>
					<button class="btn btn_st3 fs1" onclick="goProductInfo();">이용권 구매</button>  
				</div>
				
			</div>
		</c:when>
		<c:otherwise>
			<div class="menu_wrap">
				<div class="menu_layer_btm">
					<ul>
						<li><a href="/front/learn/home">메인</a></li>
						<li><a href="/front/promotion/list">이벤트</a></li>
						<li><a href="/front/notice/list">공지사항</a></li>
						<li><a href="/front/faq/list">FAQ</a></li>
					</ul>
					<button class="btn btn_st3 fs1" onclick="goProductInfo();">이용권 구매</button>  
				</div>
			</div>
		</c:otherwise>
		</c:choose>
		
	</section>
	<!-- //container -->
	
</div>

<div class="modal-wrap commonPopupLayer">
	<div class="modal">
		<div class="pop_cont">
			<p class="pop_txt alertMsg"></p>
			<div class="pop_btm_div">
				<button class="btn btn_pop close-pop confirmMsg popupConfirm">확인</button>
			</div>
		</div>
	</div>
</div>

<div class="modal-wrap commonConfirmPopupLayer">
	<div class="modal">
		<div class="pop_cont">
			<p class="pop_txt alertMsg"></p>
			<div class="pop_btm_div">
				<button class="btn btn_pop close-pop confirmMsg popupConfirmOk">확인</button>
				<button class="btn btn_pop close-pop cancelMsg popupConfirm">취소</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var $bodyClass = "";
	$(function() {
		// 전체메뉴 열기
		$('.btn_allmenu').on('click', function() {
			$bodyClass = $("body").attr("class");
			topInit();
			$.call("/front/ajax/user/notReadAlarm", null, function(r) {
				if (r.resultData.notReadCnt <= 0) {
					$(".btn_noti").find(".pointer").remove();
				}
			});
			$.call("/front/ajax/report/thisDayCnt", null, function(r) {
				if (r[0] <= 0) {
					$(".ico_t4").closest("li").addClass("disabled");
					$(".ico_t4").attr("href", "#");
				}
				if (r[1] <= 0) {
					$(".ico_t4").siblings("div").remove();
				}
				if (r[2] <= 0) {
					$(".ico_t3").closest("li").addClass("disabled");
					$(".ico_t3").attr("href", "#");
				}
				if (r[3] <= 0) {
					$(".ico_t3").siblings("div").remove();
				}
			});
			$("body").removeClass();
			$("body").addClass("learning_body");
			$('.menu_layer').show();
		});
		// 전체메뉴 닫기
		$('.menu_layer .btn_close').on('click', function() {
			$("body").removeClass();
			$("body").addClass($bodyClass);
			$('.menu_layer').hide();
		});
		$('.btn_allmenu').css({
			'z-index' : 1
		});
		
		$('.btn_back').on('click', function() {
			history.back(-1);
		});
		$('.btn_back').css({
			'z-index' : 1
		});
		// 		$('.menu_layer .header').css({
		// 			'height' : '5.438rem'
		// 		});
	});
	
	

	// 공통 alert
	function commonModalPopup(msg, confirmMsg) {
		var $commonPopupLayer = $('.commonPopupLayer');
		$commonPopupLayer.addClass('is-visible');
		$commonPopupLayer.find('.alertMsg').html(msg);
		if (confirmMsg == undefined || confirmMsg == 'undefined' || confirmMsg == '') {
			$commonPopupLayer.find('.confirmMsg').text(confirmMsg);
		} else {
			$commonPopupLayer.find('.confirmMsg').text(confirmMsg);
		}
	}

	// 공통 Confirm
	function commonConfirmModalPopup(msg, confirmMsg) {
		var $commonPopupLayer = $('.commonConfirmPopupLayer');
		$commonPopupLayer.addClass('is-visible');
		$commonPopupLayer.find('.alertMsg').html(msg);
		if (confirmMsg == undefined || confirmMsg == 'undefined' || confirmMsg == '') {
			$commonPopupLayer.find('.confirmMsg').text(confirmMsg);
		} else {
			$commonPopupLayer.find('.confirmMsg').text(confirmMsg);
		}
	}

	function goUserInfo() {
		window.location.href = '/front/user/userEdit';
	}

	function goLogin() {
		window.location.href = '/front/login';
	}

	function topInit() {
		if ("${loginSessionId}" != "") {
			$.call("/front/ajax/user/userInfo", {}, function(returnJson) {

				var resultData = null;
				if (returnJson.resultData) resultData = returnJson.resultData;
				else return;

				if (resultData.saf_seq) $('img[name=memIcon]').attr("src", "/common/siteImgView?safSeq=" + resultData.saf_seq);
				else $('img[name=memIcon]').attr("src", "/assets/images/default_img.png");
				if (resultData.mem_nickname) $('p[class=my_id]').find('em').text(resultData.mem_nickname);

			});
		}
	}
	
	function goProductInfo(){
	    window.location.href = "/front/product/info";
	}
	
</script>
