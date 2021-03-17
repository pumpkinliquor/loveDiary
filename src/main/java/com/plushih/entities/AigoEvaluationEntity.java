package com.plushih.entities;

import com.plushih.common.utils.StringUtils;
import com.plushih.entities.PlusEntity;
import java.io.Serializable;
import java.sql.Timestamp;

public class AigoEvaluationEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer evtId;
	private Integer levId;
	private String evtKey;
	private String evtText;
	private Integer evtOrder;
	private String useYn;
	private String regDate;
	private Integer regUmSeq;
	private String udtDate;
	private Integer udtUmSeq;

	public Integer getEvtId(){ return evtId; }
	public void setEvtId(Integer evtId){ this.evtId = evtId; }

	public Integer getLevId(){ return levId; }
	public void setLevId(Integer levId){ this.levId = levId; }

	public String getEvtKey(){ return evtKey; }
	public void setEvtKey(String evtKey){ this.evtKey = evtKey; }

	public String getEvtText(){ return evtText; }
	public void setEvtText(String evtText){ this.evtText = evtText; }

	public Integer getEvtOrder(){ return evtOrder; }
	public void setEvtOrder(Integer evtOrder){ this.evtOrder = evtOrder; }

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
		return "AigoEvaluationEntity[evtId="+evtId+",levId="+levId+",evtKey="+evtKey+",evtText="+evtText+",evtOrder="+evtOrder+",useYn="+useYn+",regDate="+regDate+",regUmSeq="+regUmSeq+",udtDate="+udtDate+",udtUmSeq="+udtUmSeq+"]";
	}
}