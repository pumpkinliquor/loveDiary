package com.plushih.common.ci;

import com.plushih.common.utils.StringUtils;
import freemarker.template.utility.StringUtil;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Enumeration;

/**
 *
 * 인풋 코드이그나이드처럼 사용하기위함
 */
public class Input {

    private HttpServletRequest request;
    private RequestMethod method;

    private String searchString;
    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }





    public Input() {
    }

    public Input(HttpServletRequest request, RequestMethod method) {
        this.request = request;
        this.method = method;

        Enumeration e = request.getParameterNames();
        while ( e.hasMoreElements() ){
			String name = (String) e.nextElement();
			String[] values = request.getParameterValues(name);
			for (String value : values) {
			    if(name.indexOf("search[value]")>-1){
			        setSearchString(value);
                }
				//Debug.log("name=" + name + ",value=" + value);
			}
		}
    }

    public String ipAddress(){
        return request.getRemoteAddr();
    }
    

    public String get(String parameter) {
        String reval = null;
        if (method == RequestMethod.GET) {
            reval = request.getParameter(parameter);
        }
        if(reval != null ){
           reval= StringUtils.addSlashes(reval);
        }
        return reval;
    }
    public String post(String parameter) {
        String reval = null;
        if (method == RequestMethod.POST) {
            reval = request.getParameter(parameter);
        }
        if(reval != null ){
           reval= StringUtils.addSlashes(reval);
        }
        return reval;
    }
    public Object post(String parameter,String dataType) {
        Object reval = null;
        if (method == RequestMethod.POST) {
            reval = request.getParameter(parameter);

            switch((dataType+"").toLowerCase()){
                default:
			case "TINYBLOB":
            case "BLOB":
            case "MEDIUMBLOB":
            case "LONGBLOB":
            case "CHAR":
            case "VARCHAR":
            case "TINYTEXT":
            case "TEXT":
            case "MEDIUMTEXT":
            case "LONGTEXT":
				reval = (String)reval;
				break;
            case "DATE":
            case "TIME":
            case "DATETIME":
            case "TIMESTAMP":
				reval = (String)reval;
				break;
			case "YEAR":
            case "TINYINT":
            case "SMALLINT":
            case "MEDIUMINT":
            case "INT":
            case "BIGINT":
				reval = (Integer)reval;
				break;
				case "DOUBLE":
				reval = (double)reval;
				break;
            case "FLOAT":
                reval = (float)reval;
                break;
			case "DECIMAL":
				reval = (BigDecimal)reval;
				break;
            }
        }
        return reval;
    }

    public String get_post(String parameter) {
        String reval = null;
        reval = request.getParameter(parameter);
        return StringUtils.nvl(reval,"");
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public void setMethod(RequestMethod method) {
        this.method = method;
    }
}
