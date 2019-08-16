package com.join.template.text.process;

import com.join.template.core.Element;
import com.join.template.core.GrammarExpl;
import com.join.template.core.Process;
import com.join.template.core.context.Content;
import com.join.template.core.factory.JoinFactory;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

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
