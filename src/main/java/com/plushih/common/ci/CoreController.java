package com.plushih.common.ci;

import com.plushih.common.constant.LoginSession;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * hsk3807
 */
public class CoreController {
    public static HashMap<String,Long> runMap = new HashMap<String,Long>();
	public void logStart(String s){
		runMap.put(s,System.currentTimeMillis());
	}
	public static void logEnd(String s){
		long end = System.currentTimeMillis();
		Debug.log( "컨트롤러 실행 시간 : " + ( end - runMap.get(s) )/1000.0 +"초" ,"##### start = "+s+" #####","##### end  = "+s+" ##### end Debug");

	}

	/**
	 * 펑션리턴
	 * @return String
	 */
	public String getFunction(){
		StackTraceElement[] traces = Thread.currentThread().getStackTrace();
		String name = "";
		for(StackTraceElement stackTraceElement : traces){

			if(stackTraceElement.toString().indexOf("com.plushih")>-1){
//				String ctrName = stackTraceElement.getFileName().substring(0,stackTraceElement.getFileName().lastIndexOf("."));
//
//				Debug.log("ctrName========="+ctrName);
				name = stackTraceElement.toString();//.substring(25);//.substring(stackTraceElement.toString().indexOf(ctrName));
				//name = name.substring(name.indexOf(stackTraceElement.getMethodName()));
			}
		}
		return name;
	}

	public String pathToLangFront(String path, Map<String, String> pathVariables, ModelMap model){
		model.addAttribute("lang",pathVariables);
		model.addAttribute("path",path);



//		String lan="KR";
//    	if(pathVariables.containsKey("lan")) {
//    		lan = pathVariables.get("lan");
//    	}
//
//    	model.addAttribute("bg", path.split("/")[0]);
//    	model.addAttribute("bgGb", path.split("/")[1]);
//    	model.addAttribute("lan",lan);
    	path = String.format("/front/%s",path);
		return path;
	}
	public String pathToLangFront(String path, Map<String, String> pathVariables, ModelMap model, HttpServletRequest request){
		model.addAttribute("lang",pathVariables);
		model.addAttribute("path",path);

		HttpSession session = request.getSession();

        String tempId = LoginSession.getTempId(session);
        String tempClass = LoginSession.getTempClass(session);
        String tempState = LoginSession.getTempState(session);
        String tempGrade = LoginSession.getTempGrade(session);
        //String tempSubject = LoginSession.getTempSubject(session);
		model.addAttribute("temp_id",tempId);
		model.addAttribute("tempClass",tempClass);
		model.addAttribute("tempState",tempState);
		model.addAttribute("tempGrade",tempGrade);
		//model.addAttribute("tempSubject",tempSubject);



//		String lan="KR";
//    	if(pathVariables.containsKey("lan")) {
//    		lan = pathVariables.get("lan");
//    	}
//
//    	model.addAttribute("bg", path.split("/")[0]);
//    	model.addAttribute("bgGb", path.split("/")[1]);
//    	model.addAttribute("lan",lan);
    	//path = String.format("/front/%s",path);
		return path;
	}
	public String pathToLangMain(Map<String, String> pathVariables, ModelMap model){
		model.addAttribute("lang",pathVariables);

//		String lan="KR";
//    	if(pathVariables.containsKey("lan")) {
//    		lan = pathVariables.get("lan");
//    	}
//    	model.addAttribute("lan",lan);
		return "/main";
	}
}
