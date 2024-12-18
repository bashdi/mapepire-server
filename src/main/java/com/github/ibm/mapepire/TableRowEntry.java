package com.github.ibm.mapepire;

import java.util.AbstractMap;
import java.util.Map;

public class TableRowEntry implements Map.Entry<String, Object> {

    private String key;
    private Object value;

    public void changeEntry(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public Object setValue(Object value) {
        Object oldValue = this.value;
        this.value = value;
        return oldValue;
    }
}
