package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class Equals extends BinaryExpression {

	public Equals(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
}
