package model.statement;

import model.exception.MismatchException;
import model.expression.Expression;
import model.state.ProgramState;
import model.type.BoolType;
import model.type.Type;
import model.value.BoolValue;
import model.value.Value;

public record IfStatement(Expression condition, Statement thenStatement, Statement elseStatement) implements Statement {
    @Override
    public ProgramState execute(ProgramState state) {
        Value val = condition.evaluate(state.symTable(), state.heapTable());
        if (!(val.getType() instanceof BoolType)) {
            throw new MismatchException("If condition is not boolean");
        }

        BoolValue booleanVal = (BoolValue) val;
        Statement chosenStatement = booleanVal.value() ? thenStatement : elseStatement;
        state.execStack().push(chosenStatement);
        return null;
    }

    public String toString() {
        return "(if( " + condition + ") then(" + thenStatement + ") else(" + elseStatement + "))";
    }
}
