package com.join.template.text;

import com.join.template.core.Element;
import com.join.template.core.Template;
import com.join.template.core.factory.JoinFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        JoinFactoryBuilder joinFactoryBuilder = new JoinFactoryBuilder();
        Template template = joinFactoryBuilder.builder().getTemplate("/test1.html");
        System.out.println((System.currentTimeMillis() - start));
        List<Element> elements = template.getAllElement();
        Element root = template.getRootElement();
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
        System.out.println(process);
        System.out.println((System.currentTimeMillis() - end));

    }

}
