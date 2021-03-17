package com.plushih.entities;

import com.plushih.common.utils.StringUtils;
import com.plushih.entities.PlusEntity;
import java.io.Serializable;
import java.sql.Timestamp;

public class AigoCategoryEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer acaId;
	private Integer parentAcaId;
	private String parentAcaKey;
	private String acaKey;
	private String acaName;
	private String acaValue;
	private Integer acaOrder;
	private String regDate;
	private Integer regUmSeq;
	private String udtDate;
	private Integer udtUmSeq;

	public Integer getAcaId(){ return acaId; }
	public void setAcaId(Integer acaId){ this.acaId = acaId; }

	public Integer getParentAcaId(){ return parentAcaId; }
	public void setParentAcaId(Integer parentAcaId){ this.parentAcaId = parentAcaId; }

	public String getParentAcaKey(){ return parentAcaKey; }
	public void setParentAcaKey(String parentAcaKey){ this.parentAcaKey = parentAcaKey; }

	public String getAcaKey(){ return acaKey; }
	public void setAcaKey(String acaKey){ this.acaKey = acaKey; }

	public String getAcaName(){ return acaName; }
	public void setAcaName(String acaName){ this.acaName = acaName; }

	public String getAcaValue(){ return acaValue; }
	public void setAcaValue(String acaValue){ this.acaValue = acaValue; }

	public Integer getAcaOrder(){ return acaOrder; }
	public void setAcaOrder(Integer acaOrder){ this.acaOrder = acaOrder; }

	public String getUseYn(){ return useYn; }
	public void setUseYn(String useYn){ this.useYn = useYn; }


	public Integer getRegUmSeq(){ return regUmSeq; }
	public void setRegUmSeq(Integer regUmSeq){ this.regUmSeq = regUmSeq; }

	public String getUdtDate(){ return udtDate; }
	public void setUdtDate(String udtDate){ this.udtDate = udtDate; }

	public Integer getUdtUmSeq(){ return udtUmSeq; }
	public void setUdtUmSeq(Integer udtUmSeq){ this.udtUmSeq = udtUmSeq; }

	@Override
	public String toString() {
		return "AigoCategoryEntity[acaId="+acaId+",parentAcaId="+parentAcaId+",parentAcaKey="+parentAcaKey+",acaKey="+acaKey+",acaName="+acaName+",acaValue="+acaValue+",acaOrder="+acaOrder+",useYn="+useYn+",regDate="+regDate+",regUmSeq="+regUmSeq+",udtDate="+udtDate+",udtUmSeq="+udtUmSeq+"]";
	}
}