package com.plushih.entities;

import com.plushih.common.utils.StringUtils;
import com.plushih.entities.PlusEntity;
import java.io.Serializable;
import java.sql.Timestamp;

public class AigoUnitEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer unitId;
	private Integer subId;
	private String unitKey;
	private String unitName;
	private String unitValue;
	private String unitParent;
	private Integer unitOrder;
	private String useYn;
	private String regDate;
	private Integer regUmSeq;
	private String udtDate;
	private Integer udtUmSeq;

	public Integer getUnitId(){ return unitId; }
	public void setUnitId(Integer unitId){ this.unitId = unitId; }

	public Integer getSubId(){ return subId; }
	public void setSubId(Integer subId){ this.subId = subId; }

	public String getUnitKey(){ return unitKey; }
	public void setUnitKey(String unitKey){ this.unitKey = unitKey; }

	public String getUnitName(){ return unitName; }
	public void setUnitName(String unitName){ this.unitName = unitName; }

	public String getUnitValue(){ return unitValue; }
	public void setUnitValue(String unitValue){ this.unitValue = unitValue; }

	public String getUnitParent(){ return unitParent; }
	public void setUnitParent(String unitParent){ this.unitParent = unitParent; }

	public Integer getUnitOrder(){ return unitOrder; }
	public void setUnitOrder(Integer unitOrder){ this.unitOrder = unitOrder; }

	public String getUseYn(){ return useYn; }
	public void setUseYn(String useYn){ this.useYn = useYn; }

	public String getRegDate(){ return regDate; }
	public void setRegDate(String regDate){ this.regDate = regDate; }

	public Integer getRegUmSeq(){ return regUmSeq; }
	public void setRegUmSeq(Integer regUmSeq){ this.regUmSeq = regUmSeq; }

	public String getUdtDate(){ return udtDate; }
	public void setUdtDate(String udtDate){ this.udtDate = udtDate; }

	public Integer getUdtUmSeq(){ return udtUmSeq; }
	public void setUdtUmSeq(Integer udtUmSeq){ this.udtUmSeq = udtUmSeq; }

	@Override
	public String toString() {
		return "AigoUnitEntity[unitId="+unitId+",subId="+subId+",unitKey="+unitKey+",unitName="+unitName+",unitValue="+unitValue+",unitParent="+unitParent+",unitOrder="+unitOrder+",useYn="+useYn+",regDate="+regDate+",regUmSeq="+regUmSeq+",udtDate="+udtDate+",udtUmSeq="+udtUmSeq+"]";
	}
}