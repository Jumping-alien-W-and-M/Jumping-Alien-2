package jumpingalien.program.expression.binary;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.util.Util;

public class LessThanOrEqualTo extends OrderBinaryExpression {

	public LessThanOrEqualTo(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
	@Override
	public Boolean getValue(GameObject executingObject) {
		return (double) getFirstExpression().getValue(executingObject) - 
					(double) getSecondExpression().getValue(executingObject) < Util.DEFAULT_EPSILON;
	}
	
}
