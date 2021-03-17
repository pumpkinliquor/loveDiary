<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>
<c:set var="ql" value="${resultMap.resultData.levReportList }" />
<c:set var="levDtl" value="${resultMap.resultData.levReportDtl }" />
<c:set var="levMap" value="${resultMap.resultData.levelMap }" />

<body class="report_body">
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>레벨 평가 리포트</h1>
			<div class="my_cont">
				<p class="my_id">
					<c:choose>
						<c:when test="${!empty userInfo.memNickname }">
							<c:out value="${userInfo.memNickname }" />
						</c:when>
						<c:otherwise>
							<c:out value="${userInfo.memEmail }" />
						</c:otherwise>
					</c:choose>
					님
				</p>
				<div class="my_grade">
					<span><em>Lv.<c:out value="${paramMap.levId }" /></em> 레벨평가</span>
					<c:if test="${fn:length(resultMap.resultData.levelList) > 1 }">
						<a href="#" class="btn_level btnSelectLevel"></a>
						<!-- 수정0222 -->
						<div class="layer_pop level_pop">
							<!-- 수정0222 -->
							<form>
								<ul class="select_div">
									<c:forEach var="row" items="${resultMap.resultData.levelList }" varStatus="i">
										<li class="ipt_radio_div"><input type="radio" id="select<c:out value="${i.index }"/>" name="levId" value="<c:out value="${row.levId }"/>" onclick="goLevelReport('<c:out value="${row.levId }"/>');" <c:if test="${paramMap.levId eq row.levId }">checked</c:if>/> <label for="select<c:out value="${i.index }"/>"><span><c:out value="${row.levName }" /></span></label></li>
									</c:forEach>
								</ul>
							</form>
						</div>
					</c:if>
				</div>
			</div>
		</header>
		<div class="container report">
			<section class="contents study_result_cont">
				<h2>학습 결과와 상세 분석</h2>
				<div class="study_result_div">
					<div class="q_div btx">
						<p class="tit ico_s2 fL">
							<span>평가 소요시간</span>
						</p>
						<c:set var="myTime" value="${fn:split(levDtl.myTime,':') }" />
						<span class="txt_time fR totalDurTimeArea"><c:out value="${myTime[0] }" />시간 <c:out value="${myTime[1] }" />분 <c:out value="${myTime[2] }" />초</span>
					</div>
					<div class="my_result_div">
						<c:set var="mySec" value="${fn:split(levDtl.mySec,':') }" />
						<c:set var="choiceSec" value="${fn:split(levDtl.choiceSec,':') }" />
						<c:set var="minusSec" value="${((mySec[1] * 60) + mySec[2]) - ((choiceSec[1] * 60) + choiceSec[2])}" />
						<c:if test="${minusSec > 0 && minusSec < 60 }">
							<c:set var="moveSec" value="t10" />
						</c:if>
						<c:if test="${minusSec >= 60 && minusSec < 120 }">
							<c:set var="moveSec" value="t20" />
						</c:if>
						<c:if test="${minusSec >= 120 && minusSec < 180 }">
							<c:set var="moveSec" value="t30" />
						</c:if>
						<c:if test="${minusSec >= 180 && minusSec < 240 }">
							<c:set var="moveSec" value="t40" />
						</c:if>
						<c:if test="${minusSec >= 240 && minusSec < 300 }">
							<c:set var="moveSec" value="t50" />
						</c:if>
						<c:if test="${minusSec >= 300 }">
							<c:set var="moveSec" value="t50" />
						</c:if>
						<c:if test="${minusSec < 0 && minusSec > -60 }">
							<c:set var="moveSec" value="t_10" />
						</c:if>
						<c:if test="${minusSec <= -60 && minusSec > -120 }">
							<c:set var="moveSec" value="t_20" />
						</c:if>
						<c:if test="${minusSec <= -120 && minusSec > -180 }">
							<c:set var="moveSec" value="t_30" />
						</c:if>
						<c:if test="${minusSec <= -180 && minusSec > -240 }">
							<c:set var="moveSec" value="t_40" />
						</c:if>
						<c:if test="${minusSec <= -240 && minusSec > -300 }">
							<c:set var="moveSec" value="t_50" />
						</c:if>
						<c:if test="${minusSec <= -300 }">
							<c:set var="moveSec" value="t_50" />
						</c:if>
						<div class="my_graph">
							<div class="average">
								<span class="txt">권장 풀이소요시간</span><i class="bar_l"></i>
							</div>
							<div class="my_r my_time <c:out value="${moveSec}"/>">
								<span class="txt">나의 소요시간</span> <span class="my_posi"><img class="my_pic" src="/common/siteImgView?safSeq=${loginSessionSafSeq}" alt="" onclick="goUserInfo();" onerror="this.src='/assets/images/_tmp/tmp_img_photo.jpg'"></span> <i class="bar_l"></i>
							</div>
						</div>
						<p>
							1문제당 평균 <b class="durMinArea"><c:out value="${mySec[1] }" /></b>분 <b class="durSecArea"><c:out value="${mySec[2] }" /></b>초가 필요합니다. <br>권장 풀이 시간은 문제당 <b class="recommendMinArea"><c:out value="${choiceSec[1] }" /></b>분 <b class="recommendSecArea"><c:out value="${choiceSec[2] }" /></b>초 입니다.
						</p>
					</div>
					<div class="q_div btx">
						<p class="tit ico_rate fL">
							<span>평가 정답율</span>
						</p>
						<span class="txt_time fR passPerArea"><c:out value="${levDtl.per }" />%</span>
					</div>
					<div class="my_result_div btx">
						<c:set var="minusPer" value="${levDtl.per - levDtl.allPer }" />
						<c:if test="${minusPer > 0 && minusPer < 20}">
							<c:set var="movePer" value="t10" />
						</c:if>
						<c:if test="${minusPer >= 20 && minusPer < 40}">
							<c:set var="movePer" value="t20" />
						</c:if>
						<c:if test="${minusPer >= 40 && minusPer < 60}">
							<c:set var="movePer" value="t30" />
						</c:if>
						<c:if test="${minusPer >= 60 && minusPer < 80}">
							<c:set var="movePer" value="t40" />
						</c:if>
						<c:if test="${minusPer >= 80 && minusPer < 100}">
							<c:set var="movePer" value="t50" />
						</c:if>
						<c:if test="${minusPer >= 100}">
							<c:set var="movePer" value="t50" />
						</c:if>
						<c:if test="${minusPer < 0 && minusPer > -20}">
							<c:set var="movePer" value="t_10" />
						</c:if>
						<c:if test="${minusPer <= -20 && minusPer > -40}">
							<c:set var="movePer" value="t_20" />
						</c:if>
						<c:if test="${minusPer <= -40 && minusPer > -60}">
							<c:set var="movePer" value="t_30" />
						</c:if>
						<c:if test="${minusPer <= -60 && minusPer > -80}">
							<c:set var="movePer" value="t_40" />
						</c:if>
						<c:if test="${minusPer <= -80 && minusPer > -100}">
							<c:set var="movePer" value="t_50" />
						</c:if>
						<c:if test="${minusPer <= -100}">
							<c:set var="movePer" value="t_50" />
						</c:if>
						<div class="my_graph">
							<div class="average">
								<span class="txt">평균 정답율</span><i class="bar_l"></i>
							</div>
							<div class="my_r my_rate <c:out value="${movePer }"/>">
								<span class="txt">나의 정답율</span> <span class="my_posi"><img class="my_pic" src="/common/siteImgView?safSeq=${loginSessionSafSeq}" alt="" onclick="goUserInfo();" onerror="this.src='/assets/images/_tmp/tmp_img_photo.jpg'"></span> <i class="bar_l"></i>
							</div>
						</div>
						<p>
							전체 풀이 문항수 <b class="totalQstCnt"><c:out value="${levDtl.qstCnt }" /></b>문제 중 <br>맞춘 문제는 <b class="passQstCnt"><c:out value="${levDtl.trueCnt }" /></b>문제 입니다.
						</p>
						<!-- 수정0209 -->
					</div>
				</div>
			</section>
			<section class="contents analysis_cont pb">
				<h2>문항별 분석</h2>
				<p class="txt_st1 mt25 mb5">문제 선택 시 해당 문제 다시 풀기로 이동 합니다.</p>
				<div class="tbl_wrap">
					<table id="studyT" class="studyT table table-bordered table-hover dataTable dtr-inline">
						<!-- 수정0219 -->
						<colgroup>
							<col width="70px">
							<col width="70px">
							<col width="150px">
							<col width="70px">
							<col width="70px">
							<col width="70px">
							<col width="70px">
							<col width="70px">
						</colgroup>
						<!-- //수정0219 -->
						<thead>
							<tr>
								<th class="sticky-col first-col sorting"><span>구분</span></th>
								<th class="sticky-col second-col sorting"><span>번호</span></th>
								<th><span>개념요소</span></th>
								<th><span>채점<br>결과
								</span></th>
								<th><span>정답율</span></th>
								<th><span>난이도</span></th>
								<th><span>가장<br>빈번한<br>오답
								</span></th>
								<th><span>상위50%<br>정답율
								</span></th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${!empty ql }">
								<c:forEach var="q" items="${ql }">
									<tr>
										<td class="sticky-col first-col">
											<c:out value="${q.acaName }" />
										</td>
										<td class="sticky-col second-col">
											<input type="hidden" name="qstId" value="${q.qstId }" />
											<a href="javascript:void(0)" onclick="goQuestion(this);">${q.qstId }</a>
										</td>
										<td>
											<c:out value="${q.cptNmStr }" />
										</td>
										<c:choose>
											<c:when test="${q.qstValue eq '1' }">
												<c:set var="spNum" value="①" />
											</c:when>
											<c:when test="${q.qstValue eq '2' }">
												<c:set var="spNum" value="②" />
											</c:when>
											<c:when test="${q.qstValue eq '3' }">
												<c:set var="spNum" value="③" />
											</c:when>
											<c:when test="${q.qstValue eq '4' }">
												<c:set var="spNum" value="④" />
											</c:when>
											<c:when test="${q.qstValue eq '5' }">
												<c:set var="spNum" value="⑤" />
											</c:when>
											<c:otherwise>
												<c:set var="spNum" value="${q.qstValue}" />
											</c:otherwise>
										</c:choose>
										<td <c:if test="${q.passYn eq 'n'}">class="fc_R"</c:if>>
											<c:if test="${q.passYn eq 'n'}">X ${spNum }</c:if>
											<c:if test="${q.passYn eq 'y'}">O</c:if>
										</td>
										<td>
											<fmt:formatNumber value="${q.allPer }" type="percent" />
										</td>
										<td>
											<c:out value="${q.testLev }" />
										</td>
										<td>
											<c:if test="${q.maxFalseValue ne null}">${q.maxFalseValue }</c:if>
											<c:if test="${q.maxFalseValue eq null || q.maxFalseValue eq ''}">-</c:if>
										</td>
										<td>
											<c:if test="${q.topPer ne null}">
												<fmt:formatNumber value="${q.topPer }" type="percent" />
											</c:if>
											<c:if test="${q.topPer eq null || q.topPer eq ''}">
													-
												</c:if>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty ql }">
								<tr>
									<td colspan="8">문제풀이 이력이 없습니다.</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
				<p class="txt_st1">※ 난이도는 A가 가장 어렵고 E가 가장 쉬운 문항을 의미합니다.</p>
			</section>

			<%-- 평가결과 계산 --%>
			<c:set var="weekly" value="${resultMap.resultData.weeklyMap }" />
			<c:set var="level" value="${resultMap.resultData.levelMap }" />
			<c:set var="after" value="${resultMap.resultData.afterMap }" />

			<c:set var="weeklyResult" value="fail" />
			<fmt:formatNumber var="weeklyPer" value="${weekly.userAnsCnt/weekly.totalQstCnt*100}" pattern="#,###" />
			<c:if test="${weeklyPer >= 70 }">
				<c:set var="weeklyResult" value="succ" />
			</c:if>

			<c:set var="levelResult" value="fail" />
			<fmt:formatNumber var="levelPer" value="${level.userAnsCnt/level.totalQstCnt*100}" pattern="#,###" />
			<c:if test="${levelPer >= 70 }">
				<c:set var="levelResult" value="succ" />
			</c:if>

			<c:choose>
				<c:when test="${paramMap.pagePath eq 'levelTest' }">
					<section class="contents levelup_cont">
						<h2>레벨업 결과</h2>
						<p class="txt_st1 mb10">주간평가, 레벨평가 풀이 문제 70% 이상 정답일 경우 승급 됩니다.</p>
						<button class="btn btn_st4 btnWeekly">주간평가 결과확인</button>
						<button class="btn btn_st4 btn_result btnWeeklyResult" style="display: none;">
							주간평가 결과
							<c:out value="${weeklyPer }" />
							%
							<c:choose>
								<c:when test="${weeklyPer >= 70 }">
									<span class="badge"><em>달성</em></span>
								</c:when>
								<c:otherwise>
									<span class="badge failed"><em>미달성</em></span>
								</c:otherwise>
							</c:choose>
						</button>
						<button class="btn btn_st4 btnLevel">레벨평가 결과확인</button>
						<button class="btn btn_st4 btn_result btnLevelResult" style="display: none;">
							레벨평가 결과
							<c:out value="${levelPer }" />
							%
							<c:choose>
								<c:when test="${levelPer >= 70 }">
									<span class="badge"><em>달성</em></span>
								</c:when>
								<c:otherwise>
									<span class="badge failed"><em>미달성</em></span>
								</c:otherwise>
							</c:choose>
						</button>
						<p class="txt_st2">보충학습을 진행하면 기준 점수를 달성하지 못했어도 레벨 업 가능합니다. 지금 보충학습을 시작하세요.</p>
						<c:choose>
							<c:when test="${after.totalQstCnt > 0 and after.totalQstCnt eq after.userAnsCnt }">
								<button class="btn btn_st4 btn_result">
									보충학습 Go <span class="badge"><em>완료</em></span>
								</button>
							</c:when>
							<c:otherwise>
								<button class="btn btn_st4">보충학습 Go</button>
							</c:otherwise>
						</c:choose>
					</section>
				</c:when>
				<c:otherwise>
					<%-- 결과 --%>
					<%-- 평가완료 이외에 전체메뉴 > 레벨평가 리포트로 접근했을 때에 보여주는 영역 --%>
					<section class="contents levelup_cont">
						<h2>레벨업 결과</h2>
						<p class="txt_st1 mb10">주간평가, 레벨평가 풀이 문제 70% 이상 정답일 경우 승급 됩니다.</p>
						<button class="btn btn_st4 btn_result">
							주간평가 결과
							<c:out value="${weeklyPer }" />
							%
							<c:choose>
								<c:when test="${weeklyPer >= 70 }">
									<span class="badge"><em>달성</em></span>
								</c:when>
								<c:otherwise>
									<span class="badge failed"><em>미달성</em></span>
								</c:otherwise>
							</c:choose>
						</button>
						<button class="btn btn_st4 btn_result">
							레벨평가 결과
							<c:out value="${levelPer }" />
							%
							<c:choose>
								<c:when test="${levelPer >= 70 }">
									<span class="badge"><em>달성</em></span>
								</c:when>
								<c:otherwise>
									<span class="badge failed"><em>미달성</em></span>
								</c:otherwise>
							</c:choose>
						</button>
						<p class="txt_st2">보충학습을 진행하면 기준 점수를 달성하지 못했어도 레벨 업 가능합니다. 지금 보충학습을 시작하세요.</p>
						<c:choose>
							<c:when test="${after.totalQstCnt > 0 and after.totalQstCnt eq after.userAnsCnt }">
								<button class="btn btn_st4 btn_result">
									보충학습 Go <span class="badge"><em>완료</em></span>
								</button>
							</c:when>
							<c:otherwise>
								<button class="btn btn_st4">보충학습 Go</button>
							</c:otherwise>
						</c:choose>
					</section>
				</c:otherwise>
			</c:choose>

			<%-- // 다음레벨 학습 이동 // 클릭 시 레벨업 시켜준다. --%>
			<c:if test="${loginSessionLevel lt 5 and paramMap.levId eq loginSessionLevel}">
				<div class="conts_btm">
					<p>6주 뒤 1등급 상승을 위한 AIGo 처방</p>
					<strong>LEVEL <c:out value="${loginSessionLevel+1 }" /> 코스
					</strong>
					<c:choose>
						<c:when test="${levelPer >= 70 and weeklyPer >= 70 }">
							<a href="#!" class="btn btn_st3" onclick="fnLevelup();">Go</a>
						</c:when>
						<c:when test="${(levelPer < 70 or weeklyPer < 70) and (after.totalQstCnt > 0 and after.totalQstCnt eq after.userAnsCnt) }">
							<a href="#!" class="btn btn_st3">Go</a>
						</c:when>
						<c:otherwise>
							<a href="#!" class="btn btn_st3" style="background: #a9a9a9 !important;">Go</a>
						</c:otherwise>
					</c:choose>
				</div>
			</c:if>

		</div>
		<!-- //container -->
	</div>

