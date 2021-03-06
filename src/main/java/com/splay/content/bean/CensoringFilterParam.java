/**
 * This document is a part of the source code and related artifacts
 * for Starz Play FZLLC  
 *
 * http://www.starzplay.com/
 *
 * Copyright 2017
 *
 */
package com.splay.content.bean;

/**
 * @author Shabeer Ellath
 * @Version 1.0
 * Created Date: Nov 20, 2017
 * 
 */
public class CensoringFilterParam extends BaseFilterParam {

	private String level;

	
	
	/* (non-Javadoc)
	 * @see com.splay.content.bean.BaseFilterParam#setParamValues(java.lang.String[])
	 */
	@Override
	public void setParamValues(String[] params) {
		if(params!=null && params.length>=0) {
			this.level = params[0];
		}
	}



	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}
	
}
