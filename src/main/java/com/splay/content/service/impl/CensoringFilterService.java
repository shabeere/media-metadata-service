/**
 * This document is a part of the source code and related artifacts
 * for Starz Play FZLLC  
 *
 * http://www.starzplay.com/
 *
 * Copyright 2017
 *
 */
package com.splay.content.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.splay.content.bean.BaseFilterParam;
import com.splay.content.bean.CensoringFilterParam;
import com.splay.content.exception.DataloadFailedException;
import com.splay.content.model.VideoMetaData;
import com.splay.content.service.IMediaFilterService;
import com.splay.util.AppConstants;
import com.splay.util.ContentFilterHelper;

/**
 * @author Shabeer Ellath
 * @Version 1.0
 * Created Date: Nov 20, 2017
 * 
 */
@Service("FilterService_CENSORING")
public class CensoringFilterService implements IMediaFilterService {

	private static final Logger logger = LoggerFactory.getLogger(CensoringFilterService.class);
	
	@Autowired
	private ContentFilterHelper filterHelper;
	
	/*
	 * (non-Javadoc)
	 * @see com.splay.content.service.IMediaFilterService#applyFilter(java.lang.String, com.splay.content.bean.BaseFilterParam)
	 */
	@Override
	public VideoMetaData applyFilter(String metaDataUrl, BaseFilterParam filterParam) 
		throws DataloadFailedException{
		logger.trace("Applying sensoring filter");
		if(filterParam==null || ! (filterParam instanceof CensoringFilterParam)) {
			logger.warn("Invalid filter parameter. Expected non-null CensoringFilterParam");
			return null;
		}
		CensoringFilterParam censoringFilter = (CensoringFilterParam)filterParam;
		VideoMetaData metaData = null;
		String censoringLevel = censoringFilter.getLevel();
		if(censoringLevel==null) {
			metaData = retreiveDataWithoutCensoring(metaDataUrl);
		} else {
			metaData = retreiveDataWithCensoring(metaDataUrl, 
					censoringLevel.equalsIgnoreCase(AppConstants.CONTENT_CENSORED));
		}
		
		return metaData;
	}
	
	/* 
	 * This function retrieve content from an external URL and 
	 * returns the same content after applying filters based on
	 * the filter parameters received in the request
	 * 
	 * (non-Javadoc)
	 * @see com.splay.content.service.IMediaDataLoadService#retreiveDataWithCensoring(java.lang.String, boolean)
	 */
	private VideoMetaData retreiveDataWithCensoring(String srcUrl, boolean isCensored) 
			throws DataloadFailedException {
		logger.trace("Retreiving data with censoring filter");
		VideoMetaData metaData;
		try {
			metaData = filterHelper.retreiveFromSource(srcUrl);
		} catch (Exception e) {
			logger.error("Error while retreiving raw data from source");
			throw new DataloadFailedException("Could not load data from source.");
		}
		logger.trace("Applying filter based on request");
    	VideoMetaData filteredData = filterHelper.filterContent(metaData, isCensored);
		
		return filteredData;
	}

	/*
	 * This function retrieve content from an external URL and 
	 * returns the same content to the caller
	 * 
	 * (non-Javadoc)
	 * @see com.splay.content.service.IMediaDataLoadService#retreiveDataWithoutCensoring(java.lang.String)
	 */
	private VideoMetaData retreiveDataWithoutCensoring(String srcUrl) 
			throws DataloadFailedException {
		logger.trace("Retreiving data without censoring filter");
		VideoMetaData metaData;
		try {
			metaData = filterHelper.retreiveFromSource(srcUrl);
		} catch (Exception e) {
			logger.error("Error while retreiving raw data from source");
			throw new DataloadFailedException("Could not load data from source.");
		}
		logger.trace("No filter will be applied");
    	
		return metaData;
	}

}
