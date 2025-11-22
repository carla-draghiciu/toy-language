package model.expression;

import model.exception.DivisionException;
import model.exception.MismatchException;
import model.exception.UnknownInputException;
import model.state.Memory;
import model.state.SymbolTable;
import model.value.IntValue;
import model.value.Value;

public record ArithmeticExpression(Expression left, Expression right, char operator) implements Expression {
    @Override
    public Value evaluate(SymbolTable symbolTable, Memory heapTable) {
        Value leftValue = left.evaluate(symbolTable, heapTable);
        Value rightValue = right.evaluate(symbolTable, heapTable);

        if (!(leftValue instanceof IntValue && rightValue instanceof IntValue)) {
            throw new MismatchException("Not an integer");
        }

        switch (operator) {
            case '+': return new IntValue(((IntValue)leftValue).value() + ((IntValue)rightValue).value());
            case '-': return new IntValue(((IntValue)leftValue).value() - ((IntValue)rightValue).value());
            case '*': return new IntValue(((IntValue)leftValue).value() * ((IntValue)rightValue).value());
            case '/': return divide(leftValue, rightValue);
            default: throw new UnknownInputException("Unknown operator");
        }
    }

    private Value divide(Value leftValue, Value rightValue) {
        int leftTerm = ((IntValue)leftValue).value();
        int rightTerm = ((IntValue)rightValue).value();
        if (rightTerm == 0) {
            throw new DivisionException("Cannot divide by zero");
        }

        return new IntValue(leftTerm / rightTerm);
    }

    @Override
    public String toString() {
        return left.toString() + " " + operator + " " + right.toString();
    }
}
