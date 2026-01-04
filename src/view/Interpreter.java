package view;
import controller.Controller;
import model.adt.MyDictionary;
import model.exception.TypeCheckException;
import model.expression.*;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.type.RefType;
import model.type.StringType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import org.w3c.dom.Text;
import repository.ArrayListRepository;
import repository.Repository;
import view.commands.ExitCommand;
import view.commands.RunExample;
import view.examples.Example;
import view.examples.Examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interpreter {

    public static void main(String[] args) {
//        Scanner keyboard = new Scanner(System.in);
//        System.out.print("Enter log file path: ");
//        String logPath = keyboard.nextLine();

        TextMenu tm = new TextMenu();
        tm.addCommand(new ExitCommand("0", "exit"));

        List<Example> examples = Examples.getExamples();
        for (int i = 0; i < examples.size(); i++) {
            var entry = examples.get(i);
            Controller controller = entry.controller();
            Statement statement = entry.statement();

            tm.addCommand(new RunExample(Integer.toString(i+1), statement.toString(), controller));
        }

        tm.show();
    }
}
