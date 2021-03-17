<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

    <!-- content Wrapper -->
    <div class="content-wrapper">
      <div class="content-header">
        <div class="container-fluid">
          <h2>코드 목록</h2>
        </div>
      </div>

      <section class="content" id="wrapList">
        <div class="row">

          <table id="gridElement" width="100%" class="table text-center">
            <colgroup>
              <col style="width: 5%;">
              <col style="width: 5%;">
              <col style="width: 5%;">
              <col style="width: 15%;">
              <col style="width: 15%;">
              <col style="width: 5%;">
              <col style="width: 10%;">
            </colgroup>
            <thead>
              <tr class="bg">
                <th>
                  <div class="custom-control custom-checkbox">
                    <input type="checkbox" id="jb-checkbox" class="custom-control-input">
                    <label class="custom-control-label" for="jb-checkbox"></label>
                  </div>
                </th>
                <th>번호</th>
                <th></th>
                <th>이미지</th>
                <th>제품명</th>
                <th>상태</th>
                <th>드옥일</th>
              </tr>
            </thead>
            <tbody>

            </tbody>
          </table>
          <div class="table-footer">
            <a href="#" class="btn btn-cancel btnDelete" data-toggle="modal" data-target="#delect-check-Modal">삭제</a>

            <div class="dataTables_paginate paging_simple_numbers" id="example2_paginate">
              <ul class="pagination">
              </ul>
            </div>

            <a href="#" class="btn btn-offer btnReg">등록</a>
          </div>
        </div>
      </section>

