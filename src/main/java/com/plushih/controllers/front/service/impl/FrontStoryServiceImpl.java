package com.plushih.controllers.front.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plushih.controllers.front.service.FrontStoryService;
import com.plushih.controllers.front.vo.PlusBbsVO;
import com.plushih.daos.CommonDao;

@Service("frontStoryService")
public class FrontStoryServiceImpl implements FrontStoryService {

	@Autowired
	private CommonDao commonDao;

	/**
	 * 방송광고 리스트 조회
	 */
	@Override
	public List<Map<String, Object>> selectAdList(PlusBbsVO vo) throws Exception {
		return commonDao.selectList("FrontStoryDAO.selectAdList", vo);
	}

	/**
	 * 방송광고 리스트 총 개수
	 */
	@Override
	public int selectAdCnt(PlusBbsVO vo) throws Exception {
		return commonDao.selectOne("FrontStoryDAO.selectAdCnt", vo);
	}

	/**
	 * 방송광고 상세
	 */
	@Override
	public PlusBbsVO selectAdDetail(PlusBbsVO vo) throws Exception {
		return commonDao.selectOne("FrontStoryDAO.selectAdDetail", vo);
	}

	/**
	 * 보도자료 리스트
	 */
	@Override
	public List<Map<String, Object>> selectProductNewsList(PlusBbsVO vo) throws Exception {
		return commonDao.selectList("FrontStoryDAO.selectProductNewsList", vo);
	}

	/**
	 * 보도자료 개수 
	 */
	@Override
	public int selectProductNewsCnt(PlusBbsVO vo) throws Exception {
		return commonDao.selectOne("FrontStoryDAO.selectProductNewsCnt", vo);
	}

	/**
	 * 보도자료 상세
	 */
	@Override
	public PlusBbsVO selectPressReleaseDetail(PlusBbsVO vo) throws Exception {
		return commonDao.selectOne("FrontStoryDAO.selectPressReleaseDetail", vo);
	}

	/**
	 * 보도자료 상세 이전
	 */
	@Override
	public PlusBbsVO selectPressReleaseDetailPrevDetail(PlusBbsVO vo) throws Exception {
		return commonDao.selectOne("FrontStoryDAO.selectPressReleaseDetailPrevDetail",vo);
	}

	/**
	 * 보도자료 상세 다음
	 */
	@Override
	public PlusBbsVO selectPressReleaseDetailNextDetail(PlusBbsVO vo) throws Exception {
		return commonDao.selectOne("FrontStoryDAO.selectPressReleaseDetailNextDetail",vo);
	}

	

}
