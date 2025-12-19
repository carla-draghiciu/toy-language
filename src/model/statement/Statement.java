package model.statement;

import model.adt.MyDictionary;
import model.adt.MyIDictionary;
import model.state.ProgramState;
import model.type.Type;

public interface Statement {
    ProgramState execute(ProgramState state);
    MyDictionary<String, Type> typecheck(MyDictionary<String,Type> typeEnv);
}
