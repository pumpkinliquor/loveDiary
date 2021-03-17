package com.plushih.controllers.front;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plushih.common.ci.CoreController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plushih.common.constant.LoginSession;
import com.plushih.controllers.totaladmin.AdminBusinessController;


import java.beans.XMLEncoder;                     
import org.w3c.dom.*;
import org.xml.sax.*;
import java.util.*;
import java.io.*;
import java.net.*;
import javax.xml.parsers.*;
import javax.servlet.http.HttpServletResponse.*;
import java.text.*;



@Controller
@RequestMapping("front/ir")
public class FrontIrController extends CoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger( AdminBusinessController.class );


    /**
     * irOverview
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/irOverview","/irOverview/{lan}"})
    public String irOverview (HttpServletRequest request
    						, HttpServletResponse response
    						, @PathVariable Map<String, String> pathVariables
    						, ModelMap model
    						, Locale localeParam ) throws Exception {
    	String geturl = "http://asp1.krx.co.kr/servlet/krx.asp.XMLSise?code=000520";
    	String JongCd = geturl.substring(51, 57);
    	String gettime="";
    	String janggubun = "";
    	String DungRakrate_str = "";
    	
    	int timeconclude_length = 0;
    	int dailystock_length = 0;
    	int Askprice_length= 0;
    	int Hoga_length= 0;
    	
    	int CurJuka = 0;
    	int Debi = 0;
    	float StandardPrice = 0;
    	float DungRakrate = 0;
    	
    	String up = "▲";
    	String down = "▼";
    	String bohab = "─";
    	String line="";
    	String xml = "";
    	
    	String Stockinfo[]= new String [17];
    	String Timeconclude[][] = new String [10][7];
    	String Dailystock[][] = new String [10][9];
    	String Askprice[][] = new String [5][4];
    	String Hoga[]= new String [22];
    	
    	try{
    		URL url = new URL(geturl);
    		URLConnection conn = url.openConnection();
    		HttpURLConnection httpConnection = (HttpURLConnection) conn;
    		InputStream is = null;
    		InputStreamReader isr = null;
    		
    		is =  new URL(geturl).openStream();
    		isr = new InputStreamReader(is, "UTF-8");		
    		
    		BufferedReader rd = new BufferedReader(isr,400);
    		
    		StringBuffer strbuf = new StringBuffer();
    		
    		//xml line1 공백제거
    		while ((line = rd.readLine()) != null){			
    		  	strbuf.append(line);
    		}
    		
    		//System.out.println("주가정보");
    		//System.out.println(strbuf.toString()); //xml파싱확인
    		
    		DocumentBuilderFactory docFact = DocumentBuilderFactory.newInstance();
    		docFact.setNamespaceAware(true);
    		DocumentBuilder docBuild = docFact.newDocumentBuilder();

    		Document doc = docBuild.parse(new InputSource(new StringReader(strbuf.toString())));

    		/*주가정보*/
    		
    		NodeList stockInfo = doc.getElementsByTagName("stockInfo");
    		
    		NamedNodeMap stockinfo = stockInfo.item(0).getAttributes();
    		gettime = stockinfo.getNamedItem("myNowTime").getNodeValue();
    		janggubun = stockinfo.getNamedItem("myJangGubun").getNodeValue();
    		
    		NodeList TBL_StockInfo = doc.getElementsByTagName("TBL_StockInfo");
    		NamedNodeMap StockInfo = TBL_StockInfo.item(0).getAttributes();
    		
    		Stockinfo[0] = StockInfo.getNamedItem("JongName").getNodeValue();		//종목명 
    		Stockinfo[1] = StockInfo.getNamedItem("CurJuka").getNodeValue();		//현재가 
    		Stockinfo[2] = StockInfo.getNamedItem("DungRak").getNodeValue();		//전일대비코드
    		Stockinfo[3] = StockInfo.getNamedItem("Debi").getNodeValue();			//전일대비
    		Stockinfo[4] = StockInfo.getNamedItem("PrevJuka").getNodeValue();		//전일종가 
    		Stockinfo[5] = StockInfo.getNamedItem("Volume").getNodeValue();			//거래량
    		Stockinfo[6] = StockInfo.getNamedItem("Money").getNodeValue();			//거래대금  
    		Stockinfo[7] = StockInfo.getNamedItem("StartJuka").getNodeValue();		//시가 
    		Stockinfo[8] = StockInfo.getNamedItem("HighJuka").getNodeValue();		//고가
    		Stockinfo[9] = StockInfo.getNamedItem("LowJuka").getNodeValue();		//저가 		
    		Stockinfo[10] = StockInfo.getNamedItem("High52").getNodeValue();		//52주 최고 
    		Stockinfo[11] = StockInfo.getNamedItem("Low52").getNodeValue();			//52주 최저  
    		Stockinfo[12] = StockInfo.getNamedItem("UpJuka").getNodeValue();		//상한가 
    		Stockinfo[13] = StockInfo.getNamedItem("DownJuka").getNodeValue();		//하한가 
    		Stockinfo[14] = StockInfo.getNamedItem("Per").getNodeValue();			//PER            
    		Stockinfo[15] = StockInfo.getNamedItem("Amount").getNodeValue();		//상장주식수    
    		Stockinfo[16] = StockInfo.getNamedItem("FaceJuka").getNodeValue();		//액면가
    		
    		model.addAttribute("curJuka", Stockinfo[1]); 		//현재가
    		model.addAttribute("netChange", Stockinfo[3]);		//전일대비
    		model.addAttribute("stockCnt", Stockinfo[15]);		//상장주식수
    		model.addAttribute("parValue", Stockinfo[16]);		//액면가
    		
    		//상승, 보합, 하락 아이콘
    		String arrow="";
    		if(Stockinfo[2].equals("1")||Stockinfo[2].equals("2")){
    			model.addAttribute("arrow", up);
    			model.addAttribute("arrowStr", "up");
    		}else if(Stockinfo[2].equals("3")) {
    			model.addAttribute("arrow", bohab);
    			model.addAttribute("arrowStr", "bohab");
    		}else if(Stockinfo[2].equals("4")||Stockinfo[2].equals("5")){
    			model.addAttribute("arrow", down);
    			model.addAttribute("arrowStr", "down");
    		}
    		
    		
    		
    		// 등락율 계산
    		CurJuka = Integer.parseInt(Stockinfo[1].replaceAll(",", ""));
    		Debi = Integer.parseInt(Stockinfo[3].replaceAll(",", ""));
    		
    		/*등락구분코드*/
    		// 1 - 상한, 2 - 상승, 3 - 보합, 4 - 하한, 5 - 하락
    		
    		if(Stockinfo[2].equals("1")||Stockinfo[2].equals("2")||Stockinfo[2].equals("3")){
    			StandardPrice = CurJuka - Debi;
    		} else if(Stockinfo[2].equals("4")||Stockinfo[2].equals("5")){
    			StandardPrice = CurJuka + Debi;
    		}
    		
    		// 등락률 = (당일종가 - 기준가) / 기준가 * 100
    		// 기준가 = 당일종가(현재가) - 전일대비
    		DungRakrate = ((CurJuka - StandardPrice) / StandardPrice) * 100;
    		DungRakrate_str = String.format("%.2f", DungRakrate);
    		
    		model.addAttribute("DungRakrate_str", DungRakrate_str);		//등락률
    		
    		/*일자별시세*/
    		
    		NodeList TBL_Dailystock = doc.getElementsByTagName("DailyStock");
    		
     		dailystock_length = TBL_Dailystock.getLength();
    		
    		for(int j=0;j<dailystock_length;j++){
    			
    			NamedNodeMap DailyStock = TBL_Dailystock.item(j).getAttributes();
    			
    			Dailystock[j][0] = DailyStock.getNamedItem("day_Date").getNodeValue();		//일자
    			Dailystock[j][1] = DailyStock.getNamedItem("day_EndPrice").getNodeValue();	//종가
    			Dailystock[j][2] = DailyStock.getNamedItem("day_getDebi").getNodeValue();	//전일대비
    			Dailystock[j][3] = DailyStock.getNamedItem("day_Start").getNodeValue();		//시가
    			Dailystock[j][4] = DailyStock.getNamedItem("day_High").getNodeValue();		//고가
    			Dailystock[j][5] = DailyStock.getNamedItem("day_Low").getNodeValue();		//저가
    			Dailystock[j][6] = DailyStock.getNamedItem("day_Volume").getNodeValue();	//거래량
    			Dailystock[j][7] = DailyStock.getNamedItem("day_getAmount").getNodeValue();	//거래대금
    			Dailystock[j][8] = DailyStock.getNamedItem("day_Dungrak").getNodeValue();	//전일대비코드
    			
    		}
    		
    		/*시간대별 체결가*/
    		
    		NodeList TBL_TimeConclude = doc.getElementsByTagName("TBL_TimeConclude");
    		
    		timeconclude_length = TBL_TimeConclude.getLength()-1;
    		for(int i=0;i<timeconclude_length;i++){
    			
    			NamedNodeMap TimeConclude = TBL_TimeConclude.item(i+1).getAttributes();
    			
    			Timeconclude[i][0] = TimeConclude.getNamedItem("time").getNodeValue();		//시간
    			Timeconclude[i][1] = TimeConclude.getNamedItem("negoprice").getNodeValue();	//체결가
    			Timeconclude[i][2] = TimeConclude.getNamedItem("Debi").getNodeValue();		//전일대비
    			Timeconclude[i][3] = TimeConclude.getNamedItem("sellprice").getNodeValue();	//매도호가
    			Timeconclude[i][4] = TimeConclude.getNamedItem("buyprice").getNodeValue();	//매수호가
    			Timeconclude[i][5] = TimeConclude.getNamedItem("amount").getNodeValue();	//체결량
    			Timeconclude[i][6] = TimeConclude.getNamedItem("Dungrak").getNodeValue();	//전일대비코드
    		}
    		
    		/*증권사별거래*/
    		
    		NodeList TBL_AskPrice = doc.getElementsByTagName("AskPrice");
    		
    		Askprice_length = TBL_AskPrice.getLength();
    		for(int i=0;i<Askprice_length;i++){
    			
    			NamedNodeMap AskPrice = TBL_AskPrice.item(i).getAttributes();
    			
    			Askprice[i][0] = AskPrice.getNamedItem("member_memdoMem").getNodeValue();	//매도증권사
    			Askprice[i][1] = AskPrice.getNamedItem("member_memdoVol").getNodeValue();	//매도거래량
    			Askprice[i][2] = AskPrice.getNamedItem("member_memsoMem").getNodeValue();	//매수증권사
    			Askprice[i][3] = AskPrice.getNamedItem("member_mesuoVol").getNodeValue();	//매수거래량
    		}
    		
    		/*호가*/
    		
    		NodeList TBL_Hoga = doc.getElementsByTagName("TBL_Hoga");
    		
    		Hoga_length = TBL_Hoga.getLength();


    		NamedNodeMap hoga = TBL_Hoga.item(0).getAttributes();
    			
    		Hoga[0] = hoga.getNamedItem("mesuJan0").getNodeValue();		//매수잔량
    		Hoga[1] = hoga.getNamedItem("mesuHoka0").getNodeValue();	//매수호가
    		Hoga[2] = hoga.getNamedItem("mesuJan1").getNodeValue();		//매수잔량
    		Hoga[3] = hoga.getNamedItem("mesuHoka1").getNodeValue();	//매수호가
    		Hoga[4] = hoga.getNamedItem("mesuJan2").getNodeValue();		//매수잔량
    		Hoga[5] = hoga.getNamedItem("mesuHoka2").getNodeValue();	//매수호가
    		Hoga[6] = hoga.getNamedItem("mesuJan3").getNodeValue();		//매수잔량
    		Hoga[7] = hoga.getNamedItem("mesuHoka3").getNodeValue();	//매수호가
    		Hoga[8] = hoga.getNamedItem("mesuJan4").getNodeValue();		//매수잔량
    		Hoga[9] = hoga.getNamedItem("mesuHoka4").getNodeValue();	//매수호가
    		Hoga[10] = hoga.getNamedItem("medoHoka0").getNodeValue();	//매도잔량
    		Hoga[11] = hoga.getNamedItem("medoJan0").getNodeValue();	//매도호가
    		Hoga[12] = hoga.getNamedItem("medoHoka1").getNodeValue();	//매도잔량
    		Hoga[13] = hoga.getNamedItem("medoJan1").getNodeValue();	//매도호가
    		Hoga[14] = hoga.getNamedItem("medoHoka2").getNodeValue();	//매도잔량
    		Hoga[15] = hoga.getNamedItem("medoJan2").getNodeValue();	//매도호가
    		Hoga[16] = hoga.getNamedItem("medoHoka3").getNodeValue();	//매도잔량
    		Hoga[17] = hoga.getNamedItem("medoJan3").getNodeValue();	//매도호가
    		Hoga[18] = hoga.getNamedItem("medoHoka4").getNodeValue();	//매도잔량
    		Hoga[19] = hoga.getNamedItem("medoJan4").getNodeValue();	//매도호가	
    		
    		DecimalFormat formatter = new DecimalFormat("###,###,###");
    				
    		Hoga[20] = formatter.format(
    				   Integer.parseInt(Hoga[0].replace(",", ""))+
    				   Integer.parseInt(Hoga[2].replace(",", ""))+
    				   Integer.parseInt(Hoga[4].replace(",", ""))+
    				   Integer.parseInt(Hoga[6].replace(",", ""))+
    				   Integer.parseInt(Hoga[8].replace(",", "")));
    		Hoga[21] = formatter.format(
    				   Integer.parseInt(Hoga[11].replace(",", ""))+
    				   Integer.parseInt(Hoga[13].replace(",", ""))+
    				   Integer.parseInt(Hoga[15].replace(",", ""))+
    				   Integer.parseInt(Hoga[17].replace(",", ""))+
    				   Integer.parseInt(Hoga[19].replace(",", "")));		
    	
    	} catch(Exception e){
    		
    	}
    	
    	
        //path 설정
		String path = "ir/irOverview";
    	path = pathToLangFront(path,pathVariables,model);
      	return path;
    	
    	
    }
    
    /**
     * financialInfo
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/financialInfo","/financialInfo/{lan}"})
    public String financialInfo (HttpServletRequest request
    							, HttpServletResponse response
    							, @PathVariable Map<String, String> pathVariables
    							, ModelMap model
    							, Locale localeParam ) throws Exception {
    	
    	//path 설정
		String path = "ir/financialInfo";
    	path = pathToLangFront(path,pathVariables,model);
      	return path;
    }
    
    /**
     * irInfo
     * @param model
     * @param localeParam
     * @return
     */
    @RequestMapping(value = { "/irInfo","/irInfo/{lan}"})
    public String irInfo (HttpServletRequest request
    					, HttpServletResponse response
    					, @PathVariable Map<String, String> pathVariables
    					, ModelMap model
    					, Locale localeParam ) throws Exception {
    	
    	//path 설정
		String path = "ir/irInfo";
    	path = pathToLangFront(path,pathVariables,model);
      	return path;
    	
    }

    
    @RequestMapping(value = { "/stockInfo"})
    public String stockInfo (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
    	return "/front/empty/ir/stockInfo";
    }
    @RequestMapping(value = { "/disInfo"})
    public String disInfo (HttpServletRequest request, HttpServletResponse response, ModelMap model, Locale localeParam ) throws Exception {
    	return "/front/empty/ir/disInfo";
    }

}