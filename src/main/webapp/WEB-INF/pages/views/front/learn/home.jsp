<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@ page import="java.util.Enumeration"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>
<c:set var="requestParam" value="<%=new ObjectMapper().writeValueAsString(request.getParameterMap())%>" />
<c:set var="rate" value="${resultMap.resultData.rate}" />

<%
	Enumeration<String> attrNames = request.getAttributeNames();
%>
<jsp:useBean id="now" class="java.util.Date" />
<!-- 현재년도 -->
<fmt:formatDate var="nowY" value="${now }" pattern="yyyy" />
<!-- 현재년월일 -->
<fmt:formatDate var="nowDate" value="${now }" pattern="yyyyMMdd" />
<!-- 현재년월일 데이트 형 변환 -->
<fmt:parseDate value="${nowDate }" var="nowDay" pattern="yyyyMMdd" />
<!-- 현재일 계산 -->
<fmt:parseNumber value="${nowDay.time / (1000*60*60*24)}" integerOnly="true" var="nowTime" />
<!-- 현재년도 수능일 -->
<fmt:parseDate value="${nowY }1118" var="testDay" pattern="yyyyMMdd" />
<!-- 현재년도 수능일 계산 -->
<fmt:parseNumber value="${testDay.time / (1000*60*60*24)}" integerOnly="true" var="testTime" />
<body class="learning_body">
	<div class="wrapper">
		<header class="header">
			<form id="mainFrm" name="mainFrm">
				<input type="hidden" id="levId" name="levId" value="${userInfo.memLevel }" />
			</form>
			<a href="javascript:" class="btn_allmenu">전체메뉴</a>
			<h1 class="logo">AIGo</h1>
			<div class="dday_div">
				<span> <!-- 수능 디데이 2021년 11월 18일 기준으로 카운트. 1년 단위 갱신 --> <c:choose>
						<c:when test="${(testTime - nowTime) ge 0 }">
							<fmt:parseDate value="${nowY }1118" var="testDay" pattern="yyyyMMdd" />
							<fmt:parseNumber value="${testDay.time / (1000*60*60*24)}" integerOnly="true" var="testTime" />
							${nowY + 1 } 수능 <em>D-${testTime - nowTime}</em>
						</c:when>
						<c:otherwise>
							<fmt:parseDate value="${nowY + 1 }1118" var="testDay" pattern="yyyyMMdd" />
							<fmt:parseNumber value="${testDay.time / (1000*60*60*24)}" integerOnly="true" var="testTime" />
							${nowY + 2 } 수능 <em>D-${testTime - nowTime}</em>
						</c:otherwise>
					</c:choose>
				</span>
			</div>
			<div class="my_cont">
				<img class="my_pic" src="/common/siteImgView?safSeq=${userInfo.safSeq}" alt="임시 프로필 이미지" onclick="goUserInfo();" onerror="this.src='/assets/images/default_img.png'">
				<p class="my_id">
					<c:choose>
						<c:when test="${!empty userInfo.memNickname }">
							<c:out value="${userInfo.memNickname }" />
						</c:when>
						<c:otherwise>
							<c:out value="${userInfo.memEmail }" />
						</c:otherwise>
					</c:choose>
				</p>
				<div class="my_grade">
					<!-- 백분위 퍼센트(올림차순) : (1 - (((나보다 높은 점수 누적인원) + (동점자수 / 2)) / 전체응시자수)) * 100 -->
					<!-- 백분위 퍼센트(내림차순) : (1 - (((나보다 낮은 점수 누적인원) + (동점자수 / 2)) / 전체응시자수)) * 100 -->
					<!-- 로그인 한 회원의 최초 부여 레벨과 현재 예상 등급 -->
					<!-- 예상등급 노출기준 : 성취도 노출기준에 따른 백분위 등급 -->
					<c:choose>
						<c:when test="${not empty userInfo.firstLevel }">
							<c:if test="${userInfo.firstLevel eq '1'}">
								<c:set var="userFirstGrade" value="6등급 이하" />
							</c:if>
							<c:if test="${userInfo.firstLevel eq '2'}">
								<c:set var="userFirstGrade" value="4등급" />
							</c:if>
							<c:if test="${userInfo.firstLevel eq '3'}">
								<c:set var="userFirstGrade" value="3등급" />
							</c:if>
							<c:if test="${userInfo.firstLevel eq '4'}">
								<c:set var="userFirstGrade" value="2등급" />
							</c:if>
							<c:if test="${userInfo.firstLevel > '4'}">
								<c:set var="userFirstGrade" value="1등급" />
							</c:if>
						</c:when>
						<c:otherwise>
							<c:set var="userFirstGrade" value="${userInfo.tempGrade } 등급" />
						</c:otherwise>
					</c:choose>
					<span>최초 등급 <c:out value="${userFirstGrade }" />
					</span> <span class="fc1">성취 예상 등급 <em><c:out value="${resultMap.resultData.userAchieveData.myGrade}" />등급</em></span>
				</div>
			</div>
		</header>
		<div class="container learning">
			<section class="contents level_conts">
				<div class="level_div">
					<!-- 등급 : (((나보다 높은 점수 누적인원) + (동점자수 / 2)) / 전체응시자수) * 10 -->
					<!-- 예상등급 노출기준 : 성취도 노출기준에 따른 백분위 -->
					<span class="rate">Your Rate : <c:out value="${resultMap.resultData.userAchieveData.myRate}" />%
					</span>
					<!-- 5레벨로 구분, 나의 현재 레벨까지 채워진 가로 바 형태 그래프 -->
					<div class="lv_section">
						<div class="bar_wrap">
							<div class="bar_div lv<c:out value="${userInfo.memLevel }"/>" style="">
								<div class="msg_div lv<c:out value="${userInfo.memLevel }"/>">
									<c:if test="${userInfo.memLevel eq '1' }">
										<c:set var="lvNm" value="나는 할 수 있다" />
									</c:if>
									<c:if test="${userInfo.memLevel eq '2' }">
										<c:set var="lvNm" value="매일매일 꾸준히" />
									</c:if>
									<c:if test="${userInfo.memLevel eq '3' }">
										<c:set var="lvNm" value="조금만 더 노력해요" />
									</c:if>
									<c:if test="${userInfo.memLevel eq '4' }">
										<c:set var="lvNm" value="1등급 고지가 눈 앞" />
									</c:if>
									<c:if test="${userInfo.memLevel eq '5' }">
										<c:set var="lvNm" value="실수를 줄이자" />
									</c:if>
									<div>
										<span class="typing">${lvNm }</span>
									</div>
								</div>
								<span class="bar_l"></span> <span class="bar"></span> <em class="bar_txt">현재 LEVEL <c:out value="${userInfo.memLevel }" />
								</em>
							</div>
						</div>
					</div>
					<ul>
						<li>Level 1</li>
						<li>Level 2</li>
						<li>Level 3</li>
						<li>Level 4</li>
						<li>Level 5</li>
					</ul>
					<div class="go_div">
						<p>
							등급 상승을 위한 <br> <span class="txt_AI">AI</span>문제풀이를 시작하세요!
						</p>
						<!-- 로그인+모의진단 완료+해당레벨 레벨평가 제외 모든 문항 풀이 이력 있으면서 레벨평가는 완료하지 않은 경우 노출 -->
						<!-- 선택 시 해당 레벨 성취기준 선택 페이지로 이동 -->
						<a href="javascript:" class="btn btn_go" onclick="goAcheivePage('${userInfo.memLevel }');"><span class="txt_GO">GO</span></a>
					</div>
				</div>
			</section>



			<!-- 로그인+모의진단 완료+확인문제 풀이 이력 0회 일 시 노출 -->
			<c:if test="${resultMap.resultData.cnts.testCnt <= 0 }">
				<!-- 정답율 상위 3개 문제 노출 -->
				<section class="contents q_conts">
					<h2>대다수의 학생들이 맞춘 문제</h2>
					<ul>
						<c:if test="${!empty resultMap.resultData.allPassY }">
							<c:forEach var="y" items="${resultMap.resultData.allPassY }">
								<li class="q_txt_div"onclick="goReviewQuestion(this); return false;">
									<input type="hidden" name="lQstId" value="${y.qstId }"/>
									<div class="fL">
										<p class="tit">
											<c:out value="${y.unitName }" />
										</p>
										<p class="q_txt">
											<c:out value="${y.acvName }" />
										</p>
										<span class="badge_rate"><c:out value="${y.acaName }" /> <c:out value="${y.qstId }" />번</span>
									</div>
									<div class="fR">
										<div class="badge">
											<div>
												<span class="f1">정답율</span> <span class="f2"><em><fmt:formatNumber value="${y.per }" type="percent" /></em></span>
											</div>
										</div>
									</div>
								</li>
							</c:forEach>
						</c:if>
					</ul>
				</section>

				<!-- 정답율 하위 3개 문제 노출 -->
				<section class="contents q_conts">
					<h2>대다수의 학생들이 틀린 문제</h2>
					<ul class="wrong">
						<c:if test="${!empty resultMap.resultData.allPassN }">
							<c:forEach var="n" items="${resultMap.resultData.allPassN }">
								<li class="q_txt_div" onclick="goReviewQuestion(this); return false;">
									<input type="hidden" name="lQstId" value="${n.qstId }"/>
									<div class="fL">
										<p class="tit">
											<c:out value="${n.unitName }" />
										</p>
										<p class="q_txt">
											<c:out value="${n.acvName }" />
										</p>
										<span class="badge_rate"><c:out value="${n.acaName }" /> <c:out value="${n.qstId }" />번</span>
									</div>
									<div class="fR">
										<div class="badge">
											<div>
												<span class="f1">정답율</span> <span class="f2"><em><fmt:formatNumber value="${n.per }" type="percent" /></em></span>
											</div>
										</div>
									</div>
								</li>
							</c:forEach>
						</c:if>
					</ul>
				</section>
			</c:if>


			<!-- 로그인+모의진단 완료+확인문제 풀이이력 1회 이상 일 시 노출 -->
			<c:if test="${resultMap.resultData.cnts.testCnt > 0 }">
				<!-- 채점 이력이 있는 정답을 맞춘 문제 중 풀이 이력 최근 순 5개 노출 -->
				<section class="contents q_conts">
					<h2>맞춘 문제도 다시 보자</h2>
					<c:if test="${!empty resultMap.resultData.passY }">
						<ul>
							<c:forEach var="y" items="${resultMap.resultData.passY }">
								<li class="q_txt_div"onclick="goReviewQuestion(this); return false;">
									<input type="hidden" name="lQstId" value="${y.qstId }"/>
									<div class="fL">
										<p class="tit">
											<c:out value="${y.unitName }" />
										</p>
										<p class="q_txt">
											<c:out value="${y.acvName }" />
										</p>
										<span class="badge_rate"><c:out value="${y.acaName }" /> <c:out value="${y.qstId }" />번</span>
									</div>
									<div class="fR">
										<div class="badge">
											<c:set var="allTruePer" value="${y.allTrueCnt / y.allTestCnt }" />
											<div>
												<span class="f1">정답율</span> <span class="f2"><em><fmt:formatNumber value="${allTruePer }" type="percent" /></em></span>
											</div>
										</div>
									</div>
								</li>
							</c:forEach>
						</ul>
					</c:if>
					<c:if test="${empty resultMap.resultData.passY }">
						<ul>
							<!-- 맞춘 문제 없을 경우 -->
							<li class="none">맞춘 문제가 없습니다.<br>지금, 문제를 풀어보세요.
							</li>
						</ul>
					</c:if>
				</section>

				<!-- 채점 이력이 있는 정답을 틀린 문제 중 풀이 이력 최근 순 5개 노출 -->
				<section class="contents q_conts">
					<h2>틀린 문제는 자꾸자꾸 다시 보자</h2>
					<c:if test="${!empty resultMap.resultData.passN }">
						<ul class="wrong">
							<c:forEach var="n" items="${resultMap.resultData.passN }">
								<li class="q_txt_div pdbm"onclick="goReviewQuestion(this); return false;">
									<input type="hidden" name="lQstId" value="${n.qstId }"/>
									<div class="fL">
										<p class="tit">
											<c:out value="${n.unitName }" />
										</p>
										<p class="q_txt">
											<c:out value="${n.acvName }" />
										</p>
										<span class="badge_rate"><c:out value="${n.acaName }" /> <c:out value="${n.qstId }" />번</span>
									</div>
									<div class="fR">
										<div class="badge">
											<c:choose>
												<c:when test="${n.allTrueCnt eq 0 }">
													<c:set var="allFalsePer" value="0" />
												</c:when>
												<c:otherwise>
													<c:set var="allFalsePer" value="${n.allTrueCnt / n.allTestCnt}" />
												</c:otherwise>
											</c:choose>
											<div>
												<span class="f1">정답율</span> <span class="f2"><em><fmt:formatNumber value="${allFalsePer }" type="percent" /></em></span>
											</div>
										</div>
										<fmt:parseDate var="falseDtFromFormat" value="${n.falseDt }" pattern="yyyy-MM-dd HH:mm:ss.SSS" />
										<fmt:formatDate var="falseDtToFormat" pattern="yy/MM/dd" value="${falseDtFromFormat }" />
										<p>
											틀린 날
											<c:out value="${falseDtToFormat }" />
										</p>
									</div>
								</li>
							</c:forEach>
						</ul>
					</c:if>
					<c:if test="${empty resultMap.resultData.passN }">
						<ul class="wrong">
							<!-- 틀린 문제 없을 경우 -->
							<li class="none">틀린 문제가 없습니다.<br>지금, 문제를 풀어보세요.
							</li>
						</ul>
					</c:if>
				</section>

				<!--  -->
				<section class="contents q_conts">
					<div class="txt_div">
						<p>
							<!-- 조회 시점의 누적 앱 설치자 수 -->
							요즘 다 AIgo로 수학 공부해요.<br>지금
							<fmt:formatNumber value="${resultMap.resultData.cnts.appPersonCnt}" pattern="#,###" />
							명의 친구들이 문제를 풀고 있어요.
						</p>
					</div>
					<ul>
						<li class="q_txt_div">
							<div class="fL">
								<div class="ov_h mb">
									<!-- 해당 계정으로 푼 문제 중 정답 문제 수만 카운트 (99,999가 넘어가면 99,999+) -->
									<span class="my_q">내가 맞춘 문제</span> <span class="q_total"><b><fmt:formatNumber value="${resultMap.resultData.cnts.trueCnt}" pattern="#,###" /></b>개</span>
								</div>
								<div class="ov_h mb">
									<!-- 해당 계정으로 푼 문제 중 채점이력이 있는 문제 수 카운트 (99,999가 넘어가면 99,999+) -->
									<span class="my_q">내가 푼 문제</span> <span class="q_total"><b><fmt:formatNumber value="${resultMap.resultData.cnts.testCnt}" pattern="#,###" /></b>개</span>
								</div>
							</div>
							<div class="fR">
								<div class="badge">
									<!-- 해당 계정으로 채점이력이 있는 문항 수 / 맞춘 문제 수 || % : 소수점 없음 최대 100% -->
									<div>
										<span class="f1">정답율</span> <span class="f2"><c:set var="truePer" value="${resultMap.resultData.cnts.trueCnt / resultMap.resultData.cnts.testCnt}" /> <em><fmt:formatNumber value="${truePer }" type="percent" /></em></span>
									</div>
								</div>
							</div>
						</li>
					</ul>
				</section>
			</c:if>

			<c:if test="${resultMap.resultData.cnts.testCnt < 5 }">
				<!-- 레벨별 성취기준 순서대로 노출 -->
				<section class="q_conts2">
					<h2 class="mbx">
						<c:choose>
							<c:when test="${!empty userInfo.memNickname }">
								<c:out value="${userInfo.memNickname }" />
							</c:when>
							<c:otherwise>
								<c:out value="${userInfo.memEmail }" />
							</c:otherwise>
						</c:choose>
						님의 <br>취약 영역 보완 문제
					</h2>
					<div class="q_card_slide">
						<c:if test="${!empty resultMap.resultData.unitList }">
							<c:forEach var="u" items="${resultMap.resultData.unitList }">
								<div class="q_card">
									<div class="card_top">
										<span class="subject">${u.subName }</span>
									</div>
									<div class="card_div">
										<div class="tit">
											<strong>${u.unitName }</strong>
										</div>
										<p class="txt">
											<c:choose>
												<c:when test="${!empty userInfo.memNickname }">
													<c:out value="${userInfo.memNickname }" />
												</c:when>
												<c:otherwise>
													<c:out value="${userInfo.memEmail }" />
												</c:otherwise>
											</c:choose>
											님 을 위한 문제<br> <em><fmt:formatNumber value="${u.userAllTestCnt}" pattern="#,###" /></em>개 준비 완료
										</p>
										<!-- 선택 시 해당 성취기준요소 학습목표로 이동 -->
										<a href="javascript:" class="go_btn" onclick="goTestPage('${u.subId }','${u.unitId }','${u.levId}','${u.acvId }');"><span>지금 학습을 시작하세요</span></a>
									</div>
								</div>
							</c:forEach>
						</c:if>
					</div>
				</section>

				<!-- 조회 시점의 누적 앱 설치자 수 노출 -->
				<section class="q_cont_btm">
					<p>
						<em><fmt:formatNumber value="${resultMap.resultData.cnts.appPersonCnt}" pattern="#,###" /></em>명의 학생들이 지금 수학 문제를 풀고 있습니다.
					</p>
				</section>
			</c:if>
			<c:if test="${resultMap.resultData.cnts.trueCnt > 4 }">
				<!-- 대단원별 문제 수 5개 이상 시 -->
				<section class="q_conts2">
					<h2 class="mbx">
						<c:choose>
							<c:when test="${!empty userInfo.memNickname }">
								<c:out value="${userInfo.memNickname }" />
							</c:when>
							<c:otherwise>
								<c:out value="${userInfo.memEmail }" />
							</c:otherwise>
						</c:choose>
						님이 <br>지금 가장 잘하는 단원이에요
					</h2>
					<!-- 대단원별 정답율 상위 5개 노출 -->
					<div class="q_card_slide owl-loaded owl-drag">
						<div class="owl-stage-outer">
							<div class="owl-stage" style="transform: translate3d(0px, 0px, 0px); transition: all 0s ease 0s; width: 2390px; padding-left: 30px; padding-right: 30px;">
								<c:forEach var="g" items="${resultMap.resultData.great }">
									<div class="owl-item active" style="width: 562.5px; margin-right: 20px;">
										<div class="q_card">
											<div class="card_top">
												<span class="subject"><c:out value="${g.subName }" /></span>
											</div>
											<div class="card_div">
												<div class="tit">
													<strong><c:out value="${g.unitName }" /></strong>
												</div>
												<p class="txt">
													<c:out value="${g.testCnt }" />
													문제 중 맞춘 문제
													<c:out value="${g.trueCnt }" />
													문제<br>정답율
													<fmt:formatNumber value="${g.per }" type="percent" />
												</p>
												<!-- 선택 시 해당 단원 필터링 된 상태의 다시풀기 페이지로 이동 -->
												<a href="javascript:" class="go_btn" onclick="goReTestPage('${g.subId}','${g.unitId}','${g.levId }','${u.acvId }'); return false;"><span>지금 학습을 시작하세요</span></a>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
						<div class="owl-nav disabled">
							<button type="button" role="presentation" class="owl-prev">
								<span aria-label="Previous">‹</span>
							</button>
							<button type="button" role="presentation" class="owl-next">
								<span aria-label="Next">›</span>
							</button>
						</div>
						<div class="owl-dots">
							<button role="button" class="owl-dot active">
								<span></span>
							</button>
							<button role="button" class="owl-dot">
								<span></span>
							</button>
						</div>
					</div>
				</section>
				<section class="q_cont_btm">
					<p>
						<!-- 최근 문제풀이 날짜까지의 일 수 카운트 최소 1 최대 999 (999 초과시 999+, 24시간 미만인 경우 1로 노출)-->
						<fmt:parseDate value="${resultMap.resultData.cnts.lastTestDt}" var="lastDay" pattern="yyyyMMdd" />
						<fmt:parseNumber value="${lastDay.time / (1000*60*60*24)}" integerOnly="true" var="lastTime" />
						<c:choose>
							<c:when test="${(nowTime - lastTime) gt 0 && (nowTime - lastTime) lt 1000}">
								<em>${nowTime - lastTime }</em>일 전에 마지막으로 AIgo의 맞춤 문제를 풀었습니다.
							</c:when>
							<c:when test="${(nowTime - lastTime) gt 0 && (nowTime - lastTime) gt 999}">
								<em>999+</em>일 전에 마지막으로 AIgo의 맞춤 문제를 풀었습니다.
							</c:when>
							<c:otherwise>
								<em>1</em>일 전에 마지막으로 AIgo의 맞춤 문제를 풀었습니다.
							</c:otherwise>
						</c:choose>
					</p>
				</section>
			</c:if>
		</div>
		<!-- //container -->
	</div>
