package com.plushih.common.tlds;

import com.plushih.common.utils.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by bearhj on 12/26/14.
 */
public class CutText extends TagSupport {

  private static final long serialVersionUID = 985800340420649574L;
  private String text;
  private int length;
  private String ellipsis;

  public int doStartTag() throws JspException {

    try {
      pageContext.getOut().write(StringUtils.cutText(text, length, ellipsis));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return SKIP_BODY;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public String getEllipsis() {
    return ellipsis;
  }

  public void setEllipsis(String ellipsis) {
    this.ellipsis = ellipsis;
  }
}