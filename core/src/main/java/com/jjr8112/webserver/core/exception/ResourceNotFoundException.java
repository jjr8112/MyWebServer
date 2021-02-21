package com.jjr8112.webserver.core.exception;

import com.jjr8112.webserver.core.enumeration.HttpStatus;
import com.jjr8112.webserver.core.exception.base.ServletException;

/**
 * 静态资源未找到
 */
public class ResourceNotFoundException extends ServletException {
    private static final HttpStatus status = HttpStatus.NOT_FOUND;
    public ResourceNotFoundException() {
        super(status);
    }
}

