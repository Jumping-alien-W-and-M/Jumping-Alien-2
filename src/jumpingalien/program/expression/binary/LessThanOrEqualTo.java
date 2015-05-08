package jumpingalien.program.expression.binary;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.util.Util;

public class LessThanOrEqualTo extends CompBinaryExpression {

	public LessThanOrEqualTo(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
	@Override
	public Boolean getValue() {
		return (double) getFirstExpression().getValue() - (double) getSecondExpression().getValue()
				< Util.DEFAULT_EPSILON;
	}
	
}
