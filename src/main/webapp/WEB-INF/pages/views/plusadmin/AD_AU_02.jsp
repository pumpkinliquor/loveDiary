<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<h2>권한설정</h2>
		<div id="wrapList">
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
		</div>
<div id="wrapEdit" class="hidden" action="/plusadmin/ajax/aigo/userGrantExcute" method="post" enctype="multipart/form-data">
	<table class="tbl-st mb">
				<colgroup>
					<col width="12%">
					<col width="*">
					<col width="12%">
					<col width="*">
				</colgroup>
				<tbody>
					<tr>
						<th>아이디</th>
						<td class="td_left"><input type="text" value="" name="umId" id="umId" class="ipt_30" readonly="readonly"></td>
						<th>이름</th>
						<td class="td_left"><input type="text" value="" name="umName" id="umName" class="ipt_30" readonly="readonly"></td>
					</tr>
				</tbody>
			</table>

			<table class="tbl-st tbl-border mb" id="gridElementGrant">
				<colgroup>
					<col width="12%">
					<col width="13%">
					<col width="15%">
					<col width="20%">
					<col width="20%">
				</colgroup>
				<thead>
					<tr>
						<th colspan="5" class="bg1 blx">권한설정</th>
					</tr>
					<tr>
						<th class="blx">대메뉴</th>
						<th>중메뉴</th>
						<th>소메뉴</th>
						<th>상태</th>
						<th>읽기/쓰기</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>

			<table class="tbl-st">
				<colgroup>
					<col width="12%">
					<col width="*">
				</colgroup>
				<tbody>
					<tr class="EDIT hidden">
						<th>등록/수정</th>
						<td colspan="3" class="show_iddate">
							이지현(admin)  yyyy-mm-dd   hh:mm:ss
						</td>
					</tr>
				</tbody>
			</table>
			<div class="tbl-foot">
				<a href="javascript:;" class="btn btn-list btnList">목록</a>
				<div class="paging">
				</div>
				<a href="javascript:;" class="btn btn-offer btnSubmit">저장</a>
			</div>
