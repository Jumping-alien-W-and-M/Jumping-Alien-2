package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class Or extends BinaryExpression {

	public Or(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
}
