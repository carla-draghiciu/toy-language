package model.expression;

import model.exception.MismatchException;
import model.exception.UndeclaredException;
import model.state.Memory;
import model.state.SymbolTable;
import model.value.RefValue;
import model.value.Value;

public record ReadHeapExpression(Expression expression) implements Expression {
    @Override
    public Value evaluate(SymbolTable symbolTable, Memory heapTable) {
        Value exp_value = expression.evaluate(symbolTable, heapTable);
        if (!(exp_value instanceof RefValue)) {
            throw new MismatchException("Expression is not a reference value");
        }
        RefValue refVal = (RefValue) exp_value;
        int address = refVal.address();
        if (!heapTable.addressExists(address)) {
            throw new UndeclaredException("Address " + address + " does not exist");
        }

        return heapTable.getValue(address);
    }

    @Override
    public String toString() {
        return "rH(" + expression + ")";
    }
}
