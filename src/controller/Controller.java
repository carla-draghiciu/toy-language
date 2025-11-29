package controller;

import model.exception.EmptyStackException;
import model.state.*;
import model.statement.Statement;
import repository.Repository;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public final class Controller {
    private final ExecutorService executor;
    private final Repository repository;

    public Controller(Repository repository) {
        this.repository = repository;
        this.executor = Executors.newFixedThreadPool(2);    }

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

    public void oneStepForAllPrg(List<ProgramState> programStates) {
        programStates.forEach(p -> repository.logPrgStateExec(p));

        List<Callable<ProgramState>> callList = programStates.stream()
                .map(state -> (Callable<ProgramState>) (state::oneStep))
                .toList();

        try {
            List<ProgramState> newPrgList = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (ExecutionException ee) {
                            throw (RuntimeException) ee.getCause();
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                            return null;
                        }
                    })
                    .filter(p -> p != null)
                    .collect(Collectors.toList());

            programStates.addAll(newPrgList);
        } catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
        programStates.forEach(p -> repository.logPrgStateExec(p));
        repository.setProgramList(programStates);
    }

    public void executeAll() {
//        executor = Executors.newFixedThreadPool(2);

        List<ProgramState> prgList = removeCompletedPrograms(repository.getProgramList());
        while(prgList.size() > 0) {
            oneStepForAllPrg(prgList);
            GarbageCollector gc = new GarbageCollector();
            gc.collect(repository.getCurrentState());
            displayCurrentState();
            prgList = removeCompletedPrograms(repository.getProgramList());
        }

        executor.shutdownNow();
        repository.setProgramList(prgList);

    }

    public void displayCurrentState() {
        IO.println(repository.getCurrentState());
    }

    public Repository repository() {
        return repository;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Controller) obj;
        return Objects.equals(this.repository, that.repository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repository);
    }

    @Override
    public String toString() {
        return "Controller[" +
                "repository=" + repository + ']';
    }

}


