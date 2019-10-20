package com.join.template.core.grammar;

import com.join.template.core.factory.JoinFactory;

import java.util.Map;

public interface GrammarAttr {

    GrammarAttr setJoinFactory(JoinFactory joinFactory);

    /**
     * 搜索表达式属性
     *
     * @param attr 表达式属性
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/26 12:13
     */
    Map<String, String> findAttribute(CharSequence attr);

    /**
     * 生成表达式属性
     *
     * @param attr 表达式属性
     * @return java.lang.String
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/26 12:13
     */
    String genAttribute(Map<String, String> attr);


}
