/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.mvc.freemarker.directives;

import java.io.IOException;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 
 * @author wy
 * @version v 0.1 2013-10-3 上午7:45:23 wy Exp $
 */
public class HtmlDirectiveModel implements TemplateDirectiveModel {

    /**
     * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.Environment,
     *      java.util.Map, freemarker.template.TemplateModel[],
     *      freemarker.template.TemplateDirectiveBody)
     */
    @Override
    @SuppressWarnings("rawtypes")
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
                        TemplateDirectiveBody body) throws TemplateException, IOException {

    }

}
