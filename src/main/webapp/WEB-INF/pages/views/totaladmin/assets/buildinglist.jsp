<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<div class="row page-titles">
</div>
<div id="tabs">
    <ul class="addTab">
        <li><a href="#tabsPage01">자산목록</a></li>
    </ul>
    <div id="tabsPage01">
    </div>
</div>
<script type="text/javascript">
$(document).ready(function(){
    $.call('/ajax/codeList',{codes:'BI,AB_TYPE'},function(r){
        $.extend(plus.codes,r.codes);
    });
    /* 타이틀 항목*/
    $('.page-titles').append($('#tplPageTitle').tmpl({pageTitle:'자산관리',newTabTitle:'자산 등록'}));

    /* tab 생성*/
    plus.tab.thisTab = $('#tabs').tabs();

    /* tab 생성후 초기이벤트*/
    plus.event.tabAfter=function(rowData){
        var pageContentLast = $('.pageContent:last');
        var tableElement =pageContentLast.find('table');

            plus.makeInput('hidden','abSeq').prependTo(pageContentLast);
            tr = plus.makeElement('colgroup').appendTo(tableElement);
                plus.makeElement('col','',{width:'10%'}).appendTo(tr);
                plus.makeElement('col','',{width:'15%'}).appendTo(tr);
                plus.makeElement('col','',{width:'10%'}).appendTo(tr);
                plus.makeElement('col','',{width:'15%'}).appendTo(tr);
	// tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
	// 	plus.makeTd('text','bc_seq','사업고객SEQ').appendTo(tr);
	tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('select','biSeq','사업자SEQ').appendTo(tr).addItem(plus.codes.BI,true);
		plus.makeTd('select','abType','자산타입').appendTo(tr).addItem(plus.codes.AB_TYPE,true);
	tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('text','abName','자산명칭').appendTo(tr);
	tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('text','abArea03','대지면적[m2]').appendTo(tr);
		plus.makeTd('text','abArea06','연면적[m2]').appendTo(tr);
	tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('text','abArea01','건축면적[m2]').appendTo(tr);
		plus.makeTd('text','abArea05','조경면적[m2]').appendTo(tr);
		plus.event.addAddress(rowData,tableElement,mode);
	tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('text','abUse','주용도').appendTo(tr);
		plus.makeTd('text','abStructure','주구조').appendTo(tr);

	tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('text','abGround','지상층').appendTo(tr);
		plus.makeTd('text','abUnderGround','지하층').appendTo(tr);
		tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('text','abCoverage','건폐율(%)').appendTo(tr);
		plus.makeTd('text','abFloorArea','용적율(%)').appendTo(tr);
        plus.event.formAfter(pageContentLast,rowData);

        if(Object.keys(rowData).length>0){
            var wrap  =plus.tab.getActiveTab();
            wrap.find('.makeDepthSet').remove();
            wrap.append($('#tplBuildingView').tmpl());
            plus.event.buildingDetail();
        }
    }
    plus.event.tabReady=function(){

        $('.newTab').click(function(){
            plus.tab.addTab($(this).html(),{},$('#tplTabView').tmpl({updateUrl:'/totaladmin/ajax/assets/buildingExcute',deleteUrl:'/totaladmin/ajax/assets/buildingDelete'}));
        });
        var gridElement =null,gridColumn =[];
        gridColumn.push({'data':'bcSeq','title':'사업고객SEQ',hidden:true});
        gridColumn.push({'data':'biSeq','title':'사업자SEQ',hidden:true});
        gridColumn.push({'data':'abName','title':'자산명칭'});
        gridColumn.push({'data':'abType','title':'자산타입'});
        gridColumn.push({'data':'roadaddress','title':'주소'});
        gridColumn.push({'data':'abArea03','title':'대지면적[m2]',hidden:true});
        gridColumn.push({'data':'abArea06','title':'연면적[m2]',hidden:true});
        gridColumn.push({'data':'abArea01','title':'건축면적[m2]',hidden:true});
        gridColumn.push({'data':'abArea05','title':'조경면적[m2]',hidden:true});
        gridColumn.push({'data':'abArea04','title':'주차면적[m2]',hidden:true});
        gridColumn.push({'data':'abCoverage','title':'건폐율(%)',hidden:true});
        gridColumn.push({'data':'abFloorArea','title':'용적율(%)',hidden:true});
        gridElement = plus.makeGrid('#gridElement',gridColumn,plus.makeAjax('/totaladmin/ajax/assets/buildingList',{},'resultList'),{attr:'속성'});

        /* tr클릭 이벤트*/
        $('#gridElement tbody').on('click','tr.even,tr.odd',function () {
            var rowData =  gridElement.row( this ).data();
            var tabTitle  = String.format('[{0}] {1}',rowData['biName'],'');
            console.log(rowData);
            plus.tab.addTab(tabTitle,rowData,$('#tplTabView').tmpl({updateUrl:'/totaladmin/ajax/assets/buildingExcute',deleteUrl:'/totaladmin/ajax/assets/buildingDelete'}));
        });
    }

    /* tab페이지 첫번재 엘리먼트트에 넣을것*/
    $('#tabsPage01').append($('#tplGridView').tmpl());

    /* tab페이지 초기에 실행할 이벤트*/
    plus.event.tabReady();
    plus.event.submitAfter=function(){
        var wrap  =plus.tab.getActiveTab();
        wrap.find('.makeDepthSet').remove();
        wrap.append($('#tplBuildingView').tmpl());
    }
    plus.event.buildingDetail=function(){
        var wrap  =plus.tab.getActiveTab();
        var abSeq = wrap.find('#abSeq').val();
        var biSeq = wrap.find('#biSeq').val();

        var subEvent = {
            abSeq:null,
            biSeq:null,
            dongParam:null,
            floorParam:null,
            roomParam:null,
            dongElement:null,
            floorElement:null,
            roomElement:null,

            setNaming:function(flag){
                var naming='';
                var namingTxt='';
                switch(flag.substr(0,1)){
                    case 'd':naming='ad'; namingTxt='동'; break;
                    case 'f':naming='af'; namingTxt='층'; break;
                    case 'r':naming='ar'; namingTxt='호실'; break;
                }
                return naming;
            },

            makeDong:function(rowData){
                var tableElement = plus.makeElement('table','<colgroup><col width="10%"/><col width="15%"/><col width="10%"/><col width="15%"/></colgroup><tbody class="tableElement"></tbody>',{class:'table popupTable table-striped table-bordered table-hover'});
                var form  = $('#editModal form');
                form.html(tableElement);
                plus.makeInput('hidden','abSeq').prependTo(form).setValue(subEvent.ab_seq);
                plus.makeInput('hidden','biSeq').prependTo(form).setValue(subEvent.bi_seq);
                plus.makeInput('hidden','adSeq').prependTo(form).setValue(subEvent.ad_seq);
                tableElement = tableElement.find('.tableElement');
                tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
                plus.makeTd('text','adName','동 명칭').appendTo(tr).colspan(3);
                tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
                plus.makeTd('text','adUse','용도').appendTo(tr);
                plus.makeTd('text','adAreaBuild','건축면적').appendTo(tr);
                tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
                plus.makeTd('text','adAreaExclusive','전용면적').appendTo(tr);
                plus.makeTd('text','adPerExclusive','전용률').appendTo(tr);
                if(Object.keys(rowData).length>0){
                    $.each(rowData,function(i,v){
                       $('#'+i,form).setValue(v);
                    });
                }
                form.attr('action','/totaladmin/ajax/assets/dongExcute');
                $('.btnLabel').unbind('click').click(function(){
                    if(confirm(['동을 ',$('.btnLabel').html(),'하시겠습니까'].join(''))){
                        form.ajaxSubmit({
                            success:function(r){
                                $('#editModal .close').click();
                                subEvent.dongList({ab_seq:ab_seq,bi_seq:bi_seq});
                            }
                        })
                    }

                })
                return true;
            },
            makeFloor:function(rowData){
                if(!subEvent.ad_seq) {
                    alert('동 정보를 선택후 진행해주시기 바랍니다.');
                    return false;
                }
                var tableElement = plus.makeElement('table','<colgroup><col width="10%"/><col width="15%"/><col width="10%"/><col width="15%"/></colgroup><tbody class="tableElement"></tbody>',{class:'table popupTable table-striped table-bordered table-hover'});
                var form  = $('#editModal form');
                form.html(tableElement);
                plus.makeInput('hidden','abSeq').prependTo(form).setValue(subEvent.bi_seq);
                plus.makeInput('hidden','biSeq').prependTo(form).setValue(subEvent.bi_seq);
                plus.makeInput('hidden','adSeq').prependTo(form).setValue(subEvent.ad_seq);
                plus.makeInput('hidden','afSeq').prependTo(form).setValue(subEvent.af_seq);
                tableElement = tableElement.find('.tableElement');
                tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
                plus.makeTd('text','afName','층 명칭').appendTo(tr).colspan(3);
                tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
                plus.makeTd('text','afUse','용도').appendTo(tr);
                plus.makeTd('text','afAreaBuild','건축면적').appendTo(tr);
                tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
                plus.makeTd('text','afAreaExclusive','전용면적').appendTo(tr);
                plus.makeTd('text','afPerExclusive','전용률').appendTo(tr);
                if(Object.keys(rowData).length>0){
                    $.each(rowData,function(i,v){
                       $('#'+i,form).setValue(v);
                    });
                }
                form.attr('action','/totaladmin/ajax/assets/floorExcute');
                $('.btnLabel').unbind('click').click(function(){
                    if(confirm(['층을 ',$('.btnLabel').html(),'하시겠습니까'].join(''))) {
                        form.ajaxSubmit({
                            success: function (r) {
                                $('#editModal .close').click();
                                subEvent.dongList({abSeq:abSeq,biSeq:biSeq});
                                subEvent.floorList({abSeq:abSeq,biSeq:biSeq,adSeq:subEvent.adSeq});
                            }
                        })
                    }
                });
                return true;
            },
            makeRoom:function(rowData){
                if(!subEvent.adSeq) {
                    alert('동 정보를 선택후 진행해주시기 바랍니다.');
                    return false;
                }
                if(!subEvent.afSeq) {
                    alert('층 정보를 선택후 진행해주시기 바랍니다.');
                    return false;
                }
                var tableElement = plus.makeElement('table','<colgroup><col width="10%"/><col width="15%"/><col width="10%"/><col width="15%"/></colgroup><tbody class="tableElement"></tbody>',{class:'table popupTable table-striped table-bordered table-hover'});
                var form  = $('#editModal form');
                form.html(tableElement);
                plus.makeInput('hidden','abSeq').prependTo(form).setValue(subEvent.biSeq);
                plus.makeInput('hidden','biSeq').prependTo(form).setValue(subEvent.biSeq);
                plus.makeInput('hidden','adSeq').prependTo(form).setValue(subEvent.adSeq);
                plus.makeInput('hidden','afSeq').prependTo(form).setValue(subEvent.afSeq);
                plus.makeInput('hidden','arSeq').prependTo(form).setValue(subEvent.arSeq);
                tableElement = tableElement.find('.tableElement');
                tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
                plus.makeTd('text','arName','호실 명칭').appendTo(tr).colspan(3);
                tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
                plus.makeTd('text','arUse','용도').appendTo(tr);
                plus.makeTd('text','arAreaBuild','건축면적').appendTo(tr);
                tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
                plus.makeTd('text','arAreaExclusive','전용면적').appendTo(tr);
                plus.makeTd('text','arPerExclusive','전용률').appendTo(tr);
                if(Object.keys(rowData).length>0){
                    $.each(rowData,function(i,v){
                       $('#'+i,form).setValue(v);
                    });
                }
                form.attr('action','/totaladmin/ajax/assets/roomExcute');
                $('.btnLabel').unbind('click').click(function(){
                    if(confirm(['호실을 ',$('.btnLabel').html(),'하시겠습니까'].join(''))) {
                        form.ajaxSubmit({
                            success: function (r) {
                                $('#editModal .close').click();
                                subEvent.floorList({abSeq:abSeq,biSeq:biSeq,adSeq:subEvent.adSeq});
                                subEvent.roomList({abSeq:abSeq,biSeq:biSeq,adSeq:subEvent.adSeq,afSeq:subEvent.afSeq});
                            }
                        })
                    }
                })
                return true;;
            },
            itemClick:function(){
                var dataFlag = $(this).attr('data');
                var titleObject = {
                    'dongAdd':'동 정보등록',
                    'floorAdd':'층 정보등록',
                    'roomAdd':'룸 정보등록',

                    'dongInfo':'동 정보수정',
                    'floorInfo':'층 정보수정',
                    'roomInfo':'호실 정보수정',

                    'dongDel':'해당 동 정보를 삭제하시겠습니까?',
                    'floorDel':'층 정보 정보를 삭제하시겠습니까?',
                    'roomDel':'호실 정보 정보를 삭제하시겠습니까?'
                };

                var modalForm =false;
                if($.inArray(dataFlag,['dongAdd','floorAdd','roomAdd'])>-1){
                    $('#editModalLabel').html(titleObject[dataFlag]);
                    $('.btnLabel').html('등록');
                    modalForm =true;
                }
                if($.inArray(dataFlag,['dongInfo','floorInfo','roomInfo'])>-1){
                    $('.btnLabel').html('수정');
                    modalForm =true;
                }
                var param = $.extend({},{abSeq:abSeq,biSeq:biSeq},$(this).closest('.list-group-item').find('.itemx').data());

                if(modalForm){
                    var fixID = (subEvent.setNaming(dataFlag));
                    if(fixID=='ad') {
                        if(subEvent.makeDong(param)==false) return false;
                    }
                    if(fixID=='af') {
                        if(subEvent.makeFloor(param)==false) return false;
                    }
                    if(fixID=='ar'){
                        if(subEvent.makeRoom(param)==false) return false;
                    }
                }



            },
            dongList:function(param){
                if(param['abSeq']) subEvent.abSeq=param['abSeq'];
                if(param['biSeq']) subEvent.biSeq=param['biSeq'];

                $.call('/totaladmin/ajax/assets/dongList',param,function(r) {
                    var dongListElement = $('.dongList',wrap);
                    dongListElement.empty();
                    $.each(r.resultList, function (i, v) {

                        var addDongBtn = $('#addDongBtn').tmpl(v);
                        var li = plus.makeElement('li',plus.makeElement('a',v.adName,{"href":'javascript:;','class':'itemx'}).addIcon('circle m-r-10').data(v),{"class":'list-group-item'}).append(addDongBtn);
                        dongListElement.append(li);
                        li.find('.dropdown-item').click(subEvent.itemClick);
                        li.find('a.itemx').click(function(){
                            subEvent.adSeq = $(this).data()['adSeq'];
                            subEvent.afSeq= 0;
                            subEvent.arSeq= 0;
                            subEvent.floorList($(this).data());
                        });
                    })
                });
            },
            floorList:function(param){

                $.call('/totaladmin/ajax/assets/floorList',param,function(r) {
                    var floorListElement = $('.floorList',wrap);
                    floorListElement.empty();
                    $.each(r.resultList, function (i, v) {
                        var addFloorBtn = $('#addFloorBtn').tmpl(v);
                        var li = plus.makeElement('li',plus.makeElement('a',v.afName,{"href":'javascript:;','class':'itemx'}).addIcon('circle m-r-10').data(v),{"class":'list-group-item'}).append(addFloorBtn);
                        floorListElement.append(li);
                        li.find('.dropdown-item').click(subEvent.itemClick);
                        li.find('a.itemx').click(function(){
                            subEvent.afSeq = $(this).data()['afSeq'];
                            subEvent.arSeq = "";
                            subEvent.roomList($(this).data());
                        });
                    })
                });
            },
            roomList:function(param){

                $.call('/totaladmin/ajax/assets/roomList',param,function(r) {
                    var roomListElement = $('.roomList',wrap);
                    roomListElement.empty();
                    $.each(r.resultList, function (i, v) {
                        var addRoomBtn = $('#addRoomBtn').tmpl(v);
                        var li = plus.makeElement('li',plus.makeElement('a',v.arName,{"href":'javascript:;','class':'itemx'}).addIcon('circle m-r-10').data(v),{"class":'list-group-item'}).append(addRoomBtn);
                        roomListElement.append(li);
                        li.find('.dropdown-item').click(subEvent.itemClick);
                    })
                });
            }

        }
        wrap.find('.card-title .dropdown-item').click(subEvent.itemClick);
        subEvent.dongList({abSeq:abSeq,biSeq:biSeq});

    }
});
</script>
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="editModalLabel">New message</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form method="post" onsubmit="return false;">
">

        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
        <button type="button" class="btn btn-primary btnLabel">저장</button>
      </div>
    </div>
  </div>
