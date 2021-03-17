<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<h2>개념요소 관리</h2>

			<table class="srchT">
				<colgroup>
					<col width="18%">
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
								<select class="s_p1 codex SUB" name="subId" type="select">
									<option>과목</option>
									<option></option>
								</select>
								<select class="s_p1 codex ACV" name="acvId" type="select">
									<option>성취기준</option>
									<option></option>
								</select>
								<input type="text" name="searchString" id="searchString" class="ipt2">
								<button type="submit" class="btn btn-st1 btnSearch">검색</button>
							</div>
						</td>
					</tr>
				</tbody>
			</table>

			<p class="point mb5">※ 상태(공개/비공개) 변경 시 반드시 문항 상태를 확인하시기 바랍니다. </p>
			<table class="tbl-st" id="gridElement">
				<colgroup>
<%--					<col width="5%">--%>
					<col width="14%">
					<col width="14%">
					<col width="18%">
					<col width="18%">
					<col width="12%">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
<%--						<th></th>--%>
						<th>개념요소코드</th>
						<th>과목명</th>
						<th>성취기준이름</th>
						<th>개념요소명</th>
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
<div class="modal-wrap pop02 ">
    <div class="modal">
      <div class="modal-header">
        <h1>개념요소 등록/수정</h1>
      </div>

		<form id="wrapEdit" action="/plusadmin/ajax/aigo/conceptExcute" method="post" enctype="multipart/form-data">
			<input type="hidden" name="cptId" id="cptId" value="" />
			<input type="hidden" id="start" name="start" value="" />
			<input type="hidden" id="length" name="length" value="" />
      <div class="content">
        <table class="popT">
        	<colgroup>
        		<col width="30%">
        		<col width="*">
        	</colgroup>
        	<tbody>
        		<tr class="EDIT hidden">
        			<th>개념요소코드</th>
        			<td><b class="cptId">AA002</b></td>
        		</tr>
        		<tr>
        			<th>과목명 <em class="point">*</em></th>
        			<td>
        				<div class="radio_wrap rdo_wrap1 code SUB" type="select" data="subId">
						</div>
        			</td>
        		</tr>
        		<tr>
        			<th>성취기준이름 <em class="point">*</em></th>
        			<td>
        				<div class="radio_wrap rdo_wrap1 code ACV" type="select" data="acvId">
						</div>
        			</td>
        		</tr>
        		<tr>
        			<th>개념요소명 <em class="point">*</em></th>
        			<td><input type="text" value="" name="cptName" id="cptName"></td>
        		</tr>
        		<tr>
        			<th>상태</th>
        			<td>
        				<div class="radio_wrap rdo_wrap1 code USE_YN" type="radio" data="useYn"></div>
        			</td>
        		</tr>
        		<tr class="EDIT hidden">
					<th>등록/수정</th>
					<td class="show_iddate">이지현(admin) yyyy-mm-dd hh:mm:ss</td>
				</tr>
        	</tbody>
        </table>

      </div>
      <div class="modal-footer">
        <button class="btn btn-primary fR">저장</button>
      </div>
		</form>
      <a href="#" class="close-pop close-btn">&#10005;</a>
    </div>
  </div>
<script type="text/javascript">
var gridElement =null,gridColumn =[];
$(document).ready(function(){
    /* 코드 사용 */
    /* 코드 사용 */
    $.call('/ajax/codeList',{codes:'BI,USE_YN,ACV,SUB,USE_YN_ALL'},function(r){
        $.extend(plus.codes,r.codes);
    });
    $.each(plus.codes,function(k,v){
      $('.code.'+k).addCodeItem(v,true)
      $('.codex.'+k).addCodeItem(v,true)
    });





    /* tab 생성후 초기이벤트*/
    plus.event.tabAfter=function(pageContentLast, rowData, mode){
        var rules = {
            subId:{required:true}
            ,acvId:{required:true}
            ,cptName:{required:true}
        };
        pageContentLast.data({rules:rules});
        var tableElement =pageContentLast.find('table');
        plus.event.formAfter(pageContentLast,rowData,mode);
        plus.event.bbsfile(rowData);


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

        // gridColumn.push({'data': 'umSeq', 'title': plus.event.checkAll, 'type': 'checkbox', hidden: false,render:plus.event.seqCheckBox});
        gridColumn.push({'data':'cptId','title':'개념요소코드'});
        gridColumn.push({'data':'subName','title':'과목명'});
        gridColumn.push({'data':'acvName','title':'성취기준이름','class':'tl',render:plus.renderer.clickbox});

        gridColumn.push({'data':'cptName','title':'개념요소명','class':'tl',render:plus.renderer.clickbox});

        gridColumn.push({'data':'useYn','title':'상태',code:plus.codes['USE_YN'],render:plus.renderer.code});
        gridColumn.push({'data':'regDate','title':'등록/수정',render:plus.renderer.iddate});

        gridElement = plus.makeGrid('#gridElement',gridColumn,plus.makeAjax('/plusadmin/ajax/aigo/conceptList',{
        	subId:function(){return $('.codex.SUB').val();},
        	acvId:function(){return $('.codex.ACV').val();},
        	searchString:function(){return $('#searchString').val();},
        	useYn:function(){return $('.USE_YN_ALL :checked').val();},
		},'resultList'),{attr:'속성'});



        /* tr클릭 이벤트*/
        $('#gridElement tbody ').on('click','.clickkbox',function () {
            var rowData =  gridElement.row( $(this).closest('tr') ).data();
            var info = gridElement.page.info();
            console.log(info);
            var tabTitle  = String.format('[{0}] {1}',rowData['abName'],'');

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
      plus.frontPage.popup($('#wrapEdit'), {cptId:'0','start':0,length:0,cptName:'',levId:'',subId:'',useYn:'y',bbDate:(new Date()).format('yyyy-MM-dd')},'NEW');
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