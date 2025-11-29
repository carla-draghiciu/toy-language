package model.statement;

import model.state.*;

import java.io.File;

public record ForkStatement(Statement statement) implements Statement {
    @Override
    public ProgramState execute(ProgramState state) {
        ExecutionStack newES = new LinkedListExecutionStack();
        SymbolTable newST = new MapSymbolTable();
        Out newO = new ArrayListOut();
        FileTable newFT = new MapFileTable();
        Memory newM = new HeapMemory();

        newES.push(statement);
        var ps = new ProgramState(newES, newST, newO, newFT, newM);
        return ps;
    }
}
