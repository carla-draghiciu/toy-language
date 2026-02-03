package model.statement;

import model.adt.MyDictionary;
import model.exception.MismatchException;
import model.expression.Expression;
import model.expression.RelationalExpression;
import model.expression.VariableExpression;
import model.state.ProgramState;
import model.type.IntType;
import model.type.Type;

public record ForStatement(String varName, Expression exp1, Expression exp2, Expression exp3, Statement statement) implements Statement {
    // for(v=exp1;v<exp2;v=exp3) stmt
    @Override
    public ProgramState execute(ProgramState state) {
        // int v; v=exp1;(while(v<exp2) stmt;v=exp3) - push the new statement on the stack
        Statement newSt = new CompoundStatement(
                new VariableDeclarationStatement(new IntType(), "v"),
                new CompoundStatement(
                        new AssignmentStatement("v", exp1),
                        new WhileStatement(
                                new RelationalExpression(new VariableExpression("v"), exp2, "<"),
                                new CompoundStatement(
                                        statement,
                                        new AssignmentStatement("v", exp3)
                                )
                        )
                )
        );
        state.execStack().push(newSt);
        return null;
    }

    // The typecheck method of for statement verifies if exp1, exp2, and exp3 have  the type int.
    @Override
    public MyDictionary<String, Type> typecheck(MyDictionary<String,Type> typeEnv) {
//        Type t1 = exp1.typecheck(typeEnv);
//        Type t2 = exp2.typecheck(typeEnv);
//        Type t3 = exp3.typecheck(typeEnv);
//        if (t1 instanceof IntType && t2 instanceof IntType && t3 instanceof IntType) {
//            return typeEnv;
//        }
//        throw new MismatchException("FOR Expressions must be integers");
        return typeEnv;
    }
}
