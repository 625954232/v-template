package com.join.template.core.explain;

import com.join.template.core.Element;
import com.join.template.core.expression.ExprHandle;

/**
 * @author CAOYOU
 * @Title: DefaultExplain
 * @date 2019/8/2612:08
 */
public abstract class AbstractExplain implements Explain {
    private ExprHandle exprHandle;

    @Override
    public void verifyElement(Element element) {
        verifyElement(element.getOriginal(), element.isEndElement(), element.getAttributes());
    }


}
