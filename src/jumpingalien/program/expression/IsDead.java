package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class IsDead extends UnaryExpression {
	
	public IsDead(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
}