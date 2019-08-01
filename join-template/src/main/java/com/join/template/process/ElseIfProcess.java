package com.join.template.process;

import com.join.template.context.Content;
import com.join.template.factory.JoinFactory;
import com.join.template.node.Element;

import java.io.Writer;

public class ElseIfProcess extends AbstractProcess implements Process {

    public ElseIfProcess(JoinFactory joinFactory) {
        super(joinFactory);
    }

    @Override
    public void process(Element element, Content context, Writer writer) {
        super.process(element, context, writer);
    }
}
