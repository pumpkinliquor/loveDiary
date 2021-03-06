<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>수능앱 Aigo</title>

</head>

<body class="bg_w">
<div class="wrapper menu_layer">
  <header class="header ">
    <a href="#" class="btn_close">닫기</a>
    <div class="btn_div">
      <a href="#" class="btn_noti">알림 <i class="pointer"></i></a>
      <a href="#" class="btn_settings"></a>
    </div>
  </header>
  <section class="container">
      <div class="my_menu_top">
        <img class="my_pic" name="memIcon" >
        <p class="my_id" name="memUserid">님<br>반갑습니다</p>
      </div>
      <div class="menu_wrap">
        <div class="tit_depth1"><strong>AI 수학 1등급 Go</strong></div>
        <ul>
          <li class="tit_depth2">
            <a href="#" class="ico_t1"><span class="txt">AI 문제 풀기</span></a>
          </li>
          <li class="tit_depth2">
            <a href="#" class="ico_t2"><span class="txt">다시풀기</span></a>
          </li>
        </ul>

        <div class="tit_depth1"><strong>AI 수학 학습 분석</strong></div>
        <ul>
          <li class="tit_depth2">
            <a href="#" class="ico_t3"><span class="txt">AI 종합 리포트</span></a>
          </li>
          <li class="tit_depth2">
            <a href="#" class="ico_t4"><span class="txt">주간학습 리포트</span></a>
          </li>
          <li class="tit_depth2">
            <a href="#" class="ico_t5"><span class="txt">레벨평가 리포트</span></a>
          </li>
        </ul>
      </div>
  </section><!-- //container -->

  <footer class="banner_area">
    <a href="#"><img src=""></a>
  </footer>

  
</div>


<script type="text/javascript">
$(function(){

  init();
});

function init(){
  $.call("/front/ajax/user/userInfo", {}, function(returnJson){
	var resultData = null;
	if( returnJson.resultData ) resultData = returnJson.resultData;
   	else return; 
   
	if(resultData.saf_seq) $('img[name=memIcon]').attr("src", "/common/siteImgView?safSeq=" + resultData.saf_seq);
		
	});

}
</script>
</body>
</html>