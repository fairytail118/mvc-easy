/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.mvc;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

/**
 * 
 * @author wy
 * @version v 0.1 2013-6-17 下午8:25:18 wy Exp $
 */
public class DateEditor extends PropertyEditorSupport {

    /** 默认的格式化 */
    public static final DateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final String[]   PARSE_PATTERNS = new String[] { "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd"                         };

    /**
     * @see java.beans.PropertyEditorSupport#getAsText()
     */
    @Override
    public String getAsText() {
        Object value = getValue();
        if (value instanceof Date) {
            return DEFAULT_FORMAT.format((Date) value);
        }
        return super.getAsText();
    }

    /**
     * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        text = text.trim();
        if (StringUtils.isBlank(text)) {
            setValue(null);
            return;
        }
        try {
            Date date = DateUtils.parseDate(text, PARSE_PATTERNS);
            setValue(date);
        }
        catch (ParseException ex) {
            IllegalArgumentException iae = new IllegalArgumentException("Could not parse date["
                                                                        + text + "]: "
                                                                        + ex.getMessage());
            iae.initCause(ex);
            throw iae;
        }

    }

}
