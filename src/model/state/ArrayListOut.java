package model.state;

import model.adt.MyList;
import model.value.Value;

import java.util.ArrayList;
import java.util.List;

public class ArrayListOut implements Out {
    private final MyList<Value> values = new MyList<Value>();

    @Override
    public void add(Value value) {
        values.add(value);
    }

    @Override
    public MyList<Value> getArrOut() {
        return values;
    }

    @Override
    public String toString() {
        return "Out:       " + values.toString() + "\n";
    }
}
