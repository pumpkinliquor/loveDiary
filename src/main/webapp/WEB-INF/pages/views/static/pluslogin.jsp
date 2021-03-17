<%@ page import="com.plushih.common.constant.LoginSession" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <meta name="title" content="Aigo Admin" />
    <title>Aigo Admin</title>

    <link rel="stylesheet" type="text/css" href="/admassets/css/admin.css" media="screen" />

    <script type="text/javascript" charset="utf-8" src="/assets/js/jquery/jquery.min.3.2.0.js"></script>
    <script type="text/javascript" charset="utf-8" src="/assets/js/jquery/jquery.form.min.js"></script>
    <%

        String retrunUrl = LoginSession.getReturnUrl(request.getSession());
    %>
    <c:set var="referURL" value="<%=retrunUrl%>" />

	<script type="text/javascript">

		var refer = '${referURL}';
		if(!refer){
		    refer ='/';
        }
        $(document).ready(function() {

            $('form').ajaxForm({
                success: function(data) {
                    if (data.resultCode == 'success') {
                        //alert(data.resultMessage);
                        location.href = refer;
                    } else {
                        if(data.resultMessage){
                        	$('.txt1').hide();
                            alert(data.resultMessage);
                        } else {
                        	$('.txt1').show();
                            //alert('로그인 정보가 틀렸습니다.');
                        }
                        //location.href = "/";
                    }
                }
            });
            $("input").keypress(function(e) {
                if (e.keyCode == 13) {
                    $(".loginSubmit").click();
                }
            });
            $(".loginSubmit").click(function() {
                $("#login").submit();
            })
            $('.ev').click(function() {
                $('#umId').val($(this).attr('data'));
                $(".loginSubmit").click();
            })

        });

        function goNewUser() {
            location.href = "/static/newUser";
        }

    </script>
</head>
<body>
<div class="admin_login_wrap">
	<div class="login_cont">
		<h1><img src="/admassets/images/logo.png" alt="Aigo" /></h1>
		<h2>ADMIN LOGIN</h2>
		<div class="login_box">
			<form class="form-signin" id="loginFrm" name="loginFrm" action="/static/ajax/loginInfo" method="post">
				<div class="login_frm">
					<div>
						<label for="umId">아이디</label><input type="text" class="ipt_id" name="umId" id="umId" value=""/>
					</div>
					<div>
						<label for="umPw">비밀번호</label>
						<input type="password" class="ipt_pw" name="umPw" id="umPw"  value="" />
					</div>
					<button type="submit" class="btn_login">로그인</button>

				</div>
				<p class="txt1 "  style="display: none;">※ 아이디/비밀번호가 정확하지 않습니다. </p>
			</form>
			<p class="txt2">접속이 안될 경우, 관리자에게 문의 주시기 바랍니다.<br>외부접속은 관리자에게 요청하셔야 접속이 가능합니다.</p>
		</div>

	</div>
</div>


</body>
</html>