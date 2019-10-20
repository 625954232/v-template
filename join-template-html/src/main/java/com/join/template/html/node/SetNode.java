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
public class SetNode extends AbstractElement implements NodeVerify, Element {
    //值属性
    private String var;
    //别名属性
    private String name;



    @Override
    public void readAttributes() {
        this.var = this.getAttribute(configuration.getAttVar());
        this.name = this.getAttribute(configuration.getAttName());
    }


    @Override
    public void verify() {
        if (!attributes.containsKey(configuration.getAttVar())) {
            throw new TemplateException("请求设置你要存放的值（" + configuration.getAttVar() + "）：" + original);
        }
        if (!attributes.containsKey(configuration.getAttName())) {
            throw new TemplateException("请求设置你要存放值的别名（" + configuration.getAttItem() + "）：" + original);
        }
    }

}
