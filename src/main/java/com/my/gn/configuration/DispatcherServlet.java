package com.my.gn.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.my.gn.configuration.interceptor.Interceptor;

@Configuration
@EnableWebMvc //mvc: 태그 사용하기위함
@ComponentScan(basePackages="com.my.gn"
						, useDefaultFilters = false // false 설정시 includeFilters 설정된 어노테이션만 조회
						, includeFilters = @Filter(type = FilterType.ANNOTATION, classes = org.springframework.stereotype.Controller.class) // @controller 어노테이션만 Bean 사용
						, excludeFilters = {@Filter(type = FilterType.ANNOTATION, classes = org.springframework.stereotype.Service.class),
												 @Filter(type = FilterType.ANNOTATION, classes = org.springframework.stereotype.Repository.class)})
public class DispatcherServlet extends WebMvcConfigurerAdapter{

	private static final Logger LOGGER = LoggerFactory.getLogger(DispatcherServlet.class);
	
	DispatcherServlet(){
		LOGGER.info("================ DispatcherServlet Load ================");
	}
	
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
    
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setOrder(2);
        return resolver;
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/main.do");
		registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
		super.addViewControllers(registry);
	}
    
    /* 정적파일 처리 핸들러 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    
	/* 인터셉터 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new Interceptor()).excludePathPatterns("/");
	}
	
	//cors
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}
	
	
}
