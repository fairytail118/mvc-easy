/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.captcha;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easy.core.common.Constants;

/**
 * 
 * @author wy
 * @version v 0.1 2013-9-17 下午8:22:01 wy Exp $
 */
public class CaptchaServlet extends HttpServlet {

	/** serialVersionUID */
	private static final long serialVersionUID = -5132544599206897068L;

	/** 日志 */
	private static final Logger log = LoggerFactory
			.getLogger(CaptchaServlet.class);

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ServletOutputStream out = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			request.getSession(true).removeAttribute(Constants.CAPTCHA_CODE);
			Captcha captcha = new SpecCaptcha(100, 28, 4);// png格式验证码
			captcha.out(os);
			String code = captcha.text();

			request.getSession(true).setAttribute(Constants.CAPTCHA_CODE, code);

			byte[] bytes = os.toByteArray();
			response.setContentType("image/png;charset=UTF-8");
			response.setContentLength(bytes.length);
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			out = response.getOutputStream();
			out.write(bytes);
			out.flush();
		} catch (Exception e) {
			log.error("生成验证码失败", e);
		} finally {
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(os);
		}

	}

}
