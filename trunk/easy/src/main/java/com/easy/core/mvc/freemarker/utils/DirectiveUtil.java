/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.mvc.freemarker.utils;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.view.AbstractTemplateView;

import com.easy.core.mvc.DateEditor;

import freemarker.core.Environment;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;

/**
 * 模板操作工具类
 * 
 * @author wy
 * @version v 0.1 2013-10-3 下午1:52:21 wy Exp $
 */
public class DirectiveUtil {

    /**
     * 获得RequestContext
     * 
     * ViewResolver中的exposeSpringMacroHelpers必须为true
     * 
     * @param env
     * @return
     * @throws TemplateException
     */
    public static RequestContext getContext(Environment env) throws TemplateException {
        TemplateModel ctx = env
            .getGlobalVariable(AbstractTemplateView.SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE);
        if (ctx instanceof AdapterTemplateModel) {
            return (RequestContext) ((AdapterTemplateModel) ctx)
                .getAdaptedObject(RequestContext.class);
        } else {
            throw new TemplateModelException(
                "RequestContext '" + AbstractTemplateView.SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE
                        + "' not found in DataModel.");
        }
    }

    /**
     * 获取字符串
     * 
     * @param name
     * @param params
     * @return
     * @throws TemplateException
     */
    public static String getString(String name, Map<String, TemplateModel> params)
                                                                                  throws TemplateException {
        TemplateModel model = params.get(name);
        if (model == null) {
            return null;
        }
        if (model instanceof TemplateScalarModel) {
            return ((TemplateScalarModel) model).getAsString();
        } else if ((model instanceof TemplateNumberModel)) {
            return ((TemplateNumberModel) model).getAsNumber().toString();
        } else {
            throw new TemplateModelException(name + " value is not String");
        }
    }

    /**
     * 获取Long
     * 
     * @param name
     * @param params
     * @return
     * @throws TemplateException
     */
    public static Long getLong(String name, Map<String, TemplateModel> params)
                                                                              throws TemplateException {
        TemplateModel model = params.get(name);
        if (model == null) {
            return null;
        }
        if (model instanceof TemplateScalarModel) {
            String s = ((TemplateScalarModel) model).getAsString();
            if (StringUtils.isBlank(s)) {
                return null;
            }
            try {
                return Long.parseLong(s);
            }
            catch (NumberFormatException e) {
                throw new TemplateModelException(name + " value is not Long");
            }
        } else if (model instanceof TemplateNumberModel) {
            return ((TemplateNumberModel) model).getAsNumber().longValue();
        } else {
            throw new TemplateModelException(name + " value is not Long");
        }
    }

    /**
     * 获取int
     * 
     * @param name
     * @param params
     * @return
     * @throws TemplateException
     */
    public static Integer getInt(String name, Map<String, TemplateModel> params)
                                                                                throws TemplateException {
        TemplateModel model = params.get(name);
        if (model == null) {
            return null;
        }
        if (model instanceof TemplateScalarModel) {
            String s = ((TemplateScalarModel) model).getAsString();
            if (StringUtils.isBlank(s)) {
                return null;
            }
            try {
                return Integer.parseInt(s);
            }
            catch (NumberFormatException e) {
                throw new TemplateModelException(name + " value is not Integer");
            }
        } else if (model instanceof TemplateNumberModel) {
            return ((TemplateNumberModel) model).getAsNumber().intValue();
        } else {
            throw new TemplateModelException(name + " value is not Integer");
        }
    }

    /**
     * 获取boolean
     * 
     * @param name
     * @param params
     * @return
     * @throws TemplateException
     */
    public static Boolean getBool(String name, Map<String, TemplateModel> params)
                                                                                 throws TemplateException {
        TemplateModel model = params.get(name);
        if (model == null) {
            return null;
        }
        if (model instanceof TemplateBooleanModel) {
            return ((TemplateBooleanModel) model).getAsBoolean();
        } else if (model instanceof TemplateNumberModel) {
            return !(((TemplateNumberModel) model).getAsNumber().intValue() == 0);
        } else if (model instanceof TemplateScalarModel) {
            String s = ((TemplateScalarModel) model).getAsString();
            if (StringUtils.isNotBlank(s)) {
                return s.equalsIgnoreCase("true") || !s.equals("0");
            } else {
                return null;
            }
        } else {
            throw new TemplateModelException(name + " value is not Boolean");
        }
    }

    /**
     * 获取日期
     * 
     * @param name
     * @param params
     * @return
     * @throws TemplateException
     */
    public static Date getDate(String name, Map<String, TemplateModel> params)
                                                                              throws TemplateException {
        TemplateModel model = params.get(name);
        if (model == null) {
            return null;
        }
        if (model instanceof TemplateDateModel) {
            return ((TemplateDateModel) model).getAsDate();
        } else if (model instanceof TemplateScalarModel) {
            String date = ((TemplateScalarModel) model).getAsString();
            try {
                return DateUtils.parseDate(date, DateEditor.PARSE_PATTERNS);
            }
            catch (ParseException e) {
                throw new TemplateModelException(name + " value is not Date", e);
            }

        } else {
            throw new TemplateModelException(name + " value is not Date");
        }
    }

}
