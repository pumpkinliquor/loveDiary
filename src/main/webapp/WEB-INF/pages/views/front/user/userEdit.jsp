<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>
<html lang="ko">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>수능앱 Aigo</title>

<link rel="stylesheet" href="/assets/css/style_m.css">
<script src="/assets/js/script_m.js"></script>

</head>

<body class="join_body">
<form id="form" name="form" enctype="multipart/form-data" method="POST" action="/front/ajax/user/userUpdate" autocomplete="off" onSubmit="return false;" >
	<input type="hidden" name="memUserid" value="" />

<div class="wrapper">
	<header class="header">
	    <a href="#" class="btn_allmenu">전체메뉴</a>
	    <h1>개인정보수정</h1>
	    
	    <div class="profile_img" type="file" name="file1" >
	    	<input type="file" name="file1" style="display: none;" >
				<img name="memIcon" style="width:100%; height: 100%;" onclick="document.all.file1.click()" onerror="this.src='/assets/images/default_img.png'" />
			</input>
		</div>
						
      </div>
  </header>
	<div class="container join">
		<section class="contents usersArea">
			
				<div class="member_join_cont">
				
					<div class="member_ipt_div">
			          <h2>이메일</h2>
			          <div class="ipt_div">
			            <input type="text" name="memEmail" value="" disabled>
			          </div>
			        </div>
			        
			        <div class="member_ipt_div">
			          <h2>닉네임</h2>
			          <div class="ipt_div">
			            <input type="text" name="memNickname" class="ipt_plc" autocomplete="off" >
			          </div>
			          <p class="er_txt nickNameErrorArea"></p> 
			        </div>
        
			       <div class="member_ipt_div">
			          <h2>비밀번호</h2>
			          <div class="ipt_div ipt_edit_div">
			            <input type="password" name="memPassword" value="" autocomplete="off" disabled>
			            <a href="#" class="ico_pw_view disabled" style="display:none;">비밀번호 보기</a>
			            <button onclick="passwordReset(this)" class="btn_gray_s">변경</button>
			          </div>
			          <p class="er_txt passwordErrorArea"></p> 
			        </div> 
			        
			        <div class="member_ipt_div">
			          <h2>비밀번호 재입력</h2>
			          <input type="password" name="comparePassword" value="" autocomplete="off" disabled>
			          <p class="er_txt passwordErrorCompareArea"></p> 
			        </div>
			        
			        <div class="member_ipt_div">
			          <h2>핸드폰번호</h2>
			          <div class="ipt_div ipt_edit_div">
			            <input type="number" name="memPhone" value="" maxlength="11" readonly >
			            <button name="resetPhoneNumber" onclick="phoneAuthBtn()" class="btn_gray_s">변경</button>
			          </div>
			          <p class="er_txt phoneErrorArea"></p> 
			        </div>
			        
			        <div class="member_ipt_div" style="display: none;">
			          <h2>인증번호 입력</h2>
			          <div class="ipt_div ipt_edit_div">
			            <input type="number" name="masKey" value="">
			            <span id="viewTimer" class="txt_red"></span>
			            <button class="btn_gray_s" name="masKeyBtn" onclick="fnCheckSmsAuthCode()" >확인</button>
			          </div>
			          <p class="er_txt masKeyErrorArea"></p> 
			        </div>
			        
					<div class="member_ipt_div">
						<h2>학년</h2>
						<div class="ipt_div">
							<input type="text" name="memClassName" value="" onclick="fnClassPopup()" readonly>
							<input type="text" name="memClass" value="" style="display: none;" >
						</div>
						<!-- <p class="er_txt nickNameErrorArea"></p>  -->
					</div> 
					<div class="member_ipt_div">
						<h2>선택과 과목</h2>
						<div class="ipt_div">
							<input type="text" name="memSubIdName" value="" onclick="fnSubPopup()" readonly>
							<input type="text" name="memSubId" value="" style="display: none;" >
						</div>
						<!-- <p class="er_txt nickNameErrorArea"></p>  -->
					</div>   
					<button type="button" class="btn btn_join" onclick="confirmUpdate();">확인</button> 
				</div><!-- //member_ipt_cont -->
			</form> 
		</section>
		
	</div><!-- //container -->
</div>

