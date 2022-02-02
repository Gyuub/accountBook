package com.my.gn.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomFilter implements Filter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomFilter.class);

	public CustomFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.info("================  FilterConfig START ================");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		LOGGER.info("================  Filtering.... ================");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		LOGGER.info("================  FilterConfig END ================");
		
	}

}
