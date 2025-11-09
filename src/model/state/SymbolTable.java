package model.state;

import model.type.Type;
import model.value.Value;

public interface SymbolTable {
    void declareVariable(Type type, String name);
    Value getVariableValue(String name);
    Type getVariableType(String name);
    void setValue(String name, Value value);
    boolean isDefined(String name);
}
