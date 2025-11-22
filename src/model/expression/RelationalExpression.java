package model.expression;

import model.exception.MismatchException;
import model.exception.UnknownInputException;
import model.state.Memory;
import model.state.SymbolTable;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.Value;

public record RelationalExpression(Expression left, Expression right, String operator) implements Expression {
    @Override
    public Value evaluate(SymbolTable symbolTable, Memory heapTable) {
        Value l = left.evaluate(symbolTable, heapTable);
        Value r = right.evaluate(symbolTable, heapTable);
        if (!(l instanceof IntValue) || !(r instanceof IntValue)) {
            throw new MismatchException("Expressions must be integers");
        }

        IntValue leftVal = (IntValue) l;
        IntValue rightVal = (IntValue) r;
        switch (operator) {
            case "<": return new BoolValue(leftVal.value() < rightVal.value());
            case "<=": return new BoolValue(leftVal.value() <= rightVal.value());
            case ">":   return new BoolValue(leftVal.value() > rightVal.value());
            case ">=": return new BoolValue(leftVal.value() >= rightVal.value());
            case "==": return new BoolValue(leftVal.value() == rightVal.value());
            case "!=": return new BoolValue(leftVal.value() != rightVal.value());
            default: throw new UnknownInputException("Unknown operator");
        }
    }

    @Override
    public String toString() {
        return left.toString() + " " + operator + " " + right.toString();
    }
}
