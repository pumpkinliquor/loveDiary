package com.plushih.entities;

import com.plushih.common.utils.StringUtils;
import com.plushih.entities.PlusEntity;
import java.io.Serializable;
import java.sql.Timestamp;

public class AigoPromotionEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer prmId;
	private String prmStartDate;
	private String prmEndDate;
	private String prmName;
	private String prmUrl;
	private String prmTarget;
	private String prmText;
	private String prmDate;
	private Integer prmHeight;
	private Integer prmOrder;
	private String userActive;
	private String useYn;
	private String prmDatetime;
	private String prmIp;
	private Integer memId;
	private String regSysdate;
	private String udtSysdate;

	public Integer getPrmId(){ return prmId; }
	public void setPrmId(Integer prmId){ this.prmId = prmId; }

	public String getPrmStartDate(){ return prmStartDate; }
	public void setPrmStartDate(String prmStartDate){ this.prmStartDate = prmStartDate; }

	public String getPrmEndDate(){ return prmEndDate; }
	public void setPrmEndDate(String prmEndDate){ this.prmEndDate = prmEndDate; }

	public String getPrmName(){ return prmName; }
	public void setPrmName(String prmName){ this.prmName = prmName; }

	public String getPrmUrl(){ return prmUrl; }
	public void setPrmUrl(String prmUrl){ this.prmUrl = prmUrl; }

	public String getPrmTarget(){ return prmTarget; }
	public void setPrmTarget(String prmTarget){ this.prmTarget = prmTarget; }

	public String getPrmText(){ return prmText; }
	public void setPrmText(String prmText){ this.prmText = prmText; }

	public String getPrmDate(){ return prmDate; }
	public void setPrmDate(String prmDate){ this.prmDate = prmDate; }

	public Integer getPrmHeight(){ return prmHeight; }
	public void setPrmHeight(Integer prmHeight){ this.prmHeight = prmHeight; }

	public Integer getPrmOrder(){ return prmOrder; }
	public void setPrmOrder(Integer prmOrder){ this.prmOrder = prmOrder; }

	public String getUserActive() {
		return userActive;
	}

	public void setUserActive(String userActive) {
		this.userActive = userActive;
	}

	public String getUseYn(){ return useYn; }
	public void setUseYn(String useYn){ this.useYn = useYn; }

	public String getPrmDatetime(){ return prmDatetime; }
	public void setPrmDatetime(String prmDatetime){ this.prmDatetime = prmDatetime; }

	public String getPrmIp(){ return prmIp; }
	public void setPrmIp(String prmIp){ this.prmIp = prmIp; }

	public Integer getMemId(){ return memId; }
	public void setMemId(Integer memId){ this.memId = memId; }

	public String getRegSysdate(){ return regSysdate; }
	public void setRegSysdate(String regSysdate){ this.regSysdate = regSysdate; }

	public String getUdtSysdate(){ return udtSysdate; }
	public void setUdtSysdate(String udtSysdate){ this.udtSysdate = udtSysdate; }

	@Override
	public String toString() {
		return "AigoPromotionEntity[prmId="+prmId+",prmStartDate="+prmStartDate+",prmEndDate="+prmEndDate+",prmName="+prmName+",prmUrl="+prmUrl+",prmTarget="+prmTarget+",prmText="+prmText+",prmDate="+prmDate+",prmHeight="+prmHeight+",prmOrder="+prmOrder+",useYn="+useYn+",prmDatetime="+prmDatetime+",prmIp="+prmIp+",memId="+memId+",regSysdate="+regSysdate+",udtSysdate="+udtSysdate+"]";
	}
}