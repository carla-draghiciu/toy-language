package model.expression;

import model.adt.MyDictionary;
import model.adt.MyIDictionary;
import model.exception.UndeclaredException;
import model.state.Memory;
import model.state.SymbolTable;
import model.type.Type;
import model.value.Value;

public record VariableExpression(String name) implements Expression {
    @Override
    public Value evaluate(SymbolTable symbolTable, Memory heapTable) {
        if (!symbolTable.isDefined(name)) {
            throw new UndeclaredException("Variable " + name + " is not defined");
        }
        return symbolTable.getVariableValue(name);
    }

    @Override
    public Type typecheck(MyDictionary<String,Type> typeEnv) {
        return typeEnv.getElement(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
