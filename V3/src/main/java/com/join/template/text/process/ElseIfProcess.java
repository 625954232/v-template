package com.join.template.text.process;

import com.join.template.core.Element;
import com.join.template.core.Grammar;
import com.join.template.core.Process;
import com.join.template.core.context.Content;
import com.join.template.core.factory.JoinFactory;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class ElseIfProcess extends AbstractProcess implements Process, Grammar {

    public ElseIfProcess(JoinFactory joinFactory) {
        super(joinFactory);
    }

    @Override
    public void process(Element element, Content context, Writer writer) {
        super.process(element, context, writer);
    }

    @Override
    public Map<String, String> getGrammarAttr() {
        Map<String, String> fields = new HashMap<>();
        fields.put(configuration.getAttrText(), "判断条件");
        return fields;
    }
}
