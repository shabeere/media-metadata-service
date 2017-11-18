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
import org.springframework.web.client.RestTemplate;

import com.splay.content.exception.DataloadFailedException;
import com.splay.content.model.VideoMetaData;
import com.splay.content.service.IMediaDataLoadService;
import com.splay.util.ContentFilterHelper;

/**
 * @author Shabeer Ellath
 * @Version 1.0
 * Created Date: Nov 18, 2017
 * 
 */
@Service
public class MediaDataLoadService implements IMediaDataLoadService {

	private static final Logger logger = LoggerFactory.getLogger(MediaDataLoadService.class);
	
	@Autowired
	private ContentFilterHelper filterHelper;

	/* 
	 * This function retrieve content from an external URL and 
	 * returns the same content after applying filters based on
	 * the filter parameters received in the request
	 * 
	 * (non-Javadoc)
	 * @see com.splay.content.service.IMediaDataLoadService#retreiveDataWithCensoring(java.lang.String, boolean)
	 */
	@Override
	public VideoMetaData retreiveDataWithCensoring(String srcUrl, boolean isCensored) 
			throws DataloadFailedException {
		logger.trace("Retreiving data with censoring filter");
		VideoMetaData metaData;
		try {
			metaData = retreiveFromSource(srcUrl);
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
	@Override
	public VideoMetaData retreiveDataWithoutCensoring(String srcUrl) 
			throws DataloadFailedException {
		logger.trace("Retreiving data without censoring filter");
		VideoMetaData metaData;
		try {
			metaData = retreiveFromSource(srcUrl);
		} catch (Exception e) {
			logger.error("Error while retreiving raw data from source");
			throw new DataloadFailedException("Could not load data from source.");
		}
		logger.trace("No filter will be applied");
    	
		return metaData;
	}

	/*
	 * Private function that retreives data from external source
	 *  
	 * @param metaDataUrl The external source URL
	 * @return
	 * @throws Exception
	 */
	private VideoMetaData retreiveFromSource(String metaDataUrl) 
			throws Exception{
		RestTemplate restTemplate = new RestTemplate();
    	logger.trace("Loading data from external URL {}", metaDataUrl);
    	// Load data from external URL
    	VideoMetaData metaData = restTemplate.getForObject(metaDataUrl, VideoMetaData.class);
    	logger.trace("Data loaded from external URL");
    	return metaData;
	}
}
