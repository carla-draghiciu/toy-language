package model.state;

import model.exception.EmptyStackException;
import model.statement.Statement;

public class ProgramState {
    private static int prevID = 1;

    private final int id;
    private final ExecutionStack execStack;
    private final SymbolTable symTable;
    private final Out out;
    private final FileTable fileTable;
    private final Memory heapTable;

    public ProgramState(ExecutionStack execStack, SymbolTable symTable, Out out, FileTable fileTable, Memory heapTable) {
        this.execStack = execStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heapTable = heapTable;

        this.id = prevID++;
    }

    public int getId() {
        return id;
    }

    public ExecutionStack execStack() {
        return execStack;
    }

    public SymbolTable symTable() {
        return symTable;
    }

    public Out out() {
        return out;
    }

    public FileTable fileTable() {
        return fileTable;
    }

    public Memory heapTable() {
        return heapTable;
    }

    @Override
    public String toString() {
//        return "\n    Id:    " + id + "\n" +
//                "ExeStack:  " + execStack.toString() + "\n" +
//                "SymTable:  " + symTable.toString() + "\n" +
//                "Out:       " + out.toString() + "\n" +
//                "FileTable: " + fileTable.toString() + "\n" +
//                "HeapTable: " + heapTable.toString() + "\n";
        return "\n    Id:    " + id + "\n" +
                execStack.toString() +
                symTable.toString() +
                out.toString() +
                fileTable.toString() +
                heapTable.toString();
    }

    public boolean isNotCompleted() {
        return ! execStack.isEmpty();
    }

    public ProgramState oneStep() {
        if (execStack().isEmpty()) {
            throw new EmptyStackException("STack is empty");
        }

        Statement nextStep =  execStack().pop();
        return nextStep.execute(this);
    }
}
