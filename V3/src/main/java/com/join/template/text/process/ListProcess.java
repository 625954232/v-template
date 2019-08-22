package com.join.template.text.process;

import com.join.template.core.Element;
import com.join.template.core.constant.Constant;
import com.join.template.core.constant.MarkedWords;
import com.join.template.core.expression.ExpressionHandle;
import com.join.template.core.process.AbstractProcess;
import com.join.template.core.process.Process;
import com.join.template.core.context.Content;
import com.join.template.core.verify.TemplateException;
import org.apache.commons.lang.StringUtils;

import javax.xml.ws.Holder;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListProcess extends AbstractProcess implements Process {

    @Override
    public void process(Element element, Content context, Writer writer) {
        try {
            super.process(element, context, writer);
            String var = element.getAttribute(configuration.getAttVar());

            String open = element.getAttribute(configuration.getAttOpen());
            String close = element.getAttribute(configuration.getAttClose());

            Holder<List> list = new Holder<>();
            List<Element> validElement = new ArrayList<>();
            if (!StringUtils.isBlank(open) && !MarkedWords.Attr_Statement_Opener.equals(open)) {
                writer.write(open);
            }
            if (context == null) {
                return;
            }
            Object value = context.get(var);

            boolean valid = this.validElement(element, value, validElement, list);
            if (valid) {
                this.writerValidElement(element, validElement, context, list, writer);
            } else {
                this.writerInvalidElement(element, validElement, context, writer);
            }

            if (!StringUtils.isBlank(close) && !MarkedWords.Attr_Statement_Terminator.equals(close)) {
                writer.write(close);
            }
        } catch (IOException e) {
            throw new TemplateException("语法转换失败：", e);
        }
    }


    private void writerValidElement(Element element, List<Element> validElement, Content context, Holder<List> list, Writer writer) throws IOException {
        String item = element.getAttribute(configuration.getAttItem());
        String separator = element.getAttribute(configuration.getAttSseparator());
        String index = element.getAttribute(configuration.getAttIndex());
        for (int i = 0; i < list.value.size(); i++) {
            if (i > 0 && !StringUtils.isBlank(separator) && !MarkedWords.Attr_Separator.equals(separator)) {
                writer.write(separator);
            }
            if (!StringUtils.isBlank(index)) {
                context.put(index, i);
            }
            context.put(item, list.value.get(i));

            for (int j = 0; j < validElement.size(); j++) {
                Element child = validElement.get(j);
                removeSpace(child, j, validElement.size());
                ExpressionHandle exprConfig = joinFactory.getExpressionHandle(child.getNodeType());
                exprConfig.getProcess().process(child, context, writer);
            }
        }
    }


    private void writerInvalidElement(Element element, List<Element> validElement, Content context, Writer writer) {
        for (int j = 0; j < validElement.size(); j++) {
            Element child = validElement.get(j);
            removeSpace(child, j, validElement.size());
            ExpressionHandle exprConfig = joinFactory.getExpressionHandle(child.getNodeType());
            exprConfig.getProcess().process(child, context, writer);
        }
    }



    private boolean validElement(Element element, Object value, List<Element> validElements, Holder<List> holder) {
        boolean isAdd = false;
        boolean isValid = false;
        List<Element> childs = element.getChilds();
        if (value == null) {
            isValid = isAdd = false;
        } else if (value instanceof List) {
            List list = (List) value;
            isValid = isAdd = list.size() > 0;
            holder.value = list;
        } else if (value.getClass().isArray()) {
            Object[] list = (Object[]) value;
            isValid = isAdd = list.length > 0;
            holder.value = Arrays.asList(list);
        } else {
            throw new TemplateException("循环条件错误值（var）：" + element.getOriginal());
        }
        for (Element child : childs) {
            if (Constant.EXPR_ELSE == child.getNodeType()) {
                if (isAdd) {
                    return isValid;
                }
                isAdd = true;
            } else {
                if (isAdd) {
                    validElements.add(child);
                }
            }
        }
        return isValid;
    }


}
