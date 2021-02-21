package com.jjr8112.webserver.core.exception;

import com.jjr8112.webserver.core.enumeration.HttpStatus;
import com.jjr8112.webserver.core.exception.base.ServletException;

/**
 * 模板引擎解析错误（html文件编写错误）
 */
public class TemplateResolveException extends ServletException {
    private static final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    public TemplateResolveException() {
        super(status);
    }
}