<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp" %>

<div class="wrapper menu_layer" style="position: fixed; z-index: 9999; display: none;">
	<header class="header">
		<a href="#" class="btn_close">닫기</a>
		<div class="btn_div">
			<a href="#" class="btn_noti">알림 <!-- <i class="pointer"></i> --></a>
			<a href="#" class="btn_settings"></a>
		</div>
	</header>
	<section class="container">
		<div class="my_menu_top">
			<c:choose>
				<c:when test="${not empty loginSessionId }">
					<img class="my_pic" src="/assets/images/_tmp/tmp_img_photo.jpg" alt="임시 프로필 이미지">
					<p class="my_id"><b><c:out value="${loginSessionNickName }"/></b> 님<br>반갑습니다.</p>
				</c:when>
				<c:otherwise>
					<img class="my_pic" src="/assets/images/_tmp/tmp_img_photo.jpg" alt="임시 프로필 이미지">
					<p class="my_id">로그인 해주세요.</p>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="menu_wrap">
			<div class="tit_depth1"><strong>AI 수학 1등급 Go</strong></div>
			<ul>
				<li class="tit_depth2"><a href="#" class="ico_t1"><span class="txt">AI 문제 풀기</span></a></li>
				<li class="tit_depth2"><a href="/front/review/list" class="ico_t2"><span class="txt">다시풀기</span></a></li>
			</ul>
			<div class="tit_depth1"><strong>AI 수학 학습 분석</strong></div>
			<ul>
				<li class="tit_depth2"><a href="#" class="ico_t3"><span class="txt">AI 종합 리포트</span></a></li>
				<li class="tit_depth2"><a href="#" class="ico_t4"><span class="txt">주간학습 리포트</span></a></li>
				<li class="tit_depth2"><a href="#" class="ico_t5"><span class="txt">레벨평가 리포트</span></a></li>
			</ul>
		</div>
	</section><!-- //container -->
	<footer class="banner_area" style="position: fixed; bottom: 0;">
		<a href="#"><img src=""></a>
	</footer>
</div>

<script type="text/javascript">
	
	$(function(){
		// 전체메뉴 열기
		$('.btn_allmenu').on('click', function(){
			$('.menu_layer').show();
		});
		// 전체메뉴 닫기
		$('.menu_layer .btn_close').on('click', function(){
			$('.menu_layer').hide();
		});
		$('.btn_allmenu').css({'z-index' : 1});
		$('.menu_layer .header').css({'height': '5.438rem'});
	});
	
</script>
