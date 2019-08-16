package com.join.template.text.process;

import com.join.template.core.Element;
import com.join.template.core.process.AbstractProcess;
import com.join.template.core.process.Process;
import com.join.template.core.context.Content;

import java.io.IOException;
import java.io.Writer;

public class TextProcess extends AbstractProcess implements Process {


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
