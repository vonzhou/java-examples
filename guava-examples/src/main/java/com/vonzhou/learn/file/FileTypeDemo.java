package com.vonzhou.learn.file;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 判断文件类型的方法:文件开头的魔数
 *
 * @version 2017/2/14.
 */
public class FileTypeDemo {
    public static void main(String[] args) throws Exception {
        String filePath = "C:\\Users\\hzfengzhou\\Desktop\\1.jpeg";
        FileType ft = getType(filePath);
        System.out.println(ft.getValue());

        String s = Files.probeContentType(Paths.get(filePath));
        System.out.println(s);
    }

    public static String getFileHeader(String filePath) throws Exception {
        byte[] b = new byte[28];
        InputStream inputStream = new FileInputStream(filePath);
        inputStream.read(b, 0, 28);
        inputStream.close();

        return bytesToHex(b);
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static FileType getType(String filePath) throws Exception {
        String fileHead = getFileHeader(filePath);
        System.out.println(fileHead);
        if (fileHead == null || fileHead.length() == 0)
            return null;
        fileHead = fileHead.toUpperCase();
        FileType[] fileTypes = FileType.values();
        for (FileType ft : fileTypes) {
            if (fileHead.startsWith(ft.getValue())) {
                return ft;
            }
        }
        return null;
    }

    public static enum FileType {
        JPEG("FFD8FF"),
        PDF("255044462D312E");

        FileType(String value) {
            this.value = value;
        }

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
