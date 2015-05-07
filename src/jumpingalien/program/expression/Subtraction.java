package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class Subtraction extends BinaryExpression {

	public Subtraction(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
}
