package model.state;

import model.adt.MyDictionary;
import model.value.StringValue;

import java.io.BufferedReader;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MapLockTable implements LockTable {
    private final MyDictionary<Integer, Integer> lockTable = new MyDictionary<Integer, Integer>();
    private int newFreeLocation;
    private Lock lock = new ReentrantLock();

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

    @Override
    public Lock getLock() {
        return lock;
    }

    @Override
    public boolean exists(int key) {
        return lockTable.contains(key);
    }

    @Override
    public int getID(int key) {
        return lockTable.getElement(key);
    }

    @Override
    public void setID(int key, int id) {
        lockTable.remove(key);
        lockTable.add(key, id);
    }
}
