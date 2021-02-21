package com.jjr8112.webserver.core.listener;

import com.jjr8112.webserver.core.listener.event.ServletRequestEvent;

import java.util.EventListener;

/**
 * 请求层面上的监听器
 */
public interface ServletRequestListener extends EventListener {
    /**
     * 请求初始化
     * @param sre
     */
    void requestInitialized(ServletRequestEvent sre);

    /**
     * 请求销毁
     * @param sre
     */
    void requestDestroyed(ServletRequestEvent sre);
}
