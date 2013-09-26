/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.security;

import javax.servlet.http.HttpServletRequest;

import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.UserAgent;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.easy.core.utils.RequestUtil;

/**
 * 用户访问登录信息
 * 
 * @author wy
 * @version v 0.1 2013-9-24 下午11:04:28 wy Exp $
 */
public class EasyWebAuthenticationDetails extends WebAuthenticationDetails {

    /**  */
    private static final long serialVersionUID = 8903197463984821818L;

    private String            remoteAddress;

    /** 客户端浏览器 */
    private String            clientBrowser;

    /** 客户端操作系统 */
    private String            clientOs;

    public EasyWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
    }

    /**
     * 初始化后完之后执行此方法
     */
    public void doPopulateAdditionalInformation(HttpServletRequest request) {

        remoteAddress = RequestUtil.getIpAddr(request);
        String agent = request.getHeader("user-agent");
        try {
            UserAgent userAgent = UserAgent.parseUserAgentString(agent);
            clientBrowser = userAgent.getBrowser().getName()
                            + (userAgent.getBrowser().getGroup() == Browser.IE ? ""
                                : (" " + userAgent.getBrowserVersion().getVersion()));
            clientOs = userAgent.getOperatingSystem().getName();
        }
        catch (Exception e) {
            clientBrowser = "未知";
            clientOs = "未知";
        }
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public String getClientBrowser() {
        return clientBrowser;
    }

    public String getClientOs() {
        return clientOs;
    }

}
