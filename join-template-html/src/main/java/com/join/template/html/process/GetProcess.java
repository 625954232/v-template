package com.join.template.html.process;

import com.join.template.core.element.Element;
import com.join.template.core.process.AbstractProcess;
import com.join.template.core.process.Process;
import com.join.template.core.context.Content;
import com.join.template.core.verify.Assert;
import com.join.template.core.verify.TemplateException;
import com.join.template.html.node.GetNode;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class GetProcess extends AbstractProcess<GetNode> implements Process<GetNode> {

    @Override
    public void process(GetNode element, Content context, Writer writer) {
        super.process(element, context, writer);
        try {
            if (context == null) {
                return;
            }
            Assert.isBlank(element.getVar(), "%s标签%s属性不可为空", element.getOriginal(), configuration.getAttVar());
            Object object = context.get(element.getVar());
            if (object == null) {
                return;
            }
            if (!StringUtils.isBlank(element.getFormat())) {
                if (object instanceof Number || object instanceof Date) {
                    String date = new SimpleDateFormat(element.getFormat()).format(object);
                    writer.write(date);
                } else if (object instanceof LocalDateTime) {
                    LocalDateTime dateTime = (LocalDateTime) object;
                    String date = dateTime.format(DateTimeFormatter.ofPattern(element.getFormat()));
                    writer.write(date);
                } else {
                    throw new TemplateException("%s不是时间格式不可格式化", object.toString());
                }
            } else {
                writer.write(object.toString());
            }
        } catch (IOException e) {
        }

    }


}
