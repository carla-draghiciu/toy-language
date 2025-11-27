package controller;

import model.exception.EmptyStackException;
import model.state.*;
import model.statement.Statement;
import repository.Repository;

import java.util.List;
import java.util.stream.Collectors;

public record Controller(Repository repository) {
    public void addNewProgram(Statement statement) {
        ExecutionStack execStack = new LinkedListExecutionStack();
        execStack.push(statement);

        ProgramState newState = new ProgramState(execStack, new MapSymbolTable(), new ArrayListOut(), new MapFileTable(), new HeapMemory());
        repository.addProgramState(newState);
    }

    public List<ProgramState> removeCompletedPrograms(List<ProgramState> programStates) {
        return programStates.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void executeAll(int index) {
        ProgramState state = repository.getCurrentState(index);

        while(!state.execStack().isEmpty()) {
            state = state.oneStep();
            GarbageCollector gc =  new GarbageCollector();
            gc.collect(state);
            displayCurrentState(index);
            repository.logPrgStateExec(state);
        }
    }

    public void displayCurrentState(int index) {
        IO.println(repository.getCurrentState(index));
    }
}
