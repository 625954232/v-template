package com.join.template.core.constant;

/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 常量
 * @date 2019/8/19 11:42
 */
public interface Constant {
    //单例模板
    String TYPE_SINGLE = "SINGLE";
    //MAP形式缓存模版
    String TYPE_MAP = "MAP";

    //表达式类型/节点类型-值
    int EXPR_VAR = 1;
    //表达式类型/节点类型-引入模版
    int EXPR_INCLUDE = 2;
    //表达式类型/节点类型-列表
    int EXPR_LIST = 3;
    //表达式类型/节点类型-判断总节点
    int EXPR_IF = 4;
    //表达式类型/节点类型-判断其他
    int EXPR_IF_ELSE = 5;
    //表达式类型/节点类型-其他判断
    int EXPR_IF_ELSE_IF = 6;
    //表达式类型/节点类型-顶层节点
    int EXPR_ROOT = 7;
    //表达式类型/节点类型-文本
    int EXPR_TEXT = 8;
    //表达式类型/节点类型-写入值
    int EXPR_SET = 9;
    //表达式类型/节点类型-读取值
    int EXPR_GET = 10;
}
