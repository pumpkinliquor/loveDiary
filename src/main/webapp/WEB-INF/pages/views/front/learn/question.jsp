<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp" %>
<c:set var="data" value="${resultMap.resultData.question}"/>
<c:set var="dataFile" value="${resultMap.resultData.questionFileList}"/>
<c:set var="learnProgress" value="${resultMap.resultData.learnProgressInfo}"/>
<c:set var="concept" value="${resultMap.resultData.concept}"/>
<c:set var="commentary" value="${resultMap.resultData.commentaryInfo}"/>
<c:set var="notionList" value="${resultMap.resultData.notionInfo}"/>
<c:set var="achieve" value="${resultMap.resultData.userAchieveData}"/>

<body class="question_body">
	<div class="wrapper">
		<header class="header">
			<h1 class="logo">AIGo</h1>
			<a href="/front/learn/exit" class="btn_out">나가기</a>
			<div class="my_grade_top">
				<div class="onoffswitch2">
					<input type="checkbox" id="lv_div">
					<label for="lv_div"><span></span></label>
				</div>
				<div class="lv_div">
					<span>최초 등급 5등급 </span>
					<span class="fc1">성취 예상 등급 
						<em>
						<c:choose>
							<c:when test="${achieve.myGrade < 6 }"><c:out value="${achieve.myGrade }" />등급</c:when>
							<c:otherwise>6등급 이하</c:otherwise>
						</c:choose>
						</em>
					</span>
				</div>
			</div>
		</header>
		<div class="container btm0 onoff">
			<div class="tab_wrap">
				<div class="tab_menu_wrap afterAnswerTabArea" style="display: none;">
					<div class="tab_menu tab3" id="qTab">
						<button class="tab_m1 tab_m active" type="button">채점</button>
						<c:if test="${not empty commentary }"><button class="tab_m2 tab_m" type="button">해설</button></c:if>
						<c:if test="${not empty notionList }"><button class="tab_m3 tab_m" type="button">개념</button></c:if>
					</div>
				</div>
				<div class="tab_cont_wrap" id="qTabCont">
					<div class="tab_cont1 tab_cont active">
						<div class="question_conts question_common2">
							<div class="q_top_section" id="qTopHeight">
								<h2 class="q_number"><c:out value="${data.currentQuestionViewNumber }"/>.</h2>
								<span class="ico_X passArea afterAnswerArea" style="display: none;">O</span>
								<div class="q_solve_top">
									<p class="q">
										<c:out value="${data.subName }"/> <c:out value="${data.levName }"/>. <c:out value="${data.acvName }"/>
										<!-- 테스트 후 삭제 -->
										<br/><b style="color: red;">문제분류 : ${data.acaName }, 답 : ${data.qstValue }, 문항 키 : ${data.qstKey } (관리자 확인/검수용)</b>
										<!-- 테스트 후 삭제 -->
									</p>
									<div class="tag_div">
										<c:if test="${not empty concept }" >
										<c:forEach var="row" items="${concept }" >
										<c:set var="arrCptName" value="${fn:split(row.notData, '|') }" />
										<a href="#" class="tag"><c:out value="${arrCptName[1]}"/></a>
										</c:forEach>
										</c:if>
									</div>
									<div class="ov_h posiA beforeAnswerArea">
										<div class="aigo_rate"><span class="logo_img">AIGo</span><span class="txt">정답률</span> <em><c:out value="${data.passPer }"/>%</em><!-- 수정0219 --></div>
										<c:choose>
											<c:when test="${data.qstPreType eq 'S' }">
												<c:choose>
												<c:when test="${data.qstPreS01 <= data.passPer and data.passPer <= data.qstPreE01 }"><c:set var="difficulty" value="A"/></c:when>
												<c:when test="${data.qstPreS02 <= data.passPer and data.passPer <= data.qstPreE02 }"><c:set var="difficulty" value="B"/></c:when>
												<c:when test="${data.qstPreS03 <= data.passPer and data.passPer <= data.qstPreE03 }"><c:set var="difficulty" value="C"/></c:when>
												<c:when test="${data.qstPreS04 <= data.passPer and data.passPer <= data.qstPreE04 }"><c:set var="difficulty" value="D"/></c:when>
												<c:when test="${data.qstPreS05 <= data.passPer and data.passPer <= data.qstPreE05 }"><c:set var="difficulty" value="E"/></c:when>
												<c:otherwise><c:set var="difficulty" value="E"/></c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
												<c:choose>
												<c:when test="${0 <= data.passPer and data.passPer <= 25 }"><c:set var="difficulty" value="A"/></c:when>
												<c:when test="${26 <= data.passPer and data.passPer <= 50 }"><c:set var="difficulty" value="B"/></c:when>
												<c:when test="${51 <= data.passPer and data.passPer <= 75 }"><c:set var="difficulty" value="C"/></c:when>
												<c:when test="${76 <= data.passPer and data.passPer <= 90 }"><c:set var="difficulty" value="D"/></c:when>
												<c:when test="${91 <= data.passPer and data.passPer <= 100 }"><c:set var="difficulty" value="E"/></c:when>
												<c:otherwise><c:set var="difficulty" value="E"/></c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose>
										<div><span>난이도</span><span class="d_E"><em><c:out value="${difficulty }"/></em></span></div>
									</div>
								</div>
								<div class="q_section">
									<div class="q_img_div">
										<%-- 문항 컨텐츠 --%>
										<c:choose>
											<c:when test="${data.qstContType eq 'I'}">
												<c:forEach var="row" items="${dataFile }">
													<c:if test="${fn:contains(row.safCode,'qstContType') }">
														<img class="q_img" src="/common/siteImgView?safSeq=<c:out value="${row.safSeq }"/>" alt="문제 이미지" onerror="this.src=''"/>
													</c:if>
												</c:forEach>
											</c:when>
											<c:when test="${data.qstContType eq 'T'}">
												<c:if test="${not empty data.qstContText }"><c:out value="${data.qstContText }" escapeXml="false" /></c:if>
											</c:when>
										</c:choose>
										<br/>
										<c:choose>
											<c:when test="${data.qstCont02Type eq 'I'}">
												<c:forEach var="row" items="${dataFile }">
													<c:if test="${fn:contains(row.safCode,'qstCont02Type') }">
														<img class="q_img" src="/common/siteImgView?safSeq=<c:out value="${row.safSeq }"/>" alt="문제 이미지" onerror="this.src=''"/>
													</c:if>
												</c:forEach>
											</c:when>
											<c:when test="${data.qstCont02Type eq 'T'}">
												<c:if test="${not empty data.qstCont02Text }"><c:out value="${data.qstCont02Text }" escapeXml="false" /></c:if>
											</c:when>
										</c:choose>
										<br/>
										<%-- 선지 영역은 객관식일 경우에만 노출 --%>
										<c:if test="${data.qstType eq 'S' }">
											<c:choose>
												<c:when test="${data.qstCont03Type eq 'I'}">
													<c:forEach var="row" items="${dataFile }">
														<c:if test="${fn:contains(row.safCode,'qstCont03Type') }">
															<img class="q_img" src="/common/siteImgView?safSeq=<c:out value="${row.safSeq }"/>" alt="문제 이미지" onerror="this.src=''"/>
														</c:if>
													</c:forEach>
												</c:when>
												<c:when test="${data.qstCont03Type eq 'T'}">
													<c:if test="${not empty data.qstAnswer01 }">① <c:out value="${data.qstAnswer01 }"/><br/></c:if>
													<c:if test="${not empty data.qstAnswer02 }">② <c:out value="${data.qstAnswer02 }"/><br/></c:if>
													<c:if test="${not empty data.qstAnswer03 }">③ <c:out value="${data.qstAnswer03 }"/><br/></c:if>
													<c:if test="${not empty data.qstAnswer04 }">④ <c:out value="${data.qstAnswer04 }"/><br/></c:if>
													<c:if test="${not empty data.qstAnswer05 and data.qstTypeNum > 4 }">⑤ <c:out value="${data.qstAnswer05 }"/><br/></c:if>
												</c:when>
											</c:choose>
										</c:if>
									</div>
									<div class="dontknow beforeAnswerArea">
										<a href="#!" class="fR btn btn_st1" onclick="fnChooseAnswer('');">잘 모르겠어요</a>
									</div>
									<div class="answer_cont mt afterAnswerArea" style="display: none;">
										<div class="answer">
											<span class="userAnswerArea">입력: -</span>
											<em>정답: <c:out value="${data.qstValue }"/></em>
										</div>
										<%-- 객관식일 경우 정답/입력답 표시  --%>
										<c:if test="${data.qstType eq 'S' }">
										<ul class="number_div mt10">
											<c:forEach begin="1" end="${data.qstTypeNum }" var="num">
											<li class="<c:out value="${num eq data.qstValue? 'on' : '' }" />" >
												<div><span class="num"><c:out value="${num }" /></span></div>
												<div><span class="markArea <c:out value="${num eq data.qstValue? ' ico_O' : '' }" />"></span></div>
											</li>
											</c:forEach>
										</ul>
										</c:if>
									</div>
									<div class="btm_btn_div2 afterAnswerArea" style="display: none;">
										<a href="#" class="next btnNext" onclick="goNext();"><span>다음</span></a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<%-- 해설 --%>
					<div class="tab_cont2 tab_cont">
						<div class="question_conts question_common2">
							<div class="q_top_section" id="qTopHeight">
								<h2 class="q_number"><c:out value="${data.currentQuestionViewNumber }"/>.</h2>
								<div class="q_solve_top">
									<p class="q">
										<c:out value="${data.subName }"/> <c:out value="${data.levName }"/>. <c:out value="${data.acvName }"/>
										<!-- 테스트 후 삭제 -->
										<br/><b style="color: red;">문제분류 : ${data.acaName }, 답 : ${data.qstValue }, 문항 키 : ${data.qstKey } (관리자 확인/검수용)</b>
										<!-- 테스트 후 삭제 -->
									</p>
									<div class="tag_div">
										<c:if test="${not empty concept }" >
										<c:forEach var="row" items="${concept }" >
										<c:set var="arrCptName" value="${fn:split(row.notData, '|') }" />
										<a href="#" class="tag"><c:out value="${arrCptName[1]}"/></a>
										</c:forEach>
										</c:if>
									</div>
