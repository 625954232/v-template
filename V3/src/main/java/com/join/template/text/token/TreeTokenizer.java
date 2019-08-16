package com.join.template.text.token;

import com.join.template.core.Element;
import com.join.template.core.Tokenizer;
import com.join.template.core.constant.Constant;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.verify.TemplateException;


public class TreeTokenizer extends AbstractTokenizer implements Tokenizer {
    private int ifBeginSize = 0;
    private int ifEndSize = 0;
    private int listBeginSize = 0;
    private int listEndSize = 0;

    @Override
    public void read(String text) {
        super.read(text);
        if (ifBeginSize < ifEndSize) {
            throw new TemplateException("未找到if的开始标签");
        }
        if (ifBeginSize > ifEndSize) {
            throw new TemplateException("未找到if的结束标签");
        }
        if (listBeginSize < listEndSize) {
            throw new TemplateException("未找到list的开始标签");
        }
        if (listBeginSize > listEndSize) {
            throw new TemplateException("未找到list的结束标签");
        }
    }

    /**
     * 标签排列
     *
     * @param element
     */
    protected void arrange(Element element) {
        if (element == null) {
            return;
        }
        element.setParent(this.parent);
        if (element.getNodeType() == Constant.EXPR_LIST || element.getNodeType() == Constant.EXPR_IF) {
            if (element.getOriginal().startsWith(this.configuration.getExprFirstBegin())) {
                this.parent = element;
                this.current = element;
                if (element.getNodeType() == Constant.EXPR_IF)
                    ifBeginSize++;
                if (element.getNodeType() == Constant.EXPR_LIST)
                    listBeginSize++;
            } else if (element.getOriginal().startsWith(this.configuration.getExprLastBegin())) {
                this.parent = this.current.getParent();
                this.parent.addChilds(this.current);
                this.current = this.parent;
                if (element.getNodeType() == Constant.EXPR_IF)
                    ifEndSize++;
                if (element.getNodeType() == Constant.EXPR_LIST)
                    listEndSize++;
            }
        } else {
            this.current.addChilds(element);
        }
    }

}
