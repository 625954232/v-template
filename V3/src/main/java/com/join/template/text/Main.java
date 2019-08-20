package com.join.template.text;

import com.alibaba.fastjson.JSON;
import com.join.template.core.constant.EntityType;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.grammar.generate.GrammarField;
import com.join.template.core.grammar.GrammarGenerate;
import com.join.template.core.grammar.GrammarInfo;
import com.join.template.core.listener.GrammarGenListener;
import com.join.template.core.util.IOUtil;

import java.net.URL;
import java.util.ArrayList;
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


        List<GrammarInfo> list = new ArrayList<>();
        for (Map.Entry<String, List<Map>> entry : maps.entrySet()) {
            GrammarGenerate grammarGenerate = joinFactory.getGrammarGenerate();
            grammarGenerate.setGrammarGenListener(new GrammarGenListener() {

                @Override
                public void onCreate(Map map, GrammarField fieldName, GrammarInfo grammarInfo) {
                    if ("agent".equals(grammarInfo.getName())) {
                        grammarInfo.setDescribe("代理人信息");
                        grammarInfo.setType(EntityType.Array);
                    } else if ("respondent".equals(grammarInfo.getName())) {
                        grammarInfo.setDescribe("被申请人信息");
                        grammarInfo.setType(EntityType.Array);
                    } else if ("applicant".equals(grammarInfo.getName())) {
                        grammarInfo.setDescribe("申请人信息");
                        grammarInfo.setType(EntityType.Array);
                    } else {
                        grammarInfo.setType(EntityType.String);
                    }
                }
            });
            GrammarField fieldName = new GrammarField();
            fieldName.setNameFieldName("filedKey");
            fieldName.setTypeFieldName("type");
            fieldName.setDescribeFieldName("filedValue");
            fieldName.setChildFieldName("child");
            GrammarInfo grammarInfo = grammarGenerate.generateGrammar(entry.getKey(), entry.getValue(), fieldName);
            list.add(grammarInfo);
        }
        System.out.println(JSON.toJSONString(list));
    }
}
