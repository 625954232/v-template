package com.join.template.text.process;

import com.join.template.core.Element;
import com.join.template.core.context.Content;
import com.join.template.core.factory.JoinFactory;

import java.io.Writer;

public class SetProcess extends AbstractProcess {


    public SetProcess(JoinFactory joinFactory) {
        super(joinFactory);
    }

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
