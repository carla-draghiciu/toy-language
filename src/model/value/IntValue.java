package model.value;

import model.exception.MismatchException;
import model.type.Type;

public record IntValue(int value) implements Value {
    public Type getType() {
        return Type.INTEGER;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
        //return Integer.toString(value);
    }

    @Override
    public boolean equals(Value other) {
        if (!(other instanceof IntValue)) {
            throw new MismatchException("Types don't match");
        }
        return value == ((IntValue) other).value;
    }
}
