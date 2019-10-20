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
public class VarNode extends AbstractElement implements NodeVerify, Element {


    private String var;

    @Override
    public void readAttributes() {
        String replace = this.original.replace(this.configuration.getVarTagStart(), "");
        this.var = replace.replaceFirst(this.configuration.getVarTagEnd(), "");
        this.attributes.put(this.configuration.getAttVar(), this.var);
    }


    @Override
    public void verify() {
    }

}
