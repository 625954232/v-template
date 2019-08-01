package com.join.template.process;

import com.join.template.context.Context;
import com.join.template.factory.JoinFactory;
import com.join.template.node.Element;

import java.io.Writer;

public class IfElseProcess extends AbstractProcess {


    public IfElseProcess(JoinFactory configuration) {
        super(configuration);
    }

    @Override
    public void process(Element root, Context context, Writer writer) {

    }
}
