package com.join.template.core.grammar.handle;


import com.join.template.core.Template;
import com.join.template.core.element.Element;
import com.join.template.core.example.Example;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.listener.ParserListener;
import com.join.template.core.listener.ProcessListener;
import com.join.template.core.process.Process;

import java.util.List;

/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 表达式处理器
 * @date 2019/8/19 11:41
 */
public interface Grammar {
    /**
     * 获取总工厂
     *
     * @param
     * @return com.join.template.core.factory.JoinFactory
     * @author CAOYOU/625954232@qq.com
     * @date 2019/9/9 14:13
     */
    JoinFactory getJoinFactory();

    /**
     * 获取标记
     *
     * @param
     * @return java.lang.String
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:39
     */
    String getTag();

    /**
     * 获取节点类型
     *
     * @param
     * @return java.lang.Integer
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:39
     */
    Integer getNodeType();

    /**
     * 获取处理器
     *
     * @param
     * @return com.join.template.core.process.Process
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:39
     */
    Process getProcess();

    /**
     * 获取语法解释器
     *
     * @param
     * @return com.join.template.core.example.GrammarExpl
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:39
     */
    Example getExample();

    /**
     * 获取解析监听
     *
     * @param
     * @return java.util.List<com.join.template.core.listener.ParserListener>
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:40
     */
    List<ParserListener> getParserListeners();

    /**
     * 获取处理监听
     *
     * @param
     * @return java.util.List<com.join.template.core.listener.ProcessListener>
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:40
     */
    List<ProcessListener> getProcessListeners();

    /**
     * 获取节点建造器
     *
     * @param template
     * @return
     */
    Element createElement(Template template);


}