<!-- 									<div class="ov_h posiA"> -->
<%-- 										<div class="aigo_rate"><span class="logo_img">AIGo</span><span class="txt">정답률</span> <em><c:out value="${data.passPer }"/>%</em><!-- 수정0219 --></div> --%>
<%-- 										<c:choose> --%>
<%-- 											<c:when test="${data.qstPreType eq 'S' }"> --%>
<%-- 												<c:choose> --%>
<%-- 												<c:when test="${data.qstPreS01 <= data.passPer and data.passPer <= data.qstPreE01 }"><c:set var="difficulty" value="A"/></c:when> --%>
<%-- 												<c:when test="${data.qstPreS02 <= data.passPer and data.passPer <= data.qstPreE02 }"><c:set var="difficulty" value="B"/></c:when> --%>
<%-- 												<c:when test="${data.qstPreS03 <= data.passPer and data.passPer <= data.qstPreE03 }"><c:set var="difficulty" value="C"/></c:when> --%>
<%-- 												<c:when test="${data.qstPreS04 <= data.passPer and data.passPer <= data.qstPreE04 }"><c:set var="difficulty" value="D"/></c:when> --%>
<%-- 												<c:when test="${data.qstPreS05 <= data.passPer and data.passPer <= data.qstPreE05 }"><c:set var="difficulty" value="E"/></c:when> --%>
<%-- 												<c:otherwise><c:set var="difficulty" value="E"/></c:otherwise> --%>
<%-- 												</c:choose> --%>
<%-- 											</c:when> --%>
<%-- 											<c:otherwise> --%>
<%-- 												<c:choose> --%>
<%-- 												<c:when test="${0 <= data.passPer and data.passPer <= 25 }"><c:set var="difficulty" value="A"/></c:when> --%>
<%-- 												<c:when test="${26 <= data.passPer and data.passPer <= 50 }"><c:set var="difficulty" value="B"/></c:when> --%>
<%-- 												<c:when test="${51 <= data.passPer and data.passPer <= 75 }"><c:set var="difficulty" value="C"/></c:when> --%>
<%-- 												<c:when test="${76 <= data.passPer and data.passPer <= 90 }"><c:set var="difficulty" value="D"/></c:when> --%>
<%-- 												<c:when test="${91 <= data.passPer and data.passPer <= 100 }"><c:set var="difficulty" value="E"/></c:when> --%>
<%-- 												<c:otherwise><c:set var="difficulty" value="E"/></c:otherwise> --%>
<%-- 												</c:choose> --%>
<%-- 											</c:otherwise> --%>
<%-- 										</c:choose> --%>
<%-- 										<div><span>난이도</span><span class="d_E"><em><c:out value="${difficulty }"/></em></span></div> --%>
<!-- 									</div> -->
								</div>
								<div class="answer_div">
									<div class="answer">
										<span class="userAnswerArea">입력: -</span>
										<em>정답: <c:out value="${data.qstValue }"/></em>
									</div>
								</div>
							</div>
							<div class="q_section" style="top: 220px;">
								<div class="q_img_div">
									<c:choose>
										<c:when test="${commentary.cmtrType eq 'P' }">
											<c:if test="${not empty commentary.cmtrValue }">
												<div class="video-container">
													<iframe width="560" height="315" src="https://www.youtube.com/embed/<c:out value="${commentary.cmtrPlayPath }"/>?modestbranding=1&controls=0&showinfo=0&rel=0&iv_load_policy=3&fs=0" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen> </iframe>
												</div>
											</c:if>
										</c:when>
										<c:when test="${commentary.cmtrType eq 'I' }">
											<c:if test="${not empty commentary.fileList }">
												<c:forEach var="row" items="${commentary.fileList }">
													<img src="/common/siteImgView?safSeq=<c:out value="${row.safSeq }"/>"/> 
												</c:forEach>
											</c:if>
										</c:when>
										<c:when test="${commentary.cmtrType eq 'T' }">
											<c:out value="${commentary.cmtrText }" escapeXml="false" />
										</c:when>
										<c:otherwise></c:otherwise>
									</c:choose>
								</div>
								<div class="btm_btn_div2">
									<a href="#" class="next btnNext" onclick="goNext();"><span>다음</span></a>
								</div>
							</div>
						</div>
					</div>
					<%-- 개념 --%>
					<div class="tab_cont3 tab_cont">
						<div class="question_conts question_common2">
							<div class="q_top_section" id="qTopHeight">
								<h2 class="q_number"><c:out value="${data.currentQuestionViewNumber }"/>.</h2>
								<div class="q_solve_top">
									<p class="q">
										<c:out value="${data.subName }"/> <c:out value="${data.levName }"/>. <c:out value="${data.acvName }"/>
										<!-- 테스트 후 삭제 -->
										<br/><b style="color: red;">문제분류 : ${data.acaName }, 답 : ${data.qstValue }, 문항 키 : ${data.qstKey } (관리자 확인/검수용)</b>
										<!-- 테스트 후 삭제 -->
									</p>
									<div class="tag_div">
										<c:if test="${not empty concept }" >
										<c:forEach var="row" items="${concept }" >
										<c:set var="arrCptName" value="${fn:split(row.notData, '|') }" />
										<a href="#" class="tag"><c:out value="${arrCptName[1]}"/></a>
										</c:forEach>
										</c:if>
									</div>
