package com.jjr8112.webserver.core.network.wrapper;

import java.io.IOException;

/**
 * 自定义socket封装器
 */
public interface SocketWrapper {
    void close() throws IOException;
}

