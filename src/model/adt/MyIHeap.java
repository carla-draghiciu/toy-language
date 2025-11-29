package model.adt;

import java.util.Map;
import java.util.Set;

public interface MyIHeap<K, T> {
    void add(K key, T item);
    T get(K key);
    boolean exists(K key);
    void remove(K key);
    public Iterable<Map.Entry<K,T>> entrySet();
}
