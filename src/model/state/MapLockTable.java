package model.state;

import model.adt.MyDictionary;
import model.value.StringValue;

import java.io.BufferedReader;

public class MapLockTable implements LockTable {
    private final MyDictionary<Integer, Integer> lockTable = new MyDictionary<Integer, Integer>();

}
