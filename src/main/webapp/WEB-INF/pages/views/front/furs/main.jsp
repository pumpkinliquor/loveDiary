<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>
<body class="pre_body">
<div class="wrapper bg_1">
  <div class="pre_container">
      <p class="txt0"><span class="txt_AI">AI</span>진단 준비가 끝났습니다.</p>
      <p class="txt1">
         지금, 단 5문제만 풀면<br>성공적으로 1등급을 달성하는<br>비법을 확인할 수 있습니다.
      </p>
      <div class="start_btn_div">
      	<a href="#" class="btn_start"><span>시작하기</span></a> 
     </div>
<!--      <a href="javascript:;" class="btn_start"><span>Go</span></a> -->

     <p class="txt2">
       수학문제를 연습장에 풀 수 있는 <br>
         환경에서 진행해 주세요.
     </p>
     <div class="btm_btn_div">
<%--       <a href="javascript:;" class="prev"><span>이전</span></a>--%>
<!--        <a href="javascript:void(0)" class="next disabled"><span>다음</span></a> -->
     </div>
  </div>
</div>

<script src="/assets/plugin/sonic.js"></script>
<script>
    //${temp_id};
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
	
	$(document).ready(function(){
		$('.btn_start').click(function(){
			$('.disabled').removeClass('disabled');
			
		});
		$('.sonic').click(function(){
		//	if($(this).is('.disabled')){
		//		return false;
		//	}
// 			$.post('/front/ajax/aigo/furs/step01',{tep_class:$('.q_choice .on button').attr('data')},function(r){
				location.href= '/front/furs/question';
// 			});
		})
	});
</script>
</body>