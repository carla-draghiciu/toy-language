package model.adt;

import model.exception.EmptyStackException;

public interface MyIStack<T> {
    void push(T element);
    T pop() throws EmptyStackException;
    boolean isEmpty();
}
