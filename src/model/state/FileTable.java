package model.state;

import model.adt.MyDictionary;
import model.value.StringValue;

import java.io.BufferedReader;

public interface FileTable {
    boolean isDefined(StringValue name);
    void addFile(StringValue name, BufferedReader br);
    BufferedReader getFile(StringValue name);
    MyDictionary<StringValue, BufferedReader> getFiles();
    void removeFile(StringValue name);
}
