package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class IsWater extends UnaryExpression {
	
	public IsWater(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
}