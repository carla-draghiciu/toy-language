package model.adt;

import java.util.Iterator;

public interface MyIList<T> {
    void add(T elem);
    Iterator<T> iterator();
}
