package com.plushih.controllers.ajax;

import com.plushih.common.ci.CoreController;
import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.constant.Default;
import com.plushih.common.messages.Messages;
import com.plushih.entities.CodeMasterEntity;
import com.plushih.entities.CommonResultEntity;
import com.plushih.services.CodeService;
import com.plushih.entities.*;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.*;

@RestController
public class AjaxCodeController extends CoreController {

    @Autowired
    private CodeService codeService;

    @Autowired
    private Messages messages;

    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxCodeController.class);
    public HashMap<String, HashMap<String, String>> codeResrultList = new HashMap<String, HashMap<String, String>>();

    public void setCodeResrulAdd(List<CodeMasterEntity> resList){
        if(resList !=null){

            for (int ix = 0; ix < resList.size(); ix++) {
                if(codeResrultList.containsKey(resList.get(ix).get("cmGubun").toString())){
                    codeResrultList.get(resList.get(ix).get("cmGubun").toString()).put(resList.get(ix).get("cmCode").toString(),resList.get(ix).get("cmName").toString());
                } else {
                    HashMap<String,String> map = new HashMap<String,String>();
                    map.put(resList.get(ix).get("cmCode").toString(),resList.get(ix).get("cmName").toString());
                    codeResrultList.put(resList.get(ix).get("cmGubun").toString(), map);
                }
            }
        }
    }
    /**
     * 공통코드
     *
     * @param codes
     * @return
     */
    @RequestMapping(value = "/ajax/codeList", method = {RequestMethod.POST,RequestMethod.GET})
    public @ResponseBody HashMap<String, Object> codeList(String codes, Model model) {
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        String fullName = getFunction();

        logStart(fullName);

        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String resultMessage = "error.unexpected";
        String resultCode = Default.ResultValue.FAIL;
        String[] arrCode;

        try {
            resultCode = Default.ResultValue.SUCCESS;

            if (!StringUtils.isEmpty(codes)) {
                if (codes.indexOf(',') > -1) {
                    arrCode = codes.split(",");
                } else {
                    arrCode = new String[]{codes};
                }
                plusActiveRecord db = new plusActiveRecord();
                db.where_in("cm_gubun",arrCode);

                List<CodeMasterEntity> codeList =  codeService.getCodeList(db);
                setCodeResrulAdd(codeList);

                Method[] methods  = this.getClass().getDeclaredMethods();
                StringBuffer sb = new StringBuffer();
                for( Method method : methods ){
                    if(method.getName().indexOf("set")>-1)  continue;
                    if(!ArrayUtils.contains(arrCode,method.getName())) continue;
                    Debug.log("codes==="+codes +"&&method.getName()=="+method.getName());
                    sb.append(method.getName());

                    // 메소드 인자가 있다면 출력하자.
                    Class<?>[] argTypes = method.getParameterTypes();
                    sb.append("(");
                    int size = argTypes.length;
                    for( Class<?> argType : argTypes ){
                        String argName = argType.getName();
                        sb.append(argName + " val");
                        if( --size != 0 ){
                            sb.append(", ");
                        }
                    }
                    sb.append(")");

                    // 리턴 인자를 출력하자.
                    Class<?> returnType = method.getReturnType();
                    sb.append(" : " + returnType.getName());
			        sb.setLength(0);

                    Debug.log("functionName==="+functionName +"&&method.getName()=="+method.getName());
                    Class[] paramTypes = method.getParameterTypes();
                    Class retType = method.getReturnType();
                    if(retType.getName().equals("void")) {//매소드 리턴타입 구분
                        Debug.log("method.getName()=="+method.getName()+"+paramTypes.length+==" + paramTypes.length, "");
                        Method tempMethod = this.getClass().getMethod(method.getName());
			            tempMethod.invoke(this);
                    }
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
            //LOGGER.error(e.getMessage());
        }
        logEnd(fullName);
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("codes",codeResrultList);
        return res;
    }

    public void LG() throws Exception {
        plusActiveRecord dbEntity = new plusActiveRecord();
        dbEntity.select("'LG' as  cm_gubun,lg_seq as cm_code,lg_title cm_name ");
        dbEntity.from("plus_language");
        dbEntity.where("lg_use","Y");
        List<CodeMasterEntity> resList = codeService.getFastList(dbEntity);
        setCodeResrulAdd(resList);

    }

    public void BI() throws Exception {
        plusActiveRecord dbEntity = new plusActiveRecord();
        dbEntity.select("'BI' as  cm_gubun,bi_seq as cm_code,bi_name cm_name ");
        dbEntity.from("plus_business_info");
        List<CodeMasterEntity> resList = codeService.getFastList(dbEntity);
        setCodeResrulAdd(resList);

    }



    public void BC() throws Exception {
        plusActiveRecord dbEntity = new plusActiveRecord();
        dbEntity.select("'BC' as  cm_gubun,bc_seq as cm_code,bc_name cm_name ");
        dbEntity.from("plus_business_client");
        List<CodeMasterEntity> resList = codeService.getFastList(dbEntity);
        setCodeResrulAdd(resList);
    }

    public void UG() throws Exception {
        plusActiveRecord dbEntity = new plusActiveRecord();
        dbEntity.select("'UG' as  cm_gubun,ug_seq as cm_code,ug_name cm_name ");
        dbEntity.from("plus_user_group");
        List<CodeMasterEntity> resList = codeService.getFastList(dbEntity);
        setCodeResrulAdd(resList);

    }
    /**
     * 성취기준
     * @throws Exception
     */
    public void ACV() throws Exception {
        plusActiveRecord dbEntity = new plusActiveRecord();
        dbEntity.select("'ACV' as  cm_gubun,acv_id as cm_code,acv_name cm_name ");
        dbEntity.from("cb_aigo_achievement");
        dbEntity.where("use_yn","y");
        List<CodeMasterEntity> resList = codeService.getFastList(dbEntity);
        setCodeResrulAdd(resList);

    }

    /**
     * 과목명 코드
     * @throws Exception
     */
    public void SUB() throws Exception {
        plusActiveRecord dbEntity = new plusActiveRecord();
        dbEntity.select("'SUB' as  cm_gubun,sub_id as cm_code,sub_name cm_name ");
        dbEntity.from("cb_aigo_subject");
        dbEntity.where("use_yn","y");
        List<CodeMasterEntity> resList = codeService.getFastList(dbEntity);
        setCodeResrulAdd(resList);

    }
    /**
     * 과목명 코드
     * @throws Exception
     */
    public void ACTIVESUB() throws Exception {
        plusActiveRecord dbEntity = new plusActiveRecord();
        dbEntity.select("'ACTIVESUB' as  cm_gubun,sub_id as cm_code,sub_name cm_name ");
        dbEntity.from("cb_aigo_subject");
        dbEntity.where("use_yn","y");
        dbEntity.where("user_active","y");
        List<CodeMasterEntity> resList = codeService.getFastList(dbEntity);
        setCodeResrulAdd(resList);

    }
    /**
     * 개념요소명
     * @throws Exception
     */
    public void CPT() throws Exception {
        plusActiveRecord dbEntity = new plusActiveRecord();
        dbEntity.select("'CPT' as  cm_gubun,cpt_id as cm_code,cpt_name cm_name ");
        dbEntity.from("cb_aigo_concept");
        dbEntity.where("use_yn","y");
        List<CodeMasterEntity> resList = codeService.getFastList(dbEntity);
        setCodeResrulAdd(resList);

    }
    /**
     * 과목명 코드
     * @throws Exception
     */
    public void LEV() throws Exception {
        plusActiveRecord dbEntity = new plusActiveRecord();
        dbEntity.select("'LEV' as  cm_gubun,lev_id as cm_code,lev_name cm_name ");
        dbEntity.from("cb_aigo_level");
        dbEntity.where("use_yn","y");
        List<CodeMasterEntity> resList = codeService.getFastList(dbEntity);
        setCodeResrulAdd(resList);

    }
    /**
     * 과목명 코드
     * @throws Exception
     */
    public void UNIT() throws Exception {
        plusActiveRecord dbEntity = new plusActiveRecord();
        dbEntity.select("'UNIT' as  cm_gubun,unit_id as cm_code,unit_name cm_name ");
        dbEntity.from("cb_aigo_unit");
        dbEntity.where("use_yn","y");
        List<CodeMasterEntity> resList = codeService.getFastList(dbEntity);
        setCodeResrulAdd(resList);

    }
    /**
     * 과목명 코드
     * @throws Exception
     */
    public void ACA() throws Exception {
        plusActiveRecord dbEntity = new plusActiveRecord();
        dbEntity.select("'ACA' as  cm_gubun,aca_id as cm_code,aca_name cm_name ");
        dbEntity.from("cb_aigo_category");
        dbEntity.where("parent_aca_id","0");
        dbEntity.where("use_yn","y");
        List<CodeMasterEntity> resList = codeService.getFastList(dbEntity);
        setCodeResrulAdd(resList);

    }
    /**
     * 과목명 코드
     * @throws Exception
     */
    public void NOT() throws Exception {
        plusActiveRecord dbEntity = new plusActiveRecord();
        dbEntity.select("'NOT' as  cm_gubun,not_id as cm_code,not_name cm_name ");
        dbEntity.from("cb_aigo_notion");
        dbEntity.where("use_yn","y");
        List<CodeMasterEntity> resList = codeService.getFastList(dbEntity);
        setCodeResrulAdd(resList);

    }
    /**
     * 공통코드
     *
     * @param codes
     * @return
     */
//    @RequestMapping(value = "/ajax/codeList2", method = {RequestMethod.POST,RequestMethod.GET})
//    public List<HashMap<String, Object>> codeList2(String codes) {
//
//        CommonResultEntity commonResultEntity = new CommonResultEntity();
//        String resultMessage = "error.unexpected";
//        String resultCode = Default.ResultValue.FAIL;
//        StringUtils sutil = new StringUtils();
//        String[] arrCode;
//        LinkedHashMap<String, HashMap<String, String>> codeResrultList = new LinkedHashMap<String, HashMap<String, String>>();
//        List<HashMap<String,Object>> resList  = new ArrayList<HashMap<String,Object>>();
//        try {
//            resultCode = Default.ResultValue.SUCCESS;
//
//            /*
//             Connection con = DriverManager.getConnection("jdbc:mysql://hsk3807nas.ipdisk.co.kr:3306/at?autoReconnection=true", "at", "at");
//
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery( "select * from plus_code_master where 1=1" );
//            while (rs.next()) {               // Position the cursor                 3
//             Debug.log("Employee number = " + rs.getString(1));
//                                              // Print the column value
//            }
//            rs.close();                       // Close the ResultSet                 4
//            st.close();
//*/
//            /*
//            plusActiveRecord db = new plusActiveRecord();
//            db.clearQuery();
//            db.select("a.*");
//            db.from("plus_code_master a");
//            db.join("plus_code_master b","a.cm_seq=b.cm_seq","inner");
//            db.where("a.cm_seq",1);
//            db.where_in("a.cm_seq",new String[]{"1","2","3"});
//            db.like("a.cm_seq","1");
//            db.orderBy("a.cm_seq");
//
//            Debug.log(db._table);
//            Debug.log(db._select);
//            Debug.log(db._join);
//            Debug.log(db.prepareQuery());
//
//            Debug.log("@@@@@@@@@@start");
//            Debug.log(db._select);
//            Debug.log(db._join);
//            Debug.log(db._table);
//            int dataCount = ciService.getCount(db);
//            Debug.log("dataCount =="+dataCount);
//            Debug.log("@@@@@@@@@@end");
//
//            Debug.log(db._join);
//            resList = ciService.getList(db);
//            */
//            /*
//            if (!sutil.isEmpty(codes)) {
//                if (codes.indexOf(',') > -1) {
//                    arrCode = codes.split(",");
//                } else {
//                    arrCode = new String[]{codes};
//                }
//
//                for (int x = 0; x < arrCode.length; x++) {
//
//                    commonCodeEntity.setIccGubun(arrCode[x]);
//                    //List<CommonCodeEntity> codeList = commonCodeService.getCodeList(commonCodeEntity);
//                    HashMap<String, String> objMap = new HashMap<String, String>();
////                    for (int ix = 0; ix < codeList.size(); ix++) {
////                        objMap.put(codeList.get(ix).getIccCode() + "", codeList.get(ix).getIccName());
////                    }
//                    codeResrultList.put(arrCode[x], objMap);
//
//                }
//
//
//            }
//            */
//
//        } catch (Exception e) {
//            LOGGER.error(e.toString());
//        }
//        return resList;
//    }

}
