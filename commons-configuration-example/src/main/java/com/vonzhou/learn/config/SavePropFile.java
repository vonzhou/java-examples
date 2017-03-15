package com.vonzhou.learn.config;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

/**
 * @version 2017/3/15.
 */
public class SavePropFile {
    public static void main(String[] args) {
        Configurations configs = new Configurations();
        try {
            // obtain the configuration
            FileBasedConfigurationBuilder<PropertiesConfiguration> builder = configs.propertiesBuilder("database.properties");
            PropertiesConfiguration config = builder.getConfiguration();

            System.out.println(config.getProperty("database.host"));

            // update property
            config.addProperty("newProperty", "newValue");

            // save configuration
            builder.save();
        } catch (ConfigurationException cex) {
            // Something went wrong
        }
    }
}
