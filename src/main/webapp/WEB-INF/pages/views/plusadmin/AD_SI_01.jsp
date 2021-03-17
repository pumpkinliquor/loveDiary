<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<h2>프로모션 관리</h2>

			<table class="srchT">
				<colgroup>
					<col width="18%">
					<col width="*">
				</colgroup>
				<tbody>
					<tr>
						<th rowspan="2">검색</th>
						<td>
							<div class="srch_div">
								<select class="s_p1" name="searchGb" id="searchGb">
									<option value="">선택</option>
									<option value="ALL">전체</option>
									<option value="ING">진행중</option>
									<option value="RAD">대기</option>
									<option value="CLO">종료</option>
								</select>
								<select class="s_p1" name="searchType" id="searchType">
									<option value="">선택</option>
									<option value="prm_name">제목</option>
									<option value="prm_text">내용</option>
									<option value="concat(prm_name,prm_text)">제목+내용</option>
								</select>
								<input type="text" name="searchString" id="searchString" class="ipt2">
								<button type="submit" class="btn btn-st1 btnSearch">검색</button>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		<div id="wrapList">
			<table class="tbl-st mt30" id="gridElement">
				<colgroup>
					<col width="6%">
					<col width="7%">
					<col width="12%">
					<col width="12%">
					<col width="12%">
					<col width="12%">
					<col width="9%">
					<col width="7%">
					<col width="7%">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>대상</th>
						<th>제목</th>
						<th>시작일</th>
						<th>종료일</th>
						<th>당첨자발표</th>
						<th>노출순서</th>
						<th>상태</th>
						<th>노출</th>
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
				<a href="javascript:;" class="btn btn-offer btnReg">등록</a>

			</div>
		</div>

<div class="modal-wrap pop01">
	<div class="modal">
		<div class="modal-header">
			<h1>과목 등록/수정</h1>
		</div>
		<form id="wrapEdit" action="/plusadmin/ajax/aigo/promotionExcute" method="post" enctype="multipart/form-data">
			<input type="hidden" name="prmId" id="prmId" value="" />
			<input type="hidden" id="start" name="start" value="" />
			<input type="hidden" id="length" name="length" value="" />
			<input type="hidden" name="fileList" value="[]" />
			<div class="content">

				<table class="popT">
					<colgroup>
						<col width="30%">
						<col width="*">
					</colgroup>
					<tbody>
					<tr>
	    			<th>타이틀 <em class="point">*</em></th>
	    			<td colspan="3"><input type="text" class="" name="prmName" id="prmName"></td>
	    		</tr>
	    		<tr>
	    			<th>대상</th>
	    			<td>
						<div class="radio_wrap rdo_wrap1 code PRM_TARGET" type="select" data="prmTarget"></div>

	    			</td>
	    			<th>노출순서</th>
	    			<td><input type="text" class="" name="prmOrder" id="prmOrder"></td>
	    		</tr>
	    		<tr>
	    			<th>기간 <em class="point">*</em></th>
	    			<td>
	    				<div class="date_div">
	                      <span class="ipt_dates"><input type="date" class="ipt_date" name="prmStartDate" id="prmStartDate"></span><span>~</span>
	                      <span class="ipt_dates"><input type="date" class="ipt_date" name="prmEndDate" id="prmEndDate"></span>
	                    </div>
	    			</td>
	    			<th>당첨자 발표</th>
	    			<td><span class="ipt_dates"><input type="date" class="ipt_date" name="prmDate" id="prmDate"></span></td>
	    		</tr>
	    		<tr>
	    			<th>배너 <em class="point">*</em></th>
	    			<td colspan="3" class="fileMap">
	    				<div class="add_file_div inlineB">
	        				<div class="filebox">
	        					<label for="ex_filename1" class="btn btn-st1">파일찾기</label>
	        					<input class="upload-name" name="pcthumb" value="파일명" disabled="disabled">
	        					<i class="fas fa-times fa-times2"></i>

	        					<input type="file" id="ex_filename1" class="upload-hidden">
	        				</div>
	        				<p class="txt_p">※  이벤트 배너 이미지 사이즈: 가로 X 세로 규격에 맞는 배너를 사용하세요. </p>
	    				</div>
	    				<div class="thumb_img"><img src="../images/tmp_thumb.jpg"></div>
	    			</td>
	    		</tr>
	    		<tr>
	    			<th>경품 <em class="point">*</em></th>
	    			<td colspan="3">
	    				<input type="text" placeholder="텍스트 최대 30자" name="prmEvent" id="prmEvent">
	    			</td>
	    		</tr>
	    		<tr>
	    			<th>이벤트 상세 <em class="point">*</em></th>
	    			<td colspan="3">
	    				<textarea class="form-control" name="prmText" id="prmText" style="height:200px;">에디터 영역</textarea>
	    			</td>
	    		</tr>
	    		<tr>
	    			<th>상태</th>
	    			<td colspan="3">
	    				<div class="radio_wrap rdo_wrap1 code USE_YN" type="radio" data="useYn"></div>
	    			</td>
	    		</tr>
	    		<tr>
	    			<th>사용자노출</th>
	    			<td colspan="3">
	    				<div class="radio_wrap rdo_wrap1 code USE_YN" type="radio" data="userActive"></div>
	    			</td>
	    		</tr>
	    		<tr class="EDIT hidden">
					<th>등록/수정</th>
					<td colspan="3" class="show_iddate">이지현(admin) yyyy-mm-dd hh:mm:ss</td>
				</tr>
					</tbody>
				</table>

			</div>
			<div class="modal-footer">
				<button class="btn btn-primary fR btnSubmit">저장</button>
			</div>
		</form>
		<a href="#" class="close-pop close-btn">&#10005;</a>
	</div>
