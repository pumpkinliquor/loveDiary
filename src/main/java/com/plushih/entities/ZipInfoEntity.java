package com.plushih.entities;

import java.io.Serializable;

public class ZipInfoEntity extends PlusEntity implements Serializable{
	private static final long serialVersionUID = 770506395266028181L;

	private Integer tbSeq;
	private String tbType;
	private String address;
	private String addressenglish;
	private String bcode;
	private String bname;
	private String bname1;
	private String bname2;
	private String buildingcode;
	private String buildingname;
	private String jibunaddress;
	private String jibunaddressenglish;
	private String postcode;
	private String postcode1;
	private String postcode2;
	private String postcodeseq;
	private String roadaddress;
	private String roadaddressenglish;
	private String roadname;
	private String roadnamecode;
	private String sido;
	private String sigungu;
	private String sigungucode;
	private String zonecode;
	private String addressdetail;

	public Integer getTbSeq(){ return tbSeq; }
	public void setTbSeq(Integer tbSeq){ this.tbSeq = tbSeq; }

	public String getTbType(){ return tbType; }
	public void setTbType(String tbType){ this.tbType = tbType; }

	public String getAddress(){ return address; }
	public void setAddress(String address){ this.address = address; }

	public String getAddressenglish(){ return addressenglish; }
	public void setAddressenglish(String addressenglish){ this.addressenglish = addressenglish; }

	public String getBcode(){ return bcode; }
	public void setBcode(String bcode){ this.bcode = bcode; }

	public String getBname(){ return bname; }
	public void setBname(String bname){ this.bname = bname; }

	public String getBname1(){ return bname1; }
	public void setBname1(String bname1){ this.bname1 = bname1; }

	public String getBname2(){ return bname2; }
	public void setBname2(String bname2){ this.bname2 = bname2; }

	public String getBuildingcode(){ return buildingcode; }
	public void setBuildingcode(String buildingcode){ this.buildingcode = buildingcode; }

	public String getBuildingname(){ return buildingname; }
	public void setBuildingname(String buildingname){ this.buildingname = buildingname; }

	public String getJibunaddress(){ return jibunaddress; }
	public void setJibunaddress(String jibunaddress){ this.jibunaddress = jibunaddress; }

	public String getJibunaddressenglish(){ return jibunaddressenglish; }
	public void setJibunaddressenglish(String jibunaddressenglish){ this.jibunaddressenglish = jibunaddressenglish; }

	public String getPostcode(){ return postcode; }
	public void setPostcode(String postcode){ this.postcode = postcode; }

	public String getPostcode1(){ return postcode1; }
	public void setPostcode1(String postcode1){ this.postcode1 = postcode1; }

	public String getPostcode2(){ return postcode2; }
	public void setPostcode2(String postcode2){ this.postcode2 = postcode2; }

	public String getPostcodeseq(){ return postcodeseq; }
	public void setPostcodeseq(String postcodeseq){ this.postcodeseq = postcodeseq; }

	public String getRoadaddress(){ return roadaddress; }
	public void setRoadaddress(String roadaddress){ this.roadaddress = roadaddress; }

	public String getRoadaddressenglish(){ return roadaddressenglish; }
	public void setRoadaddressenglish(String roadaddressenglish){ this.roadaddressenglish = roadaddressenglish; }

	public String getRoadname(){ return roadname; }
	public void setRoadname(String roadname){ this.roadname = roadname; }

	public String getRoadnamecode(){ return roadnamecode; }
	public void setRoadnamecode(String roadnamecode){ this.roadnamecode = roadnamecode; }

	public String getSido(){ return sido; }
	public void setSido(String sido){ this.sido = sido; }

	public String getSigungu(){ return sigungu; }
	public void setSigungu(String sigungu){ this.sigungu = sigungu; }

	public String getSigungucode(){ return sigungucode; }
	public void setSigungucode(String sigungucode){ this.sigungucode = sigungucode; }

	public String getZonecode(){ return zonecode; }
	public void setZonecode(String zonecode){ this.zonecode = zonecode; }

	public String getAddressdetail(){ return addressdetail; }
	public void setAddressdetail(String addressdetail){ this.addressdetail = addressdetail; }

	@Override
	public String toString() {
		return "ZipInfoEntity[tbSeq="+tbSeq+",tbType="+tbType+",address="+address+",addressenglish="+addressenglish+",bcode="+bcode+",bname="+bname+",bname1="+bname1+",bname2="+bname2+",buildingcode="+buildingcode+",buildingname="+buildingname+",jibunaddress="+jibunaddress+",jibunaddressenglish="+jibunaddressenglish+",postcode="+postcode+",postcode1="+postcode1+",postcode2="+postcode2+",postcodeseq="+postcodeseq+",roadaddress="+roadaddress+",roadaddressenglish="+roadaddressenglish+",roadname="+roadname+",roadnamecode="+roadnamecode+",sido="+sido+",sigungu="+sigungu+",sigungucode="+sigungucode+",zonecode="+zonecode+",addressdetail="+addressdetail+"]";
	}
}