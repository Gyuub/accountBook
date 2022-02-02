package com.my.gn.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

import com.my.gn.configuration.database.ContextDataBase;
import com.my.gn.configuration.database.ContextSqlMapper;

/**
 * @Class RootWebapplication.java
 *	@Description RootWebapplication
 * <pre>
 *
 *      수정일                  수정자                   수정내용
 * --------------  -------     ---------------------
 *  2019. 9. 11.          hgb             최초작성
 *
 * </pre>
 * @author hgb
 * @version 0.0  
 * @see
 *
 */
@Configuration
@Import({
	  ContextDataBase.class
	, ContextSqlMapper.class
	})
@ComponentScan(basePackages="com.my.gn"
						, useDefaultFilters = false 
						, includeFilters = {@Filter(type = FilterType.ANNOTATION, classes = org.springframework.stereotype.Service.class)
												,@Filter(type = FilterType.ANNOTATION, classes = org.springframework.stereotype.Repository.class)} 
						, excludeFilters =  @Filter(type = FilterType.ANNOTATION, classes = org.springframework.stereotype.Controller.class)) //
public class RootWebapplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RootWebapplication.class);
	
	public RootWebapplication() {
		LOGGER.info("================ RootWebapplication Load ================");
	}
	
}
