package model.statement;

import model.adt.MyDictionary;
import model.exception.MismatchException;
import model.state.ProgramState;
import model.type.BoolType;
import model.type.Type;

public class NoOperationStatement implements Statement {
    @Override
    public ProgramState execute(ProgramState state) {
        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String,Type> typeEnv) {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "";
    }
}
