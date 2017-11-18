/**
 * This document is a part of the source code and related artifacts
 * for Starz Play FZLLC  
 *
 * http://www.starzplay.com/
 *
 * Copyright 2017
 *
 */
package com.splay.content.service;

import com.splay.content.exception.DataloadFailedException;
import com.splay.content.model.VideoMetaData;

/**
 * @author Shabeer Ellath
 * @Version 1.0
 * Created Date: Nov 18, 2017
 * 
 */
public interface IMediaDataLoadService {

	VideoMetaData retreiveDataWithCensoring(String srcUrl, boolean isCensored) 
			throws DataloadFailedException;
	
	VideoMetaData retreiveDataWithoutCensoring(String srcUrl)
			throws DataloadFailedException;
}
