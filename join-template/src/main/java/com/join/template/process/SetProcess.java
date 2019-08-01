package com.join.template.process;

import com.join.template.constant.Constant;
import com.join.template.context.Context;
import com.join.template.expression.Expression;
import com.join.template.factory.JoinFactory;
import com.join.template.node.Element;

import java.io.Writer;

public class SetProcess extends AbstractProcess {


    public SetProcess(JoinFactory joinFactory) {
        super(joinFactory);
    }

    @Override
    public void process(Element element, Context context, Writer writer) {
        if (context == null) {
            return;
        }
    }


}
