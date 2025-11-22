package model.state;

import model.adt.MyHeap;
import model.value.Value;

public class HeapMemory implements Memory {
    private final MyHeap<Integer, Value> memory = new MyHeap<Integer, Value>();
    private int newFreeLocation;

    public HeapMemory() {
        newFreeLocation = 1;
    }

    @Override
    public int getNewFreeLocation() {
        return newFreeLocation;
    }

    @Override
    public void updateNextFreeLocation() {
        int i = 1;
        while (memory.exists(i)) {
            i++;
        }
        newFreeLocation = i;
    }

    @Override
    public void addEntry(int location, Value value) {
        memory.add(location, value);
    }

    @Override
    public String toString() {
        return memory.toString();
    }
}
