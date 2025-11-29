package model.state;

import model.adt.MyDictionary;
import model.value.StringValue;

import java.io.BufferedReader;

public class MapFileTable implements FileTable {
    private final MyDictionary<StringValue, BufferedReader> fileTable = new MyDictionary<StringValue, BufferedReader>();

    @Override
    public boolean isDefined(StringValue name) {
        return fileTable.contains(name);
    }

    @Override
    public void addFile(StringValue name, BufferedReader br) {
        fileTable.add(name, br);
    }

    @Override
    public BufferedReader getFile(StringValue name) {
        return fileTable.getElement(name);
    }

    @Override
    public void removeFile(StringValue name) {
        fileTable.remove(name);
    }

    @Override
    public String toString() {
        return "FileTable: " + fileTable.toString() + "\n";
    }
}
