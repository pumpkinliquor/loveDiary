package com.plushih.common.tlds;

import com.plushih.common.utils.HttpUtils;
import com.plushih.common.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Created by sangyong on 10/10/14.
 */
public class LocaleTag extends TagSupport {

  private String locale;

  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }

  @Override
  public int doStartTag() throws JspException {
    HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
    JspWriter out = pageContext.getOut();

    try {
      String url = null;
      if (!StringUtils.isEmpty(HttpUtils.getParamsToStrExcludeById(request, "locale"))) {
        url = HttpUtils.getParamsToStrExcludeById(request, "locale") + "&locale=" + locale;
      } else {
        url = "?locale=" + locale;
      }
      out.write(request.getAttribute("currentUrl") + url);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SKIP_BODY;
  }

}
