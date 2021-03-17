package com.plushih.common.ci;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class CommonLoad implements ApplicationListener {

	private static final Logger log = LoggerFactory.getLogger( CommonLoad.class );
//	@Autowired
//	private CommonService commonService;
//
//	@Autowired
//	private CommonLabel commonLabel;
//
//	@Autowired
//	private SystemConfig systemConfig;
//
//	@Autowired
//	private FileRepository respository;

	public void onApplicationEvent(ApplicationEvent event){


//				System.out.println("respository.getRoot()=="+respository.getRoot());
//
//				String path = respository.getRoot();
//		System.out.println("eeeeeeeeeeeeeeee ee123eeeeeeeeeeee"+path);
//				if(path == null){
//		System.out.println("eeeeeeeeeeeeeeeeee123eeeeeeeeeeee");
//				path = this.getClass().getClassLoader().getResource("/path.properties").getPath();
//					path = path.substring(0, path.indexOf("WEB-INF"));
//
//					log.info("path : "+path);
////					respository.setRoot(path);
//				}
		if(event instanceof ContextRefreshedEvent){

			//LOGGER.info("onApplicationEvent(ApplicationEvent event)");
			System.out.println("eeeeeeeeeeeeeeeeeeeeee123eeeeeeee");
//			try {
//				String path = respository.getRoot();
//				if(path == null){
//					path = this.getClass().getClassLoader().getResource("/path.properties").getPath();
//					path = path.substring(0, path.indexOf("WEB-INF"));
//
//					log.info("path : "+path);
//					respository.setRoot(path);
//				}
//				commonService.loadLabel();
//				commonService.loadMessage();
//				//commonService.loadSearch();
//				CommonConfig.SYSTEM_THEME = systemConfig.getTheme();
//				CommonConfig.SYSTEM_THEME_PATH = systemConfig.getThemePath();
//				CommonConfig.SYSTEM_MAPKEY = systemConfig.getMapkey();
//				CommonConfig.SYSTEM_MAPKEY2 = systemConfig.getMapkey2();
//
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				log.error("onApplicationEvent", e);
//			}
//			log.debug("Label size : "+commonLabel.getLagelSize());
//			log.debug("Message size : "+commonLabel.getMessageSize());
//			log.debug("systemConfig : " + systemConfig);
		}
	}
}