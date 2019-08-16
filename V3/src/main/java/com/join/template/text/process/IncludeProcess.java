package com.join.template.text.process;

import com.join.template.core.Element;
import com.join.template.core.Grammar;
import com.join.template.core.Template;
import com.join.template.core.context.Content;
import com.join.template.core.factory.JoinFactory;
import org.apache.commons.lang.StringUtils;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class IncludeProcess extends AbstractProcess implements Grammar {


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

    @Override
    public Map<String, String> getGrammarAttr() {
        Map<String, String> fields = new HashMap<>();
        fields.put(configuration.getAttFile(), "模板名称");
        return fields;
    }
}
