package com.plushih.entities;

import com.plushih.common.utils.StringUtils;
import com.plushih.entities.PlusEntity;
import java.io.Serializable;
import java.sql.Timestamp;

public class AigoNotionEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer notId;
	private Integer subId;
	private Integer acvId;
	private Integer cptId;
	private String notKey;
	private String notName;
	private String notType;
	private Integer notOrder;
	private String notText;
	private String useYn;
	private String regDate;
	private Integer regUmSeq;
	private String udtDate;
	private Integer udtUmSeq;

	public Integer getNotId(){ return notId; }
	public void setNotId(Integer notId){ this.notId = notId; }

	public Integer getSubId(){ return subId; }
	public void setSubId(Integer subId){ this.subId = subId; }

	public Integer getAcvId(){ return acvId; }
	public void setAcvId(Integer acvId){ this.acvId = acvId; }

	public Integer getCptId(){ return cptId; }
	public void setCptId(Integer cptId){ this.cptId = cptId; }

	public String getNotKey(){ return notKey; }
	public void setNotKey(String notKey){ this.notKey = notKey; }

	public String getNotName(){ return notName; }
	public void setNotName(String notName){ this.notName = notName; }

	public String getNotType(){ return notType; }
	public void setNotType(String notType){ this.notType = notType; }

	public Integer getNotOrder(){ return notOrder; }
	public void setNotOrder(Integer notOrder){ this.notOrder = notOrder; }

	public String getNotText(){ return notText; }
	public void setNotText(String notText){ this.notText = notText; }

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
		return "AigoNotionEntity[notId="+notId+",subId="+subId+",acvId="+acvId+",cptId="+cptId+",notKey="+notKey+",notName="+notName+",notType="+notType+",notOrder="+notOrder+",notText="+notText+",useYn="+useYn+",regDate="+regDate+",regUmSeq="+regUmSeq+",udtDate="+udtDate+",udtUmSeq="+udtUmSeq+"]";
	}
}