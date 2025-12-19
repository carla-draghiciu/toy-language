package model.expression;

import model.adt.MyDictionary;
import model.adt.MyIDictionary;
import model.state.Memory;
import model.state.SymbolTable;
import model.type.Type;
import model.value.Value;

public interface Expression {
    Value evaluate(SymbolTable symbolTable, Memory heapTable);
    Type typecheck(MyDictionary<String,Type> typeEnv);
}
