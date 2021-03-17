<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<h2>출제경향관리</h2>
		<div id="wrapList">
			<table class="tbl-st" id="gridElement">
				<colgroup>
					<col width="5%">
					<col width="10%">
					<col width="10%">
					<col width="*">
					<col width="5%">
					<col width="20%">
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>레벨</th>
						<th>과목</th>
						<th>성취기준이름</th>
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
				<a href="javascript:;" class="btn btn-offer btnReg">등록</a>

			</div>
		</div>
			<form id="wrapEdit" class="hidden" action="/plusadmin/ajax/aigo/tendencyExcute" method="post" enctype="multipart/form-data">
			<input type="hidden" name="tenId" id="tenId" value="" />
			<input type="hidden" id="start" name="start" value="" />
			<input type="hidden" id="length" name="length" value="" />
			<table class="tbl-st writeT">
	        	<colgroup>
	        		<col width="20%">
	        		<col width="*">
	        	</colgroup>
	        	<tbody>
	        		<tr class="EDIT hidden">
	        			<th>번호 <em class="point">*</em></th>
	        			<td>112</td>
	        		</tr>
	        		<tr>
	        			<th>성취기준 <em class="point">*</em></th>
	        			<td>
	        				<div class="mb10">
		        				<select class="s_p1 code LEV tab_cont" type="select" data="levId" name="levId" id="levId">

		        				</select>
		        				<select class="s_p1 code SUB tab_cont" type="select" data="subId" name="subId" id="subId">

		        				</select>
	        				</div>
	        				<div class="radio_wrap rdo_wrap1 code ACV" type="select" data="acvId"></div>
	        			</td>
	        		</tr>
					<tr>
						<th>학습목표 <em class="point">*</em></th>
							<td class="fileMap" >
								<div class="add_file_div inlineB">
									<div class="filebox">
										<label for="pcthumb" class="btn btn-st1">파일찾기</label>
										<input class="upload-name" value="파일명" disabled="disabled">
										<i class="fas fa-times fa-times2"></i>
										<label for="pcthumb" class="file-bg hidden">Download</label>
										<input type="file" id="pcthumb" name="pcthumb" class="upload-hidden file-upload">
									</div>
	<%--		        				<p class="point">※ 이미지 사이즈 000*000</p>--%>
								</div>
								<div class="thumb_img file-img2"><img src="/admassets/images/tmp_thumb.jpg" onerror="this.src='/admassets/images/tmp_thumb.jpg'"></div>
							</td>
						</tr>
	        		<tr>
	        			<th>내용 <em class="point">*</em></th>
	        			<td><textarea class="form-control html-editor" name="tenText" id="tenText" style="height:200px;"></textarea></td>
	        		</tr>
	        		<tr>
	        			<th>상태</th>
	        			<td>
	        				<div class="radio_wrap rdo_wrap1 code USE_YN" type="radio" data="useYn"></div>
	        			</td>
	        		</tr>
	        		<tr class="EDIT hidden">
						<th>등록/수정</th>
						<td class="show_iddate">
							이지현(admin) yyyy-mm-dd hh:mm:ss
						</td>
					</tr>
	        	</tbody>
	        </table>
	        <div class="tbl-foot">
				<a href="javascript:;" class="btn btn-list btnList">목록</a>
				<a href="javascript:;" class="btn btn-offer btnSubmit">저장</a>
			</div>
			</form>
