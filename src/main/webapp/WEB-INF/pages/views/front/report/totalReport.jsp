<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@ page import="java.util.Enumeration"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>

<script>
	MathJax = {
		tex : {
			inlineMath : [ [ '$', '$' ], [ '\\(', '\\)' ] ]
		}
	};
</script>

<script id="MathJax-script" async src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-chtml.js"></script>

<style type="text/css">
.chart_div {
	position: relative;
}

.my_label {
	width: auto;
	padding-left: 5px;
	padding-right: 5px;
	width: 50px;
	margin-left: -25px;
}

.my_posi_box.my_label .bg {
	left: 50%;
}

#chart {
	
}
</style>

<script src="https://cdn.jsdelivr.net/npm/react@16.12/umd/react.production.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/react-dom@16.12/umd/react-dom.production.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/prop-types@15.7.2/prop-types.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-core/5.8.34/browser.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
<script src="https://cdn.jsdelivr.net/npm/react-apexcharts@1.3.6/dist/react-apexcharts.iife.min.js"></script>

<c:set var="requestParam" value="<%=new ObjectMapper().writeValueAsString(request.getParameterMap())%>" />
<%-- <c:set var="a" value="${resultMap.resultData.acvInfo }" /> --%>
<%-- <c:set var="report" value="${resultMap.resultData.totalReport }" /> --%>
<c:set var="ut" value="${resultMap.resultData.userUnitTrue }" />
<c:set var="uf" value="${resultMap.resultData.userUnitFalse }" />
<c:set var="at" value="${resultMap.resultData.allUnitTrue }" />
<c:set var="af" value="${resultMap.resultData.allUnitFalse }" />
<c:set var="totalInfoTopList" value="${resultMap.resultData.totalReportTmp }" />
<c:set var="totalDurChart" value="${resultMap.resultData.totalDurChart }" />
<c:set var="totalDurChartTotalMonth" value="${resultMap.resultData.totalDurChart.chartMonth }" />
<c:set var="totalDurChartTotalTime" value="${resultMap.resultData.totalDurChart.totalDurTime }" />
<c:set var="totalDurChartTotalStr" value="${resultMap.resultData.totalDurChart.chartSysdate }" />
<c:set var="totalChartMonthDAY" value="${resultMap.resultData.totalChartMonth.chartDay }" />
<c:set var="totalChartMonthTEST" value="${resultMap.resultData.totalChartMonth.totalTestCnt }" />
<c:set var="totalChartMonthTRUE" value="${resultMap.resultData.totalChartMonth.totalTrueCnt }" />
<c:set var="totalChartMonthStr" value="${resultMap.resultData.totalChartMonth.chartSysdate }" />
<c:set var="totalChartOneMonthDAY" value="${resultMap.resultData.totalChartOneMonth.chartDay }" />
<c:set var="totalChartOneMonthTEST" value="${resultMap.resultData.totalChartOneMonth.totalTestCnt }" />
<c:set var="totalChartOneMonthTRUE" value="${resultMap.resultData.totalChartOneMonth.totalTrueCnt }" />
<c:set var="totalChartOneMonthStr" value="${resultMap.resultData.totalChartOneMonth.chartSysdate }" />
<c:set var="totalChartWeekDAY" value="${resultMap.resultData.totalChartWeek.chartDay }" />
<c:set var="totalChartWeekTEST" value="${resultMap.resultData.totalChartWeek.totalTestCnt }" />
<c:set var="totalChartWeekTRUE" value="${resultMap.resultData.totalChartWeek.totalTrueCnt }" />
<c:set var="totalChartWeekStr" value="${resultMap.resultData.totalChartWeek.chartSysdate }" />
<c:set var="totalDayOfWeekCnt" value="${resultMap.resultData.totalDayOfWeek.totalDayOfWeekCnt }" />
<c:set var="totalDayOfWeekStr" value="${resultMap.resultData.totalDayOfWeek.totalDayOfWeekStr }" />
<c:set var="totalClockOfDayCnt" value="${resultMap.resultData.totalClockOfDay.totalClockCnt }" />
<c:set var="totalClockOfDayStr" value="${resultMap.resultData.totalClockOfDay.totalClockStr }" />
<c:set var="testCnts" value="${resultMap.resultData.allTestCnt }" />
<c:set var="weekCnt" value="${resultMap.resultData.weekCnt }" />
<c:set var="levCnt" value="${resultMap.resultData.levCnt }" />
<c:forEach var="t" items="${resultMap.resultData.totalReportTmp }" varStatus="vs">
	<c:if test="${t.memId ne null }">
		<c:set var="totalInfoTop" value="${resultMap.resultData.totalReportTmp[vs.index] }" />
	</c:if>
