package com.plushih.entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserMemberHistoryEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer mnhId;
	private Integer memId;
	private String mnhNickname;
	private Timestamp mnhRegistDatetime;
	private String mtaTermsOfService;
	private String mtaPrivacyPolicy;
	private String mtaMarketing;
	private Timestamp mtaRegisterDatatime;
	private String mlhTo;
	private Timestamp mlhDatetime;
	
	public Integer getMnhId() {
		return mnhId;
	}
	public void setMnhId(Integer mnhId) {
		this.mnhId = mnhId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public String getMnhNickname() {
		return mnhNickname;
	}
	public void setMnhNickname(String mnhNickname) {
		this.mnhNickname = mnhNickname;
	}
	public Timestamp getMnhRegistDatetime() {
		return mnhRegistDatetime;
	}
	public void setMnhRegistDatetime(Timestamp mnhRegistDatetime) {
		this.mnhRegistDatetime = mnhRegistDatetime;
	}
	public String getMtaTermsOfService() {
		return mtaTermsOfService;
	}
	public void setMtaTermsOfService(String mtaTermsOfService) {
		this.mtaTermsOfService = mtaTermsOfService;
	}
	public String getMtaPrivacyPolicy() {
		return mtaPrivacyPolicy;
	}
	public void setMtaPrivacyPolicy(String mtaPrivacyPolicy) {
		this.mtaPrivacyPolicy = mtaPrivacyPolicy;
	}
	public String getMtaMarketing() {
		return mtaMarketing;
	}
	public void setMtaMarketing(String mtaMarketing) {
		this.mtaMarketing = mtaMarketing;
	}
	public Timestamp getMtaRegisterDatatime() {
		return mtaRegisterDatatime;
	}
	public void setMtaRegisterDatatime(Timestamp mtaRegisterDatatime) {
		this.mtaRegisterDatatime = mtaRegisterDatatime;
	}
	public String getMlhTo() {
		return mlhTo;
	}
	public void setMlhTo(String mlhTo) {
		this.mlhTo = mlhTo;
	}
	public Timestamp getMlhDatetime() {
		return mlhDatetime;
	}
	public void setMlhDatetime(Timestamp mlhDatetime) {
		this.mlhDatetime = mlhDatetime;
	}
	
	@Override
	public String toString() {
		return "UserMemberHistoryEntity [mnhId=" + mnhId + ", memId=" + memId + ", mnhNickname=" + mnhNickname
				+ ", mnhRegistDatetime=" + mnhRegistDatetime + ", mtaTermsOfService=" + mtaTermsOfService
				+ ", mtaPrivacyPolicy=" + mtaPrivacyPolicy + ", mtaMarketing=" + mtaMarketing + ", mtaRegisterDatatime="
				+ mtaRegisterDatatime + ", mlhTo=" + mlhTo + ", mlhDatetime=" + mlhDatetime + "]";
	}
	
}