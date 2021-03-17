package com.plushih.entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserMemberStatisticsEntity  extends PlusEntity implements Serializable{
	
	private static final long serialVersionUID = 158105902005757160L;
	
	private String memId;
	private String memUserid;
	private String baseDate;
	private int userCount;
	private int insCount;
	private int joinCount;
	private int totalUserCount;
	private int totalInsCount;
	private int totalJoinCount;
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemUserid() {
		return memUserid;
	}
	public void setMemUserid(String memUserid) {
		this.memUserid = memUserid;
	}
	public int getUserCount() {
		return userCount;
	}
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	public int getInsCount() {
		return insCount;
	}
	public String getBaseDate() {
		return baseDate;
	}
	public void setBaseDate(String baseDate) {
		this.baseDate = baseDate;
	}
	public void setInsCount(int insCount) {
		this.insCount = insCount;
	}
	public int getJoinCount() {
		return joinCount;
	}
	public void setJoinCount(int joinCount) {
		this.joinCount = joinCount;
	}
	public int getTotalUserCount() {
		return totalUserCount;
	}
	public void setTotalUserCount(int totalUserCount) {
		this.totalUserCount = totalUserCount;
	}
	public int getTotalInsCount() {
		return totalInsCount;
	}
	public void setTotalInsCount(int totalInsCount) {
		this.totalInsCount = totalInsCount;
	}
	public int getTotalJoinCount() {
		return totalJoinCount;
	}
	public void setTotalJoinCount(int totalJoinCount) {
		this.totalJoinCount = totalJoinCount;
	}
	@Override
	public String toString() {
		return "UserMemberStatisticsEntity [memId=" + memId + ", memUserid=" + memUserid + ", baseDate=" + baseDate
				+ ", userCount=" + userCount + ", insCount=" + insCount + ", joinCount=" + joinCount
				+ ", totalUserCount=" + totalUserCount + ", totalInsCount=" + totalInsCount + ", totalJoinCount="
				+ totalJoinCount + "]";
	}
	
	
}