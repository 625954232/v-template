package com.join.template.context;


public interface Content {

    Object get(String K);

    void put(String K, Object V);

    boolean hasKey(String K);

    void remove(String K);
}
