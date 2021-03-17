<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>



  <!--공통스크립트-->
  <script src="https://code.jquery.com/jquery-2.2.0.min.js" type="text/javascript"></script>
  <script src="/assets/user/KR/js/script.js"></script>

  <!--메인슬라이더-->  
  <script src="/assets/user/KR/js/slick.js"></script>
  <!--메인 progress bar-->
  <script>
    //progress bar
    $(document).on('ready', function() {
      $(".main-slide").slick({
        lazyLoad: 'ondemand',
        infinite: true , 
        slidesToShow: 1, 
        slidesToScroll: 1,
        autoplay: true, 
        arrows:false,
        dots:true, 
        autoplaySpeed:3000,
        speed:1000 , 
        pauseOnHover:false,
      });

      $(".slick-slider").on('afterChange',function(){
        $(".pro-bar").addClass('pro-ani');
      });
      $(".slick-slider").on('beforeChange',function(){
        $(".pro-bar").removeClass('pro-ani');
      });

        //pause btn
      $(".play").on("click", function(){
        $(".main-slide").slick("slickPlay");
      });
      $(".pause").on("click", function(){
        $(".main-slide").slick("slickPause");
      });
  })
  
</script>