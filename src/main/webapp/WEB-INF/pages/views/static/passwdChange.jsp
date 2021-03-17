<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>LG생활건강 | 관리자 로그인</title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<link rel="stylesheet" type="text/css" href="css/common.css" />
	<link href="/asset/js/static/style.css" rel="stylesheet" type="text/css" media="all"/>
	<!-- Custom Theme files -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="Static Login Form Responsive" />

	<script src="/asset/js/jquery/jquery.min.1.11.1.js"></script>
	<script src="/asset/js/jquery/jquery.form.js"></script>
	<script src="/asset/js/static/easyResponsiveTabs.js" type="text/javascript"></script>
	<script type="text/javascript">
        $(document).ready(function () {
            $('#horizontalTab').easyResponsiveTabs({
                type: 'default', //Types: default, vertical, accordion
                width: 'auto', //auto or any width like 600px
                fit: true   // 100% fit in a container
            });


            $('form').ajaxForm({
                success: function(data){
                    if(data.resultCode=='success'){
                        console.log("data.resultCode 111 >>> "+data.resultCode+data.resultMessage);
                        alert(data.resultMessage);
                        location.href = "../../../..";
                    } else {
                        console.log("data.resultCode 222 >>> "+data.resultCode+data.resultMessage);
                        alert(data.resultMessage);
                        //location.href = "/";
                    }
                }
            });
            $(".loginSubmit").click(function() {
                $("#loginForm").submit();
            });

            $("input").focus(function () {
				var val			= $(this).val();
				var dataId		= $(this).data("val");
				if(val == dataId) {
				    $(this).val('');
				}

            }).blur(function () {
                var val			= $(this).val();
                var dataId		= $(this).data("val");
                if(val == dataId || val == '') {
                    $(this).val(dataId);
                }
            });
        });

        function goLogin() {
            location.href =  "../../../..";
        }


	</script>
</head>

<body>
<div class="login_wrap">
	<div class="inner">
		<h1><img src="${contextPath}/asset/images/login_logo.png" /></h1>
		<p class="tit">LG 생활건강 연동관리시스템에 오신것을 환영합니다.</p>
		<div class="login_box">
			<form id="loginForm" action="/static/rest/updatePasswd" method="post">
				<ul>
					<li><input name="iumId" type="text" id="iumId" value="your id." data-val="your id."/></li>
					<li><input name="iumName" type="text" id="iumName" value="your name."  data-val="your name."></li>
					<li><input name="iumHp" type="text" id="iumHp" value="your hp."  data-val="your hp."></li>
					<li><input name="iumEmail" type="text" id="iumEmail" value="your email."  data-val="your email."></li>
					<li><input name="iumPw" type="password" id="iumPw" value="your password." data-val="your password."></li>
				</ul>
				<p class="login_btn"><a class="loginSubmit" style="cursor:pointer">Change my PASSWORD</a></p>
				<p style="padding-top:12px; color:#866b77; font-size:13px;"><a href="/static/login">+ login</a></p>
			</form>

		</div>
	</div>
</div><!-- //wrapper END -->
<div class="login_footer">
	Copyright ⓒ LG household & healthy care LTD. 2018 all rights reserved.
</div>
</body>
</html>
