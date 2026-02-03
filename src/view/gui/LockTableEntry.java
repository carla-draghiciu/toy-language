package view.gui;

public class LockTableEntry {
    private final int location;
    private final int value;

    public LockTableEntry(int location, int value) {
        this.location = location;
        this.value = value;
    }

    public int getLocation() {
        return location;
    }

    public int getValue() {
        return value;
    }
}
