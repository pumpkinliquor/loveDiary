package com.plushih.common;

import com.plushih.common.messages.Messages;
import com.plushih.entities.RentMasterEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;


public class TesSendEmail {

    public static Message message = null;

    public static void createMail(){
        MimeBodyPart mbp = new MimeBodyPart();
        try{
            //메일 본문 작성
            //text 경우
            mbp.setText("Mail send");

            //message 객체에 본문을 넣기 위하여 Multipart 객체 생성
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(mbp);

            /*
            //파일 첨부일 경우
            MimeBodyPart mbp_file = new MimeBodyPart();
            mbp_file.setDataHandler(new DataHandler(new FileDataSource("[보낼 파일 경로]")));
            mbp_file.setFileName("[보낼 파일 이름]");
            mp.addBodyPart(mbp_file);

            //html일 경우
            MimeBodyPart mbp_html = new MimeBodyPart();
            mbp_html.setDataHandler(new DataHandler(new ByteArrayDataSource(new FileInputStream(new File("[보낼 HTML 경로]")), "text/html")));
            mp.addBodyPart(mbp_html);
            */

            //메일 제목 넣기
            message.setSubject("Send Mail");

            //메일 본문을 넣기
            message.setContent(mp);

            //보내는 날짜
            message.setSentDate(new Date());

            //보내는 메일 주소
            message.setFrom(new InternetAddress("storm1927@gmail.com"));

            //단건 전송일 때는 사용 start
            //message.setRecipient(RecipientType.TO, new InternetAddress(""));
            //단건 전송일 때는 사용 end

            //복수 건 전송일 때는 사용 start
            InternetAddress[] receive_address = {new InternetAddress("storm1927@gmail.com")};
            message.setRecipients(RecipientType.TO, receive_address);
            //복수 건 전송일 때는 사용 end

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void connectSMTP(){
        Properties prop = new Properties();

        //Gmail 연결을 위하여 아래 설정 적용
        //사내 메일 망일 경우 smtp host 만 설정해도 됨 (특정 포트가 아닐경우)
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.starttls.enable","true");
        prop.put("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");

        //SMTP 서버 계정 정보 (GMail 용)
        MyAuthenticator authenticator = new MyAuthenticator("storm1927@gmail.com", "dltjdgh1!");

        Session session = Session.getDefaultInstance(prop, authenticator);

        try{
            message = new MimeMessage(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendMail(){
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
        connectSMTP();
        createMail();
        sendMail();
    }

    static class MyAuthenticator extends Authenticator {
        private String id;
        private String pw;
        public MyAuthenticator(String id, String pw) {
            this.id = id;
            this.pw = pw;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(id, pw);
        }
    }

}
