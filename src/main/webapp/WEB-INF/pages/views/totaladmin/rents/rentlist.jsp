<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<div class="row page-titles">
</div>
<div id="tabs">
    <ul class="addTab">
        <li><a href="#tabsPage01">임대목록</a></li>
    </ul>
    <div id="tabsPage01">
    </div>
</div>
<script type="text/javascript">
$(document).ready(function(){

    $.call('/ajax/codeList',{codes:'BI,BC_TYPE,ACC_TYPE,AB_TYPE'},function(r){
        $.extend(plus.codes,r.codes);
    });

    /* 타이틀 항목*/
    $('.page-titles').append($('#tplPageTitle').tmpl({pageTitle:'임대관리',newTabTitle:'임대 등록'}));

    /* tab 생성*/
    plus.tab.thisTab = $('#tabs').tabs();

    /* tab 생성후 초기이벤트*/
    plus.event.tabAfter=function(rowData){
        var pageContentLast = $('.pageContent:last');
        var tableElement =pageContentLast.find('table');

            plus.makeInput('hidden','bi_seq').prependTo(pageContentLast);
            tr = plus.makeElement('colgroup').appendTo(tableElement);
                plus.makeElement('col','',{width:'10%'}).appendTo(tr);
                plus.makeElement('col','',{width:'15%'}).appendTo(tr);
                plus.makeElement('col','',{width:'10%'}).appendTo(tr);
                plus.makeElement('col','',{width:'15%'}).appendTo(tr);
            tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
                plus.makeTd('select','bi_seq','사업장').appendTo(tr).addItem(plus.codes.BI,true);
                plus.makeTd('select','bc_seq','고객').appendTo(tr).addItem(plus.codes.BC,true);
           tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
                plus.makeTd('select','ab_seq','자산SEQ').appendTo(tr).addItem({},true);
                plus.makeTd('select','ab_type','자산타입').appendTo(tr).addItem(plus.codes.AB_TYPE,true);
            tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
                plus.makeTd('select','ad_seq','동SEQ').appendTo(tr).addItem({},true);
                plus.makeTd('select','af_seq','층SEQ').appendTo(tr).addItem({},true);
            tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
                plus.makeTd('select','ar_seq','호실SEQ').appendTo(tr).addItem({},true);
                plus.makeTd('select','rm_type','입주상태').appendTo(tr).addItem({},true);
            tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
                plus.makeTd('text','rm_code','계약코드').appendTo(tr);
                plus.makeTd('text','rm_area_01','계약면적').appendTo(tr);
            tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
                plus.makeTd('text','rm_area_02','전용면적').appendTo(tr);
                plus.makeTd('text','rm_area_04','공용면적').appendTo(tr);
                /*********************************************/

	tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('text','rc_comp_name','상호').appendTo(tr);
		plus.makeTd('text','rc_name','대표자명').appendTo(tr);
	tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('text','rc_business_num','사업자번호').appendTo(tr);
		plus.makeTd('text','rc_jumin_num','주민/법인 번호').appendTo(tr);
	tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('text','rc_business','업태').appendTo(tr);
		plus.makeTd('text','rc_event','종목').appendTo(tr);


    tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
        plus.makeTd('text','rc_person_name','담당자이름').appendTo(tr);
        plus.makeTd('text','rc_person_number','담당자번호').appendTo(tr);
	tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('text','rc_tel','담당자 연락처').appendTo(tr);
		plus.makeTd('text','rc_mobile','담당자 핸드폰').appendTo(tr);
	tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('text','rc_fax','담당자 팩스').appendTo(tr);
		plus.makeTd('text','rc_email','담당자 이메일').appendTo(tr);
		plus.event.addAddress(rowData,tableElement,mode);
	tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('text','rc_acc_bank','계좌은행').appendTo(tr);
		plus.makeTd('text','rc_acc_num','계좌번호').appendTo(tr);
	tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('text','rc_depositor','입금자명').appendTo(tr);
		plus.makeTd('select','rc_acc_type','납부 type(0:가상계좌, 1:계좌이체, 2:자동이체, 3:기타)').appendTo(tr).addItem(plus.codes['ACC_TYPE']);


                /*********************************************/
            tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
                plus.makeTd('text','rm_deposit_type','보증금타입').appendTo(tr);
                plus.makeTd('text','rm_deposit_price','보증금').appendTo(tr);
            tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
                plus.makeTd('text','rm_month_price','월임대료').appendTo(tr);
                plus.makeTd('text','rm_care_price','관리비').appendTo(tr);
            tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
                plus.makeTd('text','rm_pay_type','납입타입').appendTo(tr);
                plus.makeTd('text','rm_pay_date','납입일').appendTo(tr);

            plus.event.formAfter(pageContentLast,rowData);
            var subData = {

            };
            var AB_TYPE = {};
            var ROOM = {};
            $('#bi_seq',tableElement).change(function(){
                plus.codes.BC = {};
                plus.codes.BUILDING = {};
                plus.codes.DONG = {};
                plus.codes.FLOOR = {};
                plus.codes.ROOM = {};
                subData['AB_TYPE'] = {};
                $('#bc_seq',tableElement).addItem(plus.codes.BC,true).trigger('change.select2');
                $('#ab_seq',tableElement).addItem(plus.codes.BUILDING,true).trigger('change.select2');
                $('#ad_seq',tableElement).addItem(plus.codes.DONG,true).trigger('change.select2');
                $('#af_seq',tableElement).addItem(plus.codes.FLOOR,true).trigger('change.select2');
                $('#ar_seq',tableElement).addItem(plus.codes.ROOM,true).trigger('change.select2');

                $.call('/ajax/codeList',{'codes':'BC,BUILDING','bi_seq':$(this).val()},function(r){
                    $.extend(plus.codes,r.codes);
                    try{
                        subData['AB_TYPE'] = r.AB_TYPE;
                    } catch(e) {}


                    $('#bc_seq',tableElement).addItem(plus.codes.BC,true);
                    $('#ab_seq',tableElement).addItem(plus.codes.BUILDING,true);
                })
            });
            $('#ab_seq',tableElement).change(function(){
                plus.codes.DONG = {};
                plus.codes.FLOOR = {};
                plus.codes.ROOM = {};
                $('#ab_type',tableElement).val(subData['AB_TYPE'][$(this).val()]).trigger('change.select2');
                $('#ad_seq',tableElement).addItem(plus.codes.DONG,true).trigger('change.select2');
                $('#af_seq',tableElement).addItem(plus.codes.FLOOR,true).trigger('change.select2');
                $('#ar_seq',tableElement).addItem(plus.codes.ROOM,true).trigger('change.select2');

                $.call('/ajax/codeList',{'codes':'DONG','bi_seq':$('#bi_seq',tableElement).val(),'ab_seq':$(this).val()},function(r){
                    $.extend(plus.codes,r.codes);
                    $('#ad_seq',tableElement).addItem(plus.codes.DONG,true).select2({width:'100%'});
                })
            });
            $('#ad_seq',tableElement).change(function(){
                plus.codes.FLOOR = {};
                plus.codes.ROOM = {};
                $('#af_seq',tableElement).addItem(plus.codes.FLOOR,true).trigger('change.select2');
                $('#ar_seq',tableElement).addItem(plus.codes.ROOM,true).trigger('change.select2');
                $.call('/ajax/codeList',{'codes':'FLOOR','bi_seq':$('#bi_seq',tableElement).val(),'ab_seq':$('#ab_seq',tableElement).val(),'ad_seq':$(this).val()},function(r){
                    $.extend(plus.codes,r.codes);
                    $('#af_seq',tableElement).addItem(plus.codes.FLOOR,true).select2({width:'100%'});
                })
            });
            $('#af_seq',tableElement).change(function(){
                plus.codes.ROOM = {};
                subData['ROOM'] = {};
                $('#ar_seq',tableElement).addItem(plus.codes.ROOM,true).trigger('change.select2');
                $.call('/ajax/codeList',{'codes':'ROOM','bi_seq':$('#bi_seq',tableElement).val(),'ab_seq':$('#ab_seq',tableElement).val(),'ad_seq':$('#ad_seq',tableElement).val(),'af_seq':$(this).val()},function(r){
                    $.extend(plus.codes,r.codes);

                    try{
                        subData['ROOM'] = r.ROOM;
                    } catch(e) {}
                    $('#ar_seq',tableElement).addItem(plus.codes.ROOM,true).select2({width:'100%'});
                })
            });
            $('#ar_seq',tableElement).change(function(){
                if(Object.keys(subData['ROOM']).length>0){
                    var roomData = subData['ROOM'][$(this).val()];
                    console.log(roomData);
                    var reMapData = {'rm_area_01':roomData['ar_area_rent'],'rm_area_02':roomData['ar_area_exclusive'],'rm_area_03':roomData['ar_area_build']};
                    $.each(reMapData,function(i,v){
                            $('#'+i,tableElement).setValue(v);
                    });
                }
            });

    }
    plus.event.tabReady=function(){

        $('.newTab').click(function(){
            plus.tab.addTab($(this).html(),{},$('#tplTabView').tmpl({updateUrl:'/totaladmin/ajax/rents/rentExcute',deleteUrl:'/totaladmin/ajax/rents/rentDelete'}));
        });
        var gridElement =null,gridColumn =[];
        gridColumn.push({'data':'bi_name','title':'사업자명'});
        gridColumn.push({'data':'rc_name','title':'고객사명'});
        
        gridColumn.push({'data':'ab_name','title':'자산명'});
        gridColumn.push({'data':'af_name','title':'층'});
        gridColumn.push({'data':'ar_name','title':'호실'});
        

        gridColumn.push({'data':'rm_area_01','title':'계약면적'});
        gridColumn.push({'data':'rm_area_02','title':'전용면적'});
        gridColumn.push({'data':'rm_area_04','title':'공용면적'});

        gridColumn.push({'data':'rm_deposit_price','title':'보증금'});
        gridColumn.push({'data':'rm_month_price','title':'월임대료'});
        gridColumn.push({'data':'rm_care_price','title':'관리비'});
        gridColumn.push({'data':'rm_type','title':'입주상태 (OUT: 퇴실, IN:입실)'});
        gridElement = plus.makeGrid('#gridElement',gridColumn,plus.makeAjax('/totaladmin/ajax/rents/rentList',{},'rentList'),{attr:'속성'});

        /* tr클릭 이벤트*/
        $('#gridElement tbody').on('click','tr.even,tr.odd',function () {
            var rowData =  gridElement.row( this ).data();
            var tabTitle  = String.format('[{0}] {1}',rowData['bi_name'],'');
            console.log(rowData);
            plus.tab.addTab(tabTitle,rowData,$('#tplTabView').tmpl({updateUrl:'/totaladmin/ajax/rents/rentExcute',deleteUrl:'/totaladmin/ajax/rents/rentDelete'}));
        });
    }

    /* tab페이지 첫번재 엘리먼트트에 넣을것*/
    $('#tabsPage01').append($('#tplGridView').tmpl());

    /* tab페이지 초기에 실행할 이벤트*/
    plus.event.tabReady()
});
</script>