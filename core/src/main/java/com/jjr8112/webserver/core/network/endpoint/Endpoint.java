package com.jjr8112.webserver.core.network.endpoint;

import org.springframework.util.StringUtils;

public abstract class Endpoint {
    /**
     * 启动服务器
     * @param port
     */
    public abstract void start(int port);

    /**
     * 关闭服务器
     */
    public abstract void close();

    /**
     * 根据传入的bio、nio、aio获取相应的Endpoint实例
     * 实际上是一个字符串，保存当前为bio、nio或 aio的连接方式
     * @param connector
     * @return
     */
    public static Endpoint getInstance(String connector) {
        StringBuilder sb = new StringBuilder();
        sb.append("com.jjr8112.webserver.core.network.endpoint")
                .append(".")
                .append(connector)
                .append(".")
                .append(StringUtils.capitalize(connector))  // 第一个字母小写转大写
                .append("Endpoint");
        try {
            return (Endpoint) Class.forName(sb.toString()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException(connector);
    }
}
