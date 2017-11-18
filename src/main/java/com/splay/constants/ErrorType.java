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

/**
 * ErrorType enum for the application
 * 
 * @author Shabeer Ellath
 * @Version 1.0
 * Created Date: Nov 17, 2017
 * 
 */
public enum ErrorType {

	INVALID_PARAMETER("P001", "Invalid Parameter"),
	SERVER_ERROR("P002", "Server Error"),
	DATA_NOT_AVAILBALE("P003", "Data not available");;
	
	private String code;
	private String name;
	
	/**
	 * @param code
	 * @param name
	 */
	private ErrorType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	
}
