<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<body>
<div class="wrapper bg_1">
  <div class="progress_bar"><span class="step1"></span></div>
  <div class="pre_q_container">
    <div class="q_cont">
      <div class="q_txt">
        <p>현재 학년은?</p>
      </div>
      <ul class="q_choice temp_class">
        <li><button data="h3">고3</button></li>
        <li><button data="hn">N수생</button></li>
        <li><button data="h2">고2</button></li>
        <li><button data="h1">고1</button></li>
        <li><button data="he">기타</button></li>
      </ul>
    </div>
    <div class="btm_btn_div">
      <a href="#" class="prev"><span>이전</span></a>
      <a href="javascript:void(0)" class="next disabled"><span>다음</span></a>
    </div>
  </div>
</div>

<script type="text/javascript">


  $(document).ready(function(){
        /* 코드 사용 */
        $.call('/ajax/codeList',{codes:'USE_YN,temp_class'},function(r){
            $.extend(plus.codes,r.codes);
        });
        $.each(plus.codes,function(k,v){
            $('.q_choice.'+k).choiceItem(v);
        });

        $('.prev').click(function(){
            $('.disabled').removeClass('disabled');
            location.href= '/';
        });
        $('.next').click(function(){
            if($(this).is('.disabled')){
                return false;
            }
            $.call('/front/ajax/aigo/firs/step02',{temp_class:$('.q_choice .on').attr('data')},function(r){
                location.href= '/front/furs/step03';
            });

        })
    });

</script>
</body>