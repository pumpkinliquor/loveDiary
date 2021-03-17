package com.plushih.entities;

import java.io.Serializable;
import java.util.List;

public class BusinessInfoEntity extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer biSeq;
	private String biName;
	private String biBizNum;
	private String biCeoName;
	private String biTel;
	private String biFax;
	private String biEmail;
	private String biStartDate;
	private String biEndDate;
	private String biEtc;
	private String biIsDel;
	private String biDelDate;
	private String regDate;
	private String updDate;


	public AddressInfoEntity addressInfo;

	public AddressInfoEntity getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(AddressInfoEntity addressInfo) {
		this.addressInfo = addressInfo;
	}

	public Integer getBiSeq(){ return biSeq; }
	public void setBiSeq(Integer biSeq){ this.biSeq = biSeq; }

	public String getBiName(){ return biName; }
	public void setBiName(String biName){ this.biName = biName; }

	public String getBiBizNum(){ return biBizNum; }
	public void setBiBizNum(String biBizNum){ this.biBizNum = biBizNum; }

	public String getBiCeoName(){ return biCeoName; }
	public void setBiCeoName(String biCeoName){ this.biCeoName = biCeoName; }

	public String getBiTel(){ return biTel; }
	public void setBiTel(String biTel){ this.biTel = biTel; }

	public String getBiFax(){ return biFax; }
	public void setBiFax(String biFax){ this.biFax = biFax; }

	public String getBiEmail(){ return biEmail; }
	public void setBiEmail(String biEmail){ this.biEmail = biEmail; }

	public String getBiStartDate(){ return biStartDate; }
	public void setBiStartDate(String biStartDate){ this.biStartDate = biStartDate; }

	public String getBiEndDate(){ return biEndDate; }
	public void setBiEndDate(String biEndDate){ this.biEndDate = biEndDate; }

	public String getBiEtc(){ return biEtc; }
	public void setBiEtc(String biEtc){ this.biEtc = biEtc; }

	public String getBiIsDel(){ return biIsDel; }
	public void setBiIsDel(String biIsDel){ this.biIsDel = biIsDel; }

	public String getBiDelDate(){ return biDelDate; }
	public void setBiDelDate(String biDelDate){ this.biDelDate = biDelDate; }

	public String getRegDate(){ return regDate; }
	public void setRegDate(String regDate){ this.regDate = regDate; }

	public String getUpdDate(){ return updDate; }
	public void setUpdDate(String updDate){ this.updDate = updDate; }

	@Override
	public String toString() {
		return "BusinessInfoEntity[biSeq="+biSeq+",biName="+biName+",biBizNum="+biBizNum+",biCeoName="+biCeoName+",biTel="+biTel+",biFax="+biFax+",biEmail="+biEmail+",biStartDate="+biStartDate+",biEndDate="+biEndDate+",biEtc="+biEtc+",biIsDel="+biIsDel+",biDelDate="+biDelDate+",regDate="+regDate+",updDate="+updDate+"]";
	}

	private List<BusinessInfoEntity> biList;

	public List<BusinessInfoEntity> getBiList() {
		return biList;
	}

	public void setBiList(List<BusinessInfoEntity> biList) {
		this.biList = biList;
	}

}