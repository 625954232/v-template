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
public class IFNode extends AbstractElement implements NodeVerify, Element {
    //值属性
    private String text;


    @Override
    public void readAttributes() {
        this.text = this.getAttribute(configuration.getAttrText());
    }


    @Override
    public void verify() {
        if (!attributes.containsKey(configuration.getAttrText())) {
            throw new TemplateException("请设置判断条件（" + configuration.getAttrText() + "）：" + original);
        }
    }

}
