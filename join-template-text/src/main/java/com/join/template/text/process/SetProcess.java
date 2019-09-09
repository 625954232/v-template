package com.join.template.text.process;

import com.join.template.core.Element;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.process.AbstractProcess;
import com.join.template.core.process.Process;
import com.join.template.core.context.Content;

import java.io.Writer;

public class SetProcess extends AbstractProcess implements Process {


    @Override
    public void process(Element element, Content context, Writer writer) {
        super.process(element, context, writer);
        if (context == null) {
            return;
        }
        String var = element.getAttribute(configuration.getAttVar());
        String name = element.getAttribute(configuration.getAttName());
        context.put(name, var);
    }


}
