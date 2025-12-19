package model.statement;

import model.adt.MyDictionary;
import model.adt.MyIDictionary;
import model.state.ProgramState;
import model.type.Type;

public record CompoundStatement(Statement left, Statement right) implements Statement {
    @Override
    public ProgramState execute(ProgramState state) {
        state.execStack().push(right);
        state.execStack().push(left);
        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String,Type> typeEnv) {
        //MyIDictionary<String,Type> typEnv1 = first.typecheck(typeEnv);
        //MyIDictionary<String,Type> typEnv2 = snd.typecheck(typEnv1);
        //return typEnv2;
        return right.typecheck(left.typecheck(typeEnv));
    }

    @Override
    public String toString() {
//        return left.toString() + "\n" + right.toString();
        return "(" + left.toString() + " ; " + right.toString() + ")";
    }
}
