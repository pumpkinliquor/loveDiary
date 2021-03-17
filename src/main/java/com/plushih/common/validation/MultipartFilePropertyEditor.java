package com.plushih.common.validation;

import java.beans.PropertyEditorSupport;

public class MultipartFilePropertyEditor extends PropertyEditorSupport {

  /**
   * 파일 미첨부 시, MultipartFile 대신 빈문자열(String)로 인식되어
   * Bad Request 오류가 발생하는 것을 방지하기 위해 문자열로 넘어오면 초기값 null 로 설정
   * @param text
   */
  @Override
  public void setAsText(String text) {
    setValue(null);
  }

}
