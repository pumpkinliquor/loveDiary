package com.plushih.common.messages;

import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * Created by sangyong on 10/10/14.
 */
public class Messages {

  private MessageSource messages;

  public void setMessages ( final MessageSource messageSource ) {

    this.messages = messageSource;
  }

  public String getMessage ( final String key, final Object[] args ) {

    return this.messages.getMessage( key, args, Locale.getDefault() );
  }

  public String getMessage ( final String key, final Object[] args, final Locale locale ) {

    return this.messages.getMessage( key, args, locale );
  }

  public String getMessage ( final String key ) {

    return this.messages.getMessage( key, null, Locale.getDefault() );
  }

  public String getMessage ( final String key, final Locale locale ) {

    return this.messages.getMessage( key, null, locale );
  }
}
