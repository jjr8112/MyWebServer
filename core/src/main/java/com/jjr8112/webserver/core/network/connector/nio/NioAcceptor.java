package com.jjr8112.webserver.core.network.connector.nio;

import com.jjr8112.webserver.core.network.endpoint.nio.NioEndpoint;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * Nio 请求接收器
 */
@Slf4j
public class NioAcceptor implements Runnable {
    private NioEndpoint nioEndpoint;

    public NioAcceptor(NioEndpoint nioEndpoint) {
        this.nioEndpoint = nioEndpoint;
    }

    @Override
    public void run() {
        log.info("{} 开始监听",Thread.currentThread().getName());
        while (nioEndpoint.isRunning()) {
            SocketChannel client;
            try {
                client = nioEndpoint.accept();          // 接收客户端连接
                if(client == null){
                    continue;
                }
                client.configureBlocking(false);        // 非阻塞
                log.info("Acceptor接收到连接请求 {}",client);
                nioEndpoint.registerToPoller(client);   // 当前客户端 socket实例加入到 poller的管理中
                log.info("socketWrapper:{}", client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
