package com.join.template.text.reader;

import com.join.template.core.Element;
import com.join.template.core.Parser;
import com.join.template.core.Reader;
import com.join.template.core.entity.ExprConfig;
import com.join.template.core.constant.Constant;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.util.TemplateUtil;
import com.join.template.core.verify.TemplateException;

public class DefaultReader implements Reader {

    @Override
    public Element reader(String matchBeginTag, String matchEndTag, String text) {
        JoinFactory joinFactory = TemplateUtil.getJoinFactory();
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
