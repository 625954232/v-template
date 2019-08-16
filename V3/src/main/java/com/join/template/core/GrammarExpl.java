package com.join.template.core;


import java.util.Map;

public interface GrammarExpl {

    default void verifyGrammarAttr(Element element) {
        verifyGrammarAttr(element.getOriginal(), element.isEndElement(), element.getAttributes());
    }

    void verifyGrammarAttr(String original, Boolean endElement, Map<String, String> attr);

    Map<String, String> getGrammarAttr();


}