<div name="classModal" class="modal-wrap">
    <div class="modal">
      <div class="pop_cont">
        <form>
          <ul id="class_select_div" class="select_div">
          <!-- InnerHtml -->
          </ul>
        </form>
      </div>

    </div>
</div>

<div name="subModal" class="modal-wrap">
    <div class="modal">
      <div class="pop_cont">
        <form>
          <ul id="sub_select_div" class="select_div">
          <!-- InnerHtml -->
          </ul>
        </form>
      </div>

    </div>
</div>

<script type="text/javascript">
var levCd = "";
var subCd = "";
var $form = $("#form");

$(document).ready(function() {
        $('input[name=file1]').on("change", handleImgFileSelect);
    }); 
    
$(function(){
	var param = $form.domJson();
	
	// 비밀번호 보기 활성화/비활성화
	$('.ico_pw_view').on('click', function(){
		var $this = $(this);
		var $memPassword = $("input[name='memPassword']");
		if($this.hasClass('disabled')){
			$this.removeClass('disabled');
			$memPassword.attr("type", "text");
		}else{
			$this.addClass('disabled');
			$memPassword.attr("type", "password");
		}
	});
	
	// 비밀번호 입력 시 유효성 검사
	$("input[name='memPassword'], input[name='comparePassword']").on('keyup', function(e){
		checkPassword( $("input[name='memPassword']").val(), $("input[name='comparePassword']").val(), '' );
		if($(this).val() == ""){
			$(".passwordErrorArea").text("");
			$(".passwordErrorCompareArea").text("");
		}
	});
	
	init();
});

$(document).on("keyup", "[name=memPassword]", function() {
	if($(this).val() != "") {
		$(this).siblings("a").css("display", "");
	} else {
		$(this).siblings("a").css("display", "none");
	}
});

// 사용자 정보 초기화
function init(){
	$.call("/front/ajax/user/userInfo", {}, function(returnJson){
		
		var resultData = null;
		if( returnJson.resultData ) resultData = returnJson.resultData;
		else return; 
		
		levCd = returnJson.resultData.cm_code;
		subCd = returnJson.resultData.mem_sub_id;
		
		$('input[name=memNickname]').attr("placeholder", resultData.mem_email);
		
		if(resultData.saf_seq) $('img[name=memIcon]').attr("src", "/common/siteImgView?safSeq=" + resultData.saf_seq);
		else $('img[name=memIcon]').attr("src", "/assets/images/default_img.png");
		if(resultData.mem_userid) $('input[name=memUserid]').val(resultData.mem_userid);
		if(resultData.mem_email) $('input[name=memEmail]').val(resultData.mem_email);
		if(resultData.mem_nickname) $('input[name=memNickname]').val(resultData.mem_nickname);
		if(resultData.mem_phone) $('input[name=memPhone]').val(resultData.mem_phone);
		if(resultData.mem_class) $('input[name=memClass]').val(resultData.mem_class);
		if(resultData.cm_name) $('input[name=memClassName]').val(resultData.cm_name);
		if(resultData.mem_sub_id) $('input[name=memSubId]').val(resultData.mem_sub_id);
		if(resultData.sub_name) $('input[name=memSubIdName]').val(resultData.sub_name);
		
		var $memPassword = $("input[name='memPassword']");
		var $comparePassword = $("input[name='comparePassword']");
		$memPassword.attr("disabled", true);
		$comparePassword.attr("disabled", true);
		$memPassword.siblings("button").css("display", "");
		$memPassword.siblings("a").css("display", "none");
		$(".passwordErrorArea").text("");
	});
	
}

//이미지 미리보기
function handleImgFileSelect(e) {
    var files = e.target.files;
    var filesArr = Array.prototype.slice.call(files);

    filesArr.forEach(function(f) {
        if(!f.type.match("image.*")) {
            alert("확장자는 이미지 확장자만 가능합니다.");
            return;
        }

        sel_file = f;

        var reader = new FileReader();
        reader.onload = function(e) {
            $('img[name=memIcon]').attr("src", e.target.result);
        }
        reader.readAsDataURL(f);
    });
}

function passwordReset(e){
	var $memPassword = $("input[name='memPassword']");
	var $comparePassword = $("input[name='comparePassword']");
	$memPassword.val('');
	$comparePassword.val('');
	$memPassword.removeAttr("disabled");
	$comparePassword.removeAttr("disabled");
	$(e).css("display", "none");
	$(e).siblings("a").css("display", "");
	$(".passwordErrorArea").text("");
}

