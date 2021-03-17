<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="Content-Script-Type" content="text/javascript">
    <meta http-equiv="Content-Style-Type" content="text/css">
    <meta http-equiv="pragma" content="no-cache">
    <title>이미지삽입</title>
    <link rel="stylesheet" type="text/css" href="../../css/editor.css" />
    <link href="../../css/imgupload.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
/* NHN Web Standard 1Team JJS 120106 */
/* Common */
body,p,h1,h2,h3,h4,h5,h6,ul,ol,li,dl,dt,dd,table,th,td,form,fieldset,legend,input,textarea,button,select{margin:0;padding:0}
body,input,textarea,select,button,table{font-family:'돋움',Dotum,Helvetica,sans-serif;font-size:12px}
img,fieldset{border:0}
ul,ol{list-style:none}
em,address{font-style:normal}
a{text-decoration:none}
a:hover,a:active,a:focus{text-decoration:underline}

/* Contents */
.blind{visibility:hidden;position:absolute;line-height:0}
#pop_wrap{width:383px}
#pop_header{height:26px;padding:14px 0 0 20px;border-bottom:1px solid #ededeb;background:#f4f4f3}
.pop_container{padding:11px 20px 0}
#pop_footer{margin:21px 20px 0;padding:10px 0 16px;border-top:1px solid #e5e5e5;text-align:center}
h1{color:#333;font-size:14px;letter-spacing:-1px}
.btn_area{word-spacing:2px}
.pop_container .drag_area{overflow:hidden;overflow-y:auto;position:relative;width:341px;height:129px;margin-top:4px;border:1px solid #eceff2}
.pop_container .drag_area .bg{display:block;position:absolute;top:0;left:0;width:341px;height:129px;background:#fdfdfd url(../../img/photoQuickPopup/bg_drag_image.png) 0 0 no-repeat}
.pop_container .nobg{background:none}
.pop_container .bar{color:#e0e0e0}
.pop_container .lst_type li{overflow:hidden;position:relative;padding:7px 0 6px 8px;border-bottom:1px solid #f4f4f4;vertical-align:top}
.pop_container :root .lst_type li{padding:6px 0 5px 8px}
.pop_container .lst_type li span{float:left;color:#222}
.pop_container .lst_type li em{float:right;margin-top:1px;padding-right:22px;color:#a1a1a1;font-size:11px}
.pop_container .lst_type li a{position:absolute;top:6px;right:5px}
.pop_container .dsc{margin-top:6px;color:#666;line-height:18px}
.pop_container .dsc_v1{margin-top:12px}
.pop_container .dsc em{color:#13b72a}
.pop_container2{padding:46px 60px 20px}
.pop_container2 .dsc{margin-top:6px;color:#666;line-height:18px}
.pop_container2 .dsc strong{color:#13b72a}
.upload{margin:0 4px 0 0;_margin:0;padding:6px 0 4px 6px;border:solid 1px #d5d5d5;color:#a1a1a1;font-size:12px;border-right-color:#efefef;border-bottom-color:#efefef;length:300px;}
:root  .upload{padding:6px 0 2px 6px;}
</style>
    <script language="javascript">

        var isGecko = true;
        function fileCheck(target, obj) {

            pathpoint = obj.lastIndexOf('.');
            filepoint = obj.substring(pathpoint + 1, obj.length);
            filetype = filepoint.toLowerCase();
            if (filetype == 'jpg' || filetype == 'gif' || filetype == 'png' || filetype == 'jpeg') {
                if (!isGecko) {
                    brower = navigator.userAgent.toUpperCase();
                    if (brower.indexOf('MSIE 7') != -1 || brower.indexOf('MSIE 8') != -1) {
                        //target.innerHTML = '<font color=\"B0B0B0\">미리보기는 MS IE 6.0 이하에서만<br>가능합니다.</font>';
                    } else {
                        target.innerHTML = "<img src='" + obj + "' width='220' height='143'>";
                    }
                } else {
                    //target.innerHTML = '<font color=\"B0B0B0\">미리보기는 MS IE계열만<br>가능합니다.</font>';
                }
            } else {
                alert('이미지 파일만 선택할 수 있습니다.');
                target.innerHTML = '';
                return false;
            }
        }

        function submitImageUploadForm(uploadForm) {
            var theFrm = document.editor_upimage;

            fileName = theFrm.update_image.value;
            if (fileName == "") {
                alert('본문에 삽입할 이미지를 선택해주세요.');
                return;
            }
            pathpoint = fileName.lastIndexOf('.');
            filepoint = fileName.substring(pathpoint + 1, fileName.length);
            filetype = filepoint.toLowerCase();
            if (filetype != 'jpg' && filetype != 'gif' && filetype != 'png' && filetype != 'jpeg' && filetype != 'bmp') {
                alert('이미지 파일만 선택할 수 있습니다.');
                self.close();
                return;
            }

            theFrm.imagepath.value = parent.parent.imagepath;
            try {
                theFrm.submit();
            } catch (e) {
                theFrm.reset();
                alert('파일을 업로드할 수 없습니다.');
                return;
            }
        }

        function closeWin() {
            parent.parent.oEditors.getById["<%=(""+request.getParameter("id")).replaceAll("<","&lt;").replaceAll(">","&gt;")%>"].exec("SE_TOGGLE_IMAGEUPLOAD_LAYER");
            return false;
        }

    </script>
</head>
<body>
<div id="pop_wrap">
    <!-- header -->
    <div id="pop_header">
        <h1>사진 첨부하기</h1>
    </div>

    <div id="pop_container2" class="pop_container2">
        <!-- content -->
<%--        <form id="editor_upimage" name="editor_upimage" action="imgUploadProc.jsp" method="post" enctype="multipart/form-data" onSubmit="return false;">--%>
<%--            <div id="pop_content2">--%>
<%--                <input type="file" class="upload" id="uploadInputBox" name="Filedata">--%>
<%--                <p class="dsc" id="info"><strong>10MB</strong>이하의 이미지 파일만 등록할 수 있습니다.<br>(JPG, GIF, PNG, BMP)</p>--%>
<%--            </div>--%>
<%--        </form>--%>
        <!-- //content -->
    </div>
    <div id="pop_container" class="pop_container" style="display:none;">
        <div class="drag_area" id="drag_area" style="display:none;"><em class="blind"></em></div>
    	<!-- content -->
<%--        <div id="pop_content">--%>
<%--	        <p class="dsc"><em id="imageCountTxt">0장</em>/10장 <span class="bar">|</span> <em id="totalSizeTxt">0MB</em>/50MB</p>--%>
<%--        	<!-- [D] 첨부 이미지 여부에 따른 Class 변화--%>
<%--            	 첨부 이미지가 있는 경우 : em에 "bg" 클래스 적용 //첨부 이미지가 없는 경우 : em에 "nobg" 클래스 적용   -->--%>

<%--            <div class="drag_area" id="drag_area">--%>
<%--				<ul class="lst_type" >--%>
<%--				</ul>--%>
<%--            	<em class="blind">마우스로 드래그해서 이미지를 추가해주세요.</em><span id="guide_text" class="bg"></span>--%>
<%--            </div>--%>
<%--			<div style="display:none;" id="divImageList"></div>--%>
<%--            <p class="dsc dsc_v1"><em>한 장당 10MB, 1회에 50MB까지, 10개</em>의 이미지 파일을<br>등록할 수 있습니다. (JPG, GIF, PNG, BMP)</p>--%>
<%--        </div>--%>
        <!-- //content -->
    </div>
        <div id="naver_common_editor">
            <form id="editor_upimage" name="editor_upimage" action="/static/imgUploadProc" method="post" enctype="multipart/form-data">
                <input type="hidden" name="imagepath">
                <input type="hidden" name="id" value="<%=(""+request.getParameter("id")).replaceAll("<","&lt;").replaceAll(">","&gt;")%>">
                <div class="pic_content" style="border:0;">
                    <p class="search">
                        <input type="file" name="update_image" style="width:222px;ime-mode:disabled" onchange="fileCheck(document.getElementById('update_image_view'), this.value);" onkeydown="return false">
                    </p>
                    <div class="pic_area" id="update_image_view">
                        저작권을 보호합시다.<br />
                        이용자 피해 방지와 관리자 보호를 위해 저작권 침해가 우려되는 파일의 사용을 일부 제한하고 있습니다.
                    </div>
                </div>

            </form>
        </div>
    <div id="pop_footer">
        <div class="btn_area">
            <a href="javascript:submitImageUploadForm(document.getElementById('editor_upimage'));"><img src="../../img/photoQuickPopup/btn_confirm.png" alt="확인" width="38" height="21" id="btn_confirm"></a>
            <a href="javascript:closeWin()"><img src="../../img/photoQuickPopup/btn_cancel.png" alt="취소" width="38" height="21" border="0" id="btn_cancel"></a>
        </div>
    </div>
</div>
<script type="text/javascript" src="jindo.min.js" charset="utf-8"></script>
<script type="text/javascript" src="jindo_fileUpload.js" charset="utf-8"></script>
<script type="text/javascript" src="QuickPhotoPopup.js" charset="utf-8"></script>

</body>
</html>
