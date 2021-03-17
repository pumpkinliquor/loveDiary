<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<script src="/assets/js/jquery/mask.ip-input.js"></script>
<h2>아이디발급</h2>

<table class="srchT">
    <colgroup>
        <col width="20%">
        <col width="*">
    </colgroup>
    <tbody>
    <tr>
        <th rowspan="2">검색</th>
        <td class="bbx">
            <span class="tit">상태</span>
            <div class="radio_wrap rdo_wrap1 codex USE_YN_ALL" type="radio" data="searchUseYn"></div>
        </td>
    </tr>
    <tr>
        <td class="ptx">
            <div class="srch_div">
                <select class="s_p1" name="searchType" id="searchType">

                    <option value="umName">이름</option>
                    <option value="umCompanySub">팀명</option>
                </select>
                <input type="text" name="searchString" id="searchString" class="ipt1">
                <button type="button" class="btn btn-st1 btnSearch"  >검색</button>
            </div>
        </td>
    </tr>
    </tbody>
</table>

<table class="tbl-st" id="gridElement">
    <colgroup>
        <col width="7%">
        <col width="10%">
        <col width="10%">
        <col width="10%">
        <col width="15%">
        <col width="10%">
        <col width="10%">
        <col width="*">
    </colgroup>
    <thead>
    <tr>
        <th>번호</th>
        <th>이름</th>
        <th>아이디</th>
        <th>사업부</th>
        <th>팀</th>
        <th>외부접속</th>
        <th>상태</th>
        <th>등록/수정</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>
<div class="tbl-foot">
    <div class="paging">
        <a href="#" class="prev">
            <i class="fas fa-caret-left"></i>
        </a>
        <span class="on">1</span>
        <a href="#">2</a>
        <a href="#">3</a>
        <a href="#">4</a>
        <a href="#">5</a>
        <a href="#">6</a>
        <a href="#">7</a>
        <a href="#">8</a>
        <a href="#">9</a>
        <a href="#">10</a>
        <a href="#" class="next">
            <i class="fas fa-caret-right"></i>
        </a>
    </div>
    <a href="#" class="btn btn-offer btnReg">등록</a>

</div>
<div class="modal-wrap pop01">
    <div class="modal">
      <div class="modal-header">
        <h1>아이디 등록/수정</h1>
      </div>
        <form id="wrapEdit" action="/plusadmin/ajax/user/userExcute" method="post" enctype="multipart/form-data">
			<input type="hidden" name="umSeq" id="umSeq" value="" />
			<input type="hidden" name="umStep" id="umStep" value="" />
			<input type="hidden" name="umType" id="umType" value="" />
			<input type="hidden" id="start" name="start" value="" />
			<input type="hidden" id="length" name="length" value="" />
      <div class="content">
        <table class="popT">
        	<colgroup>
        		<col width="20%">
        		<col width="30%">
        		<col width="20%">
        		<col width="30%">
        	</colgroup>
        	<tbody>
        		<tr>
        			<th>이름 <em class="point">*</em></th>
        			<td colspan="3"><input type="text" class="" name="umName" id="umName" maxlength="10"></td>
        		</tr>
        		<tr>
        			<th>아이디 <em class="point">*</em></th>
        			<td><input type="text" class="" name="umId" id="umId" readonly="readonly" minlength="4" maxlength="30"></td>
        			<th>비밀번호 <em class="point">*</em></th>
        			<td><input type="password" class="" name="umPw" id="umPw" minlength="8" maxlength="30"></td>
        		</tr>
        		<tr>
        			<th>사업부</th>
        			<td colspan="3"><input type="text" class="" name="umCompany" id="umCompany"></td>
        		</tr>
        		<tr>
        			<th>소속팀</th>
        			<td><input type="text" class="" name="umCompanySub" id="umCompanySub"></td>
        			<th>직급</th>
        			<td><input type="text" class="" name="umJicgup" id="umJicgup"></td>
        		</tr>
        		<tr>
        			<th>IP</th>
        			<td colspan="3">
        				<input type="text" class="ip_address" name="umUmAddr" id="umUmAddr">
<%--        				<p class="alert_txt">숫자,콤마(.)만 입력 가능합니다.</p>--%>
        			</td>
        		</tr>
        		<tr>
        			<th>외부접속 <em class="point">*</em></th>
        			<td colspan="3">
        				<div class="radio_wrap rdo_wrap1 code CONNECT" type="radio" data="umWork"></div>
						<!-- 허용 시 -->
						<p class="txt">외부접속 허용 사유를 입력해주세요.</p>
						<textarea id="umEtc" name="umEtc"></textarea>
						<!-- //허용 시 -->
        			</td>
        		</tr>
        		<tr>
        			<th>상태</th>
        			<td colspan="3">
        				<div class="radio_wrap rdo_wrap1 code USE_YN" type="radio" data="useYn"></div>
        			</td>
        		</tr>
        		<tr class="EDIT hidden">
        			<th>등록/수정</th>
        			<td colspan="3" class="show_iddate">
        				이지현(admin)  yyyy-mm-dd   hh:mm:ss
        			</td>
        		</tr>
        	</tbody>
        </table>

      </div>
      <div class="modal-footer">
        <button class="btn btn-primary fR btnSubmit">저장</button>
      </div>
        </form>
      <a href="#" class="close-pop close-btn" >&#10005;</a>
    </div>
  </div>