function confirmUpdate(){
		
	var $resetPhoneNumber = $("button[name='resetPhoneNumber']");
	var $memClass = $("input[name='memClass']");
	var $memSubId = $("input[name='memSubId']");
	
	var param = $form.domJson();
	
	param['memClass'] = $memClass.text();
	param['memSubId'] = $memSubId.text();
	
	
	var nicknameCheck = true;
	var passwordCheck = true;
	var phoneCheck = true;
	var phoneAuthCheck = true;
	
	//수정할 항목 검사
	if(param.memNickname.length > 0 ) nicknameCheck = false;
	if(param.memPassword.length > 0 || param.comparePassword.length > 0) passwordCheck = false;
	if(param.memPhone.length > 0 ) phoneCheck = false;
	
	//닉네임 정규식 확인
	if( nicknameCheck || checkNickname( param.memNickname,  'nickNameErrorArea' ) ) nicknameCheck = true;
	else nicknameCheck = false;
	//비밀번호 정규식 확인
	if( passwordCheck || checkPassword( param.memPassword, param.comparePassword, 'passwordErrorArea' ) ) passwordCheck = true;
	else passwordCheck = false;
	if( $("[name=memPhone]" ).val() != "" ) {
		//휴대폰 정규식 확인
		if( checkPhoneNumber( param.memPhone, 'phoneErrorArea' )) phoneCheck = true;
		else phoneCheck = false;
		//휴대폰 인증 확인
		if($resetPhoneNumber.text() == '변경' || 
		($resetPhoneNumber.text() != '변경' &&  $("input[name='masKey']").attr('disabled') == 'disabled' )) phoneAuthCheck = true;
		else { 
			$('.masKeyErrorArea').text('인증번호를 인증하세요.'); 
			phoneAuthCheck = false;
		}
	}

	
	if(
		nicknameCheck 
		&& passwordCheck
// 		&& phoneCheck
// 		&& phoneAuthCheck
	){

		if(!confirm("저장하시겠습니까?")){
			return;
		}

		
		$("#form").ajaxForm({
            type: 'POST',
            url : "/front/ajax/user/userUpdate",
            enctype : "multipart/form-data",
            dataType : "json",
            success : function(result){
                alert("정상적으로 변경되었습니다.");
                init()
            }
        }).submit();
	} 

}

function phoneAuthBtn(){
	var $memPhone = $("input[name='memPhone']");
	var $resetPhoneNumber = $("button[name='resetPhoneNumber']");
	
	if($resetPhoneNumber.text() == '변경'){
		$memPhone.attr('readonly', false);
		$resetPhoneNumber.text('인증번호 받기');
	}else if($resetPhoneNumber.text() == '인증번호 받기'){
		$("input[name=masKey]").closest("div[class=member_ipt_div]").css("display", "");
		if(checkPhoneNumber( $memPhone.val(), 'phoneErrorArea' )){
			//인증번호 전송
			fnSendSmsAuthcode();	
			//3분 타이머
			$memPhone.attr('readonly', true);
			$resetPhoneNumber.text('인증번호 재전송');
		}
	}else {
		//인증번호 재전송
		if(tid > 0) {
		 SetTime = 180;
		 clearInterval(tid); 
	 	};
		fnSendSmsAuthcode();	
	}
}

//인증코드 전송
function fnSendSmsAuthcode(){
	var param = $form.domJson();
	
	$.call("/front/ajax/user/sendSmsAuthcode", param, function(returnJson){
		if(returnJson.resultCode == "S00") TimerStart();
	});
}

//인증시간 타이머
var SetTime = 180;      // 최초 설정 시간(기본 : 초)
function TimerStart(){
	tid=setInterval('msg_time()',1000); 
}

function msg_time() {   // 1초씩 카운트      
    m = Math.floor(SetTime / 60) + ":" + plus.lpad((SetTime % 60),2,'0'); // 남은 시간 계산
    var msg = m;  
    //document.all.ViewTimer.innerHTML = msg;     // div 영역에 보여줌
    document.all.viewTimer.innerText = msg
    SetTime--;                  // 1초씩 감소
    if (SetTime < 0) {          // 시간이 종료 되었으면..        
        clearInterval(tid);     // 타이머 해제
        //alert("종료");

    }       
}

