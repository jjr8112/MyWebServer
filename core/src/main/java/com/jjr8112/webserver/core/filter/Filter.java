package com.jjr8112.webserver.core.filter;

import com.jjr8112.webserver.core.request.Request;
import com.jjr8112.webserver.core.response.Response;

/**
 * 过滤器
 */
public interface Filter {
    /**
     * 过滤器初始化
     */
    void init();

    /**
     * 过滤
     * @param request
     * @param response
     * @param filterChain
     */
    void doFilter(Request request, Response response, FilterChain filterChain) ;

    /**
     * 过滤器销毁
     */
    void destroy();
}