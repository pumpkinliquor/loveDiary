<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
    request.setCharacterEncoding("UTF-8");
    response.setHeader("Cache-Control", "no-cache, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    response.setHeader("returnURI", request.getRequestURI());
    response.setContentType("text/html; charset=UTF-8");
%>
<html lang="${lan}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/assets/css/style_m.css?v=1">
    <link rel="stylesheet" href="/assets/js/plugin/owlcarousel/owl.carousel.min.css">
	<link rel="stylesheet" href="/assets/js/plugin/owlcarousel/owl.theme.default.css">
<!--     <link rel="stylesheet" href="/assets/css/simplePagination.css"> -->
<!-- 	<script type="text/javascript" charset="UTF-8" src="/assets/js/jquery.min.js"></script> -->
	<script type="text/javascript" charset="UTF-8" src="/assets/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="/assets/js/script_m.js?v=1"></script>
    <script type="text/javascript" charset="UTF-8" src="/assets/js/boostrap/popper.js"></script>
    <script type="text/javascript" charset="UTF-8" src="/assets/js/boostrap/bootstrap.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="/assets/js/datatables.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="/assets/js/jquery/jquery.form.min.js"></script>
<!--     <script type="text/javascript" charset="UTF-8" src="/assets/js/jquery.simplePagination.js"></script> -->
    <script type="text/javascript" charset="UTF-8" src="/assets/js/global.js"></script>
    <script type="text/javascript" charset="UTF-8" src="/assets/js/plus.js?v=1"></script>
    <script type="text/javascript" charset="UTF-8" src="/assets/js/front.plus.js"></script>


</head>
	<tiles:insertAttribute name="menu" />
	<tiles:insertAttribute name="bg" />
	<tiles:insertAttribute name="contents" />
	<tiles:insertAttribute name="footer" />
<script>
    //progress bar
    $(document).on('ready', function() {
        if ($(".main-slide").length > 0) {

            $(".main-slide").slick({
                lazyLoad: 'ondemand',
                infinite: true,
                slidesToShow: 1,
                slidesToScroll: 1,
                autoplay: true,
                arrows: false,
                dots: true,
                autoplaySpeed: 3000,
                speed: 1000,
                pauseOnHover: false,
            });
            $(".slick-slider").on('afterChange', function() {
                $(".pro-bar").addClass('pro-ani');
            });
            $(".slick-slider").on('beforeChange', function() {
                $(".pro-bar").removeClass('pro-ani');
            });
            //pause btn
            $(".play").on("click", function() {
                $(".main-slide").slick("slickPlay");
            });
            $(".pause").on("click", function() {
                $(".main-slide").slick("slickPause");
            });
        }
        if ($(".csr-slider").length > 0) {
            $(".csr-slider").slick({
                lazyLoad: 'ondemand',
                infinite: true,
                slidesToShow: 1,
                slidesToScroll: 1,
                autoplay: true,
                arrows: true,
                dots: true,
                autoplaySpeed: 3000,
                speed: 1000,
                pauseOnHover: false,
            });
        }



        if($('.close-btn').length){
            modalClose();
        }

        function strip_tags (input, allowed) {
            // making sure the allowed arg is a string containing only tags in lowercase (<a><b><c>)
            allowed = (((allowed || "") + "").toLowerCase().match(/<[a-z][a-z0-9]*>/g) || []).join('');
            var tags = /<\/?([a-z][a-z0-9]*)\b[^>]*>/gi ,
                commentsAndPhpTags = /<!--[\s\S]*?-->|<\?(?:php)?[\s\S]*?\?>/gi;
            return input.replace(commentsAndPhpTags, '')
                        .replace(tags, function ($0, $1) {return allowed.indexOf('<' + $1.toLowerCase() + '>') > -1 ? $0 : '';});
        }
        $('.strip_tags').each(function(){
            $(this).html(strip_tags($(this).html()));
        })

    });
    function selectLang(val){
    	var lang = $(val).attr('id');

          	if($.trim(location.pathname).indexOf("EN") > -1){
          		if(lang == 'KR'){
                   location.href = location.pathname.replace("EN",'KR');
          		}
               } else if($.trim(location.pathname).indexOf("KR") > -1){
              	 if(lang == 'EN'){
                  location.href = location.pathname.replace("KR",'EN');
              	 }
               }else{
             	  location.href = location.pathname + "/" +  $(val).attr('id');
               }
    	}
</script>
</html>