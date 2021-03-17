package com.plushih.controllers.front.service;

import java.util.List;
import java.util.Map;

import com.plushih.controllers.front.vo.PlusBbsVO;
import com.plushih.controllers.front.vo.ProductVO;

public interface FrontOurBusinessService {

	/**
	 * rnd 리스트
	 * @param plusBbsVO
	 * @return
	 */
	List<Map<String, Object>> selectRndListList(PlusBbsVO plusBbsVO) throws Exception;

	/**
	 * rnd 리스트 개수
	 * @param plusBbsVO
	 * @return
	 */
	int selectRndListCnt(PlusBbsVO plusBbsVO)  throws Exception;;

	 
}