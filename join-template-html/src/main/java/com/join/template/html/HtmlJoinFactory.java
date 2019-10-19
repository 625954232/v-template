package com.join.template.html;

import com.join.template.core.*;
import com.join.template.core.factory.AbstractJoinFactory;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.factory.JoinFactoryBuilder;
import com.join.template.core.resource.ResourceInfo;
import com.join.template.core.util.IOUtil;
import com.join.template.core.constant.Constant;
import com.join.template.html.explain.*;
import com.join.template.html.node.*;
import com.join.template.html.process.*;
import com.join.template.html.reader.HtmlParser;


public class HtmlJoinFactory extends AbstractJoinFactory implements JoinFactoryBuilder {

    public HtmlJoinFactory(Configuration configuration) {
        super(configuration);
    }

    @Override
    public void init() {
        this.setParser(HtmlParser.class);
        this.builderExprHandle()
                .nodeType(Constant.EXPR_ROOT)
                .process(new Processs())
                .elementClass(RootNode.class)
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_TEXT)
                .process(new TextProcess())
                .elementClass(TextNode.class)
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_VAR)
                .elementClass(VarNode.class)
                .process(new VarcharProcess())
                .explain(new VarcharExplain())
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_LIST).tag("list")
                .elementClass(ListNode.class)
                .process(new ListProcess())
                .explain(new ListExplain())
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_IF).tag("if")
                .elementClass(IFNode.class)
                .process(new IfProcess())
                .explain(new IfExplain())
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_ELSE).tag("else")
                .elementClass(ElseNode.class)
                .process(new IfElseProcess())
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_IF_ELSE_IF).tag("elseif")
                .elementClass(IFNode.class)
                .process(new ElseIfProcess())
                .explain(new ElseIfExplain())
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_INCLUDE).tag("include")
                .elementClass(IncludeNode.class)
                .process(new IncludeProcess())
                .explain(new IncludeExplain())
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_SET).tag("set")
                .elementClass(SetNode.class)
                .process(new SetProcess())
                .explain(new SetExplain())
                .addIn(this)

                .builderExprHandle()
                .nodeType(Constant.EXPR_GET).tag("get")
                .elementClass(GetNode.class)
                .process(new GetProcess())
                .explain(new GetExplain())
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
