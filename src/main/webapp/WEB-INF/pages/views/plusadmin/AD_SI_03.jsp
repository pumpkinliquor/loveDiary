<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<h2>푸시관리</h2>

		<table class="srchT">
				<colgroup>
					<col width="18%">
					<col width="*">
				</colgroup>
				<tbody>
					<tr>
						<th rowspan="2">검색</th>
						<td class="bbx">
							<div class="radio_wrap" style="width:auto">
								<div class="ipt_radio_div">
									<input type="radio" id="srchAll" name="srch" checked="checked">
									<label for="srchAll">전체</label>
								</div>
								<div class="ipt_radio_div">
									<input type="radio" id="srch1" name="srch">
									<label for="srch1">일반</label>
								</div>
								<div class="ipt_radio_div mr20">
									<input type="radio" id="srch2" name="srch">
									<label for="srch2">예약</label>
								</div>
								<div class="ipt_radio_div mr20">
									<input type="radio" id="srch3" name="srch">
									<label for="srch3">반복</label>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="ptx">
							<div class="srch_div">
								<select class="s_p1">
									<option>선택</option>
									<option>제목</option>
									<option>내용</option>
									<option>제목+내용</option>
								</select>
								<input type="text" name="" class="ipt_p1">
								<button type="submit" class="btn btn-st1">검색</button>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		<div id="wrapList">
			<table class="tbl-st mt30" id="gridElement">
				<colgroup>
					<col width="4.5%">
					<col width="4.5%">
					<col width="5.5%">
					<col width="4.5%">
					<col width="9%">

					<col width="9%">
					<col width="9%">
					<col width="9%">

					<col width="6%">
					<col width="4.5%">
					<col width="4.5%">
					<col width="4.5%">
					<col width="10%">

					<col width="9%">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th>NO</th>
						<th class="sort_x">형태</th>
						<th class="sort_x">발송OS</th>
						<th class="sort_x">대상</th>
						<th class="sort_x">제목</th>

						<th class="sort_x">내용</th>
						<th>등록일</th>
						<th>발송일시</th>
						<th class="sort_x">발송상태</th>
						<th class="sort_x">발송수</th>
						<th class="sort_x">도달수</th>
						<th class="sort_x">확인수</th>
						<th class="sort_x">도달수<br>페이지</th>
						<th class="sort_x">등록/수정</th>
						<th class="sort_x">취소</th>
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
				<a href="javascript:;" class="btn btn-offer btnReg">등록</a>

			</div>
		</div>
		<form id="wrapEdit" class="hidden" action="/plusadmin/ajax/aigo/notificationExcute" method="post" enctype="multipart/form-data">
			<input type="hidden" name="notId" id="notId" value="" />
			<input type="hidden" name="targetMemId" id="targetMemId" value="" />
			<input type="hidden" name="notReadDatetime" id="notReadDatetime" value="" />
			<input type="hidden" id="start" name="start" value="" />
			<input type="hidden" id="length" name="length" value="" />
			<table class="tbl-st writeT">
		    	<colgroup>
		    		<col width="17%">
		    		<col width="*">
		    	</colgroup>
		    	<tbody>
		    		<tr>
		    			<th>푸시 형태 <em class="point">*</em></th>
		    			<td>
		    				<div class="radio_wrap rdo_wrap1 code NOT_TYPE" type="radio"  data="notType">

							</div>
							<p class="point mt5">일반: 저장 즉시 발송, 예약: 세팅한 발송일시에 발송, 반복: 세팅 기준으로 반복 발송 </p>
		    			</td>
		    		</tr>
		    		<tr>
		    			<th>발송일시 <em class="point">*</em></th>
		    			<td>
		    				<div class="date_div">
		                      <span class="ipt_dates"><input type="date" class="ipt_date mr" id=""></span>
		                      <input type="number" name="" class="ipt_time" value="00"><span>:</span><input type="text" name="" class="ipt_time" value="00">
		                    </div>
		    			</td>
		    		</tr>
		    		<tr>
		    			<th>발송 OS <em class="point">*</em></th>
		    			<td>
		    				<div class="radio_wrap rdo_wrap1 code NOT_OS" type="radio"  data="notOs">

							</div>
		    			</td>
		    		</tr>
		    		<tr>
		    			<th rowspan="2">발송대상 선택 <em class="point">*</em></th>
		    			<td>
		    				<div class="radio_wrap rdo_wrap1 inlinB">
								<div class="ipt_radio_div">
									<input type="radio" id="targetALL" name="target" checked="checked">
									<label for="targetALL">전체</label>
								</div>
								<div class="ipt_radio_div">
									<input type="radio" id="targetGroup" name="target">
									<label for="targetGroup">그룹</label>
								</div>
								<button type="button" class="btn btn_add btn-secondary"><i></i>그룹 추가</button>
							</div>
							<div class="radio_wrap rdo_wrap1 inlinB">
								<div class="ipt_radio_div">
									<input type="radio" id="targetIndividual" name="target">
									<label for="targetIndividual">개인</label>
								</div>
								<button type="button" class="btn btn_add btn-secondary"><i></i>사용자 추가</button>
							</div>

		    			</td>
		    		</tr>
		    		<tr>
		    			<td>
		    				<div class="regi_members">
			    				<span class="tit">등록회원</span>
			    				<ul class="member_list">
			    					<li><span>Jee****@ago.com</span><a href="#" class="btn_delete">x</a></li>
			    					<li><span>Jee****@ago.com</span><a href="#" class="btn_delete">x</a></li>
			    					<li><span>Jee****@ago.com</span><a href="#" class="btn_delete">x</a></li>
			    					<li><span>Jee****@ago.com</span><a href="#" class="btn_delete">x</a></li>
			    					<li><span>Jee****@ago.com</span><a href="#" class="btn_delete">x</a></li>
			    					<li><span>Jee****@ago.com</span><a href="#" class="btn_delete">x</a></li>
			    					<li><span>Jee****@ago.com</span><a href="#" class="btn_delete">x</a></li>
			    				</ul>
			    				<a href="javascript:void(0)" class="btn btn-default btn_reset"><i class="fas fa-undo"></i> RESET</a>
		    				</div>
		    			</td>
		    		</tr>
		    		<tr>
		    			<th>제목 <em class="point">*</em></th>
		    			<td>
		    				<input type="text" class="" name="notTitle" id="notTitle" placeholder="푸시 타이틀로 노출되는 내용입니다.">
		    				<p class="point mt5">공백포함 40자</p>
		    			</td>
		    		</tr>
		    		<tr>
		    			<th>내용 <em class="point">*</em></th>
		    			<td>
		    				<textarea class="txtarea1" name="notMessage" id="notMessage" placeholder="푸시 내용 입력"></textarea>
		    				<div class="txtarea_btm">
		    					<p class="point">공백포함 140자</p>
		    					<span class="txt_num">1/40</span>
		    				</div>
		    			</td>
		    		</tr>
		    		<tr>
		    			<th>도달 페이지</th>
		    			<td>
		    				<input type="text" class="" name="notUrl" id="notUrl" placeholder="">
		    				<p class="point mt5">미 입력 시 앱 메인으로 열립니다</p>
		    			</td>
		    		</tr>
					<tr class="EDIT hidden">
						<th>등록</th>
						<td class="show_iddate">이지현(admin) yyyy-mm-dd hh:mm:ss</td>
					</tr>
		    	</tbody>
		    </table>

	        <div class="tbl-foot">
				<a href="#" class="btn btn-list btnList">목록</a>
				<a href="#" class="btn btn-offer btnSubmit">저장</a>
			</div>
		</form>