<script type="text/javascript">
var gridElement =null,gridColumn =[];
$(document).ready(function(){
    /* 코드 사용 */
    /* 코드 사용 */
    $.call('/ajax/codeList',{codes:'BI,USE_YN,ACV,SUB,CONNECT,USE_YN_ALL'},function(r){
        $.extend(plus.codes,r.codes);
    });
    $.each(plus.codes,function(k,v){
      $('.code.'+k).addCodeItem(v,true);
      $('.codex.'+k).addCodeItem(v);
    });

    $('.ip_address').keyup(function(e){
       $(this).val($.trim($(this).val()).replace(/[^0-9\.]/g,''));
    });
    $('.ip_address').ipAddress();

    /* tab 생성후 초기이벤트*/
    plus.event.tabAfter=function(pageContentLast, rowData, mode){
        var rules = {
            umName:{required:true,minlength:2}
            ,umId:{required:true,minlength:3}
            ,umWork:{required:true}
            //,umEtc:{required:true}
            ,umUmAddr:{ip:true}
            ,umPw:{required:true,minlength:4}
        };

        pageContentLast.data({rules:rules});
        var tableElement =pageContentLast.find('table');
        plus.event.formAfter(pageContentLast,rowData,mode);
        //plus.event.bbsfile(rowData);


    }

    //페이지 래디 정의
    plus.event.tabReady=function(){
        $('.USE_YN_ALL :radio:eq(0)').click();
        plus.evnet.submitAfter=function(){
            $('.close-btn').click();

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
            var id = plus.getId(r.bbSeq);
            plus.makeInput('checkbox','',{'class':"custom-control-input biSeq",id:"bbSeq"+(id),value:r.bbSeq}).appendTo(div);
            plus.makeElement('label','',{'class':'custom-control-label',for:'bbSeq'+(id)}).appendTo(div);
            return div.prop('outerHTML')
        }
        plus.event.clickbox=function(d, t, r){
          var div = plus.makeElement('a',d,{'href':'javascript:;','class':'custom-control custom-checkbox'});
          return div.prop('outerHTML')
        }

        gridColumn.push({'data': 'umSeq', 'title': '순서', 'type': 'checkbox', hidden: false,render:plus.renderer.rrownum});
        gridColumn.push({'data':'umName','title':'이름','class':'tl',render:plus.renderer.clickbox});
        gridColumn.push({'data':'umId','title':'아이디',render:plus.renderer.clickbox});
        gridColumn.push({'data':'umCompany','title':'사업부',render:plus.renderer.clickbox});
        gridColumn.push({'data':'umCompanySub','title':'팀',render:plus.renderer.clickbox});
        gridColumn.push({'data':'umWork','title':'외부접속',code:plus.codes['CONNECT'],render:plus.renderer.code});



        gridColumn.push({'data':'useYn','title':'상태',code:plus.codes['USE_YN'],render:plus.renderer.code});
        gridColumn.push({'data':'regDate','title':'등록/수정',render:plus.renderer.iddate});

        gridElement = plus.makeGrid('#gridElement',gridColumn,plus.makeAjax('/plusadmin/ajax/user/userList',
            {searchString:function(){return $('#searchString').val()}
            ,useYn:function(){return $('.USE_YN_ALL :checked').val()}
            ,searchType:function(){return $('#searchType').val()}},
            'resultList'),{attr:'속성'});



        /* tr클릭 이벤트*/
        $('#gridElement tbody ').on('click','.clickkbox',function () {
            var rowData =  gridElement.row( $(this).closest('tr') ).data();
            var info = gridElement.page.info();
            console.log(info);
            var tabTitle  = String.format('[{0}] {1}',rowData['abName'],'');
            $('#umId').prop('readonly',true);
            //console.log(rowData);
            //plus.frontTab.addTab(tabTitle,rowData,$('#wrapEdit').tmpl({updateUrl:'/front/ajax/assets/buildingExcute',deleteUrl:'/front/ajax/assets/buildingDelete'}));
            $('#wrapList').hide();
            rowData['start']=info['start']
            rowData['length']=info['length']
          console.log(rowData);
            plus.frontPage.popup($('#wrapEdit'),rowData,'EDIT');

        });
    }


    /* tab페이지 초기에 실행할 이벤트*/
    plus.event.tabReady();

    $('.btnSearch').click(function(){
        gridElement.ajax.reload();
    });
    $('#searchString').keypress(function(e){
        if(e.keyCode==13){
            $('.btnSearch').click();
        }
    });
    /* 등록 버튼*/
    $('.btnReg').click(function(){
        $('#umId').prop('readonly',false);
        var json = $('#wrapEdit').domJson();
        $.each(json,function(k,v){
            json[k] = '';
        });

      plus.frontPage.popup($('#wrapEdit'), $.extend(json,{bbSeq:'0','start':0,length:0,umStep:'OK',umType:'ADMIN',umWork:'N',useYn:'y',bbDate:(new Date()).format('yyyy-MM-dd')}),'NEW');
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

        var connect =   $('.code.CONNECT :radio:checked').val();
        if(connect=='Y' && $.trim($('#umEtc').val())==''){
            Swal.fire({
              icon: 'warning',
              title: '' +
                  '외부접속 사유는 필수입니다.',
          });
            return false;
        }

        $(this).closest('form').submit();
        return false;
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