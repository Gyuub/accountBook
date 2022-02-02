package com.my.gn.configuration;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @Class WebInitializer.java
 *	@Description WebInitializer
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
 *	Servlet container(톰캣) 실행 >
 */
public class WebInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		//웹 어플리케이션의 시작과 종료 리스너
		WebInitializerListener listener = new WebInitializerListener();
        servletContext.addListener(listener);
        
        //root context 설정
  		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
  		rootContext.register(com.my.gn.configuration.RootWebapplication.class);
  		servletContext.addListener(new ContextLoaderListener(rootContext));
  		//ContextLoaderListener를 이용하면 RootContext 를 만
      		
		//servlet context 설정
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(com.my.gn.configuration.DispatcherServlet.class);

		//dispatcher 설정
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
		dispatcher.setAsyncSupported(true);		//servlet 3.0 이상 사용시 설정
		dispatcher.setLoadOnStartup(1);
        //dispatcher.addMapping("*");
        dispatcher.addMapping("/");
        
        // encoding 필터 적용
        FilterRegistration.Dynamic charaterEncodingFilter = servletContext.addFilter("charaterEncodingFilter", new CharacterEncodingFilter());
        charaterEncodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        charaterEncodingFilter.setInitParameter("encoding", "UTF-8");
        charaterEncodingFilter.setInitParameter("forceEncoding", "true");
        
	}
	
	

}
