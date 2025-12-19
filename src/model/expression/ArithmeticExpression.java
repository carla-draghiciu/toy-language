package model.expression;

import model.adt.MyDictionary;
import model.adt.MyIDictionary;
import model.exception.DivisionException;
import model.exception.MismatchException;
import model.exception.UnknownInputException;
import model.state.Memory;
import model.state.SymbolTable;
import model.type.IntType;
import model.type.Type;
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
    public Type typecheck(MyDictionary<String,Type> typeEnv){
        Type typ1, typ2;
        typ1=left.typecheck(typeEnv);
        typ2=right.typecheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new IntType();
            } else
            throw new MismatchException("second operand is not an integer");
        }else
        throw new MismatchException("first operand is not an integer");

    }


    @Override
    public String toString() {
        return left.toString() + " " + operator + " " + right.toString();
    }
}
