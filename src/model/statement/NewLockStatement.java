package model.statement;

import model.adt.MyDictionary;
import model.exception.MismatchException;
import model.exception.UndeclaredException;
import model.state.ProgramState;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;

public record NewLockStatement(String varName) implements Statement {
    @Override
    public ProgramState execute(ProgramState state) {
        // LockTable2 = LockTable1 synchronizedUnion {newfreelocation ->-1}
        // if var exists in SymTable1 and has the type int
        // then SymTable2 = update(SymTable1,var, newfreelocation)
        // else print an error and stop the execution.
        state.lockTable().getLock().lock();
        try {
            state.lockTable().add(-1);
            int location = state.lockTable().getNewFreeLocation();
            if (state.symTable().isDefined(varName)) {
                if (state.symTable().getVariableType(varName) instanceof IntType) {
                    state.symTable().setValue(varName, new IntValue(location));
                } else {
                    throw new MismatchException("Variable is not int");
                }
            } else {
                throw new UndeclaredException("Variable is not defined");
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
