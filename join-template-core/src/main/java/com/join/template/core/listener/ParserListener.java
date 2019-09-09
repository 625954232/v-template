package com.join.template.core.listener;

import com.join.template.core.Element;
import com.join.template.core.expression.ExprHandle;
import com.join.template.core.expression.TemplateExprHandle;

/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 解析监听器
 * @date 2019/8/19 11:58
 */
public interface ParserListener {
    /**
     * 设置表达式处理器
     *
     * @param exprHandle
     * @return void
     * @author CAOYOU/625954232@qq.com
     * @date 2019/9/9 14:07
     */
    void setExprHandle(ExprHandle exprHandle);

    /**
     * 解析监听
     *
     * @param element 节点
     * @return void
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:51
     */
    void onParser(Element element);


}