<!-- 									<div class="ov_h posiA"> -->
<%-- 										<div class="aigo_rate"><span class="logo_img">AIGo</span><span class="txt">정답률</span> <em><c:out value="${data.passPer }"/>%</em><!-- 수정0219 --></div> --%>
<%-- 										<c:choose> --%>
<%-- 											<c:when test="${data.qstPreType eq 'S' }"> --%>
<%-- 												<c:choose> --%>
<%-- 												<c:when test="${data.qstPreS01 <= data.passPer and data.passPer <= data.qstPreE01 }"><c:set var="difficulty" value="A"/></c:when> --%>
<%-- 												<c:when test="${data.qstPreS02 <= data.passPer and data.passPer <= data.qstPreE02 }"><c:set var="difficulty" value="B"/></c:when> --%>
<%-- 												<c:when test="${data.qstPreS03 <= data.passPer and data.passPer <= data.qstPreE03 }"><c:set var="difficulty" value="C"/></c:when> --%>
<%-- 												<c:when test="${data.qstPreS04 <= data.passPer and data.passPer <= data.qstPreE04 }"><c:set var="difficulty" value="D"/></c:when> --%>
<%-- 												<c:when test="${data.qstPreS05 <= data.passPer and data.passPer <= data.qstPreE05 }"><c:set var="difficulty" value="E"/></c:when> --%>
<%-- 												<c:otherwise><c:set var="difficulty" value="E"/></c:otherwise> --%>
<%-- 												</c:choose> --%>
<%-- 											</c:when> --%>
<%-- 											<c:otherwise> --%>
<%-- 												<c:choose> --%>
<%-- 												<c:when test="${0 <= data.passPer and data.passPer <= 25 }"><c:set var="difficulty" value="A"/></c:when> --%>
<%-- 												<c:when test="${26 <= data.passPer and data.passPer <= 50 }"><c:set var="difficulty" value="B"/></c:when> --%>
<%-- 												<c:when test="${51 <= data.passPer and data.passPer <= 75 }"><c:set var="difficulty" value="C"/></c:when> --%>
<%-- 												<c:when test="${76 <= data.passPer and data.passPer <= 90 }"><c:set var="difficulty" value="D"/></c:when> --%>
<%-- 												<c:when test="${91 <= data.passPer and data.passPer <= 100 }"><c:set var="difficulty" value="E"/></c:when> --%>
<%-- 												<c:otherwise><c:set var="difficulty" value="E"/></c:otherwise> --%>
<%-- 												</c:choose> --%>
<%-- 											</c:otherwise> --%>
<%-- 										</c:choose> --%>
<%-- 										<div><span>난이도</span><span class="d_E"><em><c:out value="${difficulty }"/></em></span></div> --%>
<!-- 									</div> -->
								</div>
								<div class="answer_div">
									<div class="answer">
										<span class="userAnswerArea">입력: -</span>
										<em>정답: <c:out value="${data.qstValue }"/></em>
									</div>
								</div>
							</div>
							<div class="q_section" style="top: 220px;">
								<div class="q_img_div">
									<c:if test="${not empty notionList }">
										<c:forEach var="notion" items="${notionList }">
											<c:out value="${notion.cptName }"/>
											<br/>
											<c:choose>
												<c:when test="${notion.notType eq 'P' }">
													<c:if test="${not empty notion.notPlayPath }">
														<div class="video-container">
															<iframe width="560" height="315" src="https://www.youtube.com/embed/<c:out value="${notion.notPlayPath }"/>?modestbranding=1&controls=0&showinfo=0&rel=0&iv_load_policy=3&fs=0" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen> </iframe>
														</div>
													</c:if>
												</c:when>
												<c:when test="${notion.notType eq 'I' }">
													<c:if test="${not empty notion.fileList }">
														<c:forEach var="row" items="${notion.fileList }">
															<img src="/common/siteImgView?safSeq=<c:out value="${row.safSeq }"/>"/> 
														</c:forEach>
													</c:if>
												</c:when>
												<c:when test="${notion.notType eq 'T' }">
													<c:out value="${notion.notText }" escapeXml="false" />
												</c:when>
												<c:otherwise></c:otherwise>
											</c:choose>
										</c:forEach>
									</c:if>
								</div>
								<div class="btm_btn_div2">
									<a href="#" class="next btnNext" onclick="goNext();"><span>다음</span></a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<section class="progress_cont beforeAnswerArea">
				<%-- 해당 성취기준의 학습진행률 (문제풀이) --%>
				<c:if test="${not empty learnProgress }">
				<c:set var="totalQstCnt" value="0" />
				<c:set var="ansQstCnt" value="0" />
				<c:set var="currQstNum" value="1" />
				<c:forEach var="row" items="${learnProgress }">
					<c:set var="totalQstCnt" value="${totalQstCnt+1}" />
				<c:if test="${row.qstId eq data.qstId }">
					<c:set var="currQstNum" value="${row.rownum }" />
				</c:if>
				<c:if test="${not empty row.ansValue }">
					<c:set var="ansQstCnt" value="${ansQstCnt+1}" />
				</c:if>
				</c:forEach>
				<div class="progress_div">
					<span class="bar" style="width:<fmt:formatNumber value="${ansQstCnt/totalQstCnt*100}" pattern="#,###" />%"><span class="text"><c:out value="${totalQstCnt }"/>문제 중 <fmt:formatNumber value="${currQstNum}" pattern="#,###" />번</span></span>
					<span class="txt_right" style="width:<fmt:formatNumber value="${100-(ansQstCnt/totalQstCnt*100)}" pattern="#,###" />%">학습율 <fmt:formatNumber value="${ansQstCnt/totalQstCnt*100}" pattern="#,###" />% </span>
				</div>
				</c:if>
				<div class="expect_div div2">
					<dl class="fc">
						<dt>나의 풀이 시간</dt>
						<dd class="myTime">00분 00초</dd>
					</dl>
					<dl>
						<dt>권장 풀이 시간</dt>
						<dd>
							<c:choose>
							<c:when test="${not empty data.qstAddEtc07 }">
								<c:set var="arrTime" value="${fn:split(data.qstAddEtc07,':')}" />
								<c:out value="${arrTime[0]}분 ${arrTime[1]}초"/>
							</c:when>
							<c:otherwise>05분 00초</c:otherwise>
							</c:choose>
						</dd>
					</dl>
				</div>
			</section>
			<section class="answer_submit_cont beforeAnswerArea">
				<c:choose>
					<c:when test="${data.qstType eq 'S' }">
					<%-- 객관식 답 입력 --%>
					<div class="number_div">
						<c:forEach begin="1" end="${data.qstTypeNum }" var="num">
						<button type="button" onclick="fnChooseAnswer('<c:out value="${num }" />');"><c:out value="${num }" /></button>
						</c:forEach>
					</div>
					</c:when>
					<c:otherwise>
					<%-- 주관식 답 입력 --%>
					<div><input type="text" name="" class="ipt_answer shortAnswer" placeholder="정답을 입력하세요" maxlength="30"></div>
					</c:otherwise>
				</c:choose>
				<button type="button" class="btn btn_submit disabled" onclick="fnSendAnswer();">답안제출</button>
			</section>
		</div><!-- //container -->
	</div>
