package com.jjr8112.webserver.core.exception;

import com.jjr8112.webserver.core.enumeration.HttpStatus;
import com.jjr8112.webserver.core.exception.base.ServletException;

/**
 * 未找到对应的Servlet（web.xml配置错误）
 */
public class ServletNotFoundException extends ServletException {
    private static final HttpStatus status = HttpStatus.NOT_FOUND;
    public ServletNotFoundException() {
        super(status);
    }
}
