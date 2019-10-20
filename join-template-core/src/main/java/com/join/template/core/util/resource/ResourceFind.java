package com.join.template.core.util.resource;

import com.join.template.core.util.ClassUtil;

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





}
