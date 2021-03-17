<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>수능앱 Aigo</title>

</head>

<body >
<div class="wrapper">
 <header class="header">
    <a href="#" class="btn_allmenu">전체메뉴</a>
    <h1>설정</h1>
  </header>
  <div class="container cont_box settings">
    <div class="contents">
    	<h2>계정정보</h2>
		<ul class="set_my_info">
			<li><a href="/front/user/userEdit">개인정보 수정</a></li>
			<li><a href="/front/pay/history">구매내역</a></li>
			<li><a href="/front/user/leave">탈퇴신청</a></li>
<!-- 			<li><a href="#!" onclick="alert('서비스 준비중입니다.');">탈퇴신청</a></li> -->
		</ul>
<!--       <div> -->
<!-- 	      <h2>앱설정</h2> -->
<!-- 	      <ul> -->
	        <!-- <li class="settings_div">
	          <p class="txt">미디어 재생 Wi-Fi 환경에서만 사용</p>
	          <div class="onoffswitch">
	            <input type="checkbox" id="set1">
	            <label for="set1"><span></span></label>
	          </div>
	        </li>
	        <li class="settings_div">
	          <p class="txt">어두운 모드</p>
	          <div class="onoffswitch">
	            <input type="checkbox" id="set2">
	            <label for="set2"><span></span></label>
	          </div>
	        </li> -->
<!-- 	        <li class="settings_div"> -->
<!-- 	          <p class="txt">글꼴크기</p> -->
<!-- 	          <a href="#" class="fR btn">설정하기</a> -->
<!-- 	        </li> -->
<!-- 	      </ul> -->
<!--       </div> -->

      <h2>알림설정</h2>
      <ul>
       <li class="settings_div">
          <p class="txt">마케팅 알림 받기</p>
          <div class="onoffswitch">
            <input type="checkbox" name="aigoAlarmMkt" id="set3" onclick="alarmSet(this)">
            <label for="set3"><span></span></label>
          </div>
        </li>
        <li class="settings_div">
          <p class="txt">학습 알림 받기</p>
          <div class="onoffswitch">
            <input type="checkbox" name="aigoAlarmLearn" id="set4" onclick="alarmSet(this)">
            <label for="set4"><span></span></label>
          </div>
        </li>
        <li class="settings_div">
          <p class="txt">야간 (21시~08시) 알림 받기</p>
          <div class="onoffswitch">
            <input type="checkbox" name="aigoAlarmNight" id="set5" onclick="alarmSet(this)">
            <label for="set5"><span></span></label>
          </div>
        </li>
      </ul>

      <div class="ver_info">
        <ul>
          <!-- <li>
            <p><strong>캐시삭제</strong></p>
            <div class="fR"><a href="#">92.00MB</a></div>
          </li> -->
          <li>
            <p><strong>버전정보</strong><span>v1.20</span></p>
            <div class="fR">
              <span></span>
              <a href="#">업데이트하기</a>
            </div>
          </li>
          <li>
            <p><strong>버전정보</strong><span>v1.20</span></p>
            <div class="fR"><p><span>최신버전입니다.</span></p></div>
          </li>
        </ul>
      </div>

      <ul class="foot_m">
        <li><a href="/front/terms/termsOfService">이용약관</a></li>
        <li><a href="/front/terms/privacyPolicy">개인정보 처리방침</a></li>
        <li><a href="#!" onclick="logout();">로그아웃</a></li>
      </ul>
    </div>
  </div><!-- //container -->
</div>


<div class="modal-wrap">
    <div class="modal">
      <div class="pop_cont">
          <p class="pop_txt">Wi-Fi 환경이 아닌 경우 데이터 요금이 <br>부과될 수 있습니다.</p>
          <div class="pop_btm_div">
            <button class="btn btn_pop close-pop">확인</button>
          </div>
      </div>

    </div>
</div>

<script type="text/javascript">
$(function(){
	init();
});

function init(){
	$.call("/front/ajax/user/settingInfo", {}, function(returnJson){
		var resultData = returnJson.resultData;

		$('input[name=aigoAlarmMkt]').attr("checked", resultData.aigo_alarm_mkt == 'y');
		$('input[name=aigoAlarmLearn]').attr("checked", resultData.aigo_alarm_learn == 'y');
		$('input[name=aigoAlarmNight]').attr("checked", resultData.aigo_alarm_night =='y');

	});
}

  function alarmSet(e){
  	 var param = { [e.name]: e.checked  ? 'y' : 'n' };

  	 $.call("/front/ajax/user/settingUpdate", param, function(returnJson){
  	 	var resultCode = returnJson.resultCode;
  	 	if(resultCode != 'S00') alert('시스템 오류입니다.\n관리자에게 문의하세요.');
  	 });
  }
</script>
</body>

</html>