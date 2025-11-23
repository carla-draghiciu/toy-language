package model.state;

import model.statement.Statement;

public record ProgramState(ExecutionStack execStack,
                           SymbolTable symTable,
                           Out out,
                           FileTable fileTable,
                           Memory heapTable) {
    @Override
    public String toString() {
        return "\nExeStack:  " + execStack.toString() + "\n" +
                "SymTable:  " + symTable.toString() + "\n" +
                "Out:       " + out.toString() + "\n" +
                "FileTable: " + fileTable.toString() + "\n" +
                "HeapTable: " + heapTable.toString() + "\n";
    }
}
