package com.plushih.services.ci;


import com.plushih.common.ci.plusActiveRecord;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.FieldIndexEntity;
import com.plushih.entities.FieldInfoEntity;

import java.util.List;
import java.util.Map;

public interface CommonService extends CiService{

	public Map<String, Object> cmmFileList(Map<String, Object> paramMap) throws Exception;
	
	public Map<String, Object> userInfo(Object paramMap) throws Exception;


//    public CommonResultEntity getList(plusActiveRecord dbEntity) throws Exception ;
//    public CommonResultEntity getRow(plusActiveRecord dbEntity) throws Exception ;
//    public CommonResultEntity getRow(plusActiveRecord dbEntity) throws Exception ;
	

}
