<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp" %>

<script>
	var resultCode = '${resultMap.resultCode}';
	if (resultCode == 'S00') {
		window.location.href = '/front/main';
	} else if (resultCode == 'F01') {
		alert('이미 등록된 계정입니다.');
	} else if (resultCode == 'S00') {
		alert('시스템 오류입니다.\n관리자에게 문의하세요.');
	}
</script>