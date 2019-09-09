package com.join.template.text.process;

import com.join.template.core.Element;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.process.AbstractProcess;
import com.join.template.core.process.Process;
import com.join.template.core.Template;
import com.join.template.core.context.Content;
import org.apache.commons.lang.StringUtils;

import java.io.Writer;

public class IncludeProcess extends AbstractProcess implements Process {

    @Override
    public void process(Element element, Content context, Writer writer) {
        super.process(element, context, writer);
        String file = element.getAttribute(joinFactory.getConfiguration().getAttFile());
        if (StringUtils.isBlank(file)) {
            return;
        }
        Template template = joinFactory.getTemplate(file);
        template.putContext(context);
        template.process(writer);
    }


}
