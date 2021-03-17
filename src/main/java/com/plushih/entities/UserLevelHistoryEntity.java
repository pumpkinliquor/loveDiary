package com.plushih.entities;

import java.io.Serializable;

public class UserLevelHistoryEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 285168480768523846L;
	
	private int mlhId;
	private int memId;
	private int mlhFrom;
	private int mlhTo;
	private String mlhDatetime;
	private String mlhReason;
	private String mlhIp;
	private String regSysdate;
	private String udtSysdate;
	
	public int getMlhId() {
		return mlhId;
	}
	public void setMlhId(int mlhId) {
		this.mlhId = mlhId;
	}
	public int getMemId() {
		return memId;
	}
	public void setMemId(int memId) {
		this.memId = memId;
	}
	public int getMlhFrom() {
		return mlhFrom;
	}
	public void setMlhFrom(int mlhFrom) {
		this.mlhFrom = mlhFrom;
	}
	public int getMlhTo() {
		return mlhTo;
	}
	public void setMlhTo(int mlhTo) {
		this.mlhTo = mlhTo;
	}
	public String getMlhDatetime() {
		return mlhDatetime;
	}
	public void setMlhDatetime(String mlhDatetime) {
		this.mlhDatetime = mlhDatetime;
	}
	public String getMlhReason() {
		return mlhReason;
	}
	public void setMlhReason(String mlhReason) {
		this.mlhReason = mlhReason;
	}
	public String getMlhIp() {
		return mlhIp;
	}
	public void setMlhIp(String mlhIp) {
		this.mlhIp = mlhIp;
	}
	public String getRegSysdate() {
		return regSysdate;
	}
	public void setRegSysdate(String regSysdate) {
		this.regSysdate = regSysdate;
	}
	public String getUdtSysdate() {
		return udtSysdate;
	}
	public void setUdtSysdate(String udtSysdate) {
		this.udtSysdate = udtSysdate;
	}
	
	@Override
	public String toString() {
		return "UserLevelHistoryEntity [mlhId=" + mlhId + ", memId=" + memId + ", mlhFrom=" + mlhFrom + ", mlhTo="
				+ mlhTo + ", mlhDatetime=" + mlhDatetime + ", mlhReason=" + mlhReason + ", mlhIp=" + mlhIp
				+ ", regSysdate=" + regSysdate + ", udtSysdate=" + udtSysdate + "]";
	}
	
}