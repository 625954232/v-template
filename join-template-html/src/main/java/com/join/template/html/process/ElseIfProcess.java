package com.join.template.html.process;

import com.join.template.core.process.AbstractProcess;
import com.join.template.core.process.Process;
import com.join.template.core.context.Content;
import com.join.template.html.node.ElseNodeExample;

import java.io.Writer;

public class ElseIfProcess extends AbstractProcess<ElseNodeExample> implements Process<ElseNodeExample> {


    @Override
    public void process(ElseNodeExample element, Content context, Writer writer) {
        super.process(element, context, writer);
    }


}
