package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class And extends BinaryExpression {

	public And(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
}