//휴대폰 인증번호 확인
function fnCheckSmsAuthCode(){
	var $masKey = $("input[name='masKey']");
	var $resetPhoneNumber = $("button[name='resetPhoneNumber']");
	var $masKeyBtn = $("button[name='masKeyBtn']");
	
	var param = $form.domJson();
	
	$.call("/front/ajax/user/checkSmsAuthCode", param, function(returnJson){
		var json = returnJson.resultData;
		var $masKeyErrorArea = $('.masKeyErrorArea');
		var errorMsg = '';
		if(returnJson.resultCode == 'S00'){
			// 인증확인 처리
			document.all.viewTimer.innerText = '';
			clearInterval(tid);
			$masKey.attr('disabled', true);
			$masKeyBtn.attr('disabled', true);
			$resetPhoneNumber.attr('disabled', true);
		}else{
			if(returnJson.resultCode == 'F02'){
				errorMsg = '인증번호를 입력해주세요.';
			}else if(returnJson.resultCode == 'F03'){
				errorMsg = '유효하지 않은 인증번호입니다.';
				$masKeyErrorArea.text('');
			}else if(returnJson.resultCode == 'F04'){
				errorMsg = '';
				alert('인증번호 처리 중 오류가 발생하였습니다.\n관리자에게 문의하세요.');
				return;
			}else{
				errorMsg = '';
				alert('시스템 오류입니다.\n관리자에게 문의하세요.');
				return;
			}
		}
		$masKeyErrorArea.text(errorMsg);
	});
}

//학년 팝업
function fnClassPopup(){
	var $memClass = $("input[name='memClass']");
	var $classModal= $("div[name='classModal']");
	
	$.call("/front/ajax/user/levelList", {}, function(returnJson){
		var resultList = returnJson.resultList;
		
		var radioListHtml = '';
		for(var i =0; i < resultList.length; i++){
			radioListHtml = radioListHtml + '<li class="ipt_radio_div"> <input type="radio" onclick="selectLevel(this)" value="'+resultList[i].cm_name+'" id="' + resultList[i].cm_code + '" name="select"/> <label for="' + resultList[i].cm_code + '"><span>' + resultList[i].cm_name + '</span></label> </li>';
		}
		document.all.class_select_div.innerHTML = radioListHtml;
		
		$("ul[id=class_select_div]").find("li").find("input").each(function(i, e) {
			if($(e).attr("id") == levCd) {
				$(e).attr("checked", "checked");
			}
		});
		
	});
	
	$classModal.addClass('is-visible');
}

//학년선택
function selectLevel(e){
	var $memClassName = $("input[name='memClassName']");
	var $memClass = $("input[name='memClass']");
	var $classModal= $("div[name='classModal']");
	
	levCd = e.id;
	
	$memClassName.val(e.value);
	$memClass.val(e.id);
	
	$classModal.removeClass('is-visible');
}

//과목 팝업
function fnSubPopup(){
	var $memSubId = $("input[name='memSubId']");
	var $subModal= $("div[name='subModal']");
	
	$.call("/front/ajax/user/subList", {}, function(returnJson){
		var resultList = returnJson.resultList;
		
		var radioListHtml = '';
		for(var i =0; i < resultList.length; i++){
			radioListHtml = radioListHtml + '<li class="ipt_radio_div"> <input type="radio" onclick="selectSub(this)" value="'+resultList[i].sub_name+'" id="' + resultList[i].sub_id + '" name="select" /> <label for="' + resultList[i].sub_id + '"><span>' + resultList[i].sub_name + '</span></label> </li>';
		}
		
		document.all.sub_select_div.innerHTML = radioListHtml;
		
		$("ul[id=sub_select_div]").find("li").find("input").each(function(i, e) {
			if($(e).attr("id") == subCd) {
				$(e).attr("checked", "checked");
			}
		});
		
	});
	
	$subModal.addClass('is-visible');
}

//과목 선택
function selectSub(e){
	var $memSubIdName = $("input[name='memSubIdName']");
	var $memSubId= $("input[name='memSubId']");
	var $subModal= $("div[name='subModal']");
	
	subCd = e.id;
	
	$memSubIdName.val(e.value);
	$memSubId.val(e.id);
	
	
	$subModal.removeClass('is-visible');
}


</script>
</body>
</html>