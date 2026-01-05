package model.state;

import model.adt.MyList;
import model.value.Value;

public interface Out {
    void add(Value value);
    MyList<Value> getArrOut();
}
