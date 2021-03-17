<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<body>
<div class="wrapper bg_1">
  <div class="pre_q_container">
      <p class="txt1">
        <span class="txt_AI">AI</span> 진단준비가 끝났습니다. <br>지금 나에게 꼭 필요한 <br>최우선 순위의 수능 수학 문제찾기
      </p>
     <a href="/front/furs/question" class="btn_start"><span>Go</span></a>

     <p class="txt2">
       수학문제를 연습장에 풀수있는 <br>
         환경에서 진행해주세요.
     </p>
     <div class="btm_btn_div">
<%--       <a href="#" class="prev"><span>이전</span></a>--%>
       <a href="javascript:void(0)" class="next disabled"><span>다음</span></a>
     </div>
  </div>
</div>

<script>
    $(document).ready(function(){
        $('.btn_start').click(function(){
            $('.disabled').removeClass('disabled');

        });
    });
</script>
</body>