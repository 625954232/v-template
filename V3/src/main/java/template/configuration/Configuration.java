package com.join.template.configuration;

import com.join.template.constant.Constant;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.io.File;

@Data
public class Configuration {
    //占位符开始标记
    private String varTagStart = "${";
    //占位符结束标记
    private String varTagEnd = "}";
    //表达式开始标签首部标记
    private String exprFirstBegin = "<#";
    //表达式结束标签首部标记
    private String exprLastBegin = "</#";
    //表达式开始标签或结束标签结束标记
    private String exprEndSupport = "#>";
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
    private String attrText = "text";
    //格式化属性
    private String attrFormat = "format";
    //编码
    private String encoding;


    /**
     * 项目根目录
     */
    private String userDir;
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


    public Configuration() {
        init();
    }

    /**
     * 初始化
     */
    public void init() {
        this.userDir = System.getProperty("user.dir");
        this.resourcesDir = getClass().getResource("/").getPath();
        this.encoding = "UTF-8";
        this.type = Constant.TYPE_MAP;
        this.templateCacheSize = 50;
    }

    /**
     * 获取资源文件
     *
     * @param fileName 文件名称
     * @return
     */
    public File getResource(String fileName) {
        if (!StringUtils.isBlank(this.userDir)) {
            File file = new File(this.userDir + fileName);
            if (file.exists()) {
                return file;
            }
        }
        if (!StringUtils.isBlank(this.resourcesDir)) {
            File file = new File(this.resourcesDir + fileName);
            if (file.exists()) {
                return file;
            }
        }
        return new File(fileName);
    }


}
