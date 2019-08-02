package com.join.template.configuration;


import com.join.template.parser.Parser;
import com.join.template.process.Process;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ExprConfig {
    /**
     * 标记
     */
    private String tag;
    /**
     * 标记
     */
    private String nodeType;
    /**
     * 解析器
     */
    private Parser parser;
    /**
     * 处理器
     */
    private Process process;
}
