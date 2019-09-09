package com.join.template.text.process;

import com.join.template.core.Element;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.process.AbstractProcess;
import com.join.template.core.process.Process;
import com.join.template.core.context.Content;

import java.io.Writer;

public class IfElseProcess extends AbstractProcess implements Process {


    public IfElseProcess(JoinFactory joinFactory) {
        super(joinFactory);
    }

    @Override
    public void process(Element element, Content context, Writer writer) {
        super.process(element, context, writer);
    }


}