<script type="text/javascript">
var gridElement =null,gridColumn =[];
$(document).ready(function(){
    /* 코드 사용 */
    /* 코드 사용 */
    $.call('/ajax/codeList',{codes:'BI,LG,BB_USE'},function(r){
        $.extend(plus.codes,r.codes);
    });
    $.each(plus.codes,function(k,v){
      $('.code.'+k).addCodeItem(v)
    });

    /* tab 생성후 초기이벤트*/
    plus.event.tabAfter=function(pageContentLast, rowData, mode){
        var rules = {
            bbTitle:{required:true}
            ,bbOpen:{required:true}
        };
        pageContentLast.find('form').data({rules:rules});
        var tableElement =pageContentLast.find('table');
        plus.event.formAfter(pageContentLast,rowData,mode);
        plus.event.bbsfile(rowData);


    }

    //페이지 래디 정의
        plus.evnet.submitAfter=function(){
            $('#wrapList').show();
            $('#wrapEdit').hide();

            var info = gridElement.page.info();
            //console.log(info);
            //alert(JSON.stringify(info));
            gridElement.ajax.reload(null,false);
        }
        /* 그리드완료된후 호출 */
        plus.event.gridComplet=function(){
            //전체선택 체크박스 클릭

        }
        plus.event.seqCheckBox=function(d, t, r){
            var div = plus.makeElement('div','',{'class':'custom-control custom-checkbox'});
            var id = plus.getId(r.cmSeq);
            plus.makeInput('checkbox','',{'class':"custom-control-input cmSeq",id:"cmSeq"+(id),value:r.cmSeq}).appendTo(div);
            plus.makeElement('label','',{'class':'custom-control-label',for:'cmSeq'+(id)}).appendTo(div);
            return div.prop('outerHTML')
        }
        plus.event.clickbox=function(d, t, r){
          var div = plus.makeElement('a',d,{'href':'javascript:;','class':'custom-control custom-checkbox'});
          return div.prop('outerHTML')
        }

        gridColumn.push({'data': 'umSeq', 'title': plus.event.checkAll, 'type': 'checkbox', hidden: false,render:plus.event.seqCheckBox});
        gridColumn.push({'data':'cmSeq','title':'번호'});
        gridColumn.push({'data':'cmGubun','title':'코드구분',render:plus.renderer.clickbox});
        gridColumn.push({'data':'cmCode','title':'코드'});
        gridColumn.push({'data':'cmName','title':'코드명'});
        //
        // gridColumn.push({'data':'filemap.pcthumb','title':'이미지',render:plus.renderer.img});
        // gridColumn.push({'data':'bbEtc01','title':'제품명'});
        gridColumn.push({'data':'lgUse','title':'상태',code:plus.codes['BB_USE'],render:plus.renderer.code});
        // gridColumn.push({'data':'bbView','title':'조회수'});
        gridColumn.push({'data':'regDate','title':'등록일',render:plus.renderer.datetime});

        gridElement = plus.makeGrid('#gridElement',gridColumn,plus.makeAjax('/totaladmin/ajax/configs/codeList',{bbBbs:'BA'},'resultList'),{pageLength:50,attr:'속성'});



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
            //var tplTrView = $('#tplTrView').tmpl({url:'/totaladmin/ajax/configs/codeList'});
            var tplTrView = $('#tplTrView').tmpl({url:'/totaladmin/ajax/configs/codeList',updateUrl:'/totaladmin/ajax/configs/codeExcute',deleteUrl:'/totaladmin/ajax/configs/deleteCodeAction'});
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
            // $.each(rowData,function(i,v){
            //     $('#'+i,wrapElement).setValue(v);
            // });
            plus.event.formAfter(tplTrView,rowData,'EDIT');
        });


    /* 등록 버튼*/
    $('.btnReg').click(function(){
      $('#wrapList').hide();
      plus.frontPage.show($('#wrapEdit'), {bbSeq:'0','start':0,length:0,bbContents:'',bbDate:(new Date()).format('yyyy-MM-dd')},'NEW');
    });
    /* 삭제 버튼*/
    $('.btnDelete').click(function(){
          var delBbsSeqs = [];
          $.each($('#gridElement').find(':checked'),function(){
            delBbsSeqs.push($(this).val());
          });
          if(delBbsSeqs.length==0){
            alert('1건 이상의 데이터를 선택해주세요');
          }
          // plus.event.tplDelete($(this).closest('form'),);
          // $(this).closest('form').submit();

        if(confirm('삭제 하시겠습니까?')) {
          $.call('/plusadmin/ajax/bbs/bbsDelete', {delBbsSeqs: delBbsSeqs.join(',')}, function(r) {
            plus.event.tplCallback(r)
            gridElement.ajax.reload();
          })
        }
    });
    $('.btnList').click(function() {
      $('#wrapList').show();
      $('#wrapEdit').hide();
    });
    $('.btnSubmit').click(function(){
      var fileList = [];
      $(':file').each(function(){
        var data = $(this).data();
        if(data['bafSeq']){
          fileList.push(data);
        }
      });
      //fileList
      console.log(JSON.stringify(fileList));
      $(this).closest('form').find('input[name=fileList]').val(JSON.stringify(fileList));
      $(this).closest('form').submit();
    });
    $(':file').change(function(){
      var fileOne = $(this).get(0).files[0];
      if(fileOne){
        $(this).closest('td').find('.upload-name').val(fileOne.name);
        $(this).closest('td').find('img').attr('src',URL.createObjectURL(fileOne));
      }
      console.log(fileOne);
    });


});

</script>
<div class="dataTables_paginate paging_simple_numbers" id="gridElement_paginate">
  <a class="paginate_button page-item first disabled" aria-controls="gridElement" data-dt-idx="0" tabindex="-1" id="gridElement_first"><i class="fa fa-angle-double-left"></i></a><a class="paginate_button page-item previous disabled" aria-controls="gridElement" data-dt-idx="1" tabindex="-1" id="gridElement_previous"><i class="fa fa-angle-left"></i></a><span><a class="paginate_button page-item current active" aria-controls="gridElement" data-dt-idx="2" tabindex="0">1</a></span><a class="paginate_button page-item next disabled" aria-controls="gridElement" data-dt-idx="3" tabindex="-1" id="gridElement_next"><i class="fa fa-angle-right"></i></a><a class="paginate_button page-item last disabled" aria-controls="gridElement" data-dt-idx="4" tabindex="-1" id="gridElement_last"><i class="fa fa-angle-double-right"></i></a>
</div>
