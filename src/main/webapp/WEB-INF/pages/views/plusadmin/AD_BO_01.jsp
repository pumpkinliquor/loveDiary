<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<h2>공지사항</h2>

	    <table class="srchT">
			<colgroup>
				<col width="20%">
				<col width="*">
			</colgroup>
			<tbody>
				<tr>
					<th rowspan="2">검색</th>
					<td class="bbx">
						<div class="radio_wrap code USE_YN_ALL" type="radio" data="searchUseYn">

						</div>
					</td>
				</tr>
				<tr>
					<td class="ptx">
						<div class="srch_div">
							<select class="s_p1">
								<option value="">선택</option>
								<option value="bb_title">제목</option>
								<option value="bb_contents">내용</option>
								<option value="concat(bb_title,bb_contents)">제목+내용</option>
							</select>
							<input type="text" name="" class="ipt_p1">
							<button type="submit" class="btn btn-st1 btnSearch">검색</button>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		<div id="wrapList">
			<table class="tbl-st mt30" id="gridElement">
				<colgroup>
					<col width="7%">
					<col width="9%">
					<col width="*">
					<col width="12%">
					<col width="9%">
					<col width="9%">
					<col width="12%">
					<col width="9%">
					<col width="9%">
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>노출대상</th>
						<th>제목</th>
						<th>작성자</th>
						<th>상단고정</th>
						<th>첨부</th>
						<th>등록일시</th>
						<th>조회수</th>
						<th>공개</th>
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
		<form id="wrapEdit" class="hidden" action="/plusadmin/ajax/bbs/bbsExcute" method="post" enctype="multipart/form-data">
			<input type="hidden" name="bbSeq" id="bbSeq" value="" />
			<input type="hidden" name="bbBbs" id="bbBbs" value="" />
			<input type="hidden" id="start" name="start" value="" />
			<input type="hidden" id="length" name="length" value="" />
			<input type="hidden" name="fileList" value="[]" />
			<table class="tbl-st writeT">
		    	<colgroup>
	        		<col width="17%">
	        		<col width="*">
	        	</colgroup>
		    	<tbody>
		    		<tr class="EDIT hidden">
		    			<th>번호 <em class="point">*</em></th>
		    			<td class="bbSeq">112</td>
		    		</tr>
		    		<tr>
	        			<th>노출대상 <em class="point">*</em></th>
	        			<td>
	        				<div class="radio_wrap rdo_wrap1 code PRM_TARGET" type="radio" data="bbTarget">

							</div>
	        			</td>
	        		</tr>
					<tr>
	        			<th>상단고정 <em class="point">*</em></th>
	        			<td>
	        				<div class="radio_wrap rdo_wrap1 code BB_TYPE" type="radio" data="bbType">

							</div>
	        			</td>
	        		</tr>
	        		<tr>
	        			<th>제목 <em class="point">*</em></th>
	        			<td>
	        				<input type="text" name="bbTitle" id="bbTitle">
	        			</td>
	        		</tr>
	        		<tr>
		    			<th>내용 <em class="point">*</em></th>
		    			<td>
		    				<div class="editor_div">
								<textarea class="form-control html-editor" name="bbContents" id="bbContents" style="height:200px;"></textarea>
							</div>
		    			</td>
		    		</tr>
		    		<tr>
		    			<th>첨부파일</th>
		    			<td class="file" data="file">

		    			</td>
		    		</tr>
		    		<tr>
	        			<th>상태</th>
	        			<td >
							<div class="radio_wrap rdo_wrap1 code USE_YN" type="radio" data="useYn"></div>
						</td>
					</tr>
					<tr class="EDIT hidden">
						<th>등록/수정</th>
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
    $.call('/ajax/codeList',{codes:'BI,USE_YN,BB_TYPE,PRM_TARGET,USE_YN_ALL'},function(r){
        $.extend(plus.codes,r.codes);
    });

    $.each(plus.codes,function(k,v){
      $('.code.'+k).addCodeItem(v,true)
      $('.codex.'+k).addCodeItem(v,true)
    });
    $('.USE_YN_ALL :radio:eq(0)').click();





    /* tab 생성후 초기이벤트*/
    plus.event.tabAfter=function(pageContentLast, rowData, mode){
        var rules = {
            levId:{required:true}
            ,useYn:{required:true}
        };
        $('.srchT').hide();
        pageContentLast.data({rules:rules});
        var tableElement =pageContentLast.find('table');
        plus.event.formAfter(pageContentLast,rowData,mode);
        plus.event.bbsfilebbs(rowData);


    }

    //페이지 래디 정의
    plus.event.tabReady=function(){
        plus.evnet.submitAfter=function(){
            $('#wrapList').show();
            $('#wrapEdit').hide();
            $('.srchT').show();

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
						// 		<th>번호</th>
						// <th>노출대상</th>
						// <th>제목</th>
						// <th>작성자</th>
						// <th>상단고정</th>
						// <th>첨부</th>
						// <th>등록일시</th>
						// <th>조회수</th>
						// <th>공개</th>
        // gridColumn.push({'data': 'umSeq', 'title': plus.event.checkAll, 'type': 'checkbox', hidden: false,render:plus.event.seqCheckBox});
        gridColumn.push({'data':'levName','title':'순번',render:plus.renderer.rrownum });
        gridColumn.push({'data':'bbTarget','title':'노출대상',code:plus.codes['PRM_TARGET'],render:plus.renderer.code});
        gridColumn.push({'data':'bbTitle','title':'제목',render:plus.renderer.clickbox});
        gridColumn.push({'data':'regUmName','title':'작성자',render:plus.renderer.clickbox});
        gridColumn.push({'data':'bbType','title':'상단고정',code:plus.codes['BB_TYPE'],render:plus.renderer.code});
        gridColumn.push({'data':'filesize','title':'첨부'});


		gridColumn.push({'data':'regDate','title':'등록/수정',render:plus.renderer.iddate});
		gridColumn.push({'data':'bbView','title':'조회수'});
		gridColumn.push({'data':'useYn','title':'공개',code:plus.codes['USE_YN'],render:plus.renderer.code});

        gridElement = plus.makeGrid('#gridElement',gridColumn,plus.makeAjax('/plusadmin/ajax/bbs/bbsList',{bbBbs:'NOTICE'},'resultList'),{attr:'속성'});



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
            plus.frontPage.show($('#wrapEdit'),rowData,'EDIT');

        });
    }


    /* tab페이지 초기에 실행할 이벤트*/
    plus.event.tabReady();


    /* 등록 버튼*/
    $('.btnReg').click(function(){
    	$('#wrapList').hide();
      plus.frontPage.show($('#wrapEdit'), {bbSeq:'0','start':0,length:0,bbTarget:'ALL',bbBbs:'NOTICE',bbTitle:'',useYn:'y',bbContents:'',bbDate:(new Date()).format('yyyy-MM-dd')},'NEW');
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
      $('.srchT').show();
    });
    $('.btnSubmit').click(function(){
		var fileLists = [];
		  $(':file').each(function(){
			var data = $(this).data();
			console.log(data);
			if(data['bafSeq']){
			  fileLists.push(data);
			}
		  });

    	// var contypeall =   $('.code.CONTYPEALL :radio:checked').val();
    	// if(contypeall=='T'){
			var editorId = $('.html-editor').attr('id');
			oEditors.getById[editorId].exec("UPDATE_CONTENTS_FIELD", []);
			if($($.trim($('#'+editorId).val())).text()==""){
				Swal.fire(
						'[내용] 항목은 필수입니다.',
						'',
						'error'
				)
				return false;
			}
		// }
		$(this).closest('form').find('input[name=fileList]').val(JSON.stringify(fileLists));
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