package jumpingalien.program.expression.unary;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.Expression;

public abstract class MathUnaryExpression extends UnaryExpression {
	
	public MathUnaryExpression(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
		
		setType(Type.DOUBLE);
		if(expr.getType() != Type.DOUBLE) Program.printTypeCheckError(sourceLocation);
	}
}
