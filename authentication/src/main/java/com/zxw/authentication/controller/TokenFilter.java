package com.zxw.authentication.controller;

import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * 令牌拦截器
 *
 * @author zxw
 * @date 2020-11-07 22:42
 **/
@WebFilter
public class TokenFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
        //继承wrapper，重写了getHeader方法，先从header中获取token，找不到就从参数或者cookie中找
        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper((HttpServletRequest) request) {
            @Override
            public String getHeader(String name) {
                String header = super.getHeader(name);
                if ("token".equals(name) && StringUtils.isEmpty(header)) {
                    String parameter = super.getParameter(name);
                    if (!StringUtils.isEmpty(parameter)) {
                        return parameter;
                    }
                    Cookie[] cookies = super.getCookies();
                    for (Cookie cookie : cookies) {
                        if (name.equals(cookie.getName())) {
                            return cookie.getValue();
                        }
                    }
                }
                return header;
            }
        };
        doFilter(wrapper,response,chain);
    }

    @Override
    public void destroy() {

    }
}
