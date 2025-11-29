package model.statement;

import model.state.ProgramState;

public record CompoundStatement(Statement left, Statement right) implements Statement {
    @Override
    public ProgramState execute(ProgramState state) {
        state.execStack().push(right);
        state.execStack().push(left);
        return null;
    }

    @Override
    public String toString() {
//        return left.toString() + "\n" + right.toString();
        return "(" + left.toString() + " ; " + right.toString() + ")";
    }
}
