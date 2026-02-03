package view.gui;

import controller.Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.state.*;
import model.statement.Statement;
import model.value.Value;
import view.commands.RunExample;
import view.examples.Example;
import view.examples.Examples;

import java.util.List;


public class MainPage extends Application{
    Stage window;
    Scene scene1;
    Scene scene2;
    List<Example> examples = Examples.getExamples();

    // scene1
    Button startButton;
    ListView<String> programs;

    // scene2
    Controller controller;
    ProgramState programState;
    Memory heapMemory;
    Out out;
    FileTable fileTable;
    SymbolTable symbolTable;
    private int lastSelectedID = 0;
    ExecutionStack executionStack;
    LockTable lockTable;

    TextField noPrgStates;
    TableView<HeapEntry> heapTableView;
    ListView<String> outListView;
    ListView<String> fileTableListView;
    ListView<Integer> idsListView;
    TableView<SymbolTableEntry> symbolTableView;
    ListView<String> exeStackListView;
    Button runButton;
    TableView<LockTableEntry> lockTableView;

    private void populateScene1() {
        startButton = new Button("Start");
        programs = new ListView<>();

        for (int i = 0; i < examples.size(); i++) {
            var entry = examples.get(i);
            Statement statement = entry.statement();
            programs.getItems().add(Integer.toString(i+1) + ". " + statement.toString());
        }
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(programs, startButton);
        scene1 = new Scene(layout, 1000, 400);
    }

    private ObservableList<HeapEntry> getHeapEntries(Memory heap) {
        ObservableList<HeapEntry> heapEntries = FXCollections.observableArrayList();
        for (var h : heap.getHeap().entrySet()) {
            int adr = h.getKey();
            Value value = h.getValue();

            heapEntries.add(new HeapEntry(adr, value.toString()));
        }
        return heapEntries;
    }

    private void refreshNoPrgStates() {
        int nr = controller.getNoOfPrgStates();
        noPrgStates.setText(Integer.toString(nr));
    }

    private void refreshHeapTableView() {
        programState = controller.getLastProgramState();
        heapTableView.setItems(getHeapEntries(programState.heapTable()));
    }

    private void refreshOutListView() {
        outListView.getItems().clear();
        for (Value o: out.getArrOut()) {
            outListView.getItems().add(o.toString());
        }
    }

    private void refreshFileTableListView() {
        fileTableListView.getItems().clear();
        for (var o: fileTable.getFiles().entrySet()) {
            String file = o.getKey().toString() + " = " + o.getValue().toString();
            fileTableListView.getItems().add(file);
        }
    }

    private void refreshIdsListView() {
        idsListView.getItems().clear();
        for (int id : controller.getAllIDs()) {
            idsListView.getItems().add(id);
        }
    }

    private ObservableList<SymbolTableEntry> getSymbolTableEntries(SymbolTable symbolTable) {
        ObservableList<SymbolTableEntry> stEntries = FXCollections.observableArrayList();
        for (var h : symbolTable.getDict().entrySet()) {
            String var = h.getKey().toString();
            Value value = h.getValue();

            stEntries.add(new SymbolTableEntry(var, value.toString()));
        }
        return stEntries;
    }

    private void refreshSymbolTableView(int id) {
        if (id == 0) {
            return;
        }
        SymbolTable s = controller.getProgramState(id).symTable();
        symbolTableView.setItems(getSymbolTableEntries(s));
    }

    private void refreshExeStackListView(int id) {
        if (id == 0) {
            return;
        }
        exeStackListView.getItems().clear();
        ExecutionStack es = controller.getProgramState(id).execStack();
        for (Statement o: es.getStack()) {
            exeStackListView.getItems().add(o.toString());
        }
    }

    private ObservableList<LockTableEntry> getLockEntries(LockTable lockTable) {
        ObservableList<LockTableEntry> Entries = FXCollections.observableArrayList();
        for (var h : lockTable.getDict().entrySet()) {
            int adr = h.getKey();
            int value = h.getValue();

            Entries.add(new LockTableEntry(adr, value));
        }
        return Entries;
    }

    private void refreshLockTableView() {
        programState = controller.getLastProgramState();
        lockTableView.setItems(getLockEntries(programState.lockTable()));
    }

