package com.plushih.entities;

import java.io.Serializable;

public class AigoLevelEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer levId;
	private String levKey;
	private String levName;
	private Integer levValue;
	private String useYn;
	private String regDate;
	private Integer regUmSeq;
	private String udtDate;
	private Integer udtUmSeq;

	public Integer getLevId(){ return levId; }
	public void setLevId(Integer levId){ this.levId = levId; }

	public String getLevKey(){ return levKey; }
	public void setLevKey(String levKey){ this.levKey = levKey; }

	public String getLevName(){ return levName; }
	public void setLevName(String levName){ this.levName = levName; }

	public Integer getLevValue(){ return levValue; }
	public void setLevValue(Integer levValue){ this.levValue = levValue; }

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
		return "AigoLevelEntity[levId="+levId+",levKey="+levKey+",levName="+levName+",levValue="+levValue+",useYn="+useYn+",regDate="+regDate+",regUmSeq="+regUmSeq+",udtDate="+udtDate+",udtUmSeq="+udtUmSeq+"]";
	}
}