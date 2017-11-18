/**
 * This document is a part of the source code and related artifacts
 * for Starz Play FZLLC  
 *
 * http://www.starzplay.com/
 *
 * Copyright 2017
 *
 */
package com.splay.content.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.splay.constants.ErrorType;

/**
 * @author Shabeer Ellath
 * @Version 1.0
 * Created Date: Nov 17, 2017
 * 
 */
public class ErrorResponse {

	@JsonProperty("error-code")
	private String errorCode;
	@JsonProperty("error-desc")
	private String errorDesc;
	
	/**
	 * 
	 */
	public ErrorResponse(ErrorType anError) {
		this.errorCode = anError.getCode();
		this.errorDesc = anError.getName();
	}
	/**
	 * 
	 */
	public ErrorResponse(ErrorType anError, String message) {
		this.errorCode = anError.getCode();
		this.errorDesc = message;
	}
	
	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @return the errorDesc
	 */
	public String getErrorDesc() {
		return errorDesc;
	}
	/**
	 * @param errorDesc the errorDesc to set
	 */
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	
}
