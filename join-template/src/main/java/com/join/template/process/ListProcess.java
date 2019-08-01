package com.join.template.process;

import com.join.template.context.Content;
import com.join.template.factory.JoinFactory;
import com.join.template.node.Element;
import com.join.template.verify.TemplateException;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.List;

public class ListProcess extends AbstractProcess     {
    public ListProcess(JoinFactory joinFactory) {
        super(joinFactory);
    }


    @Override
    public void process(Element element, Content context, Writer writer) {
        try {
            super.process(element, context, writer);
            String var = element.getAttribute(configuration.getAttVar());
            String item = element.getAttribute(configuration.getAttItem());
            String open = element.getAttribute(configuration.getAttOpen());
            String close = element.getAttribute(configuration.getAttClose());
            String separator = element.getAttribute(configuration.getAttSseparator());
            String index = element.getAttribute(configuration.getAttIndex());
            if (!StringUtils.isBlank(open)) {
                writer.write(open);
            }
            if (context == null) {
                return;
            }
            Object value = context.get(var);
            if (value == null) {
                throw new IllegalArgumentException("循环条件没有默认值（var）：" + element.getHead());
            }
            if (value instanceof List) {
                Collection list = (Collection) value;
                int i = 0;
                for (Object o : list) {
                    if (i > 0 && !StringUtils.isBlank(separator)) {
                        writer.write(separator);
                    }
                    if (!StringUtils.isBlank(index)) {
                        context.put(index, i);
                    }
                    context.put(item, o);
                    for (Element child : element.getChilds()) {
                        Process process = joinFactory.getProcess(child.getNodeType());
                        process.process(child, context, writer);
                    }
                    i++;
                }
            }
            if (!StringUtils.isBlank(close)) {
                writer.write(close);
            }
        } catch (IOException e) {
            throw new TemplateException("语法转换失败：", e);
        }
    }

}
