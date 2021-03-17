package com.plushih.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CommonResultEntity implements Serializable {

  private static final long serialVersionUID = -8264804965208752968L;

    /**
     * The draw counter that this object is a response to - from the draw parameter
     * sent as part of the data request.
     */
    private int draw;

    /**
     * Total records, before filtering.
     */
    private long recordsTotal;

    /**
     * Total records, after filtering (i.e. the total number of records after
     * filtering has been applied - not just the number of records being returned
     * for this page of data).
     */
    private long recordsFiltered;

    /**
     * The data to be displayed in the table. This is an array of data source
     * objects, one for each row, which will be used by DataTables.
     */
    private List<Map<String, String>> data;

    /**
     * If an error occurs during the running of the server-side processing script,
     * you can inform the user of this error by passing back the error message to be
     * displayed using this parameter. Do not include if there is no error.
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String error;

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<Map<String, String>> getData() {
		return data;
	}

	public void setData(List<Map<String, String>> data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}




    private String resultMessage;
    private String resultAction;


    private String resultUrl;
    private Object resultValue;


    private List<?> resultList;


    private String resultCode;
    private String resultMsg;
    private String errCode;



    private String errMsg;

  public Map<String, Object> getResultData() {
    return resultData;
  }

  public void setResultData(Map<String, Object> resultData) {
    this.resultData = resultData;
  }

  private Map<String,Object> resultData;


  public void add(String pushKey,Object pushValue){
    resultData.put(pushKey,pushValue);
  }

  public String getResultCode() {
    return resultCode;
  }

  public void setResultCode(String resultCode) {
    this.resultCode = resultCode;
  }

  public String getResultMessage() {
    return resultMessage;
  }

  public void setResultMessage(String resultMessage) {
    this.resultMessage = resultMessage;
  }

  public String getResultAction() {
    return resultAction;
  }

  public void setResultAction(String resultAction) {
    this.resultAction = resultAction;
  }

  public Object getResultValue() {
    return resultValue;
  }

  public void setResultValue(Object resultValue) {
    this.resultValue = resultValue;
  }

  public List<?> getResultList() {
    return resultList;
  }

  public void setResultList(List<?> resultList) {
    this.resultList = resultList;
  }

  public String getResultUrl() {
    return resultUrl;
  }

  public void setResultUrl(String resultUrl) {
    this.resultUrl = resultUrl;
  }
  public String getResultMsg() {
    return resultMsg;
  }

  public void setResultMsg(String resultMsg) {
    this.resultMsg = resultMsg;
  }
  public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
