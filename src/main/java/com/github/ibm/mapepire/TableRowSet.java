package com.github.ibm.mapepire;

import java.util.*;

public class TableRowSet implements Set<Map.Entry<String, Object>> {

    private final String[] keys;
    private final Object[] values;

    public TableRowSet(String[] keys, Object[] values) {
        this.keys = keys;
        this.values = values;
    }

    @Override
    public int size() {
        return keys.length;
    }

    @Override
    public boolean isEmpty() {
        return keys.length == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Map.Entry<String, Object>> iterator() {
        return new Iterator<Map.Entry<String, Object>>() {
            private int currentPosition = 0;
            private final TableRowEntry entry = new TableRowEntry();

            @Override
            public boolean hasNext() {
                return currentPosition < keys.length;
            }

            @Override
            public Map.Entry<String, Object> next() {
                entry.changeEntry(keys[currentPosition], values[currentPosition++]);
                return entry;
            }
        };
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Map.Entry<String, Object> stringObjectEntry) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Map.Entry<String, Object>> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}