</div>
<script type="text/javascript">
var gridElement =null,gridElementGrant = null,gridColumn =[];
$(document).ready(function(){
    /* 코드 사용 */
    /* 코드 사용 */
    $.call('/ajax/codeList',{codes:'BI,USE_YN,ACV,SUB,CONNECT,USE_YN_ALL'},function(r){
        $.extend(plus.codes,r.codes);
    });
    $.each(plus.codes,function(k,v){
      $('.code.'+k).addCodeItem(v,true)
      $('.codex.'+k).addCodeItem(v,true)
    });
    /* tab 생성후 초기이벤트*/
    plus.event.tabAfter=function(pageContentLast, rowData, mode){
        var rules = {

        };
        pageContentLast.find('form').data({rules:rules});
        var tableElement =pageContentLast.find('table');
        plus.event.formAfter(pageContentLast,rowData,mode);


    }
		plus.ready
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
        plus.event.gridComplet=function(r){
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
        // <th>이름</th>
        // <th>아이디</th>
        // <th>사업부</th>
        // <th>팀</th>
        // <th>외부접속</th>
        // <th>상태</th>
        // <th>등록/수정</th>
        //gridColumn.push({'data': 'umSeq', 'title': plus.event.checkAll, 'type': 'checkbox', hidden: false,render:plus.event.seqCheckBox});
        gridColumn.push({'data': 'umSeq', 'title': '순서', 'type': 'checkbox', hidden: false,render:plus.renderer.rownum});
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
            ,searchType:function(){return $('#searchType').val()}},'resultList'),{attr:'속성'});



        /* tr클릭 이벤트*/
        $('#gridElement tbody ').on('click','.clickkbox',function () {
            var rowData =  gridElement.row( $(this).closest('tr') ).data();
            var info = gridElement.page.info();
            console.log(info);

            $('#umId').prop('readonly',true);
            $('#wrapList').hide();
            // rowData['start']=info['start']
            // rowData['length']=info['length']

			var gridColumn =[];


        gridColumn.push({'data':'maModule','title':'대메뉴',render:function(val,f,row){if(row['maLevel']==1){return row['maName'];}return '';}});
        gridColumn.push({'data':'maName','title':'중메뉴',render:function(val,f,row){if(row['maLevel']==2){return row['maName'];}return '';}});
        gridColumn.push({'data':'maName2','title':'소메뉴','type':'',render:function(val,f,row){if(row['maLevel']==3){return row['maName'];}return '';}});
        // gridColumn.push({'data':'maLevel','title':'메뉴레벨','type':'','hidden':false});
        // gridColumn.push({'data':'maPath','title':'메뉴경로','type':'','hidden':true});
        // gridColumn.push({'data':'maSort','title':'정렬','type':'','hidden':true});
        gridColumn.push({'data':'useYn','title':'상태',render:function(d, t, r ){return $('<div class="radio_wrap rdo_wrap1 useYn" type="radio" data="useYn'+(r['maCode'])+'"></div>').addCodeItem(plus.codes['USE_YN']).prop("outerHTML")}});

        gridColumn.push({'data':'mgGrant','title':'읽기/쓰기',render:function(d, t, r){return $('<div class="radio_wrap rdo_wrap1 mgGrant" type="radio" data="mgGrant'+(r['maCode'])+'"></div>').addCodeItem({'R':'읽기','A':'쓰기'}).prop("outerHTML")}});
        // gridColumn.push({'data':'maIcon','title':'메뉴아이콘','type':'','hidden':true});
        // gridColumn.push({'data':'regDate','title':'등록일','type':'','hidden':true});
        gridElementGrant = plus.makeGrid('#gridElementGrant',gridColumn,plus.makeAjax('/totaladmin/ajax/admmenus/menuGrant',{umSeq:rowData['umSeq']},'resultList'),{bPaginate:false,pageLength:100,attr:'속성',drawCallback:function(settings, json){

                console.log('eeeeeeeeeee',settings['json']['resultList'], json);
                var tbody  = $('#gridElementGrant tbody');
                $.each(settings['json']['resultList'],function(k,v){
                	var useYn =$.trim(v['useYn'])||'y';
                	var mgGrant =$.trim(v['mgGrant'])||'A';
                	console.log(useYn)
					tbody.find('tr').eq(k).find('td:eq(3)').find(':radio[value='+(useYn)+']').prop('checked',true);
					tbody.find('tr').eq(k).find('td:eq(4)').find(':radio[value='+(mgGrant)+']').prop('checked',true);
				});
            }});

			/*
			* <th class="blx">대메뉴</th>
						<th>중메뉴</th>
						<th>소메뉴</th>
						<th>상태</th>
						<th>읽기/쓰기</th>
			* */
            plus.frontPage.show($('#wrapEdit'),rowData,'EDIT');

        });
    }

    $('.btnSubmit').click(function(){
        var trElement = $('#gridElementGrant tbody tr');
        console.log(trElement.length);
        var putData = [];
        var ugName = '';
        $.each(gridElementGrant.data(),function(i,v){
             var rowData =  gridElementGrant.row( trElement.eq(i) ).data();
             if(!rowData['umSeq'] || rowData['umSeq']=='0') return true;
             putData.push({maSeq:rowData['maSeq'],'umSeq':rowData['umSeq'],useYn:trElement.eq(i).find('.useYn :checked').val(),mgGrant:trElement.eq(i).find('.mgGrant :checked').val()});
             ugName = rowData['ugName'];

        });
        if(putData.length==0){
            alert('그룹을 선핵해주세요');
            return false;
        }
        if(confirm('메뉴권한을 등록하시겠습니까?')){
            $.call('/totaladmin/ajax/admmenus/menuGrantBatch',{'putData':JSON.stringify(putData)},function(r){
				Swal.fire(
				  '권한설정 완료되었습니다.',
				  '',
				  'success'
				)
				$('.btnList').click();
            });
        }
    });


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

      plus.frontPage.popup($('#wrapEdit'), $.extend(json,{bbSeq:'0','start':0,length:0,umStep:'OK',umType:'ADMIN',bbDate:(new Date()).format('yyyy-MM-dd')}),'NEW');
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