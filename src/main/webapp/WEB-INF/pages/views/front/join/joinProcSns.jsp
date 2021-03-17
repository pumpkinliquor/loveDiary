<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp" %>

<script>
	var resultCode = '${resultMap.resultCode}';
	if (resultCode == 'S00') {
		goMain();
	} else if (resultCode == 'F01') {
		commonModalPopup('이미 등록된 계정입니다.');
		$(".popupConfirm").click({}, goJoin);
	} else if (resultCode == 'S00') {
		commonModalPopup('시스템 오류입니다.<br/>관리자에게 문의하세요.');
		$(".popupConfirm").click({}, goJoin);
	}
	
	function goMain(){
		window.location.href = '/front/furs/main';
	}
	
	function goJoin(){
		window.location.href = '/front/join';
	}
</script>