<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.codehaus.jackson.map.ObjectMapper"%>
<%@ page import="java.util.Enumeration"%>
<%@ include file="/WEB-INF/pages/layouts/include/tagLib.jsp"%>
<c:set var="requestParam" value="<%=new ObjectMapper().writeValueAsString(request.getParameterMap())%>" />
<c:set var="a" value="${resultMap.resultData.acvInfo }" />
<c:set var="al" value="${resultMap.resultData.acvList}" />
<c:set var="ql" value="${resultMap.resultData.weekAcvList }" />
<c:set var="m" value="${resultMap.resultData.rivalInfo }" />
<c:set var="r" value="${resultMap.resultData.runtimeInfo }" />
<c:set var="p" value="${resultMap.resultData.rivalPerInfo }" />
<c:set var="n" value="${resultMap.resultData.notAcvInfo }" />
<c:set var="secUserDurTime" value="${fn:split(r.secToUserDurTime,':') }" />
<c:set var="secAllDurTime" value="${fn:split(r.secToAllDurTime,':') }" />
<%
	Enumeration<String> attrNames = request.getAttributeNames();
%>
<style>
.layer_pop.level_pop {
	width: 90%;
}
</style>

<body class="report_body" style="">
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1>주간학습 리포트</h1>
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
					<span>${a.subName } </span> <span class="bar"> <em>Lv.${a.levId }</em> ${a.acvName }
					</span>
					<c:if test="${fn:length(al) > 1}">
						<a href="#" class="btn_level"></a>
					</c:if>
					<div class="layer_pop level_pop">
						<!-- 수정0222 -->
						<ul class="select_div">
							<c:if test="${!empty al }">
								<c:forEach var="l" items="${al }" varStatus="vs">
									<li class="ipt_radio_div"><input type="radio" id="radio_acvId${vs.index }" name="acvId" value="${l.acvId }" <c:if test="${a.acvId eq l.acvId }">checked="checked"</c:if>> <label for="radio_acvId${vs.index }"><span><c:out value="${l.acvName }" /></span></label></li>
								</c:forEach>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
		</header>
		<div class="container report">
			<section class="contents study_result_cont">
				<span class="period">집계기간 ${a.stDate } ~ ${a.edDate }</span>
				<h2>학습 결과와 상세 분석</h2>
				<div class="study_result_div">
					<div class="div">
						<p class="tit ico_s1 fL">
							<span>학습일</span>
						</p>
						<span class="txt_time fR"> <c:choose>
								<c:when test="${a.durDay le 0 }">
								1
							</c:when>
								<c:otherwise>
								${a.durDay }
							</c:otherwise>
							</c:choose> 일
						</span>
					</div>
					<div class="div">
						<p class="tit ico_s2 fL">
							<span>학습시간</span>
						</p>
						<c:set var="durVideoTimeStr" value="${fn:split(a.durVideoTimeStr,':') }" />
						<span class="txt_time fR"> <c:choose>
								<c:when test="${durVideoTimeStr[0] ne '00' || durVideoTimeStr[1] ne '00' || durVideoTimeStr[2] ne '00'}">
									<c:if test="${durVideoTimeStr[0] ne '00'}">${durVideoTimeStr[0]}시간</c:if>
									<c:if test="${durVideoTimeStr[1] ne '00'}">${durVideoTimeStr[1]}분</c:if>
									<c:if test="${durVideoTimeStr[2] ne '00'}">${durVideoTimeStr[2]}초 </c:if>
								</c:when>
								<c:otherwise>-</c:otherwise>
							</c:choose>
						</span>
					</div>
					<div class="div">
						<p class="tit ico_s3 fL">
							<span>문제풀이시간</span>
						</p>
						<c:set var="durTimeStr" value="${fn:split(a.durTimeStr,':') }" />
						<span class="txt_time fR"> <c:choose>
								<c:when test="${durTimeStr[0] ne '00' || durTimeStr[1] ne '00' || durTimeStr[2] ne '00'}">
									<c:if test="${durTimeStr[0] ne '00'}">${durTimeStr[0]}시간</c:if>
									<c:if test="${durTimeStr[1] ne '00'}">${durTimeStr[1]}분</c:if>
									<c:if test="${durTimeStr[2] ne '00'}">${durTimeStr[2]}초 </c:if>
								</c:when>
								<c:otherwise>-</c:otherwise>
							</c:choose>
						</span>
					</div>
					<div class="div">
						<p class="tit ico_s4 fL">
							<span>영상학습시간</span>
						</p>
						<c:set var="videoTimeStr" value="${fn:split(a.videoTimeStr,':') }" />
						<span class="txt_time fR"> <c:choose>
								<c:when test="${videoTimeStr[0] ne '00' || videoTimeStr[1] ne '00' || videoTimeStr[2] ne '00'}">
									<c:if test="${videoTimeStr[0] ne '00'}">${videoTimeStr[0]}시간</c:if>
									<c:if test="${videoTimeStr[1] ne '00'}">${videoTimeStr[1]}분</c:if>
									<c:if test="${videoTimeStr[2] ne '00'}">${videoTimeStr[2]}초 </c:if>
								</c:when>
								<c:otherwise>-</c:otherwise>
							</c:choose>
						</span>
					</div>
					<div class="q_number_div">
						<div class="q_number bt_line">
							<p class="tit ico_s5">
								<span>맞춘 문항 수</span>
							</p>
							<div class="bar_div">
								<span class="bar" style="width: <fmt:formatNumber value="${a.per }" type="percent"/>;"><em>${a.trueCnt }문항</em></span>
							</div>
						</div>
						<div class="q_number">
							<p class="tit ico_s6">
								<span>전체 풀이 문항 수</span>
							</p>
							<div class="bar_div">
								<span class="bar" style="width: <fmt:formatNumber value="${a.testPer }" type="percent"/>;"><em>${a.testCnt }문항</em></span>
							</div>
						</div>
						<div class="badge">
							<span>정답율 <br> <em><fmt:formatNumber value="${a.truePer }" type="percent" /></em>
							</span>
						</div>
					</div>
				</div>
			</section>


			<section class="contents analysis_cont">
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
								<th class="sticky-col first-col"><span>구분</span></th>
								<th class="sticky-col second-col"><span>번호</span></th>
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
									<tr role="row" class="odd">
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
						</tbody>
					</table>
				</div>
				<p class="txt_st1">※ 난이도는 A가 가장 어렵고 E가 가장 쉬운 문항을 의미합니다.</p>
			</section>

			<section class="contents compare_cont">
				<h2>경쟁자 대비 비교</h2>
				<p class="txt_st1">경쟁자 : 나와 같은 레벨, 같은 성취기준의 문제를 푼 학생</p>
				<h3>풀이문제수</h3>
				<div class="chart_div">
					<canvas id="myChart1" class="gs_reveal_fromLeft"></canvas>
				</div>
				<h3>학습시간</h3>
				<div class="chart_div">
					<canvas id="myChart2" class="gs_reveal_fromRight"></canvas>
				</div>
				<h3>정답율</h3>
				<div class="chart_div">
					<canvas id="myChart3" class="gs_reveal_fromLeft"></canvas>
				</div>
			</section>
			<a href="javascript:void(0)" class="btn btn_st3" onclick="goWeeklyTest();">다음 성취요소 학습하기</a>
		</div>
		<!-- //container -->
	</div>
