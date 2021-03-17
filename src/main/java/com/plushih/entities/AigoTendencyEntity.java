package com.plushih.entities;

import java.io.Serializable;

public class AigoTendencyEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer tenId;
	private Integer subId;
	private Integer levId;
	private Integer acvId;
	private String tenName;
	private String tenKey;
	private String tenValue;
	private Integer tenOrder;
	private String tenText;
	private String useYn;
	private String regDate;
	private Integer regMemId;
	private String udtDate;
	private Integer udtMemId;

	public Integer getTenId(){ return tenId; }
	public void setTenId(Integer tenId){ this.tenId = tenId; }

	public Integer getSubId(){ return subId; }
	public void setSubId(Integer subId){ this.subId = subId; }

	public Integer getLevId(){ return levId; }
	public void setLevId(Integer levId){ this.levId = levId; }

	public Integer getAcvId(){ return acvId; }
	public void setAcvId(Integer acvId){ this.acvId = acvId; }

	public String getTenName(){ return tenName; }
	public void setTenName(String tenName){ this.tenName = tenName; }

	public String getTenKey(){ return tenKey; }
	public void setTenKey(String tenKey){ this.tenKey = tenKey; }

	public String getTenValue(){ return tenValue; }
	public void setTenValue(String tenValue){ this.tenValue = tenValue; }

	public Integer getTenOrder(){ return tenOrder; }
	public void setTenOrder(Integer tenOrder){ this.tenOrder = tenOrder; }

	public String getTenText(){ return tenText; }
	public void setTenText(String tenText){ this.tenText = tenText; }

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
		return "AigoTendencyEntity[tenId="+tenId+",subId="+subId+",levId="+levId+",acvId="+acvId+",tenName="+tenName+",tenKey="+tenKey+",tenValue="+tenValue+",tenOrder="+tenOrder+",tenText="+tenText+",useYn="+useYn+",regDate="+regDate+",regMemId="+regMemId+",udtDate="+udtDate+",udtMemId="+udtMemId+"]";
	}
}