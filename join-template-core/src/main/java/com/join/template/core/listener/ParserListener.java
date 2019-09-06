package com.join.template.core.listener;

import com.join.template.core.Element;
/**  
 * @Title: 解析监听器
 * @author CAOYOU/625954232@qq.com
 * @date 2019/8/19 11:58
 */
public interface ParserListener {

    /**
     * 解析监听
     *
     * @param element 节点
     * @return void
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:51
     */
    void onParser(Element element);
}
