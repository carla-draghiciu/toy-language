package model.state;

import model.adt.MyHeap;
import model.value.Value;

import java.util.HashSet;
import java.util.Set;

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
    public boolean addressExists(int address) {
        return memory.exists(address);
    }

    @Override
    public Value getValue(int location) {
        return  memory.get(location);
    }

    @Override
    public void garbage(Set<Integer> addresses) {
        Set<Integer> toBeRemoved = new HashSet<Integer>();
        for (var elem : memory.entrySet()) {
            if (!addresses.contains(elem.getKey())) {
                toBeRemoved.add(elem.getKey());
            }
        }

        for (var key : toBeRemoved) {
            memory.remove(key);
        }
    }

    @Override
    public MyHeap<Integer, Value> getHeap() {
        return memory;
    }

    @Override
    public String toString() {
        return "HeapTable: " + memory.toString() + "\n";
    }
}