</body>

<%-- 답안 채점 Form --%>
<form id="answerform" name="answerform" method="post">
	<input type="hidden" name="qstId" value="<c:out value="${data.qstId}"/>" />
	<input type="hidden" name="ansValue" value="" />
	<input type="hidden" name="qstOrder" value="<c:out value="${data.currentQuestionNumber}"/>" />
	<input type="hidden" name="ansOrder" value="<c:out value="${data.currentQuestionViewNumber}"/>" />
	<input type="hidden" name="passYn" value="" /> <%-- 채점 결과를 리턴받을 때에만 쓰임 --%>
	<input type="hidden" name="qstValue" value="" /> <%-- 채점 결과를 리턴받을 때에만 쓰임 --%>
	<input type="hidden" name="durTime" value="" />
	<input type="hidden" name="subId" value="<c:out value="${data.subId}"/>" /> <%-- 학습 완료 체크할 때 사용 --%>
	<input type="hidden" name="acvId" value="<c:out value="${data.acvId}"/>" /> <%-- 학습 완료 체크할 때 사용 --%>
	<input type="hidden" name="levId" value="<c:out value="${data.levId}"/>" /> <%-- 학습 완료 체크할 때 사용 --%>
	<input type="hidden" name="currentTestType" value="<c:out value="${empty data.currentTestType? 'prev' :  data.currentTestType}"/>" /> <%-- 풀고있는 평가종류 / 틀렸을 경우 유사문제 출제를 위해 사용 --%>
	<input type="hidden" name="currentTestTypeSub" value="<c:out value="${empty data.currentTestTypeSub? 'basic' :  data.currentTestTypeSub}"/>" /> <%-- 풀고있는 평가종류 / 틀렸을 경우 유사문제 출제를 위해 사용 --%>
