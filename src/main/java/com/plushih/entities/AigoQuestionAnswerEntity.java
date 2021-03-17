package com.plushih.entities;

import java.io.Serializable;

public class AigoQuestionAnswerEntity  extends PlusEntity implements Serializable{
	
	private static final long serialVersionUID = 7342497843622949603L;
	
	/** 공통 */
	private Integer ansrId;
	private String useYn;
	private String memId;
	private String regDate;
	private String regMemId;
	private String udtDate;
	private String udtMemId;
	/** info table */
	private int sCnt;
	private int fCnt;
	/** list & history table 공통 */
	private Integer qstId;
	private String ansValue;
	private String qstOrder;
	private String ansOrder;
	private String passYn;
	/** list table */
	private Integer ansId;
	private Integer durTime;
	/** history table */
	private Integer anshId;
	
	/** 문제페이지 Parameter */
	private Integer subId;
	private Integer acvId;
	private Integer levId;
	private String currentTestType;
	private String currentTestTypeSub;
	
	public Integer getAnsrId() {
		return ansrId;
	}
	public void setAnsrId(Integer ansrId) {
		this.ansrId = ansrId;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getRegMemId() {
		return regMemId;
	}
	public void setRegMemId(String regMemId) {
		this.regMemId = regMemId;
	}
	public String getUdtDate() {
		return udtDate;
	}
	public void setUdtDate(String udtDate) {
		this.udtDate = udtDate;
	}
	public String getUdtMemId() {
		return udtMemId;
	}
	public void setUdtMemId(String udtMemId) {
		this.udtMemId = udtMemId;
	}
	public int getsCnt() {
		return sCnt;
	}
	public void setsCnt(int sCnt) {
		this.sCnt = sCnt;
	}
	public int getfCnt() {
		return fCnt;
	}
	public void setfCnt(int fCnt) {
		this.fCnt = fCnt;
	}
	public Integer getQstId() {
		return qstId;
	}
	public void setQstId(Integer qstId) {
		this.qstId = qstId;
	}
	public String getAnsValue() {
		return ansValue;
	}
	public void setAnsValue(String ansValue) {
		this.ansValue = ansValue;
	}
	public String getQstOrder() {
		return qstOrder;
	}
	public void setQstOrder(String qstOrder) {
		this.qstOrder = qstOrder;
	}
	public String getAnsOrder() {
		return ansOrder;
	}
	public void setAnsOrder(String ansOrder) {
		this.ansOrder = ansOrder;
	}
	public String getPassYn() {
		return passYn;
	}
	public void setPassYn(String passYn) {
		this.passYn = passYn;
	}
	public Integer getAnsId() {
		return ansId;
	}
	public void setAnsId(Integer ansId) {
		this.ansId = ansId;
	}
	public Integer getAnshId() {
		return anshId;
	}
	public void setAnshId(Integer anshId) {
		this.anshId = anshId;
	}
	public Integer getSubId() {
		return subId;
	}
	public void setSubId(Integer subId) {
		this.subId = subId;
	}
	public Integer getAcvId() {
		return acvId;
	}
	public void setAcvId(Integer acvId) {
		this.acvId = acvId;
	}
	public Integer getLevId() {
		return levId;
	}
	public void setLevId(Integer levId) {
		this.levId = levId;
	}
	public String getCurrentTestType() {
		return currentTestType;
	}
	public void setCurrentTestType(String currentTestType) {
		this.currentTestType = currentTestType;
	}
	public String getCurrentTestTypeSub() {
		return currentTestTypeSub;
	}
	public void setCurrentTestTypeSub(String currentTestTypeSub) {
		this.currentTestTypeSub = currentTestTypeSub;
	}
	public Integer getDurTime() {
		return durTime;
	}
	public void setDurTime(Integer durTime) {
		this.durTime = durTime;
	}
	
	@Override
	public String toString() {
		return "AigoQuestionAnswerEntity [ansrId=" + ansrId + ", useYn=" + useYn + ", memId=" + memId + ", regDate="
				+ regDate + ", regMemId=" + regMemId + ", udtDate=" + udtDate + ", udtMemId=" + udtMemId + ", sCnt="
				+ sCnt + ", fCnt=" + fCnt + ", qstId=" + qstId + ", ansValue=" + ansValue + ", qstOrder=" + qstOrder
				+ ", ansOrder=" + ansOrder + ", passYn=" + passYn + ", ansId=" + ansId + ", durTime=" + durTime
				+ ", anshId=" + anshId + ", subId=" + subId + ", acvId=" + acvId + ", levId=" + levId
				+ ", currentTestType=" + currentTestType + ", currentTestTypeSub=" + currentTestTypeSub + "]";
	}
	
}