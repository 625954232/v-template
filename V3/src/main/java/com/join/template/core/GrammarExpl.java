package com.join.template.core;


import java.util.Map;

public interface GrammarExpl {

    void verifyGrammarAttr(String original, Map<String, String> attr);

    Map<String, String> getGrammarAttr();


}
