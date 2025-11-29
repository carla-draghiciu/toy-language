package model.state;

import model.adt.MyDictionary;
import model.type.Type;
import model.value.Value;

import java.util.HashMap;
import java.util.Map;



public class MapSymbolTable implements SymbolTable {
    private final MyDictionary<String, Value> symbolTable = new MyDictionary<String, Value>();

    @Override
    public void declareVariable(Type type, String name) {
        symbolTable.add(name, type.getDefaultValue());
    }

    @Override
    public Value getVariableValue(String name) {
        return symbolTable.getElement(name);
    }

    @Override
    public Type getVariableType(String name) {
        return symbolTable.getElement(name).getType();
    }

    @Override
    public void setValue(String name, Value value) {
        symbolTable.add(name, value);
    }

    @Override
    public boolean isDefined(String name) {
        return symbolTable.contains(name);
    }

    @Override
    public MyDictionary<String, Value> getDict() {
        return symbolTable;
    }

    @Override
    public SymbolTable cloneTable() {
        var newsymbolTable = new MapSymbolTable();
        newsymbolTable.getDict().putAll(symbolTable);
        return newsymbolTable;
    }

    @Override
    public String toString() {
        return "SymTable:  " + symbolTable.toString() + "\n";
    }
}
