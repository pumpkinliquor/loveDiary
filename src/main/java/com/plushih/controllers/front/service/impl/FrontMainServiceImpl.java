package com.plushih.controllers.front.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plushih.controllers.front.service.FrontMainService;
import com.plushih.daos.CommonDao;

@Service("frontMainService")
public class FrontMainServiceImpl implements FrontMainService {

	@Autowired
	private CommonDao commonDao;

	/**
	 * 메인베너 리스트
	 */
	@Override
	public List<Map<String, Object>> selectMainBannerList(Map<String,Object> param) throws Exception {
		return commonDao.selectList("FrontMainDAO.selectMainBannerList",param);
	}

}
