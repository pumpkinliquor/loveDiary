<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<body class="report_body">
	<div class="wrapper">
		<header class="header">
			<a href="#" class="btn_allmenu">전체메뉴</a>
			<h1 class="title"><img src="/assets/images/logo.png" alt="AIGo"> <span>진단서</span></h1>
			<div class="my_cont">
				<p class="my_id">
					<c:choose>
						<c:when test="${!empty loginSessionNickName }">
							<c:out value="${loginSessionNickName }" />
						</c:when>
						<c:otherwise>
							<c:out value="${loginSessionEmail }" />
						</c:otherwise>
					</c:choose>님
				</p>
				<div class="my_grade">
					<p><span id="totalQstCnt">-</span>문제를 풀었고 <em id="passCnt">-문제</em>를 맞췄습니다.</p>
				</div>
			</div>
		</header>
		
		<!-- 진단결과 그래프 -->
		<div class="container report">
			<section class="contents">
				<div class="chart_wrap">
					<div class="chart_div diagnosis_chart1">
						<canvas id="chart1" style="width:100%"></canvas>
						<div class="my_posi_wrap">
							<span class="txt" id="myRateArea">Your Rate : -%</span>
							<div class="div">
								<span style="left:0%" id="myRateGraph">
									<span class="dot" style="top:0%"></span>
									<span class="line"></span>
									<span class="my_posi_box">
										<span>나의 AIGo 위치 <em id="myGradeArea">-등급</em></span>
										<span class="bg"></span>
									</span>
								</span>
							</div>
						</div><!-- //my_posi_wrap -->
					</div>
					<p class="txt_st1">* 전국 백분위, 오차범위 ±5%</p>
				</div>
				<button class="btn btn_st3 mt25" onclick="goLearn();">LEVEL <c:out value="${param.grade }"/> 코스로 학습 시작</button>
			</section>
			
			<!-- 내용영역별 분석 -->
			<section class="contents">
				<h2>내용영역별 분석</h2>
				<div class="chart_div diagnosis_chart3">
					<div class="canvas-wrap"><canvas id="chart3" class="scroll"></canvas></div>
					<div id="legend3"></div>
				</div>
			</section>
			
			<!-- 문항별 채점결과 -->
			<section class="contents">
				<h2>문항별 채점결과</h2>
				<table class="studyT mt30">
					<colgroup>
						<col width="23.5%">
						<col width="22.7%">
						<col width="36.7%">
						<col width="17.1%">
					</colgroup>
					<thead>
						<tr>
							<th>문항번호</th>
							<th>과목</th>
							<th>평가내용</th>
							<th>결과</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">-</td>
						</tr>
					</tbody>
				</table>
			</section>
			
			<!-- 진단 총평 -->
			<section class="contents total_review_cont">
				<div class="review_tit"><h2><span>AIGo 진단 총평</span></h2></div>
				<c:choose>
				<c:when test="${not empty analysisInfo.evaluationInfo }">
					<div class="review_txt_div"><c:out value="${analysisInfo.evaluationInfo.evtText }" escapeXml="false"/></div>
				</c:when>
				<c:otherwise>
					<div class="review_txt_div"><p>기본적인 수학 개념과 문제 적용력을 키우는 문제를 AIGo가 엄선하여 출제합니다. </p></div>
				</c:otherwise>
				</c:choose>
			</section>
			
			<!-- 하단배너 -->
			<section class="contents banner_conts">
				<p><span>단, 6주간만 AI가 엄선한 고퀄리티<br> 수학 문제를 무료 공개 합니다.</span></p>
			</section>
			
		</div><!-- //container -->
	</div>
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$('.studyT tbody').empty();
		$.post('/front/ajax/aigo/furs/completeList', {}, function(r) {
		
			if(r.resultList){
				$('.studyT tbody').empty();
			}
			
			var tbody = $('.studyT tbody');
			var passCnt = 0;
			$.each(r.resultList, function(k,v){
				var tr = plus.makeElement('tr').appendTo(tbody);
				var td = plus.makeElement('td',v['qstId']).appendTo(tr);
				var td = plus.makeElement('td',v['subName']).appendTo(tr);
				var td = plus.makeElement('td',v['acvName']).appendTo(tr);
				var td = plus.makeElement('td',v['tqstResult']||'X').appendTo(tr);
				if(v['tqstResult'] == 'O') passCnt++;
			});
			$("#totalQstCnt").text(r.resultList.length);
			$("#passCnt").text(passCnt+"문제");
			
			var myLevel = "${param.grade}";
			var myGrade = "";
			var myRate = 0;
			var myTopRate = 0;
			var graphTop = 0;
			
			if(myLevel == "5") { myGrade = "1등급"; myRate = 96; myTopRate = 4; graphTop = 95;}
			else if(myLevel == "4") { myGrade = "2등급"; myRate = 89; myTopRate = 11; graphTop = 90; }
			else if(myLevel == "3") { myGrade = "3등급"; myRate = 77; myTopRate = 23; graphTop = 77; }
			else if(myLevel == "2") { myGrade = "4등급"; myRate = 60; myTopRate = 60; graphTop = 37; }
			else if(myLevel == "1") { myGrade = "6등급 이하"; myRate = 23; myTopRate = 77; graphTop = 72; }
			
			$("#myRateGraph").css({"width" : "0"});
			$("#myRateGraph .dot").css({"top" : graphTop+"%"});
			$("#myRateArea").text("Your Rate : "+myTopRate+"%");
			$("#myGradeArea").text(myGrade);
			$("#myRateGraph").css({"left" : myRate+"%"});
			
		});
		
		var myqstlist = $.parseJSON('${param.myqstlist}'||'[]');
		console.log(myqstlist);
	});
	
	// 학습시작 - 성취기준 페이지 이동
	function goLearn(){
		window.location.href = "/front/learn/home";
	}
	
