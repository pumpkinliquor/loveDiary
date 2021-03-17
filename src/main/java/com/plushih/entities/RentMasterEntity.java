package com.plushih.entities;

import java.io.Serializable;

public class RentMasterEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer rmSeq;
	private Integer biSeq;
	private Integer abSeq;
	private String abType;
	private Integer adSeq;
	private Integer afSeq;
	private Integer arSeq;
	private String rmType;
	private String rmCode;
	private String rmArea01;
	private String rmArea02;
	private String rmArea04;
	private String rmDepositType;
	private String rmDepositPrice;
	private String rmMonthPrice;
	private String rmCarePrice;
	private String rmPayType;
	private String rmPayDate;
	private String rmArea03;
	private String rmCheckinDate;
	private String rmCheckoutDate;
	private Integer olUseTv;
	private Integer olComlineCnt;
	private String olManageNum;
	private String delDate;
	private String regDate;

	public Integer getRmSeq(){ return rmSeq; }
	public void setRmSeq(Integer rmSeq){ this.rmSeq = rmSeq; }

	public Integer getBiSeq(){ return biSeq; }
	public void setBiSeq(Integer biSeq){ this.biSeq = biSeq; }

	public Integer getAbSeq(){ return abSeq; }
	public void setAbSeq(Integer abSeq){ this.abSeq = abSeq; }

	public String getAbType(){ return abType; }
	public void setAbType(String abType){ this.abType = abType; }

	public Integer getAdSeq(){ return adSeq; }
	public void setAdSeq(Integer adSeq){ this.adSeq = adSeq; }

	public Integer getAfSeq(){ return afSeq; }
	public void setAfSeq(Integer afSeq){ this.afSeq = afSeq; }

	public Integer getArSeq(){ return arSeq; }
	public void setArSeq(Integer arSeq){ this.arSeq = arSeq; }

	public String getRmType(){ return rmType; }
	public void setRmType(String rmType){ this.rmType = rmType; }

	public String getRmCode(){ return rmCode; }
	public void setRmCode(String rmCode){ this.rmCode = rmCode; }

	public String getRmArea01(){ return rmArea01; }
	public void setRmArea01(String rmArea01){ this.rmArea01 = rmArea01; }

	public String getRmArea02(){ return rmArea02; }
	public void setRmArea02(String rmArea02){ this.rmArea02 = rmArea02; }

	public String getRmArea04(){ return rmArea04; }
	public void setRmArea04(String rmArea04){ this.rmArea04 = rmArea04; }

	public String getRmDepositType(){ return rmDepositType; }
	public void setRmDepositType(String rmDepositType){ this.rmDepositType = rmDepositType; }

	public String getRmDepositPrice(){ return rmDepositPrice; }
	public void setRmDepositPrice(String rmDepositPrice){ this.rmDepositPrice = rmDepositPrice; }

	public String getRmMonthPrice(){ return rmMonthPrice; }
	public void setRmMonthPrice(String rmMonthPrice){ this.rmMonthPrice = rmMonthPrice; }

	public String getRmCarePrice(){ return rmCarePrice; }
	public void setRmCarePrice(String rmCarePrice){ this.rmCarePrice = rmCarePrice; }

	public String getRmPayType(){ return rmPayType; }
	public void setRmPayType(String rmPayType){ this.rmPayType = rmPayType; }

	public String getRmPayDate(){ return rmPayDate; }
	public void setRmPayDate(String rmPayDate){ this.rmPayDate = rmPayDate; }

	public String getRmArea03(){ return rmArea03; }
	public void setRmArea03(String rmArea03){ this.rmArea03 = rmArea03; }

	public String getRmCheckinDate(){ return rmCheckinDate; }
	public void setRmCheckinDate(String rmCheckinDate){ this.rmCheckinDate = rmCheckinDate; }

	public String getRmCheckoutDate(){ return rmCheckoutDate; }
	public void setRmCheckoutDate(String rmCheckoutDate){ this.rmCheckoutDate = rmCheckoutDate; }

	public Integer getOlUseTv(){ return olUseTv; }
	public void setOlUseTv(Integer olUseTv){ this.olUseTv = olUseTv; }

	public Integer getOlComlineCnt(){ return olComlineCnt; }
	public void setOlComlineCnt(Integer olComlineCnt){ this.olComlineCnt = olComlineCnt; }

	public String getOlManageNum(){ return olManageNum; }
	public void setOlManageNum(String olManageNum){ this.olManageNum = olManageNum; }

	public String getDelDate(){ return delDate; }
	public void setDelDate(String delDate){ this.delDate = delDate; }

	public String getRegDate(){ return regDate; }
	public void setRegDate(String regDate){ this.regDate = regDate; }

	@Override
	public String toString() {
		return "RentMasterEntity[rmSeq="+rmSeq+",biSeq="+biSeq+",abSeq="+abSeq+",abType="+abType+",adSeq="+adSeq+",afSeq="+afSeq+",arSeq="+arSeq+",rmType="+rmType+",rmCode="+rmCode+",rmArea01="+rmArea01+",rmArea02="+rmArea02+",rmArea04="+rmArea04+",rmDepositType="+rmDepositType+",rmDepositPrice="+rmDepositPrice+",rmMonthPrice="+rmMonthPrice+",rmCarePrice="+rmCarePrice+",rmPayType="+rmPayType+",rmPayDate="+rmPayDate+",rmArea03="+rmArea03+",rmCheckinDate="+rmCheckinDate+",rmCheckoutDate="+rmCheckoutDate+",olUseTv="+olUseTv+",olComlineCnt="+olComlineCnt+",olManageNum="+olManageNum+",delDate="+delDate+",regDate="+regDate+"]";
	}
}