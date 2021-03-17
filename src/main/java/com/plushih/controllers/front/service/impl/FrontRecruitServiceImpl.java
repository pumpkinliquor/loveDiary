package com.plushih.controllers.front.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plushih.controllers.front.service.FrontRecruitService;
import com.plushih.controllers.front.vo.PlusBbsVO;
import com.plushih.controllers.front.vo.RecruitVO;
import com.plushih.daos.CommonDao;

@Service("frontRecruitService")
public class FrontRecruitServiceImpl implements FrontRecruitService {

	@Autowired
	private CommonDao commonDao;

	/**
	 * 채용 메인 리스트
	 */
	@Override
	public List<Map<String, Object>> selectRecruitList(RecruitVO vo) throws Exception {
		return commonDao.selectList("FrontRecruitDAO.selectRecruitList", vo);
	}

	/**
	 * 현재 채용 건수
	 */
	@Override
	public int selectCurrRecruitCnt() throws Exception {
		return commonDao.selectOne("FrontRecruitDAO.selectCurrRecruitCnt");
	}

	/**
	 * 채용 전체 리스트
	 */
	@Override
	public List<Map<String, Object>> selectRecruitAllList(RecruitVO vo) throws Exception {
		return commonDao.selectList("FrontRecruitDAO.selectRecruitAllList", vo);
	}

	/**
	 * 채용 전체 공지 개수
	 */
	@Override
	public int selectRecruitAllCnt(RecruitVO vo) throws Exception {
		return commonDao.selectOne("FrontRecruitDAO.selectRecruitAllCnt",vo);
	}

	/**
	 * 채용 공지 상세
	 */
	@Override
	public RecruitVO selectrecRuitNoticeDetail(RecruitVO vo) throws Exception {
		return commonDao.selectOne("FrontRecruitDAO.selectrecRuitNoticeDetail",vo);
	}

	/**
	 * 채용 공지 상세(이전)
	 */
	@Override
	public RecruitVO selectrecRuitNoticePrevDetail(RecruitVO vo) throws Exception {
		return commonDao.selectOne("FrontRecruitDAO.selectrecRuitNoticePrevDetail",vo);
	}

	/**
	 * 채용 공지 상세(다음)
	 */
	@Override
	public RecruitVO selectrecRuitNoticeNextDetail(RecruitVO vo) throws Exception {
		return commonDao.selectOne("FrontRecruitDAO.selectrecRuitNoticeNextDetail",vo);
	}

	/**
	 * 채용 FAQ 리스트
	 */
	@Override
	public List<Map<String, Object>> selectRecruitFaqList(PlusBbsVO vo) throws Exception {
		return commonDao.selectList("FrontRecruitDAO.selectRecruitFaqList", vo);
	}

	/**
	 * 채용 FAQ 개수
	 */
	@Override
	public int selectRecruitFaqCnt(PlusBbsVO vo) throws Exception {
		return commonDao.selectOne("FrontRecruitDAO.selectRecruitFaqCnt", vo);
	}

}
