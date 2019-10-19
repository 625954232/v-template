package com.join.template.html.node;

import com.join.template.core.Template;
import com.join.template.core.element.AbstractElement;
import com.join.template.core.element.Element;
import com.join.template.core.verify.NodeVerify;
import com.join.template.core.verify.TemplateException;
import lombok.Getter;

/**
 * @author CAOYOU
 * @Title: ListNode
 * @date 2019/10/1815:52
 */
@Getter
public class ElseNode extends AbstractElement implements NodeVerify, Element {
    //值属性
    private String var;
    //别名属性
    private String name;
    //单项别名属性
    private String item;
    //语句开始属性
    private String open;
    //索引属性
    private String index;
    //语句结束属性
    private String close;


    @Override
    public void readAttributes() {
        this.var = this.getAttribute(configuration.getAttVar());
        this.name = this.getAttribute(configuration.getAttName());
        this.item = this.getAttribute(configuration.getAttItem());
        this.open = this.getAttribute(configuration.getAttOpen());
        this.index = this.getAttribute(configuration.getAttIndex());
        this.close = this.getAttribute(configuration.getAttClose());
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
