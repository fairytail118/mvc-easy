/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easy.core.validator.annotations.EmailValidator;
import com.easy.core.validator.annotations.EqualsStringValidator;
import com.easy.core.validator.annotations.NumberRangeValidator;
import com.easy.core.validator.annotations.NumberValidator;
import com.easy.core.validator.annotations.RegexValidator;
import com.easy.core.validator.annotations.RequiredStringValidator;
import com.easy.core.validator.annotations.RequiredValidator;
import com.easy.core.validator.annotations.StringLengthValidator;
import com.easy.core.validator.annotations.Validations;

/**
 * 
 * @author wy
 * @version v 0.1 2013-9-10 下午10:58:30 wy Exp $
 */
public abstract class AbstractValidator implements Validator {

    /** 日志 */
    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * @see com.easy.core.validator.Validator#valid(com.easy.core.validator.annotations.Validations,
     *      javax.servlet.http.HttpServletRequest)
     */
    @Override
    public Map<String, String> valid(Validations validations, HttpServletRequest request) {
        List<ValidatorResult> list = new ArrayList<ValidatorResult>();

        // 邮箱验证
        EmailFieldValidator emailFieldValidator = new EmailFieldValidator();
        ValidatorResult result = null;

        for (EmailValidator validator : validations.emailValidators()) {
            result = emailFieldValidator.isValid(validator, request);
            if (result.isSuccess()) {
                continue;
            }
            list.add(result);
        }

        // 数字验证
        NumberFieldValidator numberFieldValidator = new NumberFieldValidator();
        for (NumberValidator validator : validations.numberValidators()) {
            result = numberFieldValidator.isValid(validator, request);
            if (result.isSuccess()) {
                continue;
            }
            list.add(result);
        }

        // 数字范围
        NumberRangeFieldValidator numberRangeFieldValidator = new NumberRangeFieldValidator();
        for (NumberRangeValidator validator : validations.numberRangeValidators()) {
            result = numberRangeFieldValidator.isValid(validator, request);
            if (result.isSuccess()) {
                continue;
            }
            list.add(result);
        }

        // 正则验证
        RegexFieldValidator regexFieldValidator = new RegexFieldValidator();
        for (RegexValidator validator : validations.regexValidators()) {
            result = regexFieldValidator.isValid(validator, request);
            if (result.isSuccess()) {
                continue;
            }
            list.add(result);
        }

        // 必填字段
        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator();
        for (RequiredValidator validator : validations.requiredValidators()) {
            result = requiredFieldValidator.isValid(validator, request);
            if (result.isSuccess()) {
                continue;
            }
            list.add(result);
        }

        // 必填字符
        RequiredStringFieldValidator requiredStringFieldValidator = new RequiredStringFieldValidator();
        for (RequiredStringValidator validator : validations.requiredStringValidators()) {
            result = requiredStringFieldValidator.isValid(validator, request);
            if (result.isSuccess()) {
                continue;
            }
            list.add(result);
        }
        // 字符串长度
        StringLengthFieldValidator stringLengthFieldValidator = new StringLengthFieldValidator();
        for (StringLengthValidator validator : validations.stringLengthValidators()) {
            result = stringLengthFieldValidator.isValid(validator, request);
            if (result.isSuccess()) {
                continue;
            }
            list.add(result);
        }
        //字符串匹配
        EqualsStringFieldValidator equalsStringFieldValidator = new EqualsStringFieldValidator();
        for (EqualsStringValidator validator : validations.equalsStringValidators()) {
            result = equalsStringFieldValidator.isValid(validator, request);
            if (result.isSuccess()) {
                continue;
            }
            list.add(result);
        }

        return handleResult(list, request);
    }

    /**
     * 处理验证的结果
     * 
     * @param list
     * @param request
     * @return
     */
    public abstract Map<String, String> handleResult(List<ValidatorResult> list,
                                                     HttpServletRequest request);

}