</body>

<form id="form" name="form" method="post">
	<input type="hidden" name="levId" value="<c:out value="${paramMap.levId}"/>" />
	<input type="hidden" name="orderCondition" value="num" />
	<input type="hidden" name="orderType" value="asc" />
</form>
<form id="levelupform" name="levelupform" method="post">
	<input type="hidden" name="levId" value="<c:out value="${paramMap.levId}"/>" />
</form>

<script type="text/javascript" charset="UTF-8" src="/assets/js/public/default.js"></script>
<script type="text/javascript">
	var $form = $("#form");
	var $levelupform = $("#levelupform");

	$(document).ready(function() {
		// 정렬 클릭이벤트
		$('.sort_div span').on('click', function() {
			$('.sort_div span').removeClass('on');
			$(this).addClass('on');
		});

		// 결과확인 버튼 이벤트
		$('.btnWeekly').on('click', function() {
			$(this).remove();
			$('.btnWeeklyResult').show();
		});
		$('.btnLevel').on('click', function() {
			$(this).remove();
			$('.btnLevelResult').show();
		});

		// 레벨선택 이벤트
		$('.btnSelectLevel').on('click', function() {
			$('.levelListPopup').addClass('is-visible');
		});

		// 	fnLoadReport();

		//사용자 정보 조회
		fnInit();

	});

	// // 리포트 리스트 로드
	// function fnLoadReport(){
	// 	var param = $form.domJson();
	// 	var arrNum = ['①','②','③','④','⑤'];
	// 	var difficulty = '-';

	// 	// 리포트 리스트
	// 	$.call('/front/ajax/report/levelReportList', param, function(r){
	// 		var list = r.resultList;
	// 		var innerHtml = '';
	// 		var time = 0;
	// 		var totalDurTime = 0;
	// 		var passCnt = 0;
	// 		var totalRecommendTime = 0;
	// 		for(var i=0; i<list.length; i++){
	// 			// 평가소요시간
	// 			totalDurTime += list[i].durTime;
	// 			var arrRecommendTime = list[i].qstAddEtc07.split(':');
	// 			totalRecommendTime += fnTimeToSeconds(0,arrRecommendTime[0],arrRecommendTime[1]);
	// 			// 평가 정답율
	// 			if(list[i].passYn == 'y'){
	// 				passCnt++;
	// 			}
	// 			// table data set
	// 			innerHtml += '<tr>';
	// 			innerHtml += '	<td class="sticky-col first-col">레벨평가</td>';
	// 			innerHtml += '	<td class="sticky-col second-col"><a href="/front/review/question?qstId='+list[i].qstId+'">'+list[i].rownum+'</a></td>';
	// 			innerHtml += '	<td>'+ list[i].cptElStr+'</td>';
	// 			if(list[i].passYn == 'n'){
	// 				innerHtml += '	<td class="fc_R">X '+arrNum[list[i].qstValue-1]+'</td>';
	// 			}else{
	// 				innerHtml += '	<td>'+list[i].ansValue+'</td>';
	// 			}
	// 			innerHtml += '	<td>'+ list[i].passPer+'%</td>';
	// 			// 난이도 계산
	// 			if(list[i].qstPreType == 'S'){
	// 				if(list[i].qstPreS01 <= list[i].passPer && list[i].passPer <= list[i].qstPreE01){
	// 					difficulty = 'A';
	// 				}else if(list[i].qstPreS02 <= list[i].passPer && list[i].passPer <= list[i].qstPreE02){
	// 					difficulty = 'B';
	// 				}else if(list[i].qstPreS03 <= list[i].passPer && list[i].passPer <= list[i].qstPreE03){
	// 					difficulty = 'C';
	// 				}else if(list[i].qstPreS04 <= list[i].passPer && list[i].passPer <= list[i].qstPreE04){
	// 					difficulty = 'D';
	// 				}else if(list[i].qstPreS05 <= list[i].passPer && list[i].passPer <= list[i].qstPreE05){
	// 					difficulty = 'E';
	// 				}
	// 			} else {
	// 				if(0 <= list[i].passPer && list[i].passPer <= 25){
	// 					difficulty = 'A';
	// 				}else if(26 <= list[i].passPer && list[i].passPer <= 50){
	// 					difficulty = 'B';
	// 				}else if(51 <= list[i].passPer && list[i].passPer <= 75){
	// 					difficulty = 'C';
	// 				}else if(76 <= list[i].passPer && list[i].passPer <= 90){
	// 					difficulty = 'D';
	// 				}else if(91 <= list[i].passPer && list[i].passPer <= 100){
	// 					difficulty = 'E';
	// 				}
	// 			}
	// 			innerHtml += '	<td>'+difficulty+'</td>';
	// 			if(list[i].qstType == 'S'){
	// 				innerHtml += '	<td>'+ list[i].failAnsValue+'</td>';
	// 			}else{
	// 				innerHtml += '	<td>-</td>';
	// 			}
	// 			innerHtml += '	<td>-</td>';
	// 			innerHtml += '</tr>';
	// 		}
	// 		var arrTime = getTimeStringSeconds(totalDurTime);
	// 		var arrAvgTime = getTimeStringSeconds(Math.round(totalDurTime/list.length));
	// 		var arrAvgRecommendTime = getTimeStringSeconds(Math.round(totalRecommendTime/list.length));
	// 		$('.durMinArea').text(arrAvgTime[1]);
	// 		$('.durSecArea').text(arrAvgTime[2]);
	// 		$('.recommendMinArea').text(arrAvgRecommendTime[1]);
	// 		$('.recommendSecArea').text(arrAvgRecommendTime[2]);
	// 		$('.totalDurTimeArea').text(arrTime[0]+'시간 '+arrTime[1]+'분 '+arrTime[2]+'초');
	// 		$('.passPerArea').text(Math.round(passCnt/list.length*100)+'%');
	// 		$('.totalQstCnt').text(list.length);
	// 		$('.passQstCnt').text(passCnt);
	// 		$('.reportListArea').html(innerHtml);
	// 	});
	// 	initDataTable();
	// }

	// 정렬 설정
	function fnSetOrder(condition, type) {
		$form.find("input[name='orderCondition']").val(condition);
		$form.find("input[name='orderType']").val(type);
		fnLoadReport();
	}

	// 레벨업
	function fnLevelup() {
		var param = $levelupform.domJson();
		$.call('/front/ajax/learn/levelUp', param, function(r) {
			location.href = "/front/achieve/list";
		});
	}

	// 레벨 리포트
	function goLevelReport(levId) {
		$form.find("input[name='levId']").val(levId);
		$form.attr("action", "/front/report/levelReport").submit();
	}

	$(function() {
		$('#studyT').DataTable({
			"paging" : false,
			"lengthChange" : false,
			"searching" : false,
			"ordering" : true,
			"info" : false,
			"autoWidth" : false,
			"responsive" : false,
		});
	});

	//사용자 정보 조회
	function fnInit() {
		if ("${loginSessionId}" != "") {
			$.call("/front/ajax/user/userInfo", {}, function(returnJson) {

				var resultData = null;
				if (returnJson.resultData) resultData = returnJson.resultData;
				else return;

				if (resultData.saf_seq) $('img[class=my_pic]').attr("src", "/common/siteImgView?safSeq=" + resultData.saf_seq);
				if (resultData.mem_nickname) $('p[class=my_id]').find('b').text(resultData.mem_nickname);

			});
		}
	}

	//다시풀기 문제페이지 이동
	function goQuestion(el) {
		var qstId = $(el).closest("td").find("input[name=qstId]").val();
		if (qstId != "") {
			$.formSubmit("/front/review/question", $.extend(null, {
				"qstId" : qstId
			}), {
				method : 'post'
			});
		} else {
			commonModalPopup("문제번호가 없습니다.");
			return false;
		}
	}
</script>