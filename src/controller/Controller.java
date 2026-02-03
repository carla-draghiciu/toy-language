package controller;

import model.adt.MyDictionary;
import model.exception.EmptyStackException;
import model.exception.MismatchException;
import model.exception.TypeCheckException;
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

    public int getNoOfPrgStates() {
        return this.repository.getSize();
    }

    public List<Integer> getAllIDs() {
        return this.repository.getIds();
    }

    public ProgramState getLastProgramState() {
        return this.repository.getLast();
    }

    public ProgramState getProgramState(int id) {
        return this.repository.getProgramState(id);
    }

    public void addNewProgram(Statement statement) {
        try {
            statement.typecheck(new MyDictionary<>());
        } catch (Exception e) {
            throw new TypeCheckException("Program failed. not added");
        }

        ExecutionStack execStack = new LinkedListExecutionStack();
        execStack.push(statement);

        ProgramState newState = new ProgramState(execStack, new MapSymbolTable(), new ArrayListOut(), new MapFileTable(), new HeapMemory(), new MapLockTable());
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
            gc.collect(prgList);
            displayCurrentState();
            prgList = removeCompletedPrograms(repository.getProgramList());
        }

        executor.shutdownNow();
        repository.setProgramList(prgList);
    }

    public boolean oneStep() {
        List<ProgramState> prgList = removeCompletedPrograms(repository.getProgramList());
        if (prgList.size() > 0) {
            oneStepForAllPrg(prgList);
            GarbageCollector gc = new GarbageCollector();
            gc.collect(prgList);
            return true;
        }
        else {
            return false;
        }
    }

    public void displayCurrentState() {
//        IO.println(repository.getCurrentState());
        IO.println(repository.displayState());
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


