package com.plushih.entities;

import java.io.Serializable;

public class UserMasterEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer umSeq;
	private Integer biSeq;
	private Integer ugSeq;
	private Integer abSeq;
	private Integer afSeq;
	private Integer gbSeq;

	private String umPw;
	private String umId;
	private String umName;
	private String umHp;
	private String umTel;
	private String umEmail;
	private String umUmAddr;
	private String umZipcode;
	private String umCompany;
	private String umCompanySub;
	private Integer umSort;
	private String umJichek;
	private String umJicgup;
	private String umType;
	private String umInDate;
	private String umOutDate;
	private String umImg;
	private String umEtc;
	private String umStep;

	private String umWork;

	private String regDate;
	private String regUmSeq;
	private String udtDate;
	private String udtUmSeq;
	private String sessionkey;
	private String sessionlimit;
	private String regSysdate;
	private String udtSysdate;

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

	public Integer getGbSeq() {
		return gbSeq;
	}

	public void setGbSeq(Integer gbSeq) {
		this.gbSeq = gbSeq;
	}

	public Integer getAfSeq() {
		return afSeq;
	}

	public String getSessionkey() {
		return sessionkey;
	}

	public void setSessionkey(String sessionkey) {
		this.sessionkey = sessionkey;
	}

	public String getSessionlimit() {
		return sessionlimit;
	}

	public void setSessionlimit(String sessionlimit) {
		this.sessionlimit = sessionlimit;
	}

	public Integer getUmSeq(){ return umSeq; }
	public void setUmSeq(Integer umSeq){ this.umSeq = umSeq; }

	public Integer getBiSeq(){ return biSeq; }
	public void setBiSeq(Integer biSeq){ this.biSeq = biSeq; }

	public Integer getUgSeq(){ return ugSeq; }
	public void setUgSeq(Integer ugSeq){ this.ugSeq = ugSeq; }

	public Integer getAbSeq(){ return abSeq; }
	public void setAbSeq(Integer abSeq){ this.abSeq = abSeq; }

	public Integer getAf(){ return afSeq; }
	public void setAfSeq(Integer afSeq){ this.afSeq = afSeq; }

	public String getUmPw(){ return umPw; }
	public void setUmPw(String umPw){ this.umPw = umPw; }

	public String getUmId(){ return umId; }
	public void setUmId(String umId){ this.umId = umId; }

	public String getUmName(){ return umName; }
	public void setUmName(String umName){ this.umName = umName; }

	public String getUmHp(){ return umHp; }
	public void setUmHp(String umHp){ this.umHp = umHp; }

	public String getUmTel(){ return umTel; }
	public void setUmTel(String umTel){ this.umTel = umTel; }

	public String getUmEmail(){ return umEmail; }
	public void setUmEmail(String umEmail){ this.umEmail = umEmail; }

	public String getUmUmAddr(){ return umUmAddr; }
	public void setUmUmAddr(String umUmAddr){ this.umUmAddr = umUmAddr; }

	public String getUmZipcode(){ return umZipcode; }
	public void setUmZipcode(String umZipcode){ this.umZipcode = umZipcode; }

	public String getUmCompany(){ return umCompany; }
	public void setUmCompany(String umCompany){ this.umCompany = umCompany; }

	public String getUmCompanySub(){ return umCompanySub; }
	public void setUmCompanySub(String umCompanySub){ this.umCompanySub = umCompanySub; }

	public Integer getUmSort(){ return umSort; }
	public void setUmSort(Integer umSort){ this.umSort = umSort; }

	public String getUmJichek(){ return umJichek; }
	public void setUmJichek(String umJichek){ this.umJichek = umJichek; }

	public String getUmJicgup(){ return umJicgup; }
	public void setUmJicgup(String umJicgup){ this.umJicgup = umJicgup; }

	public String getUmType(){ return umType; }
	public void setUmType(String umType){ this.umType = umType; }

	public String getUmInDate(){ return umInDate; }
	public void setUmInDate(String umInDate){ this.umInDate = umInDate; }

	public String getUmOutDate(){ return umOutDate; }
	public void setUmOutDate(String umOutDate){ this.umOutDate = umOutDate; }

	public String getUmImg(){ return umImg; }
	public void setUmImg(String umImg){ this.umImg = umImg; }

	public String getUmEtc(){ return umEtc; }
	public void setUmEtc(String umEtc){ this.umEtc = umEtc; }

	public String getUmStep(){ return umStep; }
	public void setUmStep(String umStep){ this.umStep = umStep; }

	public String getRegDate(){ return regDate; }
	public void setRegDate(String regDate){ this.regDate = regDate; }

	public String getRegUmSeq() { return regUmSeq; }
	public void setRegUmSeq(String regUmSeq) { this.regUmSeq = regUmSeq; }

	public String getUdtDate() { return udtDate; }
	public void setUdtDate(String udtDate) { this.udtDate = udtDate; }

	public String getUdtUmSeq() { return udtUmSeq; }
	public void setUdtUmSeq(String udtUmSeq) { this.udtUmSeq = udtUmSeq; }

	public String getUmWork() { return umWork; }
	public void setUmWork(String umWork) { this.umWork = umWork; }

	@Override
	public String toString() {
		return "UserMasterEntity [umSeq=" + umSeq + ", biSeq=" + biSeq + ", ugSeq=" + ugSeq + ", abSeq=" + abSeq
				+ ", afSeq=" + afSeq + ", gbSeq=" + gbSeq + ", umPw=" + umPw + ", umId=" + umId + ", umName=" + umName
				+ ", umHp=" + umHp + ", umTel=" + umTel + ", umEmail=" + umEmail + ", umUmAddr=" + umUmAddr
				+ ", umZipcode=" + umZipcode + ", umCompany=" + umCompany + ", umCompanySub=" + umCompanySub
				+ ", umSort=" + umSort + ", umJichek=" + umJichek + ", umJicgup=" + umJicgup + ", umType=" + umType
				+ ", umInDate=" + umInDate + ", umOutDate=" + umOutDate + ", umImg=" + umImg + ", umEtc=" + umEtc
				+ ", umStep=" + umStep + ", umWork=" + umWork + ", regDate=" + regDate + ", regUmSeq=" + regUmSeq
				+ ", udtDate=" + udtDate + ", udtUmSeq=" + udtUmSeq + ", sessionkey=" + sessionkey + ", sessionlimit="
				+ sessionlimit + "]";
	}
	
}