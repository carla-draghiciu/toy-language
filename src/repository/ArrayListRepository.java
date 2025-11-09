package repository;

import model.exception.TextFileException;
import model.state.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ArrayListRepository implements Repository {
    private final List<ProgramState> programStates = new ArrayList<>();
    private String logFilePath;

    public ArrayListRepository(String filePath) {
        this.logFilePath = filePath;
    }

    @Override
    public void addProgramState(ProgramState state) {
        programStates.add(state);
    }

    @Override
    public ProgramState getCurrentState(int index) {
//        return programStates.getFirst();
        return programStates.get(index);
    }

    @Override
    public void logPrgStateExec(int index) throws TextFileException {
        try (PrintWriter logFile = new PrintWriter(
                new BufferedWriter(new FileWriter(logFilePath, true)))) {

            logFile.println(getCurrentState(index).toString());

        } catch (Exception e) {
            throw new TextFileException("Could not write to log file: " + e.getMessage());
        }
    }

}
