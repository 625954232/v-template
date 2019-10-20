package com.join.template.html.process;

import com.join.template.core.element.Element;
import com.join.template.core.expression.ExprHandle;
import com.join.template.core.process.AbstractProcess;
import com.join.template.core.process.Process;
import com.join.template.core.context.Content;
import com.join.template.html.node.RootNode;

import java.io.Writer;
import java.util.List;


public class Processs extends AbstractProcess<RootNode> implements Process<RootNode> {


    @Override
    public void process(RootNode element, Content context, Writer writer) {
        super.process(element, context, writer);
        List<Element> childs = element.getChilds();
        for (int i = 0; i < childs.size(); i++) {
            Element child = childs.get(i);
            ExprHandle exprConfig = joinFactory.getExprHandle(child.getNodeType());
            exprConfig.getProcess().process(child, context, writer);
        }
    }
}
