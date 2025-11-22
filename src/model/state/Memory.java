package model.state;

import model.value.Value;

public interface Memory {
    int getNewFreeLocation();
    void updateNextFreeLocation();
    void addEntry(int location, Value value);
    boolean addressExists(int address);
    Value getValue(int location);
}
