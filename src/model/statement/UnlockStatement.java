package model.statement;

import model.adt.MyDictionary;
import model.exception.MismatchException;
import model.exception.UndeclaredException;
import model.state.ProgramState;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;
import model.value.Value;

public record UnlockStatement(String varName) implements Statement {
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

                        if (id == state.getId()) {
                            state.lockTable().setID(idx, -1);
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
        return typeEnv;
    }
}
