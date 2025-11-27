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
//        Scanner keyboard = new Scanner(System.in);
//        System.out.print("Enter log file path: ");
//        String logPath = keyboard.nextLine();
        Repository repo1 = new ArrayListRepository("src/logs/log1.txt");
        Controller ctr1 = new Controller(repo1);
        Statement ex1 = new CompoundStatement(
                new VariableDeclarationStatement(new IntType(), "v"),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))
                )
        );

        Repository repo2 = new ArrayListRepository("src/logs/log2.txt");
        Controller ctr2 = new Controller(repo2);
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

        Repository repo3 = new ArrayListRepository("src/logs/log3.txt");
        Controller ctr3 = new Controller(repo3);
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

        Repository repo4 = new ArrayListRepository("src/logs/log4.txt");
        Controller ctr4 = new Controller(repo4);
        Statement ex4 = new CompoundStatement(
                new VariableDeclarationStatement(new IntType(), "a"),
                new CompoundStatement(
                        new AssignmentStatement("a", new ValueExpression(new IntValue(5))),
                        new VariableDeclarationStatement(new IntType(), "a")
                )
        );

        Repository repo5 = new ArrayListRepository("src/logs/log5.txt");
        Controller ctr5 = new Controller(repo5);
        Statement ex5 = new CompoundStatement(
                new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                new PrintStatement(new VariableExpression("a"))
        );

        Repository repo6 = new ArrayListRepository("src/logs/log6.txt");
        Controller ctr6 = new Controller(repo6);
        Statement ex6 = new CompoundStatement(
                new VariableDeclarationStatement(new IntType(), "a"),
                new CompoundStatement(
                        new AssignmentStatement("a", new ArithmeticExpression(new ValueExpression(new IntValue(5)), new ValueExpression(new IntValue(0)), '/')),
                        new PrintStatement(new VariableExpression("a"))
                )
        );

        Repository repo7 = new ArrayListRepository("src/logs/log7.txt");
        Controller ctr7 = new Controller(repo7);
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
        
        Repository repo8 = new ArrayListRepository("src/logs/log8.txt");
        Controller ctr8 = new Controller(repo8);
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

        Repository repo9 = new ArrayListRepository("src/logs/log9.txt");
        Controller ctr9 = new Controller(repo9);
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

        Repository repo10 = new ArrayListRepository("src/logs/log10.txt");
        Controller ctr10 = new Controller(repo10);
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

        Repository repo11 = new ArrayListRepository("src/logs/log11.txt");
        Controller ctr11 = new Controller(repo11);
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

        Repository repo12 = new ArrayListRepository("src/logs/log12.txt");
        Controller ctr12 = new Controller(repo12);
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

        Repository repo13 = new ArrayListRepository("src/logs/log13.txt");
        Controller ctr13 = new Controller(repo13);
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

        Repository repo14 = new ArrayListRepository("src/logs/log14.txt");
        Controller ctr14 = new Controller(repo14);
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

        ctr1.addNewProgram(ex1);
        ctr2.addNewProgram(ex2);
        ctr3.addNewProgram(ex3);
        ctr4.addNewProgram(ex4);
        ctr5.addNewProgram(ex5);
        ctr6.addNewProgram(ex6);
        ctr7.addNewProgram(ex7);
        ctr8.addNewProgram(ex8);
        ctr9.addNewProgram(ex9);
        ctr10.addNewProgram(ex10);
        ctr11.addNewProgram(ex11);
        ctr12.addNewProgram(ex12);
        ctr13.addNewProgram(ex13);
        ctr14.addNewProgram(ex14);

        TextMenu tm = new TextMenu();
        tm.addCommand(new ExitCommand("0", "exit"));
        tm.addCommand(new RunExample("1", ex1.toString(), ctr1));
        tm.addCommand(new RunExample("2", ex2.toString(), ctr2));
        tm.addCommand(new RunExample("3", ex3.toString(), ctr3));
        tm.addCommand(new RunExample("4", ex4.toString(), ctr4));
        tm.addCommand(new RunExample("5", ex5.toString(), ctr5));
        tm.addCommand(new RunExample("6", ex6.toString(), ctr6));
        tm.addCommand(new RunExample("7", ex7.toString(), ctr7));
        tm.addCommand(new RunExample("8", ex8.toString(), ctr8));
        tm.addCommand(new RunExample("9", ex9.toString(), ctr9));
        tm.addCommand(new RunExample("10", ex10.toString(), ctr10));
        tm.addCommand(new RunExample("11", ex11.toString(), ctr11));
        tm.addCommand(new RunExample("12", ex12.toString(), ctr12));
        tm.addCommand(new RunExample("13", ex13.toString(), ctr13));
        tm.addCommand(new RunExample("14", ex14.toString(), ctr14));
        tm.show();
    }
}
