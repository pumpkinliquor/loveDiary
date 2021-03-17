<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<script src="/assets/js/jquery/mask.ip-input.js"></script>

<h2>매출집계</h2>

<form id="searchForm" name="searchForm" method="post">
<table class="srchT">
	<colgroup>
		<col width="18%">
		<col width="*">
	</colgroup>
	<tbody>
		<tr>
			<th rowspan="2">기간</th>
			<td class="bbx">
				<div class="date_div">
					<span class="ipt_dates"><input type="date" class="ipt_date" id="" name="searchStartDate" value="<c:out value="${paramMap.searchStartDate }"/>"></span>
					<span class="ipt_dates"><input type="date" class="ipt_date" id="" name="searchEndDate" value="<c:out value="${paramMap.searchEndDate }"/>"></span>
				</div>
			</td>
		</tr>
		<tr>
			<td class="ptx">
				<div class="radio_wrap" style="width:auto">
					<div class="ipt_radio_div">
						<input type="radio" id="srch1" name="searchAppType" value="" checked="checked">
						<label for="srch1">전체</label>
					</div>
					<div class="ipt_radio_div" style="width:176px">
						<input type="radio" id="srch2" name="searchAppType" value="android">
						<label for="srch2">안드로이드(구글)</label>
					</div>
					<div class="ipt_radio_div">
						<input type="radio" id="srch3" name="searchAppType" value="ios">
						<label for="srch3">iOS(애플)</label>
					</div>
				</div>
				<button type="submit" class="btn btn-st1 btn_srch" onclick="fnSearch();">검색</button>
			</td>
		</tr>
	</tbody>
</table>
</form>
<ul class="list-st1 mb30">
	<li>디폴트 오늘날짜를 기준으로 어제, 오늘, 7일 누적, 30일 누적, 연간 누적 매출액을 집계합니다.</li>
	<li>특정 기간으로 매출 집계를 조회하려면 기간 설정 후 검색 하세요. </li>
</ul>
<div class="tbl_top_div dateInfoArea">
	<p><c:out value="${paramMap.searchStartDate }"/> ~ <c:out value="${paramMap.searchEndDate }"/> 전체 상점</p>
</div>

