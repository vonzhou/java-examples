package com.vonzhou.learn.config;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

/**
 * 注意是运行时，所以是使用的文件是 target/classes中的配置文件，最终也是更新的那里
 * @version 2017/3/15.
 */
public class SaveXmlFile {
    public static void main(String[] args) {
        Configurations configs = new Configurations();
        try {
            // obtain the configuration
            FileBasedConfigurationBuilder<XMLConfiguration> builder = configs.xmlBuilder("paths.xml");
            XMLConfiguration config = builder.getConfiguration();

            // update property
            config.addProperty("newProperty", "newValue");

            // save configuration
            builder.save();
        } catch (ConfigurationException cex) {
            // Something went wrong
        }
    }
}
