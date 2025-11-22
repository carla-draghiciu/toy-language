package model.adt;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<K, T> implements MyIHeap<K, T> {
    private final Map<K, T> elements = new HashMap<>();

    @Override
    public void add(K key, T value) {
        elements.put(key, value);
    }

    @Override
    public T get(K key) {
        return elements.get(key);
    }

    @Override
    public boolean exists(K key) {
        return elements.containsKey(key);
    }

    @Override
    public String toString() {
        return elements.toString();
    }
}
