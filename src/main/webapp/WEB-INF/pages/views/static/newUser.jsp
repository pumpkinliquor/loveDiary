<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<title>Static Login Form Flat Responsive Widget Template | Home</title>
<!-- Custom Theme files -->
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
                if(data.resultCode == 'success'){
                    alert(data.resultMessage);
                    location.href = "../../../..";
                } else {
                    alert(data.resultMessage);
                    //location.href = "/";
                }
            }
        });

    });
</script>
<style type="text/css">
	.login {
		width: 55%;
		float: right;
		padding: 2% 0;
		background: #fff;
	}
	.login-top {
		padding: 0px 25px;
		margin-top: 0%;
	}
	.login-top {
		padding: 0px 25px;
	}
	.btn {
		font-size: 13px;
		color: #44c7f4;
		background: #fff;
		border: 2px solid #44c7f4;
		outline: none;
		cursor: pointer;
		border-radius: 20px;
		padding: 6px 13px;
		font-family: 'SourceSansPro-Regular';
	}
</style>
</head>
<body>

	<div class="head">
		<div class="logo">
			<div class="logo-top">
				<h1>Static Login Form</h1>
			</div>
			<div class="logo-bottom">
				<section class="sky-form">
					<label class="radio"><input type="radio" name="radio" checked="checked"><i></i>Ut mattis mattis bibendum</label>
					<label class="radio"><input type="radio" name="radio"><i></i>Nullam rutrum sagittis interdum</label>
					<label class="radio"><input type="radio" name="radio"><i></i>Nam cursus eros sed elit</label>
				</section>
			</div>
		</div>
		<div class="login">
			<div class="sap_tabs">
				<div id="horizontalTab" style="display: block; width: 100%; margin: 0px;">
					<ul class="resp-tabs-list">
						<li class="resp-tab-item" aria-controls="tab_item-0" role="tab"><span>Create my ACCOUNT</span></li>
						<%--<li class="resp-tab-item" aria-controls="tab_item-1" role="tab"><label>/</label><span>Sign up</span></li>--%>
						<div class="clearfix"></div>
					</ul>
					<div class="resp-tabs-container">
						<div class="tab-1 resp-tab-content" aria-labelledby="tab_item-0">
							<div class="login-top">
								<form action="/static/rest/insertUser" id="loginForm" method="post">
									<input type="text" name="iumId" class="userid" placeholder="아이디" required="" value=""/>
									<input type="text" name="iumName" class="userName" placeholder="이름" required="" value=""/>
									<input type="text" name="iumHp" class="userHp" placeholder="핸드폰번호" required="" value=""/>
									<input type="text" name="iumEmail" class="userEmail" placeholder="이메일" required="" value=""/>
									<input type="password" name="iumPw" class="password" placeholder="비밀번호" required="" value=""/>

									<div class="login-bottom login-bottom2">
										<div class="submit">
											<input type="submit" value="Create my ACCOUNT" />
											<input type="button" value="LOGIN" class ="btn" onclick="goLogin();" />
										</div>
										<div class="clear"></div>
									</div>
								</form>
							</div>
						</div>
						<!--
						<div class="tab-1 resp-tab-content" aria-labelledby="tab_item-1">
						<div class="login-top">
								<form action="/static/joinAction" method="post">
									<input type="text" class="name active" placeholder="Your Name" required=""/>
									<input type="text" class="email" placeholder="Email" required=""/>
									<input type="password" class="password" placeholder="Password" required=""/>

									<div class="login-bottom">
										<div class="submit">
											<input type="submit" value="SIGN UP"/>
										</div>
										<div class="clear"></div>
									</div>
								</form>
							</div>

						</div>
						-->
					</div>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</div>
	<div class="account">
        <ul>
			<li><p>Forgot your <a href="/static/idSearch">ID?</a></p></li><span>/</span>
			<li><p>Forgot your <a href="/static/passwdChange">PASSWORD?</a></p></li>
            <div class="clear"></div>
        </ul>
    </div>
    <!---728x90--->

	<div class="footer">
		<p>© 2018 LG생활건강. All Rights Reserved | Design by  <a href="http://ls.plus.com/" target="_blank">LG생활건강</a> </p>
	</div>
</body>
</html>