package model.adt;

import model.value.Value;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K, T> implements MyIDictionary<K, T> {
    private final Map<K, T> symbolTable = new HashMap<K, T>();

    @Override
    public void add(K key, T value) {
        symbolTable.put(key, value);
    }

    @Override
    public T getElement(K key) {
        return symbolTable.get(key);
    }

    @Override
    public boolean contains(K key) {
        return symbolTable.containsKey(key);
    }

    @Override
    public void remove(K key) {
        symbolTable.remove(key);
    }

    @Override
    public Iterable<Map.Entry<K,T>> entrySet() {
        return symbolTable.entrySet();
    }

    @Override
    public String toString() {
        return symbolTable.toString();
    }
}
