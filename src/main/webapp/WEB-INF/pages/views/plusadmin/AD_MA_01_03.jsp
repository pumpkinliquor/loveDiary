<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<h2>문제분류 관리</h2>

			<p class="point mb5">※ 상태(공개/비공개) 변경 시 반드시 문항 상태를 확인하시기 바랍니다. </p>
			<table class="tbl-st tbl-border display" id="gridElement">
				<colgroup>
					<col width="7%">
					<col width="12%">
					<col width="12%">
<%--					<col width="12%">--%>
<%--					<col width="12%">--%>
					<col width="12%">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th rowspan="2" class="blx">순번</th>
						<th colspan="2">1차 코드</th>
						<th colspan="2">2차 코드</th>
						<th rowspan="2">상태</th>
						<th rowspan="2">등록/수정</th>
					</tr>
					<tr class="br">
						<th>코드</th>
						<th>분류명</th>
						<th>2차코드</th>
						<th>분류명</th>
					</tr>
				</thead>
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
<div class="modal-wrap pop03 ">
	<div class="modal">
		<div class="modal-header">
			<h1>문제분류 등록/수정</h1>
		</div>
		<form id="wrapEdit" action="/plusadmin/ajax/aigo/categoryExcute" method="post" enctype="multipart/form-data">
			<input type="hidden" name="acaId" id="acaId" value="" />
			<input type="hidden" name="dataJson" id="dataJson" value="" />
			<input type="hidden" id="start" name="start" value="" />
			<input type="hidden" id="length" name="length" value="" />
			<div class="content">
				<table class="popT">
					<colgroup>
						<col width="22%">
						<col width="*">
					</colgroup>
					<tbody>
					<tr class="EDIT hidden">
						<th>1차코드</th>
						<td><b class="acaKey">AA002</b></td>
					</tr>
					<tr>
						<th>분류명 <em class="point">*</em></th>
						<td><input type="text" value="기출문제" name="acaName" id="acaName"></td>
					</tr>
					<tr>
						<th>2차분류</th>
						<td class="dataSubList">
							<a href="#" class="btn btn-secondary btnAdd">추가</a>
