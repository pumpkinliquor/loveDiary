package com.plushih.entities;

import java.io.Serializable;

public class CodeMasterEntity extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;
	private Integer cmSeq;
	private String cmGubun;
	private String cmCode;
	private String cmName;
	private Integer cmSort;
	private String cmIsUse;
	private String cmEtc;
	private String cmIsSystem;

	public Integer getCmSeq(){ return cmSeq; }
	public void setCmSeq(Integer cmSeq){ this.cmSeq = cmSeq; }

	public String getCmGubun(){ return cmGubun; }
	public void setCmGubun(String cmGubun){ this.cmGubun = cmGubun; }

	public String getCmCode(){ return cmCode; }
	public void setCmCode(String cmCode){ this.cmCode = cmCode; }

	public String getCmName(){ return cmName; }
	public void setCmName(String cmName){ this.cmName = cmName; }

	public Integer getCmSort(){ return cmSort; }
	public void setCmSort(Integer cmSort){ this.cmSort = cmSort; }

	public String getCmIsUse(){ return cmIsUse; }
	public void setCmIsUse(String cmIsUse){ this.cmIsUse = cmIsUse; }

	public String getCmEtc(){ return cmEtc; }
	public void setCmEtc(String cmEtc){ this.cmEtc = cmEtc; }

	public String getCmIsSystem(){ return cmIsSystem; }
	public void setCmIsSystem(String cmIsSystem){ this.cmIsSystem = cmIsSystem; }

	@Override
	public String toString() {
		return "CodeMasterEntity[cmSeq="+cmSeq+",cmGubun="+cmGubun+",cmCode="+cmCode+",cmName="+cmName+",cmSort="+cmSort+",cmIsUse="+cmIsUse+",cmEtc="+cmEtc+",cmIsSystem="+cmIsSystem+"]";
	}

}