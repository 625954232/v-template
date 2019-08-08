package com.join.template.reader;

import com.join.template.configuration.ExprConfig;
import com.join.template.constant.Constant;
import com.join.template.factory.JoinFactory;
import com.join.template.node.Element;
import com.join.template.parser.Parser;
import com.join.template.verify.TemplateException;

public class DefaultReader implements Reader {
    protected JoinFactory joinFactory;


    public DefaultReader(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
    }


    @Override
    public Element reader(String matchBeginTag, String matchEndTag, String text) {
        String[] splits = text.split(" ");
        if (splits.length <= 0) {
            return null;
        }
        String token = splits[0];
        ExprConfig exprConfig = joinFactory.getExprConfigByTag(token);
        if (exprConfig == null) {
            exprConfig = joinFactory.getExprConfigByType(Constant.EXPR_TEXT);
        }
        Parser parser = exprConfig.getParser();
        if (parser == null) {
            throw new TemplateException("请配置对应（%s）的语句解析器", text);
        }
        return parser.parser(matchBeginTag, matchEndTag, text, exprConfig);
    }
}