</c:forEach>

<%
	Enumeration<String> attrNames = request.getAttributeNames();
%>
<%--${report}--%>
<body class="report_body">
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1 class="title">
				<span class="txt_AI">AI</span> 종합 리포트
			</h1>
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
					<span><c:out value="${userInfo.className}"/> </span><span class="bar">최초 레벨 <c:out value="${totalInfoTop.firstLev }"/></span> <span class="fc1">현재 레벨 <c:out value="${userInfo.memLevel }"/></span>
				</div>
			</div>
		</header>

		<div class="container report">
			<section class="contents">
				<p class="tit">최초 등급 <c:out value="${userInfo.tempGrade }"/>등급</p>
				<!-- 수정0219 -->
				<p class="txt_st3">현재까지의 성취도 예상 등급 <c:out value="${resultMap.resultData.userAchieveData.myGrade}" />등급</p>
				<!-- 수정0219 -->
				<div id="app">
					<div id="html">
						<div id="chart"></div>
					</div>
				</div>


				<script type="text/babel">
			class ApexChart extends React.Component {
				constructor(props) {
					super(props);
					this.state = {

						series: [{
							data: [${totalInfoTopList[0].levAvg}
								,${totalInfoTopList[1].levAvg}
								,${totalInfoTopList[2].levAvg}
								,${totalInfoTopList[3].levAvg}
								,${totalInfoTopList[4].levAvg}]
						}],
						options: {
							chart: {
								height: 150,
								type: 'line',
								toolbar: { 
									show: false
								},
								zoom: {
									enabled: false,
								},
							},
							colors: ['#3b70db'],
							annotations: {
								points: [{
									x: ${totalInfoTop.memLevel},
									y: ${totalInfoTop.levAvg},
									marker: {
										size: 4,
										fillColor: '#3b70db',
										strokeColor:'#3b70db',
										radius: 2,
										cssClass: 'apexcharts-custom-class'
									},
									label: {
										borderColor: '#102658',
										offsetY: 0,
										style: {
											color: '#fff',
											background: '#102658',
											padding: {
												left: 5,
												right: 5,
												top: 7,
												bottom: 5,
											}
										},
										text: '나의 레벨',
									}
								}]
							},
							dataLabels: {
								enabled: false
							},
							stroke: {
								curve: 'smooth',
								width: 1
							},
							labels: [1, 2, 3, 4, 5],
							grid: {
								padding: {
									right: 30,
									left: 20
								}
							},
							xaxis: {
								tickAmount: 5,
								axisBorder: {
									show: true,
									color: '#ddd',
								},
							},
							yaxis: {
								tickAmount: 4,
								min: 1,
								max: 5,
								labels: {
									show: false,
								},
								axisBorder: {
									show: true,
									color: '#ddd',
									offsetX: 7,
									offsetY: 0
								},
							},
							tooltip: {
								enabled: false,
							},
						},
					
					
					};
				}
				render() {
					return (
						<div>
							<div id="chart">
								<ReactApexChart options={this.state.options} series={this.state.series} type="line" height={200} />
							</div>
							<div id="html-dist"></div>
						</div>
					);
				}
			}

			const domContainer = document.querySelector('#app');
			ReactDOM.render(React.createElement(ApexChart), domContainer);
		</script>


			</section>

			<section class="contents study_q_cont">
				<div class="div1">
					<div class="left">
						<p>
							<strong><c:out value="${totalInfoTop.allTestDay}"/></strong> <span>일 째</span>
						</p>
						<span>LD 학습 중</span>
					</div>
					<div class="right bg1">
						<span>누적학습시간</span>
						<p>
							<c:set var="allDurHour" value="${fn:split(totalInfoTop.allDurHour, ':') }"/>
							<strong><c:out value="${allDurHour[0]}"/></strong><span>시간</span> <strong><c:out value="${allDurHour[1]}"/></strong><span>분</span>
						</p>
					</div>
				</div>
				<!-- 수정0219 -->
				<div class="div2">
					<div class="left">
						<p>
							<span class="tit">최초 학습일</span><span><c:out value="${totalInfoTop.firstTestDt}"/></span>
						</p>
						<p>
							<span class="tit">최근 학습일</span><span><c:out value="${totalInfoTop.lastTestDt}"/></span>
						</p>
					</div>
					<div class="right">
						<p>
							<c:set var="extMonthHour" value="${fn:split(totalInfoTop.extMonthHour, ':') }"/>
							<span class="tit">전월 대비시간</span><span class="<c:if test="${!fn:contains(totalInfoTop.extMonthHour, '-') && (extMonthHour[0] ne '00' || extMonthHour[1] ne '00' || extMonthHour[2] ne '00') }">increase</c:if>"><c:out value="${extMonthHour[0]}"/>시간 <c:out value="${extMonthHour[1]}"/>분</span>
						</p>
						<p>
							<c:set var="extDayHour" value="${fn:split(totalInfoTop.extDayHour, ':') }"/>
							<span class="tit">전일 대비시간</span><span class="<c:if test="${!fn:contains(totalInfoTop.extDayHour, '-') && (extDayHour[0] ne '00' || extDayHour[1] ne '00' || extDayHour[2] ne '00') }">increase</c:if>"><c:out value="${extDayHour[0]}"/>시간 <c:out value="${extDayHour[1]}"/>분</span>
						</p>
					</div>
				</div>
				<!-- //수정0219 -->
			</section>

			<section class="contents">
				<h2>누적 학습시간 추이</h2>
				<div class="chart_div mt30">
					<!-- 수정0219 -->
					<canvas id="chartLine2" style="width: 100%"></canvas>
				</div>
			</section>

			<section class="contents study_q_cont">
				<div class="div1">
					<div class="left bg1">
						<span>맞춘 문항수</span>
						<p>
							<strong><c:out value="${testCnts.trueCnt}"/></strong>
						</p>
					</div>
					<div class="right">
						<span>전체풀이 문항수</span>
						<p>
							<strong><c:out value="${testCnts.testCnt}"/></strong>
						</p>
					</div>
				</div>
			</section>

			<section class="contents">
				<h2>정답율 추이</h2>
				<div class="tab_wrap mt35">
					<!-- 수정0219 -->
					<div class="tab_div tab3">
						<a href="#" class="tab_a1 tab_a active" type="button" onclick="rctx(1); return false;">6개월</a> <a href="#" class="tab_a2 tab_a" type="button" onclick="rctx(2); return false;">30일</a> <a href="#" class="tab_a3 tab_a" type="button" onclick="rctx(3); return false;">7일</a>
					</div>
				</div>
				<div class="tab_cont_wrap">
					<div class="tab_cont1 tab_cont active">
						<div class="chart_div">
							<canvas id="rateChart1" style="width: 100%"></canvas>
						</div>
					</div>
					<div class="tab_cont2 tab_cont">
						<div class="chart_div">
							<canvas id="rateChart2" style="width: 100%"></canvas>
						</div>
					</div>
					<div class="tab_cont3 tab_cont">
						<div class="chart_div">
							<canvas id="rateChart3" style="width: 100%"></canvas>
						</div>
					</div>
			</section>
			
			
			<section class="contents study_pattern">
				<h2>나의 학습 패턴</h2>
				<div class="div_wrap">
					<div class="left">
						<div class="chart_div pattern_chart">
							<div>
								<canvas id="myChart1" width="110" height="110"></canvas>
							</div>
							<ul>
								<li><i class="dot dot1"></i><span>월</span></li>
								<li><i class="dot dot2"></i><span>화</span></li>
								<li><i class="dot dot3"></i><span>수</span></li>
								<li><i class="dot dot4"></i><span>목</span></li>
								<li><i class="dot dot5"></i><span>금</span></li>
								<li><i class="dot dot6"></i><span>토</span></li>
								<li><i class="dot dot7"></i><span>일</span></li>
							</ul>
						</div>
					</div>
					<div class="right">
						<div class="chart_div pattern_chart">
							<div>
								<canvas id="myChart2" width="110" height="110"></canvas>
							</div>
							<ul>
								<li><i class="dot dot1"></i><span>아침</span></li>
								<li><i class="dot dot2"></i><span>오전</span></li>
								<li><i class="dot dot3"></i><span>점심</span></li>
								<li><i class="dot dot4"></i><span>오후</span></li>
								<li><i class="dot dot5"></i><span>저녁</span></li>
								<li><i class="dot dot6"></i><span>밤</span></li>
								<li><i class="dot dot7"></i><span>새벽</span></li>
							</ul>
						</div>
					</div>
				</div>
			</section>
			

			<section class="contents2">
				<h2>문항별 분석</h2>
				<button class="btn btn_st3 mb15" <c:if test="${weekCnt <= 0 }">disabled="disabled"</c:if> onclick="location.href ='/front/report/totalReportWeek';">주간학습 상세 문항분석</button>
				<button class="btn btn_st3" <c:if test="${levCnt <= 0 }">disabled="disabled"</c:if> onclick="location.href ='/front/report/totalReportLevel?levId=${totalInfoTop.levId}';">레벨평가 상세 문항분석</button>
			</section>

			<section class="contents unit_conts">
				<h2 class="mbx">
					<c:choose>
						<c:when test="${!empty userInfo.memNickname }">
							<c:out value="${userInfo.memNickname }" />
						</c:when>
						<c:otherwise>
							<c:out value="${userInfo.memEmail }" />
						</c:otherwise>
					</c:choose>
					님 <br>강점 단원
				</h2>
				<div class="unit_card_slide">
					<c:if test="${!empty ut }">
						<c:forEach var="u" items="${ut }" varStatus="vs">
							<div class="unit_card">
								<div class="div">
									<i class="ico_math<c:out value="${u.unitStyle }"/>"></i> <span class="txt"><c:out value="${u.unitName }"/></span>
								</div>
								<div class="btm">
									<span>정답율 <em><fmt:formatNumber value="${u.userPer }" type="percent" /></em></span>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>

				<h3>AIGo 경쟁자들의 강점 단원</h3>
				<div class="unit_st_div">
					<c:if test="${!empty at }">
						<c:forEach var="a" items="${at }" varStatus="vs">
							<div class="div">
								<p class="tit fL">
									<i class="ico_math<c:out value="${a.unitStyle }"/>"></i><span><c:out value="${a.unitName }"/></span>
								</p>
								<span class="txt fR">정답율 <em><fmt:formatNumber value="${a.allPer }" type="percent" /></em></span>
							</div>
						</c:forEach>
					</c:if>
				</div>
			</section>

			<section class="contents unit_conts">
				<h2 class="mbx">
					<c:choose>
						<c:when test="${!empty userInfo.memNickname }">
							<c:out value="${userInfo.memNickname }" />
						</c:when>
						<c:otherwise>
							<c:out value="${userInfo.memEmail }" />
						</c:otherwise>
					</c:choose>
					님 <br>취약 단원
				</h2>
				<div class="unit_card_slide">
					<c:if test="${!empty uf }">
						<c:forEach var="u" items="${uf }" varStatus="vs">
							<div class="unit_card">
								<div class="div">
									<i class="ico_math<c:out value="${u.unitStyle }"/>"></i> <span class="txt"><c:out value="${u.unitName }"/></span>
								</div>
								<div class="btm">
									<span>정답율 <em><fmt:formatNumber value="${u.userPer }" type="percent" /></em></span>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>

				<h3>AIGo 경쟁자들의 취약 단원</h3>
				<div class="unit_st_div">
					<c:if test="${!empty af }">
						<c:forEach var="a" items="${af }" varStatus="vs">
							<div class="div">
								<p class="tit fL">
									<i class="ico_math<c:out value="${a.unitStyle }"/>"></i><span><c:out value="${a.unitName }"/></span>
								</p>
								<span class="txt fR">정답율 <em><fmt:formatNumber value="${a.allPer }" type="percent" /></em></span>
							</div>
						</c:forEach>
					</c:if>
				</div>
			</section>

		</div>
		<!-- //container -->


	</div>

	<!-- 차트 -->
	<script src="https://www.chartjs.org/dist/2.9.4/Chart.min.js"></script>

	<script src="/assets/plugin/utils.js"></script>
	<script src="/assets/plugin/chartjs-plugin-doughnutlabel.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels"></script>
	<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-deferred"></script>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.6.0/gsap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.6.0/ScrollTrigger.min.js"></script>
	<script src="/assets/plugin/owlcarousel/owl.carousel.min.js"></script>
	<script type="text/javascript">
		aTab();
		unitCardSlider();
	</script>
	<script>
		function animateFrom(elem, direction) {
			direction = direction | 1.5;

			var x = 0, y = direction * 100;

			gsap.fromTo(elem, {
				x : x,
				y : y,
				autoAlpha : 0
			}, {
				duration : 1,
				x : 0,
				y : 0,
				autoAlpha : 1,
				ease : "expo",
				overwrite : "auto"
			});
		}

		function hide(elem) {
			gsap.set(elem, {
				autoAlpha : 0
			});
		}

		document.addEventListener("DOMContentLoaded", function() {
			gsap.registerPlugin(ScrollTrigger);

			gsap.utils.toArray(".chart_div").forEach(function(elem) {
				hide(elem); // assure that the element is hidden when scrolled into view

				ScrollTrigger.create({
					trigger : elem,
					onEnter : function() {
						animateFrom(elem)
					},
					onEnterBack : function() {
						animateFrom(elem, -1)
					},
					onLeave : function() {
						hide(elem)
					} // assure that the element is hidden when scrolled into view
				});
			});
		});

		//누적 학습시간 추이
		var totalMonth = {};
		var totalTime = {};
		var totalStr = {};
		var totalGraph = 0;
		var totalTimeStr = [];
		totalMonth = "${totalDurChartTotalMonth}".split(",");
		totalStr = "${totalDurChartTotalStr}".split(",");
		totalTime = "${totalDurChartTotalTime}".split(",");
		for(i = 0; i < totalTime.length; i++) {
			for(j = 0; j < i+1; j++) {
				totalGraph += Number(totalTime[j]);
			}
			totalTime[i] = totalGraph;
			totalGraph = 0;
			var minute = (Math.floor(totalTime[i] / 60) % 60).toString();
			var second = (totalTime[i] % 60).toString();
			totalTimeStr[i] = ((minute.length == 1) ? "0" + Math.floor(totalTime[i] / 60) % 60 : Math.floor(totalTime[i] / 60) % 60) + "." + ((second.length == 1) ? "0" + totalTime[i] % 60 : totalTime[i] % 60);
		}
		
		var ctxLine2 = document.getElementById('chartLine2');
		var chartLine2 = new Chart(ctxLine2, {
			type : 'line',
			data : {
				datasets : [ {
					backgroundColor : "rgba(59, 112, 219, 0.1)",
					borderColor : "rgba(59, 112, 219, 1)",
					pointBorderColor : "rgba(59, 112, 219, 1)",
					pointBackgroundColor : "rgba(59, 112, 219, 1)",
					data : totalTimeStr, //수정0219
					fill : true,
					lineTension : 0,
				} ],
				labels : totalStr
			},
			options : {
				responsive : true,
				maintainAspectRatio : false,
				legend : {
					display : false
				},
				elements : {
					line : {
						borderWidth : 1
					}
				},
				hover : {
					mode : 'nearest',
					intersect : true
				},
				scales : {
					xAxes : [ {
						display : true,
						gridLines : {
							display : false
						},
						ticks : {
							fontSize : 10,
							fontColor : '#656565'
						},

					} ],
					yAxes : [ {
						display : true,
						ticks : {
							display : false,
							min : Math.min.apply(null, totalTimeStr),
							stepSize : 200,
							max : Math.max.apply(null, totalTimeStr) + (Math.max.apply(null, totalTimeStr) / 5),
							fontSize : 10,
							fontColor : '#656565'//수정0219
						},
						gridLines : {
							drawBorder : false,
							zeroLineColor : '#ddd'//수정0219
						}
					} ]
				},
				animation : {

				},
				tooltips : {
					backgroundColor : '#102658',
					titleFontSize : 12,
					titleFontColor : '#fff',
					bodyFontColor : '#fff',
					bodyFontSize : 10,
					displayColors : false
				},
				plugins : {
					title : {
						display : true,
						text : '나의 레벨'
					},
					tooltip : {
						mode : 'index',
						intersect : false
					},
					deferred : {
						xOffset : 150, // defer until 150px of the canvas width are inside the viewport
						yOffset : '50%', // defer until 50% of the canvas height are inside the viewport
						delay : 500
					// delay of 500 ms after the canvas is considered inside the viewport
					},
					// Change options for ALL labels of THIS CHART
					datalabels : {
						color : '#000',//수정0219
						font : {
							size : 12
						},//수정0219
						align : function(context) {
							var value = context.dataset.data[context.dataIndex];
							return value > 0 ? 'end' : 'end';
						},
						anchor : function(context) {
							var value = context.dataset.data[context.dataIndex];
							return value > 0 ? 'end' : 'start';
						},
						formatter : function(value, context) {
							var splitMin = (value+"");
							splitMin = splitMin.split(".");
							return (splitMin[0] == 0 && typeof splitMin[1] != "undefined") ? Number(splitMin[1]) + '초' : (typeof splitMin[0] != "undefined" && typeof splitMin[1] != "undefined") ? Number(splitMin[0]) + '분' + Number(splitMin[1]) + '초' : 0;
						}
					}
				}
			}
		});
		/*
		function next() {
			var data = chartLine2.data.datasets[0].data;
			var count = data.length;
			data[count] = data[count - 1];
			chartLine2.update({duration: 0});
			data[count] = myData[count];
			chartLine2.update();
			if (count < data.length) {
				setTimeout(next, 500);
			}
		}
		setTimeout(next, 500);
		*/

		//나의학습패턴 차트1
		var dayOfWeekCnt = {};
		var dayOfWeekStr = {};
		var dayOfWeekColor = [];
		var dayStr = "";
		var dayCnt = 0;
		dayOfWeekCnt = "${totalDayOfWeekCnt}".split(",");
		dayOfWeekStr = "${totalDayOfWeekStr}".split(",");
		for (var i = 0; i < dayOfWeekCnt.length; i++) {
			dayCnt += Number(dayOfWeekCnt[i]);
			if (dayOfWeekStr[i] == "월") {
				dayOfWeekColor[i] = $(".dot1").css("background-color");
			} else if (dayOfWeekStr[i] == "화") {
				dayOfWeekColor[i] = $(".dot2").css("background-color");
			} else if (dayOfWeekStr[i] == "수") {
				dayOfWeekColor[i] = $(".dot3").css("background-color");
			} else if (dayOfWeekStr[i] == "목") {
				dayOfWeekColor[i] = $(".dot4").css("background-color");
			} else if (dayOfWeekStr[i] == "금") {
				dayOfWeekColor[i] = $(".dot5").css("background-color");
			} else if (dayOfWeekStr[i] == "토") {
				dayOfWeekColor[i] = $(".dot6").css("background-color");
			} else {
				dayOfWeekColor[i] = $(".dot7").css("background-color");
			}
		}
		for (var i = 0; i < dayOfWeekCnt.length; i++) {
			dayOfWeekCnt[i] = Math.round((Number(dayOfWeekCnt[i]) / dayCnt) * 100);
		}
		for (var i = 0; i < dayOfWeekCnt.length; i++) {
			if (Math.max.apply(null, dayOfWeekCnt) == dayOfWeekCnt[i]) {
				dayStr = dayOfWeekStr[i] + "요일";
			}
		}
		var ctx1 = document.getElementById('myChart1');
		var myChart1 = new Chart(ctx1, {
			type : 'doughnut',
			data : {
				datasets : [ {
					data : dayOfWeekCnt,
					backgroundColor : dayOfWeekColor,
					label : dayOfWeekStr
				} ],
				labels : dayOfWeekStr
			},
			options : {
				//responsive: true,
				maintainAspectRatio : false,
				plugins : {

					doughnutlabel : {
						labels : [ {
							text : dayStr,
							font : {
								size : 12
							},
							color : '#000' //수정0219
						}, {
							text : Math.max.apply(null, dayOfWeekCnt) + "%",
							font : {
								size : 14,
								weight : 'bold'
							},
							color : '#000' //수정0219
						} ]
					},
					deferred : {
						delay : 500
					// delay of 500 ms after the canvas is considered inside the viewport
					},
					datalabels : {
						display : false
					}
				},
				animation : {
					duration : 1000,
					animateScale : false,
					animateRotate : true
				},
				cutoutPercentage : 70,
				legend : {
					display : false
				},
				tooltips : {
					enabled : true,
					mode : 'label',
					callbacks : {
						label : function(tooltipItem, data) {
							var indice = tooltipItem.index;
							return data.datasets[0].data[indice] + "% " + data.labels[indice] + '요일';
						}
					}
				}
			}
		});

		var clockOfDayCnt = {};
		var clockOfDayStr = {};
		var clockOfDayColor = [];
		var clockStr = "";
		var clockCnt = 0;
		clockOfDayCnt = "${totalClockOfDayCnt}".split(",");
		clockOfDayStr = "${totalClockOfDayStr}".split(",");
		for (var i = 0; i < clockOfDayCnt.length; i++) {
			clockCnt += Number(clockOfDayCnt[i]);
			if (clockOfDayStr[i] == "아침") {
				clockOfDayColor[i] = $(".dot1").css("background-color");
			} else if (clockOfDayStr[i] == "오전") {
				clockOfDayColor[i] = $(".dot2").css("background-color");
			} else if (clockOfDayStr[i] == "점심") {
				clockOfDayColor[i] = $(".dot3").css("background-color");
			} else if (clockOfDayStr[i] == "오후") {
				clockOfDayColor[i] = $(".dot4").css("background-color");
			} else if (clockOfDayStr[i] == "저녁") {
				clockOfDayColor[i] = $(".dot5").css("background-color");
			} else if (clockOfDayStr[i] == "밤") {
				clockOfDayColor[i] = $(".dot6").css("background-color");
			} else {
				clockOfDayColor[i] = $(".dot7").css("background-color");
			}
		}
		for (var i = 0; i < clockOfDayCnt.length; i++) {
			clockOfDayCnt[i] = Math.round((Number(clockOfDayCnt[i]) / clockCnt) * 100);
		}
		for (var i = 0; i < clockOfDayCnt.length; i++) {
			if (Math.max.apply(null, clockOfDayCnt) == clockOfDayCnt[i]) {
				clockStr = clockOfDayStr[i] + "시간";
			}
		}

		var ctx2 = document.getElementById('myChart2');
		var myChart1 = new Chart(ctx2, {
			type : 'doughnut',
			data : {
				datasets : [ {
					data : clockOfDayCnt,
					backgroundColor : clockOfDayColor,
					label : clockOfDayStr
				} ],
				labels : clockOfDayStr
			},
			options : {
				//responsive: true,
				maintainAspectRatio : false,

				plugins : {

					doughnutlabel : {
						labels : [ {
							text : clockStr,
							font : {
								size : 12
							},
							color : '#000' //수정0219
						}, {
							text : Math.max.apply(null, clockOfDayCnt) + "%",
							font : {
								size : 14,
								weight : 'bold'
							},
							color : '#000' //수정0219
						} ]
					},
					deferred : {
						delay : 500
					// delay of 500 ms after the canvas is considered inside the viewport
					},
					datalabels : {
						display : false
					}
				},
				animation : {
					duration : 1000,
					animateScale : false,
					animateRotate : true
				},
				cutoutPercentage : 70,
				legend : {
					display : false
				// position:'bottom',
				// align: 'start',
				// labels: {
				//   fontSize:12,
				//   pointStyle:'circle',
				//   usePointStyle:true,
				//   fontSize:10,
				//   padding:10
				// }
				},
				tooltips : {
					enabled : true,
					mode : 'label',
					callbacks : {
						label : function(tooltipItem, data) {
							var indice = tooltipItem.index;
							return data.datasets[0].data[indice] + "% " + data.labels[indice] + '시간';
						}
					}
				}
			}
		});
		/*
		Chart.Legend.prototype.afterFit = function() {
		    this.height = this.height + 50;
		};
		*/
		
		//정답율 차트
		rctx();
		
		function rctx(rctx) {
			var dateStr = {};
			var testCnt = {};
			var trueCnt = {};
			var per = [];
			if (rctx == 1) {
				var rctx1 = document.getElementById('rateChart1');
				// 				dateStr = "${totalChartMonthDAY}".split(",");
				dateStr = "${totalChartMonthStr}".split(",");
				testCnt = "${totalChartMonthTEST}".split(",");
				trueCnt = "${totalChartMonthTRUE}".split(",");
				if(dateStr[0] == 0) {
					commonModalPopup('<b>데이터가 없습니다.</b>');
					return false;
				}
			} else if (rctx == 2) {
				var rctx1 = document.getElementById('rateChart2');
				// 				dateStr = "${totalChartOneMonthDAY}".split(",");
				dateStr = "${totalChartOneMonthStr}".split(",");
				testCnt = "${totalChartOneMonthTEST}".split(",");
				trueCnt = "${totalChartOneMonthTRUE}".split(",");
				if(dateStr[0] == 0) {
					commonModalPopup('<b>데이터가 없습니다.</b>');
					return false;
				}
			} else if (rctx == 3) {
				var rctx1 = document.getElementById('rateChart3');
				// 				dateStr = "${totalChartWeekDAY}".split(",");
				dateStr = "${totalChartWeekStr}".split(",");
				testCnt = "${totalChartWeekTEST}".split(",");
				trueCnt = "${totalChartWeekTRUE}".split(",");
				if(dateStr[0] == 0) {
					commonModalPopup('<b>데이터가 없습니다.</b>');
					return false;
				}
			} else {
				var rctx1 = document.getElementById('rateChart1');
				// 				dateStr = "${totalChartMonthDAY}".split(",");
				dateStr = "${totalChartMonthStr}".split(",");
				testCnt = "${totalChartMonthTEST}".split(",");
				trueCnt = "${totalChartMonthTRUE}".split(",");
				if(dateStr[0] == 0) {
					commonModalPopup('<b>데이터가 없습니다.</b>');
					return false;
				}
			}

			for (var i = 0; i < dateStr.length; i++) {
				per[i] = (trueCnt[i] == "0" && testCnt[i] == "0") ? 0 : Math.round((Number(trueCnt[i]) / Number(testCnt[i]) * 100));
			}

			var rateChart1 = new Chart(rctx1, {
				type : 'line',
				data : {
					datasets : [ {
						label : '',
						backgroundColor : "transparent",
						borderColor : "rgba(59, 112, 219, 1)",
						pointBorderColor : "rgba(59, 112, 219, 1)",
						pointBackgroundColor : "rgba(59, 112, 219, 1)",
						data : per,
						fill : false
					} ],
					labels : dateStr

				},
				options : {
					responsive : true,
					maintainAspectRatio : false,
					legend : {
						display : false,
					},
					elements : {
						line : {
							borderWidth : 1
						}
					},
					plugins : {
						title : {
							display : true,
							text : '나의 레벨'
						},
						tooltip : {
							mode : 'index',
							intersect : false,
						},
						deferred : {
							xOffset : 150, // defer until 150px of the canvas width are inside the viewport
							yOffset : '50%', // defer until 50% of the canvas height are inside the viewport
							delay : 500
						// delay of 500 ms after the canvas is considered inside the viewport
						},
						// Change options for ALL labels of THIS CHART
						datalabels : {
							color : '#000',//수정0219
							font : {
								size : 12
							},//수정0219
							align : function(context) {
								var value = context.dataset.data[context.dataIndex];
								return value > 0 ? 'end' : 'end';
							},
							anchor : function(context) {
								var value = context.dataset.data[context.dataIndex];
								return value > 0 ? 'end' : 'start';
							},
							formatter : function(value, context) {
								return context.dataset.data[context.dataIndex] + '%';
							}
						}
					},
					hover : {
						mode : 'nearest',
						intersect : true
					},
					scales : {
						xAxes : [ {
							display : true,
							gridLines : {
								display : false
							}
						} ],
						yAxes : [ {
							display : true,
							ticks : {
								display : false,
								min : Math.min.apply(null, per),
								stepSize : 5,
								max : Math.max.apply(null, per) + 10,
								fontSize : 10,
								fontColor : '#656565'//수정0219
							},
							gridLines : {
								drawBorder : false
							}
						} ]
					},
					animation : {
						duration : 1000,
					},
					tooltips : {
						backgroundColor : '#102658',
						titleFontSize : 12,
						titleFontColor : '#fff',
						bodyFontColor : '#fff',
						bodyFontSize : 10,
						displayColors : false
					}
				}
			});
		}
	</script>
</body>