package model.state;

import model.statement.Statement;

public record ProgramState(ExecutionStack execStack,
                           SymbolTable symTable,
                           Out out,
                           FileTable fileTable) {
    @Override
    public String toString() {
        return "ExeStack:\n" + execStack.toString() + "\n" +
                "SymTable:\n" + symTable.toString() + "\n" +
                "Out:\n" + out.toString() + "\n" +
                "FileTable:\n" + fileTable.toString() + "\n";
    }
}
