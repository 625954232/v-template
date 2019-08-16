package com.join.template.text.process;

import com.join.template.core.Element;
import com.join.template.core.Grammar;
import com.join.template.core.context.Content;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.verify.Assert;
import com.join.template.core.verify.TemplateException;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GetProcess extends AbstractProcess implements Grammar {


    public GetProcess(JoinFactory configuration) {
        super(configuration);
    }

    @Override
    public void process(Element element, Content context, Writer writer) {
        super.process(element, context, writer);
        try {
            if (context == null) {
                return;
            }
            String var = element.getAttribute(configuration.getAttVar());
            Assert.isBlank(var, "%s标签%s属性不可为空", element.getOriginal(), configuration.getAttVar());
            String format = element.getAttribute(configuration.getAttrFormat());
            Object object = context.get(var);
            if (object == null) {
                return;
            }
            if (!StringUtils.isBlank(format)) {
                if (object instanceof Number || object instanceof Date) {
                    String date = new SimpleDateFormat(format).format(object);
                    writer.write(date);
                } else if (object instanceof LocalDateTime) {
                    LocalDateTime dateTime = (LocalDateTime) object;
                    String date = dateTime.format(DateTimeFormatter.ofPattern(format));
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


    @Override
    public Map<String, String> getGrammarAttr() {
        Map<String, String> fields = new HashMap<>();
        fields.put(configuration.getAttFile(), "值别名");
        fields.put(configuration.getAttrFormat(), "时间类型格式化表达式");
        return fields;
    }
}
