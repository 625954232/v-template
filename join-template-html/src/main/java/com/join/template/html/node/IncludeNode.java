package com.join.template.html.node;

import com.join.template.core.Template;
import com.join.template.core.element.AbstractElement;
import com.join.template.core.element.Element;
import com.join.template.core.verify.NodeVerify;
import com.join.template.core.verify.TemplateException;
import lombok.Data;

@Data
public class IncludeNode extends AbstractElement implements NodeVerify, Element {

    private String file;

    @Override
    public void readAttributes() {
        file = attributes.get(configuration.getAttFile());
    }

    @Override
    public void verify() {
        if (!attributes.containsKey(configuration.getAttFile())) {
            throw new TemplateException("请设置模板名称（" + configuration.getAttFile() + "）：" + original);
        }
    }
}
