package com.join.template.text.process;

import com.join.template.core.Element;
import com.join.template.core.Expression;
import com.join.template.core.ExpressionHandle;
import com.join.template.core.process.AbstractProcess;
import com.join.template.core.process.Process;
import com.join.template.core.constant.Constant;
import com.join.template.core.context.Content;

import java.io.Writer;

public class IfProcess extends AbstractProcess implements Process {


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
                    removeStartSpace(child);
                    removeEndSpace(child);
                    ExpressionHandle exprConfig = joinFactory.getExpressionHandle(child.getNodeType());
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
