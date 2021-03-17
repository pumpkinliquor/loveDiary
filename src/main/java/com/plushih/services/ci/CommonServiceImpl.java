package com.plushih.services.ci;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.plushih.common.constant.Code;
import com.plushih.daos.CommonDao;

@Service("CommonService")
public class CommonServiceImpl extends CiServiceImpl implements CommonService{
	
	@Autowired
	private CommonDao commonDao;
	
	@Override
	public Map<String, Object> cmmFileList(Map<String, Object> paramMap) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			// 결과 Set
			resultMap.put("list", commonDao.selectList("Common.cmmFileList", paramMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	@Override
	public Map<String, Object> userInfo(Object paramMap) throws Exception {
		
		return commonDao.selectOne("Common.loginUserInfo", paramMap);
	}

}
