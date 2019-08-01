package com.join.template.reader;

import com.join.template.configuration.Configuration;
import com.join.template.node.Element;
import com.join.template.parser.Parser;


public interface Reader {

    int read(Element root, String text, Parser parser);

    int getLineSize();

    Configuration getConfiguration();


}
