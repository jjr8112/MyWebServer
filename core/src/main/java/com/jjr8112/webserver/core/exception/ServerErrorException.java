package com.jjr8112.webserver.core.exception;

import com.jjr8112.webserver.core.enumeration.HttpStatus;
import com.jjr8112.webserver.core.exception.base.ServletException;

/**
 * 500异常
 */
public class ServerErrorException extends ServletException {
    private static final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    public ServerErrorException() {
        super(status);
    }
}
