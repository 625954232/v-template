package com.join.template.text.process;

import com.join.template.core.context.Content;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.Element;

import java.io.Writer;

import com.join.template.core.Process;

public class ElseIfProcess extends AbstractProcess implements Process {

    public ElseIfProcess(JoinFactory joinFactory) {
        super(joinFactory);
    }

    @Override
    public void process(Element element, Content context, Writer writer) {
        super.process(element, context, writer);
    }
}
