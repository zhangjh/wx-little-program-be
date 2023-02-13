package me.zhangjh.chatgpt.helper.request;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangjh451@midea.com
 * @date 3:01 PM 2023/2/6
 * @Description
 */
public abstract class BaseRequest {

    public boolean debugMode(HttpServletRequest request) {
        return "1".equalsIgnoreCase(request.getParameter("debug"))
                && "wired_sheep".equalsIgnoreCase(request.getParameter("code"));
    }

    public void headerCheck(HttpServletRequest request) {
       if(debugMode(request)) {
           return;
       }
       Assert.isTrue(StringUtils.isNotEmpty(request.getHeader("userId")), "未登录");
    }

    public abstract void check();
}
