<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp" %>
<script>
	var resultCode = '${resultMap.resultCode}';
	console.log(resultCode);
	if(resultCode == '91'){
		window.location.href = '/front/main';
	} else if (resultCode == '92') {
		window.location.href = '/front/pre/main';
	} else if (resultCode == '93') {
		window.location.href = '/front/learn/home';
	} else if (resultCode == '30') {
		alert('등록된 계정이 아닙니다.\n회원가입을 통해 계정을 만드세요.');
		goLoginPage();
	} else {
		alert('SNS 계정연동에 실패하였습니다.');
		goLoginPage();
	}
</script>