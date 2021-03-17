package com.plushih.controllers.front.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plushih.controllers.front.service.FrontOurBusinessService;
import com.plushih.controllers.front.vo.PlusBbsVO;
import com.plushih.controllers.front.vo.ProductVO;
import com.plushih.daos.CommonDao;

@Service("frontOurBusinessService")
public class FrontOurBusinessServiceImpl implements FrontOurBusinessService {

	@Autowired
	private CommonDao commonDao;

	/**
	 * rnd 리스트
	 */
	@Override
	public List<Map<String, Object>> selectRndListList(PlusBbsVO vo) throws Exception {
		return commonDao.selectList("FrontOurBusinessDAO.selectRndListList", vo);
	}

	/**
	 * rnd 리스트 개수
	 */
	@Override
	public int selectRndListCnt(PlusBbsVO vo) throws Exception {
		return commonDao.selectOne("FrontOurBusinessDAO.selectRndListCnt", vo);
	}



}
