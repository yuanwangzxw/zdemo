package com.zxw.authentication.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.*;

/**
 * 改变参数拦截器
 *
 * @author zxw
 * @date 2020-11-07 23:26
 **/
@WebFilter
public class ParameterMapFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }

    private class ParameterWrapper extends HttpServletRequestWrapper{

        private Map<String, String[]> map = new HashMap<>();

        public ParameterWrapper(HttpServletRequest request) {
            super(request);
            map.putAll(request.getParameterMap());
        }

        @Override
        public Enumeration<String> getParameterNames() {
            return new Vector<>(map.keySet()).elements();
        }

        @Override
        public String getParameter(String name) {
            return Optional.ofNullable(map.get(name)).map(x->x[0]).orElse(null);
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return Collections.unmodifiableMap(map);
        }

        @Override
        public String[] getParameterValues(String name) {
            return map.get(name);
        }
    }
}
