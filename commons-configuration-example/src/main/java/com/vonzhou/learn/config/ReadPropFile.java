package com.vonzhou.learn.config;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

/**
 * @version 2017/3/15.
 */
public class ReadPropFile {
    public static void main(String[] args) {
        Configurations configs = new Configurations();
        try {
            Configuration config = configs.properties(new File("database.properties"));
            // access configuration properties
            String dbHost = config.getString("database.host");
            int dbPort = config.getInt("database.port");
            String dbUser = config.getString("database.user");
            String dbPassword = config.getString("database.password", "secret"); // provide a
            long dbTimeout = config.getLong("database.timeout");


            System.out.println(dbHost);
        } catch (ConfigurationException cex) {
            // Something went wrong
        }
    }
}
