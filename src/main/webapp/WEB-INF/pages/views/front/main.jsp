<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<body class="pre_body1">
	<div class="wrapper bg_1">
		<div class="pre_q_container">

			<div id="typedtext">
				<div class="typing-txt"> 
					<ul>
						<li>AI 진단으로 제공되는</li>
						<li>지금 나에게 꼭 필요한 </li>
						<li>최우선 순위의 수능 수학 문제찾기</li>
					</ul>
				</div> 
				<div class="typing_div">
					<ul>
						<li></li>
						<li></li>
						<li></li>
					</ul>
				</div>
			</div> <!-- 수정0209 -->
			
			<div class="start_btn_div">
				<a href="#" class="btn_start"><span>시작하기</span></a> 
			</div>
			
			<p class="txt2">
				<span>이미 가입하셨나요? </span>
				<a href="#!" onclick="goLoginPage();">로그인</a>
			</p>
			
		</div>
	</div>
</body>
<script src="/assets/plugin/sonic.js"></script>
<script src="/assets/js/typed.js"></script>
<script>

	var circle = new Sonic({
		
		width: 200,
		height: 200,
		
		stepsPerFrame: 1,
		trailLength: .8,
		pointDistance: .01,
		fps: 50,
		
		fillColor: '#0de8dd',
		
		setup: function() {
			this._.lineWidth = 10;
		},
		step: function(point, index) {
			this._.beginPath();
			this._.moveTo(point.x, point.y);
			this._.arc(point.x, point.y, 15, 0, Math.PI*2, false);
			this._.closePath();
			this._.fill();
		},
		path: [
			['arc', 100, 100, 60, 0, 360]
		]
	});
	circle.play();
	
	document.body.appendChild(circle.canvas);
</script>

<script type="text/javascript">

var typingBool2 = false; 
var typingIdx2=0; 
var liIndex2 = 0;
var liLength2 = $(".typing-txt>ul>li").length;

// 타이핑될 텍스트를 가져온다 
var typingTxt = $(".typing-txt>ul>li").eq(liIndex2).text(); 
typingTxt=typingTxt.split(""); // 한글자씩 자른다. 
if(typingBool2==false){ // 타이핑이 진행되지 않았다면 
    typingBool2=true; 
    var tyInt2 = setInterval(typing1,100); // 반복동작  //수정0308
} 

function typing1(){ 
  $(".typing_div ul li").removeClass("on");
   $(".typing_div ul li").eq(liIndex2).addClass("on");
  if(typingIdx2<typingTxt.length){ // 타이핑될 텍스트 길이만큼 반복 
  if ((typingIdx2 == 0) && (liIndex2 == 0) || (typingIdx2 == 1) && (liIndex2 == 0))
  {
    $(".typing_div ul li").eq(0).append('<b>'+typingTxt[typingIdx2]+'</b>');
  } else {
    $(".typing_div ul li").eq(liIndex2).append(typingTxt[typingIdx2]); // 한글자씩 이어준다. 
  }
  typingIdx2++; 

   } else{ if(liIndex2<liLength2-1){
     //다음문장으로  가기위해 인덱스를 1증가
       liIndex2++; 
     //다음문장을 타이핑하기위한 셋팅
        typingIdx2=0;
        typingBool2 = false; 
        typingTxt = $(".typing-txt>ul>li").eq(liIndex2).text(); 
       
     //다음문장 타이핑전 1초 쉰다
         clearInterval(tyInt2); //수정0308
          //타이핑종료
     
         setTimeout(function(){
           //1초후에 다시 타이핑 반복 시작
           tyInt2 = setInterval(typing1,100); //수정0308
         },);
        } else if(liIndex2==liLength2-1){
          
         //마지막 문장까지 써지면 반복종료
           clearInterval(tyInt2); //수정0308
        }
    } 
} 
</script>

<script>
	
