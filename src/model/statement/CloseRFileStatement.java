package model.statement;

import model.adt.MyDictionary;
import model.exception.MismatchException;
import model.exception.TextFileException;
import model.exception.UndeclaredException;
import model.expression.Expression;
import model.state.ProgramState;
import model.type.StringType;
import model.type.Type;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;

public record CloseRFileStatement(Expression expression) implements Statement {
    @Override
    public ProgramState execute(ProgramState state) {
        Value val = expression.evaluate(state.symTable(), state.heapTable());
        if (!(val instanceof StringValue)) {
            throw new MismatchException("Not a string value");
        }
        StringValue str = (StringValue) val;

        if (!state.fileTable().isDefined(str)) {
            throw new UndeclaredException("File does not exist");
        }

        BufferedReader br = state.fileTable().getFile(str);
        try {
            br.close();
        } catch (Exception e) {
            throw new TextFileException(e.getMessage());
        }

        state.fileTable().removeFile(str);
        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) {
        Type typeExp = expression.typecheck(typeEnv);
        if (typeExp.equals(new StringType())) {
            return typeEnv;
        } else {
            throw new MismatchException("closeRFile argument is not a string");
        }
    }


    @Override
    public String toString() {
        return "closeRFile(" + expression + ")";
    }
}