</div>
<script type="text/javascript">
var gridElement =null,gridColumn =[];
$(document).ready(function(){
    /* 코드 사용 */
    $.call('/ajax/codeList',{codes:'BI,USE_YN,PRM_TARGET'},function(r){
        $.extend(plus.codes,r.codes);
    });
    $.each(plus.codes,function(k,v){
      $('.code.'+k).each(function(){
      	$(this).addCodeItem(v);
	  })

    });

    $(':radio[name=useYn]').change(function(){
    	if($(':radio[name=useYn]:checked').val()=='n'){
			$(':radio[name=userActive]:eq(1)').prop('checked',true);
		}
	})

    /* tab 생성후 초기이벤트*/
    plus.event.tabAfter=function(pageContentLast, rowData, mode){

        var rules = {
            prmName:{required:true}
            ,useYn:{required:true}
        };
        pageContentLast.data({rules:rules});
        pageContentLast.data({messages:rules});
        var tableElement =pageContentLast.find('table');
        plus.event.formAfter(pageContentLast,rowData,mode);
        plus.event.bbsfile(rowData);
        $('.error').html('');


    }

    //페이지 래디 정의
    plus.event.tabReady=function(){
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
        gridColumn.push({'data': 'umSeq', 'title': '번호',  render:plus.renderer.rrownum});
        gridColumn.push({'data':'prmTarget','title':'대상',code:plus.codes['PRM_TARGET'],render:plus.renderer.code});
        gridColumn.push({'data':'prmName','title':'제목','class':'tl',render:plus.renderer.clickbox});
		gridColumn.push({'data':'prmStartDate','title':'시작일','class':'tl',render:plus.renderer.clickbox});
		gridColumn.push({'data':'prmEndDate','title':'종료일','class':'tl',render:plus.renderer.clickbox});
		gridColumn.push({'data':'prmDate','title':'당첨자발표','class':'tl',render:plus.renderer.clickbox});
		gridColumn.push({'data':'prmOrder','title':'노출순서'});
		gridColumn.push({'data':'useYn','title':'상태',code:plus.codes['USE_YN'],render:plus.renderer.code});
        gridColumn.push({'data':'userActive','title':'사용자노출',code:plus.codes['USE_YN'],render:plus.renderer.code});
        gridColumn.push({'data':'regDate','title':'등록/수정',render:plus.renderer.iddate});
        gridElement = plus.makeGrid('#gridElement',gridColumn,plus.makeAjax('/plusadmin/ajax/aigo/promotionList',{},'resultList'),{pageLength:10,attr:'속성'});


        /* tr클릭 이벤트*/
        $('#gridElement tbody ').on('click','.clickkbox',function () {
            var rowData =  gridElement.row( $(this).closest('tr') ).data();
            var info = gridElement.page.info();
            console.log(info);
            var tabTitle  = String.format('[{0}] {1}',rowData['abName'],'');

            //console.log(rowData);
            //plus.frontTab.addTab(tabTitle,rowData,$('#wrapEdit').tmpl({updateUrl:'/front/ajax/assets/buildingExcute',deleteUrl:'/front/ajax/assets/buildingDelete'}));
            //$('#wrapList').hide();
            rowData['start']=info['start']
            rowData['length']=info['length']

            plus.frontPage.popup($('#wrapEdit'),rowData,'EDIT');

        });
        // $(':radio[name=useYn]').change(function(){
		// if($(':radio[name=useYn]:checked').val()=='n'){
		//  alert('해당 단원으로 등록된 문항 모두 비공개됩니다.')
		// }
		// })
        }


    /* tab페이지 초기에 실행할 이벤트*/
    plus.event.tabReady();
    /* 등록 버튼*/
    $('.btnReg').click(function(){
      //$('#wrapList').hide();

      plus.frontPage.popup($('#wrapEdit'), {subId:'0','start':0,length:0,subName:'',useYn:'y',userActive:'y'},'NEW');

    });

    $('.btnSearch').click(function(){
        //gridElement.ajax.reload();

		gridElement = plus.makeGrid('#gridElement',gridColumn,plus.makeAjax('/plusadmin/ajax/aigo/promotionList',$('.srchT').domJson(),'resultList'),{pageLength:10,attr:'속성'});
    });
    $('#searchString').keypress(function(e){
        if(e.keyCode==13){
            $('.btnSearch').click();
        }
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
    $('.btnSubmit').unbind('click').click(function(){

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
