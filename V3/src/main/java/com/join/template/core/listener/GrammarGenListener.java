package com.join.template.core.listener;

import com.join.template.core.grammar.generate.GrammarField;
import com.join.template.core.grammar.GrammarInfo;
import com.join.template.core.type.TypeInfo;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author CAOYOU
 * @Title: GrammarGenListener
 * @date 2019/8/1916:51
 */
public interface GrammarGenListener {

    void onCreate(Map map, GrammarInfo grammarInfo);

    void onCreate(TypeInfo typeInfo, GrammarInfo grammarInfo);
}
