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
import com.splay.content.exception.DataloadFailedException;
import com.splay.content.model.VideoMetaData;
import com.splay.content.service.IMediaFilterService;
import com.splay.util.ContentFilterHelper;

/**
 * @author Shabeer Ellath
 * @Version 1.0
 * Created Date: Nov 20, 2017
 * 
 */
@Service("FilterService_NONE")
public class NoFilterFilterService implements IMediaFilterService {

	private static final Logger logger = LoggerFactory.getLogger(NoFilterFilterService.class);
	
	@Autowired
	private ContentFilterHelper filterHelper;
	
	/* (non-Javadoc)
	 * @see com.splay.content.service.IMediaFilterService#applyFilter(java.lang.String, com.splay.content.bean.BaseFilterParam)
	 */
	@Override
	public VideoMetaData applyFilter(String metaDataUrl, BaseFilterParam filterParam) throws DataloadFailedException {
		logger.trace("Retreiving data with no filter");
		VideoMetaData metaData;
		try {
			metaData = filterHelper.retreiveFromSource(metaDataUrl);
		} catch (Exception e) {
			logger.error("Error while retreiving raw data from source");
			throw new DataloadFailedException("Could not load data from source.");
		}
		
		return metaData;
	}

}
