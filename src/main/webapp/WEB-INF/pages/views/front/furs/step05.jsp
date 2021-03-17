<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<body>
<div class="wrapper bg_1">
  <div class="progress_bar"><span class="step4"></span></div>
  <div class="pre_q_container">
    <div class="q_cont">
      <div class="q_txt">
        <p>모의고사 30문제 중 <br>자신 있게 풀 수 있는 문제 수는?</p>
      </div>
      <ul class="q_choice qst_num q_cont_grd">
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
        $.call('/ajax/codeList',{codes:'USE_YN,qst_num'},function(r){
            $.extend(plus.codes,r.codes);
        });
        $.each(plus.codes,function(k,v){
            $('.q_choice.'+k).choiceItem(v);
        });
        $('.prev').click(function(){
            $('.disabled').removeClass('disabled');
            location.href= '/front/furs/step04';
        });
        $('.next').click(function(){
            if($(this).is('.disabled')){
                return false;
            }
            $.post('/front/ajax/aigo/furs/step05',{qst_num:$('.q_choice .on').attr('data')},function(r){
                location.href= '/front/furs/step06';
            });
        });
        $('.q_choice button').click(function(){
            $.post('/front/ajax/aigo/furs/step05',{qst_num:$(this).attr('data')},function(r){
                location.href= '/front/furs/step06';
            });
        });
    });

</script>
</body>