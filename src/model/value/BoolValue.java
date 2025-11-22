package model.value;

import model.exception.MismatchException;
import model.type.BoolType;
import model.type.Type;

public record BoolValue(boolean value) implements Value {
    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public String toString() {
        return String.valueOf(value);

    }

    @Override
    public boolean equals(Value other) {
        if (!(other instanceof BoolValue)) {
            throw new MismatchException("Types don't match");
        }
        return value == ((BoolValue) other).value;
    }
}
