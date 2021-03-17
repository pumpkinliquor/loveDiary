<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<div class="row page-titles">
</div>
<div id="tabs">
    <ul class="addTab">
        <li><a href="#tabsPage01">고객사목록</a></li>
    </ul>
    <div id="tabsPage01">
    </div>
</div>
<script type="text/javascript">
$(document).ready(function(){
    /* 코드가져오기 항목*/

    plus.rules={
        bi_seq:{
            "required": true
        }
    };

    $.call('/ajax/codeList',{codes:'BI,BC_TYPE'},function(r){
        $.extend(plus.codes,r.codes);
    });



    /* tab 생성후 초기이벤트*/
    plus.event.tabAfter=function(rowData){
        var pageContentLast = $('.pageContent:last');
        var tableElement =pageContentLast.find('table');

            plus.makeInput('hidden','bc_seq').prependTo(pageContentLast);
            tr = plus.makeElement('colgroup').appendTo(tableElement);
                plus.makeElement('col','',{width:'10%'}).appendTo(tr);
                plus.makeElement('col','',{width:'15%'}).appendTo(tr);
                plus.makeElement('col','',{width:'10%'}).appendTo(tr);
                plus.makeElement('col','',{width:'15%'}).appendTo(tr);
            tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
                plus.makeTd('select','biSeq','사업장').appendTo(tr).addItem(plus.codes.BI,true)
                plus.makeTd('checkbox','biSame','사업장정보 동일').appendTo(tr).addItem({y:'동일'});

                tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('select','bcType','고객타입').appendTo(tr).addItem(plus.codes.BC_TYPE,true)
		plus.makeTd('html','','고객사아이디등록').appendTo(tr);

            tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('text','bcName','고객사명').appendTo(tr);
		plus.makeTd('text','bcBizNum','사업자번호/생년월일').appendTo(tr);

	tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('text','bcCeoName','대표자명').appendTo(tr);
		plus.makeTd('text','bcTel','전화번호').appendTo(tr);
	tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('text','bcFax','Fax번호').appendTo(tr);
		plus.makeTd('text.email','BcEmail','이메일').appendTo(tr);
		plus.event.addAddress(rowData,tableElement);

	tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('text','bcStartDate','사용기간(시작일)').appendTo(tr);
		plus.makeTd('text','bcEndDate','사용기간(종료일)').appendTo(tr);
	tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
		plus.makeTd('text','bcEtc','특이사항').appendTo(tr).colspan(3);

         plus.event.formAfter(pageContentLast,rowData);

    }
    plus.event.tabReady=function(){

        $('.newTab').click(function(){
            plus.tab.addTab($(this).html(),{},$('#tplTabView').tmpl({updateUrl:'/totaladmin/ajax/business/businessClientExcute',deleteUrl:'/totaladmin/ajax/business/businessClientDelete'}));
        });
        var gridElement =null,gridColumn =[];
         gridColumn.push({'data':'bcType','title':'고객타입'});
        gridColumn.push({'data':'bcName','title':'고객사명'});
        gridColumn.push({'data':'bcBizNum','title':'사업자번호/생년월일'});
        gridColumn.push({'data':'bcCeoName','title':'대표자명'});
        gridColumn.push({'data':'bcTel','title':'전화번호'});
        gridColumn.push({'data':'bcFax','title':'Fax번호'});
        gridColumn.push({'data':'bcEmail','title':'이메일'});
        gridElement = plus.makeGrid('#gridElement',gridColumn,plus.makeAjax('/totaladmin/ajax/business/businessClientList',{},'resultList'),{attr:'속성'});

        /* tr클릭 이벤트*/
        $('#gridElement tbody').on('click','tr.even,tr.odd',function () {
            // var rowData =  gridElement.row( this ).data();
            // var tabTitle  = String.format('[{0}] {1}',rowData['biName'],'');
            // console.log(rowData);
            // plus.tab.addTab(tabTitle,rowData,$('#tplTabView').tmpl({updateUrl:'/totaladmin/ajax/business/businessClientExcute',deleteUrl:'/totaladmin/ajax/business/businessClientDelete'}));

            $('#viewTr').remove();
            var tdCount =  $(this).find('td').length;
            var index = $('#gridElement tbody tr').index(this);
            var rowData =  gridElement.row( this ).data();
            var tplTrView = $('#tplTrView').tmpl({url:'/totaladmin/ajax/configs/codeList'});
            var targetForm = tplTrView;
            console.log(targetForm.prop('nodeName'))
            targetForm.ajaxForm({
                success:function(r,x){
                    console.log(r,x);
                }
            });

            var tr,tableElement = $('.tableElement',tplTrView),wrapElement = tplTrView;

            plus.makeInput('hidden','cm_seq').prependTo(wrapElement);
            tr = plus.makeElement('colgroup').appendTo(tableElement);
                plus.makeElement('col','',{width:'10%'}).appendTo(tr);
                plus.makeElement('col','',{width:'15%'}).appendTo(tr);
                plus.makeElement('col','',{width:'10%'}).appendTo(tr);
                plus.makeElement('col','',{width:'15%'}).appendTo(tr);
                plus.makeElement('col','',{width:'10%'}).appendTo(tr);
                plus.makeElement('col','',{width:'15%'}).appendTo(tr);
            tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
                plus.makeTd('text','cmCode','코드').appendTo(tr);
                plus.makeTd('text','cmName','코드명').appendTo(tr);
            tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
                plus.makeTd('text','cmGubun','구분').appendTo(tr);
                plus.makeTd('text','cmSort','정렬기준').appendTo(tr);
            tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
                plus.makeTd('select','cmIsUse','사용여부').appendTo(tr).addItem({'y':'사용','n':'미사용'});
                plus.makeTd('select','cmIsSystem','시스템에서사용').appendTo(tr).addItem({'y':'사용','n':'미사용'});
            tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
                plus.makeTd('text','cmEtc','메모값').appendTo(tr).last().attr('colspan',3);

            var trView = $('<tr id="viewTr"><td></td></tr>').find('td').attr('colspan',tdCount).html(tplTrView).parent();
            trView.insertAfter(this);
            $.each(rowData,function(i,v){
                $('#'+i,wrapElement).setValue(v);
            });
        });
    }

    /* tab페이지 첫번재 엘리먼트트에 넣을것*/
    $('#tabsPage01').append($('#tplGridView').tmpl());

    /* tab페이지 초기에 실행할 이벤트*/
    plus.event.tabReady()
});
</script>