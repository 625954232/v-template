package com.join.template.html.process;

import com.join.template.core.element.Element;
import com.join.template.core.process.AbstractProcess;
import com.join.template.core.process.Process;
import com.join.template.core.context.Content;
import com.join.template.html.node.TextNode;

import java.io.IOException;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcess extends AbstractProcess<TextNode> implements Process<TextNode> {


    @Override
    public void process(TextNode element, Content context, Writer writer) {
        super.process(element, context, writer);
        try {
            String pattern = "<(\\w+)></\\1>";
            Matcher matcher = Pattern.compile(pattern).matcher(element.getOriginal());
            String replace = matcher.replaceAll("");
            writer.write(replace);
        } catch (IOException e) {
            throw new IllegalArgumentException("语法转换失败：", e);
        }
    }


}
