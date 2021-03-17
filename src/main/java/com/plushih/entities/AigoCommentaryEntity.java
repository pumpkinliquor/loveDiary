package com.plushih.entities;

import java.io.Serializable;

public class AigoCommentaryEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer cmtrId;
	private Integer qstId;
	private String cmtrKey;
	private String cmtrName;
	private String cmtrType;
	private String cmtrValue;
	private Integer cmtrOrder;
	private String cmtrText;
	private String useYn;
	private String regDate;
	private Integer regUmSeq;
	private String udtDate;
	private Integer udtUmSeq;
	private Integer qstKey;
	private String regUmName;
	private String regUmId;

	public Integer getCmtrId(){ return cmtrId; }
	public void setCmtrId(Integer cmtrId){ this.cmtrId = cmtrId; }

	public Integer getQstId(){ return qstId; }
	public void setQstId(Integer qstId){ this.qstId = qstId; }

	public String getCmtrKey(){ return cmtrKey; }
	public void setCmtrKey(String cmtrKey){ this.cmtrKey = cmtrKey; }

	public String getCmtrName(){ return cmtrName; }
	public void setCmtrName(String cmtrName){ this.cmtrName = cmtrName; }

	public String getCmtrType(){ return cmtrType; }
	public void setCmtrType(String cmtrType){ this.cmtrType = cmtrType; }

	public String getCmtrValue(){ return cmtrValue; }
	public void setCmtrValue(String cmtrValue){ this.cmtrValue = cmtrValue; }

	public Integer getCmtrOrder(){ return cmtrOrder; }
	public void setCmtrOrder(Integer cmtrOrder){ this.cmtrOrder = cmtrOrder; }

	public String getCmtrText(){ return cmtrText; }
	public void setCmtrText(String cmtrText){ this.cmtrText = cmtrText; }

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

	public Integer getQstKey() { return qstKey; }
	public void setQstKey(Integer qstKey) { this.qstKey = qstKey; }
	
	public String getRegUmName() { return regUmName; }
	public void setRegUmName(String regUmName) { this.regUmName = regUmName; }
	
	public String getRegUmId() { return regUmId; }
	public void setRegUmId(String regUmId) { this.regUmId = regUmId; }
	
	@Override
	public String toString() {
		return "AigoCommentaryEntity [cmtrId=" + cmtrId + ", qstId=" + qstId + ", cmtrKey=" + cmtrKey + ", cmtrName="
				+ cmtrName + ", cmtrType=" + cmtrType + ", cmtrValue=" + cmtrValue + ", cmtrOrder=" + cmtrOrder
				+ ", cmtrText=" + cmtrText + ", useYn=" + useYn + ", regDate=" + regDate + ", regUmSeq=" + regUmSeq
				+ ", udtDate=" + udtDate + ", udtUmSeq=" + udtUmSeq + ", qstKey=" + qstKey + ", regUmName=" + regUmName
				+ ", regUmId=" + regUmId + "]";
	}
}