<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    //${param}
    //${param.page}
    //
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
                            objElement.change();
                            objElement.find('option').prop('disabled', true);
                            objElement.select2('destroy');
                            objElement.select2({width:'100%'});
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
        rowWrap.append(UI.makeElement('col','',{width:'8% '}));
        rowWrap.addTo(table);
        rowWrap = UI.makeElement('tr','',{'class':'rowWrap '});
        //UI.makeInput('hidden','icmCompanyCode','사업장').appendTo(rowWrap).setValue('${param.icmCompanyCode}');
        UI.makeInput('hidden', '${param.icmCompanyCode}', {name: 'icmCompanyCode', id: 'icmCompanyCode'}).appendTo(rowWrap);
        UI.makeTd('text','iclBusinessNum','사업자번호').appendTo(rowWrap);
        UI.makeInput('hidden', 'C', {name: 'iclGubun', id: 'iclGubun'}).appendTo(rowWrap);
        UI.makeTd('text','iclName','고객명').appendTo(rowWrap);
        UI.makeElement('td','',{style:'border-left:none'}).appendTo(rowWrap).append(UI.makeBtn('조회',lgUI.event.popupSearchForm,'', {class:'btn_small btn_save popup_search'}));
        rowWrap.addTo(table);
        $(".select2_group",wrapElement).select2({width:'100%'});



        var gridData =[],colData =[];

        colData.push({ label:'사업장코드',name:'icmCompanyCode',index:'ICM_COMPANY_CODE',classes:'celltl',hidden:false,width:'120px'});
        colData.push({ label:'고객코드',name:'iclCompanyCode',index:'ICL_COMPANY_CODE',classes:'celltc',width:'120px'});
        colData.push({ label:'고객명',name:'iclCompanyName',index:'ICL_COMPANY_NAME',classes:'celltl',width:240});
        colData.push({ label:'법인명',name:'iclName',index:'ICL_NAME',classes:'celltc',hidden:true});
        colData.push({ label:'사업자번호',name:'iclBusinessNum',index:'ICL_BUSINESS_NUM',classes:'celltc'});
        colData.push({ label:'사업장소재지',name:'iclBusinessLocation',index:'ICL_BUSINESS_LOCATION',width:250,classes:'celltl',hidden:true});
        colData.push({ label:'사업장소재지',name:'iclBusinessPostno',index:'ICL_BUSINESS_POSTNO',width:250,classes:'celltl',hidden:true});
        colData.push({ label:'법인번호',name:'iclCorporationNum',index:'ICL_CORPORATION_NUM',classes:'celltc'});
        colData.push({ label:'업태',name:'iclIndustry',index:'ICL_INDUSTRY',classes:'celltl',hidden:true});
        colData.push({ label:'종목',name:'iclEvent',index:'ICL_EVENT',classes:'celltl',hidden:true});
        colData.push({ label:'구분자',name:'iclGubun',index:'ICL_GUBUN',classes:'celltl',hidden:true});
        colData.push({ label:'구분자',name:'iclType',index:'ICL_TYPE',classes:'celltl',hidden:true});
        colData.push({label: '관계사코드', name: 'iclAffiliatesCode', index: 'ICL_AFFILIATES_CODE', classes: 'celltl', hidden: true});
        colData.push({label: '관계사명', name: 'iclAffiliatesName', index: 'ICL_AFFILIATES_NAME', classes: 'celltl', hidden: true});
        colData.push({label: '삭제지시자', name: 'iclDelYn', index: 'ICL_DEL_YN', classes: 'celltl', hidden: true});
        colData.push({label: '전기보류지시자', name: 'iclHoldYn', index: 'ICL_HOLD_YN', classes: 'celltl', hidden: true});
        lgUI.grid.popupGrid = lgUI.grid.makeGrid('popupGridElement',colData,'/rest/sap/getClientList',true,{iclGubun:'C'},{onSelectRow:lgUI.event.gridPopupClick});

    });


</script>
<div id="plusPopUpTabs" class="inner">
    <ul class="addTab">
        <li class="select"><a href="#tabs-grid" class="pageTitle">고객 연동 목록</a></li>
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
