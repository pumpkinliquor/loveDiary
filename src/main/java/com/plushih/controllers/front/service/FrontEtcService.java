package com.plushih.controllers.front.service;

import java.util.List;
import java.util.Map;

import com.plushih.controllers.front.vo.PlusBbsVO;

public interface FrontEtcService {

	/**
	 * FAQ리스트
	 * @param plusBbsVO
	 * @return
	 */
	List<Map<String, Object>> selectFaqList(PlusBbsVO plusBbsVO);



}