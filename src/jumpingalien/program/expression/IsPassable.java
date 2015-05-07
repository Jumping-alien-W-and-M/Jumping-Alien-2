package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class IsPassable extends UnaryExpression {
	
	public IsPassable(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
}