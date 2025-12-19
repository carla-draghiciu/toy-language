package model.expression;

import model.adt.MyDictionary;
import model.adt.MyIDictionary;
import model.exception.MismatchException;
import model.exception.UndeclaredException;
import model.state.Memory;
import model.state.SymbolTable;
import model.type.RefType;
import model.type.Type;
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
    public Type typecheck(MyDictionary<String,Type> typeEnv) {
        Type typ=expression.typecheck(typeEnv);
        if (typ instanceof RefType) {
            RefType reft =(RefType) typ;
            return reft.getInner();
        } else
            throw new MismatchException("the rH argument is not a Ref Type");
    }

    @Override
    public String toString() {
        return "rH(" + expression + ")";
    }
}