</div>
<script type="text/x-jquery-tmpl" id="addDongBtn">
<div class="btn-group pull-right">
    <button type="button" class="btn btn-secondary btn-outline btn-xs dropdown-toggle" data-toggle="dropdown" aria-expanded="false">  층(\${floorCnt}) <span class="caret"></span> </button>
    <div class="dropdown-menu" x-placement="bottom-start" style="position: absolute; transform: translate3d(0px, 28px, 0px); top: 0px; left: 0px; will-change: transform;">
        <a class="dropdown-item" href="javascript:;" data="dongInfo"  data-toggle="modal" data-target="#editModal">동정보</a>
        <a class="dropdown-item" href="javascript:;" data="dongDel">동삭제</a>
        <a class="dropdown-item" href="javascript:;" data="addFloor">층추가</a>
    </div>
</div>
</script>
<script type="text/x-jquery-tmpl" id="addFloorBtn">
<div class="btn-group pull-right">
    <button type="button" class="btn btn-secondary btn-outline  btn-xs dropdown-toggle" data-toggle="dropdown" aria-expanded="false">  호실(\${roomCnt}) <span class="caret"></span> </button>
    <div class="dropdown-menu" x-placement="bottom-start" style="position: absolute; transform: translate3d(0px, 28px, 0px); top: 0px; left: 0px; will-change: transform;">
        <a class="dropdown-item" href="javascript:;" data="floorInfo"  data-toggle="modal" data-target="#editModal">층정보</a>
        <a class="dropdown-item" href="javascript:;" data="floorDel">층삭제</a>
        <a class="dropdown-item" href="javascript:;" data="addRoom">호실추가</a>
    </div>