<script type="text/javascript">
var gridElement =null,gridColumn =[];
$(document).ready(function(){
    /* 코드 사용 */
    /* 코드 사용 */
    $.call('/ajax/codeList',{codes:'BI,USE_YN,SUB,LEV,ACV,ACA'},function(r){
        $.extend(plus.codes,r.codes);
    });
    $.each(plus.codes,function(k,v){
      $('.code.'+k).addCodeItem(v,true);
      $('.codex.'+k).addCodeItem(v,true);
    });
    $('.code.CONTYPEALL :radio').click(function(){
    	$('tr.CONTYPEALL').hide();
		$('tr.CONTYPEALL.'+$(this).attr('value')).show()
	})
	// $('#levId option').prop('disabled',false);
	// $('#subId option').prop('disabled',false);

	$('.fileMap :file').change(function(){
      var fileOne = $(this).get(0).files[0];
      if(fileOne){
        $(this).closest('td').find('.upload-name').val(fileOne.name);
        $(this).closest('td').find('img').attr('src',URL.createObjectURL(fileOne));
      }
    });


    /* tab 생성후 초기이벤트*/
    plus.event.tabAfter=function(pageContentLast, rowData, mode){
        var rules = {
            levId:{required:true}
            ,subId:{required:true}
            ,acvId:{required:true}
            ,tenText:{required:true}
        };
        pageContentLast.data({rules:rules});
        var tableElement =pageContentLast.find('table');
        console.log("$('#subId').val(rowData['subId']);",rowData['subId']);

        plus.event.formAfter(pageContentLast,rowData,mode);
        $('#subId').val(rowData['subId']);
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
        plus.event.clickbox=function(d, t, r){
          var div = plus.makeElement('a',d,{'href':'javascript:;','class':'custom-control custom-checkbox'});
          return div.prop('outerHTML')
        }
						// <th>번호</th>
						// <th>레벨</th>
						// <th>과목</th>
						// <th>성취기준이름</th>
						// <th>상태</th>
						// <th>등록/수정</th>
        gridColumn.push({'data': 'umSeq', 'title': '번호', 'type': 'checkbox', hidden: false,render:plus.renderer.rrownum});
        gridColumn.push({'data':'levName','title':'레벨명'});
        gridColumn.push({'data':'subName','title':'과목명'});
        gridColumn.push({'data':'acvName','title':'성취기준명','class':'tl',render:plus.renderer.clickbox});

        gridColumn.push({'data':'useYn','title':'상태',code:plus.codes['USE_YN'],render:plus.renderer.code});
        gridColumn.push({'data':'regDate','title':'등록/수정',render:plus.renderer.iddate});

        gridElement = plus.makeGrid('#gridElement',gridColumn,plus.makeAjax('/plusadmin/ajax/aigo/tendencyList',{},'resultList'),{attr:'속성'});



        /* tr클릭 이벤트*/
        $('#gridElement tbody ').on('click','.clickkbox',function () {
            var rowData =  gridElement.row( $(this).closest('tr') ).data();
            var info = gridElement.page.info();
            console.log(rowData);
            var tabTitle  = String.format('[{0}] {1}',rowData['abName'],'');
			$('#wrapList').hide();

            //console.log(rowData);
            //plus.frontTab.addTab(tabTitle,rowData,$('#wrapEdit').tmpl({updateUrl:'/front/ajax/assets/buildingExcute',deleteUrl:'/front/ajax/assets/buildingDelete'}));
            $('#wrapList').hide();
            rowData['start']=info['start']
            rowData['length']=info['length']
            plus.frontPage.show($('#wrapEdit'),rowData,'EDIT');
			// $('#levId option').prop('disabled',true);
			// $('#subId option').prop('disabled',true);

        });

		$('#acvId').change(function(){
			$.call('/plusadmin/ajax/aigo/achievementDetail',{'acvId':$('#acvId').val()},function(r){
				// $('#levId option:selected').prop('disabled',true);
				// $('#subId option:selected').prop('disabled',true);
				$('#subId').val(r['resultData']['subId']).change();
				$('#levId').val(r['resultData']['levId']).change();
				$('#levId option:selected').prop('disabled',false);
				$('#subId option:selected').prop('disabled',false);


			});
		})

    }


    /* tab페이지 초기에 실행할 이벤트*/
    plus.event.tabReady();
    /* 등록 버튼*/
    $('.btnReg').click(function(){
    	$('#wrapList').hide();
    	$('select.code.tab_cont').removeClass('tab_cont');
		// $('#levId option').prop('disabled',false);
		// $('#subId option').prop('disabled',false);
      	plus.frontPage.show($('#wrapEdit'), {tenId:'0','start':0,useYn:'Y',length:0,tenText:'',subId:'',acvId:'',levId:''},'NEW');
      	$(':radio[name=useYn]:eq(0)').click();
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

		var editorId = $('.html-editor').attr('id');
		oEditors.getById[editorId].exec("UPDATE_CONTENTS_FIELD", []);
		if($('#notText').val()==""){
			Swal.fire(
					'[내용] 항목은 필수입니다.',
					'',
					'error'
			)
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