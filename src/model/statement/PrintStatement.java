package model.statement;

import model.adt.MyDictionary;
import model.adt.MyIDictionary;
import model.expression.Expression;
import model.state.ProgramState;
import model.type.Type;

public record PrintStatement(Expression expression) implements Statement {
    @Override
    public ProgramState execute(ProgramState state) {
        state.out().add(expression.evaluate(state.symTable(), state.heapTable()));
        return null;
    }

    @Override
    public  MyDictionary<String, Type> typecheck(MyDictionary<String,Type> typeEnv) {
        expression.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "print(" + expression + ")";
    }
}
