package com.join.template.html.process;

import com.join.template.core.expression.Expression;
import com.join.template.core.process.AbstractProcess;
import com.join.template.core.process.Process;
import com.join.template.core.context.Content;
import com.join.template.core.util.Assert;
import com.join.template.html.node.VarNodeExample;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.Writer;


public class VarcharProcess extends AbstractProcess<VarNodeExample> implements Process<VarNodeExample> {


    @Override
    public void process(VarNodeExample element, Content context, Writer writer) {
        super.process(element, context, writer);
        try {
            if (context == null) {
                return;
            }

            if (StringUtils.isBlank(element.getVar())) {
                return;
            }
            Expression expression = joinFactory.createExprActuator();
            expression.setExpression(element.getVar());
            expression.setContext(context);
            Object evaluate = expression.evaluate();
            Assert.isNull(evaluate, "%s（%s）值不可为空", element.getOriginal(), element.getVar());
            writer.write(evaluate.toString());
        } catch (IOException e) {
            throw new IllegalArgumentException("语法转换失败：", e);
        }
    }


}
