package com.plushih.entities;

import java.io.Serializable;

public class AigoSubjectEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer subId;
	private String subKey;
	private String subName;
	private Integer subOrder;
	private String useYn;
	private String regDate;
	private Integer regMemId;
	private String udtDate;
	private Integer udtMemId;

	public Integer getSubId(){ return subId; }
	public void setSubId(Integer subId){ this.subId = subId; }

	public String getSubKey(){ return subKey; }
	public void setSubKey(String subKey){ this.subKey = subKey; }

	public String getSubName(){ return subName; }
	public void setSubName(String subName){ this.subName = subName; }

	public Integer getSubOrder(){ return subOrder; }
	public void setSubOrder(Integer subOrder){ this.subOrder = subOrder; }

	public String getUseYn(){ return useYn; }
	public void setUseYn(String useYn){ this.useYn = useYn; }

	public String getRegDate(){ return regDate; }
	public void setRegDate(String regDate){ this.regDate = regDate; }

	public Integer getRegMemId(){ return regMemId; }
	public void setRegMemId(Integer regMemId){ this.regMemId = regMemId; }

	public String getUdtDate(){ return udtDate; }
	public void setUdtDate(String udtDate){ this.udtDate = udtDate; }

	public Integer getUdtMemId(){ return udtMemId; }
	public void setUdtMemId(Integer udtMemId){ this.udtMemId = udtMemId; }

	@Override
	public String toString() {
		return "AigoSubjectEntity[subId="+subId+",subKey="+subKey+",subName="+subName+",subOrder="+subOrder+",useYn="+useYn+",regDate="+regDate+",regMemId="+regMemId+",udtDate="+udtDate+",udtMemId="+udtMemId+"]";
	}
}