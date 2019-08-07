package com.join.template.process;

import com.join.template.context.Content;
import com.join.template.core.Template;
import com.join.template.factory.JoinFactory;
import com.join.template.node.Element;
import org.apache.commons.lang.StringUtils;

import java.io.Writer;

public class IncludeProcess extends AbstractProcess {


    public IncludeProcess(JoinFactory joinFactory) {
        super(joinFactory);
    }

    @Override
    public void process(Element element, Content context, Writer writer) {
        super.process(element, context, writer);
        String file = element.getAttribute(joinFactory.getConfiguration().getAttFile());
        if (StringUtils.isBlank(file)) {
            return;
        }
        Template template = joinFactory.putTemplate(file);
        template.putContext(context);
        template.process(writer);
    }
}