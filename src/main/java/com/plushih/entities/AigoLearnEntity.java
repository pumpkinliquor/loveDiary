package com.plushih.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class AigoLearnEntity  extends PlusEntity implements Serializable{
	private static final long serialVersionUID = -2665960990460041703L;
	
	private Integer qstId;
	private Integer subId;
	private Integer levId;
	private Integer acvId;
	private Integer acaId;
	private Integer subAcaId;
	private Integer cmtrId;
	private String qstKey;
	private String qstName;
	private String qstType;
	private String qstTypeNum;
	private Integer qstPoint;
	private String qstValue;
	private Integer qstOrder;
	private String qstContType;
	private String qstContText;
	private String qstCont02Type;
	private String qstCont02Text;
	private String qstCont03Type;
	private String qstCont03Text;
	private String qstAnswer01;
	private String qstAnswer02;
	private String qstAnswer03;
	private String qstAnswer04;
	private String qstAnswer05;
	private String qstPreS01;
	private String qstPreE01;
	private String qstPreS02;
	private String qstPreE02;
	private String qstPreS03;
	private String qstPreE03;
	private String qstPreS04;
	private String qstPreE04;
	private String qstPreS05;
	private String qstPreE05;
	private String qstAddEtc01;
	private String qstAddEtc02;
	private String qstAddEtc03;
	private String qstAddEtc04;
	private String qstAddEtc05;
	private String qstAddEtc06;
	private String qstAddEtc07;
	private String useYn;
	private String regDate;
	private Integer regUmSeq;
	private String udtDate;
	private Integer udtUmSeq;
	private String subName;
	private String levName;
	private String cmtrKey;
	private String acvName;
	private String unitId;
	private String unitName;
	private String safPath;
	private String safFile;
	private String userPassYn;
	private String userAnsValue;
	private List<AigoConceptEntity> conceptList;
	private String currentTestType;
	private String currentTestTypeSub;
	private String currentQuestionNumber;
	private int currentQuestionViewNumber;
	private int totalQstCnt;
	private int userAnsCnt;
	private String memId;
	private String fileCategory;
	private int[] arrCategories;			// 출제할 문제분류 카테고리 배열 (다중 카테고리 조회용)
	private int[] arrExceptCategories;		// 제외할 문제분류 카테고리 배열 (다중 카테고리 조회용)
	private String rownum;
	
	public Integer getQstId() {
		return qstId;
	}
	public void setQstId(Integer qstId) {
		this.qstId = qstId;
	}
	public Integer getSubId() {
		return subId;
	}
	public void setSubId(Integer subId) {
		this.subId = subId;
	}
	public Integer getLevId() {
		return levId;
	}
	public void setLevId(Integer levId) {
		this.levId = levId;
	}
	public Integer getAcvId() {
		return acvId;
	}
	public void setAcvId(Integer acvId) {
		this.acvId = acvId;
	}
	public Integer getAcaId() {
		return acaId;
	}
	public void setAcaId(Integer acaId) {
		this.acaId = acaId;
	}
	public Integer getSubAcaId() {
		return subAcaId;
	}
	public void setSubAcaId(Integer subAcaId) {
		this.subAcaId = subAcaId;
	}
	public Integer getCmtrId() {
		return cmtrId;
	}
	public void setCmtrId(Integer cmtrId) {
		this.cmtrId = cmtrId;
	}
	public String getQstKey() {
		return qstKey;
	}
	public void setQstKey(String qstKey) {
		this.qstKey = qstKey;
	}
	public String getQstName() {
		return qstName;
	}
	public void setQstName(String qstName) {
		this.qstName = qstName;
	}
	public String getQstType() {
		return qstType;
	}
	public void setQstType(String qstType) {
		this.qstType = qstType;
	}
	public String getQstTypeNum() {
		return qstTypeNum;
	}
	public void setQstTypeNum(String qstTypeNum) {
		this.qstTypeNum = qstTypeNum;
	}
	public Integer getQstPoint() {
		return qstPoint;
	}
	public void setQstPoint(Integer qstPoint) {
		this.qstPoint = qstPoint;
	}
	public String getQstValue() {
		return qstValue;
	}
	public void setQstValue(String qstValue) {
		this.qstValue = qstValue;
	}
	public Integer getQstOrder() {
		return qstOrder;
	}
	public void setQstOrder(Integer qstOrder) {
		this.qstOrder = qstOrder;
	}
	public String getQstContType() {
		return qstContType;
	}
	public void setQstContType(String qstContType) {
		this.qstContType = qstContType;
	}
	public String getQstContText() {
		return qstContText;
	}
	public void setQstContText(String qstContText) {
		this.qstContText = qstContText;
	}
	public String getQstCont02Type() {
		return qstCont02Type;
	}
	public void setQstCont02Type(String qstCont02Type) {
		this.qstCont02Type = qstCont02Type;
	}
	public String getQstCont02Text() {
		return qstCont02Text;
	}
	public void setQstCont02Text(String qstCont02Text) {
		this.qstCont02Text = qstCont02Text;
	}
	public String getQstCont03Type() {
		return qstCont03Type;
	}
	public void setQstCont03Type(String qstCont03Type) {
		this.qstCont03Type = qstCont03Type;
	}
	public String getQstCont03Text() {
		return qstCont03Text;
	}
	public void setQstCont03Text(String qstCont03Text) {
		this.qstCont03Text = qstCont03Text;
	}
	public String getQstAnswer01() {
		return qstAnswer01;
	}
	public void setQstAnswer01(String qstAnswer01) {
		this.qstAnswer01 = qstAnswer01;
	}
	public String getQstAnswer02() {
		return qstAnswer02;
	}
	public void setQstAnswer02(String qstAnswer02) {
		this.qstAnswer02 = qstAnswer02;
	}
	public String getQstAnswer03() {
		return qstAnswer03;
	}
	public void setQstAnswer03(String qstAnswer03) {
		this.qstAnswer03 = qstAnswer03;
	}
	public String getQstAnswer04() {
		return qstAnswer04;
	}
	public void setQstAnswer04(String qstAnswer04) {
		this.qstAnswer04 = qstAnswer04;
	}
	public String getQstAnswer05() {
		return qstAnswer05;
	}
	public void setQstAnswer05(String qstAnswer05) {
		this.qstAnswer05 = qstAnswer05;
	}
	public String getQstPreS01() {
		return qstPreS01;
	}
	public void setQstPreS01(String qstPreS01) {
		this.qstPreS01 = qstPreS01;
	}
	public String getQstPreE01() {
		return qstPreE01;
	}
	public void setQstPreE01(String qstPreE01) {
		this.qstPreE01 = qstPreE01;
	}
	public String getQstPreS02() {
		return qstPreS02;
	}
	public void setQstPreS02(String qstPreS02) {
		this.qstPreS02 = qstPreS02;
	}
	public String getQstPreE02() {
		return qstPreE02;
	}
	public void setQstPreE02(String qstPreE02) {
		this.qstPreE02 = qstPreE02;
	}
	public String getQstPreS03() {
		return qstPreS03;
	}
	public void setQstPreS03(String qstPreS03) {
		this.qstPreS03 = qstPreS03;
	}
	public String getQstPreE03() {
		return qstPreE03;
	}
	public void setQstPreE03(String qstPreE03) {
		this.qstPreE03 = qstPreE03;
	}
	public String getQstPreS04() {
		return qstPreS04;
	}
	public void setQstPreS04(String qstPreS04) {
		this.qstPreS04 = qstPreS04;
	}
	public String getQstPreE04() {
		return qstPreE04;
	}
	public void setQstPreE04(String qstPreE04) {
		this.qstPreE04 = qstPreE04;
	}
	public String getQstPreS05() {
		return qstPreS05;
	}
	public void setQstPreS05(String qstPreS05) {
		this.qstPreS05 = qstPreS05;
	}
	public String getQstPreE05() {
		return qstPreE05;
	}
	public void setQstPreE05(String qstPreE05) {
		this.qstPreE05 = qstPreE05;
	}
	public String getQstAddEtc01() {
		return qstAddEtc01;
	}
	public void setQstAddEtc01(String qstAddEtc01) {
		this.qstAddEtc01 = qstAddEtc01;
	}
	public String getQstAddEtc02() {
		return qstAddEtc02;
	}
	public void setQstAddEtc02(String qstAddEtc02) {
		this.qstAddEtc02 = qstAddEtc02;
	}
	public String getQstAddEtc03() {
		return qstAddEtc03;
	}
	public void setQstAddEtc03(String qstAddEtc03) {
		this.qstAddEtc03 = qstAddEtc03;
	}
	public String getQstAddEtc04() {
		return qstAddEtc04;
	}
	public void setQstAddEtc04(String qstAddEtc04) {
		this.qstAddEtc04 = qstAddEtc04;
	}
	public String getQstAddEtc05() {
		return qstAddEtc05;
	}
	public void setQstAddEtc05(String qstAddEtc05) {
		this.qstAddEtc05 = qstAddEtc05;
	}
	public String getQstAddEtc06() {
		return qstAddEtc06;
	}
	public void setQstAddEtc06(String qstAddEtc06) {
		this.qstAddEtc06 = qstAddEtc06;
	}
	public String getQstAddEtc07() {
		return qstAddEtc07;
	}
	public void setQstAddEtc07(String qstAddEtc07) {
		this.qstAddEtc07 = qstAddEtc07;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Integer getRegUmSeq() {
		return regUmSeq;
	}
	public void setRegUmSeq(Integer regUmSeq) {
		this.regUmSeq = regUmSeq;
	}
	public String getUdtDate() {
		return udtDate;
	}
	public void setUdtDate(String udtDate) {
		this.udtDate = udtDate;
	}
	public Integer getUdtUmSeq() {
		return udtUmSeq;
	}
	public void setUdtUmSeq(Integer udtUmSeq) {
		this.udtUmSeq = udtUmSeq;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public String getLevName() {
		return levName;
	}
	public void setLevName(String levName) {
		this.levName = levName;
	}
	public String getCmtrKey() {
		return cmtrKey;
	}
	public void setCmtrKey(String cmtrKey) {
		this.cmtrKey = cmtrKey;
	}
	public String getAcvName() {
		return acvName;
	}
	public void setAcvName(String acvName) {
		this.acvName = acvName;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getSafPath() {
		return safPath;
	}
	public void setSafPath(String safPath) {
		this.safPath = safPath;
	}
	public String getSafFile() {
		return safFile;
	}
	public void setSafFile(String safFile) {
		this.safFile = safFile;
	}
	public String getUserPassYn() {
		return userPassYn;
	}
	public void setUserPassYn(String userPassYn) {
		this.userPassYn = userPassYn;
	}
	public String getUserAnsValue() {
		return userAnsValue;
	}
	public void setUserAnsValue(String userAnsValue) {
		this.userAnsValue = userAnsValue;
	}
	public List<AigoConceptEntity> getConceptList() {
		return conceptList;
	}
	public void setConceptList(List<AigoConceptEntity> conceptList) {
		this.conceptList = conceptList;
	}
	public String getCurrentTestType() {
		return currentTestType;
	}
	public void setCurrentTestType(String currentTestType) {
		this.currentTestType = currentTestType;
	}
	public String getCurrentQuestionNumber() {
		return currentQuestionNumber;
	}
	public void setCurrentQuestionNumber(String currentQuestionNumber) {
		this.currentQuestionNumber = currentQuestionNumber;
	}
	public int getTotalQstCnt() {
		return totalQstCnt;
	}
	public void setTotalQstCnt(int totalQstCnt) {
		this.totalQstCnt = totalQstCnt;
	}
	public int getUserAnsCnt() {
		return userAnsCnt;
	}
	public void setUserAnsCnt(int userAnsCnt) {
		this.userAnsCnt = userAnsCnt;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getFileCategory() {
		return fileCategory;
	}
	public void setFileCategory(String fileCategory) {
		this.fileCategory = fileCategory;
	}
	public int[] getArrCategories() {
		return arrCategories;
	}
	public void setArrCategories(int[] arrCategories) {
		this.arrCategories = arrCategories;
	}
	public int[] getArrExceptCategories() {
		return arrExceptCategories;
	}
	public void setArrExceptCategories(int[] arrExceptCategories) {
		this.arrExceptCategories = arrExceptCategories;
	}
	public String getCurrentTestTypeSub() {
		return currentTestTypeSub;
	}
	public void setCurrentTestTypeSub(String currentTestTypeSub) {
		this.currentTestTypeSub = currentTestTypeSub;
	}
	public int getCurrentQuestionViewNumber() {
		return currentQuestionViewNumber;
	}
	public void setCurrentQuestionViewNumber(int currentQuestionViewNumber) {
		this.currentQuestionViewNumber = currentQuestionViewNumber;
	}
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	@Override
	public String toString() {
		return "AigoLearnEntity [qstId=" + qstId + ", subId=" + subId + ", levId=" + levId + ", acvId=" + acvId
				+ ", acaId=" + acaId + ", subAcaId=" + subAcaId + ", cmtrId=" + cmtrId + ", qstKey=" + qstKey
				+ ", qstName=" + qstName + ", qstType=" + qstType + ", qstTypeNum=" + qstTypeNum + ", qstPoint="
				+ qstPoint + ", qstValue=" + qstValue + ", qstOrder=" + qstOrder + ", qstContType=" + qstContType
				+ ", qstContText=" + qstContText + ", qstCont02Type=" + qstCont02Type + ", qstCont02Text="
				+ qstCont02Text + ", qstCont03Type=" + qstCont03Type + ", qstCont03Text=" + qstCont03Text
				+ ", qstAnswer01=" + qstAnswer01 + ", qstAnswer02=" + qstAnswer02 + ", qstAnswer03=" + qstAnswer03
				+ ", qstAnswer04=" + qstAnswer04 + ", qstAnswer05=" + qstAnswer05 + ", qstPreS01=" + qstPreS01
				+ ", qstPreE01=" + qstPreE01 + ", qstPreS02=" + qstPreS02 + ", qstPreE02=" + qstPreE02 + ", qstPreS03="
				+ qstPreS03 + ", qstPreE03=" + qstPreE03 + ", qstPreS04=" + qstPreS04 + ", qstPreE04=" + qstPreE04
				+ ", qstPreS05=" + qstPreS05 + ", qstPreE05=" + qstPreE05 + ", qstAddEtc01=" + qstAddEtc01
				+ ", qstAddEtc02=" + qstAddEtc02 + ", qstAddEtc03=" + qstAddEtc03 + ", qstAddEtc04=" + qstAddEtc04
				+ ", qstAddEtc05=" + qstAddEtc05 + ", qstAddEtc06=" + qstAddEtc06 + ", qstAddEtc07=" + qstAddEtc07
				+ ", useYn=" + useYn + ", regDate=" + regDate + ", regUmSeq=" + regUmSeq + ", udtDate=" + udtDate
				+ ", udtUmSeq=" + udtUmSeq + ", subName=" + subName + ", levName=" + levName + ", cmtrKey=" + cmtrKey
				+ ", acvName=" + acvName + ", unitId=" + unitId + ", unitName=" + unitName + ", safPath=" + safPath
				+ ", safFile=" + safFile + ", userPassYn=" + userPassYn + ", userAnsValue=" + userAnsValue
				+ ", conceptList=" + conceptList + ", currentTestType=" + currentTestType + ", currentTestTypeSub="
				+ currentTestTypeSub + ", currentQuestionNumber=" + currentQuestionNumber
				+ ", currentQuestionViewNumber=" + currentQuestionViewNumber + ", totalQstCnt=" + totalQstCnt
				+ ", userAnsCnt=" + userAnsCnt + ", memId=" + memId + ", fileCategory=" + fileCategory
				+ ", arrCategories=" + Arrays.toString(arrCategories) + ", arrExceptCategories="
				+ Arrays.toString(arrExceptCategories) + ", rownum=" + rownum + "]";
	}
	
	
}