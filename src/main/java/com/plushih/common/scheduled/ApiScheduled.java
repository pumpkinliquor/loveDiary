package com.plushih.common.scheduled;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.plushih.common.ci.CoreController;
import com.plushih.common.ci.plusActiveRecord;
import com.plushih.common.utils.StringUtils;
import com.plushih.entities.CommonResultEntity;
import com.plushih.services.ci.CommonService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class ApiScheduled extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( ApiScheduled.class );

    @Autowired
    private CommonService commonService;

    public void syncPush(String memId,String token,String title,String text,String urls) throws Exception {
        String urlStr = "https://fcm.googleapis.com/fcm/send";
        String authorization = "key=AAAAHcCumsU:APA91bGN9xgcv0TvwoJgz2n9fS04qhSxxYLwU6eAfDstTFLPvhHfhsGlxGG6z_6tsGhE-j7kSpTPmWuQ1fYM5HewhYHuZu3ReJw3KUVpBolaMUXUcjQTWdn726bh0j1REmxnr1rNfYqf";


        JSONObject body = new JSONObject();
        if(!StringUtils.isEmpty(urls)){
            body.put("to",  urls);
        }
        body.put("priority", "high");

        JSONObject notification = new JSONObject();
        notification.put("title", title);
        notification.put("body", text);

        JSONObject data = new JSONObject();
        data.put("Key-1", "JSA Data 1");
        data.put("Key-2", "JSA Data 2");

        body.put("notification", notification);
        body.put("data", data);



        URL url = null;
        HttpURLConnection connection = null;
        BufferedOutputStream bos = null;
        BufferedReader reader = null;

        try {
            url = new URL(urlStr);
            connection = urlStr.startsWith("https://") ? (HttpsURLConnection) url.openConnection()
                    : (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.setRequestProperty("cache-control", "no-cache");
            connection.setRequestProperty("Authorization", authorization);

            connection.setDoOutput(true);
            connection.setDoInput(true);

            connection.connect();

            bos = new BufferedOutputStream(connection.getOutputStream());

            String message = body.toString();
            bos.write(message.getBytes("UTF-8"));

            bos.flush();
            bos.close();

            int responseCode = connection.getResponseCode();
            String responseMessage = connection.getResponseMessage();
            StringBuffer buffer = null;
            if (responseCode == HttpURLConnection.HTTP_OK) {

                buffer = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                String temp = null;
                while ((temp = reader.readLine()) != null) {
                    buffer.append(temp);
                }
                reader.close();
            }
            try{
                String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
                String fullName = getFunction();
                logStart(fullName);
                plusActiveRecord db =new plusActiveRecord(functionName,null);
                db.from("cb_notification");
                db.add("mem_id",memId);
                db.add("not_title",title);
                db.add("not_message",text);
                db.add("not_url",urls);
                db.add("reg_date",db.getYmdHis());
                db.add("udt_date",db.getYmdHis());
                commonService.setInsert(db);

            } catch(Exception e){
                e.printStackTrace();
            }
            connection.disconnect();
            System.out.println(String.format("Response : %d, %s", responseCode, responseMessage));
            System.out.println("Response DATA : ");
            System.out.println(buffer == null ? "NULL " : buffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 매분마다
     * 초 분 시 일 월 주(년)
     */
    //@Scheduled(cron="0 0,10,20,30,40,50 * * * ?") //
    @Scheduled(cron="0 0,10,20,30,40,50  * * * *") //
    public void syncDayEveryMinutScheduled() throws Exception {
        Calendar calendar               = new GregorianCalendar(Locale.KOREA);
        SimpleDateFormat dateFormat     = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LOGGER.info(":::::::::::::::::: syncDayEveryMinutScheduled 스케쥴러 Start : " + dateFormat.format(calendar.getTime()) +":::::::::::::::::: ");
        try {
            String fullName = getFunction();
            logStart(fullName);
            CommonResultEntity commonResultEntity = new CommonResultEntity();
            String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
            plusActiveRecord db =new plusActiveRecord(functionName,null);
            db.from("plus_bbs");
            db.where("bb_bbs","NOTICE");
            db.whereString("reg_sysdate BETWEEN DATE_ADD(NOW(), INTERVAL -10 MINUTE) AND NOW()");
            List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
            dataHashList = commonService.getList(db);

            db.clearQuery();
            db.select("a.*,b.mev_key");
            db.from("cb_member a");
            db.join("cb_member_extra_vars b ","a.mem_id=b.mem_id","left");
            db.where("aigo_alarm_mkt","y");
            db.whereString("mev_key is not null");

            List<Map<String,Object>> dataMemList = new ArrayList<Map<String,Object>>();
            dataMemList = commonService.getList(db);

            for(Map<String,Object> map : dataHashList){
                for(Map<String,Object> mapInfo :dataMemList){
                    this.syncPush(mapInfo.get("memId")+"",mapInfo.get("mevKey")+"","Aigo 새소식 도착","새로운 공지사항이 등록되었습니다.","");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info(":::::::::::::::::: syncDayEveryMinutScheduled 스케쥴러 End : " + dateFormat.format(calendar.getTime()) +":::::::::::::::::: ");
    }



    /**
     * 매시간 알림
     * 24시간 경과알림
     */
    @Scheduled(cron="0 0 * * * *") //매일 24시
    public void syncDayEveryScheduled() throws Exception {
        Calendar calendar           = new GregorianCalendar(Locale.KOREA);
        SimpleDateFormat dateFormat             = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LOGGER.info(":::::::::::::::::: syncDayEveryScheduled 스케쥴러 실행: " + dateFormat.format(calendar.getTime()) +":::::::::::::::::: ");

        try {
            String fullName = getFunction();
            logStart(fullName);
            CommonResultEntity commonResultEntity = new CommonResultEntity();
            String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
            plusActiveRecord db =new plusActiveRecord(functionName,null);
            List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();

            db.clearQuery();
            db.select("a.*,b.mev_key");
            db.from("cb_member a");
            db.join("cb_member_extra_vars b ","a.mem_id=b.mem_id","left");
            db.join("(select count(*) cnt,mem_id from cb_aigo_temp_qst group by mem_id) c ","a.mem_id=c.mem_id","left");
            db.whereString("substr(a.reg_sysdate,1,16) = substr(DATE_ADD(NOW(), INTERVAL -24 HOUR),16)");
            db.where("aigo_alarm_mkt","y");
            db.where("c.cnt","0");

            db.whereString("mev_key is not null");

            List<Map<String,Object>> dataMemList = new ArrayList<Map<String,Object>>();
            dataMemList = commonService.getList(db);

            for(Map<String,Object> mapInfo :dataMemList){
                this.syncPush(mapInfo.get("mevId")+"",mapInfo.get("mevKey")+"","Ai가 진단하는 나의 수준은?","나의 수학 오답원인! Ai가 명확하게 판단하여 가장 빠른 성적향상의 커리큘럼을 제시합니다. 지금 진단해보세요.","");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info(":::::::::::::::::: syncDayEveryScheduled 스케쥴러 실패: " + dateFormat.format(calendar.getTime()) +":::::::::::::::::: ");
        }



        LOGGER.info(":::::::::::::::::: 알림 스케쥴러 종료: " + dateFormat.format(calendar.getTime()) +":::::::::::::::::: ");



    }



    //@Scheduled(cron="0 * * * * *") //매일 오전 11시, 오후 11시
    @Scheduled(cron="0 0 10 * * *") //매일 오전 11시, 오후Q 11시
    public void syncDay10Scheduled() throws Exception {
        Calendar calendar                           = new GregorianCalendar(Locale.KOREA);
        SimpleDateFormat dateFormat                 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LOGGER.info(":::::::::::::::::: syncDay10Scheduled  Scheduled START: " + dateFormat.format(calendar.getTime()) + ":::::::::::::::::: ");

        try {
            String fullName = getFunction();
            logStart(fullName);
            CommonResultEntity commonResultEntity = new CommonResultEntity();
            String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
            plusActiveRecord db =new plusActiveRecord(functionName,null);
            List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();

            db.clearQuery();
            db.select("a.*,b.mev_key");
            db.from("cb_member a");
            db.join("cb_member_extra_vars b ","a.mem_id=b.mem_id","left");
            db.join("(select max(reg_sysdate) reg_sysdate,mem_id from  cb_aigo_my_answer_list group by mem_id) d ","a.mem_id=d.mem_id ","left");
            //db.join("(select count(*) cnt,mem_id,from cb_aigo_my_answer_list where reg_sysdate < DATE_ADD(NOW(), INTERVAL -3 day) group by mem_id) c ","a.mem_id=c.mem_id","left");
            db.whereString("DATEDIFF(now(),d.reg_sysdate) = 3");
            db.where("aigo_alarm_mkt","y");
            //db.where("c.cnt","0");

            db.whereString("mev_key is not null");

            List<Map<String,Object>> dataMemList = new ArrayList<Map<String,Object>>();
            dataMemList = commonService.getList(db);

            for(Map<String,Object> mapInfo :dataMemList){
                this.syncPush(mapInfo.get("mevId")+"",mapInfo.get("mevKey")+"","Aigo로 공부한지 3일이 지났어요.","3일동안 Aigo로 공부하지 않았어요. 수학은 꾸준히 일정량의 문제를 푸는게 중요해요. 지금 "+(mapInfo.get("mevEmail"))+"님께만 제공되는 맞춤 수준 수학문제를 풀어보세요. ","");
            }

            db.clearQuery();
            db.select("a.*,b.mev_key");
            db.from("cb_member a");
            db.join("cb_member_extra_vars b ","a.mem_id=b.mem_id","left");
            db.join("(select max(reg_sysdate) reg_sysdate,mem_id from  cb_aigo_my_answer_list group by mem_id) d ","a.mem_id=d.mem_id ","left");
            //db.join("(select count(*) cnt,mem_id,from cb_aigo_my_answer_list where reg_sysdate < DATE_ADD(NOW(), INTERVAL -3 day) group by mem_id) c ","a.mem_id=c.mem_id","left");
            db.whereString("DATEDIFF(now(),d.reg_sysdate) = 7");
            db.where("aigo_alarm_mkt","y");
            //db.where("c.cnt","0");

            db.whereString("mev_key is not null");

            dataMemList = commonService.getList(db);

            for(Map<String,Object> mapInfo :dataMemList){
                this.syncPush(mapInfo.get("mevId")+"",mapInfo.get("mevKey")+"","Aigo로 공부한지 7일이 지났어요.","7일동안 Aigo로 공부하지 않았어요. 수학은 꾸준히 일정량의 문제를 푸는게 중요해요. 지금 "+(mapInfo.get("mevEmail"))+"님께만 제공되는 맞춤 수준 수학문제를 풀어보세요. ","");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info(":::::::::::::::::: syncDay10Scheduled  Scheduled FAIL: " + dateFormat.format(calendar.getTime()) + ":::::::::::::::::: ");
        }

        LOGGER.info(":::::::::::::::::: syncDay10Scheduled  Scheduled END: " + dateFormat.format(calendar.getTime()) + ":::::::::::::::::: ");

    }




    /**
     * 매일 17시
     */
    //@Scheduled(cron="0 * * * * *") //매일 오전 11시, 오후 11시
    @Scheduled(cron="0 0 17 * * *") //매일 오전 11시, 오후Q 11시
    public void syncDay17Scheduled() throws Exception {
        Calendar calendar           = new GregorianCalendar(Locale.KOREA);
        SimpleDateFormat dateFormat             = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LOGGER.info(":::::::::::::::::: syncDay17Scheduled  Scheduled START: " + dateFormat.format(calendar.getTime()) + ":::::::::::::::::: ");

        try {
            String fullName = getFunction();
            logStart(fullName);
            CommonResultEntity commonResultEntity = new CommonResultEntity();
            String functionName = new Object(){}.getClass().getEnclosingMethod().getName();
            plusActiveRecord db =new plusActiveRecord(functionName,null);
            List<Map<String,Object>> dataHashList = new ArrayList<Map<String,Object>>();
            db.clearQuery();
            db.select("a.*,b.mev_key");
            db.from("cb_member a");
            db.join("cb_member_extra_vars b ","a.mem_id=b.mem_id","left");
            db.join("(select pass_yn,mem_id from  cb_aigo_my_answer_list where pass_yn='n' group by mem_id) d ","a.mem_id=d.mem_id ","left");
            //db.join("(select count(*) cnt,mem_id,from cb_aigo_my_answer_list where reg_sysdate < DATE_ADD(NOW(), INTERVAL -3 day) group by mem_id) c ","a.mem_id=c.mem_id","left");
            db.whereString("d.mem_id is null");
            db.where("aigo_alarm_mkt","y");
            //db.where("c.cnt","0");

            db.whereString("mev_key is not null");
            List<Map<String,Object>> dataMemList = new ArrayList<Map<String,Object>>();
            dataMemList = commonService.getList(db);

            for(Map<String,Object> mapInfo :dataMemList){
                this.syncPush(mapInfo.get("mevId")+"",mapInfo.get("mevKey")+"","Aigo에서 내가 틀린 문제를 다시 풀어보세요.","Aigo에서 내가 틀린 문제를 다시 풀어보세요.","");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        LOGGER.info(":::::::::::::::::: syncDay17Scheduled END: " + dateFormat.format(calendar.getTime()) + ":::::::::::::::::: ");
    }


    /**
     * 일일 학습알림
     * 매일 09시
     */
//    @Scheduled(cron="0 * * * * *") //매일 오전 11시, 오후 11시
    @Scheduled(cron="0 0 09 * * *") //매일 오전 11시, 오후Q 11시
    public void syncDay09Scheduled() throws Exception {
        Calendar calendar                       = new GregorianCalendar(Locale.KOREA);
        SimpleDateFormat dateFormat             = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HttpServletRequest request              = null;
        LOGGER.info(":::::::::::::::::: syncDay09Scheduled  Scheduled START: " + dateFormat.format(calendar.getTime()) + ":::::::::::::::::: ");

        try {


        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info(":::::::::::::::::: syncDay09Scheduled ERROR: " + dateFormat.format(calendar.getTime()) + ":::::::::::::::::: ");
        }

        LOGGER.info(":::::::::::::::::: syncDay09Scheduled END: " + dateFormat.format(calendar.getTime()) + ":::::::::::::::::: ");
    }


}