</script>

 <!-- 차트 -->
<script src="https://www.chartjs.org/dist/2.9.4/Chart.min.js"></script>
<script src="/assets/plugin/utils.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-deferred"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.6.0/gsap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.6.0/ScrollTrigger.min.js"></script>
<script type="text/javascript">
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
    gsap.set(elem, {autoAlpha: 0});
  }

  document.addEventListener("DOMContentLoaded", function() {
    gsap.registerPlugin(ScrollTrigger);

    gsap.utils.toArray(".chart_div").forEach(function(elem) {
      hide(elem); // assure that the element is hidden when scrolled into view

      ScrollTrigger.create({
        trigger: elem,
        onEnter: function() { animateFrom(elem) },
        onEnterBack: function() { animateFrom(elem, -1) },
        onLeave: function() { hide(elem) } // assure that the element is hidden when scrolled into view
      });
    });
  });


//나의 AIGo 위치 차트
var lineChartData = {
  labels: ["99.9%", "83.3%", "66.6%", "50%", "33.3%", "16.6%", "0.1%"],
  datasets:
  [{
  label: "나의 AIGo 위치",
  data: [],
  borderColor: '#3b70db',
  backgroundColor: "rgba(59, 112, 219, 0.1)",
  pointBorderColor: "rgba(59, 112, 219, 0)",
  pointBackgroundColor: "rgba(59, 112, 219, 0)",
   // backgroundColor: utils.transparentize(presets.skyblue),
  fill:'origin',
  },{
  label: "나의 AIGo 위치",
  data: [0.01, 0.06, 0.20, 0.32, 0.20, 0.06, 0.01],
  borderColor: '#3b70db',
  pointBorderColor: "rgba(59, 112, 219, 0)",
  pointBackgroundColor: "rgba(59, 112, 219, 0)",
  pointBackgroundColor: "transparent"
  }]
};
/*
const verticalLinePlugin = {
  getLinePosition: function (chart, pointIndex) {
  const meta = chart.getDatasetMeta(0); // first dataset is used to discover X coordinate of a point
  const data = meta.data;
  return data[pointIndex]._model.x;
  },
  renderVerticalLine: function (chartInstance, pointIndex) {
  const lineLeftOffset = this.getLinePosition(chartInstance, pointIndex);
  const scale = chartInstance.scales['y-axis-0'];
  const context = chartInstance.chart.ctx;


  // render vertical line
  context.beginPath();
  context.strokeStyle = '#ff0000';
  context.setLineDash([2, 1.5]);
  context.moveTo(lineLeftOffset, scale.top + 0);
  context.lineTo(lineLeftOffset, scale.bottom);
  context.stroke();

  // write label
  context.fillStyle = "#000";
  context.textAlign = 'center';
  context.font = '10px sans-serif';
  context.fillText('Your Rate : 3.79%', lineLeftOffset, scale.top + 30);
  },
  afterDatasetsDraw: function (chart, easing) {
  if (chart.config.lineAtIndex) {
    chart.config.lineAtIndex.forEach(pointIndex => this.renderVerticalLine(chart, pointIndex));
  }
  }
  };
Chart.plugins.register(verticalLinePlugin);
*/
var ctxChart1 = document.getElementById("chart1").getContext("2d");
var chart1 = new Chart(ctxChart1, {
  type: 'line',
  data: lineChartData,
  options: {
  responsive: true,
  maintainAspectRatio: false,
  legend: {
    display: false
  },
  elements: {
    line: {
    fill: false,
    borderWidth:2
    }
  },
  plugins: {
     datalabels: {
    display: false
     }
  },
  tooltips: false,
  deferred: {
    xOffset: 150,   // defer until 150px of the canvas width are inside the viewport
    yOffset: '50%', // defer until 50% of the canvas height are inside the viewport
    delay: 500    // delay of 500 ms after the canvas is considered inside the viewport
    },
   scales: {
    xAxes: [{
      ticks:{
      display: true,
      autoSkip: true,
      maxRotation: 0,//수정0226
      minRotation: 0,//수정0226
      maxTicksLimit: 6,
      fontSize : 10,
      fontColor: '#656565'
      },
      gridLines: {
        zeroLineColor: '#ddd',
        drawBorder: false
      }
    }],
    yAxes: [{
      ticks:{
      display: true,
      autoSkip: true,
      maxTicksLimit: 6,
      fontSize : 10,
      fontColor: '#656565'
      },
      gridLines: {
        zeroLineColor: '#ddd',
        drawBorder: false
      }
    }]
  },
  animation: {
    duration: 1000,
  },
  },
 //  lineAtIndex: [9]
});

