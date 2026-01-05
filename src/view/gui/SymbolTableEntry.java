package view.gui;

public class SymbolTableEntry {
    private final String variable;
    private final String value;

    public SymbolTableEntry(String variable, String value) {
        this.variable = variable;
        this.value = value;
    }

    public String getVariable() {
        return variable;
    }

    public String getValue() {
        return value;
    }
}
