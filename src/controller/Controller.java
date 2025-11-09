package controller;

import model.exception.EmptyStackException;
import model.state.*;
import model.statement.Statement;
import repository.Repository;

public record Controller(Repository repository) {
    public void addNewProgram(Statement statement) {
        ExecutionStack execStack = new LinkedListExecutionStack();
        execStack.push(statement);

        ProgramState newState = new ProgramState(execStack, new MapSymbolTable(), new ArrayListOut(), new MapFileTable());
        repository.addProgramState(newState);
    }

    public ProgramState executeStep(ProgramState state) {
        if (state.execStack().isEmpty()) {
            throw new EmptyStackException("STack is empty");
        }

        Statement nextStep =  state.execStack().pop();
        return nextStep.execute(state);
    }

    public void executeAll(int index) {
        ProgramState state = repository.getCurrentState(index);

        while(!state.execStack().isEmpty()) {
            state = executeStep(state);
            displayCurrentState(index);
            repository.logPrgStateExec(index);
        }
    }

    public void displayCurrentState(int index) {
        IO.println("Current state: " + repository.getCurrentState(index));
    }
}
