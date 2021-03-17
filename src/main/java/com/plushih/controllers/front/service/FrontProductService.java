package com.plushih.controllers.front.service;

import java.util.List;
import java.util.Map;

import com.plushih.controllers.front.vo.PlusBbsVO;
import com.plushih.controllers.front.vo.ProductVO;

public interface FrontProductService {

	/**
	 * 제품 리스트
	 * @param productVO
	 * @return
	 */
	List<Map<String,Object>> selectProductList(ProductVO vo) throws Exception;

	/**
	 * 제품 개수
	 * @param productVO
	 * @return
	 */
	int selectProductCnt(ProductVO vo) throws Exception;

	/**
	 * 제품뉴스 리스트
	 * @param plusBbsVO
	 * @return
	 */
	List<Map<String, Object>> selectProductNewsList(PlusBbsVO plusBbsVO) throws Exception;

	/**
	 * 제품뉴스 리스트 개수
	 * @param plusBbsVO
	 * @return
	 */
	int selectProductNewsCnt(PlusBbsVO plusBbsVO) throws Exception;

	/**
	 * 제품 뉴스 상세
	 * @param plusBbsVO
	 * @return
	 */
	PlusBbsVO selectProductNewsDetail(PlusBbsVO plusBbsVO)  throws Exception;

	/**
	 * 제품 뉴스 상제 이전
	 * @param plusBbsVO
	 * @return
	 */
	PlusBbsVO selectProductNewsPrevDetail(PlusBbsVO plusBbsVO) throws Exception;

	/**
	 * 제품 뉴스 상제 다음
	 * @param plusBbsVO
	 * @return
	 */
	PlusBbsVO selectProductNewsNextDetail(PlusBbsVO plusBbsVO) throws Exception; 
}