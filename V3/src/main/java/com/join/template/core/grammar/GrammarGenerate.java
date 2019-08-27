package com.join.template.core.grammar;

import com.join.template.core.constant.TemplateType;
import com.join.template.core.grammar.generate.AbstractGrammarGenerate;
import com.join.template.core.grammar.generate.GrammarField;
import com.join.template.core.listener.GrammarGenListener;

import java.util.List;
import java.util.Map;

/**
 * @author CAOYOU
 * @Title: 实体类语法生成器
 * @date 2019/8/1915:49
 */
public interface GrammarGenerate<T extends GrammarInfo> {
    /**
     * 设置模版类型
     *
     * @param templateType
     * @return com.join.template.core.grammar.GrammarGenerate
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:37
     */
    GrammarGenerate setTemplateType(TemplateType templateType);

    /**
     * 设置语法信息类
     *
     * @param grammarInfo
     * @return com.join.template.core.explain.GrammarGenerate
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 15:15
     */
    GrammarGenerate setGrammarInfo(Class<? extends GrammarInfo> grammarInfo);

    /**
     * 生成语法生成监听
     *
     * @param grammarGenListener 语法生成监听
     * @return com.join.template.core.grammar.GrammarGenerate
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/21 10:48
     */
    GrammarGenerate setGrammarGenListener(GrammarGenListener grammarGenListener);

    /**
     * 设置语法字段对应信息
     *
     * @param grammarField 语法字段对应信息
     * @return com.join.template.core.grammar.GrammarInfo
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/21 10:48
     */
    GrammarGenerate setGrammarField(GrammarField grammarField);

    /**
     * 根据实体类生成语法（带根节点信息）
     *
     * @param name  别名
     * @param clazz 对象属性
     * @return com.join.template.core.explain.generate.EntityGrammarInfo
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 16:25
     */
    GrammarGenerate generateGrammarRoot(String name, Class clazz);

    /**
     * 根据Map结构生成语法（带根节点信息）
     *
     * @param name 别名
     * @param map  Map结构的字段信息
     * @return com.join.template.core.grammar.GrammarInfo
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/21 10:47
     */
    GrammarGenerate generateGrammarRoot(String name, List<Map> map);

    /**
     * 根据实体类生成语法（不带根节点信息）
     *
     * @param clazz 实体类
     * @return com.join.template.core.grammar.GrammarGenerate
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/21 11:30
     */
    GrammarGenerate generateGrammar(Class clazz);

    /**
     * 根据Map结构生成语法（不带根节点信息）
     *
     * @param maps Map结构的字段信息
     * @return com.join.template.core.grammar.GrammarGenerate
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/21 11:28
     */
    GrammarGenerate generateGrammar(List<Map> maps);

    /**
     * 获取生成完成的语法信息
     *
     * @param
     * @return java.util.List<com.join.template.core.grammar.GrammarInfo>
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/21 11:32
     */
    List<T> getGrammarInfos();


    /**
     * 预览
     *
     * @param text
     * @param previewSize 预览列表的个数
     * @return java.lang.String
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/22 10:01
     */
    String preview(String text, int previewSize);
}
