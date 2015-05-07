package jumpingalien.program.expression.unary;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class IsPassable extends UnaryExpression {
	
	public IsPassable(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
}