package com.join.template.process;

import com.join.template.configuration.Configuration;
import com.join.template.factory.JoinFactory;

public abstract class AbstractProcess implements Process {
    protected Configuration configuration;
    protected JoinFactory joinFactory;

    public AbstractProcess(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
        this.configuration = joinFactory.getConfiguration();
    }

}