<table class="salesT tbl-st2">
	<colgroup>
		<col width="17%">
		<col width="18%">
		<col width="17.5%">
		<col width="17.5%">
		<col width="17.5%">
		<col width="17.5%">
		<col width="17.5%">
		<col width="17.5%">
	</colgroup>
	<thead>
		<tr>
			<th colspan="2" class="blx">구분</th>
			<th>D-1</th>
			<th>기준일</th>
			<th>7일 누적</th>
			<th>30일 누적</th>
			<th>연 누적</th>
			<th>조회기간</th>
		</tr>
	</thead>
	<tbody>
		<!-- 금년도 -->
		<c:forEach var="row" items="${data.year }" varStatus="i">
			<tr>
				<c:if test="${i.index eq 0}"><th rowspan="6" class="first">금년</th></c:if>
				<th>
					<c:choose>
						<c:when test="${row.orderStatus eq 'ORDER' }">판매건수</c:when>
						<c:when test="${row.orderStatus eq 'PAY' }">매출건수</c:when>
						<c:when test="${row.orderStatus eq 'REFUND' }">환불건수</c:when>
					</c:choose>
				</th>
				<td><fmt:formatNumber value="${row.beforeCorCount}" pattern="#,###" /></td>
				<td class="b blue"><fmt:formatNumber value="${row.todayCorCount}" pattern="#,###" /></td>
				<td><fmt:formatNumber value="${row.weeklyCorCount}" pattern="#,###" /></td>
				<td><fmt:formatNumber value="${row.monthCorCount}" pattern="#,###" /></td>
				<td><fmt:formatNumber value="${row.yearCorCount}" pattern="#,###" /></td>
				<td><fmt:formatNumber value="${row.searchCorCount}" pattern="#,###" /></td>
			</tr>
			<tr>
				<th>
					<c:choose>
						<c:when test="${row.orderStatus eq 'ORDER' }">판매금액</c:when>
						<c:when test="${row.orderStatus eq 'PAY' }">매출금액</c:when>
						<c:when test="${row.orderStatus eq 'REFUND' }">환불금액</c:when>
					</c:choose>
				</th>
				<td>
					<c:choose>
						<c:when test="${row.orderStatus eq 'ORDER' }"><fmt:formatNumber value="${row.beforeOrgPrice}" pattern="#,###" /></c:when>
						<c:otherwise><fmt:formatNumber value="${row.beforeTotalMoney}" pattern="#,###" /></c:otherwise>
					</c:choose>
				</td>
				<td class="b blue">
					<c:choose>
						<c:when test="${row.orderStatus eq 'ORDER' }"><fmt:formatNumber value="${row.todayOrgPrice}" pattern="#,###" /></c:when>
						<c:otherwise><fmt:formatNumber value="${row.todayTotalMoney}" pattern="#,###" /></c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${row.orderStatus eq 'ORDER' }"><fmt:formatNumber value="${row.weeklyOrgPrice}" pattern="#,###" /></c:when>
						<c:otherwise><fmt:formatNumber value="${row.weeklyTotalMoney}" pattern="#,###" /></c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${row.orderStatus eq 'ORDER' }"><fmt:formatNumber value="${row.monthOrgPrice}" pattern="#,###" /></c:when>
						<c:otherwise><fmt:formatNumber value="${row.monthTotalMoney}" pattern="#,###" /></c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${row.orderStatus eq 'ORDER' }"><fmt:formatNumber value="${row.yearOrgPrice}" pattern="#,###" /></c:when>
						<c:otherwise><fmt:formatNumber value="${row.yearTotalMoney}" pattern="#,###" /></c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${row.orderStatus eq 'ORDER' }"><fmt:formatNumber value="${row.searchOrgPrice}" pattern="#,###" /></c:when>
						<c:otherwise><fmt:formatNumber value="${row.searchTotalMoney}" pattern="#,###" /></c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
		<!-- 전년도 -->
		<c:forEach var="row" items="${data.lastYear }" varStatus="i">
			<tr>
				<c:if test="${i.index eq 0}"><th rowspan="6" class="first">전년</th></c:if>
				<th>
					<c:choose>
						<c:when test="${row.orderStatus eq 'ORDER' }">판매건수</c:when>
						<c:when test="${row.orderStatus eq 'PAY' }">매출건수</c:when>
						<c:when test="${row.orderStatus eq 'REFUND' }">환불건수</c:when>
					</c:choose>
				</th>
				<td><fmt:formatNumber value="${row.beforeCorCount}" pattern="#,###" /></td>
				<td class="b blue"><fmt:formatNumber value="${row.todayCorCount}" pattern="#,###" /></td>
				<td><fmt:formatNumber value="${row.weeklyCorCount}" pattern="#,###" /></td>
				<td><fmt:formatNumber value="${row.monthCorCount}" pattern="#,###" /></td>
				<td><fmt:formatNumber value="${row.yearCorCount}" pattern="#,###" /></td>
				<td><fmt:formatNumber value="${row.searchCorCount}" pattern="#,###" /></td>
			</tr>
			<tr>
				<th>
					<c:choose>
						<c:when test="${row.orderStatus eq 'ORDER' }">판매금액</c:when>
						<c:when test="${row.orderStatus eq 'PAY' }">매출금액</c:when>
						<c:when test="${row.orderStatus eq 'REFUND' }">환불금액</c:when>
					</c:choose>
				</th>
				<td>
					<c:choose>
						<c:when test="${row.orderStatus eq 'ORDER' }"><fmt:formatNumber value="${row.beforeOrgPrice}" pattern="#,###" /></c:when>
						<c:otherwise><fmt:formatNumber value="${row.beforeTotalMoney}" pattern="#,###" /></c:otherwise>
					</c:choose>
				</td>
				<td class="b blue">
					<c:choose>
						<c:when test="${row.orderStatus eq 'ORDER' }"><fmt:formatNumber value="${row.todayOrgPrice}" pattern="#,###" /></c:when>
						<c:otherwise><fmt:formatNumber value="${row.todayTotalMoney}" pattern="#,###" /></c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${row.orderStatus eq 'ORDER' }"><fmt:formatNumber value="${row.weeklyOrgPrice}" pattern="#,###" /></c:when>
						<c:otherwise><fmt:formatNumber value="${row.weeklyTotalMoney}" pattern="#,###" /></c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${row.orderStatus eq 'ORDER' }"><fmt:formatNumber value="${row.monthOrgPrice}" pattern="#,###" /></c:when>
						<c:otherwise><fmt:formatNumber value="${row.monthTotalMoney}" pattern="#,###" /></c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${row.orderStatus eq 'ORDER' }"><fmt:formatNumber value="${row.yearOrgPrice}" pattern="#,###" /></c:when>
						<c:otherwise><fmt:formatNumber value="${row.yearTotalMoney}" pattern="#,###" /></c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${row.orderStatus eq 'ORDER' }"><fmt:formatNumber value="${row.searchOrgPrice}" pattern="#,###" /></c:when>
						<c:otherwise><fmt:formatNumber value="${row.searchTotalMoney}" pattern="#,###" /></c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
		<!-- 증감율 -->
		<c:forEach var="row" items="${data.rate }" varStatus="i">
			<tr class="rateArea">
				<c:if test="${i.index eq 0}"><th rowspan="6" class="first">증감율</th></c:if>
				<th>
					<c:choose>
						<c:when test="${row.orderStatus eq 'ORDER' }">판매건수</c:when>
						<c:when test="${row.orderStatus eq 'PAY' }">매출건수</c:when>
						<c:when test="${row.orderStatus eq 'REFUND' }">환불건수</c:when>
					</c:choose>
				</th>
				<td><fmt:formatNumber value="${row.beforeCorCountPer}" pattern="#,###" />%</td>
				<td class="b blue"><fmt:formatNumber value="${row.todayCorCountPer}" pattern="#,###" />%</td>
				<td><fmt:formatNumber value="${row.weeklyCorCountPer}" pattern="#,###" />%</td>
				<td><fmt:formatNumber value="${row.monthCorCountPer}" pattern="#,###" />%</td>
				<td><fmt:formatNumber value="${row.yearCorCountPer}" pattern="#,###" />%</td>
				<td><fmt:formatNumber value="${row.searchCorCountPer}" pattern="#,###" />%</td>
			</tr>
			<tr class="rateArea">
				<th>
					<c:choose>
						<c:when test="${row.orderStatus eq 'ORDER' }">판매금액</c:when>
						<c:when test="${row.orderStatus eq 'PAY' }">매출금액</c:when>
						<c:when test="${row.orderStatus eq 'REFUND' }">환불금액</c:when>
					</c:choose>
				</th>
				<td>
					<c:choose>
						<c:when test="${row.orderStatus eq 'ORDER' }"><fmt:formatNumber value="${row.beforeOrgPricePer}" pattern="#,###" />%</c:when>
						<c:otherwise><fmt:formatNumber value="${row.beforeTotalMoneyPer}" pattern="#,###" />%</c:otherwise>
					</c:choose>
				</td>
				<td class="b blue">
					<c:choose>
						<c:when test="${row.orderStatus eq 'ORDER' }"><fmt:formatNumber value="${row.todayOrgPricePer}" pattern="#,###" />%</c:when>
						<c:otherwise><fmt:formatNumber value="${row.todayTotalMoneyPer}" pattern="#,###" />%</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${row.orderStatus eq 'ORDER' }"><fmt:formatNumber value="${row.weeklyOrgPricePer}" pattern="#,###" />%</c:when>
						<c:otherwise><fmt:formatNumber value="${row.weeklyTotalMoneyPer}" pattern="#,###" />%</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${row.orderStatus eq 'ORDER' }"><fmt:formatNumber value="${row.monthOrgPricePer}" pattern="#,###" />%</c:when>
						<c:otherwise><fmt:formatNumber value="${row.monthTotalMoneyPer}" pattern="#,###" />%</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${row.orderStatus eq 'ORDER' }"><fmt:formatNumber value="${row.yearOrgPricePer}" pattern="#,###" />%</c:when>
						<c:otherwise><fmt:formatNumber value="${row.yearTotalMoneyPer}" pattern="#,###" />%</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${row.orderStatus eq 'ORDER' }"><fmt:formatNumber value="${row.searchOrgPricePer}" pattern="#,###" />%</c:when>
						<c:otherwise><fmt:formatNumber value="${row.searchTotalMoneyPer}" pattern="#,###" />%</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<script type="text/javascript">

