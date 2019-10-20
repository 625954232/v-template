package com.join.template.html;


import com.alibaba.fastjson.JSON;
import com.join.template.core.element.Element;
import com.join.template.core.Template;
import com.join.template.core.constant.EntityType;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.grammar.generate.GrammarField;
import com.join.template.core.grammar.GrammarGenerate;
import com.join.template.core.grammar.GrammarInfo;
import com.join.template.core.listener.GrammarGenListener;
import com.join.template.core.type.TypeInfo;
import com.join.template.core.util.IOUtil;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Main {
    @Test
    public void process() {
        long start = System.currentTimeMillis();

        TextJoinFactoryBuilder joinFactoryBuilder = new TextJoinFactoryBuilder();
        JoinFactory joinFactory = joinFactoryBuilder.build();

        Map<Integer, String> grammars = joinFactory.createGrammarGenerate().generateGrammarExplain();
        System.out.println("语法解释：" + grammars.toString());

        Template template = joinFactory.getTemplate("/preview.html");
        System.out.println("语法解析耗时：" + (System.currentTimeMillis() - start));

        List<Element> elements = template.getAllElement();
        System.out.println("全部节点：" + elements.size());

        Element root = template.getRootElement();
        System.out.println("顶层节点：" + root.toString());

        template.putValue("iftext", 4);
        template.putValue("html", "测试文本");
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

    @Test
    public void grammarGenerate() {
        TextJoinFactoryBuilder joinFactoryBuilder = new TextJoinFactoryBuilder();
        JoinFactory joinFactory = joinFactoryBuilder.build();


        GrammarGenerate grammarGenerate = joinFactory.createGrammarGenerate();
        grammarGenerate.setGrammarGenListener(grammarGenListener);

        GrammarField grammarField = new GrammarField();
        grammarField.setNameField("filedKey");
        grammarField.setTypeField("type");
        grammarField.setDescribeField("filedValue");
        grammarField.setChildField("addChild");
        grammarGenerate.setGrammarField(grammarField);

        URL resource = Main.class.getResource("/gramma.json");
        String string = IOUtil.toString(resource);
        Map<String, List<Map>> maps = JSON.parseObject(string, Map.class);
        for (Map.Entry<String, List<Map>> entry : maps.entrySet()) {
            grammarGenerate.generateGrammarRoot(entry.getKey(), entry.getValue());

        }
        List<GrammarInfo> list = grammarGenerate.getGrammarInfos();
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void generateGrammarPreview() {
        TextJoinFactoryBuilder joinFactoryBuilder = new TextJoinFactoryBuilder();
        JoinFactory joinFactory = joinFactoryBuilder.build();

        GrammarGenerate grammarGenerate = joinFactory.createGrammarGenerate();
        grammarGenerate.setGrammarGenListener(grammarGenListener);
        grammarGenerate.setGrammarField(grammarField);

        URL gramma = Main.class.getResource("/gramma.json");
        URL preview = Main.class.getResource("/preview.html");
        String grammaStr = IOUtil.toString(gramma);
        String previewStr = IOUtil.toString(preview);
        Map<String, List<Map>> maps = JSON.parseObject(grammaStr, Map.class);
        for (Map.Entry<String, List<Map>> entry : maps.entrySet()) {
            grammarGenerate.generateGrammarRoot(entry.getKey(), entry.getValue());
        }
        String preview1 = grammarGenerate.preview(previewStr, 4);
        System.out.println(preview1);
    }


    private GrammarField grammarField = new GrammarField() {{
        this.setNameField("filedKey");
        this.setTypeField("type");
        this.setDescribeField("filedValue");
        this.setChildField("addChild");
    }};

    private GrammarGenListener grammarGenListener = new GrammarGenListener() {

        @Override
        public void onCreate(Map map, GrammarInfo grammarInfo) {
            if ("agent".equals(grammarInfo.getName())) {
                grammarInfo.describe("代理人信息");
                grammarInfo.type(EntityType.Array);
            } else if ("respondent".equals(grammarInfo.getName())) {
                grammarInfo.describe("被申请人信息");
                grammarInfo.type(EntityType.Array);
            } else if ("applicant".equals(grammarInfo.getName())) {
                grammarInfo.describe("申请人信息");
                grammarInfo.type(EntityType.Array);
            } else {
                grammarInfo.type(EntityType.String);
            }
        }

        @Override
        public void onCreate(TypeInfo typeInfo, GrammarInfo grammarInfo) {

        }

        @Override
        public void onPreview(GrammarInfo grammarInfo, Object value, Map<String, Object> map) {
            if (EntityType.Entity != grammarInfo.getType() && EntityType.Array != grammarInfo.getType())
                if (StringUtils.isNotBlank(grammarInfo.getDescribe())) {
                    map.put(grammarInfo.getName(), grammarInfo.getDescribe());
                } else {
                    map.put(grammarInfo.getName(), grammarInfo.getName());
                }
        }
    };
}
