<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>

    $(document).ready(function(){

        lgUI.event.gridPopupClick=function(rowid){
             var rowData = lgUI.grid.popupGrid.getRowData(rowid);
            var wrapElement = $('#plustabs > .ui-tabs-panel').eq($('#plustabs > ul.addTab li.ui-tabs-active').index());
            if(rowData){
                rowData =$.extend({},rowData,{
                    irmAddress:rowData['ibmLocation']
                    ,irmAddressDetail:rowData['ibmDetail']
                    ,irmFacilityType:rowData['ibmType']
                    ,irmDepositCode:rowData['ibmDepartCode']

                })
            }
            if(rowid!=undefined)
            if(confirm('해당 정보를 선택하시겠습니까?')){
                $.each(rowData,function(k,v){
                    var objElement  =$('#'+k,wrapElement);
                   objElement.val($.trim(v));
                    try{
                        if(k=='icmCompanyCode') return true;
                        if(objElement.get(0).nodeName=='SELECT'){
                            objElement.change()
                        }
                    }  catch(e){

                    }
                });
                lgUI.event.selectCodeFloor(wrapElement,rowData['ibmBuildingCode']);
                LayerPopupUtils.close();
            }
            return false;
        }

        lgUI.event.popupSearchForm = function(){
            console.log('popupSearchForm');

            if($("#icmCountryCode").val() == '') {
                alert("사업장을 선택 해주세요.");
                return false;
            }

            if($("#ibmType").val() == '') {
                alert("시설유형을 선택 해주세요.");
                return false;
            }

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
        rowWrap.append(UI.makeElement('col','',{width:'8% '}));
        rowWrap.addTo(table);
        rowWrap = UI.makeElement('tr','',{'class':'rowWrap '});
        //UI.makeTd('select','icmCompanyCode','사업장').appendTo(rowWrap).addItem(codes.icmCompanyCode);
        UI.makeInput('hidden', '${param.icmCompanyCode}', {name: 'icmCompanyCode', id: 'icmCompanyCode'}).appendTo(rowWrap);
        UI.makeTd('select','ibmType','시설유형').appendTo(rowWrap).addItem(codes.EQTYPE,true);
        UI.makeTd('text','ibmBuildingName','건축물명').appendTo(rowWrap);
        UI.makeElement('td','',{style:'border-left:none'}).appendTo(rowWrap).append(UI.makeBtn('조회',lgUI.event.popupSearchForm,'', {class:'btn_small btn_save popup_search'}));
        rowWrap.addTo(table);

        var gridData =[],colData =[];

        colData.push({label: '사업장코드', name: 'icmCompanyCode', classes:'celltc',width:100});
        colData.push({label: '건물 고유 코드', name: 'ibmBuildingCode', classes:'celltc'});
        colData.push({label: '건축물명칭', name: 'ibmBuildingName', classes:'celltl'});
        colData.push({label: '부서코드', name: 'ibmDepartCode', classes:'celltl',hidden: true});
        colData.push({label: '부서명', name: 'ibmDepartName', classes:'celltl',hidden: true});
        colData.push({label: '시설유형', name: 'ibmType',index:'A.IBM_TYPE',classes:'celltl',hidden: true});
        colData.push({label: '취득일', name: 'ibmAcquisitionDate', classes:'celltl'});
        colData.push({label: '취득가', name: 'ibmAcquisitionPrice', classes:'celltr',formatter: lgUI.grid.commaFormat});
        colData.push({label: '장부가', name: 'ibmBookPrice', classes:'celltl'});
        colData.push({label: '감가상각비', name: 'ibmDeprPrice', classes:'celltl',hidden: true});
        colData.push({label: '주소(도로명)', name: 'ibmLocation',index:'A.IBM_LOCATION',classes:'celltl',hidden: true});
        colData.push({label: '주소(지번)', name: 'ibmJibun',index:'A.IBM_JIBUN',classes:'celltl',hidden: true});
        colData.push({label: '우편번호', name: 'ibmPostno',index:'A.IBM_POSTNO',classes:'celltl',hidden: true});
        colData.push({label: '상세주소', name: 'ibmDetail',index:'A.IBM_DETAIL',classes:'celltl',hidden: true});

        try{
            var objValue = '${objValue}'||'{}';
            objValue = $.parseJSON(objValue);
            if(Object.keys(objValue).length>0){
                $('.popupFlagTitle').show();
                lgUI.grid.popupGridx = lgUI.grid.makeGrid('popupGridSelectElement',colData,'/rest/building/buildingList',0,objValue,{shrinkToFit:false,width:'100%',height:'auto',onSelectRow:function(){}});
            } else {
                $('.popupFlagTitle').hide();
            }
        } catch(e){}

        lgUI.grid.popupGrid = lgUI.grid.makeGrid('popupGridElement',colData,'/rest/building/buildingList',{},{},{shrinkToFit:false,width:'100%',onSelectRow:lgUI.event.gridPopupClick});
    });


</script>
<div id="plusPopUpTabs" class="inner">
    <ul class="addTab">
        <li class="select"><a href="#tabs-grid" class="pageTitle">건물 연동 목록</a></li>
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
