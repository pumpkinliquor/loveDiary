package com.plushih.controllers.ajax;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ibm.icu.text.SimpleDateFormat;
import com.plushih.common.ci.CoreController;
import com.plushih.common.constant.Code;
import com.plushih.common.constant.Default;
import com.plushih.common.constant.LoginSession;
import com.plushih.common.utils.HashUtils;
import com.plushih.common.utils.NumberUtils;
import com.plushih.controllers.front.service.FrontAlarmService;
import com.plushih.controllers.front.service.FrontUserService;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.UserMemberEntity;

@Controller
@RequestMapping("front/ajax/user")
public class AjaxUserController extends CoreController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AjaxSnsController.class);

	@Autowired
	private FrontAlarmService frontAlarmService;
	
	@Autowired
	private FrontUserService frontUserService;

	/**
	 * @ClassName : AjaxUserController.java
	 * @Method : userInfo
	 * @Date : 2021. 1. 19.
	 * @author : dev.khko
	 * @Description : 사용자 정보 조회
	 */
	@ResponseBody
	@RequestMapping(value = {"/userInfo"}, method = {RequestMethod.POST})
	public CommonResultEntity userInfo (HttpServletRequest request, UserMemberEntity userMemberEntity) throws Exception {
		CommonResultEntity commonResultEntity = new CommonResultEntity();

		commonResultEntity.setResultData((frontUserService.selectUserInfo(request, userMemberEntity)));

		 return commonResultEntity;
	}

	/**
	 * @ClassName : AjaxUserController.java
	 * @Method : userUpdate
	 * @Date : 2021. 1. 20.
	 * @author : dev.khko
	 * @Description : 사용자 정보 수정
	 */
	@ResponseBody
	@RequestMapping(value = {"/userUpdate"}, method = {RequestMethod.POST})
	public  CommonResultEntity userUpdate (MultipartHttpServletRequest request) throws Exception {
		CommonResultEntity commonResultEntity = new CommonResultEntity();

		commonResultEntity.setResultCode(frontUserService.updateUserInfo(request));

        return commonResultEntity;
	}

	/**
	 * @ClassName	: AjaxUserController.java
	 * @Method		: sendAuthcode
	 * @Date		: 2021. 1. 21.
	 * @author		: dev.khko
	 * @Description	: 휴대폰번호 인증 > SMS로 인증코드 전송
	 */
	@ResponseBody
	@RequestMapping(value = {"/sendSmsAuthcode","/sendSmsAuthcode/{lan}"}, method = {RequestMethod.POST})
	public CommonResultEntity sendSmsAuthcode (HttpServletRequest request, @RequestParam Map<String, Object> param) throws Exception {

		CommonResultEntity res = new CommonResultEntity();
		// 인증번호 생성 및 메일 발송
		String resultCode = frontUserService.sendSmsAuthcode(request, param);
		res.setResultCode(resultCode);

		return res;
	}

	/**
	 * @ClassName	: AjaxUserController.java
	 * @Method		: checkSmsAuthCode
	 * @Date		: 2021. 1. 21.
	 * @author		: dev.khko
	 * @Description	: 인증번호 유효성 체크 및 사용 처리
	 */
	@ResponseBody
	@RequestMapping(value = {"/checkSmsAuthCode","/checkSmsAuthCode/{lan}"}, method = {RequestMethod.POST})
	public CommonResultEntity checkSmsAuthCode (HttpServletRequest request, @RequestParam Map<String, Object> paramMap) throws Exception {

		CommonResultEntity res = new CommonResultEntity();
		Map<String, Object> authMap = new HashMap<>();

		// 인증번호 유효성 체크 및 사용 처리
		String resultCode = frontUserService.checkSmsAuthCode(request, paramMap);
		res.setResultCode(resultCode);

		// 비밀번호 재설정 페이지로의 비정상인 접근 제어
		// 인증번호로 sms 인증 성공 후에만 이동가능
		if(Code.Result.SUCC.equals(resultCode)) {
			authMap.put(Default.ResultValue.VIEW_ACCESS_KEY, HashUtils.encryptSHA256(new SimpleDateFormat().format(new Date())));
			authMap.put("memUserid", String.valueOf(paramMap.get("memUserid")));
		}
		res.setResultData(authMap);

		return res;
	}

	/**
	 * @ClassName : AjaxUserController.java
	 * @Method : levelList
	 * @Date : 2021. 1. 22.
	 * @author : dev.khko
	 * @Description : 사용자 정보 조회
	 */
	@ResponseBody
	@RequestMapping(value = {"/levelList"}, method = {RequestMethod.POST})
	public CommonResultEntity levelList (HttpServletRequest request) throws Exception {
		CommonResultEntity commonResultEntity = new CommonResultEntity();

		commonResultEntity.setResultList(frontUserService.selectLevelCode(request));

		 return commonResultEntity;
	}

	/**
	 * @ClassName : AjaxUserController.java
	 * @Method : subList
	 * @Date : 2021. 1. 22.
	 * @author : dev.khko
	 * @Description : 사용자 정보 조회
	 */
	@ResponseBody
	@RequestMapping(value = {"/subList"}, method = {RequestMethod.POST})
	public CommonResultEntity subList (HttpServletRequest request) throws Exception {
		CommonResultEntity commonResultEntity = new CommonResultEntity();

		commonResultEntity.setResultList(frontUserService.selectSubCode(request));

		return commonResultEntity;
	}

	/**
	 * @ClassName : AjaxUserController.java
	 * @Method : settingInfo
	 * @Date : 2021. 1. 25.
	 * @author : dev.khko
	 * @Description : 설정 정보 조회
	 */
	@ResponseBody
	@RequestMapping(value = {"/settingInfo"}, method = {RequestMethod.POST})
	public CommonResultEntity settingInfo (HttpServletRequest request, UserMemberEntity userMemberEntity) throws Exception {
		CommonResultEntity commonResultEntity = new CommonResultEntity();

		commonResultEntity.setResultData((frontUserService.selectSettingInfo(request, userMemberEntity)));

		return commonResultEntity;
	}

	/**
	 * @ClassName : AjaxUserController.java
	 * @Method : settingUpdate
	 * @Date : 2021. 1. 25.
	 * @author : dev.khko
	 * @Description : 설정 정보 수정
	 */
	@ResponseBody
	@RequestMapping(value = {"/settingUpdate"}, method = {RequestMethod.POST})
	public CommonResultEntity settinsettingUpdategInfo (HttpServletRequest request, UserMemberEntity userMemberEntity) throws Exception {
		CommonResultEntity commonResultEntity = new CommonResultEntity();

		return frontUserService.updateSettingInfo(request, userMemberEntity, commonResultEntity);
	}
	
	/**
	 * @ClassName : AjaxUserController.java
	 * @Method : settingUpdate
	 * @Date : 2021. 1. 25.
	 * @author : dev.khko
	 * @Description : 설정 정보 수정
	 */
	@ResponseBody
	@RequestMapping(value = {"/readAlarm"}, method = {RequestMethod.POST})
	public CommonResultEntity readAlarm (@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {
		CommonResultEntity commonResultEntity = new CommonResultEntity();
		
		// 필요한 데이터 조회
		paramMap.put("memUserid",LoginSession.getLoginId(request.getSession()));
		paramMap.put("memId", LoginSession.getSeq(request.getSession()));
		
		return frontAlarmService.readAlarm(paramMap, commonResultEntity);
	}
	
	/**
	 * @ClassName : AjaxUserController.java
	 * @Method : settingUpdate
	 * @Date : 2021. 1. 25.
	 * @author : dev.khko
	 * @Description : 설정 정보 수정
	 */
	@ResponseBody
	@RequestMapping(value = {"/notReadAlarm"}, method = {RequestMethod.POST})
	public CommonResultEntity notReadAlarm (@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {
		CommonResultEntity commonResultEntity = new CommonResultEntity();
		
		// 필요한 데이터 조회
		paramMap.put("memUserid",LoginSession.getLoginId(request.getSession()));
		paramMap.put("memId", LoginSession.getSeq(request.getSession()));
		
		commonResultEntity.setResultData(frontAlarmService.notReadAlarmCnt(paramMap));
		
		return commonResultEntity;
	}
	
	/**
	 * @ClassName	: AjaxUserController.java
	 * @Method		: checkAccount
	 * @Date		: 2021. 3. 12. 
	 * @author		: dev.yklee
	 * @Description	: 회원탈퇴 > 계정 확인
	 */
	@ResponseBody
	@RequestMapping(value = {"/checkAccount"}, method = {RequestMethod.POST})
	public CommonResultEntity checkAccount (UserMemberEntity userMemberEntity, HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam) throws Exception {
		
		CommonResultEntity res = new CommonResultEntity();
		// 필요한 데이터 조회
		userMemberEntity.setMemId(NumberUtils.stringToInt(LoginSession.getSeq(request.getSession())));
		res.setResultCode(frontUserService.checkUserAccount(userMemberEntity));
		
		return res;
	}
}
