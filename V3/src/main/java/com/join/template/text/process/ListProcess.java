package com.join.template.text.process;

import com.join.template.core.Element;
import com.join.template.core.Grammar;
import com.join.template.core.entity.ExprConfig;
import com.join.template.core.context.Content;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.verify.TemplateException;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListProcess extends AbstractProcess implements Grammar {
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
                throw new TemplateException("循环条件没有默认值（var）：" + element.getOriginal());
            }
            if (value instanceof List) {
                List list = (List) value;
                for (int i = 0; i < list.size(); i++) {
                    if (i > 0 && !StringUtils.isBlank(separator)) {
                        writer.write(separator);
                    }
                    if (!StringUtils.isBlank(index)) {
                        context.put(index, i);
                    }
                    context.put(item, list.get(i));
                    for (int j = 0; j < element.getChilds().size(); j++) {
                        Element child = element.getChilds().get(j);
                        ExprConfig exprConfig = joinFactory.getExprConfigByType(child.getNodeType());
                        exprConfig.getProcess().process(child, context, writer);
                    }
                }
            }
            if (!StringUtils.isBlank(close)) {
                writer.write(close);
            }
        } catch (IOException e) {
            throw new TemplateException("语法转换失败：", e);
        }
    }

    @Override
    public Map<String, String> getGrammarAttr() {
        Map<String, String> fields = new HashMap<>();
        fields.put(configuration.getAttVar(), "参数名称");
        fields.put(configuration.getAttItem(), "单项别名");
        fields.put(configuration.getAttOpen(), "语句开始符");
        fields.put(configuration.getAttClose(), "语句结束符");
        fields.put(configuration.getAttSseparator(), "分隔符");
        fields.put(configuration.getAttIndex(), "索引");
        return fields;
    }
}
