package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class IsJumping extends UnaryExpression {
	
	public IsJumping(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
}