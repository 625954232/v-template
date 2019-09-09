package com.join.template.core.listener;

import com.join.template.core.Element;
import com.join.template.core.context.Content;
import com.join.template.core.expression.ExprHandle;
import com.join.template.core.expression.TemplateExprHandle;

import java.io.Writer;

/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 处理监听器
 * @date 2019/8/19 11:52
 */
public interface ProcessListener {
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
     * 处理监听
     *
     * @param element 节点
     * @param context 参数
     * @param writer  写入
     * @return void
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:51
     */
    void onProcess(Element element, Content context, Writer writer);

}
