package com.plushih.entities;

import java.io.Serializable;

public class AddressInfoEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer aiSeq;
	private String aiType;
	private String aiAddress;
	private String aiAddressenglish;
	private String aiBcode;
	private String aiBname;
	private String aiBname1;
	private String aiBname2;
	private String aiBuildingcode;
	private String aiBuildingname;
	private String aiJibunaddress;
	private String aiJibunaddressenglish;
	private String aiPostcode;
	private String aiPostcode1;
	private String aiPostcode2;
	private String aiPostcodeseq;
	private String aiRoadaddress;
	private String aiRoadaddressenglish;
	private String aiRoadname;
	private String aiRoadnamecode;
	private String aiSido;
	private String aiSigungu;
	private String aiSigungucode;
	private String aiZonecode;
	private String aiAddressdetail;

	public Integer getAiSeq(){ return aiSeq; }
	public void setAiSeq(Integer aiSeq){ this.aiSeq = aiSeq; }

	public String getAiType(){ return aiType; }
	public void setAiType(String aiType){ this.aiType = aiType; }

	public String getAiAddress(){ return aiAddress; }
	public void setAiAddress(String aiAddress){ this.aiAddress = aiAddress; }

	public String getAiAddressenglish(){ return aiAddressenglish; }
	public void setAiAddressenglish(String aiAddressenglish){ this.aiAddressenglish = aiAddressenglish; }

	public String getAiBcode(){ return aiBcode; }
	public void setAiBcode(String aiBcode){ this.aiBcode = aiBcode; }

	public String getAiBname(){ return aiBname; }
	public void setAiBname(String aiBname){ this.aiBname = aiBname; }

	public String getAiBname1(){ return aiBname1; }
	public void setAiBname1(String aiBname1){ this.aiBname1 = aiBname1; }

	public String getAiBname2(){ return aiBname2; }
	public void setAiBname2(String aiBname2){ this.aiBname2 = aiBname2; }

	public String getAiBuildingcode(){ return aiBuildingcode; }
	public void setAiBuildingcode(String aiBuildingcode){ this.aiBuildingcode = aiBuildingcode; }

	public String getAiBuildingname(){ return aiBuildingname; }
	public void setAiBuildingname(String aiBuildingname){ this.aiBuildingname = aiBuildingname; }

	public String getAiJibunaddress(){ return aiJibunaddress; }
	public void setAiJibunaddress(String aiJibunaddress){ this.aiJibunaddress = aiJibunaddress; }

	public String getAiJibunaddressenglish(){ return aiJibunaddressenglish; }
	public void setAiJibunaddressenglish(String aiJibunaddressenglish){ this.aiJibunaddressenglish = aiJibunaddressenglish; }

	public String getAiPostcode(){ return aiPostcode; }
	public void setAiPostcode(String aiPostcode){ this.aiPostcode = aiPostcode; }

	public String getAiPostcode1(){ return aiPostcode1; }
	public void setAiPostcode1(String aiPostcode1){ this.aiPostcode1 = aiPostcode1; }

	public String getAiPostcode2(){ return aiPostcode2; }
	public void setAiPostcode2(String aiPostcode2){ this.aiPostcode2 = aiPostcode2; }

	public String getAiPostcodeseq(){ return aiPostcodeseq; }
	public void setAiPostcodeseq(String aiPostcodeseq){ this.aiPostcodeseq = aiPostcodeseq; }

	public String getAiRoadaddress(){ return aiRoadaddress; }
	public void setAiRoadaddress(String aiRoadaddress){ this.aiRoadaddress = aiRoadaddress; }

	public String getAiRoadaddressenglish(){ return aiRoadaddressenglish; }
	public void setAiRoadaddressenglish(String aiRoadaddressenglish){ this.aiRoadaddressenglish = aiRoadaddressenglish; }

	public String getAiRoadname(){ return aiRoadname; }
	public void setAiRoadname(String aiRoadname){ this.aiRoadname = aiRoadname; }

	public String getAiRoadnamecode(){ return aiRoadnamecode; }
	public void setAiRoadnamecode(String aiRoadnamecode){ this.aiRoadnamecode = aiRoadnamecode; }

	public String getAiSido(){ return aiSido; }
	public void setAiSido(String aiSido){ this.aiSido = aiSido; }

	public String getAiSigungu(){ return aiSigungu; }
	public void setAiSigungu(String aiSigungu){ this.aiSigungu = aiSigungu; }

	public String getAiSigungucode(){ return aiSigungucode; }
	public void setAiSigungucode(String aiSigungucode){ this.aiSigungucode = aiSigungucode; }

	public String getAiZonecode(){ return aiZonecode; }
	public void setAiZonecode(String aiZonecode){ this.aiZonecode = aiZonecode; }

	public String getAiAddressdetail(){ return aiAddressdetail; }
	public void setAiAddressdetail(String aiAddressdetail){ this.aiAddressdetail = aiAddressdetail; }

	@Override
	public String toString() {
		return "AddressInfoEntity[aiSeq="+aiSeq+",aiType="+aiType+",aiAddress="+aiAddress+",aiAddressenglish="+aiAddressenglish+",aiBcode="+aiBcode+",aiBname="+aiBname+",aiBname1="+aiBname1+",aiBname2="+aiBname2+",aiBuildingcode="+aiBuildingcode+",aiBuildingname="+aiBuildingname+",aiJibunaddress="+aiJibunaddress+",aiJibunaddressenglish="+aiJibunaddressenglish+",aiPostcode="+aiPostcode+",aiPostcode1="+aiPostcode1+",aiPostcode2="+aiPostcode2+",aiPostcodeseq="+aiPostcodeseq+",aiRoadaddress="+aiRoadaddress+",aiRoadaddressenglish="+aiRoadaddressenglish+",aiRoadname="+aiRoadname+",aiRoadnamecode="+aiRoadnamecode+",aiSido="+aiSido+",aiSigungu="+aiSigungu+",aiSigungucode="+aiSigungucode+",aiZonecode="+aiZonecode+",aiAddressdetail="+aiAddressdetail+"]";
	}
}