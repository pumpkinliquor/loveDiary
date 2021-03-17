package com.plushih.entities.common;

/**
 * Created by lms on 15. 5. 17.
 */
public class CheckAccessException extends CommonRuntimeException {
  private String type;
  private String resultAction;


  public CheckAccessException(String resultMessage) {
    super(resultMessage);
  }

  public CheckAccessException(String resultMessage, String resultAction) {
    super(resultMessage);
    this.resultAction = resultAction;
  }

  public String getResultAction() {
    return resultAction;
  }

  public void setResultAction(String resultAction) {
    this.resultAction = resultAction;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public static void checkAccess(boolean expression, String resultMessage) {
    if(!expression) {
      throw new CheckAccessException(resultMessage);
    }
  }

  public static void checkAccess(boolean expression, String resultMessage, String resultAction) {
    if(!expression) {
      throw new CheckAccessException(resultMessage, resultAction);
    }
  }

}
