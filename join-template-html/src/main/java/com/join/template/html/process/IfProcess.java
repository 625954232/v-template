package com.join.template.html.process;

import com.join.template.core.element.Element;
import com.join.template.core.expression.Expression;
import com.join.template.core.grammar.handle.Grammar;
import com.join.template.core.process.AbstractProcess;
import com.join.template.core.process.Process;
import com.join.template.core.constant.Constant;
import com.join.template.core.context.Content;
import com.join.template.html.node.IFNodeExample;

import java.io.Writer;

public class IfProcess extends AbstractProcess<IFNodeExample> implements Process<IFNodeExample> {

    @Override
    public void process(IFNodeExample element, Content content, Writer writer) {
        super.process(element, content, writer);
        if (content == null) {
            return;
        }
        boolean condition = evaluate(element, content); //条件是否成立
        for (int i = 0; i < element.getChilds().size(); i++) {
            Element child = element.getChilds().get(i);
            if (child.getNodeType() == Constant.EXPR_ELSE) {//条件成立判断是否有其他判断
                if (condition) {
                    return;
                }
                condition = true;
            } else if (child.getNodeType() == Constant.EXPR_IF || child.getNodeType() == Constant.EXPR_IF_ELSE_IF) {
                if (condition) {
                    return;
                }
                condition = evaluate((IFNodeExample) child, content);
            } else {
                if (condition) {
                    Grammar exprConfig = joinFactory.getGrammar(child.getNodeType());
                    exprConfig.getProcess().process(child, content, writer);
                }
            }
        }
    }

    private boolean evaluate(IFNodeExample element, Content content) {
        Expression expression = joinFactory.createExprActuator();
        expression.setExpression(element.getText());
        expression.setContext(content);
        Object evaluate = expression.evaluate();
        return evaluate instanceof Boolean ? (boolean) evaluate : false;
    }


}
