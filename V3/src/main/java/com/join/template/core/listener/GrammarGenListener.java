package com.join.template.core.listener;

import com.join.template.core.grammar.FieldName;
import com.join.template.core.grammar.EntityGrammarInfo;

import java.util.Map;

/**
 * @author CAOYOU
 * @Title: GrammarGenListener
 * @date 2019/8/1916:51
 */
public interface GrammarGenListener {

    void onCreate(Map map, FieldName entityField, EntityGrammarInfo entityGrammarInfo);
}
