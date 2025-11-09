package model.adt;

import model.exception.EmptyStackException;

import java.util.LinkedList;
import java.util.List;

public class MyStack<T> implements MyIStack<T> {
    private final List<T> elements = new LinkedList<T>();

    @Override
    public void push(T element)
    {
        elements.addFirst(element);
    }

    @Override
    public T pop() {
        if (elements.isEmpty())
            throw new EmptyStackException("Stack is empty");
        T elem = elements.removeFirst();
        return elem;
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public String toString() {
        String result = "{ ";
        for (int i = 0; i < elements.size() - 1; i++) {
            result += elements.get(i).toString() + " | ";
        }
        if (elements.size() > 0)
            result += elements.get(elements.size() - 1).toString();
        return result + " }";
    }
}
