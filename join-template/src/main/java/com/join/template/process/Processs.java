package com.join.template.process;

import com.join.template.context.Context;
import com.join.template.factory.JoinFactory;
import com.join.template.node.Element;

import java.io.Writer;
import java.util.List;

public class Processs extends AbstractProcess {


    public Processs(JoinFactory configuration) {
        super(configuration);
    }


    @Override
    public void process(Element element, Context context, Writer writer) {
        List<Element> childs = element.getChilds();
        for (int i = 0; i < childs.size(); i++) {
            Element child = childs.get(i);
            joinFactory.getProcess(child.getNodeType()).process(child, context, writer);
        }
    }
}
