package jumpingalien.program.expression.binary;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.Expression;

public class Equals extends CompBinaryExpression {

	public Equals(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
	@Override
	public Boolean getValue() {
		if (getFirstExpression().getType() == Type.DOUBLE)
			return getFirstExpression().getValue().equals(getSecondExpression().getValue());
		return (Double.compare((double) getFirstExpression().getValue(), (double) getSecondExpression().getValue()) == 0);
	}
	
}
