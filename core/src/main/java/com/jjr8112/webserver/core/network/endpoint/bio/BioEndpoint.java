package com.jjr8112.webserver.core.network.endpoint.bio;

import com.jjr8112.webserver.core.network.connector.bio.BioAcceptor;
import com.jjr8112.webserver.core.network.dispacher.bio.BioDispatcher;
import com.jjr8112.webserver.core.network.endpoint.Endpoint;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class BioEndpoint extends Endpoint {
    private ServerSocket server;
    private BioAcceptor acceptor;
    private BioDispatcher dispatcher;
    private volatile boolean isRunning = true;

    @Override
    public void start(int port) {
        try {
            dispatcher = new BioDispatcher();
            server = new ServerSocket(port);
            initAcceptor();
            log.info("服务器启动");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("初始化服务器失败");
            close();
        }
    }

    private void initAcceptor() {
        acceptor = new BioAcceptor(this, dispatcher);
        Thread t = new Thread(acceptor, "bio-acceptor");
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void close() {
        isRunning = false;
        dispatcher.shutdown();
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket accept() throws IOException {
        return server.accept();
    }

    public boolean isRunning() {
        return isRunning;
    }
}
