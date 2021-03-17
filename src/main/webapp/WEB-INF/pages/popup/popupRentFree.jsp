<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(function() {

        var objValue = [];
        try{
            objValue = '${objValue}';
            objValue = $.parseJSON(objValue);
        } catch(ex){

        }
        var table, th, td, box, rowWrap;

        var wrapElement = $("#popupGridSearch");

        lgUI.event.popupGridSearchAdd=function(){
            var targetTr = $('tr').has($(this));
            var irfStartDate = targetTr.find('.inp.irfStartDate').val();
            var irfEndDate = targetTr.find('.inp.irfEndDate').val();
            var irfMemo = targetTr.find('.inp.irfMemo').val();
            var rowWrap = UI.makeElement('tr','',{class:'rowWrap '});
            UI.makeTd('text.irfStartDate','irfStartDate','시작일').appendTo(rowWrap).find('input').readonly().val(irfStartDate);
            UI.makeTd('text.irfEndDate','irfEndDate','종료일').appendTo(rowWrap).find('input').readonly().val(irfEndDate);
            UI.makeTd('text.irfMemo','irfMemo','특이사항').appendTo(rowWrap).find('input').readonly().val(irfMemo);
            var td= UI.makeElement('td','','').appendTo(rowWrap);
            //UI.makeBtn('삭제', lgUI.event.popupGridDel, '', {'class':"btn_size btn_exsmall add"}).appendTo(td);
            targetTr.find('input').val('');

            rowWrap.addTo($("#popupContents > div > table"));
            lgUI.event.itemCount();
        }
        lgUI.event.popupGridSearchAddValue=function(json){
            var targetTr = $('tr').has($(this));
            var irfStartDate = json['irfStartDate'];
            var irfEndDate = json['irfEndDate'];
            var irfMemo = json['irfMemo'];
            var rowWrap = UI.makeElement('tr','',{class:'rowWrap '});
            UI.makeTd('text.irfStartDate','irfStartDate','시작일').appendTo(rowWrap).find('input').readonly().val(irfStartDate);
            UI.makeTd('text.irfEndDate','irfEndDate','종료일').appendTo(rowWrap).find('input').readonly().val(irfEndDate);
            UI.makeTd('text.irfMemo','irfMemo','특이사항').appendTo(rowWrap).find('input').readonly().val(irfMemo);
            var td= UI.makeElement('td','','').appendTo(rowWrap);
            //td.append('<button class="btn_size btn_exsmall del">삭제</button>');
            //UI.makeBtn('삭제', lgUI.event.popupGridDel, '', {'class':"btn_size btn_exsmall add"}).appendTo(td);

            rowWrap.addTo($("#popupContents > div > table"));
            lgUI.event.itemCount();
        }
        lgUI.event.popupGridDel=function(){
            if(confirm('해당 정보를 삭제하시겠습니까')){
                var targetTr = $('tr').has($(this));
                targetTr.remove();
            }
            lgUI.event.itemCount();
        }
        lgUI.event.itemCount=function(){

            $('.popupGridTitle').html('등록된 렌트프리 '+$('#popupContents tr').length+' 건');
        }
        lgUI.event.savefreeBtnClick=function(){

            var wrap =$('#plustabs > .ui-tabs-panel').eq($('#plustabs > ul li.ui-tabs-active').index());;
            var tableEl= $('.freeList');
            var arrData = [];
            tableEl.find('tr').each(function(i){
                var irfStartDate = $(this).find('.inp.irfStartDate').val()
                var irfEndDate = $(this).find('.inp.irfEndDate').val();
                var irfMemo = $(this).find('.inp.irfMemo').html();
                var jData = {'irfStartDate':irfStartDate,'irfEndDate':irfEndDate,'irfMemo':irfMemo};
                arrData.push(jData)
            });

            wrap.find('#rentFree').val(JSON.stringify(arrData));
            wrap.find('.rentFreeStat').html('상태 :'+(arrData.length)+'건 등록대기');
            lgUI.event.itemCount();
            if(confirm('렌트 프리등록을 하시겠습니까?\r렌트프리는 임대차 정보 완료후 반영됩니다.'))
                LayerPopupUtils.close();
        }

        box = UI.makeElement('div','',{class:'detail_tb pd0'}).appendTo(wrapElement);
        table = UI.makeElement('table','',{cellpadding:0, cellspacing:0,'class':'detail_tb'}).appendTo(box);
        rowWrap = UI.makeElement('colgroup','',{'class':'rowWrap '});
        rowWrap.append(UI.makeElement('col','',{width:'10% '}));
        rowWrap.append(UI.makeElement('col','',{width:'20% '}));
        rowWrap.append(UI.makeElement('col','',{width:'10% '}));
        rowWrap.append(UI.makeElement('col','',{width:'20% '}));
        rowWrap.append(UI.makeElement('col','',{width:'10% '}));
        rowWrap.append(UI.makeElement('col','',{width:'20% '}));
        rowWrap.append(UI.makeElement('col','',{width:'10% '}));
        rowWrap.addTo(table);
        rowWrap = UI.makeElement('tr','',{class:'rowWrap '});
        UI.makeTd('calendar.irfStartDate','irfStartDate','시작일').appendTo(rowWrap).addClass('col02');
        UI.makeTd('calendar.irfEndDate','irfEndDate','종료일').appendTo(rowWrap).addClass('col02');
        UI.makeTd('text.irfMemo','irfMemo','특이사항').appendTo(rowWrap).addClass('col02');
        td= UI.makeElement('td','','').appendTo(rowWrap);
        UI.makeBtn('추가', lgUI.event.popupGridSearchAdd, '', {'class':"btn_size btn_exsmall add"}).appendTo(td);
        //td.append('<button class="btn_size btn_exsmall add">추가</button>');
        //td.append('<button class="btn_size btn_exsmall del">삭제</button>');

        rowWrap.addTo(table);
        lgUI.event.datepicker(wrapElement);

        var wrapElementContent = $("#popupContents");

        box = UI.makeElement('div','',{class:'detail_tb pd0'}).appendTo(wrapElementContent);
        table = UI.makeElement('table','',{cellpadding:0, cellspacing:0,'class':'detail_tb freeList'}).appendTo(box);
        rowWrap = UI.makeElement('colgroup','',{'class':'rowWrap '});
        rowWrap.append(UI.makeElement('col','',{width:'10% '}));
        rowWrap.append(UI.makeElement('col','',{width:'20% '}));
        rowWrap.append(UI.makeElement('col','',{width:'10% '}));
        rowWrap.append(UI.makeElement('col','',{width:'20% '}));
        rowWrap.append(UI.makeElement('col','',{width:'10% '}));
        rowWrap.append(UI.makeElement('col','',{width:'20% '}));
        rowWrap.append(UI.makeElement('col','',{width:'10% '}));
        rowWrap.addTo(table);


        rowWrap = UI.makeElement('div','',{'class':'rowWrap tc'});
        UI.makeBtn('저장', lgUI.event.savefreeBtnClick, '', {'class':"btn_normal btn_save"}).appendTo(rowWrap);
        UI.makeBtn('취소',LayerPopupUtils.close,'',{'class':'btn_normal btn_delete'}).appendTo(rowWrap);
        rowWrap.addTo($('#plusPopUpTabs #tabs-grid'));


        console.log(objValue.length)
        if(objValue.length>0){
            $.each(objValue,function(i,v){
                lgUI.event.popupGridSearchAddValue(v);
            });
        }
        lgUI.event.itemCount();
        $('#plusPopUpTabs').focus();



    });



</script>
<div id="plusPopUpTabs" class="inner">
    <ul class="addTab">
        <li class="select"><a href="#tabs-grid" class="pageTitle">렌트프리</a></li>
    </ul>

    <div id="tabs-grid">

        <div id="popupGridSearch" class="group_02"></div>
        <h4 class="popupGridTitle">등록된 렌트프리</h4>
        <div class="group_02" >
            <div id="popupContents"></div>
        </div>
    </div>

</div>


