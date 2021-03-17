<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<h2>회원정보</h2>
<table class="srchT srch_id">
	<colgroup>
		<col width="20%">
		<col width="*">
	</colgroup>
	<tbody>
		<tr>
			<th rowspan="2">회원계정</th>
			<td>
				<form id="form" name="form" method="post" onsubmit="return false;">
					<div class="srch_div">
						<input type="text" name="searchString" id="searchString" class="ipt1" value="">
					</div>
						<button type="button" class="btn btn-st1 btnSearch" style="width: 100px; vertical-align: top;">검색</button>
						<p class="point mb5"> * 계정 또는 닉네임을 검색하세요.<br></p>
				</form>
			</td>
		</tr>
	</tbody>
</table>
<p class="point mb5">
	* 아이디 클릭 시 상세 정보를 조회할 수 있습니다.  <br>
	* 최종로그인일시 클릭 시 접속이력 조회로 이동합니다. <br>
	* 구매이력 클릭 시 회원의 수강,구매 상세 정보를 조회할 수 있습니다.
</p>
<table class="tbl-st2" id="gridElement">
	<colgroup>
		<col width="4%">
		<col width="10%">
		<col width="10%">
		<col width="10%">
		<col width="10%">
		<col width="5%">
		<col width="5%">
		<col width="8%">
		<col width="*">
		<col width="5%">
		<col width="5%">
		<col width="10%">
		<col width="6%">
	</colgroup>
	<thead>
		<tr>
			<th>번호</th>
			<th>계정</th>
			<th>닉네임</th>
			<th>가입구분</th>
			<th>최초설치일자</th>
			<th>설치OS</th>
			<th>앱버전</th>
			<th>가입일자</th>
			<th>최종 로그인일시</th>
			<th>학년</th>
			<th>레벨</th>
			<th>마케팅 활용동의</th>
			<th>탈퇴여부</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>

