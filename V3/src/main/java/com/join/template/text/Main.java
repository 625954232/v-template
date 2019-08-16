package com.join.template.text;

import com.join.template.core.Template;
import com.join.template.core.factory.JoinFactory;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        JoinFactoryBuilder joinFactoryBuilder = new JoinFactoryBuilder();
        JoinFactory builder = joinFactoryBuilder.genGrammar().builder();
        Map<Integer, String> grammars = builder.getGrammars();
        Template template = builder.putTemplate("/test1.html");
        System.out.println(template);
    }

}
