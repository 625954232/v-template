package com.join.template.listener.parser;

import com.join.template.configuration.Configuration;
import com.join.template.context.Content;
import com.join.template.factory.JoinFactory;
import com.join.template.listener.ParserListener;
import com.join.template.node.Element;

public class SetParserListener implements ParserListener {
    private final JoinFactory joinFactory;
    private final Configuration configuration;

    public SetParserListener(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
        this.configuration = joinFactory.getConfiguration();
    }

    @Override
    public void onParser(Element element, Content context) {
        String var = element.getAttribute(configuration.getAttVar());
        String name = element.getAttribute(configuration.getAttName());
        context.put(name, var);
    }
}
