package com.jjr8112.webserver.core.listener;

import com.jjr8112.webserver.core.listener.event.HttpSessionEvent;

import java.util.EventListener;

public interface HttpSessionListener extends EventListener {
    /**
     * session创建
     * @param se
     */
    void sessionCreated(HttpSessionEvent se);

    /**
     * session销毁
     * @param se
     */
    void sessionDestroyed(HttpSessionEvent se);

}
