package model.value;

import model.exception.MismatchException;
import model.type.StringType;
import model.type.Type;

public record StringValue(String value) implements Value {
    @Override
    public Type getType() {
        return new StringType();
    }

    public String getVal() {
        return value;
    }

    @Override
    public boolean equals(Value other) {
        if (!(other instanceof StringValue)) {
            throw new MismatchException("Types don't match");
        }
        return value == ((StringValue)other).value;
    }

    @Override
    public String toString() {
        return value;
    }
}