<!-- 회원정보 POPUP -->
<div class="modal-wrap pop01 memberInfoPopup">
	<form id="userinfoForm" name="userinfoForm" method="post">
		<input type="hidden" name="memId" id="memId" value="" />
		
		<div class="modal">
			<div class="modal-header">
				<h1>회원정보</h1>
			</div>
			<div class="content">
			<div class="tab_menu tab3">
				<a href="#" class="tab tab_1 active">기본정보</a>
				<a href="#" class="tab tab_2">모의진단</a>
				<a href="#" class="tab tab_3">풀이이력</a>
			</div>
			<div class="tab_conts_wrap">
				<div class="tab_cont1 tab_cont active">
					<h2>기본정보</h2>
					<table class="popT2 mb">
					<colgroup>
						<col width="20%">
						<col width="*">
						<col width="20%">
						<col width="*">
					</colgroup>
					<tbody>
						<tr class="EDIT">
							<th>아이디</th>
							<td class="memUserid"></td>
							<th>가입구분</th>
							<td class="memJoinChannel"></td>
						</tr>
						<tr class="EDIT">
							<th>닉네임</th>
							<td><span class="memNickname"></span> <a href="#" class="link btnNickHistory">이력</a></td>
							<th>레벨</th>
							<td><a href="#" class="link memLevel btnLevelHistory"></a></td>
						</tr>
						<tr class="EDIT">
							<th>설치일자</th>
							<td class="memRegisterDatetime"></td>
							<th>가입일자</th>
							<td class="memRegisterDatetime"></td>
						</tr>
						<tr class="EDIT">
							<th>최종로그인</th>
							<td class="memLastloginDatetime"></td>
							<th>학년</th>
							<td class="memClass"></td>
						</tr>
						<tr class="EDIT">
							<th>설치 OS</th>
							<td>Aos8(추후 작업예정)</td>
							<th>앱버전</th>
							<td>1.0.1(추후 작업예정)</td>
						</tr>
						<tr class="EDIT">
							<th>마케팅 활용동의</th>
							<td colspan="3"><b class="mtaMarketing"></b> <span class="mtaRegisterDatatime"></span></td>
						</tr>
						<tr class="EDIT">
							<th>푸시 설정</th>
							<td colspan="3"><a href="#" class="link btnUserPushInfo">설정 &gt;</a></td>
						</tr>
					</tbody>
				</table>
				<h2>결제정보</h2>
				<div>결제 내역(추후)</div>
			</div>
			<div class="tab_cont2 tab_cont">
				<h2>모의진단</h2>
				<table class="popT2 mb">
					<colgroup>
						<col width="20%">
						<col width="*">
						<col width="20%">
						<col width="*">
					</colgroup>
					<tbody>
						<tr class="EDIT">
							<th>아이디</th>
							<td class="memUserid"></td>
							<th>가입구분</th>
							<td class="memJoinChannel"></td>
						</tr>
						<tr class="EDIT">
							<th>닉네임</th>
							<td><span class="memNickname"></span> <a href="#" class="link btnNickHistory">이력</a></td>
							<th>레벨</th>
							<td><a href="#" class="link memLevel btnLevelHistory"></a></td>
						</tr>
						<tr class="EDIT">
							<th>모의진단 시작일시</th>
							<td>yyyy-mm-dd 00:00:00</td>
							<th>모의진단 종료일시</th>
							<td>yyyy-mm-dd 00:00:00</td>
						</tr>
						<tr class="EDIT">
							<th>진단결과</th>
							<td colspan="3">풀이문제: 10개, 정답문제 8개<br>백분위 71%, 예상등급 6등급<br>레벨 1</td>
						</tr>
					</tbody>
				</table>
				<table class="popT1">
					<colgroup>
						<col width="8%">
						<col width="11%">
						<col width="11%">
						<col width="*">
						<col width="*">
						<col width="13%">
						<col width="10%">
					</colgroup>
					<thead>
						<tr>
							<th>화면표시 문항번호</th>
							<th>문항코드</th>
							<th>채점코드</th>
							<th>풀이시작일시</th>
							<th>풀이종료일시</th>
							<th>풀이소요시간</th>
							<th>채점결과</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td>Q001</td>
							<td>test001</td>
							<td>yyyy-mm-dd 00:00:00</td>
							<td>yyyy-mm-dd 00:00:00</td>
							<td>0:00:00</td>
							<td>O</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="tab_cont3 tab_cont">
				<table class="popT2 mb">
					<colgroup>
						<col width="18%">
						<col width="*">
					</colgroup>
					<tbody>
						<tr>
							<th>기간</th>
							<td>
								<form>
									<div class="date_div">
										<input type="date" class="ipt_date" id=""><span>~</span>
										<input type="date" class="ipt_date" id="">
									</div>
									<button type="button" class="btn btn-default btn_srch">검색</button>
								</form>
							</td>
						</tr>
					</tbody>
				</table>
					<div class="report_link_div">
						<a href="#">주간리포트 &gt;</a>
						<a href="#">레벨평가리포트 &gt;</a>
						<a href="#">종합리포트 &gt;</a>
					</div>
					<table class="popT1">
						<colgroup>
							<col width="5%">
							<col width="10%">
							<col width="10%">
							<col width="10%">
							<col width="10%">
							<col width="10%">
							<col width="12%">
							<col width="12%">
							<col width="10%">
							<col width="10%">
						</colgroup>
						<thead>
							<tr>
								<th>번호</th>
								<th>문항코드</th>
								<th>채점코드</th>
								<th>성취기준<br>코드</th>
								<th>문제구분</th>
								<th>풀이시작<br>일시</th>
								<th>풀이종료<br>일시</th>
								<th>풀이소요<br>시간</th>
								<th>채점결과</th>
								<th>화면표시<br>문항번호</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>9</td>
								<td>Q001</td>
								<td>test001</td>
								<td></td>
								<td></td>
								<td>yyyy-mm-dd 00:00:00</td>
								<td>yyyy-mm-dd 00:00:00</td>
								<td>0:00:00</td>
								<td>O</td>
								<td></td>
							</tr>
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
					</div>
				</div>
			</div><!-- //tab_conts_wrap -->
		</div>
		<a href="#" class="close-pop close-btn">&#10005;</a>
		</div>
	</form>
</div>

<!-- 닉네임 이력 팝업 -->
<div class="modal-wrap pop02 nickHistoryPopup">
	<div class="modal">
		<div class="modal-header">
			<h1>닉네임 변경이력</h1>
		</div>
		<div class="content">
			<table class="tbl-st2" id="gridElementNickPopup">
				<colgroup>
					<col width="50%">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th>변경일자</th>
						<th>닉네임</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<a href="#" class="close-pop close-btn">&#10005;</a>
	</div>
</div>

