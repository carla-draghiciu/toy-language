package model.state;

import model.adt.MyHeap;
import model.value.Value;

import java.util.Set;

public interface Memory {
    int getNewFreeLocation();
    void updateNextFreeLocation();
    void addEntry(int location, Value value);
    boolean addressExists(int address);
    Value getValue(int location);
    void garbage(Set<Integer> addresses);
    MyHeap<Integer, Value> getHeap();
}
