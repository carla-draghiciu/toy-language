package model.statement;

import model.exception.AlreadyDeclaredException;
import model.exception.MismatchException;
import model.exception.TextFileException;
import model.expression.Expression;
import model.state.ProgramState;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;
import java.io.FileReader;

public record OpenRFileStatement(Expression expression) implements Statement {
    @Override
    public ProgramState execute(ProgramState programState) {
        Value val = expression.evaluate(programState.symTable());
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
        return programState;
    }

    @Override
    public String toString() {
        return "openRFile(" + expression + ")";
    }
}
