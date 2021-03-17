<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<h2>개념관리</h2>
		<div id="wrapList">
			<table class="srchT">
				<colgroup>
					<col width="150px">
					<col width="*">
				</colgroup>
				<tbody>
					<tr>
						<th rowspan="2">검색</th>
						<td>
							<div class="mb10">
								<span class="tit">등록일</span>
								<div class="date_div">
			                      <span class="ipt_dates"><input type="date" class="ipt_date" id="sdate" name="sdate"></span><span>~</span>
			                      <span class="ipt_dates"><input type="date" class="ipt_date" id="edate" name="edate"></span>
			                    </div>
		                    </div>
		                    <div>
		                    	<span class="tit">상태</span>
								<div class="radio_wrap rdo_wrap1 codex USE_YN_ALL" type="radio" data="searchUseYn"></div>
		                    </div>
		                </td>
		            </tr>
		            <tr>
		            	<td>
							<div class="srch_div mb10">
								<select class="s_p1 codex LEV" type="select" data="levId">
									<option>레벨</option>
									<option></option>
								</select>
								<select class="s_p1 codex SUB" type="select" data="subId">
									<option>과목</option>
									<option></option>
								</select>
								<select class="s_p2 codex UNIT" type="select" data="unitId">
									<option>대단원</option>
									<option></option>
								</select>
								<select class="s_p4 codex ACV" type="select" data="acvId">
									<option>성취기준</option>
									<option></option>
								</select>
							</div>
							<div class="srch_div">
								<select class="s_p1" name="searchType" id="searchType">
									<option value="">검색구분</option>

									<option value="CMTR">해설코드</option>
									<option value="NOT">개념코드</option>
								</select>
								<input type="text" name="searchString" id="searchString" class="ipt3">
								<button type="submit" class="btn btn-st1 btnSearch">검색</button>
							</div>
						</td>
					</tr>
				</tbody>
			</table>

			<table class="tbl-st" id="gridElement">
				<colgroup>
					<col width="9%">
					<col width="10%">
					<col width="18%">
					<col width="13%">
					<col width="10%">
					<col width="9%">
					<col width="9%">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th>개념코드</th>
						<th>과목</th>
						<th>성취기준</th>
						<th>개념요소명</th>
						<th>콘텐츠타입</th>
						<th>내용</th>
						<th>상태</th>
						<th>등록/수정</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>

	        <div class="tbl-foot">
				<a href="#" class="btn btn-offer btnReg">등록</a>
			</div>
		</div>
			<form id="wrapEdit" class="hidden" action="/plusadmin/ajax/aigo/notionExcute" method="post" enctype="multipart/form-data">
			<input type="hidden" name="notId" id="notId" value="" />
			<input type="hidden" id="fileLists" name="fileLists" value="" />
			<input type="hidden" id="start" name="start" value="" />
			<input type="hidden" id="length" name="length" value="" />
			<h3>표준분류</h3>
			<table class="tbl-st writeT mb">
	        	<colgroup>
	        		<col width="15%">
	        		<col width="25%">
	        		<col width="15%">
	        		<col width="*">
	        	</colgroup>
	        	<tbody>
	        		<tr class="EDIT copy">
	        			<th>개념코드</th>
	        			<td colspan="3" class="td_inblck" >1234 <a href="#" class="btn btn-default">복제</a> <p class="point">※ 복제 시, 새로운 코드로 문항이 복제됩니다.</p></td>
	        		</tr>
	        		<tr>
	        			<th>과목 <em class="point">*</em></th>
	        			<td>
	        				<div class="radio_wrap rdo_wrap1 code SUB" type="select" data="subId"></div>
	        			</td>
	        			<th>레벨 <em class="point">*</em></th>
	        			<td>
	        				<div class="radio_wrap rdo_wrap1 code ACV" type="select" data="acvId"></div>
	        			</td>
	        		</tr>
<%--	        		<tr>--%>
<%--	        			<th>문제분류 <em class="point">*</em></th>--%>
<%--	        			<td colspan="3">--%>
<%--	        				<select class="s_p2 code ACA" type="select" data="acaId">--%>
<%--	        					<option>확인문제</option>--%>
<%--	        				</select>--%>
<%--	        				<select class="s_p2 code SUBACA" type="select" data="subAcaId">--%>
<%--	        					<option>유사2차</option>--%>
<%--	        				</select>--%>
<%--	        			</td>--%>
<%--	        		</tr>--%>
	        		<tr>
	        			<th>개념요소명 <em class="point">*</em></th>
	        			<td colspan="3">
	        				<div class="radio_wrap rdo_wrap1 code CPT" type="select" data="cptId"></div>
	        			</td>
