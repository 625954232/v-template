package com.join.template.core.configuration;

import com.join.template.core.constant.Constant;
import com.join.template.core.resource.ResourceFind;
import com.join.template.core.resource.ResourceInfo;
import com.join.template.core.verify.TemplateException;
import lombok.Data;

import java.io.IOException;

/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 模版引擎总配置
 * @date 2019/8/19 11:52
 */
@Data
public class Configuration {
    //值表达式截取开始标记
    private String varTagStart = "${";
    //值表达式截取结束标记
    private String varTagEnd = "}";
    //语法表达式开始标签首部截取标记
    private String exprFirstBegin = "<#";
    //语法表达式结束标签首部截取标记
    private String exprLastBegin = "</#";
    //表达式开始标签或结束标签尾部截取标记
    private String exprEndSupport = "#>";
    //属性截取开始符
    private String attStart = "='";
    //属性截取结束符
    private String attEnd = "'";
    //分隔符属性
    private String attSseparator = "separator";
    //值属性
    private String attVar = "var";
    //别名属性
    private String attName = "name";
    //单项别名属性
    private String attItem = "item";
    //语句开始属性
    private String attOpen = "open";
    //索引属性
    private String attIndex = "index";
    //语句结束属性
    private String attClose = "close";
    //引用文件属性
    private String attFile = "file";
    //判断条件属性
    private String attrText = "html";
    //格式化属性
    private String attrFormat = "format";
    //编码
    private String encoding;

    /**
     * 项目资源目录
     */
    private String resourcesDir;
    /**
     * 模版缓存大小
     */
    private Integer templateCacheSize;
    /**
     * 模版工厂类型
     */
    private String type;


    private ResourceFind resourceFind;

    public Configuration() {
        init();
    }

    /**
     * 初始化
     */
    public void init() {
        this.resourceFind = new ResourceFind();
        this.resourcesDir = "";
        this.encoding = "UTF-8";
        this.type = Constant.TYPE_MAP;
        this.templateCacheSize = 50;
    }


    public ResourceInfo getResource(String fileName) {
        try {
            return resourceFind.getURL(this.resourcesDir + fileName);
        } catch (IOException e) {
            throw new TemplateException("没有该文件", e);
        }
    }

    public String getUserDir() {
        return resourceFind.getUserDir();
    }
}
