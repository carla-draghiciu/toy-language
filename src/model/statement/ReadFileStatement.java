package model.statement;

import model.exception.MismatchException;
import model.exception.TextFileException;
import model.exception.UndeclaredException;
import model.expression.Expression;
import model.state.ProgramState;
import model.type.IntType;
import model.type.Type;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;

import java.io.BufferedReader;

public record ReadFileStatement(Expression exp, String var_name) implements Statement {
    @Override
    public ProgramState execute(ProgramState state) {
        if (!state.symTable().isDefined(var_name)) {
            throw new UndeclaredException("Variable " + var_name + " is not defined");
        }

        if (!(state.symTable().getVariableType(var_name) instanceof IntType)) {
            throw new MismatchException("Value " + var_name + " is not an integer");
        }

        Value val = exp.evaluate(state.symTable(), state.heapTable());
        if (!(val instanceof StringValue)) {
            throw new MismatchException("Value " + val + " is not a string");
        }
        StringValue strVal = (StringValue) val;

        if (!state.fileTable().isDefined(strVal)) {
            throw new UndeclaredException("Value " + strVal + " is not defined");
        }

        BufferedReader br = state.fileTable().getFile(strVal);

        try {
            String line = br.readLine();
            int number;

            if (line == null) {
                number = 0;
            } else {
                number = Integer.parseInt(line);
            }

            state.symTable().setValue(var_name, new IntValue(number));
        }
        catch (Exception e) {
            throw new TextFileException(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "readFile(" + exp + ", " +  var_name + ")";
    }
}
