package com.plushih.controllers.front.service;

import java.util.List;
import java.util.Map;

import com.plushih.controllers.front.vo.PlusBbsVO;

public interface FrontStoryService {


	/**
	 * 방송광고 리스트 조회
	 * @param plusBbsVO
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> selectAdList(PlusBbsVO plusBbsVO) throws Exception;
	
	/**
	 * 방송광고 리스트 총 개수
	 * @param plusBbsVO
	 * @return
	 */
	int selectAdCnt(PlusBbsVO plusBbsVO)  throws Exception;

	/**
	 * 방송광고 상세
	 * @param plusBbsVO
	 * @return
	 */
	PlusBbsVO selectAdDetail(PlusBbsVO plusBbsVO) throws Exception;

	/**
	 * 보도자료 리스트
	 * @param plusBbsVO
	 * @return
	 */
	List<Map<String, Object>> selectProductNewsList(PlusBbsVO plusBbsVO) throws Exception;

	/**
	 * 보도자료 개수
	 * @param plusBbsVO
	 * @return
	 */
	int selectProductNewsCnt(PlusBbsVO plusBbsVO) throws Exception;

	/**
	 * 보도자료 상세
	 * @param plusBbsVO
	 * @return
	 * @throws Exception
	 */
	PlusBbsVO selectPressReleaseDetail(PlusBbsVO plusBbsVO) throws Exception;

	/**
	 * 보도자료 상세 이전
	 * @param plusBbsVO
	 * @return
	 * @throws Exception
	 */
	PlusBbsVO selectPressReleaseDetailPrevDetail(PlusBbsVO plusBbsVO) throws Exception;

	/**
	 * 보도자료 상세 다음
	 * @param plusBbsVO
	 * @return
	 * @throws Exception
	 */
	PlusBbsVO selectPressReleaseDetailNextDetail(PlusBbsVO plusBbsVO) throws Exception;


}