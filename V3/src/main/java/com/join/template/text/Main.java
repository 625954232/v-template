package com.join.template.text;

import com.join.template.core.Element;
import com.join.template.core.Template;
import com.join.template.core.factory.JoinFactory;
import jdk.nashorn.internal.ir.debug.JSONWriter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        JoinFactoryBuilder joinFactoryBuilder = new JoinFactoryBuilder();
        JoinFactory joinFactory = joinFactoryBuilder.builder();

        Map<Integer, String> grammars = joinFactory.getGrammars();
        System.out.println("语法解释：" + grammars.toString());

        Template template = joinFactory.getTemplate("/test1.html");
        System.out.println("语法解析耗时：" + (System.currentTimeMillis() - start));

        List<Element> elements = template.getAllElement();
        System.out.println("全部节点：" + elements.size());

        Element root = template.getRootElement();
        System.out.println("顶层节点：" + root.toString());

        template.putValue("iftext", 4);
        template.putValue("text", "测试文本");
        template.putValue("setdate", new Date());
        template.putValue("setlocadate", LocalDateTime.now());
        List<String> list = new ArrayList<>();
        list.add("我帅");
        list.add("我很帅");
        list.add("我非常帅");
        template.putValue("list", list);

        long end = System.currentTimeMillis();
        String process = template.process();
        System.out.println("模版生成耗时：" + (System.currentTimeMillis() - end));
        System.out.println("模版生成结果：" + process);

    }

}
