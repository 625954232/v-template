package com.join.template.html.process;

import com.join.template.core.element.Element;
import com.join.template.core.constant.Constant;
import com.join.template.core.grammar.handle.Grammar;
import com.join.template.core.process.AbstractProcess;
import com.join.template.core.process.Process;
import com.join.template.core.context.Content;
import com.join.template.core.util.TemplateException;
import com.join.template.html.node.ListNodeExample;
import org.apache.commons.lang.StringUtils;

import javax.xml.ws.Holder;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListProcess extends AbstractProcess<ListNodeExample> implements Process<ListNodeExample> {

    @Override
    public void process(ListNodeExample element, Content context, Writer writer) {
        try {
            super.process(element, context, writer);

            Holder<List> list = new Holder<>();
            List<Element> validElement = new ArrayList<>();
            boolean valid = false;

            if (context != null) {
                Object value = context.get(element.getVar());
                valid = this.validElement(element, value, validElement, list);
            }
            if (valid) {
                this.writerValidElement(element, validElement, context, list, writer);
            } else {
                this.writerInvalidElement(validElement, context, writer);
            }

        } catch (IOException e) {
            throw new TemplateException("语法转换失败：", e);
        }
    }


    private void writerValidElement(ListNodeExample element, List<Element> validElement, Content context, Holder<List> list, Writer writer) throws IOException {
        for (int i = 0; i < list.value.size(); i++) {
            if (i > 0 && StringUtils.isNotBlank(element.getSeparator())) {
                writer.write(element.getSeparator());
            }
            context.put(element.getItem().concat("_").concat(configuration.getAttIndex()), (i + 1));
            context.put(element.getItem(), list.value.get(i));
            for (int j = 0; j < validElement.size(); j++) {
                Element child = validElement.get(j);
                StringBuilder stringBuilder = new StringBuilder(child.getOriginal());
                if (i == 0 && j == 0) {
                    if (Constant.EXPR_TEXT == child.getNodeType()) {
                        this.insertOpen(stringBuilder, element.getOpen());
                    } else {
                        writer.write(element.getOpen());
                    }
                } else if (i == (list.value.size() - 1) && j == (validElement.size() - 1)) {
                    if (Constant.EXPR_TEXT == child.getNodeType()) {
                        this.insertClose(stringBuilder, element.getClose());
                    } else {
                        stringBuilder.append(element.getClose());
                    }
                }
                if (Constant.EXPR_TEXT == child.getNodeType()) {
                    writer.write(stringBuilder.toString());
                } else {
                    Grammar exprConfig = joinFactory.getGrammar(child.getNodeType());
                    exprConfig.getProcess().process(child, context, writer);
                }
            }
        }
    }


    private void writerInvalidElement(List<Element> validElement, Content context, Writer
            writer) {
        for (int j = 0; j < validElement.size(); j++) {
            Element child = validElement.get(j);
            Grammar exprConfig = joinFactory.getGrammar(child.getNodeType());
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


    private void insertOpen(StringBuilder stringBuilder, String open) {
        int length = stringBuilder.length();
        int start = 0;
        boolean special = false;
        boolean backBrace = false;
        while (start < length) {
            if (stringBuilder.charAt(0) != '<') {
                break;
            } else if (stringBuilder.charAt(start) != '\t'
                    || stringBuilder.charAt(start) != '\n'
                    || stringBuilder.charAt(start) != '\r'
                    || stringBuilder.charAt(start) != ' ') {
                special = true;
                start += 1;
            } else if (special && stringBuilder.charAt(start) != '<') {
                backBrace = true;
                special = false;
                start += 1;
            } else if (backBrace && stringBuilder.charAt(start) == '>') {
                start += 1;
                break;
            } else {
                start++;
            }
        }
        stringBuilder.insert(start, open);
        System.out.println(stringBuilder);
    }

    private void insertClose(StringBuilder stringBuilder, String close) {
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
