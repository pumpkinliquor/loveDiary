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
            if(!postData['icmCompanyCode']){
                alert('사업장코드를 입력해주세요');
                return false;
            }
            if(!postData['ipmType']){
                alert('시설유형을 입력해주세요');
                return false;
            }
            if(!postData['ipmName']){
                alert('자산명을 입력해주세요');
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
        UI.makeTd('select','icmCompanyCode','사업장').appendTo(rowWrap).addItem(codes.icmCompanyCode);
        UI.makeTd('select','ipmType','시설유형').appendTo(rowWrap).addItem(codes.ibmType);
        UI.makeTd('text','ipmName','자산명').appendTo(rowWrap);
        UI.makeElement('td','',{style:'border-left:none'}).appendTo(rowWrap).append(UI.makeBtn('조회',lgUI.event.popupSearchForm,'', {class:'btn_small btn_save popup_search'}));
        rowWrap.addTo(table);

        var gridData =[],colData =[];


        /*
        rowMap.put("ilmAssetsNum" ,t_data.getANLN1()); //자산번호
                        rowMap.put("ilmLandName" ,t_data.getTXT50()); //자산명
                        rowMap.put("ilmDepartCode" ,t_data.getKOSTL());//부서코드
                        rowMap.put("ilmDepartName" ,t_data.getKTEXT()); //부서명
                        //rowMap.put("ibmType" ,t_data.getEQTYPE()); //시설유형
                        rowMap.put("ilmAcquisitionDate" ,t_data.getZUGDT()); //첫취득일
                        rowMap.put("ilmAcquisitionPrice" ,t_data.getKANSW()); //취득가
                        rowMap.put("ilmBookPrice" ,t_data.getBCHWRT()); //장부가
                        rowMap.put("ilmDeprPrice" ,t_data.getNAFAG()); //감가상각비
                        rowMap.put("ilmLandList",t_data.getANLN1()+'('+t_data.getTXT50()+')');
        * */

        var gridData =[],colData =[];

        colData.push({ label:'사업장코드',name:'icmCompanyCode',index:'ICM_COMPANY_CODE',classes:'celltl',hidden:true});
        colData.push({label: '건물 고유 코드', name: 'ipmCode', classes:'celltc'});
        colData.push({label: '건축물명칭', name: 'ipmName', classes:'celltl'});
        colData.push({label: '부서코드', name: 'ipmDepartCode', classes:'celltl'});
        colData.push({label: '부서명', name: 'ipmDepartName', classes:'celltl'});
        colData.push({label: '시설유형', name: 'ipmType',index:'A.IPM_TYPE',classes:'celltl',hidden: true});
        colData.push({label: '취득일', name: 'ipmAcquisitionDate', classes:'celltl'});
        colData.push({label: '전기취득가', name: 'ipmAcquisitionPriceL', classes:'celltr'});
        colData.push({label: '당기취득가', name: 'ipmAcquisitionPriceC', classes:'celltr'});
        colData.push({label: '취득가', name: 'ipmAcquisitionPrice', classes:'celltr',formatter: lgUI.grid.commaFormat});
        colData.push({label: '장부가', name: 'ipmBookPrice', classes:'celltl'});
        colData.push({label: '전기감가상각비', name: 'ipmDeprPriceL', classes:'celltl'});
        colData.push({label: '당기감가상각비', name: 'ipmDeprPriceC', classes:'celltl'});
        colData.push({label: '감가상각비', name: 'ipmDeprPrice', classes:'celltl'});
        colData.push({label: '투자부동산여부', name: 'ipmInvestmentYn', classes:'celltl'});
        colData.push({label: '비활성화일', name: 'ipmInactiveDate', classes:'celltl'});


        /*
        * rowMap.put("ibmBuildingCode", t_data.getANLN1()); //자산번호
                        rowMap.put("ibmBuildingName", t_data.getTXT50()); //자산명
                        rowMap.put("ibmDepartCode", t_data.getKOSTL());//부서코드
                        rowMap.put("ibmDepartName", t_data.getKTEXT()); //부서명
                        rowMap.put("ibmType", t_data.getEQTYPE()); //시설유형
                        rowMap.put("ibmAcquisitionDate", t_data.getZUGDT()); //첫취득일
                        rowMap.put("ibmAcquisitionPrice", t_data.getKANSW()); //취득가
                        rowMap.put("ibmBookPrice", t_data.getBCHWRT()); //장부가
                        rowMap.put("ibmDeprPrice", t_data.getNAFAG()); //감가상각비
                        rowMap.put("ibmBuildingList", t_data.getANLN1() + '(' + t_data.getTXT50() + ')');*/

        lgUI.grid.popupGrid = lgUI.grid.makeGrid('popupGridElement',colData,'/rest/sap/getPremiumList',{},{},{onSelectRow:lgUI.event.gridPopupClick});

    });


</script>
<div id="plusPopUpTabs" class="inner">
    <ul class="addTab">
        <li class="select"><a href="#tabs-grid" class="pageTitle">자산 연동 목록</a></li>
    </ul>

    <div id="tabs-grid">
        <div id="popupGridSearch" class="group_01"></div>
        <div class="group_02" >
            <table id="popupGridElement" >
                <caption>리스트 테이블</caption>
            </table>
        </div>
    </div>

</div>
