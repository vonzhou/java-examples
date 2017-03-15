package com.vonzhou.learn.config;

import java.nio.file.*;
import java.util.List;

/**
 * @version 2017/3/15.
 */
public class FileBasedReloading3 {
    public static void main(String[] args) throws Exception {

        /**
         * 只能对目录注册 WatchService，如果是文件会 java.nio.file.NotDirectoryException
         */
        Path propPath = Paths.get("D:\\");
        String propFileName = "config.properties";

        WatchService watcher = propPath.getFileSystem().newWatchService();
        propPath.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);

        while (true) {
            WatchKey watchKey = watcher.take();
            List<WatchEvent<?>> events = watchKey.pollEvents();

            for (WatchEvent event : events) {
                if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                    String changed = event.context().toString();
                    if (propFileName.equals(changed)) {
                        System.out.println("配置文件发生了改变，加载之！");
                        // 重新读取文件
                    }
                }
            }

            Thread.sleep(2000);
        }

    }
}
