package com.join.template.core.process;

import com.join.template.core.Element;
import com.join.template.core.context.Content;

import java.io.Writer;
/**  
 * @Title: 处理器
 * @author CAOYOU/625954232@qq.com
 * @date 2019/8/19 12:18
 */
public interface Process {
    /**
     * 处理监听
     *
     * @param element 节点
     * @param context 参数
     * @param writer  写入
     * @return void
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:51
     */
    void process(Element element, Content context, Writer writer);
}
