package com.join.template.core.context;

/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 模版参数
 * @date 2019/8/19 11:39
 */
public interface Content {
    /**
     * 获取值
     *
     * @param K 键
     * @return java.lang.Object
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:37
     */
    Object get(String K);

    /**
     * 写入值
     *
     * @param K 键
     * @param V 值
     * @return void
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:38
     */
    void put(String K, Object V);


    /**
     * 是否包含某个键
     *
     * @param K 键
     * @return boolean
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:38
     */
    boolean hasKey(String K);


    /**
     * 移除某个键
     *
     * @param K 键
     * @return void
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:38
     */
    void remove(String K);
}
