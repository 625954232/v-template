package com.join.template.context;


public interface Content {
    /**
     * 获取值
     *
     * @param K 键
     * @return
     */
    Object get(String K);

    /**
     * 写入值
     *
     * @param K 键
     * @param V 值
     */
    void put(String K, Object V);

    /**
     * 是否包含某个键
     *
     * @param K 键
     * @return
     */
    boolean hasKey(String K);

    /**
     * 移除某个键
     *
     * @param K 键
     */
    void remove(String K);
}
