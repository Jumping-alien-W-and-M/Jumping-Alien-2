package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class LessThanOrEqualTo extends BinaryExpression {

	public LessThanOrEqualTo(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
}
