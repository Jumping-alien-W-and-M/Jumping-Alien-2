package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class IsAir extends UnaryExpression {
	
	public IsAir(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
}