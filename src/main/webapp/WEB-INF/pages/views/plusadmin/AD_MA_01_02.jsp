<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<h2>레벨 관리</h2>

			<p class="point mb5">※ 상태(공개/비공개) 변경 시 반드시 문항 상태를 확인하시기 바랍니다. </p>
			<table class="tbl-st" id="gridElement">
				<colgroup>
<%--					<col width="5%">--%>
					<col width="10%">
					<col width="10%">
					<col width="*">
					<col width="5%">
					<col width="25%">
				</colgroup>
				<thead>
					<tr>
<%--						<th></th>--%>
						<th>레벨코드</th>
						<th>레벨</th>
						<th>레벨명</th>
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
			<h1>레벨 등록/수정</h1>
		</div>
		<form id="wrapEdit" action="/plusadmin/ajax/aigo/levelExcute" method="post" enctype="multipart/form-data">
			<input type="hidden" name="levId" id="levId" value="" />
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
						<th>레벨코드</th>
						<td><b class="levKey">00002</b></td>
					</tr>
					<tr>
						<th>레벨</th>
						<td>
							<div class="radio_wrap rdo_wrap1 code LEV1-5" type="select" data="levOrder">
							</div>
						</td>
					</tr>
					<tr>
						<th>레벨명 <em class="point">*</em></th>
						<td><input type="text" value="" name="levName" id="levName"></td>
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
    setLev();

    /* tab 생성후 초기이벤트*/
    plus.event.tabAfter=function(pageContentLast, rowData, mode){
        var rules = {
            levOrder:{required:true,remote: {
				url: "/plusadmin/ajax/aigo/levelValueCheck",
				type: "post",
				data: {

				  levId: function() {
					return $( "#levId" ).val();
				  },
				  levValue: function() {
					return $( "#levValue" ).val();
				  },
				  useYn: function() {
					return $( ".code.USE_YN :radio:checked" ).val();
				  }
				}
			}},
        	levName:{required:true,remote: {
			url: "/plusadmin/ajax/aigo/levelNameCheck",
			type: "post",
			data: {
			  levName: function() {
				return $( "#levName" ).val();
			  },
			  levId: function() {
				return $( "#levId" ).val();
			  },
			  levValue: function() {
				return $( "#levValue" ).val();
			  },
			  useYn: function() {
				return $( ".code.USE_YN :radio:checked" ).val();
			  }
			}
		  }}
            ,useYn:{required:true}
        };
        console.log("룰스 >>>>> " , rules);
        pageContentLast.data({rules:rules});
        var tableElement =pageContentLast.find('table');
        plus.event.formAfter(pageContentLast,rowData,mode);
        plus.event.bbsfile(rowData);

//         setLev();
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
        // gridColumn.push({'data': 'umSeq', 'title': plus.event.checkAll, 'type': 'checkbox', hidden: false,render:plus.event.seqCheckBox});
        gridColumn.push({'data':'levKey','title':'레벨코드'});
        gridColumn.push({'data':'levValue','title':'레벨'});
        gridColumn.push({'data':'levName','title':'레벨명','class':'tl',render:plus.renderer.clickbox});
        gridColumn.push({'data':'useYn','title':'상태',code:plus.codes['USE_YN'],render:plus.renderer.code});
        gridColumn.push({'data':'regDate','title':'등록/수정',render:plus.renderer.iddate});
        gridElement = plus.makeGrid('#gridElement',gridColumn,plus.makeAjax('/plusadmin/ajax/aigo/levelList',{},'resultList'),{pageLength:10,attr:'속성'});


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
			
            console.log("rowData >>>> ",rowData);
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
      $('#wrapList').hide();
      plus.frontPage.popup($('#wrapEdit'), {levId:'0','start':0,length:0,levValue:1,levName:'',useYn:'y'},'NEW');
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
    
    function setLev() {
    	$.call('/ajax/codeList',{codes:'BI,USE_YN'},function(r){
            $.extend(plus.codes,r.codes);
        });
        plus.codes['LEV1-5'] = {'1':'1','2':'2','3':'3','4':'4','5':'5'};
        $.each(plus.codes,function(k,v){
          $('.code.'+k).addCodeItem(v)
        });
    }


});

</script>
