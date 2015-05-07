package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class LessThan extends BinaryExpression {

	public LessThan(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
}
