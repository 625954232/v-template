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
        boolean isOfElse = false;  //是否有其他判断
        int conditionSize = 0;
        for (int i = 0; i < element.getChilds().size(); i++) {
            Element child = element.getChilds().get(i);
            if (child.getNodeType() == Constant.EXPR_IF_ELSE) {//条件成立判断是否有其他判断
                isOfElse = true;
                continue;
            } else if (child.getNodeType() == Constant.EXPR_IF || child.getNodeType() == Constant.EXPR_IF_ELSE_IF) {
                conditionSize++;
                if (condition == false) {//条件不成立就执行判断标签的条件
                    String text = child.getAttribute(configuration.getAttrText());
                    Expression expression = joinFactory.getExpression();
                    expression.setExpression(text);
                    expression.setContext(context);
                    Object evaluate = expression.evaluate();
                    condition = evaluate instanceof Boolean ? (boolean) evaluate : false;
                }
            } else {
                if (condition && conditionSize > 1) {//条件成立并且条件数超过1就不做处理
                    return;
                }
                if (condition || isOfElse) {//条件成立或有其他判断就做处理
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
