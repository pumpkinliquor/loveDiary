package com.plushih.controllers.front.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plushih.controllers.front.service.FrontEtcService;
import com.plushih.controllers.front.vo.PlusBbsVO;
import com.plushih.daos.CommonDao;

@Service("frontEtcService")
public class FrontEtcServiceImpl implements FrontEtcService {

	@Autowired
	private CommonDao commonDao;

	/**
	 * FAQ리스트
	 */
	@Override
	public List<Map<String, Object>> selectFaqList(PlusBbsVO vo) {
		return commonDao.selectList("FrontEtcDAO.selectFaqList", vo);
	}



	

}
