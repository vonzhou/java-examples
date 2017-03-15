package com.vonzhou.learn.config;

import java.io.File;
import java.util.List;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

/**
 * @version 2017/3/15.
 */
public class ReadXmlFile {
    public static void main(String[] args) {
        Configurations configs = new Configurations();
        try {
            XMLConfiguration config = configs.xml("paths.xml");
            // access configuration properties

            String stage = config.getString("processing[@stage]");
            List<String> paths = config.getList(String.class, "processing.paths.path");
            System.out.println(stage);
            System.out.println(paths);

            String secondPath = config.getString("processing.paths.path(1)");
            System.out.println(secondPath);
        } catch (ConfigurationException cex) {
            // Something went wrong
        }
    }
}
