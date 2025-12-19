package model.expression;

import model.adt.MyDictionary;
import model.adt.MyIDictionary;
import model.state.Memory;
import model.state.SymbolTable;
import model.type.Type;
import model.value.Value;

public record ValueExpression(Value value) implements Expression {
    @Override
    public Value evaluate(SymbolTable symbolTable, Memory heapTable) {
        return value;
    }

    @Override
    public Type typecheck(MyDictionary<String,Type> typeEnv){
        return value.getType();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
