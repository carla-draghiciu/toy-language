package model.expression;

import model.exception.UndeclaredException;
import model.state.SymbolTable;
import model.value.Value;

public record VariableExpression(String name) implements Expression {
    @Override
    public Value evaluate(SymbolTable symbolTable) {
        if (!symbolTable.isDefined(name)) {
            throw new UndeclaredException("Variable " + name + " is not defined");
        }
        return symbolTable.getVariableValue(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
