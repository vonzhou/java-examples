package com.vonzhou.learn.util;

import java.util.Properties;

/**
 * @version 2016/11/29.
 */
public class EnvironmentUtil {
    private static final String ENV_FILE = "/environment.properties";
    private static String env = "test"; // 环境

    static {
        Properties p = new Properties();
        try {
            p.load(EnvironmentUtil.class.getResourceAsStream(ENV_FILE));
            env = p.getProperty("environment.env").trim();
        } catch (Exception e) {
            System.out.println("加载配置文件失败!" + e);
        }
    }

    public static String getEnv() {
        return env;
    }

}
