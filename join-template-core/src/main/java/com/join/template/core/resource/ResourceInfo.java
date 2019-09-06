package com.join.template.core.resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ResourceInfo {
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