<%--	        			<th>성취기준 <em class="point">*</em></th>--%>
<%--	        			<td>--%>
<%--	        				<div class="radio_wrap rdo_wrap1 code ACV" type="select" data="acvId"></div>--%>
<%--	        			</td>--%>
	        		</tr>
	        	</tbody>
	        </table>

	        <h3>내용입력</h3>
	        <!-- 콘텐츠타입이 이미지 일 때 -->
			<table class="tbl-st writeT mb">
	        	<colgroup>
	        		<col width="17%">
	        		<col width="*">
	        	</colgroup>
	        	<tbody>
	        		<tr>
	        			<th>콘텐츠타입 <em class="point">*</em></th>
	        			<td>
							<div class="radio_wrap rdo_wrap1 code CONTYPEALL" type="radio" data="notType"></div>

	        			</td>
	        		</tr>
	        		<tr class="CONTYPEALL I">
	        			<td colspan="2" class="image file" data="image">

	        			</td>
	        		</tr>

	        		<tr class="CONTYPEALL T">
	        			<td colspan="2">
	        				<div class="editor_div">
								<textarea class="form-control html-editor" name="notText" id="notText" style="height:200px;"></textarea>
							</div>
	        			</td>
	        		</tr>

	        		<tr class="CONTYPEALL P">
	        			<th>썸네일 <em class="point">*</em></th>
	        			<td class="fileMap">
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
	        		<tr class="CONTYPEALL P">
	        			<th>영상타이틀</th>
	        			<td>
	        				<input type="text" name="notPlayName" id="notPlayName" placeholder="미입력시 개념요소명으로 노출됩니다">
	        				<p class="point mt5">※ 공백포함 최대 00자</p>
	        			</td>
	        		</tr>
	        		<tr class="CONTYPEALL P">
	        			<th>영상경로</th>
	        			<td>
	        				<input type="text" name="notPlayPath" id="notPlayPath">
	        			</td>
	        		</tr>
	        		<tr class="CONTYPEALL P">
	        			<th>영상정보</th>
	        			<td>
							<div class="mb10">
								<span class="tit">런타임</span>
								<input type="text" name="notPlayRunTime" id="notPlayRunTime" class="ipt_m">
								<span class="point">※ hh:mm:ss 형식으로 입력</span>
		                    </div>
		                    <div>
		                    	<span class="tit">화질</span>
								<input type="text" name="notPlayRunRate" id="notPlayRunRate" class="ipt_m">
		                    </div>
		                </td>
	        		</tr>
	        	</tbody>
	        </table>

	         <table class="tbl-st writeT mb">
	        	<colgroup>
	        		<col width="17%">
	        		<col width="*">
	        	</colgroup>
	        	<tbody>
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

	        <div class="tbl-foot">
				<a href="javascript:;" class="btn btn-list btnList">목록</a>
				<a href="#" class="btn btn-offer btnSubmit">저장</a>
			</div>
			</form>
