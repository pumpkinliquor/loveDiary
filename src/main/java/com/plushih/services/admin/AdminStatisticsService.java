package com.plushih.services.admin;

import java.util.Map;

public interface AdminStatisticsService {

	Map<String, Object> selectSalesInfoList(Map<String, Object> paramMap) throws Exception;

}