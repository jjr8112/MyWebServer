package com.jjr8112.webserver.core.network.dispacher.nio;

import com.jjr8112.webserver.core.exception.ServerErrorException;
import com.jjr8112.webserver.core.exception.base.ServletException;
import com.jjr8112.webserver.core.network.dispacher.AbstractDispatcher;
import com.jjr8112.webserver.core.network.handler.nio.NioRequestHandler;
import com.jjr8112.webserver.core.network.wrapper.SocketWrapper;
import com.jjr8112.webserver.core.network.wrapper.nio.NioSocketWrapper;
import com.jjr8112.webserver.core.request.Request;
import com.jjr8112.webserver.core.response.Response;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

@Data
@Slf4j
public class NioDispatcher extends AbstractDispatcher {
    /**
     * 分发请求，注意IO读取必须放在IO线程中进行，不能放到线程池中，否则会出现多个线程同时读同一个socket数据的情况
     * 1、读取数据
     * 2、构造request，response
     * 3、将业务放入到线程池中处理
     * @param socketWrapper
     */
    @Override
    public void doDispatch(SocketWrapper socketWrapper) {
        NioSocketWrapper nioSocketWrapper = (NioSocketWrapper) socketWrapper;
        log.info("已经将请求放入worker线程池中");
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        log.info("开始读取Request");
        Request request = null;
        Response response = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (nioSocketWrapper.getSocketChannel().read(buffer) > 0) {
                buffer.flip();
                baos.write(buffer.array());
            }
            baos.close();
            request = new Request(baos.toByteArray());
            response = new Response();
            // 以下方法参数为初始化的 NioequestHandler对象
            pool.execute(new NioRequestHandler(nioSocketWrapper, servletContext, exceptionHandler, resourceHandler, request, response));
        } catch (IOException e) {
            e.printStackTrace();
            exceptionHandler.handle(new ServerErrorException(), response, nioSocketWrapper);
        } catch (ServletException e) {
            exceptionHandler.handle(e, response, nioSocketWrapper);
        }
    }
}
