<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(document).ready(function () {
      /*  var gridData = [], colData = [];


        var noticeList  = $/{noticeList};

        var obj     =  "";
        $.each(noticeList, function(index, value) {
            obj     +=
                '<tr>'+
                '<td>'+value.inRegDate+'</td>'+
                '<td>'+value.inTitle+'</td>'+
                '<td>'+value.inContents+'</td></tr>';
        });
        $("#gridElement tbody").html(obj);
    });

    var oEditors = [];
    var codes = {};
    var BlockUtils2 = null;
    var rules = function() {
        var rulObj = {
            inTitle:{
                "required": true
            }
        };
        return rulObj;*/
    });
    $(document).ready(function(){
        $.ajaxSetup({async: false});
        /** 공통코드 */
        $.ajaxSetup({async: true});

        /** 기본설정 (탭표현 필드, Insert URL, UPDATE URL, DELETE URL) */
        lgUI.Layout.init('inTitle','/rest/notice/noticeInsert', '/rest/notice/updateNotice', '/rest/notice/deleteNotice', '');

        lgUI.tab.thisTab= $( '#plusPopUpTabs' ).tabs();


        lgUI.event.checkBtnClick=function(inSeq){
            console.log(inSeq);
            var pageContent = $('.pageContent').has($(this));
            console.log(pageContent);
        }


        lgUI.event.gridDblClickAfter=function(pageContentLast,data,state){

            $("#popupGridElement").find("td[title="+data.inSeq+"]").parent().find("td:eq(6)").text("Y");

            var box,rowWrap,wrapElement = pageContentLast;

            box = UI.makeElement('div','',{class:'detail_tb pd0'}).appendTo(wrapElement);

            table = UI.makeElement('table','',{cellpadding:0, cellspacing:0,'class':'detail_tb'}).appendTo(box);
            rowWrap = UI.makeElement('tr','',{class:'rowWrap  hide'});
            UI.makeTd('text','inSeq','시퀀스').appendTo(rowWrap).last().append(UI.makeInput('hidden','',{'name':'mode','id':'mode'}));

            //UI.makeTd('text.display disabled','inView','조회수').appendTo(rowWrap);

            rowWrap.addTo(table);
            rowWrap = UI.makeElement('tr','',{class:'rowWrap '});
            UI.makeTd('','inTitle','타이틀').appendTo(rowWrap);
            UI.makeTd('select','inType','타입').appendTo(rowWrap).addItem({'01':'임대', '02':'임차'});
            rowWrap.addTo(table);
            rowWrap.addTo(table);
            rowWrap = UI.makeElement('tr','',{class:'rowWrap '});
            UI.makeTd('textarea','inContents','내용',{field:{idstr:'inContents'+UI.getId()}}).appendTo(rowWrap).last().attr('colspan',3);
            rowWrap.addTo(table);

            switch (state){
                case lgUI.STATE.INFO:
                    /** 버튼생성 */
                    rowWrap = UI.makeElement('div','',{class:'rowWrap trbtn'});
//                    UI.makeBtn('확인',lgUI.event.cancleBtnClick,'', {class:"btn_small btn_save"}).appendTo(rowWrap);
                    //UI.makeBtn('삭제',lgUI.event.delBtnClick,'',{class:'btn_small btn_delete'}).appendTo(rowWrap);
                    rowWrap.addTo(box);
                    /** 버튼생성 END*/

                    $.each(data,function(i,v){
                        $('td').has($('#'+i,wrapElement)).html(v);
                    });
                    break;
            }
            var dataObj    = {'inSeq':data.inSeq};

            AjaxHandler.submitAjaxData("/rest/notice/updateNotice", dataObj);

        }

//        UI.makeBtn('공지사항 등록',lgUI.event.gridNewForm,'fa fa-save').appendTo($('#gridBtn')).width('150px');


        var gridData =[],colData =[];

        colData.push({ label:'SEQ',name:'inSeq',index:'IN_SEQ',align:'center',hidden:true});
        colData.push({ label:'수신자',name:'iumId',index:'IUM_ID',align:'center'});
        colData.push({ label:'타입',name:'inType',index:'IN_TYPE',align:'center',code:{'01':'임대', '02':'임차'},formatter:lgUI.grid.codeFormat});
        colData.push({ label:'제목',name:'inTitle',index:'IN_TITLE',align:'center'});
        colData.push({ label:'내용',name:'inContents',index:'IN_CONTENTS',align:'center'});
        colData.push({ label:'확인여부',name:'inCheckYn',index:'IN_CHECK_YN',align:'center'});
//        colData.push({ label:'확인날짜',name:'inCheckDate',index:'IN_CHECK_DATE',align:'center'});
        lgUI.grid.thisGrid = lgUI.grid.makeGrid('popupGridElement',colData,'/rest/notice/getNotice');


    });


</script>
<div id="plusPopUpTabs" class="inner">
    <ul class="addTab">
        <li class="select"><a href="#tabs-grid" class="pageTitle">알림목록</a></li>
        <%--<li><a href="">타이틀 타이틀 타이 ...<span><img src="${contextPath}/asset/img/icon_tab_colse.png" alt="닫기" /></span></a></li>--%>
    </ul>

    <div id="tabs-grid">
        <div class="group_02" >
            <table id="popupGridElement" >
                <caption>리스트 테이블</caption>
            </table>
        </div>
    </div>

</div>
