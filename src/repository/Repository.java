package repository;

import model.exception.TextFileException;
import model.state.ProgramState;

public interface Repository {
    void addProgramState(ProgramState state);
    ProgramState getCurrentState(int index);
    void logPrgStateExec(int index) throws TextFileException;
}
