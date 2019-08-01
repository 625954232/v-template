package com.join.template.process;

import com.join.template.context.Context;
import com.join.template.expression.Expression;
import com.join.template.factory.JoinFactory;
import com.join.template.node.Element;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.Writer;


public class VarcharProcess extends AbstractProcess {


    public VarcharProcess(JoinFactory joinFactory) {
        super(joinFactory);
    }

    @Override
    public void process(Element element, Context context, Writer writer) {
        try {
            if (context == null) {
                return;
            }
            String var = element.getBody();
            if (StringUtils.isBlank(var)) {
                return;
            }
            Expression expression = joinFactory.getExpression();
            expression.setExpression(var);
            expression.setContext(context);
            Object evaluate = expression.evaluate();
            writer.write(evaluate.toString());
        } catch (IOException e) {
            throw new IllegalArgumentException("语法转换失败：", e);
        }
    }


}
