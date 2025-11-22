package model.adt;

public interface MyIHeap<K, T> {
    void add(K key, T item);
    T get(K key);
    boolean exists(K key);
}
