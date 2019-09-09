package com.join.template.core.process;

import com.join.template.core.Element;
import com.join.template.core.Template;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.constant.TemplateType;
import com.join.template.core.context.Content;
import com.join.template.core.expression.ExprHandle;
import com.join.template.core.factory.JoinFactory;

import java.io.Writer;

public abstract class AbstractProcess implements Process {

    protected Configuration configuration;
    protected JoinFactory joinFactory;
    protected ExprHandle exprHandle;


    @Override
    public void setExprHandle(ExprHandle exprHandle) {
        this.exprHandle = exprHandle;
        this.joinFactory = exprHandle.getJoinFactory();
        this.configuration = exprHandle.getJoinFactory().getConfiguration();
    }

    @Override
    public void process(Element element, Content context, Writer writer) {

    }


    protected void insertText(Element element, StringBuilder stringBuilder) {
        Template template = element.getTemplate();
        if (TemplateType.Html == template.getTemplateType()) {
            if (element.getOriginal().startsWith("\r\n") || element.getOriginal().endsWith("\r\n")) {
                stringBuilder.append(element.getOriginal().replaceAll("\r\n", ""));
            }
        }
    }

    protected void insertOpen(Element element, StringBuilder stringBuilder, String open) {
        int length = stringBuilder.length();
        int start = 0;
        while (start < length) {
            if (stringBuilder.charAt(0) != '<') {
                break;
            } else if (stringBuilder.charAt(start) == '>') {
                if ((start + 1) < length) {
                    if (stringBuilder.charAt(start + 1) == '<') {
                        start += 2;
                    } else {
                        start += 1;
                        break;
                    }
                } else {
                    start += 1;
                    break;
                }

            } else {
                start++;
            }
        }
        stringBuilder.insert(start, open);
    }

    public void insertClose(Element element, StringBuilder stringBuilder, String close) {
        int length = stringBuilder.length();
        int end = stringBuilder.length();
        while (end > 0) {
            if (stringBuilder.charAt(length - 1) != '>') {
                break;
            } else if ((end - 1) < length && (end - 1) > 0) {
                if (stringBuilder.charAt(end - 1) == '<' && stringBuilder.charAt(end) == '/') {
                    end -= 1;
                    break;
                } else {
                    end -= 1;
                }
            } else {
                if (end == 0)
                    break;
                end--;
            }
        }
        stringBuilder.insert(end, close);
    }
}