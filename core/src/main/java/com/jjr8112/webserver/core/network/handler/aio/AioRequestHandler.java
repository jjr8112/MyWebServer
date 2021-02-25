package com.jjr8112.webserver.core.network.handler.aio;

import com.jjr8112.webserver.core.context.ServletContext;
import com.jjr8112.webserver.core.context.WebApplication;
import com.jjr8112.webserver.core.exception.FilterNotFoundException;
import com.jjr8112.webserver.core.exception.ServletNotFoundException;
import com.jjr8112.webserver.core.exception.handler.ExceptionHandler;
import com.jjr8112.webserver.core.network.handler.AbstractRequestHandler;
import com.jjr8112.webserver.core.network.wrapper.SocketWrapper;
import com.jjr8112.webserver.core.network.wrapper.aio.AioSocketWrapper;
import com.jjr8112.webserver.core.request.Request;
import com.jjr8112.webserver.core.resource.ResourceHandler;
import com.jjr8112.webserver.core.response.Response;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.TimeUnit;

@Setter
@Getter
@Slf4j
public class AioRequestHandler extends AbstractRequestHandler {
    private CompletionHandler readHandler;

    public AioRequestHandler(SocketWrapper socketWrapper, ServletContext servletContext, ExceptionHandler exceptionHandler, ResourceHandler resourceHandler, CompletionHandler readHandler, Request request, Response response) throws ServletNotFoundException, FilterNotFoundException {
        super(socketWrapper, servletContext, exceptionHandler, resourceHandler,request,response);
        this.readHandler = readHandler;
    }

    /**
     * 写回后重新调用readHandler，进行读取（猜测AIO也是保活的）
     */
    @Override
    public void flushResponse() {
        isFinished = true;
        ByteBuffer[] responseData = response.getResponseByteBuffer();
        AioSocketWrapper aioSocketWrapper = (AioSocketWrapper) socketWrapper;
        AsynchronousSocketChannel socketChannel = aioSocketWrapper.getSocketChannel();
        socketChannel.write(responseData, 0, 2, 0L, TimeUnit.MILLISECONDS, null, new CompletionHandler<Long, Object>() {

            @Override
            public void completed(Long result, Object attachment) {
                log.info("写入完毕...");
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                socketChannel.read(byteBuffer, byteBuffer, readHandler);
            }

            @Override
            public void failed(Throwable e, Object attachment) {
                log.info("写入失败...");
                e.printStackTrace();
            }
        });
        WebApplication.getServletContext().afterRequestDestroyed(request);
    }
}
