package com.jjr8112.webserver.sample.web.lintener;

import com.jjr8112.webserver.core.listener.ServletRequestListener;
import com.jjr8112.webserver.core.listener.event.ServletRequestEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyServletRequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        log.info("request destroy...");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        log.info("request init...");
    }
}

