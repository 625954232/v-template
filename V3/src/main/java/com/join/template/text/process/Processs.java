package com.join.template.text.process;

import com.join.template.core.Element;
import com.join.template.core.Process;
import com.join.template.core.entity.ExprConfig;
import com.join.template.core.context.Content;
import com.join.template.core.factory.JoinFactory;

import java.io.Writer;
import java.util.List;


public class Processs extends AbstractProcess implements Process {


    @Override
    public void process(Element element, Content context, Writer writer) {
        super.process(element, context, writer);
        List<Element> childs = element.getChilds();
        for (int i = 0; i < childs.size(); i++) {
            Element child = childs.get(i);
            ExprConfig exprConfig = joinFactory.getExprConfigByType(child.getNodeType());
            exprConfig.getProcess().process(child, context, writer);
        }
    }
}
