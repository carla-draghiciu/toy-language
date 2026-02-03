package model.state;

import model.adt.MyDictionary;
import model.value.StringValue;

import java.io.BufferedReader;

public class MapLockTable implements LockTable {
    private final MyDictionary<Integer, Integer> lockTable = new MyDictionary<Integer, Integer>();
    private int newFreeLocation;

    public MapLockTable()
    {newFreeLocation=1;}

    @Override
    public int getNewFreeLocation() {
        return newFreeLocation;
    }

    @Override
    public void updateNextFreeLocation() {
        int i = 1;
        while (lockTable.contains(i)) {
            i++;
        }
        newFreeLocation = i;
    }

    @Override
    public void add(Integer val) {
        lockTable.add(newFreeLocation, val);
    }
}
