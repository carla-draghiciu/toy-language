package model.type;

import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;

public enum Type {
    INTEGER,
    BOOLEAN,
    STRING;

    public Value getDefaultValue() {
        switch (this)
        {
            case INTEGER:
                return new IntValue(0);
            case BOOLEAN:
                return new BoolValue(false);
            case STRING:
                return new StringValue("");
        }
        return null;
    }

    @Override
    public String toString() {
        switch (this)
        {
            case INTEGER: return "int";
            case BOOLEAN: return "bool";
            case STRING: return "string";
        }
        return "";
    }
}
