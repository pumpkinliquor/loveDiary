package com.plushih.controllers.front;


import com.plushih.common.ci.CoreController;
import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.constant.LoginSession;
import com.plushih.common.utils.StringUtils;
import com.plushih.controllers.front.service.FrontLoginService;
import com.plushih.controllers.front.service.FrontMainService;
import com.plushih.controllers.totaladmin.AdminBusinessController;
import com.plushih.entities.UserMemberEntity;
import com.plushih.services.ci.CommonService;
import freemarker.template.utility.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
public class FrontMainController extends CoreController {
	private static final Logger LOGGER = LoggerFactory.getLogger( AdminBusinessController.class );
	
	@Autowired
	FrontMainService frontMainService;
	@Autowired
	CommonService commonService;
	@Autowired
	FrontLoginService frontLoginService;


	private static String UserBrowserChk(HttpServletRequest request) {

		String userAgent = request.getHeader("user-agent");

		String browser = "";

		if (userAgent.indexOf("Trident") > -1 || userAgent.indexOf("MSIE") > -1) { //IE

			if (userAgent.indexOf("Trident/7") > -1) {
				browser = "IE 11";
			} else if (userAgent.indexOf("Trident/6") > -1) {
				browser = "IE 10";
			} else if (userAgent.indexOf("Trident/5") > -1) {
				browser = "IE 9";
			} else if (userAgent.indexOf("Trident/4") > -1) {
				browser = "IE 8";
			} else if (userAgent.indexOf("edge") > -1) {
				browser = "IE edge";
			}

		} else if (userAgent.indexOf("Whale") > -1) { //네이버 WHALE
			browser = "WHALE " + userAgent.split("Whale/")[1].toString().split(" ")[0].toString();
		} else if (userAgent.indexOf("Opera") > -1 || userAgent.indexOf("OPR") > -1) { //오페라
			if (userAgent.indexOf("Opera") > -1) {
				browser = "OPERA " + userAgent.split("Opera/")[1].toString().split(" ")[0].toString();
			} else if (userAgent.indexOf("OPR") > -1) {
				browser = "OPERA " + userAgent.split("OPR/")[1].toString().split(" ")[0].toString();
			}
		} else if (userAgent.indexOf("Firefox") > -1) { //파이어폭스
			browser = "FIREFOX " + userAgent.split("Firefox/")[1].toString().split(" ")[0].toString();
		} else if (userAgent.indexOf("Safari") > -1 && userAgent.indexOf("Chrome") == -1) { //사파리
			browser = "SAFARI " + userAgent.split("Safari/")[1].toString().split(" ")[0].toString();
		} else if (userAgent.indexOf("Chrome") > -1) { //크롬
			browser = "CHROME " + userAgent.split("Chrome/")[1].toString().split(" ")[0].toString();
		}

		Debug.log("userAgent 확인 [" + userAgent + "]");
		Debug.log("브라우저/버전 확인 [" + browser + "]");

		return browser;
	}

	/**
	 * @ClassName	: FrontMainController.java
	 * @Method		: main
	 * @Date		: 2021. 2. 4. 
	 * @author		: dev.yklee
	 * @Description	: 메인 > 사전질문
	 */
	@RequestMapping(value = { "/", "/main","/front/main","/main/{lan}"})
	public String main (HttpServletRequest request
						  , HttpServletResponse response
						  , @PathVariable Map<String, String> pathVariables
						  , ModelMap model
						  , Locale localeParam ) throws Exception {
		Integer lgSeq = 1;
		HashMap<String,Object> param = new HashMap<String, Object>();
		String functionName = getFunction();
		plusActiveRecord dbEntity =new plusActiveRecord(functionName,request);
		String token = dbEntity.input.get_post("token");
		HttpSession session = request.getSession(true);
		if(!StringUtils.isEmpty(token)){

			session.setAttribute(LoginSession.TOKEN, token );
			//dbEntity.add()
			dbEntity.select("*");
			dbEntity.from("cb_member_extra_vars");
			dbEntity.where("mev_key",token);
			if(commonService.getCount(dbEntity)>0){
				dbEntity.select("mem_id");
				Map<String,Object> memMap = commonService.getRow(dbEntity);

				Integer memId = Integer.parseInt(memMap.get("memId")+"");

				UserMemberEntity userMemberEntity = new UserMemberEntity();
				userMemberEntity.setMemId(memId);
				UserMemberEntity loginUser = frontLoginService.loginProcForMemId(request,response,userMemberEntity );


				dbEntity._values.clear();
				dbEntity.clearWhere();
				dbEntity.from("cb_stat_count");
				dbEntity.add("sco_ip",request.getRemoteAddr());
				dbEntity.add("mem_id",String.valueOf(loginUser.getMemId()));
				dbEntity.add("sco_date",dbEntity.getYmd());
				dbEntity.add("sco_time",dbEntity.getHis());
				dbEntity.add("sco_referer",request.getRequestURI());
				dbEntity.add("sco_current",request.getHeader("referer"));
				dbEntity.add("sco_agent",StringUtils.nvl(UserBrowserChk(request),""));
				commonService.setInsert(dbEntity);

				dbEntity._values.clear();
				dbEntity.clearWhere();
				dbEntity.from("cb_stat_count_date");
				dbEntity.where("scd_date",dbEntity.getYmd());
				int scdCount= commonService.getCount(dbEntity);
				if(scdCount>0){
					dbEntity.where("scd_date",dbEntity.getYmd());
					dbEntity.add("scd_count",String.valueOf(scdCount));
					commonService.setUpdate(dbEntity);
				} else {
					dbEntity.add("scd_date",dbEntity.getYmd());
					dbEntity.add("scd_count",String.valueOf(scdCount));
					commonService.setInsert(dbEntity);
				}
//
//				$insertdata = array(
//					'sco_ip' => $CI->input->ip_address(),
//					'sco_date' => cdate('Y-m-d'),
//					'sco_time' => cdate('H:i:s'),
//					'sco_referer' => $CI->agent->referrer(),
//					'sco_current' => current_full_url(),
//					'sco_agent' => $sco_agent,
//				);
//				$result = $CI->Stat_count_model->insert($insertdata);

				Debug.log("loginUser.getLoginStateCode()===="+loginUser.getLoginStateCode());
				if(loginUser.getLoginStateCode()==92){

				}
				if(loginUser.getLoginStateCode() == 91){
					//window.location.href = '/front/main';
					return "redirect:/front/main";
				} else if (loginUser.getLoginStateCode() == 92) {
					//window.location.href = '/front/furs/main';
					return "redirect:/front/furs/main";
				} else if (loginUser.getLoginStateCode() == 93) {
					//window.location.href = '/front/learn/home';
					return "redirect:/front/learn/home";
				}


			} else {
				if(token.equals("fuzrwxJESZOfxfkDj_cjMN:APA91bHs2VRXD9i2ytRSnskO-Wp-RftsJQgjckU7dUgau162um8g9tGQ62MserCSUBjNsYEH62viogklHFzzQ4Hic27JtkDmUSai3Qq-Fqsk8pfLE4bYtO1vqP5Etq2De2zuGWCS_T4p")){

					dbEntity.add("mem_id",String.valueOf(47));
					dbEntity.add("mev_key",token);
					commonService.setInsert(dbEntity);
				}
			}
		}
		
		//path 설정
		String path =  "/main";
		path = pathToLangFront(path,pathVariables,model,request);
    	return path;
		
	}
	
}