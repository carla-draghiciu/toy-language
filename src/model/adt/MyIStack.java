package model.adt;

import model.exception.EmptyStackException;

import java.util.Iterator;

public interface MyIStack<T> {
    void push(T element);
    T pop() throws EmptyStackException;
    boolean isEmpty();
    Iterator<T> iterator();
}
