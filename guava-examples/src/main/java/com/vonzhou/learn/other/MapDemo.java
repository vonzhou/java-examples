package com.vonzhou.learn.other;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * @version 2017/2/20.
 */
public class MapDemo {
    public static void main(String[] args) {
        List<String> allVersions = Arrays.asList("1.0", "2.0");
        List<Version> list = new ArrayList<>();

        for (String ver : allVersions) {
            Version version = new Version();
            version.setVersion(ver);
            list.add(version);
        }



        System.out.println(list);
    }


    public static class Version {
        private String version;
        private boolean top3;

        public boolean isTop3() {
            return top3;
        }

        public void setTop3(boolean top3) {
            this.top3 = top3;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
