package com.join.template.constant;

public interface Constant {
    //单例模板
    String TYPE_SINGLE = "SINGLE";
    //MAP形式缓存模版
    String TYPE_MAP = "MAP";

    //表达式类型/节点类型-值
    String EXPRESSION_VAR = "VAR";
    //表达式类型/节点类型-引入模版
    String EXPRESSION_INCLUDE = "INCLUDE";
    //表达式类型/节点类型-列表
    String EXPRESSION_LIST = "LIST";
    //表达式类型/节点类型-判断
    String EXPRESSION_IF_ROOT = "IF_ROOT";

    //表达式类型/节点类型-判断总节点
    String EXPRESSION_IF = "IF";
    //表达式类型/节点类型-判断其他
    String EXPRESSION_IF_ELSE = "ELSE";
    //表达式类型/节点类型-其他判断
    String EXPRESSION_IF_ELSE_IF = "ELSE_IF";
    //表达式类型/节点类型-顶层节点
    String EXPRESSION_ROOT = "ROOT";
    //表达式类型/节点类型-文本
    String EXPRESSION_TEXT = "TEXT";
    //表达式类型/节点类型-写入值
    String EXPRESSION_SET = "SET";
    //表达式类型/节点类型-读取值
    String EXPRESSION_GET = "GET";
}
