package com.join.template;

import com.join.template.core.JoinFactoryBuilder;
import com.join.template.core.Template;
import com.join.template.factory.JoinFactory;
import com.join.template.node.Element;
import com.join.template.util.IOUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TEST {

    public static void main(String[] args) {


        InputStream resourceAsStream = TEST.class.getResourceAsStream("/test1.html");
        String string = IOUtil.toString(resourceAsStream);
        JoinFactoryBuilder joinFactoryBuilder = new JoinFactoryBuilder();
        Template template = joinFactoryBuilder.builder().putTemplate("/test1.html");
        Element root = template.getRoot();
        template.putValue("bb", false);
        template.putValue("xxx", 123123131);
        template.putValue("aaa", 2344234.4);
        template.putValue("aa", false);
        template.putValue("ccc", "32423423423");
        template.putValue("vvvv", "fdfgdfgdfg");
        template.putValue("lll", false);
        List<String> list = new ArrayList<>();
        list.add("3423423423");
        list.add("的身份的風格和");
        list.add("大師傅似的");
        list.add("士大夫士大夫會更好");
        template.putValue("list", list);

        String process = template.process();
        System.out.println(process);
    }

}
