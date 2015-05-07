package jumpingalien.program.expression.unary;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.Expression;

public abstract class Checker extends UnaryExpression{
	
	public Checker(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
		
		setType(Type.BOOL);
		if(expr.getType() != Type.OBJECT) Program.printTypeCheckError(sourceLocation);
	}
}
