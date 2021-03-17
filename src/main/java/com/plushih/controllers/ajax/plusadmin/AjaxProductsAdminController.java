package com.plushih.controllers.ajax.plusadmin;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.plushih.common.ci.CoreController;
import com.plushih.common.ci.Debug;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.ci.plusQueryBuilder;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.CommonResultEntity;
import com.plushih.entities.ProductEntity;
import com.plushih.entities.SiteAttachFileEntity;
import com.plushih.services.ci.CommonService;
import com.plushih.services.front.ProductService;
import com.plushih.services.front.SiteFileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.*;

/**
 * hsk3807
 */
@Controller
@RequestMapping("plusadmin/ajax/product")
public class AjaxProductsAdminController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AjaxProductsAdminController.class );


    @Autowired
    private ProductService productService;

    @Autowired
    private SiteFileUploadService siteFileUploadService;
    @Autowired
    private CommonService commonService;

    /**
     * 사업장관리
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/productList", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsList (@ModelAttribute ProductEntity productEntity,HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        commonResultEntity = productService.getProductList(db,productEntity);


        logEnd(fullName);
        return commonResultEntity;
    }

    /**
     * 사업장관리 등록수정
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/productDelete", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsDelete (@RequestParam(required = false, value = "delProductSeqs", defaultValue = "") String delproductsSeqs, HttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);

        Debug.log(delproductsSeqs);
        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        if (!StringUtils.isEmpty(delproductsSeqs)) {

            String[] stri = delproductsSeqs.split(",");
            for(String bbSeq :stri){
                siteFileUploadService.deleteFile(bbSeq,"PRODUCT");
            }
            db.where_in("pd_seq",delproductsSeqs.split(","));
            db.from("plus_product");

            commonService.setDelete(db);


            db.flag= plusQueryBuilder.inType.DELETE;
            commonResultEntity= db.getOut();

        } else {
            db.flag= plusQueryBuilder.inType.ERR;
            commonResultEntity= db.getOut();
        }

        logEnd(fullName);
        return commonResultEntity;
    }

    /**
     * 사업장관리 등록수정
     * @param request
     * @param response
     * @param localeParam
     * @return
     */
    @RequestMapping(value =  "/productExcute", method = {RequestMethod.POST})
    public @ResponseBody CommonResultEntity bbsExcute (@ModelAttribute ProductEntity productEntity, @RequestParam(required = false, value = "fileList", defaultValue = "[]") String fileList, MultipartHttpServletRequest request, HttpServletResponse response, Locale localeParam ) throws Exception {
        String fullName = getFunction();
        logStart(fullName);

//        Cache cache = new Cache();
//        String rules = cache.get_cache("products.bbs");
//
//        Debug.log("===rules==="+rules);

        Debug.log(fileList);

        if(!StringUtils.isEmpty(fileList)){
            if(fileList.length()>10){
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<SiteAttachFileEntity>>(){}.getType();
                List<SiteAttachFileEntity> fileArrayList =gson.fromJson(fileList,listType);
                Debug.log(fileArrayList);
                for( SiteAttachFileEntity bbsAttachFileEntity:fileArrayList){
                    if(!StringUtils.isEmpty(bbsAttachFileEntity.getDelSafSeq())){
                        siteFileUploadService.deleteFile(bbsAttachFileEntity.getDelSafSeq());
                    }
                }
            }
        }

        CommonResultEntity commonResultEntity = new CommonResultEntity();
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
        plusActiveRecord db =new plusActiveRecord(functionName,request);
        Integer insertID = productService.setProductExcute(db,productEntity);
        //commonResultEntity.setResultList(businessInfoList);
        Debug.log("Debug.log(db.flag);+++++++++"+db.flag);

        MultiValueMap<String, MultipartFile> fileuploads = request.getMultiFileMap();
        Iterator<String> iterator = fileuploads.keySet().iterator();

        while(iterator.hasNext()) {

            String key = iterator.next();
            LinkedList<MultipartFile> df = (LinkedList<MultipartFile>) fileuploads.get(key);

            MultipartFile fileInfo = (MultipartFile) df.getFirst();
            if (fileInfo.getSize() > 0) {
                siteFileUploadService.uploadFile(fileInfo, 0, "/PRODUCT", "PRODUCT", insertID, key);
            }
        }
        commonResultEntity = db.getOut();
        logEnd(fullName);
        return commonResultEntity;
    }



}