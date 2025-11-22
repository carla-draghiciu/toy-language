package model.statement;

import model.exception.MismatchException;
import model.exception.UndeclaredException;
import model.expression.Expression;
import model.state.ProgramState;
import model.type.RefType;
import model.value.RefValue;
import model.value.Value;

public record NewStatement(String var_name, Expression expression) implements Statement {
    @Override
    public ProgramState execute(ProgramState state) {
        if (!state.symTable().isDefined(var_name)) {
            throw new UndeclaredException("Variable " + var_name + " is not defined");
        }
        if (!((state.symTable().getVariableType(var_name)) instanceof RefType)) {
            throw new MismatchException("Variable " + var_name + " is not of type RefType");
        }
        Value exp_value = expression.evaluate(state.symTable(), state.heapTable());
        RefValue var_value = (RefValue) state.symTable().getVariableValue(var_name);
        if (exp_value.getType().getClass() != var_value.getLocationType().getClass()) {
            throw new MismatchException("Variable " + var_name + " is not of type " + exp_value.getClass());
        }

        int location = state.heapTable().getNewFreeLocation();
        state.heapTable().addEntry(location, exp_value);
        state.heapTable().updateNextFreeLocation();
        state.symTable().setValue(var_name, new RefValue(location, var_value.getLocationType()));
        return state;
    }

    @Override
    public String toString() {
        return "new(" + var_name + ", " + expression + ")";
    }
}
