package com.plushih.entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserMemberEntity  extends PlusEntity implements Serializable{

	private static final long serialVersionUID = 158105902005757160L;

	private int memId;
	private String memUserid;
	private String memEmail;
	private String memPassword;
	private String memUsername;
	private String memNickname;
	private int memLevel;
	private int memPoint;
	private String memHomepage;
	private String memPhone;
	private String memBirthday;
	private int memSex;
	private String memZipcode;
	private String memAddress1;
	private String memAddress2;
	private String memAddress3;
	private String memAddress4;
	private int memReceiveEmail;
	private int memUseNote;
	private int memReceiveSms;
	private int memOpenProfile;
	private int memDenied;
	private int memEmailCert;
	private Timestamp memRegisterDatetime;
	private String memRegisterIp;
	private Timestamp memLastloginDatetime;
	private String memLastloginIp;
	private int memIsAdmin;
	private String memProfileContent;
	private String memAdminmemo;
	private int memFollowing;
	private int memFollowed;
	private String memIcon;
	private String memPhoto;
	private String memClass;
	private int memGrade;
	private int memSubId;
	private String memPasswordEnc;
	private String mtaTermsOfService;
	private String mtaPrivacyPolicy;
	private String mtaMarketing;
	private String mtaRegisterDatatime;
	private String memJoinChannel;
	private String memStatus;
	private int loginStateCode;
	private String memTempId;
	private String memFurGroup;
	private String memFurYn;
	private String memConYn;
	private String aigoAlarmMkt;
	private String aigoAlarmLearn;
	private String aigoAlarmNight;
	private Timestamp aigoAlarmDate;

	public int getMemId() {
		return memId;
	}
	public void setMemId(int memId) {
		this.memId = memId;
	}
	public String getMemUserid() {
		return memUserid;
	}
	public void setMemUserid(String memUserid) {
		this.memUserid = memUserid;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemPassword() {
		return memPassword;
	}
	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}
	public String getMemUsername() {
		return memUsername;
	}
	public void setMemUsername(String memUsername) {
		this.memUsername = memUsername;
	}
	public String getMemNickname() {
		return memNickname;
	}
	public void setMemNickname(String memNickname) {
		this.memNickname = memNickname;
	}
	public int getMemLevel() {
		return memLevel;
	}
	public void setMemLevel(int memLevel) {
		this.memLevel = memLevel;
	}
	public int getMemPoint() {
		return memPoint;
	}
	public void setMemPoint(int memPoint) {
		this.memPoint = memPoint;
	}
	public String getMemHomepage() {
		return memHomepage;
	}
	public void setMemHomepage(String memHomepage) {
		this.memHomepage = memHomepage;
	}
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public String getMemBirthday() {
		return memBirthday;
	}
	public void setMemBirthday(String memBirthday) {
		this.memBirthday = memBirthday;
	}
	public int getMemSex() {
		return memSex;
	}
	public void setMemSex(int memSex) {
		this.memSex = memSex;
	}
	public String getMemZipcode() {
		return memZipcode;
	}
	public void setMemZipcode(String memZipcode) {
		this.memZipcode = memZipcode;
	}
	public String getMemAddress1() {
		return memAddress1;
	}
	public void setMemAddress1(String memAddress1) {
		this.memAddress1 = memAddress1;
	}
	public String getMemAddress2() {
		return memAddress2;
	}
	public void setMemAddress2(String memAddress2) {
		this.memAddress2 = memAddress2;
	}
	public String getMemAddress3() {
		return memAddress3;
	}
	public void setMemAddress3(String memAddress3) {
		this.memAddress3 = memAddress3;
	}
	public String getMemAddress4() {
		return memAddress4;
	}
	public void setMemAddress4(String memAddress4) {
		this.memAddress4 = memAddress4;
	}
	public int getMemReceiveEmail() {
		return memReceiveEmail;
	}
	public void setMemReceiveEmail(int memReceiveEmail) {
		this.memReceiveEmail = memReceiveEmail;
	}
	public int getMemUseNote() {
		return memUseNote;
	}
	public void setMemUseNote(int memUseNote) {
		this.memUseNote = memUseNote;
	}
	public int getMemReceiveSms() {
		return memReceiveSms;
	}
	public void setMemReceiveSms(int memReceiveSms) {
		this.memReceiveSms = memReceiveSms;
	}
	public int getMemOpenProfile() {
		return memOpenProfile;
	}
	public void setMemOpenProfile(int memOpenProfile) {
		this.memOpenProfile = memOpenProfile;
	}
	public int getMemDenied() {
		return memDenied;
	}
	public void setMemDenied(int memDenied) {
		this.memDenied = memDenied;
	}
	public int getMemEmailCert() {
		return memEmailCert;
	}
	public void setMemEmailCert(int memEmailCert) {
		this.memEmailCert = memEmailCert;
	}
	public Timestamp getMemRegisterDatetime() {
		return memRegisterDatetime;
	}
	public void setMemRegisterDatetime(Timestamp memRegisterDatetime) {
		this.memRegisterDatetime = memRegisterDatetime;
	}
	public String getMemRegisterIp() {
		return memRegisterIp;
	}
	public void setMemRegisterIp(String memRegisterIp) {
		this.memRegisterIp = memRegisterIp;
	}
	public Timestamp getMemLastloginDatetime() {
		return memLastloginDatetime;
	}
	public void setMemLastloginDatetime(Timestamp memLastloginDatetime) {
		this.memLastloginDatetime = memLastloginDatetime;
	}
	public String getMemLastloginIp() {
		return memLastloginIp;
	}
	public void setMemLastloginIp(String memLastloginIp) {
		this.memLastloginIp = memLastloginIp;
	}
	public int getMemIsAdmin() {
		return memIsAdmin;
	}
	public void setMemIsAdmin(int memIsAdmin) {
		this.memIsAdmin = memIsAdmin;
	}
	public String getMemProfileContent() {
		return memProfileContent;
	}
	public void setMemProfileContent(String memProfileContent) {
		this.memProfileContent = memProfileContent;
	}
	public String getMemAdminmemo() {
		return memAdminmemo;
	}
	public void setMemAdminmemo(String memAdminmemo) {
		this.memAdminmemo = memAdminmemo;
	}
	public int getMemFollowing() {
		return memFollowing;
	}
	public void setMemFollowing(int memFollowing) {
		this.memFollowing = memFollowing;
	}
	public int getMemFollowed() {
		return memFollowed;
	}
	public void setMemFollowed(int memFollowed) {
		this.memFollowed = memFollowed;
	}
	public String getMemIcon() {
		return memIcon;
	}
	public void setMemIcon(String memIcon) {
		this.memIcon = memIcon;
	}
	public String getMemPhoto() {
		return memPhoto;
	}
	public void setMemPhoto(String memPhoto) {
		this.memPhoto = memPhoto;
	}
	public String getMemClass() {
		return memClass;
	}
	public void setMemClass(String memClass) {
		this.memClass = memClass;
	}
	public int getMemGrade() {
		return memGrade;
	}
	public void setMemGrade(int memGrade) {
		this.memGrade = memGrade;
	}
	public int getMemSubId() {
		return memSubId;
	}
	public void setMemSubId(int memSubId) {
		this.memSubId = memSubId;
	}
	public String getMemPasswordEnc() {
		return memPasswordEnc;
	}
	public void setMemPasswordEnc(String memPasswordEnc) {
		this.memPasswordEnc = memPasswordEnc;
	}
	public String getMemJoinChannel() {
		return memJoinChannel;
	}
	public void setMemJoinChannel(String memJoinChannel) {
		this.memJoinChannel = memJoinChannel;
	}
	public String getMemStatus() {
		return memStatus;
	}
	public void setMemStatus(String memStatus) {
		this.memStatus = memStatus;
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
	public String getMtaRegisterDatatime() {
		return mtaRegisterDatatime;
	}
	public void setMtaRegisterDatatime(String mtaRegisterDatatime) {
		this.mtaRegisterDatatime = mtaRegisterDatatime;
	}
	public int getLoginStateCode() {
		return loginStateCode;
	}
	public void setLoginStateCode(int loginStateCode) {
		this.loginStateCode = loginStateCode;
	}
	public String getMemTempId() {
		return memTempId;
	}
	public void setMemTempId(String memTempId) {
		this.memTempId = memTempId;
	}
	public String getMemFurGroup() {
		return memFurGroup;
	}
	public void setMemFurGroup(String memFurGroup) {
		this.memFurGroup = memFurGroup;
	}
	public String getMemFurYn() {
		return memFurYn;
	}
	public void setMemFurYn(String memFurYn) {
		this.memFurYn = memFurYn;
	}
	public String getMemConYn() {
		return memConYn;
	}
	public void setMemConYn(String memConYn) {
		this.memConYn = memConYn;
	}
	public String getAigoAlarmMkt() {
		return aigoAlarmMkt;
	}
	public void setAigoAlarmMkt(String aigoAlarmMkt) {
		this.aigoAlarmMkt = aigoAlarmMkt;
	}
	public String getAigoAlarmLearn() {
		return aigoAlarmLearn;
	}
	public void setAigoAlarmLearn(String aigoAlarmLearn) {
		this.aigoAlarmLearn = aigoAlarmLearn;
	}
	public String getAigoAlarmNight() {
		return aigoAlarmNight;
	}
	public void setAigoAlarmNight(String aigoAlarmNight) {
		this.aigoAlarmNight = aigoAlarmNight;
	}
	public Timestamp getAigoAlarmDate() {
		return aigoAlarmDate;
	}
	public void setAigoAlarmDate(Timestamp aigoAlarmDate) {
		this.aigoAlarmDate = aigoAlarmDate;
	}
	@Override
	public String toString() {
		return "UserMemberEntity [memId=" + memId + ", memUserid=" + memUserid + ", memEmail=" + memEmail
				+ ", memPassword=" + memPassword + ", memUsername=" + memUsername + ", memNickname=" + memNickname
				+ ", memLevel=" + memLevel + ", memPoint=" + memPoint + ", memHomepage=" + memHomepage + ", memPhone="
				+ memPhone + ", memBirthday=" + memBirthday + ", memSex=" + memSex + ", memZipcode=" + memZipcode
				+ ", memAddress1=" + memAddress1 + ", memAddress2=" + memAddress2 + ", memAddress3=" + memAddress3
				+ ", memAddress4=" + memAddress4 + ", memReceiveEmail=" + memReceiveEmail + ", memUseNote=" + memUseNote
				+ ", memReceiveSms=" + memReceiveSms + ", memOpenProfile=" + memOpenProfile + ", memDenied=" + memDenied
				+ ", memEmailCert=" + memEmailCert + ", memRegisterDatetime=" + memRegisterDatetime + ", memRegisterIp="
				+ memRegisterIp + ", memLastloginDatetime=" + memLastloginDatetime + ", memLastloginIp="
				+ memLastloginIp + ", memIsAdmin=" + memIsAdmin + ", memProfileContent=" + memProfileContent
				+ ", memAdminmemo=" + memAdminmemo + ", memFollowing=" + memFollowing + ", memFollowed=" + memFollowed
				+ ", memIcon=" + memIcon + ", memPhoto=" + memPhoto + ", memClass=" + memClass + ", memGrade="
				+ memGrade + ", memSubId=" + memSubId + ", memPasswordEnc=" + memPasswordEnc + ", mtaTermsOfService="
				+ mtaTermsOfService + ", mtaPrivacyPolicy=" + mtaPrivacyPolicy + ", mtaMarketing=" + mtaMarketing
				+ ", mtaRegisterDatatime=" + mtaRegisterDatatime + ", memJoinChannel=" + memJoinChannel + ", memStatus="
				+ memStatus + ", loginStateCode=" + loginStateCode + ", memTempId=" + memTempId + ", memFurGroup="
				+ memFurGroup + ", memFurYn=" + memFurYn + ", memConYn=" + memConYn + ", aigoAlarmMkt=" + aigoAlarmMkt
				+ ", aigoAlarmLearn=" + aigoAlarmLearn + ", aigoAlarmNight=" + aigoAlarmNight + ", aigoAlarmDate="
				+ aigoAlarmDate + "]";
	}
}