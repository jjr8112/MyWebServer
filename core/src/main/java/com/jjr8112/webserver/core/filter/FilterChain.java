package com.jjr8112.webserver.core.filter;

import com.jjr8112.webserver.core.request.Request;
import com.jjr8112.webserver.core.response.Response;

/**
 * 拦截器链
 */
public interface FilterChain {
    /**
     * 当前filter放行，由后续的filter继续进行过滤
     * @param request
     * @param response
     */
    void doFilter(Request request, Response response) ;
}