</form>
<%-- 다음 출제문제 이동 Form --%>
<form id="form" name="form" method="post">
	<input type="hidden" name="subId" value="<c:out value="${data.subId}"/>" />
	<input type="hidden" name="acvId" value="<c:out value="${data.acvId}"/>" />
	<input type="hidden" name="levId" value="<c:out value="${data.levId}"/>" />
	<input type="hidden" name="currentQuestionViewNumber" value="<c:out value="${data.currentQuestionViewNumber}"/>" />
	<input type="hidden" name="passYn" value="" />
	<input type="hidden" name="currentTestType" value="<c:out value="${empty data.currentTestType? 'prev' :  data.currentTestType}"/>" /> <%-- 풀고있는 평가종류 / 틀렸을 경우 유사문제 출제를 위해 사용 --%>
	<input type="hidden" name="currentTestTypeSub" value="<c:out value="${empty data.currentTestTypeSub? 'basic' :  data.currentTestTypeSub}"/>" /> <%-- 풀고있는 평가종류 / 틀렸을 경우 유사문제 출제를 위해 사용 --%>
</form>

<script type="text/javascript">

var $form = $("#form");
var $answerform = $("#answerform");
var $beforeAnswerArea = $(".beforeAnswerArea");
var $afterAnswerTabArea = $(".afterAnswerTabArea");
var $afterAnswerArea = $(".afterAnswerArea");
var $container = $(".container");

