/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.mvc.converters;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * 日期转换
 * 
 * @author wy
 * @version v 0.1 2013-9-24 下午9:16:13 wy Exp $
 */
public class DateConverter implements Converter<String, Date> {

    public static final String[] PARSE_PATTERNS = new String[] { "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd"                       };

    /**
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    @Override
    public Date convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        try {
            Date date = DateUtils.parseDate(source, PARSE_PATTERNS);
            return date;
        }
        catch (ParseException ex) {
            IllegalArgumentException iae = new IllegalArgumentException("Could not parse date["
                                                                        + source + "]: "
                                                                        + ex.getMessage());
            iae.initCause(ex);
            throw iae;
        }

    }

}
