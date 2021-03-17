"use strict";

window.BoardUtil = (function (BoardUtil, $, undefined) {
  var initParam = {};
  var emptyContentsRegex = /[<p>&nbsp;</p> | &nbsp;]/g;

  var init = function (param) {
    initParam = param;
  };

  /**
   * 스마트 에디터 초기화
   */
  var initEditor = function (_id,attr) {
    if(!attr) attr = {}
    nhn.husky.EZCreator.createInIFrame($.extend({
      oAppRef: oEditors,
      elPlaceHolder : _id,
      sSkinURI: contextPath + '/asset/smarteditor/SmartEditor2Skin.html',
      htParams: {
        bUseToolbar: true,
        bUseVerticalResizer: true,
        bUseModeChanger: true,
        fOnBeforeUnload: function () {
        }
      },
      fCreator: 'createSEditor2'
    },attr));
  };

  /**
   * 게시판 입력, 수정 폼에서 목록 클릭 시
   */
  var goToTheList = function () {
    $('#listBtn').on('click', function () {
      oEditors.getById['contents'].exec('UPDATE_CONTENTS_FIELD', []);
      var contentsValue = $.trim($('#contents').val());
      contentsValue = contentsValue.replace(emptyContentsRegex, '');
      if (!CommonUtils.isEmpty(contentsValue)) {
        if (!confirm('목록으로 이동하시겠습니까?\n수정 내용이 있다면 저장되지 않습니다.')) {
          return false;
        }
      }
    });
  };

  /**
   * 게시판 저장 및 수정
   */
  var setBoard = function () {
    $('#saveBtn').on('click', function () {
      AttachFileUtil.saveDeleteFile();
      $('#setBoardForm').ajaxSubmit({
        type: 'POST',
        dataType: 'json',
        mimeType: 'multipart/form-data',
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        url: contextPath + '/rest/admin/board/setBoard',
        beforeSerialize: function () {
          oEditors.getById['contents'].exec('UPDATE_CONTENTS_FIELD', []);
          var titleValue = $.trim($('#title').val());
          var contentsValue = $.trim($('#contents').val());
          contentsValue = contentsValue.replace(emptyContentsRegex, '');
          if (CommonUtils.isEmpty(titleValue)) {
            alert('제목을 입력해주세요.');
            $('#title').focus();
            return false;
          }

          if (initParam.boardTypeCode == 10) {
            var category = $.trim($('#selectCategory').val());
            if (CommonUtils.isEmpty(category)) {
              alert('카테고리를 선택해주세요.');
              return false;
            }
          } else if (initParam.boardTypeCode == 40) {
            var mission = $.trim($('#mission').val());
            var issue = $.trim($('#issue').val());
            var startDate = $.trim($('#startDate').val());
            var endDate = $.trim($('#endDate').val());
            var stateSelect = $('#stateSelect').val();

            if (CommonUtils.isEmpty(startDate)) {
              alert('기간을 설정해주세요.');
              $('#startDate').focus();
              return false;
            }

            if (CommonUtils.isEmpty(endDate)) {
              alert('기간을 설정해주세요.');
              $('#endDate').focus();
              return false;
            }

            if (CommonUtils.isEmpty(mission)) {
              alert('목표 내용을 입력해주세요.');
              $('#mission').focus();
              return false;
            }
            if (CommonUtils.isEmpty(issue)) {
              alert('이슈 내용을 입력해주세요.');
              $('#issue').focus();
              return false;
            }

            if (CommonUtils.isEmpty(stateSelect)) {
              alert('상태를 선택해주세요.');
              return false;
            }
          }


          if (CommonUtils.isEmpty(contentsValue)) {
            alert('내용을 입력해주세요.');
            oEditors.getById['contents'].exec('FOCUS');
            return false;
          }
        },
        success: function (commonResultEntity) {
          if (commonResultEntity.resultCode == 'success') {
            alert(commonResultEntity.resultMessage)

            var url = '/admin/{0}/list?boardGroupKey={1}'.format(initParam.boardUrl, initParam.boardGroupKey);
            if (initParam.boardNumber > 0) {
              var isProject = initParam.boardUrl.indexOf('project');
              if (isProject != -1) {
                initParam.boardUrl = 'intranet';
              }
              url = '/admin/{0}/detail?currentPage={1}&boardNumber={2}&boardGroupKey={3}{4}'.format(initParam.boardUrl,
                initParam.currentPage, initParam.boardNumber, initParam.boardGroupKey, initParam.searchParamString);
            }
            location.href = contextPath + url;

          } else {
            alert(commonResultEntity.resultMessage);
          }
        },
        error: AjaxHandler.error
      });
    });
  };

  /**
   * 게시판 삭제
   */
  var deleteBoard = function () {
    $('#delBtn').on('click', function () {
      AttachFileUtil.saveDeleteFile();
      $('#setBoardForm').ajaxSubmit({
        type: 'POST',
        dataType: 'json',
        url: contextPath + '/rest/admin/board/deleteBoard',
        beforeSerialize: function () {
          if (!confirm('한번 삭제한 게시물은 복구가 불가능합니다. 삭제하시겠습니까?')) {
            return false;
          }
        },
        success: function (commonResultEntity) {
          if (commonResultEntity.resultCode == 'success') {
            alert(commonResultEntity.resultMessage);
            location.href = contextPath + '/admin/{0}/list?currentPage={1}&boardGroupKey={2}'.format(initParam.boardUrl, initParam.currentPage, initParam.boardGroupKey);
          } else {
            alert(commonResultEntity.resultMessage);
          }
        },
        error: AjaxHandler.error
      });
    });
  };

  return {
    init: init,
    initEditor: initEditor,
    goToTheList: goToTheList,
    setBoard: setBoard,
    deleteBoard: deleteBoard
  }
})(window.BoardUtil || {}, jQuery);