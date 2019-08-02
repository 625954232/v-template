package com.join.template;

import com.join.template.core.JoinFactoryBuilder;
import com.join.template.core.Template;
import com.join.template.node.Element;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TEST {

    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
        JoinFactoryBuilder joinFactoryBuilder = new JoinFactoryBuilder();
        Template template = joinFactoryBuilder.builder().putTemplate("/test1.html");
//        System.out.println((System.currentTimeMillis() - start));
        Element root = template.getRoot();
        template.putValue("iftext", 4);
        template.putValue("text", "测试文本");
        template.putValue("setdate", new Date());
        template.putValue("setlocadate", LocalDateTime.now());
        List<String> list = new ArrayList<>();
        list.add("我帅");
        list.add("我很帅");
        list.add("我非常帅");
        template.putValue("list", list);
        String process = template.process();
        System.out.println(process);
//        System.out.println((System.currentTimeMillis() - start));
    }

}
