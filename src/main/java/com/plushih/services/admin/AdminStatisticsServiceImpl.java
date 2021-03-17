package com.plushih.services.admin;

import com.plushih.daos.CommonDao;
import com.plushih.services.ci.CiServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("AdminStatisticsService")
public class AdminStatisticsServiceImpl extends CiServiceImpl implements AdminStatisticsService {
	
	@Autowired
	private CommonDao commonDao;
	
	/**
	 * @ClassName	: AdminStatisticsServiceImpl.java
	 * @Method		: selectSalesInfoList
	 * @Date		: 2021. 3. 16. 
	 * @author		: dev.yklee
	 * @Description	: 
	 */
	@Override
	public Map<String, Object> selectSalesInfoList(Map<String, Object> paramMap) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			
			// 올해 데이터
			paramMap.put("addYear", 0);
			List<Map<String, Object>> year = commonDao.selectList("AdminStatisticsDAO.selectSalesInfoList", paramMap);
			
			// 전년도 데이터
			paramMap.put("addYear", -1);
			List<Map<String, Object>> lastYear = commonDao.selectList("AdminStatisticsDAO.selectSalesInfoList", paramMap);
			
			// 증감율 데이터
			List<Map<String, Object>> rate = new ArrayList<Map<String,Object>>();
			// 올해/전년도 데이터로 계산하여 Set
			for (int i = 0; i < year.size(); i++) {
				Map<String, Object> row = new HashMap<>();
				
				// 전일
				double beforeCorCountPer = 0;
				double beforeOrgPricePer = 0;
				double beforeTotalMoneyPer = 0;
				// 금일
				double todayCorCountPer = 0;
				double todayOrgPricePer = 0;
				double todayTotalMoneyPer = 0;
				// 7일 누적
				double weeklyCorCountPer = 0;
				double weeklyOrgPricePer = 0;
				double weeklyTotalMoneyPer = 0;
				// 30일 누적
				double monthCorCountPer = 0;
				double monthOrgPricePer = 0;
				double monthTotalMoneyPer = 0;
				// 연 누적
				double yearCorCountPer = 0;
				double yearOrgPricePer = 0;
				double yearTotalMoneyPer = 0;
				// 조회기간
				double searchCorCountPer = 0;
				double searchOrgPricePer = 0;
				double searchTotalMoneyPer = 0;
				;
				// 통계데이터 종류 (판매/매출/환불)
				row.put("orderStatus", year.get(i).get("orderStatus"));
				
				// 계산식 : (금년도 발생건 -  전년도 발생건) / 전년도 발생건 * 100
				// 전일
				if(Double.valueOf(String.valueOf(lastYear.get(i).get("beforeCorCount"))) > 0) {
					beforeCorCountPer = (Double.valueOf(String.valueOf(year.get(i).get("beforeCorCount")))-Double.valueOf(String.valueOf(lastYear.get(i).get("beforeCorCount"))))/Double.valueOf(String.valueOf(lastYear.get(i).get("beforeCorCount")))*100;
					beforeOrgPricePer = (Double.valueOf(String.valueOf(year.get(i).get("beforeOrgPrice")))-Double.valueOf(String.valueOf(lastYear.get(i).get("beforeOrgPrice"))))/Double.valueOf(String.valueOf(lastYear.get(i).get("beforeOrgPrice")))*100;
					beforeTotalMoneyPer = (Double.valueOf(String.valueOf(year.get(i).get("beforeTotalMoney")))-Double.valueOf(String.valueOf(lastYear.get(i).get("beforeTotalMoney"))))/Double.valueOf(String.valueOf(lastYear.get(i).get("beforeTotalMoney")))*100;
				}
				row.put("beforeCorCountPer", beforeCorCountPer);
				row.put("beforeOrgPricePer", beforeOrgPricePer);
				row.put("beforeTotalMoneyPer", beforeTotalMoneyPer);
				
				// 금일
				if(Double.valueOf(String.valueOf(lastYear.get(i).get("todayCorCount"))) > 0) {
					todayCorCountPer = (Double.valueOf(String.valueOf(year.get(i).get("todayCorCount")))-Double.valueOf(String.valueOf(lastYear.get(i).get("todayCorCount"))))/Double.valueOf(String.valueOf(lastYear.get(i).get("todayCorCount")))*100;
					todayOrgPricePer = (Double.valueOf(String.valueOf(year.get(i).get("todayOrgPrice")))-Double.valueOf(String.valueOf(lastYear.get(i).get("todayOrgPrice"))))/Double.valueOf(String.valueOf(lastYear.get(i).get("todayOrgPrice")))*100;
					todayTotalMoneyPer = (Double.valueOf(String.valueOf(year.get(i).get("todayTotalMoney")))-Double.valueOf(String.valueOf(lastYear.get(i).get("todayTotalMoney"))))/Double.valueOf(String.valueOf(lastYear.get(i).get("todayTotalMoney")))*100;
				}
				row.put("todayCorCountPer", todayCorCountPer);
				row.put("todayOrgPricePer", todayOrgPricePer);
				row.put("todayTotalMoneyPer", todayTotalMoneyPer);
				
				// 7일 누적
				if(Double.valueOf(String.valueOf(lastYear.get(i).get("weeklyCorCount"))) > 0) {
					weeklyCorCountPer = (Double.valueOf(String.valueOf(year.get(i).get("weeklyCorCount")))-Double.valueOf(String.valueOf(lastYear.get(i).get("weeklyCorCount"))))/Double.valueOf(String.valueOf(lastYear.get(i).get("weeklyCorCount")))*100;
					weeklyOrgPricePer = (Double.valueOf(String.valueOf(year.get(i).get("weeklyOrgPrice")))-Double.valueOf(String.valueOf(lastYear.get(i).get("weeklyOrgPrice"))))/Double.valueOf(String.valueOf(lastYear.get(i).get("weeklyOrgPrice")))*100;
					weeklyTotalMoneyPer = (Double.valueOf(String.valueOf(year.get(i).get("weeklyTotalMoney")))-Double.valueOf(String.valueOf(lastYear.get(i).get("weeklyTotalMoney"))))/Double.valueOf(String.valueOf(lastYear.get(i).get("weeklyTotalMoney")))*100;
				}
				row.put("weeklyCorCountPer", weeklyCorCountPer);
				row.put("weeklyOrgPricePer", weeklyOrgPricePer);
				row.put("weeklyTotalMoneyPer", weeklyTotalMoneyPer);
				
				// 30일 누적
				if(Double.valueOf(String.valueOf(lastYear.get(i).get("monthCorCount"))) > 0) {
					monthCorCountPer = (Double.valueOf(String.valueOf(year.get(i).get("monthCorCount")))-Double.valueOf(String.valueOf(lastYear.get(i).get("monthCorCount"))))/Double.valueOf(String.valueOf(lastYear.get(i).get("monthCorCount")))*100;
					monthOrgPricePer = (Double.valueOf(String.valueOf(year.get(i).get("monthOrgPrice")))-Double.valueOf(String.valueOf(lastYear.get(i).get("monthOrgPrice"))))/Double.valueOf(String.valueOf(lastYear.get(i).get("monthOrgPrice")))*100;
					monthTotalMoneyPer = (Double.valueOf(String.valueOf(year.get(i).get("monthTotalMoney")))-Double.valueOf(String.valueOf(lastYear.get(i).get("monthTotalMoney"))))/Double.valueOf(String.valueOf(lastYear.get(i).get("monthTotalMoney")))*100;
				}
				row.put("monthCorCountPer", monthCorCountPer);
				row.put("monthOrgPricePer", monthOrgPricePer);
				row.put("monthTotalMoneyPer", monthTotalMoneyPer);
				
				// 연 누적
				if(Double.valueOf(String.valueOf(lastYear.get(i).get("yearCorCount"))) > 0) {
					yearCorCountPer = (Double.valueOf(String.valueOf(year.get(i).get("yearCorCount")))-Double.valueOf(String.valueOf(lastYear.get(i).get("yearCorCount"))))/Double.valueOf(String.valueOf(lastYear.get(i).get("yearCorCount")))*100;
					yearOrgPricePer = (Double.valueOf(String.valueOf(year.get(i).get("yearOrgPrice")))-Double.valueOf(String.valueOf(lastYear.get(i).get("yearOrgPrice"))))/Double.valueOf(String.valueOf(lastYear.get(i).get("yearOrgPrice")))*100;
					yearTotalMoneyPer = (Double.valueOf(String.valueOf(year.get(i).get("yearTotalMoney")))-Double.valueOf(String.valueOf(lastYear.get(i).get("yearTotalMoney"))))/Double.valueOf(String.valueOf(lastYear.get(i).get("yearTotalMoney")))*100;
				}
				row.put("yearCorCountPer", yearCorCountPer);
				row.put("yearOrgPricePer", yearOrgPricePer);
				row.put("yearTotalMoneyPer", yearTotalMoneyPer);
				
				// 조회기간
				if(Double.valueOf(String.valueOf(lastYear.get(i).get("searchCorCount"))) > 0) {
					searchCorCountPer = (Double.valueOf(String.valueOf(year.get(i).get("searchCorCount")))-Double.valueOf(String.valueOf(lastYear.get(i).get("searchCorCount"))))/Double.valueOf(String.valueOf(lastYear.get(i).get("searchCorCount")))*100;
					searchOrgPricePer = (Double.valueOf(String.valueOf(year.get(i).get("searchOrgPrice")))-Double.valueOf(String.valueOf(lastYear.get(i).get("searchOrgPrice"))))/Double.valueOf(String.valueOf(lastYear.get(i).get("searchOrgPrice")))*100;
					searchTotalMoneyPer = (Double.valueOf(String.valueOf(year.get(i).get("searchTotalMoney")))-Double.valueOf(String.valueOf(lastYear.get(i).get("searchTotalMoney"))))/Double.valueOf(String.valueOf(lastYear.get(i).get("searchTotalMoney")))*100;
				}
				row.put("searchCorCountPer", searchCorCountPer);
				row.put("searchOrgPricePer", searchOrgPricePer);
				row.put("searchTotalMoneyPer", searchTotalMoneyPer);
				
				rate.add(row);
			}
			
			resultMap.put("year", year);				// 금년도 데이터
			resultMap.put("lastYear", lastYear);		// 전년도 데이터
			resultMap.put("rate", rate);				// 증감율 데이터
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}

}