package model.expression;

import model.exception.MismatchException;
import model.exception.UnknownInputException;
import model.state.SymbolTable;
import model.value.BoolValue;
import model.value.Value;

public record LogicExpression(Expression left, Expression right, String operator) implements Expression {
    @Override
    public Value evaluate(SymbolTable symbolTable) {
        Value leftValue = left.evaluate(symbolTable);
        Value rightValue = right.evaluate(symbolTable);

        if (!(leftValue instanceof BoolValue && rightValue instanceof BoolValue)) {
            throw new MismatchException("Not a boolean value");
        }

        switch (operator) {
            case "and": return new BoolValue(((BoolValue)leftValue).value() && ((BoolValue)rightValue).value());
            case "or": return new BoolValue(((BoolValue)leftValue).value() || ((BoolValue)rightValue).value());
            default: throw new UnknownInputException("Unknown operator");
        }
    }

    @Override
    public String toString() {
        return left.toString() + " " + operator + " " + right.toString();
    }
}
