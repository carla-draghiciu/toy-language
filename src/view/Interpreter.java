package view;
import controller.Controller;
import model.expression.*;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.type.RefType;
import model.type.StringType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import repository.ArrayListRepository;
import repository.Repository;
import view.commands.ExitCommand;
import view.commands.RunExample;
import java.util.Scanner;

public class Interpreter {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter log file path: ");
        String logPath = keyboard.nextLine();
        Repository repo = new ArrayListRepository(logPath);
        Controller controller = new Controller(repo);
        Statement ex1 = new CompoundStatement(
                new VariableDeclarationStatement(new IntType(), "v"),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))
                )
        );

        Statement ex2 = new CompoundStatement(
                new VariableDeclarationStatement(new IntType(), "a"),
                new CompoundStatement(
                        new VariableDeclarationStatement(new IntType(), "b"),
                        new CompoundStatement(
                                new AssignmentStatement("a", new ArithmeticExpression(new ValueExpression(new IntValue(2)), new ArithmeticExpression(new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5)), '*'), '+')),
                                new CompoundStatement(
                                        new AssignmentStatement("b", new ArithmeticExpression(new VariableExpression("a"), new ValueExpression(new IntValue(1)),'+')),
                                        new PrintStatement(new VariableExpression("b"))
                                )
                        )
                )
        );

        Statement ex3 = new CompoundStatement(
                new VariableDeclarationStatement(new BoolType(), "a"),
                new CompoundStatement(
                        new VariableDeclarationStatement(new IntType(), "v"),
                        new CompoundStatement(
                                new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(
                                        new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ValueExpression(new IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))
                                )
                        )
                )
        );

        Statement ex4 = new CompoundStatement(
                new VariableDeclarationStatement(new IntType(), "a"),
                new CompoundStatement(
                        new AssignmentStatement("a", new ValueExpression(new IntValue(5))),
                        new VariableDeclarationStatement(new IntType(), "a")
                )
        );

        Statement ex5 = new CompoundStatement(
                new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                new PrintStatement(new VariableExpression("a"))
        );

        Statement ex6 = new CompoundStatement(
                new VariableDeclarationStatement(new IntType(), "a"),
                new CompoundStatement(
                        new AssignmentStatement("a", new ArithmeticExpression(new ValueExpression(new IntValue(5)), new ValueExpression(new IntValue(0)), '/')),
                        new PrintStatement(new VariableExpression("a"))
                )
        );

        Statement ex7 = new CompoundStatement(
                new VariableDeclarationStatement(new IntType(), "a"),
                new CompoundStatement(
                        new VariableDeclarationStatement(new IntType(), "v"),
                        new CompoundStatement(
                                new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ValueExpression(new IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
                                new PrintStatement(new VariableExpression("v"))
                        )
                )
        );

        //string varf;
        //varf="test.in";
        //openRFile(varf);
        //int varc;
        //readFile(varf,varc);print(varc);
        //readFile(varf,varc);print(varc)
        //closeRFile(varf)
        Statement ex8 = new CompoundStatement(
                new VariableDeclarationStatement(new StringType(), "varf"),
                new CompoundStatement(
                        new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(
                                new OpenRFileStatement(new VariableExpression("varf")),
                                new CompoundStatement(
                                        new VariableDeclarationStatement(new IntType(), "varc"),
                                        new CompoundStatement(
                                                new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(
                                                                new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(
                                                                        new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseRFileStatement(new VariableExpression("varf"))
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        Statement ex9 = new CompoundStatement(
                new VariableDeclarationStatement(new IntType(), "a"),
                new CompoundStatement(
                        new VariableDeclarationStatement(new IntType(), "b"),
                        new CompoundStatement(
                                new AssignmentStatement("a", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(
                                        new AssignmentStatement("b", new ValueExpression(new IntValue(7))),
                                        new CompoundStatement(
                                                new VariableDeclarationStatement(new StringType(), "result"),
                                                new CompoundStatement(
                                                        new IfStatement(new RelationalExpression(new VariableExpression("a"), new VariableExpression("b"), "<="), new AssignmentStatement("result", new ValueExpression(new StringValue("a is less or equal than b"))), new AssignmentStatement("result", new ValueExpression(new StringValue("a is greater or equal than b")))),
                                                        new PrintStatement(new VariableExpression("result"))
                                                )
                                        )
                                )
                        )
                )
        );

        Statement ex10 = new CompoundStatement(
                new VariableDeclarationStatement(new RefType(new IntType()), "v"),
                new CompoundStatement(
                        new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VariableDeclarationStatement(new RefType(new RefType(new IntType())), "a"),
                                new CompoundStatement(
                                        new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(
                                                new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))), new ValueExpression(new IntValue(5)), '+'))
                                        )
                                )
                        )
                )
        );

        Statement ex11 = new CompoundStatement(
                new VariableDeclarationStatement(new RefType(new IntType()), "v"),
                new CompoundStatement(
                        new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                new CompoundStatement(
                                        new HeapWritingStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v")), new ValueExpression(new IntValue(5)), '+'))
                                )
                        )
                )
        );

        var cond = new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntValue(0)), ">");
        var then = new CompoundStatement(
                new PrintStatement(new VariableExpression("v")),
                new AssignmentStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), '-'))
        );
        Statement ex12 = new CompoundStatement(
                new VariableDeclarationStatement(new IntType(), "v"),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(
                                new WhileStatement(cond, then),
                                new PrintStatement(new VariableExpression("v"))
                        )
                )
        );

        Statement ex13 = new CompoundStatement(
                new VariableDeclarationStatement(new RefType(new IntType()), "v"),
                new CompoundStatement(
                        new VariableDeclarationStatement(new RefType(new RefType(new IntType())), "a"),
                        new CompoundStatement(
                                new NewStatement("v", new ValueExpression(new IntValue(20))),
                                new CompoundStatement(
                                        new NewStatement("a", new VariableExpression("v")),
                                        new NewStatement("v", new ValueExpression(new IntValue(50)))
                                )
                        )
                )
        );

        Statement ex14 = new CompoundStatement(
                new VariableDeclarationStatement(new RefType(new IntType()), "v"),
                new CompoundStatement(
                        new VariableDeclarationStatement(new RefType(new RefType(new IntType())), "a"),
                        new CompoundStatement(
                                new NewStatement("v", new ValueExpression(new IntValue(20))),
                                new CompoundStatement(
                                        new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(
                                                new NewStatement("v", new ValueExpression(new IntValue(50))),
                                                new NewStatement("a", new VariableExpression("v"))
                                        )
                                )
                        )
                )
        );

        controller.addNewProgram(ex1);
        controller.addNewProgram(ex2);
        controller.addNewProgram(ex3);
        controller.addNewProgram(ex4);
        controller.addNewProgram(ex5);
        controller.addNewProgram(ex6);
        controller.addNewProgram(ex7);
        controller.addNewProgram(ex8);
        controller.addNewProgram(ex9);
        controller.addNewProgram(ex10);
        controller.addNewProgram(ex11);
        controller.addNewProgram(ex12);
        controller.addNewProgram(ex13);
        controller.addNewProgram(ex14);

        TextMenu tm = new TextMenu();
        tm.addCommand(new ExitCommand("0", "exit"));
        tm.addCommand(new RunExample("1", ex1.toString(), controller));
        tm.addCommand(new RunExample("2", ex2.toString(), controller));
        tm.addCommand(new RunExample("3", ex3.toString(), controller));
        tm.addCommand(new RunExample("4", ex4.toString(), controller));
        tm.addCommand(new RunExample("5", ex5.toString(), controller));
        tm.addCommand(new RunExample("6", ex6.toString(), controller));
        tm.addCommand(new RunExample("7", ex7.toString(), controller));
        tm.addCommand(new RunExample("8", ex8.toString(), controller));
        tm.addCommand(new RunExample("9", ex9.toString(), controller));
        tm.addCommand(new RunExample("10", ex10.toString(), controller));
        tm.addCommand(new RunExample("11", ex11.toString(), controller));
        tm.addCommand(new RunExample("12", ex12.toString(), controller));
        tm.addCommand(new RunExample("13", ex13.toString(), controller));
        tm.addCommand(new RunExample("14", ex13.toString(), controller));
        tm.show();
    }
}
