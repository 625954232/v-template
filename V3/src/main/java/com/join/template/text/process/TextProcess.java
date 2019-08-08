package com.join.template.text.process;

import com.join.template.core.Element;
import com.join.template.core.context.Content;
import com.join.template.core.factory.JoinFactory;

import java.io.IOException;
import java.io.Writer;

public class TextProcess extends AbstractProcess {


    public TextProcess(JoinFactory configuration) {
        super(configuration);
    }

    @Override
    public void process(Element element, Content context, Writer writer) {
        super.process(element, context, writer);
        try {
            writer.write(element.getOriginal());
        } catch (IOException e) {
            throw new IllegalArgumentException("语法转换失败：", e);
        }
    }
}
