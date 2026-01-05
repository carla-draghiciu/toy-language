package model.state;
import model.adt.MyStack;
import model.exception.EmptyStackException;
import model.statement.Statement;

import java.util.LinkedList;
import java.util.List;

public class LinkedListExecutionStack implements ExecutionStack {
    private final MyStack<Statement> statements = new MyStack<>();

    @Override
    public void push(Statement st)
    {
        statements.push(st);
    }

    @Override
    public Statement pop() {
        return statements.pop();
    }

    @Override
    public boolean isEmpty() {
        return statements.isEmpty();
    }

    @Override
    public MyStack<Statement> getStack() {
        return statements;
    }

    @Override
    public String toString() {
//        String result = "{ ";
//        for (int i = 0; i < elements.size() - 1; i++) {
//            result += elements.get(i).toString() + " | ";
//        }
//        if (elements.size() > 0)
//            result += elements.get(elements.size() - 1).toString();
//        return result + " }";
//        return statements.toString();
        return "ExeStack:  " + statements.toString() + "\n";
    }
}
