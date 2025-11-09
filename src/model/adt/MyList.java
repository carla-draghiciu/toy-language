package model.adt;

import model.value.Value;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    private final List<T> elements = new ArrayList<T>();

    @Override
    public void add(T elem) {
        elements.add(elem);
    }

    @Override
    public String toString() {
        return elements.toString();
    }
}
