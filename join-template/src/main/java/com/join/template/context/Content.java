package com.join.template.context;

/**
 * Created by wangqi on 2016/12/7.
 * inface of Context.
 */

public interface Content {
    Object get(String K);

    void put(String K, Object V);

    boolean hasKey(String K);

    void remove(String K);
}