var $searchForm = $("#searchForm");

$(document).ready(function(){
    
    $('.content').addClass('sales_cont');
    
    /* 날짜값 세팅 */
	var sDate = new Date();
	var eDate = new Date();
// 	sDate.setMonth(sDate.getMonth()-1);
	if("${param.searchStartDate}" == "" && "${param.searchEndDate}" == ""){
// 		$("input[name='searchStartDate']").val(sDate.format('yyyy-MM-dd'));
// 		$("input[name='searchEndDate']").val(eDate.format('yyyy-MM-dd'));
		$('.dateInfoArea p').text(sDate.format('yyyy-MM-dd')+' ~ '+eDate.format('yyyy-MM-dd') +' 전체 상점');
	}
    
    /* 코드 사용 */
    $.call('/ajax/codeList',{codes:'BI,USE_YN,ACV,SUB,CONNECT,USE_YN_ALL'},function(r){
        $.extend(plus.codes,r.codes);
    });
    $.each(plus.codes,function(k,v){
      $('.code.'+k).addCodeItem(v,true);
      $('.codex.'+k).addCodeItem(v);
    });
	
    setMinusPer();
    
});

// 검색
function fnSearch(){
    $searchForm.attr("/plusadmin/AD_SA_01").submit();
}

// 감소율 스타일 적용
function setMinusPer(){
    $('.rateArea').each(function(){
		$(this).find("td").each(function(){
		    if(Number($(this).text().replace("%","")) < 0){
			    $(this).addClass("b").addClass("red");
			}
		});
    });
}

</script>