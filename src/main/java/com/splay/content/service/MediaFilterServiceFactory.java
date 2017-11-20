/**
 * This document is a part of the source code and related artifacts
 * for Starz Play FZLLC  
 *
 * http://www.starzplay.com/
 *
 * Copyright 2017
 *
 */
package com.splay.content.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.splay.constants.FilterType;

/**
 * @author Shabeer Ellath
 * @Version 1.0
 * Created Date: Nov 20, 2017
 * 
 */

@Service("MediaFilterServiceFactory")
public class MediaFilterServiceFactory {

	@Autowired
    private ApplicationContext applicationContext;
	
	public IMediaFilterService getMediaFilterService(FilterType filterType) {
		return (IMediaFilterService) applicationContext.getBean("FilterService_" + filterType.getName());
    }
}
