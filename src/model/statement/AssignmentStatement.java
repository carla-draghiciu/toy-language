package model.statement;

import model.exception.MismatchException;
import model.exception.UndeclaredException;
import model.expression.Expression;
import model.state.ProgramState;
import model.type.Type;
import model.value.Value;

public record AssignmentStatement(String name, Expression expression) implements Statement {
    @Override
    public ProgramState execute(ProgramState state) {
        if (!state.symTable().isDefined(name)) {
            throw new UndeclaredException("Undefined variable");
        }
        Value exp = expression.evaluate(state.symTable());

        var rightType = exp.getType();
        var leftType = state.symTable().getVariableType(name);

        if (rightType.getClass() != leftType.getClass()) {
            throw new MismatchException("Different types");
        }

        state.symTable().setValue(name, exp);
        return state;
    }

    @Override
    public String toString() {
        return name + "=" +  expression;
    }
}