<script type="text/javascript">
var gridElement =null,gridColumn =[];
$(document).ready(function(){
    /* 코드 사용 */
    /* 코드 사용 */
    $.call('/ajax/codeList',{codes:'BI,USE_YN,NOT_TYPE,NOT_OS,NOT_TARGET,NOT_TYPE,CONTYPEALL'},function(r){
        $.extend(plus.codes,r.codes);
    });
    plus.codes['NOT_TARGET'] = [];
    $.each(plus.codes,function(k,v){
      $('.code.'+k).addCodeItem(v,true)
      $('.codex.'+k).addCodeItem(v,true)
    });
    $('.code.CONTYPEALL :radio').click(function(){
    	$('tr.CONTYPEALL').hide();
		$('tr.CONTYPEALL.'+$(this).attr('value')).show()
	})





    /* tab 생성후 초기이벤트*/
    plus.event.tabAfter=function(pageContentLast, rowData, mode){
        var rules = {
            levId:{required:true}
            ,useYn:{required:true}
        };
        pageContentLast.data({rules:rules});
        var tableElement =pageContentLast.find('table');
        plus.event.formAfter(pageContentLast,rowData,mode);
        plus.event.bbsfile(rowData);


    }

    //페이지 래디 정의
    plus.event.tabReady=function(){
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
            var id = plus.getId(r.bbSeq);
            plus.makeInput('checkbox','',{'class':"custom-control-input biSeq",id:"bbSeq"+(id),value:r.bbSeq}).appendTo(div);
            plus.makeElement('label','',{'class':'custom-control-label',for:'bbSeq'+(id)}).appendTo(div);
            return div.prop('outerHTML')
        }
        plus.renderer.btnCancle = function(d, t, r){
        	if(r['notType']=='REV'){
				var div = plus.makeElement('a','취소',{'href':'javascript:;','class':'custom-control custom-checkbox'});
				return div.prop('outerHTML')
			}
        	return '';

		}
        plus.event.clickbox=function(d, t, r){
          var div = plus.makeElement('a',d,{'href':'javascript:;','class':'custom-control custom-checkbox'});
          return div.prop('outerHTML')
        }
						// <th>NO</th>
						// <th class="sort_x">형태</th>
						// <th class="sort_x">발송OS</th>
						// <th class="sort_x">대상</th>
						// <th class="sort_x">제목</th>
						//
						// <th class="sort_x">내용</th>
						// <th>등록일</th>
						// <th>발송일시</th>

						// <th class="sort_x">발송상태</th>
						// <th class="sort_x">발송수</th>
						// <th class="sort_x">도달수</th>
						// <th class="sort_x">확인수</th>
						// <th class="sort_x">도달수<br>페이지</th>
						// <th class="sort_x">등록/수정</th>
						// <th class="sort_x">취소</th>
        // gridColumn.push({'data': 'umSeq', 'title': plus.event.checkAll, 'type': 'checkbox', hidden: false,render:plus.event.seqCheckBox});
        gridColumn.push({'data':'levName','title':'순번',render:plus.renderer.rrownum });
        gridColumn.push({'data':'notType','title':'형태',code:plus.codes['NOT_TYPE'],render:plus.renderer.code});
        gridColumn.push({'data':'notOs','title':'발송OS',code:plus.codes['NOT_OS'],render:plus.renderer.code});
        gridColumn.push({'data':'notTarget','title':'대상',code:plus.codes['NOT_TARGET'],render:plus.renderer.code});
        gridColumn.push({'data':'notTitle','title':'제목',render:plus.renderer.clickbox});
        gridColumn.push({'data':'notMessage','title':'내용',render:plus.renderer.clickbox});
        gridColumn.push({'data':'regSysdate','title':'등록일',render:plus.renderer.datetime});
        gridColumn.push({'data':'notDatetime','title':'발송일시',render:plus.renderer.datetime});
        gridColumn.push({'data':'0','title':'발송상태',render:plus.renderer.clickbox});
        gridColumn.push({'data':'0','title':'발송수',render:plus.renderer.clickbox});
        gridColumn.push({'data':'0','title':'도달수',render:plus.renderer.clickbox});
        gridColumn.push({'data':'0','title':'확인수',render:plus.renderer.clickbox});
        gridColumn.push({'data':'0','title':'도달수<br>페이지',render:plus.renderer.clickbox});


        gridColumn.push({'data':'regDate','title':'등록/수정',render:plus.renderer.iddate});
        gridColumn.push({'data':'levName','title':'취소',render:plus.renderer.btnCancle});

        gridElement = plus.makeGrid('#gridElement',gridColumn,plus.makeAjax('/plusadmin/ajax/aigo/notificationList',{},'resultList'),{attr:'속성'});



        /* tr클릭 이벤트*/
        $('#gridElement tbody ').on('click','.custom-checkbox',function () {
        	var rowData =  gridElement.row( $(this).closest('tr') ).data();
        	if(confirm('해당발송을 취소하시겠습니까?')){

			}
		});
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
            plus.frontPage.show($('#wrapEdit'),rowData,'EDIT');

        });
    }


    /* tab페이지 초기에 실행할 이벤트*/
    plus.event.tabReady();


    /* 등록 버튼*/
    $('.btnReg').click(function(){
    	$('#wrapList').hide();
      plus.frontPage.show($('#wrapEdit'), {notId:'0','start':0,length:0,useYn:'y',targetMemId:'0',notReadDatetime:(new Date()).format('yyyy-MM-dd')},'NEW');
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