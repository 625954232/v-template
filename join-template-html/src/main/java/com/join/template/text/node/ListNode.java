package com.join.template.text.node;

import com.join.template.core.Template;
import com.join.template.core.element.ElementBuilder;
import com.join.template.core.verify.NodeVerify;
import com.join.template.core.verify.TemplateException;

/**
 * @author CAOYOU
 * @Title: ListNode
 * @date 2019/10/1815:52
 */
public class ListNode extends Node implements NodeVerify {
    //值属性
    private String var = "var";
    //别名属性
    private String name = "name";
    //单项别名属性
    private String item = "item";
    //语句开始属性
    private String open = "open";
    //索引属性
    private String index = "index";
    //语句结束属性
    private String close = "close";

    public ListNode(Template template) {
        super(template);
    }

    @Override
    protected void onLoad(String original) {

    }


    @Override
    public void verify() {
        if (!attributes.containsKey(configuration.getAttVar())) {
            throw new TemplateException("请求设置循环条件-数据来源别名（" + configuration.getAttVar() + "）：" + original);
        }
        if (!attributes.containsKey(configuration.getAttItem())) {
            throw new TemplateException("请求设置循环条件-单项定义别名（" + configuration.getAttItem() + "）：" + original);
        }
    }
}
