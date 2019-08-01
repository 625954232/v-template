package com.join.template.core;

import com.join.template.context.Context;
import com.join.template.factory.JoinFactory;
import com.join.template.node.Element;
import com.join.template.parser.Parser;
import com.join.template.reader.Reader;

import java.io.Writer;

public interface Template {
    /**
     * 模版初始化
     * @param name
     * @param text
     * @return
     */
    Template init(String name, String text);


    Template putValue(String name, Object value);

    Template putContext(Context context);

    Template process(Writer writer);

    JoinFactory getJoinFactory();

    Context getContext();

    Element getRoot();

    String process();

    String getTemplateContent();

    String getTemplateName();

    Parser getParser();

    Reader getReader();

    int getLineSize();
}
