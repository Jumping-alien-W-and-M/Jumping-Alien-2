package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class Not extends UnaryExpression {
	
	public Not(Expression expr, SourceLocation sourceLocation) {
		super(expr, sourceLocation);
	}
}
