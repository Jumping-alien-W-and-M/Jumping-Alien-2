package jumpingalien.program.expression.binary;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class GreaterThanOrEqualTo extends CompBinaryExpression {

	public GreaterThanOrEqualTo(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
}
