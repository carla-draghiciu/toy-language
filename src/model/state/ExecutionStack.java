package model.state;

import model.adt.MyStack;
import model.exception.EmptyStackException;
import model.statement.Statement;

public interface ExecutionStack {
    void push(Statement st);
    Statement pop() throws EmptyStackException;
    boolean isEmpty();
    MyStack<Statement> getStack();
}