qTabMenu();

$(function(){
	$(".progress_div > .bar").each(function() {
		$(this).data("origWidth", $(this).width()).width(0).animate({
				width: $(this).data("origWidth")
		}, 1200);
	});
});	

$(document).ready(function(){
	
	timer();
	
	$('.number_div button').on('click', function(){
		$('.number_div button').removeClass('on');
		$(this).addClass('on');
		$('.btn_submit').removeClass('disabled');
		// 잘모르겠어요 버튼 처리
// 		$('.dontknow a').removeClass('on');
	});

	$('.shortAnswer').on('keyup', function(){
		var $this = $(this);
		$answerform.find("input[name='ansValue']").val($this.val());
		if($this.val() != ''){
			$('.btn_submit').removeClass('disabled');
		}else{
			$('.btn_submit').addClass('disabled');
		}
		// 잘모르겠어요 버튼 처리
// 		$('.dontknow a').removeClass('on');
	});

	$('#set1').on('click', function(){
		$('.fc1').toggle();
	});
	
	$('.dontknow a').on('click', function(){
		$('.btn_submit').removeClass('disabled');
		// 잘모르겠어요 버튼 처리
// 		$(this).addClass('on');
		$('.number_div button').removeClass('on');
		$('.shortAnswer').val("");
	});
// 	markingAnswer();
});

