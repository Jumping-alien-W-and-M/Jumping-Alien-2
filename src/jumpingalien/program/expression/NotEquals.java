package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class NotEquals extends BinaryExpression {

	public NotEquals(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
}
