/**
 * This document is a part of the source code and related artifacts
 * for Starz Play FZLLC  
 *
 * http://www.starzplay.com/
 *
 * Copyright 2017
 *
 */
package com.splay.util;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.splay.content.model.Entry;
import com.splay.content.model.VideoMetaData;

/**
 * @author Shabeer Ellath
 * @Version 1.0
 * Created Date: Nov 17, 2017
 * 
 */
@Component
public class ContentFilterHelper {

	private static final Logger logger = LoggerFactory.getLogger(ContentFilterHelper.class);
	
	/**
	 * The synchronized function that filter the contents of original VideoMetaData
	 * based on the 'isCensored' flag
	 * 
	 * @param aMetaData
	 * @param isCensored
	 * @return VideoMetaData Filtered data
	 */
	public synchronized final VideoMetaData filterContent(VideoMetaData aMetaData, boolean isCensored) {
		logger.trace("Applying filter to the contents");
		// get a 'censored' entries
		List<Entry> filteredEntry = aMetaData.getEntries().stream().filter(
				entry -> StringUtils.isNotBlank(entry.getPegContentClassification()) 
				&& entry.getPegContentClassification().equalsIgnoreCase(AppConstants.CONTENT_CENSORED)).
				collect(Collectors.toList());
		
		// Remove media based on the filter
		filteredEntry.forEach(entry -> {
			entry.getMedia().removeIf(media -> canRemoveMedia(isCensored, media.getGuid()));
		});
		
		// return modified meta data
		return aMetaData;
	}
	
	/**
	 * The private function that helps to decide if an
	 * element from the array should be removed.
	 * 
	 * @param isCensored
	 * @param guid
	 * @return
	 */
	private boolean canRemoveMedia(boolean isCensored, String guid) {
		if(StringUtils.isBlank(guid)) {
			return false;
		}
		String formattedGuid = guid.trim().toUpperCase();
		if(isCensored) {
			return !formattedGuid.endsWith(AppConstants.CONTENT_CENSORED_GUID_CHAR);
		}
		
		return formattedGuid.endsWith(AppConstants.CONTENT_CENSORED_GUID_CHAR);
	}
	
	/*
	 * function that retrieves data from external source
	 *  
	 * @param metaDataUrl The external source URL
	 * @return
	 * @throws Exception
	 */
	public synchronized final VideoMetaData retreiveFromSource(String metaDataUrl) 
			throws Exception{
		RestTemplate restTemplate = new RestTemplate();
    	logger.trace("Loading data from external URL {}", metaDataUrl);
    	// Load data from external URL
    	VideoMetaData metaData = restTemplate.getForObject(metaDataUrl, VideoMetaData.class);
    	logger.trace("Data loaded from external URL");
    	return metaData;
	}
}
