/**
 * This document is a part of the source code and related artifacts
 * for Starz Play FZLLC  
 *
 * http://www.starzplay.com/
 *
 * Copyright 2017
 *
 */
package com.splay.content.controller.advice;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.splay.constants.ErrorType;
import com.splay.content.exception.DataloadFailedException;
import com.splay.content.model.ErrorResponse;

/**
 * @author Shabeer Ellath
 * @Version 1.0
 * Created Date: Nov 17, 2017
 * 
 */
@ControllerAdvice
public class AppErrorHandler  {

	private static final Logger logger = LoggerFactory.getLogger(AppErrorHandler.class);
			
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
	@ResponseBody
    public ErrorResponse handleValidationException(ValidationException anException) {
        return new ErrorResponse(ErrorType.INVALID_PARAMETER, anException.getMessage());
    }

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleMethodArgumentNotValid(org.springframework.web.bind.MethodArgumentNotValidException, org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus, org.springframework.web.context.request.WebRequest)
	 */
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ErrorResponse handleMethodArgumentNotValid(
            MethodArgumentNotValidException anException ) {
		logger.info("MethodArgumentNotValidException raised");
		ErrorResponse erResp = new ErrorResponse(ErrorType.INVALID_PARAMETER, anException.getMessage());
	
		return erResp;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
	@ResponseBody
	public ErrorResponse handleBindException(
			BindException anException ) {
		logger.info("BindException raised:");
		
		String errorMsg = anException.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(anException.getMessage());
		
		ErrorResponse erResp = new ErrorResponse(ErrorType.INVALID_PARAMETER, errorMsg);
		return erResp;
	}
	
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseBody
	public ErrorResponse handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException anException ) {
		logger.info("Exception raised: Exception Class: {}, Message: {}", anException.getClass().getName(), anException.getMessage());
		
		ErrorResponse erResp = new ErrorResponse(ErrorType.SERVER_ERROR);
		return erResp;
		
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
	@ResponseBody
	public ErrorResponse handleException(
            Exception anException ) {
		logger.info("Exception raised: Exception Class: {}, Message: {}", anException.getClass().getName(), anException.getMessage());
		
		ErrorResponse erResp = new ErrorResponse(ErrorType.SERVER_ERROR);
		return erResp;
		
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException anException) {
        return new ErrorResponse(ErrorType.INVALID_PARAMETER, anException.getMessage());
    }
	
	@ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    @ExceptionHandler(DataloadFailedException.class)
	@ResponseBody
    public ErrorResponse handleDataloadFailedException(DataloadFailedException anException) {
        return new ErrorResponse(ErrorType.DATA_NOT_AVAILBALE, anException.getMessage());
    }
	
}
