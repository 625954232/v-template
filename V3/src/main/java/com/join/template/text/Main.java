package com.join.template.text;

import com.alibaba.fastjson.JSON;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.grammar.EntityGrammar;
import com.join.template.core.grammar.FieldName;
import com.join.template.core.listener.GrammarGenListener;
import com.join.template.core.util.IOUtil;

import java.net.URL;
import java.util.List;
import java.util.Map;

public class Main {

//    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
//
//        JoinFactoryBuilder joinFactoryBuilder = new JoinFactoryBuilder();
//        JoinFactory joinFactory = joinFactoryBuilder.builder();
//
//        Map<Integer, String> grammars = joinFactory.getGrammars();
//        System.out.println("语法解释：" + grammars.toString());
//
//        Template template = joinFactory.getTemplate("/test1.html");
//        System.out.println("语法解析耗时：" + (System.currentTimeMillis() - start));
//
//        List<Element> elements = template.getAllElement();
//        System.out.println("全部节点：" + elements.size());
//
//        Element root = template.getRootElement();
//        System.out.println("顶层节点：" + root.toString());
//
//        template.putValue("iftext", 4);
//        template.putValue("text", "测试文本");
//        template.putValue("setdate", new Date());
//        template.putValue("setlocadate", LocalDateTime.now());
//        List<String> list = new ArrayList<>();
//        list.add("我帅");
//        list.add("我很帅");
//        list.add("我非常帅");
//        template.putValue("list", list);
//
//        long end = System.currentTimeMillis();
//        String process = template.process();
//        System.out.println("模版生成耗时：" + (System.currentTimeMillis() - end));
//        System.out.println("模版生成结果：" + process);
//
//    }

    public static void main(String[] args) {
        JoinFactoryBuilder joinFactoryBuilder = new JoinFactoryBuilder();
        JoinFactory joinFactory = joinFactoryBuilder.builder();
        URL resource = Main.class.getResource("/test.json");
        String string = IOUtil.toString(resource);
        Map<String, List<Map>> maps = JSON.parseObject(string, Map.class);
        for (Map.Entry<String, List<Map>> entry : maps.entrySet()) {
            EntityGrammar entityGrammarr = joinFactory.getEntityGrammarr();
            entityGrammarr.setGrammarGenListener(new GrammarGenListener() {

                @Override
                public void onCreate(Map map, FieldName fieldName, EntityGrammar entityGrammar) {
                    fieldName.setTypeFieldName("string");
                }
            });
            FieldName fieldName = new FieldName().setNameFieldName("filedKey").setDescribeFieldName("filedValue");
            if ("agent".equals(entry.getKey())
                    || "respondent".equals(entry.getKey())
                    || "applicant".equals(entry.getKey())) {
                fieldName.setTypeFieldName("string");
            } else {
                fieldName.setTypeFieldName("list");
            }
            EntityGrammar grammar = entityGrammarr.generateGrammar(entry.getKey(), entry.getValue(), fieldName);
            grammar.setName(entry.getKey());
            System.out.println(grammar);
        }
    }
}
