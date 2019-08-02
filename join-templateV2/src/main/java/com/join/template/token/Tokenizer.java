package com.join.template.token;

import com.join.template.configuration.Configuration;
import com.join.template.constant.Constant;
import com.join.template.factory.JoinFactory;
import com.join.template.matcher.MatchListener;
import com.join.template.node.Element;
import com.join.template.node.Node;
import com.join.template.reader.Reader;

import javax.xml.ws.Holder;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    protected Configuration configuration;
    protected Reader reader;
    protected JoinFactory joinFactory;
    protected List<Element> elements = new ArrayList<>();
    protected Element root = new Node().setNodeType(Constant.EXPR_ROOT);

    public Tokenizer(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
        this.configuration = joinFactory.getConfiguration();
        this.reader = joinFactory.getReader();
    }

    public void split(String text) {
        int length = text.length();
        int current = 0;
        int start = 0;
        while (current < length) {
            for (TokenMatcher tokenMatcher : joinFactory.getTokenMatchers()) {
                if (tokenMatcher.isMatch(text, current)) {
                    if (start < current) {
                        Element element = this.reader.reader(text.substring(start, current), null);
                        if (element != null) {
                            elements.add(element);
                        }
                    }
                    start = current = tokenMatcher.match(text, current, new MatchListener() {
                        @Override
                        public void match(String token) {
                            Element element = Tokenizer.this.reader.reader(token, tokenMatcher);
                            if (element != null) {
                                elements.add(element);
                            }
                        }
                    });
                    break;
                }
            }
            current++;
        }
        arrange(root);
        System.out.println(root);
    }


    private Integer current = 0;

    public void arrange(Element root) {
        for (current = current; current < elements.size(); current++) {
            Element element = elements.get(current);
            if (element.getOriginal().startsWith(configuration.getExprLastBegin())) {
                break;
            }
            if (element.getOriginal().startsWith(configuration.getExprFirstBegin())) {
                current++;
                arrange(element);
            } else if (element.getOriginal().startsWith(configuration.getVarTagStart())) {
                element.setNodeType(Constant.EXPR_VAR);
            }
            root.getChilds().add(element);
        }
    }
}
