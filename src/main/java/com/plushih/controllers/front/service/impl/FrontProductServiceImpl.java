package com.plushih.controllers.front.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plushih.controllers.front.service.FrontProductService;
import com.plushih.controllers.front.vo.PlusBbsVO;
import com.plushih.controllers.front.vo.ProductVO;
import com.plushih.daos.CommonDao;

@Service("frontProductService")
public class FrontProductServiceImpl implements FrontProductService {

	@Autowired
	private CommonDao commonDao;

	/**
	 * 제품 리스트
	 */
	@Override
	public List<Map<String,Object>> selectProductList(ProductVO vo) throws Exception {
		return commonDao.selectList("FrontProductDAO.selectProductList", vo);
	}

	/**
	 * 제품 개수
	 */
	@Override
	public int selectProductCnt(ProductVO vo) throws Exception {
		return commonDao.selectOne("FrontProductDAO.selectProductCnt",vo);
	}
	
	/**
	 * 제퓸 뉴스 리스트
	 */
	@Override
	public List<Map<String, Object>> selectProductNewsList(PlusBbsVO vo) throws Exception {
		return commonDao.selectList("FrontProductDAO.selectProductNewsList", vo);
	}

	/**
	 * 제퓸 뉴스 개수
	 */
	@Override
	public int selectProductNewsCnt(PlusBbsVO vo) throws Exception {
		return commonDao.selectOne("FrontProductDAO.selectProductNewsCnt", vo);
	}

	/**
	 * 제품 뉴스 상세
	 */
	@Override
	public PlusBbsVO selectProductNewsDetail(PlusBbsVO vo) throws Exception {
		return commonDao.selectOne("FrontProductDAO.selectProductNewsDetail", vo);
	}

	/**
	 * 제품 뉴스 상세 이전
	 */
	@Override
	public PlusBbsVO selectProductNewsPrevDetail(PlusBbsVO vo) throws Exception {
		return commonDao.selectOne("FrontProductDAO.selectProductNewsPrevDetail",vo);
	}

	/**
	 * 제품 뉴스 상세 다음
	 */
	@Override
	public PlusBbsVO selectProductNewsNextDetail(PlusBbsVO vo) throws Exception {
		return commonDao.selectOne("FrontProductDAO.selectProductNewsNextDetail",vo);
	}



}
