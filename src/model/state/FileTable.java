package model.state;

import model.value.StringValue;

import java.io.BufferedReader;

public interface FileTable {
    boolean isDefined(StringValue name);
    void addFile(StringValue name, BufferedReader br);
    BufferedReader getFile(StringValue name);
    void removeFile(StringValue name);
}
