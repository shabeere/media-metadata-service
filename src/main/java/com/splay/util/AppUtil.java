/**
 * This document is a part of the source code and related artifacts
 * for Starz Play FZLLC  
 *
 * http://www.starzplay.com/
 *
 * Copyright 2017
 *
 */
package com.splay.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.splay.constants.FilterType;
import com.splay.content.bean.BaseFilterParam;
import com.splay.content.bean.CensoringFilterParam;
import com.splay.content.bean.NoFilterParam;

/**
 * @author Shabeer Ellath
 * @Version 1.0
 * Created Date: Nov 20, 2017
 * 
 */
public class AppUtil {

	private static final Logger logger = LoggerFactory.getLogger(AppUtil.class);
	
	/**
	 * 
	 * @param fType Filter type
	 * @return
	 */
	public static final BaseFilterParam getFilterParam(FilterType fType) {
		BaseFilterParam fParam = null; 
		switch(fType) {
			case CENSORING:
				fParam =  new CensoringFilterParam();
				break;
			case NO_FILTER:
				fParam =  new  NoFilterParam();
				break;
			default :
				logger.warn("Filter param object not handled for filter type {}", fType.getName());
				break;
		}
		
		return fParam;
	}
}
