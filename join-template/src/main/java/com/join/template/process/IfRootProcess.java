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
    public void process(Element element, Content context, Writer writer) {
        super.process(element, context, writer);
        if (context == null) {
            return;
        }
        boolean condition = false;
        boolean isOfElse = false;
        int conditionIndex = -1;
        for (int i = 0; i < element.getChilds().size(); i++) {
            Element child = element.getChilds().get(i);
            if (child.getNodeType() == Constant.EXPR_IF_ELSE) {
                isOfElse = true;
                continue;
            }
            if (condition == isOfElse || !condition == !isOfElse) {
                continue;
            } else {
                conditionIndex++;
            }
            removeSpace(child, conditionIndex);
            if (child.getNodeType() == Constant.EXPR_IF || child.getNodeType() == Constant.EXPR_IF_ELSE_IF) {
                String text = element.getAttribute(configuration.getAttrText());
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

    //去换行
    private void removeSpace(Element child, int conditionIndex) {
        if (conditionIndex >= 0
                && child.getNodeType() == Constant.EXPR_TEXT
                && child.getBody().startsWith("\r\n")) {
            String string = child.getBody().substring(2, child.getBody().length());
            child.setBody(string);
        }
        if (conditionIndex >= 0
                && child.getNodeType() == Constant.EXPR_TEXT
                && child.getBody().endsWith("\r\n")) {
            String string = child.getBody().substring(0, child.getBody().length() - 2);
            child.setBody(string);
        }
    }


}
