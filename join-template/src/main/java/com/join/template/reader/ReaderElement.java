package com.join.template.reader;

import com.join.template.configuration.Configuration;
import com.join.template.context.Context;
import com.join.template.core.Template;
import com.join.template.factory.JoinFactory;
import com.join.template.node.Element;
import com.join.template.parser.Parser;
import lombok.Getter;

@Getter
public class ReaderElement implements Reader {

    protected Context context;
    protected Template template;
    protected JoinFactory joinFactory;
    protected Configuration configuration;
    private int lineSize;

    public ReaderElement(Template template) {
        this.template = template;
        this.context = template.getContext();
        this.joinFactory = template.getJoinFactory();
        this.configuration = joinFactory.getConfiguration();
    }


    @Override
    public synchronized int read(Element root, String text, Parser parser) {
        int sbLenth = text.length();
        int current = 0;
        StringBuilder temp = new StringBuilder();
        while (current < sbLenth) {
            if (text.startsWith(configuration.getVarTagStart(), current)
                    || text.startsWith(configuration.getExprFirstBegin(), current)) {
                //解析文本字符
                if (temp.length() > 0) {
                    parser.parser(root, temp.toString(), this);
                    temp.delete(0, sbLenth);
                }
                //解析语法标签
                String data = text.substring(current);
                Element element = parser.parser(root, data, this);
                current = current + element.getHead().length() + element.getBody().length() + element.getEnd().length();
            } else {
                //拉取非语法语句的文本字符
                if (text.charAt(current) == '\r' && text.charAt(current + 1) == '\n') {
                    lineSize++;
                }
                temp.append(text.charAt(current));
                current++;
            }
        }
        if (temp.length() > 0) {//补齐语法语句后面的字符
            parser.parser(root, temp.toString(), this);
            temp.delete(0, sbLenth);
        }
        return current;
    }


}
