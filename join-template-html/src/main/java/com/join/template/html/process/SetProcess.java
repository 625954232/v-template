package com.join.template.html.process;

import com.join.template.core.process.AbstractProcess;
import com.join.template.core.process.Process;
import com.join.template.core.context.Content;
import com.join.template.html.node.SetNodeExample;

import java.io.Writer;

public class SetProcess extends AbstractProcess<SetNodeExample> implements Process<SetNodeExample> {


    @Override
    public void process(SetNodeExample element, Content context, Writer writer) {
        super.process(element, context, writer);
        if (context == null) {
            return;
        }
        context.put(element.getName(), element.getVar());
    }


}
