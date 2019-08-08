package com.join.template.core.context;

import java.util.HashMap;
import java.util.Map;


public class HashContext implements Content {

    private Map context = null;

    public HashContext() {
        this.context = new HashMap();
    }

    @Override
    public Object get(String K) {
        return (context.containsKey(K) == true ? context.get(K) : null);
    }

    @Override
    public void put(String K, Object V) {
        context.put(K, V);
    }

    @Override
    public boolean hasKey(String K) {
        return context.containsKey(K);
    }

    @Override
    public void remove(String K) {
        context.remove(K);
    }

}
