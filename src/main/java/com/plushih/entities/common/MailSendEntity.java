package com.plushih.entities.common;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sangyong on 11/28/14.
 */
public class MailSendEntity implements Serializable {

  private static final long serialVersionUID = 2283464862953687322L;

  private String   subject;
  private String[] recipient;
  private String   sender;
  private String   senderName;
  private String[] cc;
  private String   emailForm;
  private Date     sentDate;
  private boolean  isHtml;
  private String	file;
  private String fileName;

  public String[] getRecipient () {

    return recipient;
  }

  public void setRecipient ( final String[] recipientParam ) {

    recipient = recipientParam;
  }

  public String getSubject () {

    return subject;
  }

  public void setSubject ( final String subjectParam ) {

    subject = subjectParam;
  }

  public String getSender () {

    return sender;
  }

  public void setSender ( final String senderParam ) {

    sender = senderParam;
  }

  public String getSenderName () {

    return senderName;
  }

  public void setSenderName ( final String senderNameParam ) {

    senderName = senderNameParam;
  }

  public String getEmailForm () {

    return emailForm;
  }

  public void setEmailForm ( final String emailFormParam ) {

    emailForm = emailFormParam;
  }

  public boolean getIsHtml () {

    return isHtml;
  }

  public void setIsHtml ( final boolean isHtmlParam ) {

    isHtml = isHtmlParam;
  }

  public Date getSentDate () {

    return sentDate;
  }

  public void setSentDate ( Date sentDate ) {

    this.sentDate = sentDate;
  }

  public String[] getCc () {

    return cc;
  }

  public void setCc ( final String[] ccParam ) {

    cc = ccParam;
  }

public String getFile() {
	return file;
}

public void setFile(String file) {
	this.file = file;
}

public String getFileName() {
	return fileName;
}

public void setFileName(String fileName) {
	this.fileName = fileName;
}
  
}