//평가영역별 분석
// var ctxChart2 = document.getElementById("chart2").getContext("2d");
// var chart2 = new Chart(ctxChart2, {
//   type: 'horizontalBar',
//   data: {
//     labels: ['추론력', ' 문제해결력', '이해력', '계산력'],
//     datasets: [
//       {
//         data: [20, 50, 65, 75],
//         backgroundColor: '#1b83ef',
//         label: '내점수'
//       },
//       {
//         data: [75, 90, 45, 60],
//         backgroundColor: '#2e3698',
//         label: 'AIGo 평균'
//       }
//     ]
//   },

//   options: {
//     responsive: true,
//     maintainAspectRatio: false,
//     legend: {
//         position: 'bottom',
//         display: false
//     },
//     legendCallback: function(chart) {
//       var text = [];
//       text.push('<ul class="legend ' + chart.id + '-legend">');
//       for (var i = 0; i < chart.data.datasets.length; i++) {
//         text.push('<li><div class="legendValue"><span class="label_shape" style="background-color:' + chart.data.datasets[i].backgroundColor + '">&nbsp;&nbsp;&nbsp;&nbsp;</span>');

//         if (chart.data.datasets[i].label) {
//           text.push('<span class="label_txt">' + chart.data.datasets[i].label + '</span>');
//         }

//         text.push('</div></li></div>');
//       }

//       text.push('</ul>');

