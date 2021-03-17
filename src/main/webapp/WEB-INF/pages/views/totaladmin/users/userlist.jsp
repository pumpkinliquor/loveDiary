<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

    <!-- content Wrapper -->
    <div class="content-wrapper">
      <div class="content-header">
        <div class="container-fluid">
          <h2>회원 목록</h2>
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
              <col style="width: 15%;">
              <col style="width: 15%;">
              <col style="width: 5%;">
              <col style="width: 5%;">
              <col style="width: 10%;">
              <col style="width: 10%;">
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
    </div>

<script type="text/javascript">
$(document).ready(function(){
    $.call('/ajax/codeList',{'codes':'UG,USE,BI,UG,AB,UT,US,ST'},function(r){
        $.extend(plus.codes,r.codes);
    });
        /* tab 생성후 초기이벤트*/
    plus.event.tabAfter=function(rowData){
        var pageContentLast = $('.pageContent:last');
        var tableElement =pageContentLast.find('table');

        plus.makeInput('hidden','umSeq').prependTo(pageContentLast);
        tr = plus.makeElement('colgroup').appendTo(tableElement);
            plus.makeElement('col','',{width:'10%'}).appendTo(tr);
            plus.makeElement('col','',{width:'15%'}).appendTo(tr);
            plus.makeElement('col','',{width:'10%'}).appendTo(tr);
            plus.makeElement('col','',{width:'15%'}).appendTo(tr);
            plus.makeElement('col','',{width:'10%'}).appendTo(tr);
            plus.makeElement('col','',{width:'15%'}).appendTo(tr);
        tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
            plus.makeTd('select','biSeq','기본사업장').appendTo(tr).addItem(plus.codes.BI,true)
            plus.makeTd('select','ugSeq','회원그룹').appendTo(tr).addItem(plus.codes.UG,true);
            plus.makeTd('select','abSeq','기본건물(담당건물)').appendTo(tr).addItem(plus.codes.AB,true);
        tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
            plus.makeTd('text','umId','회원아이디').appendTo(tr);
            plus.makeTd('text','umName','이름').appendTo(tr);
            plus.makeTd('text','umPw','회원패스워드').appendTo(tr);
        tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
            plus.makeTd('text','umHp','핸드폰').appendTo(tr);
            plus.makeTd('text','umTel','전화번호').appendTo(tr);
            plus.makeTd('text','umEmail','이메일').appendTo(tr);
        tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
            plus.makeTd('text','umZipcode','우편번호').appendTo(tr);
            plus.makeTd('text','umUmAddr','주소').appendTo(tr);

        tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
            plus.makeTd('text','umCompany','소속회사').appendTo(tr);
            plus.makeTd('text','umCompanySub','소속부서').appendTo(tr);
            plus.makeTd('text','umSort','조직도 소트').appendTo(tr);
        tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
            plus.makeTd('text','umJichek','직책').appendTo(tr);
            plus.makeTd('text','umJicgup','직급').appendTo(tr);
            plus.makeTd('select','umWork','재직, 휴직, 퇴사 ').appendTo(tr);
        tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
            plus.makeTd('calendar','umInDate','입사일').appendTo(tr);
            plus.makeTd('text','umType','회원타입( A: 관리자, W : 작업자 )').appendTo(tr).addItem(plus.codes.UT,true);
            plus.makeTd('calendar','umOutDate','퇴사일').appendTo(tr);
        tr = plus.makeElement('tr','',{class:'box mt10  formDiv'}).appendTo(tableElement);
            plus.makeTd('text','umImg','유저이미지').appendTo(tr);
            plus.makeTd('select','umStep','상태').appendTo(tr).addItem(plus.codes.US,true); //ST: 대기, OK : 승인, NO :  반려
        plus.event.formAfter(pageContentLast,rowData);
    }

    plus.event.tabReady=function() {
        var gridElement = null, gridColumn = [];
    
        plus.event.seqCheckBox=function(d, t, r){
            var div = plus.makeElement('div','',{'class':'custom-control custom-checkbox'});
            var id = plus.getId(r.umSeq);
            plus.makeInput('checkbox','',{'class':"custom-control-input umSeq",id:"umSeq"+(id),value:r.umSeq}).appendTo(div);
            plus.makeElement('label','',{'class':'custom-control-label',for:'umSeq'+(id)}).appendTo(div);
            return div.prop('outerHTML')
        }
        plus.event.clickbox=function(d, t, r){
          var div = plus.makeElement('a',d,{'href':'javascript:;','class':'custom-control custom-checkbox'});
          return div.prop('outerHTML')
        }

        // gridColumn.push({'data': 'umSeq', 'title': plus.event.checkAll, 'type': 'checkbox', hidden: false,render:plus.event.seqCheckBox});
        gridColumn.push({'data': 'umSeq', 'title': '시퀀스', 'type': ''});
        gridColumn.push({'data': 'ugSeq', 'title': '회원그룹',  hidden: true});
        // gridColumn.push({'data': 'abSeq', 'title': '기본건물(담당건물)', 'type': 'select', hidden: true});
        gridColumn.push({'data': 'ugName', 'title': '회원그룹', });
        // gridColumn.push({'data': 'abName', 'title': '기본건물(담당건물)', 'type': 'select'});
        gridColumn.push({'data': 'umId', 'title': '회원아이디', 'type': ''});
        gridColumn.push({'data': 'umName', 'title': '이름', 'type': ''});
        gridColumn.push({'data': 'umHp', 'title': '핸드폰', 'type': ''});
        gridColumn.push({'data': 'umTel', 'title': '전화번호',  hidden: true});
        gridColumn.push({'data': 'umEmail', 'title': '이메일', 'type': ''});
        //gridColumn.push({'data':'fo_seq','title':'기본본층(담당층)','type':'select'});
        gridElement = plus.makeGrid('#gridElement', gridColumn, plus.makeAjax('/totaladmin/ajax/users/userList', {data: 'data'}, 'resultList'), {pageLength:15,attr: '속성'});
        console.log(gridElement);


        /* tr클릭 이벤트*/
        $('#gridElement tbody').on('click','tr.even,tr.odd',function () {
            var rowData =  gridElement.row( this ).data();
            var tabTitle  = String.format('[{0}] {1}',rowData['umName'],'');
            console.log(rowData);
            plus.tab.addTab(tabTitle,rowData,$('#tplTabView').tmpl({updateUrl:'/totaladmin/ajax/users/userExcute',deleteUrl:'/totaladmin/ajax/users/userListDelete'}));
        });
    }


    /* tab페이지 초기에 실행할 이벤트*/
    plus.event.tabReady();
});
 </script>