// 사용자 답 선택
function fnChooseAnswer(num){
	if(num == ''){
		$answerform.find("input[name='ansValue']").val('unknown');
	}else{
		$answerform.find("input[name='ansValue']").val(num);
	}
}

// 답안제출
function fnSendAnswer(){
	var param = $answerform.domJson();
	if($answerform.find("input[name='ansValue']").val() != '' ){
		$.call('/front/ajax/learn/sendAnswerLearn', param, function(r){
			$answerform.find("input[name='passYn']").val(r.resultData.resultStatus);
			$form.find("input[name='passYn']").val(r.resultData.resultStatus);
			$answerform.find("input[name='currentTestTypeSub']").val(r.resultData.pageTypeSub);
			$answerform.find("input[name='currentTestType']").val(r.resultData.pageType);
			$form.find("input[name='currentTestTypeSub']").val(r.resultData.pageTypeSub);
			$form.find("input[name='currentTestType']").val(r.resultData.pageType);
			markingAnswer();
			if(r.resultData.resultCode == "S03"){
				isFinish = true;
			}
		});
	}
}

// 답안 제출 시 채점액션
function markingAnswer(){
	var $qstValue = $answerform.find("input[name='qstValue']").val();
	var $ansValue = $answerform.find("input[name='ansValue']").val();
	var $passYn = $answerform.find("input[name='passYn']").val();
	var $passArea = $('.passArea');
	var $markArea = $('.markArea');
	$passArea.removeClass('ico_O').removeClass('ico_X');
	if($passYn == 'y'){
		$passArea.addClass('ico_O');
		$('.question_conts').css({'top' : '0'});
	}else{
		$afterAnswerTabArea.show();
		$passArea.addClass('ico_X');
		$markArea.eq(Number($ansValue)-1).addClass('ico_X');
		$('.question_conts').removeClass('question_common2');
	}
	if($ansValue == 'unknown'){
		$('.userAnswerArea').text('입력: 잘 모르겠어요');
	}else if($ansValue == ''){
		$('.userAnswerArea').text('입력: -');
	}else{
		$('.userAnswerArea').text('입력: '+$ansValue);
	}
	$('.tab_cont1 .question_conts').removeClass('question_common2');
	$container.addClass('q_solving');
	$beforeAnswerArea.hide();
	$afterAnswerArea.show();
}

