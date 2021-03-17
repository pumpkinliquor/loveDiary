package com.plushih.entities.common;



import com.plushih.common.constant.Default;

import java.io.Serializable;

/**
 * Created by sangyong on 11/25/14.
 */
public class DefaultResponseEntity<T> implements Serializable {


	private int statusCode;
  private String resultCode    = Default.ResultValue.FAIL;
  private String resultMessage = "";
	
	private T entity;


	public DefaultResponseEntity() {
		this.entity = null;
	}
	
	public DefaultResponseEntity(T entity) {
		this.entity = entity;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
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

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}
}
