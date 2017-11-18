/**
 * This document is a part of the source code and related artifacts
 * for Starz Play FZLLC  
 *
 * http://www.starzplay.com/
 *
 * Copyright 2017
 *
 */
package com.splay.request.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
 * <h1>Add a unique string in the MDC context</h1>
 * 
 * The RequestFilter filter class adds a unique string in the 
 * MDC context for every incoming request. This helps to identify
 *  log statements for a particular request making the debugging 
 * job much easier.
 * 
 * @see org.slf4j.MDC
 * 
 * @author Shabeer Ellath
 * @Version 1.0
 * Created Date: Nov 16, 2017
 * 
 */
@Component
public class RequestFilter implements Filter {
 
	private static final String LOGGER_ID_KEY = "LOGGER_ID_KEY";
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter()
	 */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            // Set unique ID in MDC context
            MDC.put(LOGGER_ID_KEY, UUID.randomUUID().toString()); //Variable 'mdcData' is referenced in Spring Boot's logging.pattern.level property
            chain.doFilter(request, response);
        } finally {
           // Clean up MDC context
           MDC.clear();
        }
    }

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
