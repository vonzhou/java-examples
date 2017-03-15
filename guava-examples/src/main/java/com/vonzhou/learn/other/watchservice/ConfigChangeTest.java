package com.vonzhou.learn.other.watchservice;

/**
 * @version 2017/2/9.
 */
public class ConfigChangeTest {
    private static final String FILE_PATH = "D:/a.txt";

    public static void main(String[] args) {
        ConfigurationChangeListner listner = new ConfigurationChangeListner(FILE_PATH);
        try {
            new Thread(listner).start();
            while (true) {
                Thread.sleep(2000l);
                System.out.println(ApplicationConfiguration.getInstance().getConfiguration("test-key"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
