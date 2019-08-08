package com.join.template;

import com.join.template.core.JoinFactoryBuilder;
import com.join.template.core.Template;
import com.join.template.node.Element;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        JoinFactoryBuilder joinFactoryBuilder = new JoinFactoryBuilder();
        joinFactoryBuilder.builder();
    }

}
