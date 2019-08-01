package com.join.template.process;

import com.join.template.constant.Constant;
import com.join.template.context.Context;
import com.join.template.expression.Expression;
import com.join.template.factory.JoinFactory;
import com.join.template.node.Element;


import java.io.Writer;

public class IfProcess extends AbstractProcess {


    public IfProcess(JoinFactory configuration) {
        super(configuration);
    }

    @Override
    public void process(Element root, Context context, Writer writer) {
        if (context == null) {
            return;
        }
        String text = root.getAttribute(configuration.getAttrText());

        Expression expression = joinFactory.getExpression();
        expression.setExpression(text);
        expression.setContext(context);
        Object evaluate = expression.evaluate();
        boolean condition = evaluate instanceof Boolean ? (boolean) evaluate : false;
        boolean isOfElse = false;
        for (Element child : root.getChilds()) {
            if (child.getNodeType() == Constant.EXPRESSION_IF_ELSE) {
                isOfElse = true;
            }
            if (condition == isOfElse || !condition == !isOfElse) {
                continue;
            }
            Process process = joinFactory.getProcess(child.getNodeType());
            process.process(child, context, writer);
        }
    }


}