<script type="text/javascript">
var gridElement =null,gridColumn =[];
$(document).ready(function(){
    /* 코드 사용 */
    /* 코드 사용 */
    $.call('/ajax/codeList',{codes:'BI,USE_YN,ACV,LEV,ACA,UNIT,SUB,CPT,CONTYPEALL,USE_YN_ALL'},function(r){
        $.extend(plus.codes,r.codes);
    });
    $.each(plus.codes,function(k,v){
      $('.code.'+k).addCodeItem(v,true)
      $('.codex.'+k).addCodeItem(v,true)
    });
    $('.code.CONTYPEALL :radio').click(function(){
    	$('tr.CONTYPEALL').hide();
		$('tr.CONTYPEALL.'+$(this).attr('value')).show()
	})

	$('#notPlayRunTime').mask('00:00');

	/* 날짜값 세팅 */
	var sDate = new Date();
	var eDate = new Date();
	sDate.setMonth(sDate.getMonth()-1);

	$("#sdate").val(sDate.format('yyyy-MM-dd'));
	$("#edate").val(eDate.format('yyyy-MM-dd'));


    /* tab 생성후 초기이벤트*/
    plus.event.tabAfter=function(pageContentLast, rowData, mode){
        var rules = {
            subId:{required:true}
            ,acvId:{required:true}
            ,cptId:{required:true}
            ,notType:{required:true}
        };
        pageContentLast.data({rules:rules});
        var tableElement =pageContentLast.find('table');
        plus.event.formAfter(pageContentLast,rowData,mode);
        plus.event.bbsfile(rowData);

        $('.copy').hide();
        if(rowData['notId']!='0'){
        	$('.copy').css('display','table-row');
		}


    }

    //페이지 래디 정의
    plus.event.tabReady=function(){
    	$('.USE_YN_ALL :radio:eq(0)').click();
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
		// <th>개념코드</th>
		// 				<th>과목</th>
		// 				<th>성취기준</th>
		// 				<th>개념요소명</th>
		// 				<th>콘텐츠타입</th>
		// 				<th>내용</th>
		// 				<th>상태</th>
		// 				<th>등록/수정</th>
        // gridColumn.push({'data': 'umSeq', 'title': plus.event.checkAll, 'type': 'checkbox', hidden: false,render:plus.event.seqCheckBox});
        gridColumn.push({'data':'notKey','title':'개념코드'});
        gridColumn.push({'data':'subName','title':'과목명'});
        gridColumn.push({'data':'acvName','title':'성취기준명','class':'tl',render:plus.renderer.clickbox});

        gridColumn.push({'data':'cptName','title':'개념요소명','class':'tl',render:plus.renderer.clickbox});
        gridColumn.push({'data':'notType','title':'콘텐츠타입',code:plus.codes['CONTYPEALL'],render:plus.renderer.code});
        gridColumn.push({'data':'notText','title':'내용',render:plus.renderer.clickboxview});

        gridColumn.push({'data':'useYn','title':'상태',code:plus.codes['USE_YN'],render:plus.renderer.code});
        gridColumn.push({'data':'regDate','title':'등록/수정',render:plus.renderer.iddate});

        gridElement = plus.makeGrid('#gridElement',gridColumn,plus.makeAjax('/plusadmin/ajax/aigo/notionList',$('.srchT:first').domJson(),'resultList'),{attr:'속성'});



        /* tr클릭 이벤트*/
        $('#gridElement tbody ').on('click','.clickkbox',function () {
            var rowData =  gridElement.row( $(this).closest('tr') ).data();
            var info = gridElement.page.info();
            console.log(info);
            var tabTitle  = String.format('[{0}] {1}',rowData['abName'],'');
			$('#wrapList').hide();
			$('td.file').empty();
            //console.log(rowData);
            //plus.frontTab.addTab(tabTitle,rowData,$('#wrapEdit').tmpl({updateUrl:'/front/ajax/assets/buildingExcute',deleteUrl:'/front/ajax/assets/buildingDelete'}));

            rowData['start']=info['start']
            rowData['length']=info['length']
          console.log(rowData);
            plus.frontPage.show($('#wrapEdit'),rowData,'EDIT');

        });
    }


    /* tab페이지 초기에 실행할 이벤트*/
    plus.event.tabReady();

	$('.code.SUBACA').addCodeItem({},true);
	$('.code.ACA').change(function(){

		$.call('/plusadmin/ajax/aigo/categorySubList',{parentAcaId:$(this).val()},function(r) {
			if (r['resultList']) {
				var subData = {};
				$.each(r['resultList'], function(k, v) {
						subData[v['acaId']] = v['acaName'];

				});
				$('.code.SUBACA').addCodeItem(subData,true)
			}
		})
	})
    /* 등록 버튼*/
    $('.btnReg').click(function(){
    	$('#wrapList').hide();
      plus.frontPage.show($('#wrapEdit'), {notId:'0','start':0,length:0,useYn:'y',acaId:0,subAcaId:0,notType:'T',notText:'',bbDate:(new Date()).format('yyyy-MM-dd')},'NEW');
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
    $('.fileMap :file').change(function(){
      var fileOne = $(this).get(0).files[0];
      if(fileOne){
        $(this).closest('td').find('.upload-name').val(fileOne.name);
        $(this).closest('td').find('img').attr('src',URL.createObjectURL(fileOne));
      }
    });
    $('.btnSubmit').click(function(){
		var fileLists = [];
		  $(':file').each(function(){
			var data = $(this).data();
			if(data['safSeq']){
			  fileLists.push(data);
			}
		  });

    	var contypeall =   $('.code.CONTYPEALL :radio:checked').val();
    	if(contypeall=='T'){
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
		}
	  $(this).closest('form').find('input[name=fileLists]').val(JSON.stringify(fileLists));
      $(this).closest('form').submit();
      return false;
    });

    $('.btnSearch').click(function(){
        gridElement = plus.makeGrid('#gridElement',gridColumn,plus.makeAjax('/plusadmin/ajax/aigo/notionList',$('.srchT:first').domJson(),'resultList'),{attr:'속성'});
    });


});

</script>