package com.plushih.entities;

import java.io.Serializable;
import java.util.List;

public class AigoNotionEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer notId;
	private Integer subId;
	private Integer acaId;
	private Integer subAcaId;
	private Integer acvId;
	private Integer cptId;
	private String notKey;
	private String notName;
	private String notType;
	private Integer notOrder;
	private String notText;
	private String notPlayName;
	private String notPlayPath;
	private String notPlayRuntime;
	private String notPlayRate;
	private String useYn;
	private String regDate;
	private Integer regUmSeq;
	private String udtDate;
	private Integer udtUmSeq;
	private List<SiteAttachFileEntity> fileList;
	private String qstKey;
	private String qstId;
	private String cptName;
	
	public Integer getNotId() {
		return notId;
	}
	public void setNotId(Integer notId) {
		this.notId = notId;
	}
	public Integer getSubId() {
		return subId;
	}
	public void setSubId(Integer subId) {
		this.subId = subId;
	}
	public Integer getAcaId() {
		return acaId;
	}
	public void setAcaId(Integer acaId) {
		this.acaId = acaId;
	}
	public Integer getSubAcaId() {
		return subAcaId;
	}
	public void setSubAcaId(Integer subAcaId) {
		this.subAcaId = subAcaId;
	}
	public Integer getAcvId() {
		return acvId;
	}
	public void setAcvId(Integer acvId) {
		this.acvId = acvId;
	}
	public Integer getCptId() {
		return cptId;
	}
	public void setCptId(Integer cptId) {
		this.cptId = cptId;
	}
	public String getNotKey() {
		return notKey;
	}
	public void setNotKey(String notKey) {
		this.notKey = notKey;
	}
	public String getNotName() {
		return notName;
	}
	public void setNotName(String notName) {
		this.notName = notName;
	}
	public String getNotType() {
		return notType;
	}
	public void setNotType(String notType) {
		this.notType = notType;
	}
	public Integer getNotOrder() {
		return notOrder;
	}
	public void setNotOrder(Integer notOrder) {
		this.notOrder = notOrder;
	}
	public String getNotText() {
		return notText;
	}
	public void setNotText(String notText) {
		this.notText = notText;
	}
	public String getNotPlayName() {
		return notPlayName;
	}
	public void setNotPlayName(String notPlayName) {
		this.notPlayName = notPlayName;
	}
	public String getNotPlayPath() {
		return notPlayPath;
	}
	public void setNotPlayPath(String notPlayPath) {
		this.notPlayPath = notPlayPath;
	}
	public String getNotPlayRuntime() {
		return notPlayRuntime;
	}
	public void setNotPlayRuntime(String notPlayRuntime) {
		this.notPlayRuntime = notPlayRuntime;
	}
	public String getNotPlayRate() {
		return notPlayRate;
	}
	public void setNotPlayRate(String notPlayRate) {
		this.notPlayRate = notPlayRate;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Integer getRegUmSeq() {
		return regUmSeq;
	}
	public void setRegUmSeq(Integer regUmSeq) {
		this.regUmSeq = regUmSeq;
	}
	public String getUdtDate() {
		return udtDate;
	}
	public void setUdtDate(String udtDate) {
		this.udtDate = udtDate;
	}
	public Integer getUdtUmSeq() {
		return udtUmSeq;
	}
	public void setUdtUmSeq(Integer udtUmSeq) {
		this.udtUmSeq = udtUmSeq;
	}
	public List<SiteAttachFileEntity> getFileList() {
		return fileList;
	}
	public void setFileList(List<SiteAttachFileEntity> fileList) {
		this.fileList = fileList;
	}
	public String getQstKey() {
		return qstKey;
	}
	public void setQstKey(String qstKey) {
		this.qstKey = qstKey;
	}
	public String getCptName() {
		return cptName;
	}
	public void setCptName(String cptName) {
		this.cptName = cptName;
	}
	public String getQstId() {
		return qstId;
	}
	public void setQstId(String qstId) {
		this.qstId = qstId;
	}
	
	@Override
	public String toString() {
		return "AigoNotionEntity [notId=" + notId + ", subId=" + subId + ", acaId=" + acaId + ", subAcaId=" + subAcaId
				+ ", acvId=" + acvId + ", cptId=" + cptId + ", notKey=" + notKey + ", notName=" + notName + ", notType="
				+ notType + ", notOrder=" + notOrder + ", notText=" + notText + ", notPlayName=" + notPlayName
				+ ", notPlayPath=" + notPlayPath + ", notPlayRuntime=" + notPlayRuntime + ", notPlayRate=" + notPlayRate
				+ ", useYn=" + useYn + ", regDate=" + regDate + ", regUmSeq=" + regUmSeq + ", udtDate=" + udtDate
				+ ", udtUmSeq=" + udtUmSeq + ", fileList=" + fileList + ", qstKey=" + qstKey + ", qstId=" + qstId
				+ ", cptName=" + cptName + "]";
	}
	
}