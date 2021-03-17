<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<body>
<div class="wrapper bg_1">
  <div class="progress_bar"><span class="step2"></span></div>
  <div class="pre_q_container">
    <div class="q_cont">
      <div class="q_txt">
        <p>현재 모의고사 수학 등급은?</p>
      </div>
      <ul class="q_choice temp_grade">
        <li><button data="1">1등급</button></li>
        <li><button data="2">2등급</button></li>
        <li><button data="3">3등급</button></li>
        <li><button data="4">4등급</button></li>
        <li><button data="5">5등급</button></li>
        <li><button data="6">6등급</button></li>
        <li><button data="7">7등급</button></li>
        <li><button data="8">8등급</button></li>
        <li><button data="9">9등급</button></li>
      </ul>
    </div>
    <div class="btm_btn_div">
      <a href="#" class="prev"><span>이전</span></a>
      <a href="#" class="next disabled"><span>다음</span></a>
    </div>
  </div>
</div>
<script type="text/javascript">
  qSelect();

  $(document).ready(function(){
        $.call('/ajax/codeList',{codes:'USE_YN,temp_grade'},function(r){
            $.extend(plus.codes,r.codes);
        });
        $.each(plus.codes,function(k,v){
            $('.q_choice.'+k).choiceItem(v);
        });
        $('.prev').click(function(){
            $('.disabled').removeClass('disabled');
            location.href= '/front/furs/step02';
        });
        $('.next').click(function(){
            if($(this).is('.disabled')){
                return false;
            }
            $.call('/front/ajax/aigo/firs/step03',{temp_grade:$('.q_choice .on').attr('data')},function(r){
                location.href= '/front/furs/step04';
            });
        })
    });

</script>
</body>