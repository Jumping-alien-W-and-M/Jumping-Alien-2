package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class GreaterThan extends BinaryExpression {

	public GreaterThan(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
}
