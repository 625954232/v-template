package com.join.template.html.process;

import com.join.template.core.element.Element;
import com.join.template.core.process.AbstractProcess;
import com.join.template.core.process.Process;
import com.join.template.core.Template;
import com.join.template.core.context.Content;
import com.join.template.html.node.IncludeNode;
import org.apache.commons.lang.StringUtils;

import java.io.Writer;

public class IncludeProcess extends AbstractProcess<IncludeNode> implements Process<IncludeNode> {

    @Override
    public void process(IncludeNode element, Content context, Writer writer) {
        super.process(element, context, writer);

        if (StringUtils.isBlank(element.getFile())) {
            return;
        }
        Template template = joinFactory.getTemplate(element.getFile());
        template.putContext(context);
        template.process(writer);
    }


}
