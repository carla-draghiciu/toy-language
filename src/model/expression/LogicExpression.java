package model.expression;

import model.adt.MyDictionary;
import model.adt.MyIDictionary;
import model.exception.MismatchException;
import model.exception.UnknownInputException;
import model.state.Memory;
import model.state.SymbolTable;
import model.type.BoolType;
import model.type.IntType;
import model.type.Type;
import model.value.BoolValue;
import model.value.Value;

public record LogicExpression(Expression left, Expression right, String operator) implements Expression {
    @Override
    public Value evaluate(SymbolTable symbolTable, Memory heapTable) {
        Value leftValue = left.evaluate(symbolTable, heapTable);
        Value rightValue = right.evaluate(symbolTable, heapTable);

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
    public Type typecheck(MyDictionary<String,Type> typeEnv){
        Type typ1, typ2;
        typ1=left.typecheck(typeEnv);
        typ2=right.typecheck(typeEnv);
        if (typ1.equals(new BoolType())) {
            if (typ2.equals(new BoolType())) {
                return new BoolType();
            } else
                throw new MismatchException("second operand is not a boolean");
        }else
            throw new MismatchException("first operand is not a boolean");

    }

    @Override
    public String toString() {
        return left.toString() + " " + operator + " " + right.toString();
    }
}
