package com.vonzhou.learn.config;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.reloading.PeriodicReloadingTrigger;

/**
 * @version 2017/3/15.
 */
public class FileBasedReloading2 {
    public static void main(String[] args) throws Exception {
        Parameters params = new Parameters();

        String fileName = FileBasedReloading2.class.getClassLoader().getResource("config.properties").getFile();
        System.out.println(fileName);

        ReloadingFileBasedConfigurationBuilder<FileBasedConfiguration> builder = new ReloadingFileBasedConfigurationBuilder<FileBasedConfiguration>(
                        PropertiesConfiguration.class).configure(params.fileBased().setFileName(fileName));

        PeriodicReloadingTrigger trigger = new PeriodicReloadingTrigger(builder.getReloadingController(), null, 10,
                        TimeUnit.SECONDS);
        trigger.start();

        while (true) {
            /**
             * 需要重新从 builder 中获取 Configuration 才会得到重新加载的配置项
             */
            FileBasedConfiguration configuration = builder.getConfiguration();
            System.out.println(configuration.getProperty("key1"));
            Thread.sleep(2000);
        }

    }
}