var isFinish = false;
// 다음문제 버튼
function goNext(){
	var $currentTestTypeSub = $form.find("input[name='currentTestTypeSub']");
	var $popup = $(".popupConfirm");
	if(isFinish && $form.find("input[name='currentTestTypeSub']").val() == 'basic'){
		commonModalPopup('<h3>${data.acvName}</h2><br/>해당하는 모든 문제를 풀었습니다.<br/>Aigo 맞춤 주간평가를 통해<br/>성취 진단을 진행합니다.');
		$popup.text("AI 주간평가 Go");
		$popup.click({}, goWeeklyTest);
	}else if( ($currentTestTypeSub.val() == 'similar' || $currentTestTypeSub.val() == 'similarSub')){
		commonModalPopup('방금 틀렸던 문제와 유사한 문제를<br/>다시 한 번 풀어보세요.');
		$popup.click({}, goNextQuestion);
	}else{
		goNextQuestion();
	}
}
function goNextQuestion(){
	$form.attr("action","/front/learn/question").submit();
}

// 주간평가 페이지 이동
function goWeeklyTest(){
	$form.find("input[name='currentQuestionViewNumber']").val("0");
	$form.attr("action","/front/learn/weeklyTest").submit();
}

// 풀이시간 표시
var time = 0;
var starFlag = true;
function initTimer(){
	$('.myTime').text('00분 00초');
}
function timer(){
	var hour = 0;
	var min = 0;
	var sec = 0;
	var timer;

	if(starFlag){
		starFlag = false;
		
		if(time == 0){ initTimer(); }
		
		timer = setInterval(function(){
			time++;
			min = Math.floor(time/60);
			hour = Math.floor(min/60);
			sec = time%60;
			min = min%60;
			
			var th = hour;
			var tm = min;
			var ts = sec;
			
			if(th < 10){ th = "0" + hour; }
			if(tm < 10){ tm = "0" + min; }
			if(ts < 10){ ts = "0" + sec; }
			
			$answerform.find("input[name='durTime']").val(time);
			$('.myTime').text(tm+'분 '+ts+'초');
			
		}, 1000);
	}
}

</script>