</body>

<form id="sortFrm" name="sortFrm" method="post">
	<input type="hidden" id="sortFilter" name="sortFilter" value="" />
</form>

<form id="form" name="form" method="post"></form>

<form id="acvform" name="acvform" method="post">
	<input type="hidden" name="acvId" value="<c:out value="${resultMap.resultData.notAcvInfo.acvId}"/>" />
</form>

<script type="text/javascript" charset="UTF-8" src="/assets/js/plugin/owlcarousel/owl.carousel.min.js"></script>
<script type="text/javascript" charset="UTF-8" src="/assets/js/public/default.js"></script>
<!-- 차트 -->

<script src="https://www.chartjs.org/dist/2.9.4/Chart.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-deferred"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.6.0/gsap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.6.0/ScrollTrigger.min.js"></script>
<script type="text/javascript" src="/assets/plugin/datatables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/assets/plugin/datatables/jquery.dataTables.js"></script>

<script type="text/javascript">
	var sortList = [];
	//풀이문제 수
	var ctx1 = document.getElementById('myChart1');
	var userTestCnt = Number("${m.userTestCnt}");
	var allAvgCnt = Number("${m.allAvgCnt}");
	var qstCnt = Number("${a.qstCnt}");
// 	var userDurTime = Number("${r.userDurTime}");
// 	var allDurTime = Number("${r.allDurTime}");
	var userPer = Math.round(Number("${p.userPer}") * 100);
	var allPer = Math.round(Number("${p.allPer}") * 100);
	var loginUser = ("${loginSessionNickName }" != "") ? "${loginSessionNickName }" : "${loginSessionEmail }";
	var userDurHour = Number("${secUserDurTime[0]}") * 60;
	var userDurMin = Number("${secUserDurTime[1]}") + userDurHour;
	var userDurSec = "${secUserDurTime[2]}";
	var allDurHour = Number("${secAllDurTime[0]}") * 60;
	var allDurMin = Number("${secAllDurTime[1]}") + allDurHour;
	var allDurSec = "${secAllDurTime[2]}";

	qCardSlider();
	numSelect();

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

	function animateFrom(elem, direction) {
		direction = direction | 1.5;

		var x = 0,
		y = direction * 100;

		gsap.fromTo(elem, {x: x, y: y, autoAlpha: 0}, {
			duration: 1,
			x: 0,
			y: 0,
			autoAlpha: 1,
			ease: "expo",
			overwrite: "auto"
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

	var myChart1 = new Chart(ctx1, {
		type : 'bar',
		data : {
			labels : [ loginUser + '님', '경쟁자' ],
			datasets : [ {

				label : '풀이문제수',
				data : [ userTestCnt, allAvgCnt ],
				backgroundColor : [ 'rgba(27, 131, 239, 1)', 'rgba(46, 54, 152, 1)' ],
				borderColor : [ 'rgba(27, 131, 239, 1)', 'rgba(46, 54, 152, 1)' ],
				borderWidth : 1
			} ]
		},
		options : {
			responsive : false,
			legend : {
				display : false
			},
			tooltips : {
				enabled : false
			},
			hover : {
				animationDuration : 0
			},
			animation : {
				duration : 1000,
			/*
			        //막대 위 데이터 값 표기
			        onComplete: function () {
			          var chartInstance = this.chart,
			            ctx = chartInstance.ctx;
			          ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);
			          ctx.fillStyle = 'black';
			          ctx.textAlign = 'center';
			          ctx.textBaseline = 'bottom';

			          this.data.datasets.forEach(function (dataset, i) {
			            var meta = chartInstance.controller.getDatasetMeta(i);
			            meta.data.forEach(function (bar, index) {
			              var data = dataset.data[index];
			              ctx.fillText(data, bar._model.x, bar._model.y - 5);
			            });
			          });
			        }
			*/
			},
			scales : {
				yAxes : [ {
					ticks : {
						min : 0,
						stepSize : 500,
						max : qstCnt,
						fontSize : 15,
					}
				} ],
				xAxes : [ {
					barPercentage : 0.4,
					gridLines : {
						display : false
					}
				} ]
			},
			plugins : {
				deferred : {
					xOffset : 150, // defer until 150px of the canvas width are inside the viewport
					yOffset : '50%', // defer until 50% of the canvas height are inside the viewport
					delay : 500
				// delay of 500 ms after the canvas is considered inside the viewport
				},
				// Change options for ALL labels of THIS CHART
				datalabels : {
					color : '#666',
					align : function(context) {
						var value = context.dataset.data[context.dataIndex];
						return value > 0 ? 'end' : 'start';
					},
					anchor : function(context) {
						var value = context.dataset.data[context.dataIndex];
						return value > 0 ? 'end' : 'start';
					}
				}
			}
		}
	});

	//학습시간
	var ctx2 = document.getElementById('myChart2');
	var myChart2 = new Chart(ctx2, {
		type : 'bar',
		data : {
			labels : [ loginUser + '님', '경쟁자' ],
			datasets : [ {

				label : '학습시간',
				data : [ (userDurMin+"."+userDurSec), (allDurMin+"."+allDurSec) ],
				backgroundColor : [ 'rgba(27, 131, 239, 1)', 'rgba(46, 54, 152, 1)' ],
				borderColor : [ 'rgba(27, 131, 239, 1)', 'rgba(46, 54, 152, 1)' ],
				borderWidth : 1
			} ]
		},
		options : {
			responsive : false,
			legend : {
				display : false
			},
			tooltips : {
				enabled : false
			},
			hover : {
				animationDuration : 0
			},
			animation : {
				duration : 1000,

			},
			scales : {
				yAxes : [ {
					ticks : {
						min : (userDurMin > allDurMin && (userDurMin > 0 || allDurMin > 0)) ? allDurMin - allDurMin : userDurMin - userDurMin,
						stepSize : 10000,
						max : (userDurMin > allDurMin) ? userDurMin + ((Math.round(userDurMin / 5) <= 0) ? 1 : Math.round(userDurMin / 5)) : allDurMin + ((Math.round(allDurMin / 5) <= 0) ? 1 : Math.round(allDurMin / 5)),
						fontSize : 15,
						callback : function(value) {
							return value + "분";
						}
					}
				} ],
				xAxes : [ {
					barPercentage : 0.4,
					gridLines : {
						display : false
					}
				} ]
			},
			plugins : {
				deferred : {
					xOffset : 150, // defer until 150px of the canvas width are inside the viewport
					yOffset : '50%', // defer until 50% of the canvas height are inside the viewport
					delay : 500
				// delay of 500 ms after the canvas is considered inside the viewport
				},
				// Change options for ALL labels of THIS CHART
				datalabels : {
					color : '#666',
					align : function(context) {
						var value = context.dataset.data[context.dataIndex];
						return value > 0 ? 'end' : 'start';
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

	//정답율
	var ctx3 = document.getElementById('myChart3');
	var myChart3 = new Chart(ctx3, {
		type : 'bar',
		data : {
			labels : [ loginUser + '님', '경쟁자' ],
			datasets : [ {

				label : '정답율',
				data : [ userPer, allPer ],
				backgroundColor : [ 'rgba(27, 131, 239, 1)', 'rgba(46, 54, 152, 1)' ],
				borderColor : [ 'rgba(27, 131, 239, 1)', 'rgba(46, 54, 152, 1)' ],
				borderWidth : 1
			} ]
		},
		options : {
			responsive : false,
			legend : {
				display : false
			},
			tooltips : {
				enabled : false
			},
			hover : {
				animationDuration : 0
			},
			animation : {
				duration : 1000
			},
			scales : {
				yAxes : [ {
					ticks : {
						min : 0,
						stepSize : 50,
						max : 100,
						fontSize : 15,
						callback : function(value) {
							return value + "%"
						}
					}
				} ],
				xAxes : [ {
					barPercentage : 0.4,
					gridLines : {
						display : false
					}
				} ]
			},
			plugins : {
				deferred : {
					xOffset : 150, // defer until 150px of the canvas width are inside the viewport
					yOffset : '50%', // defer until 50% of the canvas height are inside the viewport
					delay : 500
				// delay of 500 ms after the canvas is considered inside the viewport
				},
				// Change options for ALL labels of THIS CHART
				datalabels : {
					color : '#666',
					align : function(context) {
						var value = context.dataset.data[context.dataIndex];
						return value > 0 ? 'end' : 'start';
					},
					anchor : function(context) {
						var value = context.dataset.data[context.dataIndex];
						return value > 0 ? 'end' : 'start';
					},
					formatter : function(value, context) {
						return context.dataset.data[context.dataIndex] + '%';
					}
				}
			}
		}
	});

	$(function() {
	      $(".bar_div > span").each(function() {
	        $(this)
	          .data("origWidth", $(this).width())
	          .width(0)
	          .animate({
	            width: $(this).data("origWidth")
	          }, 1200);
	      });
	    });

	// 	$(document).on("click", "[class=studyT]", function(e) {
	// 		var $t = $(e.target);
	// 		var $th = $t.closest("th");
	// 		var $tr = $t.closest("tr");
	// 		var trIndex = $(this).find("tbody").find("tr").index($tr);
	// 		var $span1 = $th.find("span[class^=btn]:eq(0)");
	// 		var $span2 = $th.find("span[class^=btn]:eq(1)");
	// 		var sortList = [];
	// 		if ($th.is("[name^=sort]")) {
	// 			if ($span1.is(".on")) {
	// 				//두번째 온
	// 				$span1.removeClass("on");
	// 				$span2.addClass("on");
	// 			} else if ($span2.is(".on")) {
	// 				//없앰
	// 				$span1.removeClass("on");
	// 				$span2.removeClass("on");
	// 			} else {
	// 				//첫번째 온
	// 				$span1.addClass("on");
	// 			}
	// 			sortFunc($th);
	// 		}
	// 		if (trIndex > -1) {

	// 		}
	// 	});

	// 	function sortFunc($th) {
	// 		var thTitle = "";
	// 		$(".studyT").find("th[name^=sort]").find("span.on").each(function(i, e) {
	// 			if (i == 0) {
	// 				thTitle += $(e).attr("title");
	// 			} else {
	// 				thTitle += "," + $(e).attr("title");
	// 			}
	// 		});
	// 		thTitle += (thTitle == "") ? "qst_no asc" : "";
	// 		$("[name=sortFilter]").val(thTitle);
	// 		$.call('/front/ajax/report/weekList', $("[id=sortFrm]").serializeObject(), function(r) {
	// 			$(".studyT tbody").empty();
	// 			changeListFunc(r.resultData.weekAcvList);
	// 		});
	// 	}

	// 	function changeListFunc(r) {
	// 		for (var i = 0; i < r.length; i++) {
	// 	// 			var addClass = (r[i].passYn == "n") ? "fc_R" : "";
	// 				var falseValue = (r[i].maxFalseValue != null && r[i].maxFalseValue != "") ? r[i].maxFalseValue : "-";
	// 				var allPer = (r[i].allPer != null && r[i].allPer != "") ? r[i].allPerStr + "%" : "-";
	// 				var topPer = (r[i].topPer != null && r[i].topPer != "") ? r[i].topPerStr + "%" : "-";
	// 				var spNum = (r[i].passYn == "n") ? r[i].qstValue : "";
	// 				$(".studyT tbody").append($("[id=txt_acvList]").val());
	// 				$(".studyT tbody").find("tr:eq(" + i + ")").find("td[id=td_acaName]").text(r[i].acaName);
	// 				$(".studyT tbody").find("tr:eq(" + i + ")").find("td[id=td_qstNo]").find("a").text(r[i].qstId);
	// 				$(".studyT tbody").find("tr:eq(" + i + ")").find("td[id=td_qstNo]").find("input").val(r[i].qstId);
	// 				$(".studyT tbody").find("tr:eq(" + i + ")").find("td[id=td_cptName]").text(r[i].cptNmStr);
	// 				$(".studyT tbody").find("tr:eq(" + i + ")").find("td[id=td_spNum]").addClass(addClass);
	// 				$(".studyT tbody").find("tr:eq(" + i + ")").find("td[id=td_spNum]").text((r[i].passYn == 'y') ? "O" : "X" + " " + spNum);
	// 				$(".studyT tbody").find("tr:eq(" + i + ")").find("td[id=td_allPer]").text(allPer);
	// 				$(".studyT tbody").find("tr:eq(" + i + ")").find("td[id=td_testLev]").text(r[i].testLev);
	// 				$(".studyT tbody").find("tr:eq(" + i + ")").find("td[id=td_falseValue]").text(falseValue);
	// 				$(".studyT tbody").find("tr:eq(" + i + ")").find("td[id=td_topPer]").text(topPer);
	// 		}
	// 	}

	// 다시풀기 문제페이지 이동
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

	// 주간평가 페이지 이동
	function goWeeklyTest() {
		if ("${resultMap.resultData.notAcvInfo}" != "") {
			commonModalPopup('<h2>${resultMap.resultData.notAcvInfo.subName}</h2><br/><h3>${resultMap.resultData.notAcvInfo.acvName}</h3><br/>성취요소 학습 중 아직 못 푼 문제가 있습니다.<br/>해당 성취요소로 이동합니다.');
			$(".popupConfirm").click({}, goReportWeekly);
		} else {
			commonModalPopup('레벨업 평가 대상자로 선정되었습니다.<br/>Aigo의 레벨 평가로 달라진 학습 수준을 확인하세요.');
			$(".popupConfirm").click({}, golevelTest);
		}
	}

	function goReportWeekly() {
		$("#acvform").attr("action", "/front/achieve/detail").submit();
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
		var LayerPopupAcv = $(".level_pop");
		if (LayerPopup.closest(".commonPopupLayer").is(".is-visible")) {
			if (LayerPopup.has(e.target).length === 0) {
				LayerPopup.closest(".commonPopupLayer").removeClass("is-visible");
			}
		}
		if (LayerPopupAcv.has(e.target).length === 0) {
			LayerPopupAcv.removeClass("active");
		}
	});

	// 다시풀기 문제페이지 이동
	$(document).on("change", ":radio[name=acvId]", function() {
		var LayerPopup = $(".layer_pop");
		LayerPopup.removeClass("active");
		var acvId = $(this).val();
		if (acvId != "") {
			$.formSubmit("/front/report/weekReport", $.extend(null, {
				"acvId" : acvId
			}), {
				method : 'post'
			});
		} else {
			commonModalPopup("성취요소 번호가 없습니다.");
			return false;
		}
	});

	$(document).ready(function() {
		// 		console.log("${resultMap.resultData.acvInfo}");
		// 		console.log("${resultMap.resultData.weekAcvList}");
		// 		console.log("${resultMap.resultData.runtimeInfo}");
		// 		console.log("${resultMap.resultData.notAcvInfo}");
	});
</script>

<textarea id="qstTmpList" style="display: none;">
	<tr>
		<td class="sticky-col first-col" id="td_acaName"></td>
		<td class="sticky-col second-col" id="td_qstNo">
			<input type="hidden" name="qstId" value="" />
			<a href="javascript:void(0)" onclick="goQuestion(this);"></a>
		</td>
		<td id="td_cptName"></td>
		<td id="td_spNum"></td>
		<td id="td_allPer"></td>
		<td id="td_testLev"></td>
		<td id="td_falseValue"></td>
		<td id="td_topPer"></td>
	</tr>
</textarea>