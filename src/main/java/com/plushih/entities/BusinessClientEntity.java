package com.plushih.entities;

import java.io.Serializable;

public class BusinessClientEntity extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer bcSeq;
	private Integer biSeq;
	private Integer umSeq;
	private String bcType;
	private String bcName;

	private String biName;
	private String bcBizNum;
	private String bcCeoName;
	private String bcTel;
	private String bcFax;
	private String bcEmail;
	private String bcStartDate;
	private String bcEndDate;
	private String bcEtc;
	private String bcIsDel;
	private String bcDelDate;
	private String regDate;
	private String updDate;

	public Integer getBcSeq(){ return bcSeq; }
	public void setBcSeq(Integer bcSeq){ this.bcSeq = bcSeq; }

	public Integer getBiSeq(){ return biSeq; }
	public void setBiSeq(Integer biSeq){ this.biSeq = biSeq; }

	public Integer getUmSeq(){ return umSeq; }
	public void setUmSeq(Integer umSeq){ this.umSeq = umSeq; }

	public String getBcType(){ return bcType; }
	public void setBcType(String bcType){ this.bcType = bcType; }

	public String getBcName(){ return bcName; }
	public void setBcName(String bcName){ this.bcName = bcName; }

	public String getBcBizNum(){ return bcBizNum; }
	public void setBcBizNum(String bcBizNum){ this.bcBizNum = bcBizNum; }

	public String getBcCeoName(){ return bcCeoName; }
	public void setBcCeoName(String bcCeoName){ this.bcCeoName = bcCeoName; }

	public String getBcTel(){ return bcTel; }
	public void setBcTel(String bcTel){ this.bcTel = bcTel; }

	public String getBcFax(){ return bcFax; }
	public void setBcFax(String bcFax){ this.bcFax = bcFax; }

	public String getBcEmail(){ return bcEmail; }
	public void setBcEmail(String bcEmail){ this.bcEmail = bcEmail; }

	public String getBcStartDate(){ return bcStartDate; }
	public void setBcStartDate(String bcStartDate){ this.bcStartDate = bcStartDate; }

	public String getBcEndDate(){ return bcEndDate; }
	public void setBcEndDate(String bcEndDate){ this.bcEndDate = bcEndDate; }

	public String getBcEtc(){ return bcEtc; }
	public void setBcEtc(String bcEtc){ this.bcEtc = bcEtc; }

	public String getBcIsDel(){ return bcIsDel; }
	public void setBcIsDel(String bcIsDel){ this.bcIsDel = bcIsDel; }

	public String getBcDelDate(){ return bcDelDate; }
	public void setBcDelDate(String bcDelDate){ this.bcDelDate = bcDelDate; }

	public String getRegDate(){ return regDate; }
	public void setRegDate(String regDate){ this.regDate = regDate; }

	public String getUpdDate(){ return updDate; }
	public void setUpdDate(String updDate){ this.updDate = updDate; }

	public String getBiName() {
		return biName;
	}

	public void setBiName(String biName) {
		this.biName = biName;
	}

	@Override
	public String toString() {
		return "BusinessClientEntity[bcSeq="+bcSeq+",biSeq="+biSeq+",umSeq="+umSeq+",bcType="+bcType+",bcName="+bcName+",bcBizNum="+bcBizNum+",bcCeoName="+bcCeoName+",bcTel="+bcTel+",bcFax="+bcFax+",bcEmail="+bcEmail+",bcStartDate="+bcStartDate+",bcEndDate="+bcEndDate+",bcEtc="+bcEtc+",bcIsDel="+bcIsDel+",bcDelDate="+bcDelDate+",regDate="+regDate+",updDate="+updDate+"]";
	}
}