</body>

<form id="homeFrm" name="homeFrm" method="post">
	<input type="hidden" name="qstId" value=""/>
</form>
<script type="text/javascript" charset="UTF-8" src="/assets/js/plugin/owlcarousel/owl.carousel.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="/assets/js/public/default.js"></script>
<script type="text/javascript">
	qCardSlider();
	lavelBar();
	typing();

	$(document).ready(function() {
		consoleFunc();
		// 		var removeEle = "";
		// 		//대단원별 풀이 문제 수 5개 이상
		// 		if (Number("${resultMap.resultData.cnts.testCnt}") > 4) {
		// 			removeEle += "[id=div_homeFrontInner_2],";
		// 		} else {
		// 			removeEle += "[id=div_homeFrontInner_1],";
		// 		}
		// 		if (Number("${resultMap.resultData.cnts.testCnt}") > 0) {
		// 			removeEle += "[id=div_homeFront_2],";
		// 			removeEle += "[id=div_homeFrontInnerTestCnt_1],";
		// 			//내가 맞춘 모든 문제
		// 			if (Number("${resultMap.resultData.cnts.trueCnt}") > 0) {
		// 				removeEle += "[id=div_homeFrontInnerTrueCnt_1],";
		// 				removeEle += "[id=div_homeFrontInnerTruePer_1],";
		// 			} else {
		// 				removeEle += "[id=div_homeFrontInnerTrueCnt_2],";
		// 				removeEle += "[id=div_homeFrontInnerTruePer_2],";
		// 			}
		// 			//맞춘 문제도 다시보자
		// 			if (Number("${resultMap.resultData.cnts.levTrueCnt}") > 0) {
		// 				removeEle += "[id=div_homeFrontInnerTrue_1],";
		// 			} else {
		// 				removeEle += "[id=div_homeFrontInnerTrue_2],";
		// 			}
		// 			//틀린문제는 자꾸 다시보자
		// 			if (Number("${resultMap.resultData.cnts.levFalseCnt}") > 0) {
		// 				removeEle += "[id=div_homeFrontInnerFalse_1],";
		// 			} else {
		// 				removeEle += "[id=div_homeFrontInnerFalse_2],";
		// 			}
		// 		} else {
		// 			removeEle += "[id=div_homeFront_1],";
		// 			removeEle += "[id=div_homeFrontInnerTestCnt_2],";
		// 		}
		// 		home.aigoRemoveAll(removeEle);

		// 레벨평가 대상 팝업
		if ("${resultMap.resultData.levelTestYn}" == "y") {
			commonModalPopup('<b>${loginSessionEmail }</b>님<br/>레벨업 평가 대상자로 선정되었습니다.');
			$(".popupConfirm").click({}, golevelTest);
		} else if ("${resultMap.resultData.recentlyLearningYn}" == "y") {
			// 학습 이어서 하기
			if ("${loginSessionLearningStatus}" != "n") {
				commonConfirmModalPopup('직전에 풀던 문제를<br/>이어서 학습할까요?');
				$(".popupConfirmOk").click({}, goRecentlyLearning);
			}
		}
		console.log("${learningHistoryYn}");
	});

	function consoleFunc() {
		//console.log("${resultMap.resultData.unitList}");
		console.log("${resultMap.resultData.userAchieveData}");
	}

	function goTestPage(subId, unitId, levId, acvId) {
		// 학습목표 상세페이지 이동
		$.formSubmit("/front/achieve/detail", $.extend($('#mainFrm').serializeObject(), {
			"acvId" : acvId
		}), {
			method : 'post'
		});
	}

	function goReTestPage(subId, unitId, levId, acvId) {
		// 다시보기 리스트 페이지
		$.formSubmit("/front/review/list", $.extend($('#mainFrm').serializeObject(), {
			"levIdStr" : levId,
			"subIdStr" : subId,
			"unitIdStr" : unitId
		}), {
			method : 'post'
		});
	}

	function goAcheivePage(levId) {
		$.formSubmit("/front/achieve/list", $.extend($('#mainFrm').serializeObject(), {
			"levId" : levId
		}), {
			method : 'post'
		});
	}

	function golevelTest() {
		// 레벨평가 페이지
		var param = {
			"levId" : "${resultMap.resultData.learning.levId}",
			"currentTestType" : "level",
			"currentTestTypeSub" : "basic",
			"currentQuestionViewNumber" : "0"
		};
		$.formSubmit("/front/learn/levelTest", param, {}, {
			method : 'post'
		});
	}

	$(document).mouseup(function(e) {
		var LayerPopup = $(".modal");
		if (LayerPopup.closest(".commonConfirmPopupLayer").is(".is-visible")) {
			if (LayerPopup.has(e.target).length === 0) {
				LayerPopup.closest(".commonConfirmPopupLayer").removeClass("is-visible");
			}
		}
	});

	function goRecentlyLearning() {
		// 진행중이던 학습이 있을 경우 이동
		var url = "/front/learn/question";
		var param = {
			"subId" : "${resultMap.resultData.learning.subId}",
			"levId" : "${resultMap.resultData.learning.levId}",
			"acvId" : "${resultMap.resultData.learning.acvId}",
			"currentTestType" : "${resultMap.resultData.learning.currentTestType}",
			"currentTestTypeSub" : "${resultMap.resultData.learning.currentTestTypeSub}"
		};

		var testType = "${resultMap.resultData.learning.currentTestType}";
		if (testType == "weekly") {
			url = "/front/learn/weeklyTest";
		}
		$.formSubmit(url, param, {}, {
			method : 'post'
		});
	}
	
	// 다시풀기 문제페이지 이동
	function goReviewQuestion(el) {
		var $form = $("#homeFrm");
		var qstId = $(el).find("input:eq(0)").val();
		if (qstId != "") {
			$form.find("input[name='qstId']").val(qstId);
			$form.attr("action", "/front/review/question").submit();
		} else {
			commonModalPopup("문제번호가 없습니다.");
			return false;
		}
	}
</script>

<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js">
	MathJax.Hub.Config({
		extensions : [ "tex2jax.js", "TeX/AMSmath.js", "TeX/AMSsymbols.js" ],
		jax : [ "input/TeX", "output/HTML-CSS" ],
		tex2jax : {
			inlineMath : [ [ '$', '$' ], [ "\\(", "\\)" ] ],
			displayMath : [ [ '$$', '$$' ], [ "\\[", "\\]" ] ],
		},
		"HTML-CSS" : {
			availableFonts : [ "TeX" ]
		}
	});
</script>