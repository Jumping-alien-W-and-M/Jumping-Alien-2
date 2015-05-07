package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class IsSlime extends UnaryExpression {
	
	public IsSlime(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
}