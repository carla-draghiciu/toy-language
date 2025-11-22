package model.expression;

import model.state.Memory;
import model.state.SymbolTable;
import model.value.Value;

public interface Expression {
    Value evaluate(SymbolTable symbolTable, Memory heapTable);
}