    private void populateScene2() {
        Label psLabel = new Label("Number of program states");
        int nr = controller.getNoOfPrgStates();
        noPrgStates = new TextField(Integer.toString(nr));
        HBox psLayout = new HBox(10);
        psLayout.getChildren().addAll(psLabel, noPrgStates);

        programState = controller.getLastProgramState();
        heapMemory = programState.heapTable();
        out = programState.out();
        fileTable = programState.fileTable();
        symbolTable = programState.symTable();
        executionStack = programState.execStack();
        lockTable = programState.lockTable();

        Label heapLabel = new Label("Heap Table");
        heapTableView = new TableView<>();
        TableColumn<HeapEntry,Integer> column1 = new TableColumn<>("Address");
        TableColumn<HeapEntry,String> column2 = new TableColumn<>("Value");
        column1.setCellValueFactory(new PropertyValueFactory<>("address"));
        column2.setCellValueFactory(new PropertyValueFactory<>("value"));
        heapTableView.setItems(getHeapEntries(heapMemory));
        heapTableView.getColumns().addAll(column1,column2);
        HBox heapLayout = new HBox(10);
        heapLayout.getChildren().addAll(heapLabel,heapTableView);

        Label lockLabel = new Label("Lock Table");
        lockTableView = new TableView<>();
        TableColumn<LockTableEntry,Integer> c1 = new TableColumn<>("Location");
        TableColumn<LockTableEntry,Integer> c2 = new TableColumn<>("Value");
        c1.setCellValueFactory(new PropertyValueFactory<>("location"));
        c2.setCellValueFactory(new PropertyValueFactory<>("value"));
        lockTableView.setItems(getLockEntries(lockTable));
        lockTableView.getColumns().addAll(c1,c2);
        HBox lockLayout = new HBox(10);
        lockLayout.getChildren().addAll(lockLabel,lockTableView);

        Label outLabel = new Label("Out");
        outListView = new ListView<>();
        refreshOutListView();
        HBox outLayout = new HBox(10);
        outLayout.getChildren().addAll(outLabel,outListView);

        Label fileLabel = new Label("File Table");
        fileTableListView = new ListView<>();
        refreshFileTableListView();
        HBox fileLayout = new HBox(10);
        fileLayout.getChildren().addAll(fileLabel,fileTableListView);

        Label idsLabel = new Label("IDs");
        idsListView = new ListView<>();
        refreshIdsListView();
        HBox idsLayout = new HBox(10);
        idsLayout.getChildren().addAll(idsLabel,idsListView);

        idsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                lastSelectedID = newValue;
                refreshSymbolTableView(lastSelectedID);
                refreshExeStackListView(lastSelectedID);
            }
        });

        Label symbolTableLabel = new Label("Symbol Table");
        symbolTableView = new TableView<>();
        TableColumn<SymbolTableEntry,String> col1 = new TableColumn<>("Variable");
        TableColumn<SymbolTableEntry,String> col2 = new TableColumn<>("Value");
        col1.setCellValueFactory(new PropertyValueFactory<>("variable"));
        col2.setCellValueFactory(new PropertyValueFactory<>("value"));
        symbolTableView.setItems(getSymbolTableEntries(symbolTable));
        symbolTableView.getColumns().addAll(col1,col2);
        HBox symbolTableLayout = new HBox(10);
        symbolTableLayout.getChildren().addAll(symbolTableLabel,symbolTableView);

        Label exeStackLabel = new Label("Execution Stack");
        exeStackListView = new ListView<>();
        refreshExeStackListView(lastSelectedID);
        HBox exeStackLayout = new HBox(20);
        exeStackLayout.getChildren().addAll(exeStackLabel,exeStackListView);


        runButton = new Button("Next step");
        Label done = new Label("Program done.");
        Button backButton = new Button("Back");
        HBox runLayout = new HBox(10);
        runLayout.getChildren().add(runButton);

        runButton.setOnAction(e -> {
            try {
                controller.displayCurrentState();
                boolean ran = controller.oneStep();
                if (!ran) {
                    runLayout.getChildren().add(done);
                    runLayout.getChildren().add(backButton);
                    runLayout.getChildren().remove(runButton);
                } else {
                    refreshNoPrgStates();
                    refreshHeapTableView();
                    refreshOutListView();
                    refreshFileTableListView();
                    refreshIdsListView();
                    refreshSymbolTableView(lastSelectedID);
                    refreshExeStackListView(lastSelectedID);
                    refreshLockTableView();
                }
            } catch (Exception ex) {
                Label error = new Label("Error: " + ex.getMessage());
                runLayout.getChildren().add(error);
                runLayout.getChildren().add(backButton);
                runLayout.getChildren().remove(runButton);
            }
        });

        backButton.setOnAction(e -> {
            window.setScene(scene1);
        });

        VBox layout2 = new VBox(10);
        layout2.setPadding(new Insets(20,20,20,20));
        layout2.getChildren().addAll(psLayout, heapLayout, outLayout, fileLayout, idsLayout, symbolTableLayout, exeStackLayout, lockLayout, runLayout);
        scene2 = new Scene(layout2,1000,600);
    }

    private Example getExample() {
        String program = "";
        program = programs.getSelectionModel().getSelectedItem().toString();

        String[] parts = program.split("[.]");
//        System.out.println(parts[0]);
        int index = Integer.parseInt(parts[0]) - 1;

        Example ex = examples.get(index);
//        System.out.println(ex.toString());
        return ex;
    }

    @Override
    public void start(Stage stage) {
        window = stage;
        window.setTitle("Toy Language");

        // scene1
        populateScene1();
        startButton.setOnAction(e -> buttonClicked());

        // scene2
//        populateScene2();


        window.setScene(scene1);
        window.show();
    }

    private void buttonClicked() {
        Example ex = getExample();
        controller = ex.controller();
        lastSelectedID = 0;
        populateScene2();
        window.setScene(scene2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
