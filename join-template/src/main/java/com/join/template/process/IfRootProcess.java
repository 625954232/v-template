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
        boolean condition = false; //条件是否成立
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
                String text = child.getAttribute(configuration.getAttrText());
                Expression expression = joinFactory.getExpression();
                expression.setExpression(text);
                expression.setContext(context);
                Object evaluate = expression.evaluate();
                condition = evaluate instanceof Boolean ? (boolean) evaluate : false;
            } else {
                if (condition) {
                    removeSpace(child);
                    Process process = joinFactory.getProcess(child.getNodeType());
                    process.process(child, context, writer);
                }
            }
        }

    }

    //去换行
    private void removeSpace(Element child) {
        if (child.getNodeType() == Constant.EXPR_TEXT
                && child.getBody().startsWith("\r\n")) {
            String string = child.getBody().substring(2, child.getBody().length());
            child.setBody(string);
        }
        if (child.getNodeType() == Constant.EXPR_TEXT
                && child.getBody().endsWith("\r\n")) {
            String string = child.getBody().substring(0, child.getBody().length() - 2);
            child.setBody(string);
        }
    }


}
