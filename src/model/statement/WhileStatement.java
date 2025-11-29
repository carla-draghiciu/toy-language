package model.statement;

import model.exception.MismatchException;
import model.expression.Expression;
import model.state.ProgramState;
import model.value.BoolValue;
import model.value.Value;

public record WhileStatement(Expression condition, Statement statement) implements Statement {
    @Override
    public ProgramState execute(ProgramState state) {
        Value cond_val = condition.evaluate(state.symTable(), state.heapTable());
        if (!(cond_val instanceof BoolValue)) {
            throw new MismatchException("Condition expression is not boolean");
        }

        BoolValue bool_val = (BoolValue) cond_val;
        if (!bool_val.value()) {
            return null;
        }

        state.execStack().push(this);
        state.execStack().push(statement);
        return null;
    }

    @Override
    public String toString() {
        return "(while(" + condition + ") " + statement.toString() + ")";
    }
}
