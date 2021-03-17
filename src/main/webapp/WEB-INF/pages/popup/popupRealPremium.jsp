<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>

    $(document).ready(function(){

        lgUI.event.gridPopupClick=function(rowid){
             var rowData = lgUI.grid.popupGrid.getRowData(rowid);
            var wrapElement = $('#plustabs > .ui-tabs-panel').eq($('#plustabs > ul.addTab li.ui-tabs-active').index());
            if(rowid!=undefined)
            if(confirm('해당 정보를 선택하시겠습니까?')){
                rowData = {'irmPrestigeCode':rowData['ipmCode'],'irmPrestigePrice':rowData['ipmPrice']};
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
        UI.makeTd('select','ipmType','자산타입').appendTo(rowWrap).addItem(codes.EQTYPE,true);
        UI.makeTd('text','ipmName','자산명').appendTo(rowWrap);


        UI.makeElement('td','',{style:'border-left:none'}).appendTo(rowWrap).append(UI.makeBtn('조회',lgUI.event.popupSearchForm,'', {class:'btn_small btn_save popup_search'}));
        rowWrap.addTo(table);
        $(".select2_group",wrapElement).select2({width:'100%'});
        var gridData =[],colData =[];
        colData.push({label:'SEQ',name:'ipmSeq',index:'IPM_SEQ',align: 'center',hidden: true});
        colData.push({label:'등록일', name: 'ipmRegDate', align: 'center', hidden: true});
        colData.push({label:'수정일', name: 'ipmUpdDate', align: 'center', hidden: true});
        colData.push({label:'등록 / 수정자 아이디', name: 'ipmId', align: 'center', hidden: true});
        colData.push({label:'사업장코드', name: 'icmCompanyName', align: 'center',hidden:true});
        colData.push({label: '사업장코드', name: 'icmCompanyCode', classes:'celltc',width:100});
        colData.push({label:'고객명',name:'iclCompanyName',align:'center'});
        colData.push({label:'고객코드',name:'iclCompanyCode',index:'ICL_COMPANY_CODE',align:'center',hidden:true});
        colData.push({label:'자산타입',name:'ipmTypeName',index:'IPM_TYPE_NAME',align:'center'});
        colData.push({label:'자산타입',name:'ipmType',index:'IPM_TYPE',align:'center',hidden:true});
        colData.push({label:'임대/임차',name:'ipmRentTypeName',index:'IPM_RENT_TYPE_NAME',align:'center'});
        colData.push({label:'임대/임차',name:'ipmRentType',index:'IPM_RENT_TYPE',align:'center',hidden:true});
        colData.push({label:'자산코드', name: 'ipmCode', align: 'center',hidden:true});
        colData.push({label:'자산명', name: 'ipmName', align: 'center'});
        colData.push({label:'권리금',name:'ipmPrice',align:'center'});
        colData.push({label:'계약일자',name:'ipmDate',align:'center',hidden:true});
        colData.push({label:'계약금',name:'ipmContractPrice',align:'center',hidden:true});
        colData.push({label:'계약금 지급일',name:'ipmContractDate',align:'center',hidden:true});
        colData.push({label:'잔금',name:'ipmBalancePrice',align:'center',hidden:true});
        colData.push({label:'잔금 지급일',name:'ipmBalanceDate',align:'center',hidden:true});
        try{

            var objValue = '${objValue}'||'{}';
            objValue = $.parseJSON(objValue);
            if(Object.keys(objValue).length>0){

                $('.popupFlagTitle').show();
                lgUI.grid.popupGridx = lgUI.grid.makeGrid('popupGridSelectElement',colData,'/rest/premium/premiumList',0,objValue,{height:'auto',onSelectRow:function(){}});
            } else {
                $('.popupFlagTitle').hide();
            }
        } catch(e){

        }
        lgUI.grid.popupGrid = lgUI.grid.makeGrid('popupGridElement',colData,'/rest/premium/premiumList',{},{},{onSelectRow:lgUI.event.gridPopupClick});

    });


</script>
<div id="plusPopUpTabs" class="inner">
    <ul class="addTab">
        <li class="select"><a href="#tabs-grid" class="pageTitle">권리금 목록</a></li>
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
