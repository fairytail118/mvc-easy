/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.mybatis;

import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easy.admin.entity.User;
import com.easy.core.entity.BaseEntity;
import com.easy.core.security.util.SecurityUtil;

/**
 * 设置一些默认值
 * 
 * @author wy
 * @version v 0.1 2013-9-11 下午7:58:31 wy Exp $
 */
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class,
                                                                           Object.class }) })
public class DefaultValueInterceptor implements Interceptor {

    /** 日志对象 */
    protected final static Logger log = LoggerFactory.getLogger(AbstractPageInterceptor.class);

    /**
     * 
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public final Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getArgs()[0] instanceof MappedStatement) {
            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

            Date date = new Date();

            // 获取当前用户
            String name = null;
            User user = SecurityUtil.getLoginUser();
            if (user != null) {
                name = user.getUsername();
            }
            // 如果是更新或者插入
            if (mappedStatement.getSqlCommandType() == SqlCommandType.UPDATE
                && (invocation.getArgs()[1] != null && invocation.getArgs()[1] instanceof BaseEntity)) {
                // 如果是BaseEntity参数
                BaseEntity entity = (BaseEntity) invocation.getArgs()[1];

                if (entity.getModifyTime() == null) {
                    entity.setModifyTime(date);
                }
                if (StringUtils.isBlank(entity.getModifyUser())) {
                    entity.setModifyUser(name);
                }
            } else if (mappedStatement.getSqlCommandType() == SqlCommandType.INSERT
                       && (invocation.getArgs()[1] != null && invocation.getArgs()[1] instanceof BaseEntity)) {
                // 如果是BaseEntity参数
                BaseEntity entity = (BaseEntity) invocation.getArgs()[1];
                // 插入都需要设置
                if (entity.getCreateTime() == null) {
                    entity.setCreateTime(date);
                }
                if (StringUtils.isBlank(entity.getCreateUser())) {
                    entity.setCreateUser(name);
                }
                if (entity.getModifyTime() == null) {
                    entity.setModifyTime(date);
                }
                if (StringUtils.isBlank(entity.getModifyUser())) {
                    entity.setModifyUser(name);
                }
            }
        }

        return invocation.proceed();
    }

    /**
     * 
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {

    }

}
