package com.jjr8112.webserver.core.listener.event;

import com.jjr8112.webserver.core.context.ServletContext;
import com.jjr8112.webserver.core.request.Request;

/**
 * request相关的事件
 */
public class ServletRequestEvent extends java.util.EventObject {

    private static final long serialVersionUID = -7467864054698729101L;

    private final transient Request request;


    public ServletRequestEvent(ServletContext sc, Request request) {
        super(sc);
        this.request = request;
    }


    public Request getServletRequest () {
        return this.request;
    }


    public ServletContext getServletContext () {
        return (ServletContext) super.getSource();
    }
}

