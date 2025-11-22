package model.value;

import model.exception.MismatchException;
import model.type.RefType;
import model.type.Type;

public record RefValue(int address, Type locationType) implements Value {
    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    @Override
    public boolean equals(Value other) {
        if (!(other instanceof IntValue)) {
            throw new MismatchException("Types don't match");
        }
        return address == ((RefValue) other).address;
    }
}
