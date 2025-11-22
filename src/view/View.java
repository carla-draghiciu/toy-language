//package view;
//
//import controller.Controller;
//import model.expression.ArithmeticExpression;
//import model.expression.ValueExpression;
//import model.expression.VariableExpression;
//import model.statement.*;
//import model.type.Type;
//import model.value.BoolValue;
//import model.value.IntValue;
//import model.value.StringValue;
//
//public record View(Controller controller) {
//    private void printMenu() {
//        IO.println("~ OPTIONS ~");
//        IO.println(" > Exercise 1");
//        IO.println("   int v;\n   v=2;\n   print(v);\n");
//
//        IO.println(" > Exercise 2");
//        IO.println("   int a;\n   int b;\n   a=2+3*5;\n   b=a+1;\n   print(b);\n");
//
//        IO.println(" > Exercise 3");
//        IO.println("   bool a;\n   int v;\n   a=true;\n   (if a then v=2 else v=3);\n   print(v);\n");
//
//        IO.println(" > Exercise 4");
//        IO.println("   int a;\n   a=5;\n   int a;\n");
//
//        IO.println(" > Exercise 5");
//        IO.println("   a=true;\n   print(a);\n");
//
//        IO.println(" > Exercise 6");
//        IO.println("   int a;\n   a=5/0;\n   print(a);\n");
//
//        IO.println(" > Exercise 7");
//        IO.println("   int a;\n   int v;\n   (if a then v=2 else v=3);\n   print(v);\n");
//
//        IO.println(" > Exercise 8");
//        IO.println("   string varf;\n" +
//                "      varf=\"test.in\";\n" +
//                "        openRFile(varf);\n" +
//                "        int varc;\n" +
//                "        readFile(varf,varc);print(varc);\n" +
//                "        readFile(varf,varc);print(varc)\n" +
//                "        closeRFile(varf)");
//    }
//
//    private void populateRepo() {
//        Statement ex1 = new CompoundStatement(
//                new VariableDeclarationStatement(Typ, "v"),
//                new CompoundStatement(
//                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
//                        new PrintStatement(new VariableExpression("v"))
//                )
//        );
//
//        Statement ex2 = new CompoundStatement(
//                new VariableDeclarationStatement(Type.INTEGER, "a"),
//                new CompoundStatement(
//                        new VariableDeclarationStatement(Type.INTEGER, "b"),
//                        new CompoundStatement(
//                                new AssignmentStatement("a", new ArithmeticExpression(new ValueExpression(new IntValue(2)), new ArithmeticExpression(new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5)), '*'), '+')),
//                                new CompoundStatement(
//                                        new AssignmentStatement("b", new ArithmeticExpression(new VariableExpression("a"), new ValueExpression(new IntValue(1)),'+')),
//                                        new PrintStatement(new VariableExpression("b"))
//                                )
//                        )
//                )
//        );
//
//        Statement ex3 = new CompoundStatement(
//                new VariableDeclarationStatement(Type.BOOLEAN, "a"),
//                new CompoundStatement(
//                        new VariableDeclarationStatement(Type.INTEGER, "v"),
//                        new CompoundStatement(
//                                new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
//                                new CompoundStatement(
//                                        new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ValueExpression(new IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
//                                        new PrintStatement(new VariableExpression("v"))
//                                )
//                        )
//                )
//        );
//
//        Statement ex4 = new CompoundStatement(
//                new VariableDeclarationStatement(Type.INTEGER, "a"),
//                new CompoundStatement(
//                        new AssignmentStatement("a", new ValueExpression(new IntValue(5))),
//                        new VariableDeclarationStatement(Type.INTEGER, "a")
//                )
//        );
//
//        Statement ex5 = new CompoundStatement(
//                new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
//                new PrintStatement(new VariableExpression("a"))
//        );
//
//        Statement ex6 = new CompoundStatement(
//                new VariableDeclarationStatement(Type.INTEGER, "a"),
//                new CompoundStatement(
//                        new AssignmentStatement("a", new ArithmeticExpression(new ValueExpression(new IntValue(5)), new ValueExpression(new IntValue(0)), '/')),
//                        new PrintStatement(new VariableExpression("a"))
//                )
//        );
//
//        Statement ex7 = new CompoundStatement(
//                new VariableDeclarationStatement(Type.INTEGER, "a"),
//                new CompoundStatement(
//                        new VariableDeclarationStatement(Type.INTEGER, "v"),
//                        new CompoundStatement(
//                                new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ValueExpression(new IntValue(2))), new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
//                                new PrintStatement(new VariableExpression("v"))
//                        )
//                )
//        );
//
//        //string varf;
//        //varf="test.in";
//        //openRFile(varf);
//        //int varc;
//        //readFile(varf,varc);print(varc);
//        //readFile(varf,varc);print(varc)
//        //closeRFile(varf)
//        Statement ex8 = new CompoundStatement(
//                new VariableDeclarationStatement(Type.STRING, "varf"),
//                new CompoundStatement(
//                        new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
//                        new CompoundStatement(
//                                new OpenRFileStatement(new VariableExpression("varf")),
//                                new CompoundStatement(
//                                        new VariableDeclarationStatement(Type.INTEGER, "varc"),
//                                        new CompoundStatement(
//                                                new ReadFileStatement(new VariableExpression("varf"), "varc"),
//                                                new CompoundStatement(
//                                                        new PrintStatement(new VariableExpression("varc")),
//                                                        new CompoundStatement(
//                                                                new ReadFileStatement(new VariableExpression("varf"), "varc"),
//                                                                new CompoundStatement(
//                                                                        new PrintStatement(new VariableExpression("varc")),
//                                                                        new CloseRFileStatement(new VariableExpression("varf"))
//                                                                )
//                                                        )
//                                                )
//                                        )
//                                )
//                        )
//                )
//        );
//
//
//        controller.addNewProgram(ex1);
//        controller.addNewProgram(ex2);
//        controller.addNewProgram(ex3);
//        controller.addNewProgram(ex4);
//        controller.addNewProgram(ex5);
//        controller.addNewProgram(ex6);
//        controller.addNewProgram(ex7);
//        controller.addNewProgram(ex8);
//    }
//
//    public void run() {
//        populateRepo();
//        while (true) {
//            printMenu();
//            String input = IO.readln(" >>> ");
//            try {
//                switch (input) {
//                    case "1":
//                        controller.displayCurrentState(0);
//                        controller.executeAll(0);
//                        break;
//                    case "2":
//                        controller.displayCurrentState(1);
//                        controller.executeAll(1);
//                        break;
//                    case "3":
//                        controller.displayCurrentState(2);
//                        controller.executeAll(2);
//                        break;
//                    case "4":
//                        controller.displayCurrentState(3);
//                        controller.executeAll(3);
//                        break;
//                    case "5":
//                        controller.displayCurrentState(4);
//                        controller.executeAll(4);
//                        break;
//                    case "6":
//                        controller.displayCurrentState(5);
//                        controller.executeAll(5);
//                        break;
//                    case "7":
//                        controller.displayCurrentState(6);
//                        controller.executeAll(6);
//                        break;
//                    case "8":
//                        controller.displayCurrentState(7);
//                        controller.executeAll(7);
//                        break;
//                    case "exit":
//                        return;
//                    default:
//                        IO.println("invalid input");
//                }
//            } catch (Exception e) {
//                IO.println(e.getMessage());
//            }
//
//        }
//    }
//}
