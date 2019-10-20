package com.join.template.html.process;

import com.join.template.core.element.Element;
import com.join.template.core.process.AbstractProcess;
import com.join.template.core.process.Process;
import com.join.template.core.context.Content;
import com.join.template.html.node.ElseNode;

import java.io.Writer;

public class ElseIfProcess extends AbstractProcess<ElseNode> implements Process<ElseNode> {


    @Override
    public void process(ElseNode element, Content context, Writer writer) {
        super.process(element, context, writer);
    }


}
