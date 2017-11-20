/**
 * This document is a part of the source code and related artifacts
 * for Starz Play FZLLC  
 *
 * http://www.starzplay.com/
 *
 * Copyright 2017
 *
 */
package com.splay.constants;

import org.apache.commons.lang.StringUtils;

/**
 * @author Shabeer Ellath
 * @Version 1.0 Created Date: Nov 20, 2017
 * 
 */
public enum FilterType {

	NO_FILTER("NONE"),
	CENSORING("CENSORING");

	private String name;

	/**
	 * @param name
	 */
	private FilterType(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public static FilterType getFilterType(String name) {
		if(StringUtils.isNotBlank(name)) {
			for (FilterType ft : FilterType.values()) {
				if (name.trim().equalsIgnoreCase(ft.getName()))
					return ft;
			}
		}
		
		return null;
	}
}
