package model.statement;

import model.adt.MyDictionary;
import model.state.*;
import model.type.Type;

import java.io.File;

public record ForkStatement(Statement statement) implements Statement {
    @Override
    public ProgramState execute(ProgramState state) {
        ExecutionStack newES = new LinkedListExecutionStack();
        SymbolTable newST = state.symTable().cloneTable();
        Out newO = state.out();
        FileTable newFT = state.fileTable();
        Memory newM = state.heapTable();

        newES.push(statement);
        var ps = new ProgramState(newES, newST, newO, newFT, newM);
        return ps;
    }

    private MyDictionary<String, Type> clone(MyDictionary<String, Type> dictionary) {
        var clona = new MyDictionary<String, Type>();
        clona.putAll(dictionary);
        return clona;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) {
        // typecheck the forked statement in a CLONED environment
        statement.typecheck(clone(typeEnv));

        // fork does not change the parent's type environment
        return typeEnv;
    }


    @Override
    public String toString() {
        return "fork(" + statement.toString() + ")";
    }
}
