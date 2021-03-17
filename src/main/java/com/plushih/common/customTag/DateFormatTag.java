package com.plushih.common.customTag;
import com.plushih.common.constant.Default;
import com.plushih.common.utils.DateUtils;
import com.plushih.common.utils.StringUtils;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public class DateFormatTag extends SimpleTagSupport implements Serializable {
  private static final long serialVersionUID = 5052767065959882456L;

  private Date dateValue;
  private String dateFormat;
  private String emptyString;

  public void setDateValue(Date dateValue) {
    this.dateValue = dateValue == null ? null : new Date(dateValue.getTime());
  }

  public void setDateFormat(String dateFormat) {
    this.dateFormat = StringUtils.isEmpty(dateFormat) ? DateUtils.DEFAULT_DATE_FORMAT_YMDHMS_SLASHED : dateFormat;
  }

  public void setEmptyString(String emptyString) {
    this.emptyString = StringUtils.isEmpty(emptyString) ? Default.BLANK : emptyString;
  }

  @Override
  public void doTag() throws JspException, IOException {
    JspContext context = getJspContext();
    JspWriter out = context.getOut();
    if (dateValue == null) {
      out.println(emptyString);
    } else {
      out.println(DateUtils.getDate2String(dateValue, dateFormat));
    }
  }
}