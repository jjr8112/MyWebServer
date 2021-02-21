package com.jjr8112.webserver.core.listener.event;

import com.jjr8112.webserver.core.context.ServletContext;

/**
 * servletContext相关的事件
 */
public class ServletContextEvent extends java.util.EventObject {


    public ServletContextEvent(ServletContext source) {
        super(source);
    }

    public ServletContext getServletContext () {
        return (ServletContext) super.getSource();
    }
}
