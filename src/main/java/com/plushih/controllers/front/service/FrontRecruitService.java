package com.plushih.controllers.front.service;

import java.util.List;
import java.util.Map;

import com.plushih.controllers.front.vo.PlusBbsVO;
import com.plushih.controllers.front.vo.RecruitVO;

public interface FrontRecruitService {

	/**
	 * 채용 메인 리스트(5건)
	 * @param recruitVO
	 * @return
	 */
	List<Map<String, Object>> selectRecruitList(RecruitVO vo) throws Exception;

	/**
	 * 현재 채용 건수
	 * @param recruitVO
	 * @return
	 */
	int selectCurrRecruitCnt() throws Exception;

	/**
	 * 채용 전체 공지 리스트
	 * @param recruitVO
	 * @return
	 */
	List<Map<String, Object>> selectRecruitAllList(RecruitVO vo) throws Exception;

	/**
	 * 채용 전체 공지 개수
	 * @param recruitVO
	 * @return
	 */
	int selectRecruitAllCnt(RecruitVO vo) throws Exception;

	/**
	 * 채용 공고 상세
	 * @param recruitVO
	 * @return
	 */
	RecruitVO selectrecRuitNoticeDetail(RecruitVO vo) throws Exception;

	/**
	 * 채용 공고 상세 (이전)
	 * @param recruitVO
	 * @return
	 * @throws Exception
	 */
	RecruitVO selectrecRuitNoticePrevDetail(RecruitVO vo) throws Exception;

	/**
	 * 채용 공고 상세 (다음)
	 * @param recruitVO
	 * @return
	 */
	RecruitVO selectrecRuitNoticeNextDetail(RecruitVO vo) throws Exception;

	/**
	 * 채용FAQ 리스트
	 * @param plusBbsVO
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> selectRecruitFaqList(PlusBbsVO vo) throws Exception;

	/**
	 * 채용 FAQ 개수
	 * @param plusBbsVO
	 * @return
	 */
	int selectRecruitFaqCnt(PlusBbsVO vo)  throws Exception;
}