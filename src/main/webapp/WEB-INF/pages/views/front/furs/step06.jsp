<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<body>
<div class="wrapper bg_1">
  <div class="progress_bar"><span class="step5"></span></div>
  <div class="pre_q_container">
    <div class="q_cont">
      <div class="q_txt">
        <p>수능 수학 4점 문제<br>나의 풀이 스타일은?</p>
      </div>
      <ul class="q_choice my_style">
        <li><button data="my_style">한번에 끝까지 푼다</button></li>
        <li class="on"><button data="my_style">빠르게 찍고 넘어간다</button></li>
        <li><button data="my_style">일단 스킵 후 다른 문제를 풀고 <br>남은 시간에 푼다</button></li>
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
        $.call('/ajax/codeList',{codes:'USE_YN,my_style'},function(r){
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
            $.call('/front/ajax/aigo/firs/step06',{qst_num:$('.q_choice .on').attr('data')},function(r){
                location.href= '/front/join';
            });
        });
    });

</script>
</body>