package com.yffd.jecap.common.base.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class SpringWebContextUtil {

    private SpringWebContextUtil() {
    }

    public static HttpServletResponse getResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (null == requestAttributes) {
            log.warn("=======非登录方式访问：获取【HttpServletResponse】=======");
            return null;
        }
        return((ServletRequestAttributes) requestAttributes).getResponse();
    }

    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (null == requestAttributes) {
            log.warn("=======非登录方式访问：获取【HttpServletRequest】=======");
            return null;
        }
        return((ServletRequestAttributes) requestAttributes).getRequest();
    }

    public static HttpSession getSession(boolean create) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (null == requestAttributes) {
            log.warn("=======非登录方式访问：获取【HttpSession】=======");
            return null;
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return request.getSession(create);
    }

    public static HttpSession getSession() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (null == requestAttributes) {
            log.warn("=======非登录方式访问：获取【HttpSession】=======");
            return null;
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return request.getSession(true);
    }

}
