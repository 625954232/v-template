package com.join.template.html.process;

import com.join.template.core.process.AbstractProcess;
import com.join.template.core.process.Process;
import com.join.template.core.context.Content;
import com.join.template.html.node.TextNodeExample;

import java.io.IOException;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcess extends AbstractProcess<TextNodeExample> implements Process<TextNodeExample> {


    @Override
    public void process(TextNodeExample element, Content context, Writer writer) {
        super.process(element, context, writer);
        try {
            writer.write(element.getOriginal());
        } catch (IOException e) {
            throw new IllegalArgumentException("语法转换失败：", e);
        }
    }


}
