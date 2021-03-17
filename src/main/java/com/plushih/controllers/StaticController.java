package com.plushih.controllers;


//import com.oreilly.servlet.MultipartRequest;
//import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
//import com.oreilly.servlet.multipart.FileRenamePolicy;

import com.plushih.common.utils.HashUtils;
import com.plushih.common.utils.Inapp;
import com.plushih.common.utils.MailUtils;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.common.MailSendEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Properties;

@Controller
@RequestMapping("static")
public class StaticController {
    private static final Logger LOGGER = LoggerFactory.getLogger( StaticController.class );

    /**
     * 로그인
     * @param model
     * @param request
     * @param
     * @return
     */
    @RequestMapping("/Photo_Quick_UploadPopup")
    public String Photo_Quick_UploadPopup (Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        return "/static/Photo_Quick_UploadPopup";
    }
    /**
     * 로그인
     * @param model
     * @param request
     * @param
     * @return
     */
    @RequestMapping("/imgUploadProc")
    public String imgUploadProc (Model model, MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {

        //response.
        return "/static/imgUploadProc";
    }

    /**
     * 로그인
     * @param model
     * @param request
     * @param
     * @return
     */
    @RequestMapping("/error")
    public String error (Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        return "/static/error";
    }

    /**
     * 로그인
     * @param model
     * @param request
     * @param
     * @return
     */
    @RequestMapping("/login")
    public String login (Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        return "/static/login";
    }


    /**
     * 로그인
     * @param model
     * @param request
     * @param
     * @return
     */
    @RequestMapping("/pluslogin")
    public String pluslogin (Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        return "/static/pluslogin";
    }

    /**
     * 로그인
     * @param model
     * @param request
     * @param
     * @return
     */
    @RequestMapping("/layout")
    public String layout (Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {


        return "/static/layout";
    }
    /**
     * 사용자관리
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/pass")
    public String passPage (@RequestParam(required = false, value = "pass", defaultValue = "") String pass, Model model, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        HashUtils hashUtils             = new HashUtils();
        return  hashUtils.encryptSHA256(pass.toString());
    }

    /**
     * 사용자관리
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/camel")
    public String camelPage (@RequestParam(required = false, value = "camel", defaultValue = "") String camel, Model model, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        return  StringUtils.toCamel(camel.toString());
    }
    /**
     * 사용자관리
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/inapp")
    public String inappPage (@RequestParam(required = false, value = "camel", defaultValue = "") String camel, Model model, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String inData = "MIIT1AYJKoZIhvcNAQcCoIITxTCCE8ECAQExCzAJBgUrDgMCGgUAMIIDdQYJKoZIhvcNAQcBoIIDZgSCA2IxggNeMAoCAQgCAQEEAhYAMAoCARQCAQEEAgwAMAsCAQECAQEEAwIBADALAgEDAgEBBAMMATQwCwIBCwIBAQQDAgEAMAsCAQ4CAQEEAwIBfjALAgEPAgEBBAMCAQAwCwIBEAIBAQQDAgEAMAsCARkCAQEEAwIBAzAMAgEKAgEBBAQWAjQrMA0CAQ0CAQEEBQIDAf6MMA0CARMCAQEEBQwDMS4wMA4CAQkCAQEEBgIEUDI1NjAYAgEEAgECBBBth628lwnzqQTiECDFWimRMBsCAQACAQEEEwwRUHJvZHVjdGlvblNhbmRib3gwHAIBBQIBAQQUxSUYf2OZgYxSsLn3XNdMq0FrBFwwHgIBDAIBAQQWFhQyMDIxLTAyLTIyVDA0OjA1OjQ1WjAeAgESAgEBBBYWFDIwMTMtMDgtMDFUMDc6MDA6MDBaMCACAQICAQEEGAwWY29tLnBsdXNoaWguYWlnb2FwcGlvczA9AgEHAgEBBDWG+kZJYXGgk1q1ZVgKRK/xZqxri5kpGMxlHtEK4lKUTPqG2Z5tUw6N1DwrsHQMI919/Vc1RDBDAgEGAgEBBDumD2rMNGeyEj0m79qJjzPpwpzfYjxqdC/gv74cyKtFsWfEmdFmBHch/I6DRtMzShcGu+eAYxIqonet3jCCAXACARECAQEEggFmMYIBYjALAgIGrAIBAQQCFgAwCwICBq0CAQEEAgwAMAsCAgawAgEBBAIWADALAgIGsgIBAQQCDAAwCwICBrMCAQEEAgwAMAsCAga0AgEBBAIMADALAgIGtQIBAQQCDAAwCwICBrYCAQEEAgwAMAwCAgalAgEBBAMCAQEwDAICBqsCAQEEAwIBATAMAgIGrgIBAQQDAgEAMAwCAgavAgEBBAMCAQAwDAICBrECAQEEAwIBADAbAgIGpwIBAQQSDBAxMDAwMDAwNzgwMDM5OTkzMBsCAgapAgEBBBIMEDEwMDAwMDA3ODAwMzk5OTMwHwICBqgCAQEEFhYUMjAyMS0wMi0yMlQwNDowNTo0NFowHwICBqoCAQEEFhYUMjAyMS0wMi0yMlQwNDowNTo0NFowNgICBqYCAQEELQwrY29tLnBsdXNoaWguYWlnb2FwcGlvcy5wcm9kdWN0XzEyX21vbnRoX3VzZaCCDmUwggV8MIIEZKADAgECAggO61eH554JjTANBgkqhkiG9w0BAQUFADCBljELMAkGA1UEBhMCVVMxEzARBgNVBAoMCkFwcGxlIEluYy4xLDAqBgNVBAsMI0FwcGxlIFdvcmxkd2lkZSBEZXZlbG9wZXIgUmVsYXRpb25zMUQwQgYDVQQDDDtBcHBsZSBXb3JsZHdpZGUgRGV2ZWxvcGVyIFJlbGF0aW9ucyBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0eTAeFw0xNTExMTMwMjE1MDlaFw0yMzAyMDcyMTQ4NDdaMIGJMTcwNQYDVQQDDC5NYWMgQXBwIFN0b3JlIGFuZCBpVHVuZXMgU3RvcmUgUmVjZWlwdCBTaWduaW5nMSwwKgYDVQQLDCNBcHBsZSBXb3JsZHdpZGUgRGV2ZWxvcGVyIFJlbGF0aW9uczETMBEGA1UECgwKQXBwbGUgSW5jLjELMAkGA1UEBhMCVVMwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQClz4H9JaKBW9aH7SPaMxyO4iPApcQmyz3Gn+xKDVWG/6QC15fKOVRtfX+yVBidxCxScY5ke4LOibpJ1gjltIhxzz9bRi7GxB24A6lYogQ+IXjV27fQjhKNg0xbKmg3k8LyvR7E0qEMSlhSqxLj7d0fmBWQNS3CzBLKjUiB91h4VGvojDE2H0oGDEdU8zeQuLKSiX1fpIVK4cCc4Lqku4KXY/Qrk8H9Pm/KwfU8qY9SGsAlCnYO3v6Z/v/Ca/VbXqxzUUkIVonMQ5DMjoEC0KCXtlyxoWlph5AQaCYmObgdEHOwCl3Fc9DfdjvYLdmIHuPsB8/ijtDT+iZVge/iA0kjAgMBAAGjggHXMIIB0zA/BggrBgEFBQcBAQQzMDEwLwYIKwYBBQUHMAGGI2h0dHA6Ly9vY3NwLmFwcGxlLmNvbS9vY3NwMDMtd3dkcjA0MB0GA1UdDgQWBBSRpJz8xHa3n6CK9E31jzZd7SsEhTAMBgNVHRMBAf8EAjAAMB8GA1UdIwQYMBaAFIgnFwmpthhgi+zruvZHWcVSVKO3MIIBHgYDVR0gBIIBFTCCAREwggENBgoqhkiG92NkBQYBMIH+MIHDBggrBgEFBQcCAjCBtgyBs1JlbGlhbmNlIG9uIHRoaXMgY2VydGlmaWNhdGUgYnkgYW55IHBhcnR5IGFzc3VtZXMgYWNjZXB0YW5jZSBvZiB0aGUgdGhlbiBhcHBsaWNhYmxlIHN0YW5kYXJkIHRlcm1zIGFuZCBjb25kaXRpb25zIG9mIHVzZSwgY2VydGlmaWNhdGUgcG9saWN5IGFuZCBjZXJ0aWZpY2F0aW9uIHByYWN0aWNlIHN0YXRlbWVudHMuMDYGCCsGAQUFBwIBFipodHRwOi8vd3d3LmFwcGxlLmNvbS9jZXJ0aWZpY2F0ZWF1dGhvcml0eS8wDgYDVR0PAQH/BAQDAgeAMBAGCiqGSIb3Y2QGCwEEAgUAMA0GCSqGSIb3DQEBBQUAA4IBAQANphvTLj3jWysHbkKWbNPojEMwgl/gXNGNvr0PvRr8JZLbjIXDgFnf4+LXLgUUrA3btrj+/DUufMutF2uOfx/kd7mxZ5W0E16mGYZ2+FogledjjA9z/Ojtxh+umfhlSFyg4Cg6wBA3LbmgBDkfc7nIBf3y3n8aKipuKwH8oCBc2et9J6Yz+PWY4L5E27FMZ/xuCk/J4gao0pfzp45rUaJahHVl0RYEYuPBX/UIqc9o2ZIAycGMs/iNAGS6WGDAfK+PdcppuVsq1h1obphC9UynNxmbzDscehlD86Ntv0hgBgw2kivs3hi1EdotI9CO/KBpnBcbnoB7OUdFMGEvxxOoMIIEIjCCAwqgAwIBAgIIAd68xDltoBAwDQYJKoZIhvcNAQEFBQAwYjELMAkGA1UEBhMCVVMxEzARBgNVBAoTCkFwcGxlIEluYy4xJjAkBgNVBAsTHUFwcGxlIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MRYwFAYDVQQDEw1BcHBsZSBSb290IENBMB4XDTEzMDIwNzIxNDg0N1oXDTIzMDIwNzIxNDg0N1owgZYxCzAJBgNVBAYTAlVTMRMwEQYDVQQKDApBcHBsZSBJbmMuMSwwKgYDVQQLDCNBcHBsZSBXb3JsZHdpZGUgRGV2ZWxvcGVyIFJlbGF0aW9uczFEMEIGA1UEAww7QXBwbGUgV29ybGR3aWRlIERldmVsb3BlciBSZWxhdGlvbnMgQ2VydGlmaWNhdGlvbiBBdXRob3JpdHkwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDKOFSmy1aqyCQ5SOmM7uxfuH8mkbw0U3rOfGOAYXdkXqUHI7Y5/lAtFVZYcC1+xG7BSoU+L/DehBqhV8mvexj/avoVEkkVCBmsqtsqMu2WY2hSFT2Miuy/axiV4AOsAX2XBWfODoWVN2rtCbauZ81RZJ/GXNG8V25nNYB2NqSHgW44j9grFU57Jdhav06DwY3Sk9UacbVgnJ0zTlX5ElgMhrgWDcHld0WNUEi6Ky3klIXh6MSdxmilsKP8Z35wugJZS3dCkTm59c3hTO/AO0iMpuUhXf1qarunFjVg0uat80YpyejDi+l5wGphZxWy8P3laLxiX27Pmd3vG2P+kmWrAgMBAAGjgaYwgaMwHQYDVR0OBBYEFIgnFwmpthhgi+zruvZHWcVSVKO3MA8GA1UdEwEB/wQFMAMBAf8wHwYDVR0jBBgwFoAUK9BpR5R2Cf70a40uQKb3R01/CF4wLgYDVR0fBCcwJTAjoCGgH4YdaHR0cDovL2NybC5hcHBsZS5jb20vcm9vdC5jcmwwDgYDVR0PAQH/BAQDAgGGMBAGCiqGSIb3Y2QGAgEEAgUAMA0GCSqGSIb3DQEBBQUAA4IBAQBPz+9Zviz1smwvj+4ThzLoBTWobot9yWkMudkXvHcs1Gfi/ZptOllc34MBvbKuKmFysa/Nw0Uwj6ODDc4dR7Txk4qjdJukw5hyhzs+r0ULklS5MruQGFNrCk4QttkdUGwhgAqJTleMa1s8Pab93vcNIx0LSiaHP7qRkkykGRIZbVf1eliHe2iK5IaMSuviSRSqpd1VAKmuu0swruGgsbwpgOYJd+W+NKIByn/c4grmO7i77LpilfMFY0GCzQ87HUyVpNur+cmV6U/kTecmmYHpvPm0KdIBembhLoz2IYrF+Hjhga6/05Cdqa3zr/04GpZnMBxRpVzscYqCtGwPDBUfMIIEuzCCA6OgAwIBAgIBAjANBgkqhkiG9w0BAQUFADBiMQswCQYDVQQGEwJVUzETMBEGA1UEChMKQXBwbGUgSW5jLjEmMCQGA1UECxMdQXBwbGUgQ2VydGlmaWNhdGlvbiBBdXRob3JpdHkxFjAUBgNVBAMTDUFwcGxlIFJvb3QgQ0EwHhcNMDYwNDI1MjE0MDM2WhcNMzUwMjA5MjE0MDM2WjBiMQswCQYDVQQGEwJVUzETMBEGA1UEChMKQXBwbGUgSW5jLjEmMCQGA1UECxMdQXBwbGUgQ2VydGlmaWNhdGlvbiBBdXRob3JpdHkxFjAUBgNVBAMTDUFwcGxlIFJvb3QgQ0EwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDkkakJH5HbHkdQ6wXtXnmELes2oldMVeyLGYne+Uts9QerIjAC6Bg++FAJ039BqJj50cpmnCRrEdCju+QbKsMflZ56DKRHi1vUFjczy8QPTc4UadHJGXL1XQ7Vf1+b8iUDulWPTV0N8WQ1IxVLFVkds5T39pyez1C6wVhQZ48ItCD3y6wsIG9wtj8BMIy3Q88PnT3zK0koGsj+zrW5DtleHNbLPbU6rfQPDgCSC7EhFi501TwN22IWq6NxkkdTVcGvL0Gz+PvjcM3mo0xFfh9Ma1CWQYnEdGILEINBhzOKgbEwWOxaBDKMaLOPHd5lc/9nXmW8Sdh2nzMUZaF3lMktAgMBAAGjggF6MIIBdjAOBgNVHQ8BAf8EBAMCAQYwDwYDVR0TAQH/BAUwAwEB/zAdBgNVHQ4EFgQUK9BpR5R2Cf70a40uQKb3R01/CF4wHwYDVR0jBBgwFoAUK9BpR5R2Cf70a40uQKb3R01/CF4wggERBgNVHSAEggEIMIIBBDCCAQAGCSqGSIb3Y2QFATCB8jAqBggrBgEFBQcCARYeaHR0cHM6Ly93d3cuYXBwbGUuY29tL2FwcGxlY2EvMIHDBggrBgEFBQcCAjCBthqBs1JlbGlhbmNlIG9uIHRoaXMgY2VydGlmaWNhdGUgYnkgYW55IHBhcnR5IGFzc3VtZXMgYWNjZXB0YW5jZSBvZiB0aGUgdGhlbiBhcHBsaWNhYmxlIHN0YW5kYXJkIHRlcm1zIGFuZCBjb25kaXRpb25zIG9mIHVzZSwgY2VydGlmaWNhdGUgcG9saWN5IGFuZCBjZXJ0aWZpY2F0aW9uIHByYWN0aWNlIHN0YXRlbWVudHMuMA0GCSqGSIb3DQEBBQUAA4IBAQBcNplMLXi37Yyb3PN3m/J20ncwT8EfhYOFG5k9RzfyqZtAjizUsZAS2L70c5vu0mQPy3lPNNiiPvl4/2vIB+x9OYOLUyDTOMSxv5pPCmv/K/xZpwUJfBdAVhEedNO3iyM7R6PVbyTi69G3cN8PReEnyvFteO3ntRcXqNx+IjXKJdXZD9Zr1KIkIxH3oayPc4FgxhtbCS+SsvhESPBgOJ4V9T0mZyCKM2r3DYLP3uujL/lTaltkwGMzd/c6ByxW69oPIQ7aunMZT7XZNn/Bh1XZp5m5MkL72NVxnn6hUrcbvZNCJBIqxw8dtk2cXmPIS4AXUKqK1drk/NAJBzewdXUhMYIByzCCAccCAQEwgaMwgZYxCzAJBgNVBAYTAlVTMRMwEQYDVQQKDApBcHBsZSBJbmMuMSwwKgYDVQQLDCNBcHBsZSBXb3JsZHdpZGUgRGV2ZWxvcGVyIFJlbGF0aW9uczFEMEIGA1UEAww7QXBwbGUgV29ybGR3aWRlIERldmVsb3BlciBSZWxhdGlvbnMgQ2VydGlmaWNhdGlvbiBBdXRob3JpdHkCCA7rV4fnngmNMAkGBSsOAwIaBQAwDQYJKoZIhvcNAQEBBQAEggEAComMofDf1m6ztj9wEKcimseoWLSHGgAOG6BMOT8e7APH+4tDsq4qUMhb693jjEzCytgjcXCL5e3Hna77nqXp+1jRm+PzNU1iPOiOWLh/p+z9YrI/YiAINR6dw3t6R1v3OGPYMIUUNBvq6hmcPQsYK1+BRU318JxRymvCK7JbC8ymO0IRPKWIDdBgyzYIC3PEHOPlb4v+azSCKZy5HKGACCV62SnnQev25n5ziUy3ZxTxJL3YOy6+XD5ulKKZJRDXzGMLwwtw+831PDWNmgfPZlt8p5zg30/wg0d+whbFb57BhI00dHRgjJirFO26HmakuclYoqPPDr0a1mWODkvM4w==";
        int res = 0;
        try {
            res = Inapp.verifyApplePurchase(inData,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  String.valueOf(res);
    }
    /**
     * 사용자관리
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/inapp2")
    public String inappPage2 (@RequestParam(required = false, value = "camel", defaultValue = "") String camel, Model model, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String inData = "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";
        String res = "";
        try {
            Inapp.verifyAndroidPurchase("RSA",inData,"SHA1withRSA");
            res = Inapp.verifyAppleReceiptData("https://sandbox.itunes.apple.com/verifyReceipt",inData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  String.valueOf(res);
    }

    /**
     * 사용자관리
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/entity")
    public String entityPage (@RequestParam(required = false, value = "camel", defaultValue = "") String camel, Model model, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String defaultPath = request.getSession().getServletContext().getRealPath("/");
        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");

         String fileInfo = "";
        int size = 10 * 1024 * 1024; //byte
        String encoding = "UTF-8";

//        String filePath = defaultPath + "/img" + File.separator + "smarteditor2" + File.separator + dtFormat.format(new Date()) + File.separator;
//        FileRenamePolicy policy = new DefaultFileRenamePolicy();
//        MultipartRequest mrequest
//                = null; //파일 정책
//        try {
//            mrequest = new MultipartRequest(request //MultipartRequest를 만들기 위한 request
//            , filePath //저장 위치
//            , size //최대크기
//            , encoding //인코딩 타입
//            , policy);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        File uploadFile = mrequest.getFile("file");
//        String fileName = uploadFile.getName();
//        long uploadFile_length = uploadFile.length();
//        String a = "a_b_c";
////        a = "a_b_c";
////        Debug.log(a);
////        Debug.log(StringUtils.toCamel(a));
////        a = "entityPage";
////        Debug.log(a);
////        Debug.log(StringUtils.unCamel(a));
////
////        a = "EntityPageResteTefe";
////        Debug.log(a);
////        Debug.log(StringUtils.unCamel(a));
//        CodeMasterEntity cm = new CodeMasterEntity();
//        cm.setCmCode("cmcodetests");
//        cm.setCmSeq(1);
//        cm.set("cm_etc","etcxxxxxxxx");
//        //Debug.log("cm.getType ==[cmcode] "+cm.getType("cmcode"));
//        Debug.log("cm.get ==[cmcode] "+cm.get("cmcode"));
//        Debug.log("cm.get ==[cm_code] "+cm.get("cm_code"));
//        Debug.log("cm.get ==[cmCode] "+cm.get("cmCode"));
//        Debug.log("cm.get ==[cmseq] "+cm.get("cmseq"));
//
//
//        Cache cache = new Cache();
//        cache.set_cache("test","test");
//
//        Debug.log(cache.get_cache("test"));
//            Path currentRelativePath = Paths.get("");
//    String s = currentRelativePath.toAbsolutePath().toString();
//    System.out.println("Current relative path is: " + s);
//
//
//
//String name = new Object(){}.getClass().getEnclosingMethod().getName();
//System.out.println("name is: " + name);
        /*
        try {
            Field field = cm.getClass().getDeclaredField("cmCode");
            field.setAccessible(true);
            Object value=  field.get(cm);
            Debug.log(value.toString());


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Debug.log("fields.length"+fields.length);

        for(int i=0;i<fields.length;i++){
				Debug.log("@fggse@"+fields[i]);
			}
        //Debug.log(EntityUtils.fieldValue("cmCode",CodeMasterEntity.class).toString());
        //return   EntityUtils.fieldValue("cmCode",cm.getClass()).toString();
        */
        return "";
    }
    /**
     * 로그인
     * @param model
     * @param request
     * @param
     * @return
     */
    @RequestMapping("/newUser")
    public String newUser (Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

//        UsersMasterEntity usersMasterEntity =(UsersMasterEntity)request.getSession().getAttribute(LoginSession.INFO);
//        if(usersMasterEntity!=null){
//            response.sendRedirect("/");
//        } else {
//
//        }
        return "/static/newUser";
    }
    /**
     * 아이디 찾기
     * @param model
     * @param request
     * @param
     * @return
     */
    @RequestMapping("/idSearch")
    public String idSearch (Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        return "/static/idSearch";
    }

    /**
     * 비밀번호 변경
     * @param model
     * @param request
     * @param
     * @return
     */
    @RequestMapping("/passwdChange")
    public String passwdChange (Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        return "/static/passwdChange";
    }


}