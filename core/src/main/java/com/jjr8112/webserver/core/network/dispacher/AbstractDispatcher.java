package com.jjr8112.webserver.core.network.dispacher;


import com.jjr8112.webserver.core.context.ServletContext;
import com.jjr8112.webserver.core.context.WebApplication;
import com.jjr8112.webserver.core.exception.handler.ExceptionHandler;
import com.jjr8112.webserver.core.network.wrapper.SocketWrapper;
import com.jjr8112.webserver.core.resource.ResourceHandler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 所有Dispatcher（请求分发器）的父类
 */
public abstract class AbstractDispatcher {
    protected ResourceHandler resourceHandler;
    protected ExceptionHandler exceptionHandler;
    protected ThreadPoolExecutor pool;
    protected ServletContext servletContext;

    public AbstractDispatcher() {
        this.servletContext = WebApplication.getServletContext();       // 获取 servlet
        this.exceptionHandler = new ExceptionHandler();                 // 初始化异常处理器
        this.resourceHandler = new ResourceHandler(exceptionHandler);   // 初始化静态资源处理器
        // 匿名子类的实现
        ThreadFactory threadFactory = new ThreadFactory() {
            private int count;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "Worker Pool-" + count++);
            }
        };
        this.pool = new ThreadPoolExecutor(100, 100, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(200), threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 关闭
     */
    public void shutdown() {
        pool.shutdown();
        servletContext.destroy();
    }

    /**
     * 分发请求
     * @param socketWrapper
     */
    public abstract void doDispatch(SocketWrapper socketWrapper);
}

