package jumpingalien.program.expression.binary;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class And extends BoolBinaryExpression {

	public And(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
	@Override
	public Boolean getValue() {
		return (boolean) getFirstExpression().getValue() && (boolean) getSecondExpression().getValue();
	}
	
}
