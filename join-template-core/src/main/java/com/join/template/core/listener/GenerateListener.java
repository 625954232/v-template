package com.join.template.core.listener;

import com.join.template.core.grammar.GrammarInfo;
import com.join.template.core.grammar.generate.FieldGenerateInfo;
import com.join.template.core.grammar.generate.GenerateConfig;
import com.join.template.core.util.type.TypeInfo;

import java.util.Map;

/**
 * @author CAOYOU
 * @Title: GrammarGenListener
 * @date 2019/8/1916:51
 */
public interface GenerateListener<T extends FieldGenerateInfo> {

    T onCreate(Map map, GenerateConfig generateConfig);

    T onCreate(TypeInfo typeInfo, GenerateConfig generateConfig);

    void onPreview(T grammarInfo, Object value, Map<String, Object> map);
}
