package com.plushih.entities;

import java.io.Serializable;

public class AigoLearnHistoryEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 3268081362873192864L;
	
	private String thiTestType;
	private int subId;
	private int acaId;
	private int levId;
	private int acvId;
	private String memId;
	private int regMemId;
	private String regDate;
	private String newYn;
	private String levName;
	private String orderType;
	
	public String getThiTestType() {
		return thiTestType;
	}
	public void setThiTestType(String thiTestType) {
		this.thiTestType = thiTestType;
	}
	public int getSubId() {
		return subId;
	}
	public void setSubId(int subId) {
		this.subId = subId;
	}
	public int getAcaId() {
		return acaId;
	}
	public void setAcaId(int acaId) {
		this.acaId = acaId;
	}
	public int getLevId() {
		return levId;
	}
	public void setLevId(int levId) {
		this.levId = levId;
	}
	public int getAcvId() {
		return acvId;
	}
	public void setAcvId(int acvId) {
		this.acvId = acvId;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public int getRegMemId() {
		return regMemId;
	}
	public void setRegMemId(int regMemId) {
		this.regMemId = regMemId;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getNewYn() {
		return newYn;
	}
	public void setNewYn(String newYn) {
		this.newYn = newYn;
	}
	public String getLevName() {
		return levName;
	}
	public void setLevName(String levName) {
		this.levName = levName;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	@Override
	public String toString() {
		return "AigoLearnHistoryEntity [thiTestType=" + thiTestType + ", subId=" + subId + ", acaId=" + acaId
				+ ", levId=" + levId + ", acvId=" + acvId + ", memId=" + memId + ", regMemId=" + regMemId + ", regDate="
				+ regDate + ", newYn=" + newYn + ", levName=" + levName + ", orderType=" + orderType + "]";
	}
	
}