package com.join.template.text.process;

import com.join.template.core.Element;
import com.join.template.core.expression.Expression;
import com.join.template.core.process.AbstractProcess;
import com.join.template.core.process.Process;
import com.join.template.core.context.Content;
import com.join.template.core.verify.Assert;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.Writer;


public class VarcharProcess extends AbstractProcess implements Process {


    @Override
    public void process(Element element, Content context, Writer writer) {
        super.process(element, context, writer);
        try {
            if (context == null) {
                return;
            }
            String var = element.getAttribute(configuration.getAttVar());
            if (StringUtils.isBlank(var)) {
                return;
            }
            Expression expression = joinFactory.getExpression();
            expression.setExpression(var);
            expression.setContext(context);
            Object evaluate = expression.evaluate();
            Assert.isNull(evaluate, "%s（%s）值不可为空", element.getOriginal(), var);
            writer.write(evaluate.toString());
        } catch (IOException e) {
            throw new IllegalArgumentException("语法转换失败：", e);
        }
    }


}
