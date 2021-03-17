<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${contextPath}/resources/smarteditor/photo_uploader/popup/photo_uploader.css"/>

<script type="text/javascript">
  jQuery(document).ready(function ($) {

    $('#attachBtn').on('click', function () {
      $('#editor_upimage').ajaxSubmit({
        type: 'POST',
        dataType: 'json',
        url: contextPath + '/rest/common/editorUploadImage',
        success: function (editorAttachResultEntity) {
          if (editorAttachResultEntity.resultCode != 'fail') {
            var fileName = editorAttachResultEntity.savedFile.originalFileName;
            var filePath = editorAttachResultEntity.savedFile.savedPath + '/' + editorAttachResultEntity.savedFile.savedFileName;
            pasteHTML(fileName, filePath);
            self.close();
          }
        },
        error: function () {
          alert('사진첨부에러');
        }
      });
    });
    $('#attachBtn5').on('click', function () {
      $('#editor_upimage5').ajaxSubmit({
        type: 'POST',
        dataType: 'json',
        url: contextPath + '/rest/common/editorUploadImage',
        success: function (editorAttachResultEntity) {
          if (editorAttachResultEntity.resultCode != 'fail') {
            var fileName = editorAttachResultEntity.savedFile.originalFileName;
            var filePath = editorAttachResultEntity.savedFile.savedPath + '/' + editorAttachResultEntity.savedFile.savedFileName;
            pasteHTML(fileName, filePath);
            self.close();
          }
        },
        error: function () {
          alert('사진첨부에러');
        }
      });
    });

    function pasteHTML(fileName, filePath) {
      var sHTML = '<img src="' + contextPath + '/common/imgView?fileName=' + fileName + '&filePath=' + filePath + '" style="max-width:800px;"/>';
      window.opener.parent.oEditors.getById["contents"].exec("PASTE_HTML", [sHTML]);
    }
  });
</script>
<body>
<div id="pop_wrap">
  <!-- header -->
  <div id="pop_header"><h1>사진 첨부하기</h1></div>
  <!-- //header -->
  <!-- container -->
  <!-- [D] HTML5인 경우 pop_container 클래스와 하위 HTML 적용
           그밖의 경우 pop_container2 클래스와 하위 HTML 적용      -->
  <div id="pop_container2" class="pop_container2">

    <!-- content -->
    <form id="editor_upimage" name="editor_upimage" method="post" enctype="multipart/form-data">
      <div id="pop_content2">
        <input type="file" class="upload" id="uploadInputBox" name="file">
        <p class="dsc" id="info"><strong>10MB</strong>이하의 이미지 파일만 등록할 수 있습니다.<br>(JPG, GIF, PNG, BMP)</p>
      </div>
    </form>

    <div id="pop_footer">
      <div class="btn_area">
        <a href="javascript:;" id="attachBtn"><img
                src="${contextPath}/resources/smarteditor/img/photoQuickPopup/btn_confirm.png" width="49" height="28"
                alt="확인"></a>
        <a href="javascript:self.close();"><img
                src="${contextPath}/resources/smarteditor/img/photoQuickPopup/btn_cancel.png" width="48" height="28" alt="취소"
                id="btn_cancel"></a>
      </div>
    </div>
    <!-- //content -->

  </div>
  <div id="pop_container" class="pop_container" style="display:none;">
    <!-- content -->
    <!-- <div id="pop_content">
        <p class="dsc"><em id="imageCountTxt">0장</em>/10장 <span class="bar">|</span> <em id="totalSizeTxt">0MB</em>/50MB</p>
        [D] 첨부 이미지 여부에 따른 Class 변화
             첨부 이미지가 있는 경우 : em에 "bg" 클래스 적용 //첨부 이미지가 없는 경우 : em에 "nobg" 클래스 적용

        <div class="drag_area" id="drag_area">
            <ul class="lst_type" >
            </ul>
            <em class="blind">마우스로 드래그해서 이미지를 추가해주세요.</em><span id="guide_text" class="bg"></span>
        </div>
        <div style="display:none;" id="divImageList"></div>
        <p class="dsc dsc_v1"><em>한 장당 10MB, 1회에 50MB까지, 10개</em>의 이미지 파일을<br>등록할 수 있습니다. (JPG, GIF, PNG, BMP)</p>
    </div> -->
    <form id="editor_upimage5" name="editor_upimage5" method="post" enctype="multipart/form-data"
          onSubmit="return false;">
      <div id="pop_content2">
        <input type="file" class="upload" id="uploadInputBox" name="file">
        <p class="dsc" id="info"><strong>5MB</strong>이하의 이미지 파일만 등록할 수 있습니다.<br>(JPG, GIF, PNG, BMP)</p>
      </div>
    </form>

    <div id="pop_footer">
      <div class="btn_area">
        <a href="javascript:;" id="attachBtn5"><img
                src="${contextPath}/resources/smarteditor/img/photoQuickPopup/btn_confirm.png" width="49" height="28" alt="확인"
                id="btn_confirm"></a>
        <a href="javascript:self.close();"><img
                src="${contextPath}/resources/smarteditor/img/photoQuickPopup/btn_cancel.png" width="48" height="28" alt="취소"
                id="btn_cancel"></a>
      </div>
    </div>
    <!-- //content -->
  </div>

  <!-- //container -->
  <!-- footer -->

  <!-- //footer -->
</div>

<script type="text/javascript" src="${contextPath}/resources/smarteditor/photo_uploader/popup/jindo.min.js"
        charset="utf-8"></script>

<script type="text/javascript" src="${contextPath}/resources/smarteditor/photo_uploader/popup/jindo.fileuploader.js"
        charset="utf-8"></script>

<script type="text/javascript" src="${contextPath}/resources/smarteditor/photo_uploader/popup/attach_photo.js"
        charset="utf-8"></script>

