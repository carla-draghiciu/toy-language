package model.state;

public interface LockTable {
    int getNewFreeLocation();
    void updateNextFreeLocation();
    void add(Integer val);
}
