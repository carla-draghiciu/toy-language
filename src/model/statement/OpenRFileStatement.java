package model.statement;

import model.adt.MyDictionary;
import model.exception.AlreadyDeclaredException;
import model.exception.MismatchException;
import model.exception.TextFileException;
import model.expression.Expression;
import model.state.ProgramState;
import model.type.StringType;
import model.type.Type;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.FileReader;

public record OpenRFileStatement(Expression expression) implements Statement {
    @Override
    public ProgramState execute(ProgramState programState) {
        Value val = expression.evaluate(programState.symTable(), programState.heapTable());
        if (!(val instanceof StringValue)) {
            throw new MismatchException("Not a string value");
        }
        StringValue file = (StringValue) val;

        if (programState.fileTable().isDefined(file)) {
            throw new AlreadyDeclaredException("File is already open");
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(file.getVal()));
            programState.fileTable().addFile(file, br);
        } catch (Exception e) {
            throw new TextFileException(e.getMessage());
        }
        return null;
    }

    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String, Type> typeEnv) {
        Type typeExp = expression.typecheck(typeEnv);
        if (typeExp.equals(new StringType())) {
            return typeEnv;
        } else {
            throw new MismatchException("openRFile argument is not a string");
        }
    }


    @Override
    public String toString() {
        return "openRFile(" + expression + ")";
    }
}
