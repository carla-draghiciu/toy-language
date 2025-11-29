package model.statement;

import model.exception.AlreadyDeclaredException;
import model.state.ProgramState;
import model.type.Type;

public record VariableDeclarationStatement(Type varType, String varName) implements Statement {
    @Override
    public ProgramState execute(ProgramState state) {
        if (state.symTable().isDefined(varName)) {
            throw new AlreadyDeclaredException("Variable " + varName + " is already defined");
        }
        state.symTable().declareVariable(varType, varName);
        return null;
    } // TODO: is var already defined?

    @Override
    public String toString() {
        return varType + " " + varName;
    }
}
