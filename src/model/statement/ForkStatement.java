package model.statement;

import model.state.*;

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
    } // filetable, out and heap are references??

    @Override
    public String toString() {
        return "fork(" + statement.toString() + ")";
    }
}
