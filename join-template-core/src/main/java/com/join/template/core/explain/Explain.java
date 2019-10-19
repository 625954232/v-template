package com.join.template.core.explain;


import com.join.template.core.element.Element;
import com.join.template.core.constant.TemplateType;
import com.join.template.core.expression.ExprHandle;
import com.join.template.core.grammar.GrammarInfo;
import com.join.template.core.grammar.generate.GrammarField;
import com.join.template.core.type.TypeInfo;

import java.util.Map;

/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 语法属性解释及效验
 * @date 2019/8/19 12:18
 */
public interface Explain {
    /**
     * 设置表达式处理器
     *
     * @param exprHandle
     * @return com.join.template.core.explain.Explain
     * @author CAOYOU/625954232@qq.com
     * @date 2019/9/9 14:06
     */
    void setExprHandle(ExprHandle exprHandle);



    /**
     * 获取语法解释
     *
     * @param
     * @return
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:54
     */
    String getGrammarExplain();


    /**
     * 根据Map生成语法
     *
     * @param templateType
     * @param grammarInfo
     * @param map
     * @param field
     * @return java.lang.String
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 10:35
     */
    String genGrammar(TemplateType templateType, GrammarInfo grammarInfo, Map map, GrammarField field);

    /**
     * 根据实体、字段、方法等信息生成语法
     *
     * @param templateType
     * @param grammarInfo
     * @param typeInfo
     * @param grammarField
     * @return java.lang.String
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/21 15:59
     */
    String genGrammar(TemplateType templateType, GrammarInfo grammarInfo, TypeInfo typeInfo, GrammarField grammarField);


}
