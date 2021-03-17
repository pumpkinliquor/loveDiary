package com.plushih.controllers.ajax.plusadmin;


import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.utils.StringUtils;
import com.plushih.controllers.ajax.AjaxCodeController;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.UserMemberEntity;
import com.plushih.entities.UserMemberHistoryEntity;
import com.plushih.entities.UserMemberStatisticsEntity;
import com.plushih.services.admin.AdminMemberService;
import com.plushih.services.ci.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("plusadmin/ajax/member")
public class AjaxUsersMemberAdminController extends AjaxCodeController {
	
	@Autowired
	private AdminMemberService adminMemberService;
	
	@Autowired
	private CommonService commonService;
	
	
	/**
	 * @ClassName	: AjaxUsersMemberAdminController.java
	 * @Method		: memberList
	 * @Date		: 2021. 1. 6. 
	 * @author		: dev.yklee
	 * @Description	: Aigo 사용자 리스트 조회
	 */
	@ResponseBody
	@RequestMapping(value = "/memberList", method = {RequestMethod.POST})
	public CommonResultEntity memberList (HttpServletRequest request, HttpServletResponse response, Locale localeParam, UserMemberEntity userMemberEntity) throws Exception {
		
		String fullName = getFunction();
		logStart(fullName);
		
		String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
		
		plusActiveRecord dbEntity = new plusActiveRecord(functionName, request);
		
		// 쿼리 호출
		List<UserMemberEntity> dataList = adminMemberService.getUserMemberList(dbEntity, userMemberEntity);
		
		// 결과값 Set
		CommonResultEntity res = new CommonResultEntity();
		
		try {
			int cnount = commonService.getCount(dbEntity);
			res.setDraw(dbEntity.draw);
			res.setRecordsTotal(cnount);
			res.setRecordsFiltered(cnount);
			res.setResultList(dataList);
			Debug.log(dataList.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logEnd(fullName);
		return res;
	}
	
	/**
	 * @ClassName	: AjaxUsersMemberAdminController.java
	 * @Method		: memberNickHistoryList
	 * @Date		: 2021. 1. 8. 
	 * @author		: dev.yklee
	 * @Description	: 사용자 닉네임 히스토리
	 */
	@ResponseBody
	@RequestMapping(value = "/memberNickHistoryList", method = {RequestMethod.POST, RequestMethod.GET})
	public CommonResultEntity memberNickHistoryList (HttpServletRequest request, HttpServletResponse response, Locale localeParam, UserMemberHistoryEntity userMemberHistoryEntity) throws Exception {
		
		String fullName = getFunction();
		logStart(fullName);
		
		String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
		
		plusActiveRecord dbEntity = new plusActiveRecord(functionName, request);
		
		// 쿼리 호출
		List<UserMemberHistoryEntity> dataList = adminMemberService.getUserMemberNickHistoryList(dbEntity, userMemberHistoryEntity);
		
		// 결과값 Set
		CommonResultEntity res = new CommonResultEntity();
		
		try {
			int cnount = commonService.getCount(dbEntity);
			res.setDraw(dbEntity.draw);
			res.setRecordsTotal(cnount);
			res.setRecordsFiltered(cnount);
			res.setResultList(dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logEnd(fullName);
		return res;
	}
	
	/**
	 * @ClassName	: AjaxUsersMemberAdminController.java
	 * @Method		: memberLevelHistoryList
	 * @Date		: 2021. 1. 8. 
	 * @author		: dev.yklee
	 * @Description	: 사용자 레벨 히스토리
	 */
	@ResponseBody
	@RequestMapping(value = "/memberLevelHistoryList", method = {RequestMethod.POST, RequestMethod.GET})
	public CommonResultEntity memberLevelHistoryList (HttpServletRequest request, HttpServletResponse response, Locale localeParam, UserMemberHistoryEntity userMemberHistoryEntity) throws Exception {
		
		String fullName = getFunction();
		logStart(fullName);
		
		String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
		
		plusActiveRecord dbEntity = new plusActiveRecord(functionName, request);
		
		// 쿼리 호출
		List<UserMemberHistoryEntity> dataList = adminMemberService.getUserMemberLevelHistoryList(dbEntity, userMemberHistoryEntity);
		
		// 결과값 Set
		CommonResultEntity res = new CommonResultEntity();
		
		try {
			int cnount = commonService.getCount(dbEntity);
			res.setDraw(dbEntity.draw);
			res.setRecordsTotal(cnount);
			res.setRecordsFiltered(cnount);
			res.setResultList(dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logEnd(fullName);
		return res;
	}
	
	/**
	 * @ClassName	: AjaxUsersMemberAdminController.java
	 * @Method		: memberPushInfoList
	 * @Date		: 2021. 1. 8. 
	 * @author		: dev.yklee
	 * @Description	: 푸시 수신동의항목 조회
	 */
	@ResponseBody
	@RequestMapping(value = "/memberPushInfoList", method = {RequestMethod.POST, RequestMethod.GET})
	public CommonResultEntity memberPushInfoList (HttpServletRequest request, HttpServletResponse response, Locale localeParam, UserMemberEntity userMemberEntity) throws Exception {
		
		String fullName = getFunction();
		logStart(fullName);
		
		String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
		
		plusActiveRecord dbEntity = new plusActiveRecord(functionName, request);
		
		// 쿼리 호출
		List<UserMemberEntity> dataList = adminMemberService.getUserMemberPushInfoList(dbEntity, userMemberEntity);
		
		// 결과값 Set
		CommonResultEntity res = new CommonResultEntity();
		
		try {
			int cnount = commonService.getCount(dbEntity);
			res.setDraw(dbEntity.draw);
			res.setRecordsTotal(cnount);
			res.setRecordsFiltered(cnount);
			res.setResultList(dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logEnd(fullName);
		return res;
	}
	
	/**
	 * @ClassName	: AjaxUsersMemberAdminController.java
	 * @Method		: memberUsedDataInfo
	 * @Date		: 2021. 1. 13. 
	 * @author		: dev.yklee
	 * @Description	: 회원집계 검색영역 요약데이터
	 */
	@ResponseBody
	@RequestMapping(value = "/memberUsedDataInfo", method = {RequestMethod.POST})
	public CommonResultEntity memberUsedDataInfo (HttpServletRequest request, HttpServletResponse response, Locale localeParam, UserMemberStatisticsEntity userMemberStatisticsEntity) throws Exception {
		
		String fullName = getFunction();
		logStart(fullName);
		
		String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
		
		plusActiveRecord dbEntity = new plusActiveRecord(functionName, request);
		
		// 검색영역 카운트 데이터
		UserMemberStatisticsEntity usedData  = adminMemberService.getUserMemberUsedInfo(dbEntity, userMemberStatisticsEntity);
		
		// 결과값 Set
		CommonResultEntity res = new CommonResultEntity();
		
		try {
			int cnount = commonService.getCount(dbEntity);
			res.setDraw(dbEntity.draw);
			res.setRecordsTotal(cnount);
			res.setRecordsFiltered(cnount);
			res.setResultValue(usedData);
			res.add("sdate",dbEntity.input.get_post("sdate"));
			res.add("edate",dbEntity.input.get_post("edate"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logEnd(fullName);
		return res;
	}
	
	/**
	 * @ClassName	: AjaxUsersMemberAdminController.java
	 * @Method		: memberUsedDataList
	 * @Date		: 2021. 1. 13. 
	 * @author		: dev.yklee
	 * @Description	: 회원집계 일자별 사용 데이터 리스트
	 */
	@ResponseBody
	@RequestMapping(value = "/memberUsedDataList", method = {RequestMethod.POST})
	public CommonResultEntity memberUsedDataList (HttpServletRequest request, HttpServletResponse response, Locale localeParam, UserMemberStatisticsEntity userMemberStatisticsEntity) throws Exception {
		
		String fullName = getFunction();
		logStart(fullName);
		
		String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
		
		plusActiveRecord dbEntity = new plusActiveRecord(functionName, request);
		
		// 일자별 데이터 조회 리스트
		List<UserMemberStatisticsEntity> dataList = adminMemberService.getUserMemberUsedDataList(dbEntity, userMemberStatisticsEntity);
		
		// 결과값 Set
		CommonResultEntity res = new CommonResultEntity();
		
		try {
			int cnount = commonService.getCount(dbEntity);
			res.setDraw(dbEntity.draw);
			res.setRecordsTotal(cnount);
			res.setRecordsFiltered(cnount);
			res.setResultList(dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logEnd(fullName);
		return res;
	}

	/**
     * 사업장관리
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/memberLoginLogList", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsList (HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        //commonResultEntity = levelService.getlevelList(db);
        List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
        db.select("bb.*");
        db.from("cb_member_login_log bb");
        db.join("cb_member b","bb.mem_id = b.mem_id","left");

        if(!StringUtils.isEmpty(db.input.get_post("memUserid"))){
            db.like("b.mem_userid",db.input.get_post("memUserid"));
        }
        if(!StringUtils.isEmpty(db.input.get_post("sdate")) && !StringUtils.isEmpty(db.input.get_post("edate"))){
             db.gteq("substr(bb.reg_sysdate,1,10)",db.input.get_post("sdate"));
             db.lteq("substr(bb.reg_sysdate,1,10)",db.input.get_post("edate"));
        } else if(!StringUtils.isEmpty(db.input.get_post("sdate"))){
            db.gteq("substr(bb.reg_sysdate,1,10)",db.input.get_post("sdate"));
        } else if(!StringUtils.isEmpty(db.input.get_post("edate"))){
            db.lteq("substr(bb.reg_sysdate,1,10)",db.input.get_post("edate"));

        }
        db.order("bb.reg_sysdate","desc");

        CommonResultEntity res = new CommonResultEntity();
        try {
            dataHashList  = commonService.getList(db);

            int cnount = commonService.getCount(db);
            res.setDraw(db.draw);
            res.setRecordsTotal(cnount);
            res.setRecordsFiltered(cnount);
            res.setResultList(dataHashList);


            Debug.log(dataHashList.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        logEnd(fullName);
        return res;
    }
}