<!-- 레벨 이력 팝업 -->
<div class="modal-wrap pop03 levelHistoryPopup">
	<div class="modal">
		<div class="modal-header">
			<h1>레벨 상세보기</h1>
		</div>
		<div class="content">
			<table class="tbl-st2" id="gridElementLevelPopup">
				<colgroup>
					<col width="35%">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th>레벨</th>
						<th>레벨 달성일자</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<a href="#" class="close-pop close-btn">&#10005;</a>
	</div>
</div>

<!-- 푸시 수신동의 팝업 -->
<div class="modal-wrap pop03 userPushInfoPopup">
	<div class="modal">
		<div class="modal-header">
			<h1>푸시 설정</h1>
		</div>
		<div class="content">
			<table class="tbl-st2" id="gridElementLevelPopup">
				<colgroup>
					<col width="50%">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th>구분</th>
						<th>수신여부</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>마케팅 알림 받기</td>
						<td>Y, 2020.01.08 13:55:24</td>
					</tr>
					<tr>
						<td>학습 알림 받기</td>
						<td>N</td>
					</tr>
					<tr>
						<td>야간(21시~08시) 알림 받기</td>
						<td>Y, 2020.01.08 13:55:24</td>
					</tr>
				</tbody>
			</table>
		</div>
		<a href="#" class="close-pop close-btn">&#10005;</a>
	</div>
</div>

<script>
	
var gridElement = null, gridColumn = [], popupGridElement = null, popupGridColumn = [];

