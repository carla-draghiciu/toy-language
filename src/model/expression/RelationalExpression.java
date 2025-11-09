package model.expression;

import model.exception.MismatchException;
import model.exception.UnknownInputException;
import model.state.SymbolTable;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.Value;

public record RelationalExpression(Expression left, Expression right, String operator) implements Expression {
    @Override
    public Value evaluate(SymbolTable symbolTable) {
        Value l = left.evaluate(symbolTable);
        Value r = right.evaluate(symbolTable);
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
