package com.jjr8112.webserver.core.exception.base;

import com.jjr8112.webserver.core.enumeration.HttpStatus;
import lombok.Getter;

/**
 * 根异常
 */
@Getter
public class ServletException extends Exception {
    private HttpStatus status;
    public ServletException(HttpStatus status){
        this.status = status;
    }
}
