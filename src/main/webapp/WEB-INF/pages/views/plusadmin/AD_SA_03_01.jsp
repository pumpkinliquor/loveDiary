<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<script src="/assets/js/jquery/mask.ip-input.js"></script>
<h2>결제/환불</h2>

<table class="srchT">
				<colgroup>
					<col width="18%">
					<col width="*">
				</colgroup>
				<tbody>
					<tr>
						<th rowspan="3">기간</th>
						<td class="bbx">
						 	<form>
			                    <div class="date_div">
			                      <span class="ipt_dates"><input type="date" class="ipt_date" id=""></span>
			                      <span class="ipt_dates"><input type="date" class="ipt_date" id=""></span>
			                    </div>
		                    </form>
						</td>
					</tr>
					<tr>
						<td class="bbx ptx">
							<div class="radio_wrap" style="width:auto">
								<div class="ipt_radio_div">
									<input type="radio" id="srchAll" name="srch">
									<label for="srchAll">전체</label>
								</div>
								<div class="ipt_radio_div">
									<input type="radio" id="srch1" name="srch">
									<label for="srch1">대기</label>
								</div>
								<div class="ipt_radio_div mr20">
									<input type="radio" id="srch2" name="srch">
									<label for="srch2">결제완료</label>
								</div>
								<div class="ipt_radio_div mr20">
									<input type="radio" id="srch3" name="srch">
									<label for="srch3">환불접수</label>
								</div>
								<div class="ipt_radio_div mr20">
									<input type="radio" id="srch4" name="srch">
									<label for="srch4">환불승인</label>
								</div>
								<div class="ipt_radio_div mr20">
									<input type="radio" id="srch5" name="srch">
									<label for="srch5">환불완료</label>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="ptx">
							<div class="srch_div">
								<select class="s_p1">
									<option>선택</option>
									<option>아이디</option>
									<option>상품명</option>
									<option>상점구분</option>
									<option>상품코드</option>
								</select>
								<input type="text" name="" class="ipt_p1">
								<button type="submit" class="btn btn-st1">검색</button>
							</div>
						</td>
					</tr>
				</tbody>
			</table>

<table class="tbl-st" id="gridElement">
    <colgroup>
        <col width="*">
        <col width="9%">
        <col width="11%">
        <col width="10%">
        <col width="8%">

        <col width="8%">
        <col width="8%">
        <col width="8%">
        <col width="8%">
        <col width="7%">
        <col width="7%">
        <col width="8%">
    </colgroup>
    <thead>
        <tr>
            <th>승인일</th>
            <th>주문번호</th>
            <th>아이디</th>
            <th>상품명</th>
            <th>상점구분</th>

            <th>상품코드</th>
            <th>상품단가</th>
            <th>결제금액</th>
            <th>정산예정금액</th>
            <th>승인번호</th>
            <th>상태</th>
            <th>환불금액</th>
        </tr>
    </thead>
    </thead>
    <tbody>
    </tbody>
</table>


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