<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>

    $(document).ready(function(){

        lgUI.event.gridPopupClick=function(rowid){
             var rowData = lgUI.grid.thisGridx.getRowData(rowid);
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

        var gridData =[],colData =[];

        colData.push({ label:'회사코드',name:'icmCompanyCode',classes:'celltc',width:100});
        colData.push({ label:'회사명',name:'icmCompanyName',classes:'celltl'});
        colData.push({ label:'대표자명',name:'icmName',width:100,classes:'celltl'});
        colData.push({ label:'법인번호',name:'icmCorporationNum',classes:'celltc',width:110,formatter: lgUI.grid.trimFormatter426});
        colData.push({ label:'사업자번호',name:'icmBusinessNum',classes:'celltc',width:110,formatter: lgUI.grid.trimFormatter324});
        colData.push({ label:'사업장소재지',name:'icmBusinessLocation',classes:'celltl',hidden:true});
        colData.push({ label:'업태',name:'icmIndustry',index:'ICM_INDUSTRY',classes:'celltl',width:150});
        colData.push({ label:'종목',name:'icmEvent',index:'ICM_EVENT',classes:'celltl',hidden:true});
        lgUI.grid.thisGridx = lgUI.grid.makeGrid('popupGridElement',colData,'/rest/sap/getCorporationList','',{icmCompanyCode:'${objValue}'},{onSelectRow:lgUI.event.gridPopupClick});
    });


</script>
<div id="plusPopUpTabs" class="inner">
    <ul class="addTab">
        <li class="select"><a href="#tabs-grid" class="pageTitle">사업장 연동 목록<p class="state"></p></a></li>
    </ul>

    <div id="tabs-grid">
        <div class="group_02" >
            <table id="popupGridElement" >
                <caption>리스트 테이블</caption>
            </table>
        </div>
    </div>

</div>
