package com.join.template.core.explain;

import com.join.template.core.element.Element;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.expression.ExprHandle;
import com.join.template.core.factory.JoinFactory;

/**
 * @author CAOYOU
 * @Title: DefaultExplain
 * @date 2019/8/2612:08
 */
public abstract class AbstractExplain implements Explain {
    protected JoinFactory joinFactory;
    protected Configuration configuration;
    protected ExprHandle exprHandle;


    @Override
    public void setExprHandle(ExprHandle exprHandle) {
        this.exprHandle = exprHandle;
        this.joinFactory = exprHandle.getJoinFactory();
        this.configuration = exprHandle.getJoinFactory().getConfiguration();
    }

    @Override
    public void verifyElement(Element element) {
        verifyElement(element.getOriginal(), element.getAttributes());
    }


}
