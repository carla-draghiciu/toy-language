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
    public Type typecheck(MyDictionary<String,Type> typeEnv){
        Type typ1, typ2;
        typ1=left.typecheck(typeEnv);
        typ2=right.typecheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new BoolType();
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
