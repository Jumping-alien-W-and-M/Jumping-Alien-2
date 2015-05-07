package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class IsPlant extends UnaryExpression {
	
	public IsPlant(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
}