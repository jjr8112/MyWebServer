package com.jjr8112.webserver.core;

import com.jjr8112.webserver.core.network.endpoint.Endpoint;
import com.jjr8112.webserver.core.util.PropertyUtil;

import java.util.Scanner;

public class BootStrap {

    /**
     * 服务器启动入口
     * 用户程序与服务器的接口
     */
    public static void run() {
        String port = PropertyUtil.getProperty("server.port");                  // 用于保存端口信息
        if(port == null) {
            throw new IllegalArgumentException("server.port 不存在");
        }
        String connector = PropertyUtil.getProperty("server.connector");        // 用于保存连接方式
        if(connector == null || (!connector.equalsIgnoreCase("bio")
                && !connector.equalsIgnoreCase("nio")
                && !connector.equalsIgnoreCase("aio")) ) {
            throw new IllegalArgumentException("server.network 不存在或不符合规范");
        }
        Endpoint server = Endpoint.getInstance(connector);      // 创建应用实例
        server.start(Integer.parseInt(port));                   // 启动服务器
        Scanner scanner = new Scanner(System.in);
        String order;
        while (scanner.hasNext()) {
            order = scanner.next();
            if (order.equals("EXIT")) {
                server.close();
                System.exit(0);
            }
        }
    }
}

