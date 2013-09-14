/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

/**
 * 
 * @author wy
 * @version v 0.1 2013-6-17 下午10:41:19 wy Exp $
 */
public class ExceptionUtil {

	/**
	 * 
	 * 
	 * @param e
	 * @return
	 */
	public static String getMessage(Exception e) {
		if (e instanceof EasyException) {
			return e.getMessage();
		}
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}

	/**
	 * 返回结果信息
	 * 
	 * @param e
	 * @return
	 */
	public static String getResultMessage(Exception e) {
		if (e instanceof EasyException) {
			return e.getMessage();
		} else if (e instanceof SQLException) {
			return "数据库异常";
		}
		//其他能转换的可视异常
		
		return "系统异常";
	}

}
