<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>

    $(document).ready(function(){

        lgUI.event.gridPopupClick=function(rowid){
             var rowData = lgUI.grid.popupGrid.getRowData(rowid);
            var wrapElement = $('#plustabs > .ui-tabs-panel').eq($('#plustabs > ul.addTab li.ui-tabs-active').index());
            if(rowid!=undefined)
            if(confirm('해당 정보를 선택하시겠습니까?')){
                $.each(rowData,function(k,v){
                    var objElement  =$('#'+k,wrapElement);
                   objElement.val($.trim(v));
                    try{
                        if(objElement.get(0).nodeName=='SELECT'){
                            objElement.change()
                        }
                    }  catch(e){

                    }
                });
                LayerPopupUtils.close();
            }
            return false;
        }

        lgUI.event.popupSearchForm = function(){
            console.log('popupSearchForm');
            var postData = $('#popupGridSearch').domJson();
            console.log(postData);
            if(!postData['icmCompanyCode']){
                alert('사업장코드를 입력해주세요');
                return false;
            }
            lgUI.grid.popupGrid.setGridParam({
                datatype	: "json",
                postData	: postData,
                loadComplete	: function(data) {
                    try{
                            initPage(lgUI.grid.popupGrid.attr('id'), lgUI.grid.popupGrid.attr('id')+'Paging', true, "TOT");
                        } catch(ex){

                        }
                }
            }).trigger("reloadGrid");
        }

        var box,rowWrap,wrapElement = $('#popupGridSearch');
        box = UI.makeElement('div','',{'class':'detail_tb pd0'}).appendTo(wrapElement);
        table = UI.makeElement('table','',{cellpadding:0, cellspacing:0,'class':'detail_tb'}).appendTo(box);
        rowWrap = UI.makeElement('colgroup','',{'class':'rowWrap '});
        rowWrap.append(UI.makeElement('col','',{width:'10% '}));
        rowWrap.append(UI.makeElement('col','',{width:'20% '}));
        rowWrap.append(UI.makeElement('col','',{width:'15% '}));
        rowWrap.append(UI.makeElement('col','',{width:'20% '}));
        rowWrap.append(UI.makeElement('col','',{width:'15% '}));
        rowWrap.append(UI.makeElement('col','',{width:'20% '}));
         rowWrap.append(UI.makeElement('col','',{width:'15% '}));
        rowWrap.append(UI.makeElement('col','',{width:'20% '}));
        rowWrap.append(UI.makeElement('col','',{width:'8% '}));
        rowWrap.addTo(table);
        rowWrap = UI.makeElement('tr','',{'class':'rowWrap '});
        UI.makeInput('hidden', 'RENT', {name: 'irmCorporationType', id: 'irmCorporationType'}).appendTo(rowWrap);
        //UI.makeTd('select','icmCompanyCode','사업장').appendTo(rowWrap).addItem(codes.icmCompanyCode);
        UI.makeInput('hidden', '${param.icmCompanyCode}', {name: 'icmCompanyCode', id: 'icmCompanyCode'}).appendTo(rowWrap);
        //UI.makeTd('select','iclGubun','고객/구매처').appendTo(rowWrap).addItem(codes.iclGubun);
        UI.makeTd('text','iclCompanyName','고객명').appendTo(rowWrap);

        //UI.makeTd('text','ilmLandName','토지명').appendTo(rowWrap);
        UI.makeTd('text','ilmJibun','건물명').appendTo(rowWrap);

        UI.makeElement('td','',{style:'border-left:none'}).appendTo(rowWrap).append(UI.makeBtn('조회',lgUI.event.popupSearchForm,'', {class:'btn_small btn_save popup_search'}));
        rowWrap.addTo(table);
        $(".select2_group",wrapElement).select2({width:'100%'});

        var gridData =[],colData =[];
        colData.push({label: '사업장코드', name: 'icmCompanyCode', classes:'celltc',width:100});
        colData.push({ label:'계약번호',name:'irmContractNum',classes:'celltc',width:'100px'});
        colData.push({ label:'고객명',name:'iclCompanyName',classes:'celltl',width:'170px'});
        colData.push({ label:'빌딩명',name:'ibmBuildingName',width:'170px'});
        colData.push({ label:'층명',name:'ibfFloorName',hidden:true});
        colData.push({ label:'룸명',name:'ibrRoomName',hidden:true});
        colData.push({ label:'토지명',name:'ilmLandName',width:'170px'});
        colData.push({ label:'토지주소',name:'ibmLocation',width:'210px'});
        colData.push({ label:'계약시작',name:'irmStartDate',classes:'celltl',width:'90px',hidden:true,formatter: lgUI.grid.dateFormatter});
        colData.push({ label:'만기일자',name:'irmEndDate',classes:'celltl',width:'90px',hidden:true,formatter: lgUI.grid.dateFormatter});
        try{
            var objValue = '${objValue}'||'{}';
            objValue = $.parseJSON(objValue);
            if(Object.keys(objValue).length>0){
                $('.popupFlagTitle').show();
                lgUI.grid.popupGridx = lgUI.grid.makeGrid('popupGridSelectElement',colData,'/rest/rent/rentList',0,objValue,{height:'auto',onSelectRow:function(){}});
            } else {
                $('.popupFlagTitle').hide();
            }
        } catch(e){

        }



        lgUI.grid.popupGrid = lgUI.grid.makeGrid('popupGridElement',colData,'/rest/rent/rentList',{},{irmCorporationType:'RENT'},{onSelectRow:lgUI.event.gridPopupClick});

    });


</script>
<div id="plusPopUpTabs" class="inner">
    <ul class="addTab">
        <li class="select"><a href="#tabs-grid" class="pageTitle">임대 목록</a></li>
    </ul>

    <div id="tabs-grid">
        <h3 class="popupFlagTitle" style="display: none">현재 선택된 데이터</h3>
        <div class="group_02 notpaging" >
            <table id="popupGridSelectElement" >
                <caption>리스트 테이블</caption>
            </table>
        </div>
        <div id="popupGridSearch" class="group_01"></div>
        <div class="group_02" >
            <table id="popupGridElement" >
                <caption>리스트 테이블</caption>
            </table>
        </div>
    </div>

</div>
