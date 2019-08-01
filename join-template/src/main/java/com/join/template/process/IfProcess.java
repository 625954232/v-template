package com.join.template.process;

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
    public void process(Element root, Content context, Writer writer) {
    }


}
