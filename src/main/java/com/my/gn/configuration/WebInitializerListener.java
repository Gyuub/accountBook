package com.my.gn.configuration;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @Class WebInitializerListener.java
 *	@Description WebInitializerListener
 * <pre>
 *
 *      수정일                  수정자                   수정내용
 * --------------  -------     ---------------------
 *  2019. 7. 5.          hgb             최초작성
 *
 * </pre>
 * @author hgb
 * @version 0.0  
 * @see
 *
 */
public class WebInitializerListener implements ServletContextListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebInitializerListener.class);
	
	/**/
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		LOGGER.info("================  WebInitializerListener START ================");		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		LOGGER.info("================  WebInitializerListener END ================");
	}

	
}
