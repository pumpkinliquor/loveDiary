<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>popup</title>
<link rel="stylesheet" href="/main/css/cms/layout.css" type="text/css" />
<script type="text/javascript" src="/main/js/sh/jquery-1.7.2.min.js" ></script>
<script type="text/javascript" src="/main/js/sh/cms-common.js" ></script>
<script type="text/javascript">
//<![CDATA[
	
	var deny_ext = "gif,bmp,jpg,png,jpeg";
	var allow_ext = true;
	
	function FSubmit(){
		alert("dddddaa");
		if(deny_ext.length>0){
			deny_exts=","+deny_ext.replace(/ /g,'').toLowerCase()+",";
			if($("#upload").val().length>0){
				var ext=$("#upload").val().substring($("#upload").val().lastIndexOf('.')+1).toLowerCase();
				if(allow_ext){
					if(deny_exts.indexOf(ext)<0){
						alert("업로드가 제한된 파일입니다.");
						$("#upload").focus();
						return false;
					}
				}else{
					if(deny_exts.indexOf(ext)>=0){
						alert("업로드가 제한된 파일입니다.");
						$("#upload").focus();
						return false;
					}
				}
			}
		}
		
		document.mainform.submit();
	}
//]]>
</script>
<style type="text/css">
<!--
div {
	/* outline:1px solid #09f; */
}
-->
</style>
</head>

<body class="nobackground">
<form name="mainform" action="FileUploader.jsp" method="post" enctype="multipart/form-data" onsubmit="return false;">
<input type="hidden" name="file_seq" value="${file_seq}"/>
<input type="hidden" name="file_nm" value="${file_nm}"/>
<div id="popup">
	<div>
		<div id="popupTitle">
			<img src="/main/images/cms/popup_title1.gif" alt="파일 업로드" class=popuptitle />
			<a href="#none" onclick="window.close();"><img src="/main/images/cms/popup_close1.gif" alt="창닫기" class="popupclose"/></a>
		</div>
		<div id="popupSearch" class="mgt16">
			<span class="title"><label for="upload">파일선택</label></span>
			<span><input type="file" name="upload" id="upload" class="text date10 mgr5" /></span>
		</div>
		
		<div id="popupButton">
			<input type="image" src="/main/images/cms/submit2_btn.gif" alt="확인" class="mgr10" onclick="FSubmit();"/><a href="#popupTitle" onclick="window.close();"><img src="/main/images/cms/cancel1_btn.gif" alt="취소" /></a>
		</div>
	</div>
</div>
</form>
</body>
</html>