<%--							<!-- 추가 버튼 클릭 시 -->--%>
<%--							<div class="td_wrap">--%>
<%--								<label for="">AA002S01</label><input type="text" name="">--%>
<%--								<div class="radio_wrap rdo_wrap1">--%>
<%--									<div class="ipt_radio_div">--%>
<%--										<input type="radio" id="state0" name="stateA">--%>
<%--										<label for="state0">비공개</label>--%>
<%--									</div>--%>
<%--									<div class="ipt_radio_div">--%>
<%--										<input type="radio" id="state1" name="stateA" checked="checked">--%>
<%--										<label for="state1">공개</label>--%>
<%--									</div>--%>
<%--								</div>--%>
<%--							</div>--%>
<%--							<div class="td_wrap">--%>
<%--								<label for="">AA002S01</label><input type="text" name="">--%>
<%--								<div class="radio_wrap rdo_wrap1">--%>
<%--									<div class="ipt_radio_div">--%>
<%--										<input type="radio" id="state3" name="stateB">--%>
<%--										<label for="state3">비공개</label>--%>
<%--									</div>--%>
<%--									<div class="ipt_radio_div">--%>
<%--										<input type="radio" id="state4" name="stateB" checked="checked">--%>
<%--										<label for="state4">공개</label>--%>
<%--									</div>--%>
<%--								</div>--%>
<%--							</div>--%>
							<!-- //추가 버튼 클릭 시 -->
						</td>
					</tr>

					<tr>
						<th>상태</th>
						<td>
							<div class="radio_wrap rdo_wrap1 code USE_YN" type="radio" data="useYn">
							</div>
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
    $.call('/ajax/codeList',{codes:'BI,USE_YN'},function(r){
        $.extend(plus.codes,r.codes);
    });
    $.each(plus.codes,function(k,v){
      $('.code.'+k).addCodeItem(v)
    });

    $(':radio[name=useYn]').change(function(){
		if($(':radio[name=useYn]:checked').val()=='n'){
 			alert('해당 분류로 등록된 문항 모두 비공개됩니다.')
			$('.td_wrap').each(function(){
				$(this).find('.USE_YN :radio:last').prop('checked',true);
			})
		}
	})

    /* tab 생성후 초기이벤트*/
    plus.event.tabAfter=function(pageContentLast, rowData, mode){
        var rules = {
            acaName:{required:true,remote: {
			url: "/plusadmin/ajax/aigo/categoryCheck",
			type: "post",
			  data: {
			  acaName: function() {
				return $( "#acaName" ).val();
			  },
			  acaId: function() {
				return $( "#acaId" ).val();
			  }
			  ,subData: function() {
			  	var arr = [];
			  	$('.td_wrap').each(function(){
			  		arr.push({'subAcaId':$(this).find('.sub_aca_id').val()||'0','subAcaName':$(this).find('.sub_aca_name').val()});
				});

			  	return JSON.stringify(arr);
			  }
			}
		  }}
            ,useYn:{required:true}
        };
        pageContentLast.data({rules:rules});
        var tableElement =pageContentLast.find('table');
        plus.event.formAfter(pageContentLast,rowData,mode);
        plus.event.bbsfile(rowData);


    }
    // <div class="td_wrap">
	// 							<label for="">AA002S01</label><input type="text" name="">
	// 							<div class="radio_wrap rdo_wrap1">
	// 								<div class="ipt_radio_div">
	// 									<input type="radio" id="state0" name="stateA">
	// 									<label for="state0">비공개</label>
	// 								</div>
	// 								<div class="ipt_radio_div">
	// 									<input type="radio" id="state1" name="stateA" checked="checked">
	// 									<label for="state1">공개</label>
	// 								</div>
	// 							</div>
	// 						</div>
    $('.btnAdd').click(function(){
    	var td =$(this).closest('td');
    	var td_wrap =  plus.makeElement('div','',{'class':'td_wrap'});
		var addId = plus.getId();
    	var label = plus.makeElement('label','',{'for':addId}).appendTo(td_wrap);
    	var input = plus.makeElement('input','',{'id':addId,'type':'text','class':'sub_aca_name'}).appendTo(td_wrap);
    	var radio_wrap = plus.makeElement('div','',{'class':'radio_wrap rdo_wrap1 code USE_YN','type':'radio',data:'name'+addId}).appendTo(td_wrap);
    	radio_wrap.addCodeItem(plus.codes['USE_YN']);
    	radio_wrap.find(':radio:eq(0)').prop('checked',true);
    	td_wrap.appendTo(td);
	})

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
        gridColumn.push({'data': 'umSeq', 'title': '순번', 'type': 'rownum', hidden: false,render:plus.renderer.rrownum});
        gridColumn.push({'data':'acaKey','title':'1차 코드'});
        gridColumn.push({'data':'acaName','title':'1차 코드명','class':'tl',render:plus.renderer.clickbox});
        gridColumn.push({'data':'subAcaKey','title':'2차 코드',render:plus.renderer.clickbox});
        gridColumn.push({'data':'subAcaName','title':'2차 코드명','class':'tl',render:plus.renderer.clickbox});
        gridColumn.push({'data':'useYn','title':'상태',code:plus.codes['USE_YN'],render:plus.renderer.code});
        gridColumn.push({'data':'regDate','title':'등록/수정',render:plus.renderer.iddate});
        gridElement = plus.makeGrid('#gridElement',gridColumn,plus.makeAjax('/plusadmin/ajax/aigo/categoryList',{},'resultList'),{attr:'속성'});


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


            plus.frontPage.popup($('#wrapEdit'),rowData,'EDIT');
            $('.dataSubList').find('.td_wrap').remove();
            $.call('/plusadmin/ajax/aigo/categorySubList',{parentAcaId:rowData['acaId']},function(r){
				if(r['resultList']){

					$.each(r['resultList'],function(k,v){

						var td_wrap =  plus.makeElement('div','',{'class':'td_wrap'});
						var addId = plus.getId(k);
						var label = plus.makeElement('label','',{'for':addId}).appendTo(td_wrap);

						var input = plus.makeElement('input','',{'id':addId,'type':'hidden','class':'sub_aca_id'}).appendTo(td_wrap);
						input.val(v['acaId']);
						var input = plus.makeElement('input','',{'id':addId,'type':'text','class':'sub_aca_name'}).appendTo(td_wrap);
						input.val(v['acaName']);
						var radio_wrap = plus.makeElement('div','',{'class':'radio_wrap rdo_wrap1 code USE_YN','type':'radio',data:'name'+addId}).appendTo(td_wrap);
						radio_wrap.addCodeItem(plus.codes['USE_YN']);
						radio_wrap.find(':radio[value='+(v['useYn'])+']').prop('checked',true);
						td_wrap.appendTo($('.dataSubList'));

						$(':radio[name='+('name'+addId)+']',td_wrap).change(function(){
							if($(':radio[name='+('name'+addId)+']:checked',td_wrap).val()=='n'){
								alert('해당 분류로 등록된 문항 모두 비공개됩니다.')
							}
						})
					});
				}
			});

        });
    }


    /* tab페이지 초기에 실행할 이벤트*/
    plus.event.tabReady();
    /* 등록 버튼*/
    $('.btnReg').click(function(){
      $('#wrapList').hide();
      plus.frontPage.popup($('#wrapEdit'), {acaId:'0','start':0,length:0,acaName:'',useYn:'y'},'NEW');
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
		var datas = [];
		var td = $('.dataSubList');
    	if(td.find('.td_wrap').length>0){
    		td.find('.td_wrap').each(function(i){
    			datas.push({'parentAcaId':parseInt($('#acaId').val()),'acaName':$(this).find('.sub_aca_name').val(),'acaId':parseInt($(this).find('.sub_aca_id').val()),useYn:$(this).find(':radio:checked').val()});
			})
		}
    	$('#dataJson').val(JSON.stringify(datas));
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