$(document).ready(function(){

	/* 코드 사용 */
	$.call('/ajax/codeList',{codes:'CH_TYPE,MEM_STATUS,AGREE_YN'},function(r){
		$.extend(plus.codes,r.codes);
	});
	$.each(plus.codes,function(k,v){
		$('.code.'+k).addCodeItem(v);
	});
	
	// popup Tab
	tabMenu();
	
	/* tab 생성후 초기이벤트*/
	plus.event.tabAfter=function(pageContentLast, rowData, mode){
		// 유효성검사 할 항목 정의
		var rules = {
			subName:{required:true}
			, useYn:{required:true}
		};
		pageContentLast.data({rules:rules});
		var tableElement =pageContentLast.find('table');
		plus.event.formAfter(pageContentLast,rowData,mode);
		plus.event.bbsfile(rowData);
	};
	
	// 페이지 초기 데이터 로드
	plus.event.tabReady = function(){
		plus.evnet.submitAfter=function(){
			$('.close-btn').click();
			var info = gridElement.page.info();
			gridElement.ajax.reload(null,false);
		}
		/* 그리드 완료된 후 호출 */
		plus.event.gridComplet=function(){
		}
		//전체선택 체크박스 클릭
		/*
		plus.event.seqCheckBox = function(d, t, r){
			var div = plus.makeElement('div','',{'class':'custom-control custom-checkbox'});
			var id = plus.getId(r.bbSeq);
			plus.makeInput('checkbox','',{'class':"custom-control-input biSeq",id:"bbSeq"+(id),value:r.bbSeq}).appendTo(div);
			plus.makeElement('label','',{'class':'custom-control-label',for:'bbSeq'+(id)}).appendTo(div);
			return div.prop('outerHTML');
		}
		*/
		
		plus.event.clickbox = function(d, t, r){
			var div = plus.makeElement('a',d,{'href':'javascript:;','class':'custom-control custom-checkbox'});
			return div.prop('outerHTML');
		}
		
		gridColumn.push({'data':'memId',					'title':'번호'});
		gridColumn.push({'data':'memUserid',				'title':'계정',				render:plus.renderer.clickbox });
		gridColumn.push({'data':'memNickname',				'title':'닉네임',			render:plus.renderer.clickbox,		'class':'tl'});
		gridColumn.push({'data':'memJoinChannel',			'title':'가입구분',			render:plus.renderer.code,			code:plus.codes['CH_TYPE']});
		gridColumn.push({'data': function(){ return "-"; },	'title':'최초설치일자'});		// 수정해야함
		gridColumn.push({'data': function(){ return "-"; },	'title':'설치OS'});			// 수정해야함
		gridColumn.push({'data': function(){ return "-"; },	'title':'앱버전'});			// 수정해야함
		gridColumn.push({'data':'memRegisterDatetime',		'title':'가입일자',			render:plus.renderer.datetime});
		gridColumn.push({'data':'memLastloginDatetime',		'title':'최종 로그인일시',	render:plus.renderer.datetime});
		gridColumn.push({'data':'memClass',					'title':'학년'});
		gridColumn.push({'data':'memGrade',					'title':'레벨'});
		gridColumn.push({'data':'mtaMarketing',				'title':'마케팅 활용동의'});
		// gridColumn.push({'data':'mtaRegisterDatatime',	'title':'마케팅 활용동의일시',	render:plus.renderer.datetime });
		gridColumn.push({'data':'memStatus',				'title':'탈퇴여부',			render:plus.renderer.code,		code:plus.codes['MEM_STATUS']});
		gridElement = plus.makeGrid('#gridElement', gridColumn, plus.makeAjax('/plusadmin/ajax/member/memberList', {}, 'resultList'), {pageLength : 10, attr : '속성'});
		
	}
	
	/* tab페이지 초기에 실행할 이벤트*/
	plus.event.tabReady();
	
	// 아이디 검색
	$('.btnSearch').on('click', function(){
		var param = {
			searchString : $("input[name='searchString']").val()
		};
		gridElement = plus.makeGrid('#gridElement', gridColumn, plus.makeAjax('/plusadmin/ajax/member/memberList', param, 'resultList'), {pageLength : 10, attr : '속성'});
	});
	$('#searchString').keypress(function(e){
        if(e.keyCode==13){
            $('.btnSearch').click();
        }
    });
	
	// 회원정보팝업
	$('#gridElement tbody').on('click','.clickkbox',function () {
	
		// 회원정보 탭 (공통필드 포함)
		$('.tab_menu .tab_1').trigger('click');
		var rowData = gridElement.row( $(this).closest('tr') ).data();
		var info = gridElement.page.info();
		rowData['memRegisterDatetime'] = plus.renderer.datetime(rowData['memRegisterDatetime'],'','','');
		rowData['memLastloginDatetime'] = plus.renderer.datetime(rowData['memLastloginDatetime'],'','','');
		rowData['mtaRegisterDatatime'] = plus.renderer.datetime(rowData['mtaRegisterDatatime'],'','','');
		$('#userinfoForm').find('#memId').val(rowData['memId']);
		var codeKey = rowData['memJoinChannel'];
		rowData['memJoinChannel'] = plus.codes.CH_TYPE[codeKey];
		// rowData[''] = plus.renderer.datetime(rowData[''],'','','');			// 앱 설치일
		plus.frontPage.popup($('.memberInfoPopup'), rowData, 'EDIT');
		
		// 모의진단 탭
		
		
		// 풀이이력 탭
	});
	
	// 닉네임 변경이력
	$('.btnNickHistory').on('click', function() {
		$('.nickHistoryPopup').addClass('is-visible');
		var popupGridColumn = [];
		popupGridColumn.push({'data':'mnhRegistDatetime',	'title':'변경일자',	render:plus.renderer.datetime});
		popupGridColumn.push({'data':'mnhNickname',			'title':'닉네임'});
		plus.makeGrid('#gridElementNickPopup', popupGridColumn, plus.makeAjax('/plusadmin/ajax/member/memberNickHistoryList', {memId : $('#memId').val()}, 'resultList'), {pageLength : 5, attr : '속성'});
	});
	
	// 레벨 상세이력
	$('.btnLevelHistory').on('click', function() {
		$('.levelHistoryPopup').addClass('is-visible');
		var popupGridColumn = [];
		popupGridColumn.push({'data':'mlhTo',			'title':'레벨'});
		popupGridColumn.push({'data':'mlhDatetime',		'title':'레벨 달성일자',	render:plus.renderer.datetime});
		plus.makeGrid('#gridElementLevelPopup', popupGridColumn, plus.makeAjax('/plusadmin/ajax/member/memberLevelHistoryList', {memId : $('#memId').val()}, 'resultList'), {pageLength : 5, attr : '속성'});
	});
	
	// 푸시 수신동의 정보
	$('.btnUserPushInfo').on('click', function() {
		$('.userPushInfoPopup').addClass('is-visible');
		var popupGridColumn = [];
		//popupGridColumn.push({'data':'mlhTo',			'title':'구분'});
		//popupGridColumn.push({'data':'mlhDatetime',		'title':'수신여부'});
		//popupGridColumn.push({'data':'mlhDatetime',		'title':'동의일자',	render:plus.renderer.datetime});
		//plus.makeGrid('#gridElementPushPopup', popupGridColumn, plus.makeAjax('/plusadmin/ajax/member/memberPushInfoList', {memId : $('#memId').val()}, 'resultList'), {pageLength : 5, attr : '속성'});
	});

});
</script>