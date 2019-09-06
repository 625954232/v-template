package com.join.template.core.util;

import java.io.*;
import java.net.URL;

public class IOUtil {

    public static byte[] toByte(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            copyByte(inputStream, outputStream);
        } catch (IOException e) {
        } finally {
            close(inputStream);
            close(outputStream);
        }
        return outputStream.toByteArray();
    }


    public static String toString(InputStream inputStream) {
        byte[] bytes = toByte(inputStream);
        return new String(bytes);
    }


    public static void copyByte(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }
    }

    public static String toString(File file) {
        byte[] bytes = new byte[1024];
        try {
            bytes = toByte(new FileInputStream(file));
        } catch (FileNotFoundException e) {
        }
        return new String(bytes);
    }

    public static String toString(URL url) {
        byte[] bytes = new byte[1024];
        try {
            bytes = toByte(url.openStream());
        } catch (IOException e) {
        }
        return new String(bytes);
    }

    public static void close(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
        }
    }
}
