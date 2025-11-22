package model.adt;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<K, T> implements MyIHeap<K, T> {
    private final Map<K, T> elements = new HashMap<>();

    @Override
    public void add(K key, T value) {
        elements.put(key, value);
    }
}
