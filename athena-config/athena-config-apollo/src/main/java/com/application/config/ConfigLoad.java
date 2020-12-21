package com.application.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.FileInputStream;
import java.util.Properties;

@Slf4j
public class ConfigLoad {

    private static Properties PROPERTIES = null;

    public static Properties load(Class cls) {
        if (PROPERTIES != null) {
            return PROPERTIES;
        } else {
            String env = getCurrentEnv();
            String appFile = "application-" + env.toLowerCase() + ".properties";
            PROPERTIES = new Properties();

            try {
                PROPERTIES.load(cls.getClassLoader().getResourceAsStream(appFile));
            } catch (Exception var4) {
                throw new RuntimeException(var4);
            }

            return PROPERTIES;
        }
    }

    public static String getCurrentEnv() {
        String env = System.getenv("SERVICE_ENV");
        return StringUtils.isBlank(env) ? "dev" : env;
    }

}
