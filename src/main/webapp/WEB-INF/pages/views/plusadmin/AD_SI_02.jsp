<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<h2>배너/팝업관리</h2>
<link rel="stylesheet" href="/admassets/plugins/daterangepicker/daterangepicker.css">
<script type="text/javascript" src="/admassets/plugins/daterangepicker/daterangepicker.js"></script>
			<table class="srchT">
				<colgroup>
					<col width="18%">
					<col width="*">
				</colgroup>
				<tbody>
					<tr>
						<th rowspan="2">검색</th>
						<td class="bbx">
							<div class="radio_wrap rdo_wrap1 code USE_STEP" type="radio" data="useYn"></div>
						</td>
					</tr>
					<tr>
						<td class="ptx">
							<div class="srch_div">
								<select class="s_p1 code BAN_TARGET" type="select" name="banTarget">
								</select>
								<select class="s_p1 code BAN_DEVICE" name="searchType" id="searchType">
									<option value="bb.ban_name">배너명</option>
								</select>
								<input type="text" id="searchString" name="searchString" class="ipt_p2">
								<button type="submit" class="btn btn-st1 btnSearch">검색</button>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		<div id="wrapList">
			<table class="tbl-st mt30" id="gridElement">
				<colgroup>
					<col width="5%">
					<col width="7%">
					<col width="7%">
					<col width="9%">
					<col width="*">

					<col width="11%">
					<col width="10%">
					<col width="10%">
					<col width="7%">
					<col width="10%">
					<col width="7.5%">
					<col width="7.5%">
				</colgroup>
				<thead>
					<tr>
						<th>NO</th>
						<th>노출플랫폼</th>
						<th>위치</th>
						<th>배너명</th>
						<th>이미지</th>

						<th>랜딩링크</th>
						<th>등록일</th>
						<th>종료일</th>
						<th>상태</th>
						<th>등록/수정</th>
						<th>노출처리</th>
						<th>노출순서</th>
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
			<h1>배너등록 및 수정</h1>
		</div>
		<form id="wrapEdit" action="/plusadmin/ajax/aigo/bannerExcute" method="post" enctype="multipart/form-data">
			<input type="hidden" name="banId" id="banId" value="" />
			<input type="hidden" id="start" name="start" value="" />
			<input type="hidden" id="length" name="length" value="" />
			<input type="hidden" name="fileList" value="[]" />
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
						<th>플랫폼 <em class="point">*</em></th>
						<td>
							<select class="code BAN_DEVICE" type="select"  name="banDevice" id="banDevice" >
							</select>
						</td>
						<th>위치 <em class="point">*</em></th>
						<td>
							<select class="code BAN_TARGET" type="select" name="banTarget" id="banTarget" >
							</select>
						</td>
					</tr>
					<tr>
						<th>배너명 <em class="point">*</em></th>
						<td colspan="3"><input type="text" name="banName" id="banName" placeholder="친구추천 이벤트 배너 1차"></td>
					</tr>
					<tr>
						<th>이미지</th>
						<td colspan="3" class="fileMap">
							<div class="add_file_div inlineB">
								<div class="filebox">
									<label for="ex_filename1" class="btn btn-st1">파일찾기</label>
									<input class="upload-name"  value="파일명" disabled="disabled">
									<i class="fas fa-times fa-times2"></i>

									<input type="file" name="pcthumb" id="ex_filename1" class="upload-hidden">
								</div>
								<p class="txt_p">* 이미지 사이즈 000X000</p>
							</div>
							<div class="thumb_img"><img src="../images/tmp_thumb.jpg"></div>
						</td>
					</tr>
					<tr>
						<th>링크 URL</th>
						<td colspan="3">
							<input type="text" name="banUrl" id="banUrl" placeholder="/subaddress/주소주소" >
						</td>
					</tr>
					<tr>
						<th>등록기간</th>
						<td colspan="3">
							<div class="date_div">
								<span class="ipt_dates"><input type="date" class="ipt_date mr" name="banStartDate" id="banStartDate"></span>
							  	<input type="text" name="" class="ipt_time" value="00"><span>:</span><input type="text" name="" class="ipt_time" value="00">
							  	<span>~</span>
								<span class="ipt_dates"><input type="date" class="ipt_date mr" name="banEndDate" id="banEndDate"></span>
							  <input type="text" name="" class="ipt_time" value="00"><span>:</span><input type="text" name="" class="ipt_time" value="00">
							</div>
						</td>
					</tr>
					<tr>
						<th>노출순서</th>
						<td colspan="3">
							<input type="number" name="banOrder" id="banOrder" class="ipt_w" value="1">
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
    /* 코드 사용 */
    $.call('/ajax/codeList',{codes:'BI,USE_YN,USE_STEP,BAN_DEVICE,BAN_TARGET'},function(r){
        $.extend(plus.codes,r.codes);
    });
    $.each(plus.codes,function(k,v){
      $('.code.'+k).each(function(){
      	$(this).addCodeItem(v,true);
	  })
    });
    $('.USE_STEP :radio:eq(0)').prop('checked',true);

    $(':radio[name=useYn]').change(function(){
    	if($(':radio[name=useYn]:checked').val()=='n'){
			$(':radio[name=userActive]:eq(1)').prop('checked',true);
		}
	})

    /* tab 생성후 초기이벤트*/
    plus.event.tabAfter=function(pageContentLast, rowData, mode){
        var messages = {
			subName:{
				remote: "중복된 데이터가 있습니다."
			}
		}
        var rules = {
            subName:{required:true}
            ,banStartDate:{required:true}
            ,banEndDate:{required:true}
            ,banUrl:{required:true}
            ,banDevice:{required:true}
            ,banTarget:{required:true}
        };
        pageContentLast.data({rules:rules});
        pageContentLast.data({messages:rules});
        var tableElement =pageContentLast.find('table');
        plus.event.formAfter(pageContentLast,rowData,mode);
        plus.event.bbsfile(rowData);


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
        gridColumn.push({'data': 'umSeq', 'title': 'NO', 'type': 'checkbox', hidden: false,render:plus.renderer.rrownum});
        gridColumn.push({'data':'banDevice','title':'노출플랫폼','class':'tl',code:plus.codes['BAN_DEVICE'],render:plus.renderer.code});
        gridColumn.push({'data':'banTarget','title':'위치','class':'tl',code:plus.codes['BAN_TARGET'],render:plus.renderer.code});
        gridColumn.push({'data':'banName','title':'배너명','class':'tl',render:plus.renderer.clickbox});
        gridColumn.push({'data':'filemap.pcthumb','title':'이미지',render:plus.renderer.banner});
        gridColumn.push({'data':'banUrl','title':'랜딩링크',render:plus.renderer.clickbox});
        gridColumn.push({'data':'banStartDate','title':'등록일',render:plus.renderer.clickbox});
        gridColumn.push({'data':'banEndDate','title':'종료일',render:plus.renderer.clickbox});
        gridColumn.push({'data':'useYn','title':'상태',code:plus.codes['USE_YN'],render:plus.renderer.code});
        gridColumn.push({'data':'banOrder','title':'노출순서'});

        gridColumn.push({'data':'userActive','title':'사용자노출',code:plus.codes['USE_YN'],render:plus.renderer.code});
        gridColumn.push({'data':'regDate','title':'등록/수정',render:plus.renderer.iddate});

        //NO	노출플랫폼	위치	배너명	이미지	랜딩링크	등록일	종료일	상태	등록/수정	노출처리	노출순서
        gridElement = plus.makeGrid('#gridElement',gridColumn,plus.makeAjax('/plusadmin/ajax/aigo/bannerList',{},'resultList'),{pageLength:10,attr:'속성'});


        /* tr클릭 이벤트*/
        $('#gridElement tbody ').on('click','.clickkbox',function () {
            var rowData =  gridElement.row( $(this).closest('tr') ).data();
            var info = gridElement.page.info();
            console.log(info);
            var tabTitle  = String.format('[{0}] {1}',rowData['abName'],'');

            //console.log(rowData);
            //plus.frontTab.addTab(tabTitle,rowData,$('#wrapEdit').tmpl({updateUrl:'/front/ajax/assets/buildingExcute',deleteUrl:'/front/ajax/assets/buildingDelete'}));
            // $('#wrapList').hide();
            rowData['start']=info['start']
            rowData['length']=info['length']

            plus.frontPage.popup($('#wrapEdit'),rowData,'EDIT');

        });
        $(':radio[name=useYn]').change(function(){
		if($(':radio[name=useYn]:checked').val()=='n'){
		 alert('해당 단원으로 등록된 문항 모두 비공개됩니다.')
		}
		})
        }


    /* tab페이지 초기에 실행할 이벤트*/
    plus.event.tabReady();
    /* 등록 버튼*/
    $('.btnReg').click(function(){
      //$('#wrapList').hide();

      plus.frontPage.popup($('#wrapEdit'), {banId:'0','start':0,length:0,banName:'',useYn:'y',userActive:'y'},'NEW');

    });

    $('.btnSearch').click(function(){
        //gridElement.ajax.reload();

		gridElement = plus.makeGrid('#gridElement',gridColumn,plus.makeAjax('/plusadmin/ajax/aigo/bannerList',$('.srchT').domJson(),'resultList'),{pageLength:10,attr:'속성'});
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
