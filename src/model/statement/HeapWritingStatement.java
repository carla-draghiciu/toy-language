package model.statement;

import model.exception.MismatchException;
import model.exception.UndeclaredException;
import model.expression.Expression;
import model.state.ProgramState;
import model.type.RefType;
import model.value.RefValue;
import model.value.Value;

public record HeapWritingStatement(String var_name, Expression expression) implements Statement {
    @Override
    public ProgramState execute(ProgramState state) {
        var symbolTable = state.symTable();
        var heapTable =  state.heapTable();
        if (!symbolTable.isDefined(var_name)) {
            throw new UndeclaredException("Variable " + var_name + " is not defined");
        }

        if (!(symbolTable.getVariableType(var_name) instanceof RefType)) {
            throw new MismatchException("Variable " + var_name + " is not of type RefType");
        }

        RefValue refVal = (RefValue) symbolTable.getVariableValue(var_name);
        if (!heapTable.addressExists(refVal.address())) {
            throw new UndeclaredException("Address " + refVal.address() + " does not exist");
        }

        Value exp_val = expression.evaluate(symbolTable, heapTable);
        if (exp_val.getType().getClass() != refVal.getLocationType().getClass()) {
            throw new MismatchException("Types don't match");
        }

        heapTable.addEntry(refVal.address(), exp_val);
        return state;
    }
}
