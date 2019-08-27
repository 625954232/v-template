package com.join.template.core.util;

import java.io.*;
import java.net.URL;

public class ResourceFind {
    private ClassLoader classLoader;
    private String userDir;

    public ResourceFind() {
        this.classLoader = ClassUtil.getClassLoader();
        this.userDir = System.getProperty("user.dir");
    }


    public ResourceInfo getURL(String fileName) throws IOException {
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            resource = ResourceFind.class.getResource(fileName);
        }
        if (resource == null) {
            String psth = userDir + File.separator + fileName;
            resource = new URL(psth);
        }
        return new ResourceInfo(resource);
    }

    public String getUserDir() {
        return userDir;
    }


    public static class ResourceInfo {
        private URL source;
        private String name;
        private String path;
        private InputStream io;

        public ResourceInfo(URL source) throws IOException {
            this.source = source;
            this.path = source.getPath();
            this.name = source.getPath().substring(this.path.lastIndexOf("/") + 1, this.path.length());
            this.io = source.openStream();
        }

        public URL getSource() {
            return source;
        }

        public String getName() {
            return name;
        }

        public String getPath() {
            return path;
        }

        public InputStream getIO() {
            return io;
        }
    }


}
