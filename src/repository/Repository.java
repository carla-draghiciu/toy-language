package repository;

import model.exception.TextFileException;
import model.state.ProgramState;

import java.util.List;

public interface Repository {
    void addProgramState(ProgramState state);
    ProgramState getCurrentState();
    void logPrgStateExec(ProgramState programState) throws TextFileException;
    List<ProgramState> getProgramList();
    void setProgramList(List<ProgramState> programList);
}
