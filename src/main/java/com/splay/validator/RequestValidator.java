/**
 * This document is a part of the source code and related artifacts
 * for Starz Play FZLLC  
 *
 * http://www.starzplay.com/
 *
 * Copyright 2017
 *
 */
package com.splay.validator;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.splay.content.bean.MediaRequestParam;

/**
 * The class that validates the input parameters send to the service
 * Spaces at the beginning and end are ignored and the parameters are
 * non-case sensitive.
 * 
 * @author Shabeer Ellath
 * @Version 1.0
 * Created Date: Nov 17, 2017
 * 
 */
@Component
public class RequestValidator implements Validator {

	private static final Logger logger = LoggerFactory.getLogger(RequestValidator.class);
	
	@Value("#{'${video.filter.options}'.split(',')}") 
	private List<String> filterList;
	
	@Value("#{'${video.filter.level.options}'.split(',')}") 
	private List<String> filterLevelList;
	
	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> aClazz) {
		return MediaRequestParam.class.isAssignableFrom(aClazz);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object objToValidate, Errors errors) {
		logger.trace("Validating request parameters");
		MediaRequestParam paramObj = (MediaRequestParam)objToValidate;
		// Validate filter
		String filter = paramObj.getFilter();
		if(filter==null) {
			// There is no filter parameter. No more validation required
			return;
		}
		
		boolean isFilterValid = filterList.stream().anyMatch(val -> val.trim().equalsIgnoreCase(filter.trim()));
		if(!isFilterValid) {
			logger.warn("Invalid filter parameter value.");
			errors.rejectValue("filter", "filter parameter value is not valid");
		}
		
		// Validate filter level
		String filterLevel = paramObj.getLevel();
		if(filterLevel!=null) {
			boolean isLevelValid = filterLevelList.stream().anyMatch(val -> val.trim().equalsIgnoreCase(filterLevel.trim()));
			if(!isLevelValid) {
				logger.warn("Invalid filter level parameter value.");
				errors.rejectValue("level", "level parameter value is not valid");
			}
		}
		
	}

}
