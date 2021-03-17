package com.plushih.controllers.front.service;

import java.util.List;
import java.util.Map;

import com.plushih.controllers.front.vo.PlusBbsVO;

public interface FrontMainService {

	List<Map<String, Object>> selectMainBannerList(Map<String,Object> param) throws Exception;


}