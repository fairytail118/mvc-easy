/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.mvc.freemarker;

import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * 
 * 
 * @author wy
 * @version v 0.1 2013-6-18 上午8:37:16 wy Exp $
 */
public class RichFreeMarkerViewResolver extends FreeMarkerViewResolver {
	/**
	 * 默认的viewclass
	 */
	public RichFreeMarkerViewResolver() {
		setViewClass(RichFreeMarkerView.class);
	}

	/**
	 * @see org.springframework.web.servlet.view.AbstractTemplateViewResolver#buildView(java.lang.String)
	 */
	@Override
	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		AbstractUrlBasedView view = super.buildView(viewName);
		// start with / ignore prefix
		if (viewName.startsWith("/")) {
			view.setUrl(viewName + getSuffix());
		}
		return view;
	}
}