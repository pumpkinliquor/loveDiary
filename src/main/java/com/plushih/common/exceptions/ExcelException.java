package com.plushih.common.exceptions;

/**
 * Created by sangyong on 10/28/14.
 */
public class ExcelException extends Exception {

  private static final long serialVersionUID = -7419498411524012001L;

  protected ExcelException ( String message, Throwable cause ) {
    super( message, cause );
  }

  public ExcelException ( String message ) {
    super( message );
  }

  public ExcelException ( Throwable cause ) {
    super( cause );
  }
}
