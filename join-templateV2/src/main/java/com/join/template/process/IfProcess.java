package com.join.template.process;

import com.join.template.configuration.ExprConfig;
import com.join.template.constant.Constant;
import com.join.template.context.Content;
import com.join.template.expression.Expression;
import com.join.template.factory.JoinFactory;
import com.join.template.node.Element;

import java.io.Writer;

public class IfProcess extends AbstractProcess {


    public IfProcess(JoinFactory configuration) {
        super(configuration);
    }

    @Override
    public void process(Element element, Content content, Writer writer) {
        super.process(element, content, writer);
        if (content == null) {
            return;
        }
        boolean condition = evaluate(element, content); //条件是否成立
        for (int i = 0; i < element.getChilds().size(); i++) {
            Element child = element.getChilds().get(i);
            if (child.getNodeType() == Constant.EXPR_IF_ELSE) {//条件成立判断是否有其他判断
                if (condition) {
                    return;
                }
                condition = true;
            } else if (child.getNodeType() == Constant.EXPR_IF || child.getNodeType() == Constant.EXPR_IF_ELSE_IF) {
                if (condition) {
                    return;
                }
                condition = evaluate(child, content);
            } else {
                if (condition) {
                    removeSpace(child);
                    ExprConfig exprConfig = joinFactory.getExprConfigByType(child.getNodeType());
                    exprConfig.getProcess().process(child, content, writer);
                }
            }
        }
    }

    private boolean evaluate(Element element, Content content) {
        String text = element.getAttribute(configuration.getAttrText());
        Expression expression = joinFactory.getExpression();
        expression.setExpression(text);
        expression.setContext(content);
        Object evaluate = expression.evaluate();
        return evaluate instanceof Boolean ? (boolean) evaluate : false;
    }
}