//       return text.join('');
//     },
//     scales: {
//       xAxes: [{
//         ticks: {
//           beginAtZero: true,
//           max: 100,
//           min: 0,
//           stepSize : 50,
//           fontSize : 10
//         },
//         gridLines: {
//             drawBorder: false,
//             zeroLineColor: '#ddd'
//           }
//       }],
//       yAxes: [{
//         barPercentage: 0.8,
//         gridLines: {
//           display: false,
//           drawBorder: false//축과 데이터의 경계선 표시 여부
//           },
//         ticks: {
//           display: true,//축의 값 표시 여부
//           fontSize : 10
//         }
//       }]
//     },
//     animation: {
//       duration: 1000,
//     },
//     plugins: {
//         datalabels: {
//           anchor: 'end',
//           align: 'end',
//           font: {
//             size: 10
//           },
//           color: function(context) {
//             console.log(context);
//             var index = context.dataIndex;
//             var value = context.dataset.data[index];
//             var valueBlack = context.dataset.label;

//             if(valueBlack === '내점수') {
//               return value = '#000';
//             } else {
//               return value = 'transparent';
//             }
//           }
//         }
//       }
//     //layout : { padding: { left: -5, right: 0, top: 0, bottom: 0 } }
//   },
//   plugins: [{
//     //y축 레이블 3글자이상일경우 글자 떨어트림
//     beforeInit: function (chart) {
//       chart.data.labels.forEach(function (value, index, array) {
//         var a = [];
//         a.push(value.slice(0, 3));
//         var i = 1;
//         while(value.length > (i * 3)){
//           a.push(value.slice(i * 3, (i + 1) * 3));
//             i++;
//         }
//         array[index] = a;
//       })
//     },

//   }]
// });
// Create our legend
// $('#legend2').prepend(chart2.generateLegend());

//내용영역별 분석 차트

var arrItemData = eval("${analysisInfo.arrItemData}");
var arrTotalData = eval("${analysisInfo.arrTotalData}");
var arrUserData = eval("${analysisInfo.arrUserData}");

var ctxChart3 = document.getElementById('chart3').getContext('2d');
var chart3 = new Chart(ctxChart3, {
  type: 'bar',
  data: {
      labels: ['수와연산','도형','측정','확률과통계','규칙성','확률과통계','규칙성'],
      //labels: arrItemData,
      datasets: [{
          label: '내점수',
          backgroundColor:"#1b83ef",
          borderWidth: 0,
          data: arrUserData
      }, {
          label: 'AIGo 평균',
          backgroundColor:"#2e3698",
          borderWidth: 1,
          data: arrTotalData
      }]

  },
  options: {
      responsive: false,
      maintainAspectRatio: false,
      legend: {
          position: 'bottom',
          display: false
      },
      legendCallback: function(chart) {
        var text = [];
        text.push('<ul class="legend ' + chart.id + '-legend">');
        for (var i = 0; i < chart.data.datasets.length; i++) {
          text.push('<li><div class="legendValue"><span class="label_shape" style="background-color:' + chart.data.datasets[i].backgroundColor + '">&nbsp;&nbsp;&nbsp;&nbsp;</span>');

          if (chart.data.datasets[i].label) {
            text.push('<span class="label_txt">' + chart.data.datasets[i].label + '</span>');
          }

          text.push('</div></li></div>');
        }

        text.push('</ul>');

        return text.join('');
      },
      scales: {
        xAxes: [{
          display: true,
          barPercentage: 0.8,
          gridLines: {
              display:false
          },
          ticks: {
            beginAtZero: true,
            fontSize : 10,
            autoSkip: false,
            maxRotation: 0,//수정0226
            minRotation: 0,//수정0226
          },
        }],
        yAxes: [{
          display: true,
          ticks: {
            beginAtZero: true,
            display: true,
            min: 0,
            stepSize : 50,
            max: 100,
            fontSize : 10
          },
          gridLines: {
            drawBorder: false,
            zeroLineColor: '#ddd'
          }
        }]
      },
      title: {
          display: false,
          text: ''
      },
      animation: {
        duration: 1000,
      },
      plugins: {
        datalabels: {
          anchor: 'end',
          align: 'end',
          font: {
            size: 10
          },
          color: function(context) {
            console.log(context);
            var index = context.dataIndex;
            var value = context.dataset.data[index];
            var valueBlack = context.dataset.label;

            if(valueBlack === '내점수') {
              return value = '#000';
            } else {
              return value = 'transparent';
            }
          }
        }
      }
  }
});
$('#legend3').prepend(chart3.generateLegend());


  </script>