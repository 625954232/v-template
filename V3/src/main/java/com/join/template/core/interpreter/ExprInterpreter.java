package com.join.template.core.interpreter;

import com.join.template.core.expression.ExpressionHandle;
import com.join.template.core.grammar.GrammarExpl;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.constant.Constant;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.util.TemplateUtil;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CAOYOU
 * @Title: ExprInterpreter
 * @date 2019/8/1916:06
 */
public class ExprInterpreter implements Interpreter {
    private Configuration configuration;
    private JoinFactory joinFactory;
    private Map<Integer, String> grammars = new HashMap();

    public ExprInterpreter() {
        this.joinFactory = TemplateUtil.getJoinFactory();
        this.configuration = TemplateUtil.getConfiguration();
        this.loadGrammar();
    }

    /**
     * 加载语法解释
     *
     * @param
     * @return void
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 16:13
     */
    private void loadGrammar() {
        grammars.clear();
        Map<Object, ExpressionHandle> expressionHandles = joinFactory.getExpressionHandles();
        for (Map.Entry<Object, ExpressionHandle> configEntry : expressionHandles.entrySet()) {
            ExpressionHandle expressionHandle = configEntry.getValue();
            GrammarExpl grammar = expressionHandle.getGrammarExpl();
            if (!(configEntry.getKey() instanceof Integer)) {
                continue;
            }
            if (grammar != null) {
                String str = getGrammar(expressionHandle, grammar.getElementAttrExpl());
                grammars.put((Integer) configEntry.getKey(), str);
            } else {
                String str = getGrammar(expressionHandle, null);
                grammars.put((Integer) configEntry.getKey(), str);
            }
        }
    }

    /**
     * 获取语法解释
     *
     * @return
     */
    @Override
    public Map<Integer, String> getGrammars() {
        return grammars;
    }

    /**
     * 获取语法解释
     *
     * @param nodeType
     * @param grammarAttr
     * @return
     */
    @Override
    public String getGrammar(Integer nodeType, Map<String, String> grammarAttr) {
        ExpressionHandle expressionHandle = this.joinFactory.getExpressionHandle(nodeType);
        String grammar = getGrammar(expressionHandle, grammarAttr);
        return grammar;
    }

    /**
     * 获取语法解释
     *
     * @param expressionHandle
     * @param grammarAttr
     * @return
     */
    @Override
    public String getGrammar(ExpressionHandle expressionHandle, Map<String, String> grammarAttr) {
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isBlank(expressionHandle.getTag())) {
            return null;
        }
        builder.append(configuration.getExprFirstBegin());
        builder.append(expressionHandle.getTag());
        if (grammarAttr != null) {
            for (Map.Entry<String, String> grammarEntry : grammarAttr.entrySet()) {
                builder.append(" ").append(grammarEntry.getKey()).append("=\"").append(grammarEntry.getValue()).append("\"");
            }
        }
        builder.append(configuration.getExprEndSupport());
        GrammarExpl grammarExpl = expressionHandle.getGrammarExpl();
        if (grammarExpl != null) {
            grammarExpl.verifyElement(builder.toString(), false, grammarAttr);
        }
        if (Constant.EXPR_IF == expressionHandle.getNodeType() || Constant.EXPR_LIST == expressionHandle.getNodeType()) {
            builder.append("请在这输入你需要生成的内容");
            builder.append(configuration.getExprLastBegin());
            builder.append(expressionHandle.getTag());
            builder.append(configuration.getExprEndSupport());
        }
        return builder.toString();
    }
}