</div>
</script>
<script type="text/x-jquery-tmpl" id="addRoomBtn">
<div class="btn-group pull-right">
    <button type="button" class="btn btn-secondary btn-outline  btn-xs dropdown-toggle" data-toggle="dropdown" aria-expanded="false"> <i class="fa fa-gear"></i>  <span class="caret"></span> </button>
    <div class="dropdown-menu" x-placement="bottom-start" style="position: absolute; transform: translate3d(0px, 28px, 0px); top: 0px; left: 0px; will-change: transform;">
        <a class="dropdown-item" href="javascript:;" data="roomInfo"  data-toggle="modal" data-target="#editModal">호실정보</a>
        <a class="dropdown-item" href="javascript:;" data="roomDel">호실삭제</a>
    </div>
</div>
</script>
<!--/*공통에서사용할게아니기때문에여기페이지에*/-->
<script id="tplBuildingView" type="text/x-jquery-tmpl">
    <div class="row makeDepthSet">
        <div class="col-md-4">
            <div class="card">
                <div class="card-body dong">
                    <div class="card-title b-b p-b-10">동정보
                    <button type="button" class="pull-right btn btn-primary  btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false"> <i class="fa fa-cogs"></i> <span class="caret"></span> </button>
                    <div class="dropdown-menu" x-placement="bottom-start" style="position: absolute; transform: translate3d(0px, 28px, 0px); top: 0px; left: 0px; will-change: transform;">
                        <a class="dropdown-item" href="javascript:;">삭제</a>
                        <a class="dropdown-item" href="javascript:;" data="dongAdd" data-toggle="modal" data-target="#editModal">동추가</a>
                    </div>
                    </div>
                    <div class="list-group-scroll" >
                        <ul class="list-group   dongList style-none ps--theme_default ps--active-y">

                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">

                <div class="card-body floor">
                    <div class="card-title b-b p-b-10">층정보
                    <button type="button" class="pull-right btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false"> <i class="fa fa-cogs"></i> <span class="caret"></span> </button>
                    <div class="dropdown-menu" x-placement="bottom-start" style="position: absolute; transform: translate3d(0px, 28px, 0px); top: 0px; left: 0px; will-change: transform;">
                        <a class="dropdown-item" href="javascript:;">삭제</a>
                        <a class="dropdown-item" href="javascript:;" data="floorAdd" data-toggle="modal" data-target="#editModal">층추가</a>
                    </div>
                    </div>
                    <div class="list-group-scroll" >
                        <ul class="list-group   floorList style-none  ps--theme_default ps--active-y">

                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">
                <div class="card-body room">
                    <div class="card-title b-b p-b-10">호실정보
                    <button type="button" class="pull-right btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false"> <i class="fa fa-cogs"></i> <span class="caret"></span> </button>
                    <div class="dropdown-menu" x-placement="bottom-start" style="position: absolute; transform: translate3d(0px, 28px, 0px); top: 0px; left: 0px; will-change: transform;">
                        <a class="dropdown-item" href="javascript:;">삭제</a>
                        <a class="dropdown-item" href="javascript:;" data="roomAdd" data-toggle="modal" data-target="#editModal">호실추가</a>
                    </div>
                    </div>
                    <div class="list-group-scroll" >
                        <ul class="list-group   roomList style-none  ps--theme_default ps--active-y">
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>