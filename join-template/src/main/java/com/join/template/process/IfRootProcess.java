package com.join.template.process;

import com.join.template.constant.Constant;
import com.join.template.context.Content;
import com.join.template.expression.Expression;
import com.join.template.factory.JoinFactory;
import com.join.template.node.Element;

import java.io.Writer;

public class IfRootProcess extends AbstractProcess {


    public IfRootProcess(JoinFactory configuration) {
        super(configuration);
    }

    @Override
    public void process(Element root, Content context, Writer writer) {
        if (context == null) {
            return;
        }


        boolean condition = false;
        boolean isOfElse = false;

        for (int i = 0; i < root.getChilds().size(); i++) {
            Element child = root.getChilds().get(i);
            if (child.getNodeType() == Constant.EXPRESSION_IF_ELSE) {
                isOfElse = true;
            }
            if (condition == isOfElse || !condition == !isOfElse) {
                continue;
            }
            if (child.getNodeType() == Constant.EXPRESSION_IF || child.getNodeType() == Constant.EXPRESSION_IF_ELSE_IF) {
                String text = root.getAttribute(configuration.getAttrText());
                Expression expression = joinFactory.getExpression();
                expression.setExpression(text);
                expression.setContext(context);
                Object evaluate = expression.evaluate();
                condition = evaluate instanceof Boolean ? (boolean) evaluate : false;
                continue;
            }
            Process process = joinFactory.getProcess(child.getNodeType());
            process.process(child, context, writer);
        }
    }


}
