/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.controller;

import javax.servlet.http.HttpServletRequest;

import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.UserAgent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.easy.core.controller.BaseController;
import com.easy.core.security.util.SecurityUtil;
import com.easy.core.utils.RequestUtil;

/**
 * 首页
 * 
 * @author wy
 * @version v 0.1 2013-9-15 下午12:53:19 wy Exp $
 */
@Controller
@RequestMapping("/admin")
public class IndexController extends BaseController {

    /**
     * 首页
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, ModelMap model) {

        model.put("user", SecurityUtil.getLoginUser());

        return "admin/index";
    }

    /**
     * 内容页 显示系统相关信息
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/system_index")
    public String system(HttpServletRequest request, ModelMap model) {
        //IP
        String ip = RequestUtil.getIpAddr(request);

        //浏览器
        String browser = null;

        //操作系统
        String clientOs = null;

        String agent = request.getHeader("user-agent");
        try {
            UserAgent userAgent = UserAgent.parseUserAgentString(agent);
            browser = userAgent.getBrowser().getName()
                      + (userAgent.getBrowser().getGroup() == Browser.IE ? "" : (" " + userAgent
                          .getBrowserVersion().getVersion()));
            clientOs = userAgent.getOperatingSystem().getName();
        }
        catch (Exception e) {
            browser = "未知";
            clientOs = "未知";
        }

        model.put("user", SecurityUtil.getLoginUser());
        model.put("ip", ip);
        model.put("browser", browser);
        model.put("clientOs", clientOs);
        model.put("agent", agent);

        return "admin/system_index";
    }

}
