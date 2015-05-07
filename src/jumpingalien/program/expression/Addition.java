package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class Addition extends BinaryExpression {

	public Addition(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
}
