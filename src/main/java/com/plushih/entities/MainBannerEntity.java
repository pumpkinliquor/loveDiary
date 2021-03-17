package com.plushih.entities;

import java.io.Serializable;

public class MainBannerEntity extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;



	private Integer bnSeq;
	private Integer lgSeq;
	private String bnUse;
	private Integer biSeq;
	private String bnTitle;
	private Integer bnView;
	private String bnDate;
	private String regDate;
	private String regUserId;
	private String uptDate;
	private String udtpUserId;

	public Integer getBnSeq(){ return bnSeq; }
	public void setBnSeq(Integer bnSeq){ this.bnSeq = bnSeq; }

	public Integer getLgSeq(){ return lgSeq; }
	public void setLgSeq(Integer lgSeq){ this.lgSeq = lgSeq; }

	public String getBnUse(){ return bnUse; }
	public void setBnUse(String bnUse){ this.bnUse = bnUse; }

	public Integer getBiSeq(){ return biSeq; }
	public void setBiSeq(Integer biSeq){ this.biSeq = biSeq; }

	public String getBnTitle(){ return bnTitle; }
	public void setBnTitle(String bnTitle){ this.bnTitle = bnTitle; }

	public Integer getBnView(){ return bnView; }
	public void setBnView(Integer bnView){ this.bnView = bnView; }

	public String getBnDate(){ return bnDate; }
	public void setBnDate(String bnDate){ this.bnDate = bnDate; }

	public String getRegDate(){ return regDate; }
	public void setRegDate(String regDate){ this.regDate = regDate; }

	public String getRegUserId(){ return regUserId; }
	public void setRegUserId(String regUserId){ this.regUserId = regUserId; }

	public String getUptDate(){ return uptDate; }
	public void setUptDate(String uptDate){ this.uptDate = uptDate; }

	public String getUdtpUserId(){ return udtpUserId; }
	public void setUdtpUserId(String udtpUserId){ this.udtpUserId = udtpUserId; }

	@Override
	public String toString() {
		return "MainBannerEntity[bnSeq="+bnSeq+",lgSeq="+lgSeq+",bnUse="+bnUse+",biSeq="+biSeq+",bnTitle="+bnTitle+",bnView="+bnView+",bnDate="+bnDate+",regDate="+regDate+",regUserId="+regUserId+",uptDate="+uptDate+",udtpUserId="+udtpUserId+"]";
	}
}