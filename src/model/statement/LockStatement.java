package model.statement;

import model.adt.MyDictionary;
import model.exception.MismatchException;
import model.exception.UndeclaredException;
import model.state.ProgramState;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;
import model.value.Value;

public record LockStatement(String varName) implements Statement {
    @Override
    public ProgramState execute(ProgramState state) {
        state.lockTable().getLock().lock();
        try {
            if (state.symTable().isDefined(varName)) {
                if (state.symTable().getVariableType(varName) instanceof IntType) {
                    Value foundIndex = state.symTable().getVariableValue(varName);
                    IntValue index = (IntValue) foundIndex;
                    int idx = index.value();

                    if (state.lockTable().exists(idx)) {
                        int id = state.lockTable().getID(idx);
                        if (id == -1) {
                            state.lockTable().setID(idx, state.getId());
                        } else {
                            state.execStack().push(new LockStatement(varName));
                        }
                    } else {
                        throw new UndeclaredException("No lock on this variable");
                    }

                } else {
                    throw new MismatchException("Variable " + varName + " is not a int");
                }
            } else {
                throw new UndeclaredException("Variable is not declared");
            }
        } finally {
            state.lockTable().getLock().unlock();
        }

        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String,Type> typeEnv) {
        // Implement the method typecheck for the statement lock(var) to
        // verify if var has the type int
        if (typeEnv.getElement(varName) instanceof IntType) {
            return typeEnv;
        }
        throw new MismatchException("Variable " + varName + " is not a int");
    }
}
