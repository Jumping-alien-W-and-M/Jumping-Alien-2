package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class Multiplication extends BinaryExpression {

	public Multiplication(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
}
