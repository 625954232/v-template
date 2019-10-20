package com.join.template.html;


import com.join.template.core.Template;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.constant.Constant;
import com.join.template.core.factory.AbstractJoinFactory;
import com.join.template.core.factory.JoinFactoryBuilder;
import com.join.template.core.util.IOUtil;
import com.join.template.core.util.resource.ResourceInfo;
import com.join.template.html.expression.JexlExpression;
import com.join.template.html.grammar.EntityGenerate;
import com.join.template.html.grammar.TemplateExprAttr;
import com.join.template.html.node.*;
import com.join.template.html.process.*;
import com.join.template.html.reader.HtmlParser;


public class HtmlJoinFactory extends AbstractJoinFactory implements JoinFactoryBuilder {

    public HtmlJoinFactory(Configuration configuration) {
        super(configuration);
    }

    @Override
    public void init() {
        this.setExprAttr(TemplateExprAttr.class);
        this.setExprActuator(JexlExpression.class);
        this.setGrammarGenerate(EntityGenerate.class);
        this.setParser(HtmlParser.class);
        this.builderExprHandle()
                .nodeType(Constant.EXPR_ROOT)
                .process(new Processs())
                .element(RootNodeExample.class)
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_TEXT)
                .process(new TextProcess())
                .element(TextNodeExample.class)
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_VAR)
                .element(VarNodeExample.class)
                .process(new VarcharProcess())
                .explain(new VarNodeExample())
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_LIST).tag("list")
                .element(ListNodeExample.class)
                .process(new ListProcess())
                .explain(new ListNodeExample())
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_IF).tag("if")
                .element(IFNodeExample.class)
                .process(new IfProcess())
                .explain(new IFNodeExample())
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_ELSE).tag("else")
                .element(ElseNodeExample.class)
                .process(new IfElseProcess())
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_IF_ELSE_IF).tag("elseif")
                .element(IFNodeExample.class)
                .process(new ElseIfProcess())
                .explain(new IFNodeExample())
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_INCLUDE).tag("include")
                .element(IncludeNodeExample.class)
                .process(new IncludeProcess())
                .explain(new IncludeNodeExample())
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_SET).tag("set")
                .element(SetNodeExample.class)
                .process(new SetProcess())
                .explain(new SetNodeExample())
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_GET).tag("get")
                .element(GetNodeExample.class)
                .process(new GetProcess())
                .explain(new GetNodeExample())
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
        return new HtmlTemplate(this.build(), name, content);
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
        return new HtmlTemplate(this.build(), name, text);
    }

}
