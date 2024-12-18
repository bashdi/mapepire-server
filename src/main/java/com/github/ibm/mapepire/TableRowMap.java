package com.github.ibm.mapepire;

import java.util.*;

public class TableRowMap implements Map<String, Object> {

    private String[] columnNames;
    private Object[] values;

    public TableRowMap(String[] sharedColumnNames) {
        columnNames = sharedColumnNames;
        values = new Object[columnNames.length];
    }

    @Override
    public int size() {
        return values.length;
    }

    @Override
    public boolean isEmpty() {
        for (Object value : values) {
            if (value != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean containsKey(Object key) {
        for (String columnName : columnNames) {
            if (columnName.equals(key)) return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (Object v : values) {
            if (v == value) return true;
        }
        return false;
    }

    @Override
    public Object get(Object key) {
        int valueIndex = -1;
        for (int i = 0; i < columnNames.length; i++) {
            if (columnNames[i].equals(key)) {
                valueIndex = i;
            }
        }

        if (valueIndex < 0) return null;

        return values[valueIndex];
    }

    @Override
    public Object put(String key, Object value) {
        int valueIndex = -1;
        for (int i = 0; i < columnNames.length; i++) {
            if (columnNames[i].equals(key)) {
                valueIndex = i;
            }
        }

        if (valueIndex < 0) return null;

        Object previousValue = values[valueIndex];
        values[valueIndex] = value;
        return previousValue;
    }

    @Override
    public Object remove(Object key) {
        int valueIndex = -1;
        for (int i = 0; i < columnNames.length; i++) {
            if (columnNames[i].equals(key)) {
                valueIndex = i;
            }
        }

        if (valueIndex < 0) return null;

        Object previousValue = values[valueIndex];
        values[valueIndex] = null;
        return previousValue;
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        m.forEach(this::put);
    }

    @Override
    public void clear() {
        Arrays.fill(values, null);
    }

    @Override
    public Set<String> keySet() {
        return new HashSet<>(Arrays.asList(columnNames));
    }

    @Override
    public Collection<Object> values() {
        return Arrays.asList(values);
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return new TableRowSet(columnNames, values);
    }


}
