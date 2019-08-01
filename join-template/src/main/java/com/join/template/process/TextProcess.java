package com.join.template.process;

import com.join.template.context.Content;
import com.join.template.factory.JoinFactory;
import com.join.template.node.Element;

import java.io.IOException;
import java.io.Writer;

public class TextProcess extends AbstractProcess {


    public TextProcess(JoinFactory configuration) {
        super(configuration);
    }

    @Override
    public void process(Element root, Content context, Writer writer) {
        try {
            writer.write(root.getBody());
        } catch (IOException e) {
            throw new IllegalArgumentException("语法转换失败：", e);
        }
    }
}
