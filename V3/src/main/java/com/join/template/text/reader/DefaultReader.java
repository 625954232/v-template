package com.join.template.text.reader;

import com.join.template.core.Element;
import com.join.template.core.Parser;
import com.join.template.core.Reader;
import com.join.template.core.Template;
import com.join.template.core.constant.Constant;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.util.TemplateUtil;

public class DefaultReader implements Reader {

    @Override
    public Element reader(Template template, String matchBeginTag, String matchEndTag, String text) {
        JoinFactory joinFactory = TemplateUtil.getJoinFactory();
        String[] splits = text.split(" ");
        if (splits.length <= 0) {
            return null;
        }
        String token = splits[0];
        Parser parser = joinFactory.getExprHandle(token);
        if (parser == null) {
            parser = joinFactory.getExprHandle(Constant.EXPR_TEXT);
        }
        return parser.parser(template, matchBeginTag, matchEndTag, text);
    }
}
