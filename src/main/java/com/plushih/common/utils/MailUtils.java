package com.plushih.common.utils;

import com.plushih.entities.common.MailSendEntity;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineFactory;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class MailUtils {

  private JavaMailSender        mailSender;
  private VelocityEngineFactory velocityEngine;

  public JavaMailSender getMailSender () {

    return mailSender;
  }

  public void setMailSender ( final JavaMailSender mailSenderParam ) {

    mailSender = mailSenderParam;

  }

  public VelocityEngineFactory getVelocityEngine () {

    return velocityEngine;
  }

  public void setVelocityEngine ( final VelocityEngineFactory velocityEngineParam ) {

    velocityEngine = velocityEngineParam;
  }

  public void sendMail ( final MailSendEntity mailSendEntityParam ) throws IOException, MessagingException {

    sendMail( mailSendEntityParam, null, "UTF-8" );
  }

  public void sendMail ( final MailSendEntity mailSendEntityParam,
                         final Map< String, Object > emailData ) throws IOException, MessagingException {

    sendMail( mailSendEntityParam, emailData, "UTF-8" );
  }

  public void sendMail ( final MailSendEntity mailSendEntityParam,
                         final Map< String, Object > emailData, final String mailEncoding )
    throws IOException, MessagingException {

    MimeMessage message = mailSender.createMimeMessage();

//    String mailBody = VelocityEngineUtils.mergeTemplateIntoString( velocityEngine.createVelocityEngine(),
//                                                                   mailSendEntityParam.getEmailForm(),
//                                                                   mailEncoding,
//                                                                   emailData );
    String mailBody = mailSendEntityParam.getEmailForm();
    MimeMessageHelper messageHelper = new MimeMessageHelper( message, true, mailEncoding );
    messageHelper.setSubject( mailSendEntityParam.getSubject() );
    messageHelper.setTo( mailSendEntityParam.getRecipient() );
    messageHelper.setFrom( mailSendEntityParam.getSender(), mailSendEntityParam.getSenderName() );
    messageHelper.setText( mailBody, mailSendEntityParam.getIsHtml() );
    if ( mailSendEntityParam.getSentDate() != null ) {
      messageHelper.setSentDate( mailSendEntityParam.getSentDate() );
    }
    if ( mailSendEntityParam.getCc() != null && mailSendEntityParam.getCc().length > 0 ) {
      messageHelper.setCc( mailSendEntityParam.getCc() ); 
    }
    //파일첨부
    if ( mailSendEntityParam.getFile() != null && mailSendEntityParam.getFile().length() > 0 ) {
    	FileSystemResource file = new FileSystemResource(new File(mailSendEntityParam.getFile()));
        messageHelper.addAttachment(mailSendEntityParam.getFileName(), file);
      }
    mailSender.send( message );
  }
}

