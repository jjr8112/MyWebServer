package com.jjr8112.webserver.core.util;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class PropertyUtil {
    private static Properties props;

    static {
        loadProps();
    }

    private synchronized static void loadProps() {
        log.info("开始加载properties文件内容.......");
        props = new Properties();
        InputStream in = null;
        try {
            in = PropertyUtil.class                                       // 获取参数文件对象
                    .getClassLoader()                                     // 获取类加载器
                    .getResourceAsStream("server.properties");     // 用 server.properties查找资源
            props.load(in);
        } catch (FileNotFoundException e) {
            log.error("rpc.properties文件未找到");
        } catch (IOException e) {
            log.error("出现IOException");
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                log.error("rpc.properties文件流关闭出现异常");
            }
        }
        log.info("加载properties文件内容完成...........");
        log.info("properties文件内容：" + props);
    }

    // 获取properties文件中的各个属性
    public static String getProperty(String key) {
        if (null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if (null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }
}
