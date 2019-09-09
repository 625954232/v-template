package com.join.template.text;

import com.join.template.core.*;
import com.join.template.core.expression.*;
import com.join.template.core.factory.AbstractJoinFactory;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.factory.JoinFactoryBuilder;
import com.join.template.core.resource.ResourceInfo;
import com.join.template.core.util.IOUtil;
import com.join.template.core.constant.Constant;
import com.join.template.core.factory.JoinFactory;
import com.join.template.text.explain.*;
import com.join.template.text.process.*;
import com.join.template.text.reader.TextParser;


public class TextJoinFactory extends AbstractJoinFactory implements JoinFactory {

    public TextJoinFactory(Configuration configuration) {
        super(configuration);
    }

    @Override
    public void init() {
        this.setParser(new TextParser(this));
        this.builderExprHandle()
                .nodeType(Constant.EXPR_ROOT)
                .process(new Processs(this))
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_TEXT)
                .process(new TextProcess(this))
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_VAR)
                .process(new VarcharProcess(this))
                .explain(new VarcharExplain(this))
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_LIST).tag("list")
                .process(new ListProcess(this))
                .explain(new ListExplain(this))
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_IF).tag("if")
                .process(new IfProcess(this))
                .explain(new IfExplain(this))
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_ELSE).tag("else")
                .process(new IfElseProcess(this))
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_IF_ELSE_IF).tag("elseif")
                .process(new ElseIfProcess(this))
                .explain(new ElseIfExplain(this))
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_INCLUDE).tag("include")
                .process(new IncludeProcess(this))
                .explain(new IncludeExplain(this))
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_SET).tag("set")
                .process(new SetProcess(this))
                .explain(new SetExplain(this))
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_GET).tag("get")
                .process(new GetProcess(this))
                .explain(new GetExplain(this))
                .addIn(this);
    }


    /**
     * 缓存模版内容
     *
     * @param name    模板名称
     * @param content 模板内容
     * @return
     */
    @Override
    public Template putTemplate(String name, String content) {
        return new TextTemplate(this, name, content);
    }


    /**
     * 根绝模板名称获取模版
     *
     * @param fileName 文件名称或模板名称
     * @return
     */
    @Override
    public Template getTemplate(String fileName) {
        ResourceInfo resource = this.configuration.getResource(fileName);
        String name = resource.getName();
        String text = IOUtil.toString(resource.getIO());
        return new TextTemplate(this, name, text);
    }

}