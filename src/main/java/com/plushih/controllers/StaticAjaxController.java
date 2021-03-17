package com.plushih.controllers;


import com.plushih.common.constant.LoginSession;
import com.plushih.common.ci.CoreController;
import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.constant.Default;
import com.plushih.common.utils.HashUtils;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.MenuUsersEntity;
import com.plushih.entities.UserMasterEntity;
import com.plushih.entities.common.CommonRuntimeException;
import com.plushih.services.front.MenusService;
import com.plushih.services.front.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("static/ajax")
public class StaticAjaxController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( StaticAjaxController.class );

    @Autowired
    private UsersService usersService;

    @Autowired
    private MenusService menusService;
    /**
     *  회원 -> 회원 조회
     * @param userMasterEntity
     * @return CommonResultEntity
     */
    @RequestMapping(value = "/loginInfo", method = RequestMethod.POST)
    public @ResponseBody
    CommonResultEntity usersInfo(UserMasterEntity userMasterEntity, Model model, HttpServletRequest request) {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);

        String resultCode = Default.ResultValue.SUCCESS;
        String resultMessage = "login.success";

        try {
            HashUtils hashUtils             = new HashUtils();
            Debug.log(userMasterEntity);
            userMasterEntity.setUmPw(hashUtils.encryptSHA256(userMasterEntity.getUmPw()));
            //usersMasterEntity   = usersService.getUsersInfo(usersMasterEntity);

            db.where("um_Id",userMasterEntity.getUmId());
            db.where("um_pw",userMasterEntity.getUmPw());
//            db.where("um_pw","4d4f26369171994f3a46776ee2d88494fb9955800a5bb6261c016c4bb9f30b56");
             userMasterEntity   = usersService.getUserInfo(db,userMasterEntity);

            String remoteAddress = request.getRemoteAddr();

            //22. 아이디 정장 클릭시 아이디 저장

//            DataUtils.removeCookie(request, response, "SAVE_ID");
//            if(Consts.YN.TRUE.equals(saveYn)){
//                //쿠키에 SAVE_ID 갱신
//                DataUtils.setCookie(request, response, "SAVE_ID", userId, "", "UTF-8");
//            }


            /*
            int doCheckLogin  = 0;
            if(StringUtils.isEmpty(usersMasterEntity.getIumId())==true){
                doCheckLogin++;
            }
            if(StringUtils.isEmpty(usersMasterEntity.getIumStep())==true){
                doCheckLogin++;
            }
            */

            System.out.println("doCheckLogin >>>" + userMasterEntity);
            Debug.log("userMasterEntity = "+(userMasterEntity==null?"null " :"ObjectOK ") );

            if(userMasterEntity == null){
                resultMessage = "login.empty";
                resultCode = Default.ResultValue.FAIL;
            }
            else {
                if(userMasterEntity.getUmStep().equals(Default.UserStep.OK)){

                    HttpSession session = request.getSession(true);

                    db.clearWhere();
                    db.where("mg.um_seq",String.valueOf(userMasterEntity.getUmSeq()));
                    db.where("mu.ma_level","1");
                    db.order("mu.ma_sort");
                    //List<MenuUsersEntity> ar = menusService.getMenuUsersList(db,new MenuUsersEntity());
                    session.setAttribute(LoginSession.Menu1,menusService.getMenuAdmList(db,new MenuUsersEntity()));
                    db.where("mu.ma_level","2");
                    db.order("mu.ma_sort");
                    session.setAttribute(LoginSession.Menu2,menusService.getMenuAdmList(db,new MenuUsersEntity()));

                    db.where("mu.ma_level","3");
                    db.order("mu.ma_sort");
                    session.setAttribute(LoginSession.Menu3,menusService.getMenuAdmList(db,new MenuUsersEntity()));
                    session.setAttribute(LoginSession.INFO, userMasterEntity);
                    session.setAttribute(LoginSession.ID, userMasterEntity.getUmId());
                    session.setAttribute(LoginSession.SEQ, userMasterEntity.getUmSeq());
                    session.setAttribute(LoginSession.NAME, userMasterEntity.getUmName());
                    session.setAttribute(LoginSession.GRANT, String.valueOf(userMasterEntity.getUgSeq()));
                    session.setAttribute(LoginSession.IP, remoteAddress);
                    resultMessage = "login.success";

                }else if(userMasterEntity.getUmStep().equals(Default.UserStep.ST)) { //대기
                    resultMessage = "login.wait";
                    resultCode = Default.ResultValue.FAIL;
                }else if(userMasterEntity.getUmStep().equals(Default.UserStep.NO)) { //반려
                    resultMessage = "login.cancel";
                    resultCode = Default.ResultValue.FAIL;
                }else if(userMasterEntity.getUmType().equals(Default.UserWork.DISABLE)) { //휴면
                    resultMessage = "login.dormancy";
                    resultCode = Default.ResultValue.FAIL;
                } else {
                    resultMessage = "login.empty";
                    resultCode = Default.ResultValue.FAIL;
                }
            }

        } catch (CommonRuntimeException e) {
            e.printStackTrace();
            resultCode = e.getResultCode();
            resultMessage = e.getResultMessage();
        } catch (Exception e) {
            e.printStackTrace();
            resultCode = Default.ResultValue.FAIL;
            resultMessage = "login.fail";
            LOGGER.error(e.getMessage());
        }

        commonResultEntity.setResultCode(resultCode);
        commonResultEntity.setResultMsg(resultMessage);
        logEnd(fullName);
        return commonResultEntity;
    }
    /**
     * 로그아웃 프로세스.
     *
     * @param model Model
     * @param request HttpServletRequest
     * @return String
     */
    @RequestMapping(value = "/loginOut")
    public String logoutAction(Model model, HttpServletRequest request)  {
        CommonResultEntity resultEntity = new CommonResultEntity();
        HttpSession session = request.getSession(true);
        if(session != null){
            session.removeAttribute(LoginSession.INFO);
            session.removeAttribute(LoginSession.NAME);
            session.removeAttribute(LoginSession.ID);
            session.removeAttribute(LoginSession.DATE);
            session.removeAttribute(LoginSession.IP);
            session.invalidate();  // 초기화 해주고
        }
        resultEntity.setResultCode("OK");
        return "redirect:/front/main";


    }
    /**
     * 로그아웃 프로세스.
     *
     * @param model Model
     * @param request HttpServletRequest
     * @return String
     */
    @RequestMapping(value = "/adminloginOut")
    public String adminloginOut(Model model, HttpServletRequest request)  {
        CommonResultEntity resultEntity = new CommonResultEntity();
        HttpSession session = request.getSession(true);
        if(session != null){
            session.removeAttribute(LoginSession.INFO);
            session.removeAttribute(LoginSession.NAME);
            session.removeAttribute(LoginSession.ID);
            session.removeAttribute(LoginSession.DATE);
            session.removeAttribute(LoginSession.IP);
            session.invalidate();  // 초기화 해주고
        }
        resultEntity.setResultCode("OK");
        return "redirect:/plusadmin/main";


    }

    /**
     *  회원 -> 회원 수정
     * @param userMasterEntity
     * @return GridPageEntity
     */
    @RequestMapping(value = "/updatePasswd", method = RequestMethod.POST)
    public @ResponseBody CommonResultEntity usersUpdatePasswd(UserMasterEntity userMasterEntity) {
        HashUtils hashUtils             = new HashUtils();
        CommonResultEntity commonResultEntity   = new CommonResultEntity();
//        SavedFileEntity savedFileEntity = new SavedFileEntity();
//        String resultCode               = Default.ResultValue.SUCCESS;
//        String resultMessage            = "update.success";
//        String resultUrl                = "refresh";
//        try {
//
//            //#1. 회원체크(Id 와 Email 로 같은 계정인지 체크)StaticRestControllerStaticRestController
//            String password = usersMasterEntity.getIumPw();
//            usersMasterEntity.setIumPw("");
//            gridPageEntity = usersService.getUsersMasterList(usersMasterEntity);
//
//            if(gridPageEntity.getTotal() > 0) {
//                usersMasterEntity.setIumSeq(Integer.parseInt(String.valueOf(gridPageEntity.getRecord())));
//                usersMasterEntity.setIumPw(hashUtils.encryptSHA256(password.toString()));
//
//                //#2. 회원수정
//                gridPageEntity = usersService.updateUsersMaster(usersMasterEntity);
//                resultMessage = "password.success";
//            } else {
//                resultMessage = "password.fail";
//                resultCode    = Default.ResultValue.FAIL;
//            }
//
//        } catch (CommonRuntimeException e) {
//            resultCode                  = e.getResultCode();
//            resultMessage               = e.getResultMessage();
//            resultUrl                   = "none";
//        } catch (Exception e) {
//            resultCode                  = Default.ResultValue.FAIL;
//            resultMessage               = "update.fail";
//            resultUrl                   = "none";
//            LOGGER.error(e.getMessage());
//        }
//        gridPageEntity.setPage(usersMasterEntity.getPage());
//        gridPageEntity.setResultCode(resultCode);
//        gridPageEntity.setResultMessage(messages.getMessage(resultMessage));
//        gridPageEntity.setResultUrl(resultUrl);

        return commonResultEntity;
    }

    /**
     *  회원 -> 회원 생성
     * @param userMasterEntity
     * @return CommonResultEntity
     */
    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public @ResponseBody CommonResultEntity insertUser(UserMasterEntity userMasterEntity) {
        HashUtils hashUtils             = new HashUtils();
        CommonResultEntity commonResultEntity   = new CommonResultEntity();
//        SavedFileEntity savedFileEntity = new SavedFileEntity();
//        String resultCode               = Default.ResultValue.SUCCESS;
//        String resultMessage            = "save.success";
//        String resultUrl                = "refresh";
//        try {
//
//            //#1. 회원체크(Id 와 Email 로 같은 계정인지 체크)
//            String iumId = usersMasterEntity.getIumId();
//            String iumName = usersMasterEntity.getIumName();
//            String iumHp = usersMasterEntity.getIumHp();
//            String iumEmail = usersMasterEntity.getIumEmail();
//            String iumPw = usersMasterEntity.getIumPw();
//            usersMasterEntity = new UsersMasterEntity();
//            usersMasterEntity.setIumId(iumId);
//            gridPageEntity = usersService.getUsersMasterList(usersMasterEntity);
//
//            if(gridPageEntity.getTotal() > 0) {
//                resultMessage = "newuser.fail";
//                resultCode = Default.ResultValue.FAIL;
//            } else {
//                usersMasterEntity.setIumId(iumId);
//                usersMasterEntity.setIumName(iumName);
//                usersMasterEntity.setIumHp(iumHp);
//                usersMasterEntity.setIumTel(iumHp);
//                usersMasterEntity.setIumEmail(iumEmail);
//                usersMasterEntity.setIumPw(hashUtils.encryptSHA256(iumPw.toString()));
//                usersMasterEntity.setIumWork(Default.UserWork.WORK); //재직
//                usersMasterEntity.setIumStep(Default.UserStep.ST);   //대기
//                usersMasterEntity.setIumType(Default.UserType.W);    //작업자
//
//                //#2. 회원수정
//                int count = usersService.insertUsersMaster(usersMasterEntity);
//                if (count > 0) {
//                    resultMessage = "newuser.success";
//                } else {
//                    resultMessage = "save.fail";
//                }
//            }
//
//        } catch (CommonRuntimeException e) {
//            resultCode                  = e.getResultCode();
//            resultMessage               = e.getResultMessage();
//            resultUrl                   = "none";
//        } catch (Exception e) {
//            resultCode                  = Default.ResultValue.FAIL;
//            resultMessage               = "save.fail";
//            resultUrl                   = "none";
//            LOGGER.error(e.getMessage());
//        }
//        gridPageEntity.setPage(usersMasterEntity.getPage());
//        gridPageEntity.setResultCode(resultCode);
//        gridPageEntity.setResultMessage(messages.getMessage(resultMessage));
//        gridPageEntity.setResultUrl(resultUrl);

        return commonResultEntity;
    } //selectId

    /**
     *  회원 -> 회원 생성
     * @param userMasterEntity
     * @return GridPageEntity
     */
    @RequestMapping(value = "/selectUser", method = RequestMethod.POST)
    public @ResponseBody CommonResultEntity selectUser(UserMasterEntity userMasterEntity) {
        HashUtils hashUtils             = new HashUtils();
        CommonResultEntity commonResultEntity   = new CommonResultEntity();
//        SavedFileEntity savedFileEntity = new SavedFileEntity();
//        String resultCode               = Default.ResultValue.SUCCESS;
//        String resultMessage            = "save.success";
//        String resultUrl                = "refresh";
//        try {
//
//            //#1. 회원체크(Id 와 Email 로 같은 계정인지 체크)
//            gridPageEntity = usersService.getUsersMasterList(usersMasterEntity);
//
//            if(gridPageEntity.getTotal() > 0) {
//                resultMessage = "아이디는 " + usersMasterEntity.getIumId() + " 입니다.";
//            } else {
//                resultMessage = "입력하신 계정이 없습니다."; //password.fail
//                resultCode    = Default.ResultValue.FAIL;
//            }
//
//        } catch (CommonRuntimeException e) {
//            resultCode                  = e.getResultCode();
//            resultMessage               = e.getResultMessage();
//            resultUrl                   = "none";
//        } catch (Exception e) {
//            resultCode                  = Default.ResultValue.FAIL;
//            resultMessage               = "save.fail";
//            resultUrl                   = "none";
//            LOGGER.error(e.getMessage());
//        }
//        gridPageEntity.setPage(usersMasterEntity.getPage());
//        gridPageEntity.setResultCode(resultCode);
//        gridPageEntity.setResultMessage(resultMessage);
//        gridPageEntity.setResultUrl(resultUrl);

        return commonResultEntity;
    }


}