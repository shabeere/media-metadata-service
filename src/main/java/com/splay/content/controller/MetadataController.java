/**
 * This document is a part of the source code and related artifacts
 * for Starz Play FZLLC  
 *
 * http://www.starzplay.com/
 *
 * Copyright 2017
 *
 */

package com.splay.content.controller;

import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.splay.constants.FilterType;
import com.splay.content.bean.BaseFilterParam;
import com.splay.content.bean.MediaRequestParam;
import com.splay.content.exception.DataloadFailedException;
import com.splay.content.model.VideoMetaData;
import com.splay.content.service.IMediaFilterService;
import com.splay.content.service.MediaFilterServiceFactory;
import com.splay.util.AppConstants;
import com.splay.util.AppUtil;
import com.splay.validator.RequestValidator;

/**
 * The rest controller class that accepts the http request for
 * content meta data and returns filtered data based on request 
 * parameters.
 * 
 * @author Shabeer Ellath
 * @Version 1.0
 * Created Date: Nov 17, 2017
 * 
 */

@RestController
public class MetadataController {

	private static final Logger logger = LoggerFactory.getLogger(MetadataController.class);
	
	@Autowired
	private RequestValidator requestValidator;
	
	@Autowired
	private MediaFilterServiceFactory filterServiceFactory;
	
	@Value(value = "${metadata.data.url}")
	String metaDataUrl;

	/**
	 * This method accepts rest service requests. Two query parameters 'filter' and 'level'
	 * are set to the MediaRequestParam object by the framework.
	 * 
	 * @param videoMetadataReq This is the class that holds the request parameters
	 * @return VideoMetaData Returns the Json meta data
	 * @throws Exception
	 */
    @RequestMapping(value="/media", method = { RequestMethod.GET }, 
    		produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public VideoMetaData media(@Validated MediaRequestParam videoMetadataReq) throws Exception{
    	logger.trace("Received a request for media meta data with parameter filter {}, level {}", videoMetadataReq.getFilter(), videoMetadataReq.getLevel());
    	BindingResult result = new BeanPropertyBindingResult(videoMetadataReq, "videoMetadataReq");
    	requestValidator.validate(videoMetadataReq, result);
    	if(result.hasErrors()) {
    		throw new ValidationException(result.getFieldErrors().stream().findFirst().get().getCode());
    	}
    	// Load the data
    	VideoMetaData metaData = retrieveMetadata(videoMetadataReq);
    	
    	return metaData;
    }
    
    /**
	 * Private method that process the metadata request
	 * 
	 * @param videoMetadataReq
	 * @param filter
	 * @return
	 * @throws DataloadFailedException
	 */
	private VideoMetaData retrieveMetadata(MediaRequestParam videoMetadataReq)
			throws DataloadFailedException {
		String filter = AppConstants.CONTENT_FILTER_NO_FILTER;
    	if(videoMetadataReq.getFilter() != null) {
    		filter = videoMetadataReq.getFilter().trim().toUpperCase();
    	}
    	
    	FilterType filterType = FilterType.getFilterType(filter);
    	if(filterType==null) {
    		logger.warn("Incomplete implementation for filter", filter);
    		throw new DataloadFailedException("Incomplete implementation for filter "+filter);
    	}
    	// An additional check
    	IMediaFilterService filterService = filterServiceFactory.getMediaFilterService(filterType);
    	if(filterService==null) {
    		logger.warn("Additional valid filter {} added. Functionality not implemented", filter);
    		throw new DataloadFailedException("Filter implementation not found");
    	}
    	
    	BaseFilterParam filterParam = AppUtil.getFilterParam(filterType);
    	filterParam.setParamValues(new String[] {videoMetadataReq.getLevel()});
    	VideoMetaData metaData = filterService.applyFilter(metaDataUrl, filterParam);
    	
		return metaData;
	}
}
