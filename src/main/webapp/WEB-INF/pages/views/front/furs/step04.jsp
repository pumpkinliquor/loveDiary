<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<body>
<div class="wrapper bg_1">
  <div class="progress_bar"><span class="step3"></span></div>
  <div class="pre_q_container">
    <div class="q_cont">
      <div class="q_txt">
        <p>수능 수학 선택과목은?</p>
      </div>
      <ul class="q_choice ACTIVESUB">
      </ul>
    </div>
    <div class="btm_btn_div">
      <a href="javascript:;" class="prev"><span>이전</span></a>
<!--       <a href="javascript:;" class="next disabled"><span>다음</span></a> -->
    </div>
  </div>
</div>
<script type="text/javascript">
  qSelect();

  $(document).ready(function(){
        $.call('/ajax/codeList',{codes:'USE_YN,ACTIVESUB'},function(r){
            $.extend(plus.codes,r.codes);
        });
        $.each(plus.codes,function(k,v){
            $('.q_choice.'+k).choiceItem(v);
        });
        $('.prev').click(function(){
            $('.disabled').removeClass('disabled');
            location.href= '/front/furs/step03';
        });
        $('.next').click(function(){
            if($(this).is('.disabled')){
                return false;
            }
            $.post('/front/ajax/aigo/furs/step04',{sub_id:$('.q_choice .on').attr('data')},function(r){
                location.href= '/front/furs/step05';
            });
        });
        $('.q_choice button').click(function(){
            $.post('/front/ajax/aigo/furs/step04',{sub_id:$(this).attr('data')},function(r){
                location.href= '/front/furs/step05';
            });
        })
    });

</script>
</body>