$(document).ready(function(){
		
	$('.sonic').click(function(){
		$.call('/front/ajax/aigo/furs/step01',{tep_class:$('.q_choice .on button').attr('data')},function(r){
		 	location.href= '/front/furs/step02';
		});
	});
	
	if('${loginSessionId}'!=''){
		try {
			Native.postMessage('{"event":"send_login","memId":"${loginSessionId}"}');
		} catch (e){

		}
		location.href='/front/learn/home';
		return false;
	} else {
		
		if($.trim('${temp_id}')!='0'&& '${temp_id}'!=''){
			$.call('/front/ajax/aigo/furs/checkState',{},function(r){
				if(r.resultData){
					if($.trim(r.resultData['aiReady'])==''){
						alert('사전질문을 완료 하지 않으셨습니다\n사전질문을 완료후 이용해주시기 바랍니다.');
						location.herf='/front/furs/step03';
						return false;
					}
				} else {
					alert('사전질문을 완료 하지 않으셨습니다\n사전질문을 완료후 이용해주시기 바랍니다.');
					location.herf='/front/furs/step03';
					return false;
				}
			})
		}
	}
	
//		type01();
	});
//	var type01 = function(){
//		Typed.new("#typed", {
//			stringsElement: document.getElementById('typed-strings'),
//			typeSpeed: 100,
//			backDelay: 500,
//			loop: false,
//			contentType: 'html', // or text
//			// defaults to null for infinite loop
//			loopCount: 0,
//			callback: function(){ setTimeout(function(){
//				type02()
//			},1000);    },
//			resetCallback: function() {  }
//		});
//	}
//	var type02 = function(){
//		Typed.new("#typed2", {
//			stringsElement: document.getElementById('typed-strings2'),
//			typeSpeed: 100,
//			backDelay: 500,
//			loop: false,
//			contentType: 'html', // or text
//			// defaults to null for infinite loop
//			loopCount: 0,
//			callback: function(){ setTimeout(function(){
//				type03()
//			},1000);    },
//			resetCallback: function() {  }
//		});
//	}
//	var type03 = function(){
//		Typed.new("#typed3", {
//			stringsElement: document.getElementById('typed-strings3'),
//			typeSpeed: 100,
//			backDelay: 500,
//			loop: false,
//			contentType: 'html', // or text
//			// defaults to null for infinite loop
//			loopCount: 0,
//			callback: function(){ setTimeout(function(){
//				$('#typed1').empty();
//				$('#typed2').empty();
//				$('#typed3').empty();
//				type01();
//			},5000);
//			},
//			resetCallback: function() {  }
//		});
//	}
		/*
	var typingBool2 = false; 
	var typingIdx2=0; 
	var liIndex2 = 0;
	var liLength2 = $(".typing-txt>ul>li").length;

	// 타이핑될 텍스트를 가져온다 
	var typingTxt = $(".typing-txt>ul>li").eq(liIndex2).text(); 
	typingTxt=typingTxt.split(""); // 한글자씩 자른다. 
	if(typingBool2==false){ // 타이핑이 진행되지 않았다면 
	    typingBool2=true; 
	    var tyInt = setInterval(typing1,100); // 반복동작 
	} 

	function typing1(){ 
	  $(".typing_div ul li").removeClass("on");
	   $(".typing_div ul li").eq(liIndex2).addClass("on");
	  if(typingIdx2<typingTxt.length){ // 타이핑될 텍스트 길이만큼 반복 
	  if ((typingIdx2 == 0) && (liIndex2 == 0) || (typingIdx2 == 1) && (liIndex2 == 0))
	  {
	    $(".typing_div ul li").eq(0).append('<b>'+typingTxt[typingIdx2]+'</b>');
	  } else {
	    $(".typing_div ul li").eq(liIndex2).append(typingTxt[typingIdx2]); // 한글자씩 이어준다. 
	  }
	  typingIdx2++; 

	   } else{ if(liIndex2<liLength2-1){
	     //다음문장으로  가기위해 인덱스를 1증가
	       liIndex2++; 
	     //다음문장을 타이핑하기위한 셋팅
	        typingIdx2=0;
	        typingBool2 = false; 
	        typingTxt = $(".typing-txt>ul>li").eq(liIndex2).text(); 
	       
	     //다음문장 타이핑전 1초 쉰다
	         clearInterval(tyInt);
	          //타이핑종료
	     
	         setTimeout(function(){
	           //1초후에 다시 타이핑 반복 시작
	           tyInt = setInterval(typing1,100);
	         },);
	        } else if(liIndex2==liLength2-1){
	          
	         //마지막 문장까지 써지면 반복종료
	           clearInterval(tyInt);
	        }
	    } 
	}
	*/
	
</script>
