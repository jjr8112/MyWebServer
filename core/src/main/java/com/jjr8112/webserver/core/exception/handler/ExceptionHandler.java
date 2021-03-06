package com.jjr8112.webserver.core.exception.handler;

import com.jjr8112.webserver.core.exception.RequestInvalidException;
import com.jjr8112.webserver.core.exception.base.ServletException;
import com.jjr8112.webserver.core.network.wrapper.SocketWrapper;
import com.jjr8112.webserver.core.response.Header;
import com.jjr8112.webserver.core.response.Response;
import com.jjr8112.webserver.core.util.IOUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.jjr8112.webserver.core.constant.ContextConstant.ERROR_PAGE;

/**
 * 异常处理器
 * 会根据异常对应的HTTP Status设置response的状态以及相应的错误页面
 */
@Slf4j
public class ExceptionHandler {

    public void handle(ServletException e, Response response, SocketWrapper socketWrapper) {
        try {
            if (e instanceof RequestInvalidException) {
                log.info("请求无法读取，丢弃");
                socketWrapper.close();
            } else {
                log.info("抛出异常:{}", e.getClass().getName());
                e.printStackTrace();
                response.addHeader(new Header("Connection", "close"));
                response.setStatus(e.getStatus());
                response.setBody(IOUtil.getBytesFromFile(
                        String.format(ERROR_PAGE, String.valueOf(e.getStatus().getCode()))));
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
