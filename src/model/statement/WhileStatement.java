package model.statement;

import model.adt.MyDictionary;
import model.exception.MismatchException;
import model.expression.Expression;
import model.state.ProgramState;
import model.type.BoolType;
import model.type.Type;
import model.value.BoolValue;
import model.value.Value;

public record WhileStatement(Expression condition, Statement statement) implements Statement {
    @Override
    public ProgramState execute(ProgramState state) {
        Value cond_val = condition.evaluate(state.symTable(), state.heapTable());
        if (!(cond_val instanceof BoolValue)) {
            throw new MismatchException("Condition expression is not boolean");
        }

        BoolValue bool_val = (BoolValue) cond_val;
        if (!bool_val.value()) {
            return null;
        }

        state.execStack().push(this);
        state.execStack().push(statement);
        return null;
    }

    private MyDictionary<String, Type> clone(MyDictionary<String, Type> dictionary) {
        var clona = new MyDictionary<String, Type>();
        clona.putAll(dictionary);
        return clona;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) {
        Type typeCond = condition.typecheck(typeEnv);
        if (typeCond.equals(new BoolType())) {
            statement.typecheck(clone(typeEnv));
            return typeEnv;
        } else {
            throw new MismatchException("The condition of WHILE is not of type bool");
        }
    }


    @Override
    public String toString() {
        return "(while(" + condition + ") " + statement.toString() + ")";
    }
}
