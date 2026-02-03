package model.state;

import model.adt.MyDictionary;

import java.util.concurrent.locks.Lock;

public interface LockTable {
    int getNewFreeLocation();
    void updateNextFreeLocation();
    void add(Integer val);
    Lock getLock();
    boolean exists(int key);
    int getID(int key);
    void setID(int key, int id);
    MyDictionary<Integer, Integer> getDict();
}
