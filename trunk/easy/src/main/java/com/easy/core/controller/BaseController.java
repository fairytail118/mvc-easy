/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author wy
 * @version v 0.1 2013-9-10 上午7:39:41 wy Exp $
 */
public class BaseController {

	/** 日志 */
	protected final Logger log = LoggerFactory.getLogger(getClass());

	/** 是否重定向过来 */
	public final static String RELOAD = "realod";

}
