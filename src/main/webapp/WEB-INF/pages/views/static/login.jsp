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
	<link rel="stylesheet" type="text/css" href="/assets/css/bootstrap.min.css" media="screen" />
	<!-- Custom Theme files -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="Static Login Form Responsive" />

	<script type="text/javascript" charset="utf-8" src="/assets/js/jquery/jquery.min.3.2.0.js"></script>
	<script type="text/javascript" charset="utf-8" src="/assets/js/jquery/jquery.form.min.js"></script>
	<c:set var="referURL" value="${header.referer}" />
	<script type="text/javascript">

		var refer = '${referURL}';
        $(document).ready(function () {

            $('form').ajaxForm({
                success: function(data){
                    if(data.resultCode=='success'){
                        //alert(data.resultMessage);
                        location.href = refer;
                    } else {
                        alert(data.resultMessage);
                        //location.href = "/";
                    }
                }
            });
            $("input").keypress(function(e){
                if(e.keyCode==13){
                    $(".loginSubmit").click();
                }
            });
            $(".loginSubmit").click(function() {
                $("#login").submit();
            })
			$('.ev').click(function(){
			    $('#umId').val($(this).attr('data'));
			    $(".loginSubmit").click();
			})


            // $("input").focus(function () {
            //     var val			= $(this).val();
            //     var dataId		= $(this).data("val");
            //     if(val == dataId) {
            //         $(this).val('');
            //     }
            //
            // }).blur(function () {
            //     var val			= $(this).val();
            //     var dataId		= $(this).data("val");
            //     if(val == dataId || val == '') {
            //         $(this).val(dataId);
            //     }
            // });
        });

        function goNewUser() {
            location.href =  "/static/newUser";
        }

	</script>
</head>

<style type="text/css">
	html,
body {
  height: 100%;
}

body {
  display: -ms-flexbox;
  display: -webkit-box;
  display: flex;
  -ms-flex-align: center;
  -ms-flex-pack: center;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: center;
  justify-content: center;
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #f5f5f5;
}

.form-signin {
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: 0 auto;
}
.form-signin .checkbox {
  font-weight: 400;
}
.form-signin .form-control {
  position: relative;
  box-sizing: border-box;
  height: auto;
  padding: 10px;
  font-size: 16px;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>
<body>


    <form class="form-signin" id="loginFrm" name="loginFrm" action="/static/ajax/loginInfo" method="post">
	  <div class="row">
		<div class="text-center col-12">
			<img class="mb-4" src="/assets/images/logo-light-icon.png" alt="" width="72" height="72">
		</div>
	  </div>
      <h1 class="h3 mb-3 font-weight-normal text-center">Please sign in</h1>
      <label for="inputEmail" class="sr-only">Email address</label>
      <input type="text" id="umId" name="umId" class="form-control" value="hsk3807" placeholder="아이디" required autofocus>
      <label for="inputPassword" class="sr-only">Password</label>
      <input type="password" name="umPw" class="form-control"  value="admin" placeholder="비밀번호" required>
      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me"> Remember me
			<br/>
			<a href="javascript:;" class="ev" data="hsk3807">[권한테스트]admin 로그인</a>
			<br/>
			<a href="javascript:;" class="ev" data="hsk380701">[권한테스트]ceo 로그인</a>
			<br/>
			<a href="javascript:;" class="ev" data="hsk380702">[권한테스트]일반 로그인</a>

        </label>
      </div>
      <button class="btn btn-lg btn-primary btn-block loginSubmit" type="submit">Sign in</button>
      <p class="mt-5 mb-3 text-muted text-center">&copy; 2018~Ing</p>
    </form>


</body>
</html>